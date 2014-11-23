package kamil.accelerometertest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Kamil on 2014-11-23.
 */

public class LoadingScreen extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_activity);
        findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
        
    }
}
