package thuyvtk.activity.laundry_store.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.List;

import thuyvtk.activity.laundry_store.R;
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
    LinearLayout ln_waiting;
    ServicePresenter servicePresenter;
    ServiceTypePresenter serviceTypePresenter;
    String serviceType;
    ServiceTypeDTO serviceTypeDTO = new ServiceTypeDTO();
    SharePreferenceLib sharePreferenceLib;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_service);
        defineView();
        sharePreferenceLib = new SharePreferenceLib(this);
        serviceType = getIntent().getStringExtra("serviceName");
        serviceTypePresenter = new ServiceTypePresenter(this);
        getServiceTypeId();
        servicePresenter = new ServicePresenter(this);
        createNewService();
    }

    private void defineView() {
        btnBackActivity = findViewById(R.id.btnBackActivity);
        edtDescription = findViewById(R.id.edtDescription);
        edtPrice = findViewById(R.id.edtPrice);
        btnSaveService = findViewById(R.id.btnSaveService);
        ln_waiting = findViewById(R.id.ln_waiting);
    }

    public void backPreActivity(View view) {
        this.finish();
    }

    private void getServiceTypeId() {
        serviceTypePresenter.getServiceTypeByName(serviceType);
    }

    private void createNewService() {
        btnSaveService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateData()) {
                    if (serviceTypeDTO.getServiceTypeId() == null) {
                        // toDo
                        serviceTypePresenter.insertServiceType(new ServiceTypeDTO("", serviceType, false));
                    } else if (serviceTypeDTO.isDelete()) {
                        serviceTypePresenter.updateServiceTypeStatus(new ServiceTypeDTO(serviceTypeDTO.getServiceTypeId(), "", false));
                        insertService();
                    } else {
                        insertService();
                    }
                }
            }
        });

    }

    private void insertService() {
        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setServiceId("");
        serviceDTO.setServiceTypeId(serviceTypeDTO.getServiceTypeId());
        serviceDTO.setStoreId(sharePreferenceLib.getUser().getStoreId());
        serviceDTO.setDescription(edtDescription.getText().toString().trim());
        serviceDTO.setPrice(Float.parseFloat(edtPrice.getText().toString().trim()));
        servicePresenter.insertService(context,serviceDTO);
        ln_waiting.setVisibility(View.VISIBLE);
    }

    private boolean validateData() {
        if (edtDescription.getText().toString().isEmpty()
                || edtPrice.getText().toString().isEmpty()) {
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
    public void returnListStore(List<ServiceDTO> result) {
    }
}
