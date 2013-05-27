package fragment;

import java.util.Calendar;

import com.jeje.petroulette.AdoptActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class DatePickerFragment extends DialogFragment {

    
	
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 
		
        int year = getArguments().getInt("year");
        int month = getArguments().getInt("month");
        int day = getArguments().getInt("day");
        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), (AdoptActivity)getActivity(), year, month, day);
    }

    public static DatePickerFragment newInstance(int year,int month,int day) {
    	DatePickerFragment f = new DatePickerFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("year", year);
        args.putInt("month", month);
        args.putInt("day", day);
        f.setArguments(args);
        return f;
    }

}
