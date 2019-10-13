package thuyvtk.activity.laundry_store.repositories;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
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

}
