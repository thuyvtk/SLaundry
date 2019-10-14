package thuyvtk.activity.laundry_store.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ServiceTypeDTO implements Serializable {
    @SerializedName("Id")
    private String serviceTypeId;
    @SerializedName("Name")
    private String serviceTypeName;
    @SerializedName("IsDelete")
    private boolean isDelete;
    @SerializedName("listService")
    private List<ServiceDTO> listService;

    public ServiceTypeDTO() {
    }

    public ServiceTypeDTO(String serviceTypeId, String serviceTypeName, boolean isDelete, List<ServiceDTO> listService) {
        this.serviceTypeId = serviceTypeId;
        this.serviceTypeName = serviceTypeName;
        this.isDelete = isDelete;
        this.listService = listService;
    }

    public String getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(String serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public String getServiceTypeName() {
        return serviceTypeName;
    }

    public void setServiceTypeName(String serviceTypeName) {
        this.serviceTypeName = serviceTypeName;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public List<ServiceDTO> getListService() {
        return listService;
    }

    public void setListService(List<ServiceDTO> listService) {
        this.listService = listService;
    }
}
