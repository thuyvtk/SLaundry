package thuyvtk.activity.laundry_store.view;

import java.util.List;

import thuyvtk.activity.laundry_store.model.OrderDetailDTO;

public interface OrderView {
    void loadOrderHistory(List<OrderDetailDTO> orderList);
    void onFail(String msg);
    void createOrderSuccess();
    void returnListOrder(List<OrderDetailDTO> listOrderDetail);
    void rateSuccess(String message);
}
