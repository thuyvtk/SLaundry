package thuyvtk.activity.laundry_store.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StoreDTO implements Serializable {
    @SerializedName("Id")
    private String storeId;
    @SerializedName("Name")
    private String storeName;
    @SerializedName("Email")
    private String email;
    @SerializedName("Rate")
    private int rate;
    @SerializedName("AccountId")
    private String accountId;
    @SerializedName("Phone")
    private String phone;
    @SerializedName("Longitude")
    private String longitude;
    @SerializedName("Latitude")
    private String latitude;
    @SerializedName("Address")
    private String address;
    @SerializedName("Imgurl")
    private String imageUrl;
    @SerializedName("IsActive")
    private boolean isActive;

    public StoreDTO() {
    }

    public StoreDTO(String storeId, String storeName, String email, int rate, String accountId, String phone, String longitude, String latitude, String address, String imageUrl, boolean isActive) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.email = email;
        this.rate = rate;
        this.accountId = accountId;
        this.phone = phone;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.imageUrl = imageUrl;
        this.isActive = isActive;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
