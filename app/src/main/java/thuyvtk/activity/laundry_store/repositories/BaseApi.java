package thuyvtk.activity.laundry_store.repositories;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BaseApi {
    private Retrofit retrofitAdapter;

    public Retrofit getRetrofitAdapter() {
        return retrofitAdapter;
    }

    public <T> T getService(Class<T> tClass, String url) {
        retrofitAdapter = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofitAdapter.create(tClass);
    }
}
