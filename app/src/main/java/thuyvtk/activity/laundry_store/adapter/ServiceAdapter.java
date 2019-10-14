package thuyvtk.activity.laundry_store.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import thuyvtk.activity.laundry_store.R;
import thuyvtk.activity.laundry_store.library.SharePreferenceLib;
import thuyvtk.activity.laundry_store.model.ServiceDTO;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.StoreViewHolder> {
    private List<ServiceDTO> listService;
    private Context context;

    public ServiceAdapter(Context context, List<ServiceDTO> listService) {
        this.listService = listService;
        this.context = context;
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service, parent, false);
        return new StoreViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, int position) {
        ServiceDTO serviceDTO = listService.get(position);
        if (serviceDTO.getImgUrl() == null) {
            Picasso.with(context).load(new SharePreferenceLib(context).getUser().getImageUrl()).into(holder.service_img);
        } else {
            Picasso.with(context).load(serviceDTO.getImgUrl()).into(holder.service_img);
        }
        holder.service_name.setText(serviceDTO.getDescription());
        holder.service_price.setText(serviceDTO.getPrice() + " VND");
    }

    @Override
    public int getItemCount() {
        return listService.size();
    }

    public static class StoreViewHolder extends RecyclerView.ViewHolder {
        CardView cv_service;
        TextView service_name, service_price;
        ImageView service_img;

        public StoreViewHolder(View serviceView) {
            super(serviceView);
            cv_service = serviceView.findViewById(R.id.cv_service);
            service_name = serviceView.findViewById(R.id.service_name);
            service_price = serviceView.findViewById(R.id.service_price);
            service_img = serviceView.findViewById(R.id.service_img);
        }
    }
}
