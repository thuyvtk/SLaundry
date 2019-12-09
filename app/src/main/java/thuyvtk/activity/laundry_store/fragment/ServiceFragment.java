package thuyvtk.activity.laundry_store.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import thuyvtk.activity.laundry_store.R;
import thuyvtk.activity.laundry_store.adapter.ServiceTypeAdapter;
import thuyvtk.activity.laundry_store.library.SharePreferenceLib;
import thuyvtk.activity.laundry_store.model.ServiceDTO;
import thuyvtk.activity.laundry_store.model.ServiceTypeDTO;
import thuyvtk.activity.laundry_store.presenter.ServicePresenter;
import thuyvtk.activity.laundry_store.view.ServiceView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceFragment extends Fragment implements ServiceView {
    RecyclerView rv_service;
    ServicePresenter servicePresenter;
    ServiceTypeAdapter serviceTypeAdapter;
    LinearLayout ln_waiting;


    public ServiceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_service, container, false);
        defineView(view);
        servicePresenter = new ServicePresenter(this);
        getAllService();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        servicePresenter.getServiceByStore(sharePreferenceLib.getUser().getStoreId());
        ln_waiting.setVisibility(View.VISIBLE);
    }

    private void defineView(View view) {
        rv_service = view.findViewById(R.id.rv_service);
        rv_service.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_service.setLayoutManager(linearLayoutManager);
        ln_waiting = view.findViewById(R.id.ln_waiting);
    }

    SharePreferenceLib sharePreferenceLib;

    private void getAllService() {
        sharePreferenceLib = new SharePreferenceLib(getContext());
        servicePresenter.getServiceByStore(sharePreferenceLib.getUser().getStoreId());
        ln_waiting.setVisibility(View.VISIBLE);

    }

    public void deleteService() {
    }

    @Override
    public void returnService(ServiceDTO serviceDTO) {
    }

    @Override
    public void returnListStore(List<ServiceTypeDTO> result) {
        serviceTypeAdapter = new ServiceTypeAdapter(getContext(), result, this);
        rv_service.setAdapter(serviceTypeAdapter);
        ln_waiting.setVisibility(View.GONE);
    }

    @Override
    public void deleteServiceSuccess(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        getAllService();
    }
}


