package thuyvtk.activity.laundry_store.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ServiceDTO implements Serializable {

    @SerializedName("Id")
    private String serviceId;
    @SerializedName("Description")
    private String description;
    @SerializedName("Price")
    private float price;
    @SerializedName("ServiceTypeId")
    private String serviceTypeId;
    @SerializedName("StoreId")
    private String storeId;
    @SerializedName("Imgurl")
    private String imgUrl;

    public ServiceDTO(){
    }
    public ServiceDTO(String serviceId, String description, float price, String serviceTypeId, String storeId, String imgUrl) {
        this.serviceId = serviceId;
        this.description = description;
        this.price = price;
        this.serviceTypeId = serviceTypeId;
        this.storeId = storeId;
        this.imgUrl = imgUrl;
    }

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

    public String getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(String serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
