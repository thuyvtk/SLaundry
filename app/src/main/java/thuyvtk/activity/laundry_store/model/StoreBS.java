package thuyvtk.activity.laundry_store.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StoreBS implements Serializable {
    @SerializedName("Id")
    private String store_id;
    @SerializedName("Name")
    private String name;
    @SerializedName("Email")
    private String email;
    @SerializedName("rate")
    private int rate;
    @SerializedName("Address")
    private String Address;
    @SerializedName("Imgurl")
    private String Image;

    public StoreBS(String store_id, String name, String email, int rate, String address, String image) {
        this.store_id = store_id;
        this.name = name;
        this.email = email;
        this.rate = rate;
        Address = address;
        Image = image;
    }

    public StoreBS() {
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
