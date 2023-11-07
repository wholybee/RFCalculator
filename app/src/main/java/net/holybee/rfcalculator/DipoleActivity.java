package net.holybee.rfcalculator;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class DipoleActivity extends AppCompatActivity {
    private static final String TAG = DipoleActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dipole);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
     //   getMenuInflater().inflate(R.menu.dipole, menu);
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
        return super.onOptionsItemSelected(item);
    }



    public void dipoleCalculateButtonClick(View view) {
        EditText f,v,l,h;

        f = findViewById(R.id.editFreq);
        v = findViewById(R.id.editVel);

        float freq;
        float vel;
        try {
            freq = Float.parseFloat(f.getText().toString());
            vel = Float.parseFloat(v.getText().toString());
            if ((vel <= 0) || (vel > 1)) throw new Exception ("Invalid Velocity Factor");
        } catch (Exception e) {
            freq = 7175.0F;
            vel = 0.95F;
            f.setText("7175");
            v.setText("0.95");
        }

       l = findViewById(R.id.editLen);
//        float len = Float.valueOf(l.getText().toString());

        h = findViewById(R.id.editHalf);
//        float half = Float.valueOf(h.getText().toString());

        float len = (468/vel)/freq*1000;

        int totalInches = (int) (len * 12);
        int feet = totalInches / 12;
        int inch = totalInches % 12;


        l.setText(feet + "'" + inch +"''");

        totalInches = totalInches / 2;
        feet = totalInches / 12;
        inch = totalInches % 12;

        h.setText((feet +"'" + inch + "''"));
    }

}
