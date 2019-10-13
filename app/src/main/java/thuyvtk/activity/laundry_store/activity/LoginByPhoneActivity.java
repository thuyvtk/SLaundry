package thuyvtk.activity.laundry_store.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import thuyvtk.activity.laundry_store.R;

public class LoginByPhoneActivity extends Activity {
    Button btnSendCode;
    EditText txtPhone;
    String phone;
    final String PHONE_PREFIX_VN = "+84";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_by_phone);
        btnSendCode = findViewById(R.id.btnSendCode);
        txtPhone = findViewById(R.id.txtPhone);
        btnSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtPhone.getText().toString().trim().isEmpty()) {
                    Intent intent = new Intent(getApplicationContext(), VerifyCodeOTPActivity.class);
                    phone = PHONE_PREFIX_VN + txtPhone.getText().toString();
                    intent.putExtra("phone", phone);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter phone number", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}
