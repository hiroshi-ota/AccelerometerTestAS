package kamil.accelerometertest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends Activity implements SensorEventListener{

	private SensorManager menadzer;
	private TextView wyjscie;
	private Sensor czujnik;
	
	private float Accel;
	private float AccelCurent;
	private float AccelLast;
		
	DbAdapter dbAdapter = new DbAdapter(this);

	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        menadzer = (SensorManager) getSystemService(SENSOR_SERVICE);
        wyjscie = (TextView) findViewById(R.id.wyjscie);
        
        Accel = 0.00f;
        AccelCurent = SensorManager.GRAVITY_EARTH;
        AccelLast = SensorManager.GRAVITY_EARTH;
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        
        if (id == R.id.action_data_list){
//        Intent getData = new Intent(getApplicationContext(), DataActivity.class);
        Intent getLoad = new Intent(getApplicationContext(), LoadingScreen.class);
//        startActivity(getData);
        startActivity(getLoad);
        }
        
        return super.onOptionsItemSelected(item);
    }

    
    @Override
    protected void onResume(){
    	super.onResume();
    	
    	czujnik = menadzer.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    	menadzer.registerListener(this, czujnik, SensorManager.SENSOR_DELAY_NORMAL);
    }

	@Override
	public void onSensorChanged(SensorEvent event) {
		
		int i;
		
		float x = event.values[0];
		float y = event.values[1];
		float z = event.values[2];
		
		AccelLast = AccelCurent;
		AccelCurent = (float) ((1/9.8)* Math.sqrt((double) (x*x + y*y + z*z)));
		float delta = AccelCurent - AccelLast;
		Accel = Accel * 0.9f + delta;
		
		
		StringBuilder wyniki = new StringBuilder();
		wyniki.append("\nNazwa czujnika: ");
		Sensor czujnik = event.sensor;
		wyniki.append(czujnik.getName());
		wyniki.append("\n\n");
		wyniki.append("Wartości: ");
		wyniki.append("\n\n");


		for(i = 0; i < event.values.length; i++){
		
			wyniki.append("  [");
			if(i==0){wyniki.append("x");}
			if(i==1){wyniki.append("y");}
			if(i==2){wyniki.append("z");}
			
			wyniki.append("] = ");
			wyniki.append(Math.round((event.values[i])*100.0)/100.0);
			wyniki.append("\n");
			
		}
		
		wyniki.append("\n");
		wyniki.append("G= " + Math.round(AccelCurent));

		
		wyjscie.setText(wyniki);
		



		if((Math.round(AccelCurent)) > 1){

			Calendar c = Calendar.getInstance();
			String curent_date = c.getTime().toString();


			
			dbAdapter.setGData(curent_date, (Math.round(AccelCurent)));
		}

	}
	
	


	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
	
	
}
