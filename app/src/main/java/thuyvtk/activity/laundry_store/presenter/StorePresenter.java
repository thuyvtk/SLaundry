package thuyvtk.activity.laundry_store.presenter;
import thuyvtk.activity.laundry_store.callbacks.CallbackData;
import thuyvtk.activity.laundry_store.model.StoreDTO;
import thuyvtk.activity.laundry_store.service.StoreService;
import thuyvtk.activity.laundry_store.service.serviceImpl.StoreServiceImpl;
import thuyvtk.activity.laundry_store.view.StoreView;

public class StorePresenter {
    StoreService storeService;
    StoreView storeView;

    public StorePresenter(StoreView storeView) {
        storeService = new StoreServiceImpl();
        this.storeView = storeView;
    }

    public void getStoreByFirebaseId(String userId) {
        storeService.getStoreByFirebaseId(userId, new CallbackData<StoreDTO>() {
            @Override
            public void onSuccess(StoreDTO storeDTO) {
                storeView.returnStore(storeDTO);
            }

            @Override
            public void onFail(String message) {

            }
        });
    }

    public void updateStore(final StoreDTO storeDTO) {
        storeService.updateStore(storeDTO, new CallbackData<StoreDTO>() {
            @Override
            public void onSuccess(StoreDTO customerDTO) {
                storeView.returnStore(storeDTO);
            }

            @Override
            public void onFail(String message) {

            }
        });
    }

}
