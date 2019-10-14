package thuyvtk.activity.laundry_store.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
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

<<<<<<< HEAD
import java.util.ArrayList;
=======
import java.io.InputStream;
>>>>>>> master
import java.util.List;

import thuyvtk.activity.laundry_store.R;
import thuyvtk.activity.laundry_store.config.ImageManager;
import thuyvtk.activity.laundry_store.library.SharePreferenceLib;
import thuyvtk.activity.laundry_store.model.ServiceDTO;
import thuyvtk.activity.laundry_store.model.ServiceTypeDTO;
import thuyvtk.activity.laundry_store.presenter.ServicePresenter;
import thuyvtk.activity.laundry_store.presenter.ServiceTypePresenter;
import thuyvtk.activity.laundry_store.view.ServiceTypeView;
import thuyvtk.activity.laundry_store.view.ServiceView;

public class AddOtherServiceActivity extends Activity implements ServiceTypeView, ServiceView {
    ImageButton btnBackPreActivity;
    EditText edtServiceType;
    EditText edtDescription;
    EditText edtPrice;
    Button btnSaveService;
    LinearLayout ln_waiting;
    ServiceTypePresenter serviceTypePresenter;
    ServicePresenter servicePresenter;
    Context context;
    ImageButton btnSelectImage;
    private static final int SELECT_IMAGE = 100;
    boolean flagChangeImageProfile = false;
    Uri imageUri;
    TextView txtImgUrl;
    String imageName;
    static final String CONNECTION_STRING = "DefaultEndpointsProtocol=https;AccountName=sqlvadtabpe45ilkho;AccountKey=Q0GtVfudYOKaYykP6CLCyk7uG/0Dak6C9WuAGDj5wQizMJDFEtEPaTGkGtdmNAatlbSXo4xznJAvOw4slPYAIg==;EndpointSuffix=core.windows.net";
    static final String IMAGE_FOLDER = "imagefolder";
    final String serverName = "https://sqlvadtabpe45ilkho.blob.core.windows.net/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_other_service);
        context = this;
        serviceTypePresenter = new ServiceTypePresenter(this);
        servicePresenter = new ServicePresenter(this);
        defineView();
        selectProfileImage();
        backPreActivity();
        saveOtherService();
    }

    private void defineView() {
        btnBackPreActivity = findViewById(R.id.btnBackPreActivity);
        edtServiceType = findViewById(R.id.edtServiceType);
        edtDescription = findViewById(R.id.edtDescription);
        edtPrice = findViewById(R.id.edtPrice);
        btnSaveService = findViewById(R.id.btnSaveService);
        ln_waiting = findViewById(R.id.ln_waiting);
        btnSelectImage = findViewById(R.id.btnSelectImage);
        txtImgUrl = findViewById(R.id.txtImgUrl);
    }

    private void backPreActivity() {
        btnBackPreActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddOtherServiceActivity.this.finish();
            }
        });
    }

    private void saveOtherService() {
        btnSaveService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateData()) {
<<<<<<< HEAD
                    serviceTypePresenter.insertServiceType(new ServiceTypeDTO("", edtServiceType.getText().toString(), false, new ArrayList<ServiceDTO>()));
                    ln_waiting.setVisibility(View.VISIBLE);
=======
                    uploadImage();
>>>>>>> master
                }
            }
        });

    }

    private void insertServiceType() {
        serviceTypePresenter.insertServiceType(new ServiceTypeDTO("", edtServiceType.getText().toString(), false));
        ln_waiting.setVisibility(View.VISIBLE);
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
                                insertServiceType();
                            }

                        });
                    } catch (Exception ex) {
                        handler.post(new Runnable() {
                            public void run() {
                                Toast.makeText(getApplicationContext(), "Upload image failed!", Toast.LENGTH_SHORT).show();
                                txtImgUrl.setText(null);
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
                    this.txtImgUrl.setText(this.imageUri.toString());
                }
        }
    }
    private boolean validateData() {
        if (edtServiceType.getText().toString().isEmpty()
                || edtDescription.getText().toString().isEmpty()
                || edtPrice.getText().toString().isEmpty()
                || txtImgUrl.getText().toString().isEmpty()) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Some data are empty!");
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            return false;
        }
        return true;
    }


    @Override
    public void returnServiceType(ServiceTypeDTO serviceTypeDTO) {
        insertService(serviceTypeDTO);
    }

    private void insertService(ServiceTypeDTO serviceTypeDTO) {
        String imageURL = serverName + IMAGE_FOLDER + "/" + imageName;
        SharePreferenceLib sharePreferenceLib = new SharePreferenceLib(context);
        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setServiceId("");
        serviceDTO.setServiceTypeId(serviceTypeDTO.getServiceTypeId());
        serviceDTO.setStoreId(sharePreferenceLib.getUser().getStoreId());
        serviceDTO.setDescription(edtDescription.getText().toString().trim());
        serviceDTO.setPrice(Float.parseFloat(edtPrice.getText().toString().trim()));
        if (flagChangeImageProfile) {
            serviceDTO.setImgUrl(imageURL);
        }
        createService(serviceDTO);
    }
    boolean flag = false;
    private void createService(ServiceDTO serviceDTO){
        if (!flag) {
            servicePresenter.insertService(context,serviceDTO);
            ln_waiting.setVisibility(View.VISIBLE);
            flag = true;
        }

    }

    @Override
    public void returnService(ServiceDTO serviceDTO) {
        ln_waiting.setVisibility(View.GONE);
    }

    @Override
    public void returnListStore(List<ServiceTypeDTO> result) {

    }
}
