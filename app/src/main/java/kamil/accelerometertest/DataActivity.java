package kamil.accelerometertest;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.logging.Handler;


public class DataActivity extends Activity {
	
	int count = 0;
	
	TextView lista;
	
	DbAdapter dbAdapter = new DbAdapter(this);

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.data_activity);


		
		lista = (TextView) findViewById(R.id.lista);

		
		Cursor k = dbAdapter.getGData();
        while(k.moveToNext()){
        	int id = k.getInt(0);
        	String date = k.getString(1);
        	int g = k.getInt(2);
        	lista.setText(lista.getText() + "\n" + id + ". " + date +" " + g);
        }
        
		
	}
	
	public boolean onCreateOptionsMenu(Menu data_menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.data_menu, data_menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.delGData) {
            dbAdapter.delGData();
            
            finish();
            startActivity(getIntent());
        }
            
            return super.onOptionsItemSelected(item);

	
    }
}
