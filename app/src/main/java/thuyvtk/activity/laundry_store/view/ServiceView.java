package thuyvtk.activity.laundry_store.view;

import java.util.List;

import thuyvtk.activity.laundry_store.model.ServiceDTO;
import thuyvtk.activity.laundry_store.model.ServiceTypeDTO;

public interface ServiceView {
    void returnService(ServiceDTO serviceDTO);
    void returnListStore(List<ServiceTypeDTO> result);
}
