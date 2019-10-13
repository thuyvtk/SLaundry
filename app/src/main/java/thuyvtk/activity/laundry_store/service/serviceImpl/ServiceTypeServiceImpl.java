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
import thuyvtk.activity.laundry_store.model.ServiceTypeDTO;
import thuyvtk.activity.laundry_store.repositories.ClientApi;
import thuyvtk.activity.laundry_store.service.ServiceTypeService;

public class ServiceTypeServiceImpl implements ServiceTypeService {
    ClientApi clientApi = new ClientApi();

    @Override
    public void insertServiceType(ServiceTypeDTO serviceTypeDTO, final CallbackData<ServiceTypeDTO> callbackData) {
        Gson gson = new Gson();
        String json = gson.toJson(serviceTypeDTO);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        Call<ResponseBody> callService = clientApi.getGenericApi().insertServiceType(body);

        try {
            callService.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response != null) {
                        if (response.code() == 200 || response.code() == 201) {
                            try {
                                String result = response.body().string();
                                Type type = new TypeToken<ServiceTypeDTO>() {
                                }.getType();
                                ServiceTypeDTO responseResult = new Gson().fromJson(result, type);
                                if (responseResult != null) {
                                    callbackData.onSuccess(responseResult);
                                } else {
                                    callbackData.onFail("empty");
                                }
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

    @Override
    public void getServiceTypeByName(String name, final CallbackData<ServiceTypeDTO> callbackData) {
        Call<ResponseBody> serviceCall = clientApi.getGenericApi().getServiceTypeByName(name);
        try {
            serviceCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response != null && response.body() != null) {
                        if (response.code() == 200) {
                            try {
                                String result = response.body().string();
                                Type type = new TypeToken<ServiceTypeDTO>() {
                                }.getType();
                                ServiceTypeDTO responseResult = new Gson().fromJson(result, type);
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

    @Override
    public void updateServiceTypeStatus(final ServiceTypeDTO serviceTypeDTO, final CallbackData<String> callbackData) {
        Gson gson = new Gson();
        String json = gson.toJson(serviceTypeDTO);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        Call<ResponseBody> callService = clientApi.getGenericApi().updateServiceTypeStatus(body);

        try {
            callService.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response != null) {
                        if (response.code() == 200 || response.code() == 201) {
                                    callbackData.onSuccess("success");
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
