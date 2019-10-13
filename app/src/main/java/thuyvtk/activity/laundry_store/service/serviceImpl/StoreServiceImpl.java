package thuyvtk.activity.laundry_store.service.serviceImpl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import thuyvtk.activity.laundry_store.callbacks.CallbackData;
import thuyvtk.activity.laundry_store.model.StoreDTO;
import thuyvtk.activity.laundry_store.repositories.ClientApi;
import thuyvtk.activity.laundry_store.service.StoreService;

public class StoreServiceImpl implements StoreService {

    ClientApi clientApi = new ClientApi();

    @Override
    public void getStoreByFirebaseId(String id, final CallbackData<StoreDTO> callbackData) {
        Call<ResponseBody> serviceCall = clientApi.getGenericApi().getCustomerByFirebaseId(id);
        try {
            serviceCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response != null && response.body() != null) {
                        if (response.code() == 200) {
                            try {
                                String result = response.body().string();
                                Type type = new TypeToken<StoreDTO>() {
                                }.getType();
                                StoreDTO responseResult = new Gson().fromJson(result, type);
                                if (responseResult != null) {
                                    callbackData.onSuccess(responseResult);
                                } else {
                                    callbackData.onFail("empty");
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            callbackData.onFail("timeout");
                        }

                    }

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStore(final StoreDTO store, final CallbackData<StoreDTO> callbackData) {
        Gson gson = new Gson();
        String json = gson.toJson(store);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        Call<ResponseBody> callService = clientApi.getGenericApi().updateStore(body);

        try {
            callService.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response != null) {
                        if (response.code() == 200 || response.code() == 201) {
                            try {
                                callbackData.onSuccess(store);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            callbackData.onFail("timeout");
                        }
                    } else {
                        callbackData.onFail("timeout");
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    callbackData.onFail("timeout");
                }
            });
        } catch (Exception e) {
            callbackData.onFail("timeout");
        }
    }
}
