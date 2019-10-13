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
import android.widget.Toast;

import java.util.List;

import thuyvtk.activity.laundry_store.R;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_other_service);
        context = this;
        serviceTypePresenter = new ServiceTypePresenter(this);
        servicePresenter = new ServicePresenter(this);
        defineView();
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
                    serviceTypePresenter.insertServiceType(new ServiceTypeDTO("", edtServiceType.getText().toString(), false));
                    ln_waiting.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    private boolean validateData() {
        if (edtServiceType.getText().toString().isEmpty()
                || edtDescription.getText().toString().isEmpty()
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
    public void returnServiceType(ServiceTypeDTO serviceTypeDTO) {
        SharePreferenceLib sharePreferenceLib = new SharePreferenceLib(context);
        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setServiceId("");
        serviceDTO.setServiceTypeId(serviceTypeDTO.getServiceTypeId());
        serviceDTO.setStoreId(sharePreferenceLib.getUser().getStoreId());
        serviceDTO.setDescription(edtDescription.getText().toString().trim());
        serviceDTO.setPrice(Float.parseFloat(edtPrice.getText().toString().trim()));
        servicePresenter.insertService(context, serviceDTO);
    }

    @Override
    public void returnService(ServiceDTO serviceDTO) {
        ln_waiting.setVisibility(View.GONE);
    }

    @Override
    public void returnListStore(List<ServiceDTO> result) {

    }
}
