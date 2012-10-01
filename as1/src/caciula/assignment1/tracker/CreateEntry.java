package caciula.assignment1.tracker;

import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/*
 * Purpose: Creates a new entry to insert into the log file.
 * 
 * Design rationale: Everything is really standard; this simply reads all values from
 * the EditText boxes and appends them to the log file.
 * 
 * Outstanding issues: There is no error checking on the user input. Also, since
 * the program saves all of the data on a single line, I had to use a delimiter
 * (in this case, it was /). If the description, or any other field, contains this
 * character, that'll break the program.
 */

public class CreateEntry extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_entry);
        
        //Code to get the date is from: http://www.mkyong.com/java/java-how-to-get-current-date-time-date-and-calender/
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        
        EditText dateEntry = (EditText) findViewById(R.id.date_entry);
        dateEntry.setText(dateFormat.format(cal.getTime()));
    }
    
    public void submit (View view) {
    	EditText dateEntry = (EditText) findViewById(R.id.date_entry);
    	EditText descriptionEntry = (EditText) findViewById(R.id.description_entry);
    	EditText startingBatteryEntry = (EditText) findViewById(R.id.starting_battery_entry);
    	EditText endingBatteryEntry = (EditText) findViewById(R.id.ending_battery_entry);
    	EditText timeHourEntry = (EditText) findViewById(R.id.time_hour_entry);
    	EditText timeMinuteEntry = (EditText) findViewById(R.id.time_minute_entry);
    	EditText timeSecondEntry = (EditText) findViewById(R.id.time_second_entry);
    	
    	String date = dateEntry.getText().toString();
    	String description = descriptionEntry.getText().toString();
    	
    	String startingBattery = startingBatteryEntry.getText().toString();
    	float startingBatteryFloat = Float.parseFloat(startingBattery);
    	startingBattery = String.format("%.2f", startingBatteryFloat);
    	
    	String endingBattery = endingBatteryEntry.getText().toString();
    	float endingBatteryFloat = Float.parseFloat(endingBattery);
    	endingBattery = String.format("%.2f", endingBatteryFloat);
    	
    	int hour = java.lang.Integer.parseInt(timeHourEntry.getText().toString());
    	int minute = java.lang.Integer.parseInt(timeMinuteEntry.getText().toString());
    	int second = java.lang.Integer.parseInt(timeSecondEntry.getText().toString());
    	int timeInSeconds = (3600*hour)+(60*minute)+(second);
    	String time = "" + timeInSeconds;
    	
    	String message = date + "/" + description + "/" + startingBattery + "/" +
    			endingBattery + "/" + time + "\n";

		try {
			FileOutputStream fos;
			fos = openFileOutput("logfile", Context.MODE_APPEND);
			fos.write(message.getBytes());
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	finish();
    }
}