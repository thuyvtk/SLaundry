package thuyvtk.activity.laundry_store.service;

import java.util.List;
import thuyvtk.activity.laundry_store.callbacks.CallbackData;
import thuyvtk.activity.laundry_store.model.OrderDetailDTO;

public interface OrderService {
    void loadHistory(String userId, String dateStart, String dateEnd, CallbackData<List<OrderDetailDTO>> callbackData);
    void getOrderByDateAndStatus(String customerId, String dateStart, String dateEnd,String status, CallbackData<List<OrderDetailDTO>> callbackData);
    void setOrderStatus(String id, String orderStatus,CallbackData<String> callbackData);
}

