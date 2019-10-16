package thuyvtk.activity.laundry_store.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class OrderDetailDTO implements Serializable {
    @SerializedName("Date")
    private String dateCreate;
    @SerializedName("ListOrder")
    private List<OrderOngoingDTO> listOrder;

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public List<OrderOngoingDTO> getListOrder() {
        return listOrder;
    }

    public void setListOrder(List<OrderOngoingDTO> listOrder) {
        this.listOrder = listOrder;
    }

    public OrderDetailDTO(String dateCreate, List<OrderOngoingDTO> listOrder) {
        this.dateCreate = dateCreate;
        this.listOrder = listOrder;
    }
}
