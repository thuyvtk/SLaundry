package thuyvtk.activity.laundry_store.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import thuyvtk.activity.laundry_store.R;
import thuyvtk.activity.laundry_store.config.ImageManager;
import thuyvtk.activity.laundry_store.library.SharePreferenceLib;
import thuyvtk.activity.laundry_store.model.StoreDTO;
import thuyvtk.activity.laundry_store.presenter.StorePresenter;
import thuyvtk.activity.laundry_store.view.StoreView;

public class EditProfileActivity extends Activity implements StoreView {
    ImageButton imgBackActivity;
    StoreDTO currentUser;
    TextView edtUsername;
    TextView txtPhone;
    Button btnEditLocation;
    EditText edtEmail;
    Button btnUpdateStore;
    ImageButton btnSelectImage;
    LinearLayout ln_waiting;
    private static final int SELECT_IMAGE = 100;
    Uri imageUri;
    CircleImageView image_profile;
    String imageName;
    static final String CONNECTION_STRING = "DefaultEndpointsProtocol=https;AccountName=sqlvadtabpe45ilkho;AccountKey=Q0GtVfudYOKaYykP6CLCyk7uG/0Dak6C9WuAGDj5wQizMJDFEtEPaTGkGtdmNAatlbSXo4xznJAvOw4slPYAIg==;EndpointSuffix=core.windows.net";
    static final String IMAGE_FOLDER = "imagefolder";
    final String serverName = "https://sqlvadtabpe45ilkho.blob.core.windows.net/";
    StorePresenter storePresenter;
    SharePreferenceLib sharePreferenceLib;
    boolean flagChangeImageProfile = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        defineView();
        sharePreferenceLib = new SharePreferenceLib(getApplicationContext());
        storePresenter = new StorePresenter(this);
        getStoreProfile();
        backPreActivity();
        selectProfileImage();
        updateStoreProfile();
    }

    public void defineView() {
        imgBackActivity = findViewById(R.id.imgBackActivity);
        edtUsername = findViewById(R.id.edtUsername);
        txtPhone = findViewById(R.id.txtPhone);
        edtEmail = findViewById(R.id.edtEmail);
        btnEditLocation = findViewById(R.id.btnEditLocation);
        btnUpdateStore = findViewById(R.id.btnUpdateStore);
        btnSelectImage = findViewById(R.id.btnSelectImage);
        image_profile = findViewById(R.id.image_profile);
        ln_waiting = findViewById(R.id.ln_waiting);
    }

    public void backPreActivity() {
        imgBackActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditProfileActivity.this.finish();
            }
        });
    }

    private void getStoreProfile() {
        SharePreferenceLib sharePreferenceLib = new SharePreferenceLib(this);
        currentUser = sharePreferenceLib.getUser();
        edtUsername.setText(currentUser.getStoreName());
        txtPhone.setText(currentUser.getPhone());
        edtEmail.setText(currentUser.getEmail());
        btnEditLocation.setText(currentUser.getAddress());
        Picasso.with(this).load(sharePreferenceLib.getUser().getImageUrl()).into(image_profile);
    }

    private void selectProfileImage() {
        btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_IMAGE);
                flagChangeImageProfile = true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case SELECT_IMAGE:
                if (resultCode == RESULT_OK) {
                    this.imageUri = imageReturnedIntent.getData();
                    this.image_profile.setImageURI(this.imageUri);
                }
        }
    }

    private void uploadImage() {
        try {
            final InputStream imageStream = getContentResolver().openInputStream(this.imageUri);
            final int imageLength = imageStream.available();
            final Handler handler = new Handler();
            Thread th = new Thread(new Runnable() {
                public void run() {
                    try {
                        imageName = ImageManager.UploadImage(imageStream, imageLength, CONNECTION_STRING, IMAGE_FOLDER);
                        handler.post(new Runnable() {
                            public void run() {
                                updateStoreAPI();
                            }

                        });
                    } catch (Exception ex) {
                        handler.post(new Runnable() {
                            public void run() {
                                Toast.makeText(getApplicationContext(), "Upload image failed!", Toast.LENGTH_SHORT).show();
                                image_profile.setImageURI(null);
                                imageName = "*";// if upload fail set imagename to *
                            }
                        });
                    }
                }
            });
            th.start();

        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void updateStoreProfile() {
        btnUpdateStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validData()) {
                    if (flagChangeImageProfile) {
                        uploadImage();
                    } else {
                        updateStoreAPI();
                    }
                }
            }
        });
    }

    private void updateStoreAPI() {
        String imageURL = serverName + IMAGE_FOLDER + "/" + imageName;
        StoreDTO storeDTO = sharePreferenceLib.getUser();
        storeDTO.setStoreName(edtUsername.getText().toString());
        storeDTO.setEmail(edtEmail.getText().toString());
        storeDTO.setAddress(btnEditLocation.getText().toString());
        if (flagChangeImageProfile) {
            storeDTO.setImageUrl(imageURL);
        }
        updateStore(storeDTO);
    }

    boolean flag = false;

    private void updateStore(StoreDTO dto) {
        if (!flag) {
            storePresenter.updateStore(dto);
            ln_waiting.setVisibility(View.VISIBLE);
            flag = true;
        }

    }

    private boolean validData() {
        if (edtUsername.getText().toString().isEmpty()
                || edtEmail.getText().toString().isEmpty()) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Some data are empty!");
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            return false;
        }
        return true;
    }

    @Override
    public void returnStore(StoreDTO storeDTO) {
        sharePreferenceLib.saveUser(storeDTO);
        ln_waiting.setVisibility(View.GONE);
        flag = false;
    }
}
