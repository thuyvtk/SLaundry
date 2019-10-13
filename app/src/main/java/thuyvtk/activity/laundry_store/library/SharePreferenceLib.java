package thuyvtk.activity.laundry_store.library;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;


import thuyvtk.activity.laundry_store.model.StoreDTO;

import static android.content.Context.MODE_PRIVATE;

public class SharePreferenceLib {
    final String SHARE_NAME = "SHARED";
    final String JSON_NAME = "USER";
    SharedPreferences sharedPreferences;

    public SharePreferenceLib(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARE_NAME, MODE_PRIVATE);
    }

    public void saveUser(StoreDTO user) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString(JSON_NAME, json);
        editor.commit();
    }

    public StoreDTO getUser() {
        String json = sharedPreferences.getString(JSON_NAME, "");
        Type type = new TypeToken<StoreDTO>() {
        }.getType();
        StoreDTO dto = new Gson().fromJson(json, type);
        return dto;
    }

    public void logouṭ() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(JSON_NAME);
        editor.commit();
    }
}
