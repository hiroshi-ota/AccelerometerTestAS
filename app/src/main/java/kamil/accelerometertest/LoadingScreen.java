package kamil.accelerometertest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.os.Handler;
import java.lang.Runnable;

/**
 * Created by Kamil on 2014-11-23.
 */

public class LoadingScreen extends Activity{

    DbAdapter adapter = new DbAdapter(this);


    private final int WAIT_TIME = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("LoadingScreen screen started");
        setContentView(R.layout.loading_activity);
        findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
// Symulowanie długiego zadania
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
// Niemożliwe
                }

                System.out.println("Wyświetlanie danych z profilu");
/* Tworzenie intencji uruchamiającej aktywność ProfileData */
                Intent mainIntent =
                        new Intent(LoadingScreen.this, DataActivity.class);
                LoadingScreen.this.startActivity(mainIntent);
                LoadingScreen.this.finish();


            }
        }, WAIT_TIME);
    }
}
