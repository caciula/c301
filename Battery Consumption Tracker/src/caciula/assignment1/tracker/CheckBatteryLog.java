package caciula.assignment1.tracker;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CheckBatteryLog extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_battery_log);
        checkLog();
    }
    
    @Override
    public void onRestart() {
    	super.onRestart();
    	checkLog();
    }
    
    private void checkLog() {
    	ListView listView = (ListView) findViewById(R.id.log_entries);
    	listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        
        List<String> logfileEntries = new ArrayList<String>();
         
 		try {
 			FileInputStream fis = openFileInput("logfile");
 			DataInputStream dis = new DataInputStream(fis);
 			BufferedReader br = new BufferedReader(new InputStreamReader(dis));
 			
 			String line;
 			
 			while ((line = br.readLine()) != null) {
 				String[] tokens = line.split("/");
 				String message = "Date: " + tokens[0] + "\nDescription: " + tokens[1] + "\nStarting battery %: " + tokens[2] + "\nEnding battery %: " + tokens[3] + "\nTime (in seconds): " + tokens[4];
 				
 				logfileEntries.add(message);
 			}
 			
 			dis.close();
 		} catch (Exception e) {
 			e.printStackTrace();
 		}

 		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
 		          android.R.layout.simple_list_item_1, android.R.id.text1, logfileEntries);
 		listView.setAdapter(adapter);
    }
    
    public void createEntry (View view) {
    	Intent intent = new Intent(this, CreateEntry.class);
    	startActivity(intent);
    }
    
    public void deleteEntry (View view) {
    	ListView listView = (ListView) findViewById(R.id.log_entries);
    	
    	List<String> logfileEntries = new ArrayList<String>();
    	
    	listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
    	int selectedItemPosition = listView.getCheckedItemPosition();
    	
    	if (selectedItemPosition >= 0) {
    		try {
     			FileInputStream fis = openFileInput("logfile");
     			DataInputStream dis = new DataInputStream(fis);
     			BufferedReader br = new BufferedReader(new InputStreamReader(dis));
     			
     			String line;
     			int rowNum = 0;
     			
     			while ((line = br.readLine()) != null) {
     				if (rowNum != selectedItemPosition) {
     					logfileEntries.add(line);
     				}
     				rowNum++;
     			}
     			
     			dis.close();
     		} catch (Exception e) {
     			e.printStackTrace();
     		}
    		
    		try {
    			FileOutputStream fos;
    			fos = openFileOutput("logfile", Context.MODE_PRIVATE);
    			
    			for (int i = 0; i < logfileEntries.size(); i++) {
    				String message = logfileEntries.get(i) + "\n";
    				fos.write(message.getBytes());
    			}
    			
    			fos.close();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		
    		checkLog();
    	}
    }
}