package thuyvtk.activity.laundry_store.service;

import java.util.List;

import thuyvtk.activity.laundry_store.callbacks.CallbackData;
import thuyvtk.activity.laundry_store.model.ServiceTypeDTO;

public interface ServiceTypeService {
    void getServiceTypeByName(String name, CallbackData<ServiceTypeDTO> callbackData);
    void insertServiceType(ServiceTypeDTO serviceTypeDTO, CallbackData<ServiceTypeDTO> callbackData);
    void updateServiceTypeStatus(ServiceTypeDTO serviceTypeDTO, CallbackData<String> callbackData);
}
