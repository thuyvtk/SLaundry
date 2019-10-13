package thuyvtk.activity.laundry_store.presenter;

import android.content.Context;
import android.widget.Toast;
import java.util.List;
import thuyvtk.activity.laundry_store.callbacks.CallbackData;
import thuyvtk.activity.laundry_store.model.ServiceDTO;
import thuyvtk.activity.laundry_store.service.ServiceService;
import thuyvtk.activity.laundry_store.service.serviceImpl.ServiceServiceImpl;
import thuyvtk.activity.laundry_store.view.ServiceView;

public class ServicePresenter {
    ServiceService serviceService;
    ServiceView serviceView;

    public ServicePresenter(ServiceView serviceView) {
        serviceService = new ServiceServiceImpl();
        this.serviceView = serviceView;
    }

    public void insertService(final Context context, ServiceDTO serviceDTO) {
        serviceService.insertService(serviceDTO, new CallbackData<ServiceDTO>() {
            @Override
            public void onSuccess(ServiceDTO serviceDTO) {
                serviceView.returnService(serviceDTO);
                Toast.makeText(context, "Insert successful!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFail(String message) {
                System.out.println("Fail");
            }
        });
    }

    public void getServiceByStore(String storeId) {
        serviceService.getServiceByStore(storeId, new CallbackData<List<ServiceDTO>>() {
            @Override
            public void onSuccess(List<ServiceDTO> result) {
                serviceView.returnListStore(result);
            }

            @Override
            public void onFail(String message) {

            }
        });
    }
}
