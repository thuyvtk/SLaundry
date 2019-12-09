package thuyvtk.activity.laundry_store.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import thuyvtk.activity.laundry_store.R;
import thuyvtk.activity.laundry_store.adapter.OrderServiceAdapter;
import thuyvtk.activity.laundry_store.adapter.ServiceAdapter;
import thuyvtk.activity.laundry_store.library.SharePreferenceLib;
import thuyvtk.activity.laundry_store.model.OrderOngoingDTO;
import thuyvtk.activity.laundry_store.model.OrderServiceDTO;
import thuyvtk.activity.laundry_store.model.ServiceDTO;

public class DetailOrderHistoryActivity extends AppCompatActivity {
    OrderOngoingDTO orderOngoingDTO;
    TextView txtStoreName, txtCustomerReceipt, txtPhone, txtAddress, timeTake, timeDelivery, txtTotalReceipt;
    SharePreferenceLib sharePreferenceLib;
    ListView lvReceipt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order_history);
        defineView();
        getOrder();
    }

    public void clickToBack(View view) {
        this.finish();
    }
    private void defineView() {
        txtStoreName = findViewById(R.id.txtStoreName);
        txtCustomerReceipt = findViewById(R.id.txtCustomerReceipt);
        txtPhone = findViewById(R.id.txtPhone);
        txtAddress = findViewById(R.id.txtAddress);
        timeTake = findViewById(R.id.timeTake);
        timeDelivery = findViewById(R.id.timeDelivery);
        txtTotalReceipt = findViewById(R.id.txtTotalReceipt);
        lvReceipt = findViewById(R.id.lvReceipt);
        sharePreferenceLib = new SharePreferenceLib(this);
    }
    private void getOrder() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm - dd/MM/yyyy");
        Intent intent = getIntent();
        orderOngoingDTO = (OrderOngoingDTO) intent.getSerializableExtra("ORDERDTO");
        txtStoreName.setText(sharePreferenceLib.getUser().getStoreName());
        String name = orderOngoingDTO.getCustomerBS().getName();
        String phone = orderOngoingDTO.getCustomerBS().getPhone();
        txtCustomerReceipt.setText(name);
        txtPhone.setText(" - " + phone);
        if (orderOngoingDTO.getAddress() != null) {
            txtAddress.setText(orderOngoingDTO.getAddress());
        }
        String timeTakes = orderOngoingDTO.getTakeTime();
        String timeDeliverys = orderOngoingDTO.getDeliveryTime();
        try {
            Date take = sdf.parse(timeTakes);
            Date deliver = sdf.parse(timeDeliverys);
            timeTake.setText(sdf2.format(take));
            timeDelivery.setText(sdf2.format(deliver));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        float total = 0;
        ArrayList<ServiceDTO> listService = new ArrayList<>();
        for (OrderServiceDTO item : orderOngoingDTO.getListOrderSerice()) {
            ServiceDTO dto = item.getServiceDTO();
            dto.setQuantity(item.getQuantity());
            total += dto.getPrice();
            boolean flag = false;
            for (int i = 0; i < listService.size(); i++) {
                ServiceDTO temp = listService.get(i);
                if (temp.getServiceId().equals(dto.getServiceId())) {
                    flag = true;
                    int quantity = temp.getQuantity();
                    listService.get(i).setQuantity(quantity + dto.getQuantity());
                }
            }
            if (!flag) {
                listService.add(dto);
            }
        }
        txtTotalReceipt.setText(total + " VND");
        OrderServiceAdapter adapter = new OrderServiceAdapter(getApplicationContext(), listService);
        lvReceipt.setAdapter(adapter);
    }
}
