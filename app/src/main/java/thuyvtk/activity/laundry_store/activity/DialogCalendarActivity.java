package thuyvtk.activity.laundry_store.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.squareup.timessquare.CalendarPickerView;

import java.util.Calendar;
import java.util.Date;

import thuyvtk.activity.laundry_store.R;

public class DialogCalendarActivity extends AppCompatActivity {
    CalendarPickerView datePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_calendar);
        datePicker = findViewById(R.id.calendarPickSelectedDate);
        intCalendar();
    }
    private void intCalendar() {
        Date today = new Date();
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);
        datePicker.init(today, nextYear.getTime()).inMode(CalendarPickerView.SelectionMode.RANGE).withSelectedDate(today);


    }
}
