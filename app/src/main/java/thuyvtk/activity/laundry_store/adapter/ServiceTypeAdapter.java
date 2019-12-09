package thuyvtk.activity.laundry_store.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import thuyvtk.activity.laundry_store.R;
import thuyvtk.activity.laundry_store.activity.UpdateServiceActivity;
import thuyvtk.activity.laundry_store.library.SharePreferenceLib;
import thuyvtk.activity.laundry_store.model.ServiceDTO;
import thuyvtk.activity.laundry_store.model.ServiceTypeDTO;
import thuyvtk.activity.laundry_store.presenter.ServicePresenter;
import thuyvtk.activity.laundry_store.view.ServiceView;

public class ServiceTypeAdapter extends RecyclerView.Adapter<ServiceTypeAdapter.ServiceTypeViewHolder> {
    private List<ServiceTypeDTO> listServiceType;
    private Context context;
    ServiceView serviceView;
    ServicePresenter presenter;

    public ServiceTypeAdapter(Context context, List<ServiceTypeDTO> listServiceType) {
        this.context = context;
        this.listServiceType = listServiceType;
    }
    public ServiceTypeAdapter(Context context, List<ServiceTypeDTO> listServiceType, ServiceView serviceView) {
        this.context = context;
        this.listServiceType = listServiceType;
        this.serviceView = serviceView;
    }
    @NonNull
    @Override
    public ServiceTypeAdapter.ServiceTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service_type, parent, false);
        return new ServiceTypeAdapter.ServiceTypeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceTypeViewHolder holder, int position) {
        ServiceTypeDTO serviceTypeDTO = listServiceType.get(position);
        presenter = new ServicePresenter(serviceView);
        holder.txtServiceType.setText(serviceTypeDTO.getServiceTypeName().toUpperCase());
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        for (ServiceDTO item : serviceTypeDTO.getListService()) {
            View child = inflater.inflate(R.layout.item_service,null);
            child = addViewChild(child,item);
            holder.rv_serviceType.addView(child);
        }

    }
    CardView cv_service;
    TextView service_name, service_price;
    ImageView service_img;
    ImageButton updateService;
    ImageButton btnDelete;
    private View addViewChild(View child, final ServiceDTO serviceDTO){
        cv_service = child.findViewById(R.id.cv_service);
        service_name = child.findViewById(R.id.service_name);
        service_price = child.findViewById(R.id.service_price);
        service_img = child.findViewById(R.id.service_img);
        updateService = child.findViewById(R.id.updateService);
        btnDelete = child.findViewById(R.id.btnDelete);

        // set data
        if (serviceDTO == null) {
            Picasso.with(context).load(new SharePreferenceLib(context).getUser().getImageUrl()).into(service_img);
        } else {
            Picasso.with(context).load(serviceDTO.getImgUrl()).into(service_img);
        }
        service_name.setText(serviceDTO.getDescription());
        service_price.setText(serviceDTO.getPrice() + " VND");
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showDeleteDialog(serviceDTO);
            }
        });
        updateService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateServiceActivity.class);
                intent.putExtra("service",serviceDTO);
                context.startActivity(intent);
            }
        });
        return child;
    }

    public void showDeleteDialog(final ServiceDTO serviceDTO) {
        final AlertDialog.Builder deleteDialog = new AlertDialog.Builder(context);
        deleteDialog.setMessage("This service will be delete!");
        deleteDialog.setCancelable(false);
        deleteDialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.deleteService(serviceDTO.getServiceId());
            }
        });
        deleteDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = deleteDialog.create();
        alertDialog.show();
    }

    @Override
    public int getItemCount() {
      return   listServiceType.size();
    }

    public static class ServiceTypeViewHolder extends RecyclerView.ViewHolder {
        TextView txtServiceType;
       // RecyclerView rv_serviceType;
        LinearLayout rv_serviceType;

        public ServiceTypeViewHolder(View serviceView) {
            super(serviceView);
            txtServiceType = serviceView.findViewById(R.id.txtServiceType);
            rv_serviceType = serviceView.findViewById(R.id.rv_serviceType);
        }
    }
}
