
package thuyvtk.activity.laundry_store.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import thuyvtk.activity.laundry_store.R;
import thuyvtk.activity.laundry_store.model.ServiceDTO;
import thuyvtk.activity.laundry_store.model.ServiceTypeDTO;
import thuyvtk.activity.laundry_store.model.ServiceVN;
import thuyvtk.activity.laundry_store.presenter.ServicePresenter;
import thuyvtk.activity.laundry_store.view.ServiceView;

public class UpdateServiceActivity extends Activity implements ServiceView {
    EditText txtDescription, txtPrice;
    ServicePresenter presenter;
    ImageView imgService;
    ServiceDTO dto;
    TextView txtServiceName;
    LinearLayout ln_waiting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_service);
        defineView();
        presenter = new ServicePresenter(this);
    }

    private void defineView() {
        txtServiceName = findViewById(R.id.txtServiceName);
        txtDescription = findViewById(R.id.txtDescription);
        txtPrice = findViewById(R.id.txtPrice);
        imgService = findViewById(R.id.imgService);
        ln_waiting = findViewById(R.id.ln_waiting);
        Intent intent = getIntent();
        dto = (ServiceDTO) intent.getSerializableExtra("service");
        txtServiceName.setText(dto.getServiceId());
        txtDescription.setText(dto.getDescription());
        txtPrice.setText(dto.getPrice()+"");
        if(dto.getImgUrl()!= null && !dto.getImgUrl().equals("")){
            Picasso.with(this).load(dto.getImgUrl()).into(imgService);
        }
    }

    public void clickToUpdate(View view) {
        String description = txtDescription.getText().toString();
        String price = txtPrice.getText().toString();
        float prices = dto.getPrice();
        if(price!=""){
            prices = Float.parseFloat(price);
        }
        ServiceVN serviceVN = new ServiceVN(dto.getServiceId(),description,prices);
        presenter.updateService(serviceVN);
        ln_waiting.setVisibility(View.VISIBLE);
    }

    @Override
    public void returnService(ServiceDTO serviceDTO) {

    }

    @Override
    public void returnListStore(List<ServiceTypeDTO> result) {

    }

    @Override
    public void deleteServiceSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        ln_waiting.setVisibility(View.GONE);
    }

    public void clickToBack(View view) {
        this.finish();
    }
}
