package thuyvtk.activity.laundry_store.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ServiceVN implements Serializable {

    @SerializedName("Id")
    private String serviceId;
    @SerializedName("Description")
    private String description;
    @SerializedName("Price")
    private float price;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public ServiceVN(String serviceId, String description, float price) {
        this.serviceId = serviceId;
        this.description = description;
        this.price = price;
    }
}
