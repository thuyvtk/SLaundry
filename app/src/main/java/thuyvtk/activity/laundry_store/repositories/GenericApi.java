package thuyvtk.activity.laundry_store.repositories;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface GenericApi {
    // Service
    @POST(ConfigApi.Api.INSERT_SERVICE)
    Call<ResponseBody> insertService(@Body RequestBody service);

    @GET(ConfigApi.Api.GET_SERVICE_BY_STORE)
    Call<ResponseBody> getServiceByStore(@Query("StoreId") String storeId);

    @PUT(ConfigApi.Api.UPDATE_SERVICE)
    Call<ResponseBody> updateService(@Body RequestBody serviceDTO);
    @DELETE(ConfigApi.Api.DELETE_SERVICE)
    Call<ResponseBody> deleteService(@Query("Id") String serviceId);

    // ServiceType
    @POST(ConfigApi.Api.INSERT_SERVICE_TYPE)
    Call<ResponseBody> insertServiceType(@Body RequestBody serviceType);

    @GET(ConfigApi.Api.GET_SERVICE_TYPE_BY_NAME)
    Call<ResponseBody> getServiceTypeByName(@Query("Name") String name);

    @PUT(ConfigApi.Api.UPDATE_STATUS_SERVICE)
    Call<ResponseBody> updateServiceTypeStatus(@Body RequestBody serviceType);

    //Store
    @PUT(ConfigApi.Api.UPDATE_STORE)
    Call<ResponseBody> updateStore(@Body RequestBody store);

    @GET(ConfigApi.Api.GET_STORE_BY_FIREBASE_ID)
    Call<ResponseBody> getCustomerByFirebaseId(@Query("Id") String id);

    @GET(ConfigApi.Api.ORDER_HISTORY)
    Call<ResponseBody> getOrderHistory(@Query("Id") String userId, @Query("DateCreateStart") String dateStart,
                                       @Query("DateCreateEnd") String dateEnd);

    @GET(ConfigApi.Api.GET_BY_DATE_STATUS)
    Call<ResponseBody> getByDateAndStatus(@Query("Id") String customerId, @Query("DateCreateStart") String dateStart,
                                          @Query("DateCreateEnd") String dateEnd, @Query("Status") String status);

    @PUT(ConfigApi.Api.SET_ORDER_STATUS)
    Call<ResponseBody> setOrderStatus(@Query("Id") String orderId,@Query("Status") String status);
}
