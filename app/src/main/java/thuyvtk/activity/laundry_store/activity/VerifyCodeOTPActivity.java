package thuyvtk.activity.laundry_store.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import thuyvtk.activity.laundry_store.R;
import thuyvtk.activity.laundry_store.library.SharePreferenceLib;
import thuyvtk.activity.laundry_store.model.StoreDTO;
import thuyvtk.activity.laundry_store.presenter.StorePresenter;
import thuyvtk.activity.laundry_store.view.StoreView;

public class VerifyCodeOTPActivity extends Activity implements StoreView {
    EditText txtCodeOTP;
    TextView txtPhoneNumber;
    FirebaseAuth mAuth;
    String codeSent;
    String phone;
    Button btnContinue;
    LinearLayout ln_waiting;
    StorePresenter presenter;

    private void defineView() {
        txtCodeOTP = findViewById(R.id.txtCodeOTP);
        btnContinue = findViewById(R.id.btnContinue);
        txtPhoneNumber = findViewById(R.id.txtPhoneNumber);
        ln_waiting = findViewById(R.id.ln_waiting);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_code_otp);
        defineView();
        phone = getIntent().getStringExtra("phone");
        sendRequest();
       presenter = new StorePresenter(this);
        mAuth = FirebaseAuth.getInstance();
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = txtCodeOTP.getText().toString();
                if(!code.equals("")){
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, code);
                    signInWithPhoneAuthCredential(credential);

                }else{
                    Toast.makeText(VerifyCodeOTPActivity.this, "Please enter sent code", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendRequest() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//            Toast.makeText(, "auto fill", Toast.LENGTH_SHORT).show();
            signInWithPhoneAuthCredential(phoneAuthCredential);
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

        }

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            codeSent = s;
        }
    };

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        ln_waiting.setVisibility(View.VISIBLE);
        try{
            mAuth.signInWithCredential(credential)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = task.getResult().getUser();
                                Toast.makeText(VerifyCodeOTPActivity.this, user.getUid(), Toast.LENGTH_SHORT).show();
                                String id = user.getUid();
                               presenter.getStoreByFirebaseId(id);
                            } else {
                                // Sign in failed, display a message and update the UI
//                            Toast.makeText(MainActivity.this, "signing fail", Toast.LENGTH_SHORT).show();
                                if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
//                                Toast.makeText(MainActivity.this, "invalid code", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
        }catch (Exception e){
            Toast.makeText(this, "Wrong login code", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void returnStore(StoreDTO storeDTO) {
        SharePreferenceLib sharePreferenceLib = new SharePreferenceLib(this);
        storeDTO.setPhone(phone);
        sharePreferenceLib.saveUser(storeDTO);
        ln_waiting.setVisibility(View.GONE);
        this.finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
