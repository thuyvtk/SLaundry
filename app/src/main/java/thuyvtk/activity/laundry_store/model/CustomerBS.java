package thuyvtk.activity.laundry_store.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CustomerBS implements Serializable {
    @SerializedName("Id")
    private String cutomer_Id;
    @SerializedName("Name")
    private String name;
    @SerializedName("Email")
    private String email;
    @SerializedName("Phone")
    private String phone;
    @SerializedName("rate")
    private int rate;
    @SerializedName("Imgurl")
    private String Image;

    public String getCutomer_Id() {
        return cutomer_Id;
    }

    public void setCutomer_Id(String cutomer_Id) {
        this.cutomer_Id = cutomer_Id;
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

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public CustomerBS(String cutomer_Id, String name, String email, String phone, int rate, String image) {
        this.cutomer_Id = cutomer_Id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.rate = rate;
        Image = image;
    }

    public CustomerBS(String cutomer_Id, String name, String email, int rate, String image) {
        this.cutomer_Id = cutomer_Id;
        this.name = name;
        this.email = email;
        this.rate = rate;
        Image = image;
    }
}
