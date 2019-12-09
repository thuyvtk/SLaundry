package thuyvtk.activity.laundry_store.presenter;

import android.content.Context;
import android.widget.Toast;

import java.util.List;

import thuyvtk.activity.laundry_store.callbacks.CallbackData;
import thuyvtk.activity.laundry_store.model.OrderDetailDTO;
import thuyvtk.activity.laundry_store.service.OrderService;
import thuyvtk.activity.laundry_store.service.serviceImpl.OderServiceImpl;
import thuyvtk.activity.laundry_store.view.OrderView;

public class OrderPresenter {
    OrderService orderService;
    OrderView view;
    Context context;

    public OrderPresenter(OrderView view) {
        this.view = view;
        orderService = new OderServiceImpl();
    }

    public OrderPresenter(Context context) {
        orderService = new OderServiceImpl();
        this.context = context;
    }

    public void getOrderHistory(String userId, String dateStart, String dateEnd) {
        orderService.loadHistory(userId, dateStart, dateEnd, new CallbackData<List<OrderDetailDTO>>() {
            @Override
            public void onSuccess(List<OrderDetailDTO> orderDTOS) {
                view.loadOrderHistory(orderDTOS);
            }

            @Override
            public void onFail(String message) {
                view.onFail(message);
            }
        });
    }

    public void getOrderByDateAndStatus(String customerId, String dateStart, String dateEnd, String status) {
        orderService.getOrderByDateAndStatus(customerId, dateStart, dateEnd, status, new CallbackData<List<OrderDetailDTO>>() {
            @Override
            public void onSuccess(List<OrderDetailDTO> orderDetailDTOS) {
                view.returnListOrder(orderDetailDTOS);
            }

            @Override
            public void onFail(String message) {

            }
        });
    }

    public void updateOrderStatus(String orderId, String status){
        orderService.setOrderStatus(orderId, status, new CallbackData<String>() {
            @Override
            public void onSuccess(String s) {
               view.rateSuccess("update order success");
            }

            @Override
            public void onFail(String message) {

            }
        });
    }
}
