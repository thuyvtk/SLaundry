package thuyvtk.activity.laundry_store.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import thuyvtk.activity.laundry_store.R;
import thuyvtk.activity.laundry_store.model.ServiceDTO;

public class OrderServiceAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return listService.size();
    }
    Context context;
    ArrayList<ServiceDTO> listService;

    public OrderServiceAdapter(Context context, ArrayList<ServiceDTO> listService) {
        this.context = context;
        this.listService = listService;

    }
    @Override
    public Object getItem(int position) {
        return listService.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = LayoutInflater.from(context).inflate(R.layout.service_item, null);
        ImageView imgService = view.findViewById(R.id.imgService);
        TextView txtServiceName = view.findViewById(R.id.txtServiceName);
        TextView txtPrice = view.findViewById(R.id.txtPrice);
        TextView txtQuantity = view.findViewById(R.id.txtQuantity);
        final ServiceDTO dto = (ServiceDTO) getItem(position);
        if (dto.getImgUrl() != null && !dto.getImgUrl().equals("")) {
            Picasso.with(context).load(dto.getImgUrl()).into(imgService);
        }
        txtServiceName.setText(dto.getDescription());
        txtPrice.setText(dto.getPrice() + "");
        if (dto != null) {
            txtQuantity.setText("X" + dto.getQuantity() + "");
        }
        return view;
    }
}
