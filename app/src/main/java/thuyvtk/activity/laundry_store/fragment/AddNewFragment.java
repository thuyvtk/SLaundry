package thuyvtk.activity.laundry_store.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import thuyvtk.activity.laundry_store.R;
import thuyvtk.activity.laundry_store.activity.AddNewActivity;
import thuyvtk.activity.laundry_store.activity.AddOtherServiceActivity;
import thuyvtk.activity.laundry_store.model.ServiceTypeDTO;
import thuyvtk.activity.laundry_store.presenter.ServiceTypePresenter;
/**
 * A simple {@link Fragment} subclass.
 */
public class AddNewFragment extends Fragment{
    LinearLayout lnWash;
    LinearLayout lnIron;
    LinearLayout lnVest;
    LinearLayout lnStuffed;
    LinearLayout lnShoes;
    LinearLayout lnDry;
    LinearLayout lnBlanket;
    LinearLayout lnOther;
    LinearLayout ln_waiting;

    public AddNewFragment() {
        // Required empty public constructor
    }

    private void defineView(View view) {
        lnWash = view.findViewById(R.id.lnWash);
        lnIron = view.findViewById(R.id.lnIron);
        lnVest = view.findViewById(R.id.lnVest);
        lnStuffed = view.findViewById(R.id.lnStuffed);
        lnShoes = view.findViewById(R.id.lnShoes);
        lnDry = view.findViewById(R.id.lnDry);
        lnBlanket = view.findViewById(R.id.lnBlanket);
        lnOther = view.findViewById(R.id.lnOther);
        ln_waiting = view.findViewById(R.id.ln_waiting);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_new, container, false);
        defineView(view);
        chooseService();
        return view;
    }

    public void openPageAddNew(final String service) {
        Intent intent = new Intent(getContext(), AddNewActivity.class);
        intent.putExtra("serviceName", service);
        startActivity(intent);
    }

    public void chooseService() {
        lnWash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPageAddNew("Wash");
            }
        });
        lnIron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPageAddNew("Launder");
            }
        });

        lnVest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPageAddNew("Vest");
            }
        });

        lnShoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPageAddNew("Shoes");
            }
        });

        lnStuffed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPageAddNew("Stuffed");
            }
        });

        lnDry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPageAddNew("Dry cleaning");
            }
        });

        lnBlanket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPageAddNew("Blanket");
            }
        });

        lnOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddOtherServiceActivity.class);
                startActivity(intent);
            }
        });
    }
}
