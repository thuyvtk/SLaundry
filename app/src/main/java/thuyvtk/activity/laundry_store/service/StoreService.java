package thuyvtk.activity.laundry_store.service;

import java.util.ArrayList;


import thuyvtk.activity.laundry_store.callbacks.CallbackData;
import thuyvtk.activity.laundry_store.model.StoreDTO;

public interface StoreService {
    void getStoreByFirebaseId(String id, CallbackData<StoreDTO> callbackData);
    void updateStore(StoreDTO customer, CallbackData<StoreDTO> callbackData);
}
