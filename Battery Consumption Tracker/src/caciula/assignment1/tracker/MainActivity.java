package caciula.assignment1.tracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void checkLog (View view) {
    	Intent intent = new Intent(this, CheckBatteryLog.class);
    	startActivity(intent);
    }
    
    public void checkStatistics (View view) {
    	Intent intent = new Intent(this, CheckBatteryStatistics.class);
    	startActivity(intent);
    }
}
