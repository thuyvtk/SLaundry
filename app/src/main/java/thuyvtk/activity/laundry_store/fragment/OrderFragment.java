package thuyvtk.activity.laundry_store.fragment;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.squareup.timessquare.CalendarPickerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import thuyvtk.activity.laundry_store.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment implements DatePickerDialog.OnDateSetListener {
    ImageButton imgCalendar;
    TextView txtDateStart, txtDateEnd;
    CalendarPickerView cal_order;
    Dialog dialog;
    Button dialogButton;
    TabLayout tabOrder;

    public OrderFragment() {
        // Required empty public constructor
    }

    private void setDialog() {
        Date today = new Date();
        Calendar lastYear = Calendar.getInstance();
        lastYear.add(Calendar.YEAR, -1);
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.add(Calendar.DATE,1);
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_calendar);
        dialogButton = (Button) dialog.findViewById(R.id.btnSaveService);
        cal_order = dialog.findViewById(R.id.calendarPickSelectedDate);
        cal_order.init(lastYear.getTime(), tomorrow.getTime()).inMode(CalendarPickerView.SelectionMode.RANGE).withSelectedDate(today);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Date> selectedDates = (ArrayList<Date>) cal_order.getSelectedDates();
                SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy");
                Date startDate = selectedDates.get(0);
                Date endDate = selectedDates.get(selectedDates.size() - 1);
                txtDateStart.setText(sdf.format(startDate));
                txtDateEnd.setText(sdf.format(endDate));
                dialog.dismiss();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        defineView(view);
        setDialog();
        changeSelectedTab();
        imgCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
        return view;
    }

    private void defineView(View view) {
        imgCalendar = view.findViewById(R.id.imgCalendar);
        txtDateStart = view.findViewById(R.id.txtDayStart);
        txtDateEnd = view.findViewById(R.id.txtDayEnd);
        tabOrder = view.findViewById(R.id.tabOrder);
    }

    public void changeSelectedTab() {
        tabOrder.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 1:
                        fragment = new TabComboFragment();
                        break;
                    default:
                        fragment = new TabServiceFragment();
                }
                loadFragment(fragment);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void loadFragment(Fragment fragment) {
        if (fragment != null) {
            getActivity().
                    getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }
}
