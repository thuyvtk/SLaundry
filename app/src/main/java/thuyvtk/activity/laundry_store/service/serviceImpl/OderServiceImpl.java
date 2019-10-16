package thuyvtk.activity.laundry_store.service.serviceImpl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import thuyvtk.activity.laundry_store.callbacks.CallbackData;
import thuyvtk.activity.laundry_store.model.OrderDTO;
import thuyvtk.activity.laundry_store.model.OrderDetailDTO;
import thuyvtk.activity.laundry_store.repositories.ClientApi;
import thuyvtk.activity.laundry_store.service.OrderService;

public class OderServiceImpl implements OrderService {
    ClientApi clientApi = new ClientApi();

    @Override
    public void loadHistory(String userId, String dateStart, String dateEnd, final CallbackData<List<OrderDetailDTO>> callbackData) {
        Call<ResponseBody> serviceCall = clientApi.getGenericApi().getOrderHistory(userId, dateStart, dateEnd);
        try {
            serviceCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response != null && response.body() != null) {
                        if (response.code() == 200) {
                            try {
                                String result = response.body().string();
                                Type type = new TypeToken<List<OrderDetailDTO>>() {
                                }.getType();
                                List<OrderDetailDTO> responseResult = new Gson().fromJson(result, type);
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
    public void getOrderByDateAndStatus(String customerId, String dateStart, String dateEnd,  final CallbackData<List<OrderDetailDTO>> callbackData) {
        Call<ResponseBody> serviceCall = clientApi.getGenericApi().getByDateAndStatus(customerId, dateStart, dateEnd);
        try {
            serviceCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response != null && response.body() != null) {
                        if (response.code() == 200) {
                            try {
                                String result = response.body().string();
                                Type type = new TypeToken<List<OrderDetailDTO>>() {
                                }.getType();
                                List<OrderDetailDTO> responseResult = new Gson().fromJson(result, type);
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
        }
    }
}
