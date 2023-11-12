package net.holybee.rfcalculator;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);
 /*       try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField;
            menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if(menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception ex) {
            // Ignore
        } */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    //    getMenuInflater().inflate(R.menu.root, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_rfcalc) {
            Intent intent = new Intent(this, DBcalcActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_dipolecalc) {
            Intent intent = new Intent(this, DipoleActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_atsc) {
            Intent intent = new Intent(this, AtscActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_vswr) {
            Intent intent = new Intent(this, VSWRActivity.class);
            startActivity(intent);
            return true;

        }
        if (id == R.id.action_wave) {
            Intent intent = new Intent(this, WavelengthActivity.class);
            startActivity(intent);
            return true;

        }
        if (id == R.id.action_los) {
            Intent intent = new Intent(this, LosActivity.class);
            startActivity(intent);
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void rootStartAtsc(View v) {

        Intent intent = new Intent(this, AtscActivity.class);
        startActivity(intent);

    }
    public void rootStartDipole(View v) {
        Intent intent = new Intent(this, DipoleActivity.class);
        startActivity(intent);

    }
    public void rootStartRF(View v) {
        Intent intent = new Intent(this, DBcalcActivity.class);
        startActivity(intent);
    }
    public void rootStartVSWR(View v) {
        Intent intent = new Intent(this, VSWRActivity.class);
        startActivity(intent);
    }

    public void rootStartWavelength(View v) {
        Intent intent = new Intent(this, WavelengthActivity.class);
        startActivity(intent);
    }

    public void rootStartLos(View v) {
        Intent intent = new Intent(this, LosActivity.class);
        startActivity(intent);
    }
}
