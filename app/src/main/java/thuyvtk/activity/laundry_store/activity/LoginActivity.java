package thuyvtk.activity.laundry_store.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.lang.reflect.Array;
import java.util.Arrays;

import thuyvtk.activity.laundry_store.R;
import thuyvtk.activity.laundry_store.library.SharePreferenceLib;
import thuyvtk.activity.laundry_store.model.StoreDTO;
import thuyvtk.activity.laundry_store.presenter.StorePresenter;
import thuyvtk.activity.laundry_store.view.StoreView;

public class LoginActivity extends Activity implements StoreView {
    Button btnLogin_phone;
    LoginButton loginButton;
    CallbackManager callbackManager;
    StorePresenter presenter;
    LinearLayout ln_waiting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        checkLocalUser();
        defineView();
        presenter = new StorePresenter(this);
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setPermissions(Arrays.asList("email", "public_profile"));
            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    AccessToken token = loginResult.getAccessToken();
                    loginUsingFaceBook(token);
                }

                @Override
                public void onCancel() {

                }

                @Override
                public void onError(FacebookException error) {

                }
            });
        }

    private void loginUsingFaceBook(AccessToken token) {
        String fb_id = "FA" + token.toString();
        presenter.getStoreByFirebaseId(fb_id);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void defineView() {
        btnLogin_phone = findViewById(R.id.btnLogin_phone);
        ln_waiting = findViewById(R.id.ln_waiting);
        openPageLoginByPhone();
    }

    private void openPageLoginByPhone() {
        btnLogin_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginByPhoneActivity.class);
                startActivity(intent);
            }
        });
    }

    private void checkLocalUser() {
        SharePreferenceLib sharePreferenceLib = new SharePreferenceLib(this);
        StoreDTO currentUser = sharePreferenceLib.getUser();
        if (currentUser != null) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void returnStore(StoreDTO storeDTO) {
        SharePreferenceLib sharePreferenceLib = new SharePreferenceLib(this);
        sharePreferenceLib.saveUser(storeDTO);
        ln_waiting.setVisibility(View.GONE);
        this.finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
