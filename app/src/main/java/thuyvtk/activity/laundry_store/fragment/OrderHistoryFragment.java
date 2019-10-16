package thuyvtk.activity.laundry_store.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import thuyvtk.activity.laundry_store.R;
import thuyvtk.activity.laundry_store.adapter.OrderOngoingAdapter;
import thuyvtk.activity.laundry_store.library.SharePreferenceLib;
import thuyvtk.activity.laundry_store.model.OrderDetailDTO;
import thuyvtk.activity.laundry_store.presenter.OrderPresenter;
import thuyvtk.activity.laundry_store.view.OrderView;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderHistoryFragment extends Fragment implements OrderView {
    RecyclerView rv_order;
    OrderPresenter orderPresenter;
    LinearLayout ln_waiting;
    OrderOngoingAdapter adapter;
    TextView txtDateStart, txtDateEnd;

    public OrderHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_order_history, container, false);
        defineView(view);
        orderPresenter = new OrderPresenter((OrderView) this);
        getOrderByDate();
        return view;
    }

    private void getOrderByDate() {
        SharePreferenceLib sharePreferenceLib = new SharePreferenceLib(getContext());
        String dateStart = txtDateStart.getText().toString();
        String dateEnd = txtDateEnd.getText().toString();
        orderPresenter.getOrderHistory(sharePreferenceLib.getUser().getStoreId(), dateStart,dateEnd);
        ln_waiting.setVisibility(View.VISIBLE);
    }

    private void defineView(View view) {
        rv_order = view.findViewById(R.id.rv_order);
        rv_order.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_order.setLayoutManager(linearLayoutManager);
        ln_waiting = view.findViewById(R.id.ln_waiting);
        txtDateStart = getActivity().findViewById(R.id.txtDayStart);
        txtDateEnd = getActivity().findViewById(R.id.txtDayEnd);
    }

    @Override
    public void loadOrderHistory(List<OrderDetailDTO> orderList) {
        adapter = new OrderOngoingAdapter(getActivity(),orderList);
        rv_order.setAdapter(adapter);
        ln_waiting.setVisibility(View.GONE);
    }


    @Override
    public void onFail(String msg) {

    }

    @Override
    public void createOrderSuccess() {

    }

    @Override
    public void returnListOrder(List<OrderDetailDTO> listOrderDetail) {

    }

    @Override
    public void rateSuccess(String message) {

    }
}
