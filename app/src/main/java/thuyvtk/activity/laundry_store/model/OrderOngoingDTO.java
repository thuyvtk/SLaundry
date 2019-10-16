package thuyvtk.activity.laundry_store.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class OrderOngoingDTO implements Serializable {
    @SerializedName("Id")
    private String orderId;
    @SerializedName("TotalPrice")
    private float totalPrice;
    @SerializedName("Status")
    private String status;
    @SerializedName("CustomerId")
    private String customerId;
    @SerializedName("TakeTime")
    private String takeTime;
    @SerializedName("DeliveryTime")
    private String deliveryTime;
    @SerializedName("OrderServices")
    private List<OrderServiceDTO> listOrderServices;

    public OrderOngoingDTO(String orderId, float totalPrice, String status, String customerId, String takeTime, String deliveryTime, List<OrderServiceDTO> listOrderServices) {
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.status = status;
        this.customerId = customerId;
        this.takeTime = takeTime;
        this.deliveryTime = deliveryTime;
        this.listOrderServices = listOrderServices;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(String takeTime) {
        this.takeTime = takeTime;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public List<OrderServiceDTO> getListOrderServices() {
        return listOrderServices;
    }

    public void setListOrderServices(List<OrderServiceDTO> listOrderServices) {
        this.listOrderServices = listOrderServices;
    }
}
