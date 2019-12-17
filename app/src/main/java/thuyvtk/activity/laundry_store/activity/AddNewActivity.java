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

import java.io.InputStream;
import java.util.ArrayList;
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

public class AddNewActivity extends Activity implements ServiceView, ServiceTypeView {
    ImageButton btnBackActivity;
    EditText edtDescription;
    EditText edtPrice;
    Button btnSaveService;
    ImageButton btnSelectImage;
    TextView txtImgUrl;
    LinearLayout ln_waiting;
    ServicePresenter servicePresenter;
    ServiceTypePresenter serviceTypePresenter;
    String serviceType;
    ServiceTypeDTO serviceTypeDTO = new ServiceTypeDTO();
    SharePreferenceLib sharePreferenceLib;
    Context context = this;
    private static final int SELECT_IMAGE = 100;
    boolean flagChangeImageProfile = false;
    Uri imageUri;
    String imageName;
    static final String CONNECTION_STRING = "DefaultEndpointsProtocol=https;AccountName=image2001;AccountKey=gQZGWuOQdOI9pCzBu+iU3W24uKQ+d/NIinGMb9lgTi8wZGT1kFLJvafbcquuYNiS6a1plYpR6iqF1EpGWxR+XQ==;EndpointSuffix=core.windows.net";
    static final String IMAGE_FOLDER = "image";
    final String serverName = "https://image2001.blob.core.windows.net/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_service);
        defineView();
        sharePreferenceLib = new SharePreferenceLib(this);
        serviceType = getIntent().getStringExtra("serviceName");
        serviceTypePresenter = new ServiceTypePresenter(this);
        getServiceTypeId();
        selectProfileImage();
        servicePresenter = new ServicePresenter(this);
        createNewService();
    }

    private void defineView() {
        btnBackActivity = findViewById(R.id.btnBackActivity);
        edtDescription = findViewById(R.id.edtDescription);
        edtPrice = findViewById(R.id.edtPrice);
        btnSaveService = findViewById(R.id.btnSaveService);
        ln_waiting = findViewById(R.id.ln_waiting);
        btnSelectImage = findViewById(R.id.btnSelectImage);
        txtImgUrl = findViewById(R.id.txtImgUrl);
    }

    public void backPreActivity(View view) {
        this.finish();
    }

    private void getServiceTypeId() {
        serviceTypePresenter.getServiceTypeByName(serviceType);
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
    private void createNewService() {
        btnSaveService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateData()) {
                    if (serviceTypeDTO.getServiceTypeId() == null) {
                        // toDo
                        serviceTypePresenter.insertServiceType(new ServiceTypeDTO("", serviceType, false, new ArrayList<ServiceDTO>()));
                    } else if (serviceTypeDTO.isDelete()) {
                        serviceTypePresenter.updateServiceTypeStatus(new ServiceTypeDTO(serviceTypeDTO.getServiceTypeId(), "", false, new ArrayList<ServiceDTO>()));
                        uploadImage();
                    } else {
                        uploadImage();
                    }
                }
            }
        });

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
                                insertService();
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

    private void insertService() {
        String imageURL = serverName + IMAGE_FOLDER + "/" + imageName;
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

    private boolean validateData() {
        if (edtDescription.getText().toString().isEmpty()
                || edtPrice.getText().toString().isEmpty() || txtImgUrl.getText().toString().isEmpty()) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Some data are empty!");
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            return false;
        }
        return true;
    }

    @Override
    public void returnService(ServiceDTO serviceDTO) {
        ln_waiting.setVisibility(View.GONE);
    }

    @Override
    public void returnServiceType(ServiceTypeDTO dto) {
        serviceTypeDTO = dto;
    }

    @Override
    public void returnListStore(List<ServiceTypeDTO> result) {

    }

    @Override
    public void deleteServiceSuccess(String message) {

    }
}
