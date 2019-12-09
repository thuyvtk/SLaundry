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
    @SerializedName("customer")
    private CustomerBS customerBS;
    @SerializedName("TakeTime")
    private String takeTime;
    @SerializedName("DeliveryTime")
    private String deliveryTime;
    @SerializedName("Address")
    private String address;
    @SerializedName("OrderServices")
    private List<OrderServiceDTO> listOrderSerice;


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

    public CustomerBS getCustomerBS() {
        return customerBS;
    }

    public void setCustomerBS(CustomerBS customerBS) {
        this.customerBS = customerBS;
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

    public OrderOngoingDTO(String orderId, float totalPrice, String status, CustomerBS customerBS, String takeTime, String deliveryTime, List<OrderServiceDTO> listOrderSerice) {
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.status = status;
        this.customerBS = customerBS;
        this.takeTime = takeTime;
        this.deliveryTime = deliveryTime;
        this.listOrderSerice = listOrderSerice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public OrderOngoingDTO(String orderId, float totalPrice, String status, CustomerBS customerBS, String takeTime, String deliveryTime, String address, List<OrderServiceDTO> listOrderSerice) {
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.status = status;
        this.customerBS = customerBS;
        this.takeTime = takeTime;
        this.deliveryTime = deliveryTime;
        this.address = address;
        this.listOrderSerice = listOrderSerice;
    }

    public OrderOngoingDTO(String orderId, float totalPrice, String status, CustomerBS customerBS, String takeTime, String deliveryTime) {
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.status = status;
        this.customerBS = customerBS;
        this.takeTime = takeTime;
        this.deliveryTime = deliveryTime;
    }

    public List<OrderServiceDTO> getListOrderSerice() {
        return listOrderSerice;
    }

    public void setListOrderSerice(List<OrderServiceDTO> listOrderSerice) {
        this.listOrderSerice = listOrderSerice;
    }
}
