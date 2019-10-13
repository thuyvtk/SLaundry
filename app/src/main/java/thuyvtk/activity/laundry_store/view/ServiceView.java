package thuyvtk.activity.laundry_store.view;

import java.util.List;

import thuyvtk.activity.laundry_store.model.ServiceDTO;

public interface ServiceView {
    void returnService(ServiceDTO serviceDTO);
    void returnListStore(List<ServiceDTO> result);
}
