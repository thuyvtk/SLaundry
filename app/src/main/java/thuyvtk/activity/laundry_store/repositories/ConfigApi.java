package thuyvtk.activity.laundry_store.repositories;

public class ConfigApi {

//    public static final String BASE_URL = "https://giatdo20190924115104.azurewebsites.net/api/";
    public static final String BASE_URL = "https://giatdo20191009105153.azurewebsites.net/api/";

    // for localhost
//        public static final String BASE_URL = "https://192.168.1.22:44335/api/";


    public interface Api {
        // Service
        String INSERT_SERVICE = "Service/CreateService";
        String GET_SERVICE_BY_STORE = "Service/GetByStore";
        String UPDATE_SERVICE = "Service/UpdateService";
        String DELETE_SERVICE = "Service/Delete";
        // ServiceType
        String GET_ALL_SERVICE_TYPE = "ServiceType/GetAll";
        String INSERT_SERVICE_TYPE = "ServiceType/Create";
        String GET_SERVICE_TYPE_BY_NAME = "ServiceType/GetByName";
        String UPDATE_STATUS_SERVICE = "ServiceType/UpdateStatus";
        // Store
        String GET_STORE_BY_FIREBASE_ID = "Store/GetByUserID";
        String UPDATE_STORE = "Store/UpdateStore";
        //order
        String ORDER_HISTORY = "Order/GetStoreOrderHistory";
        String GET_BY_DATE_STATUS = "Order/GetByStoreId";
        String SET_ORDER_STATUS = "Order/SetOrderStatus";
    }

}