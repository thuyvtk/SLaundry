package thuyvtk.activity.laundry_store.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import thuyvtk.activity.laundry_store.R;
import thuyvtk.activity.laundry_store.library.SharePreferenceLib;
import thuyvtk.activity.laundry_store.model.StoreDTO;

public class LoginActivity extends Activity {
    Button btnLogin_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        checkLocalUser();
        defineView();
    }

    private void defineView() {
        btnLogin_phone = findViewById(R.id.btnLogin_phone);
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

}
