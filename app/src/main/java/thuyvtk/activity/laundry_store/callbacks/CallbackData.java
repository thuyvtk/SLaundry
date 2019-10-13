package thuyvtk.activity.laundry_store.callbacks;

public interface CallbackData<T> {
    void onSuccess(T t);

    void onFail(String message);
}
