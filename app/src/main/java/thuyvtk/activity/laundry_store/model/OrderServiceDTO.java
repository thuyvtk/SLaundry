package thuyvtk.activity.laundry_store.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrderServiceDTO implements Serializable {
    @SerializedName("Quantity")
    private int quantity;
    @SerializedName("Price")
    private float price;
    @SerializedName("ServiceId")
    private String serviceId;
    @SerializedName("Service")
    private ServiceDTO serviceDTO;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public ServiceDTO getServiceDTO() {
        return serviceDTO;
    }

    public void setServiceDTO(ServiceDTO serviceDTO) {
        this.serviceDTO = serviceDTO;
    }

    public OrderServiceDTO(int quantity, float price, String serviceId) {
        this.quantity = quantity;
        this.price = price;
        this.serviceId = serviceId;
    }
}
