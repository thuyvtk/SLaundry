package thuyvtk.activity.laundry_store.presenter;

import java.util.List;

import thuyvtk.activity.laundry_store.callbacks.CallbackData;
import thuyvtk.activity.laundry_store.model.ServiceTypeDTO;
import thuyvtk.activity.laundry_store.service.ServiceTypeService;
import thuyvtk.activity.laundry_store.service.serviceImpl.ServiceTypeServiceImpl;
import thuyvtk.activity.laundry_store.view.ServiceTypeView;

public class ServiceTypePresenter {
    ServiceTypeService serviceTypeService;
    ServiceTypeView serviceTypeView;

    public ServiceTypePresenter(ServiceTypeView serviceTypeView) {
        serviceTypeService =  new ServiceTypeServiceImpl();
        this.serviceTypeView = serviceTypeView;
    }

    public void getServiceTypeByName(String name) {
        serviceTypeService.getServiceTypeByName(name, new CallbackData<ServiceTypeDTO>() {
            @Override
            public void onSuccess(ServiceTypeDTO serviceTypeDTO) {
                serviceTypeView.returnServiceType(serviceTypeDTO);
            }

            @Override
            public void onFail(String message) {

            }
        });
    }
    public void insertServiceType(ServiceTypeDTO serviceTypeDTO) {
        serviceTypeService.insertServiceType(serviceTypeDTO, new CallbackData<ServiceTypeDTO>() {
            @Override
            public void onSuccess(ServiceTypeDTO serviceTypeDTO) {
                serviceTypeView.returnServiceType(serviceTypeDTO);
            }

            @Override
            public void onFail(String message) {

            }
        });
    }

    public void updateServiceTypeStatus(ServiceTypeDTO serviceTypeDTO) {
        serviceTypeService.updateServiceTypeStatus(serviceTypeDTO, new CallbackData<String>() {
            @Override
            public void onSuccess(String s) {

            }

            @Override
            public void onFail(String message) {

            }
        });
    }
}
