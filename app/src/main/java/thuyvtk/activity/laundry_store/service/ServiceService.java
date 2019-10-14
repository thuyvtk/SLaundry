package thuyvtk.activity.laundry_store.service;

import java.util.List;

import thuyvtk.activity.laundry_store.callbacks.CallbackData;
import thuyvtk.activity.laundry_store.model.ServiceDTO;
import thuyvtk.activity.laundry_store.model.ServiceTypeDTO;

public interface ServiceService {

    void insertService(ServiceDTO customer, CallbackData<ServiceDTO> callbackData);
    void getServiceByStore(String storeId, CallbackData<List<ServiceTypeDTO>> callbackData);
}
