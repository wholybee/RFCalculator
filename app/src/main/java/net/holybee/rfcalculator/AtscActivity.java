package net.holybee.rfcalculator;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

import java.lang.Math;


public class AtscActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atsc);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.atsc, menu);
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



    public void atscButtonClick(View view) {
        AtscCalc calc = new AtscCalc();
        calc.createChannelArray();
        EditText e = findViewById(R.id.channelText);
        int channel = Math.round(Float.parseFloat(e.getText().toString()));
        if (channel > 51) { channel = 51; e.setText(String.valueOf(channel)); }
        if (channel < 2) { channel = 2; e.setText(String.valueOf(channel)); }

        TextView t;

        t= findViewById (R.id.lowerText);
        t.setText("Lower Edge " + (float) calc.lowerEdge(channel));

        t= findViewById (R.id.upperText);
        t.setText("Upper Edge " + (float) calc.upperEdge(channel));

        t= findViewById (R.id.pilotText);
        t.setText("Pilot " + (float) calc.pilotFreq(channel));

        t= findViewById (R.id.centerText);
        t.setText("Center " + (float) calc.centerFreq(channel));

        t= findViewById (R.id.atscupperText);
        t.setText("8VSB Edge " + (float) calc.upperATSC(channel));

        t= findViewById (R.id.atsclowerText);
        t.setText("8VSB Edge " + (float) calc.lowerATSC(channel));

        t= findViewById (R.id.lowershoulderText);
        t.setText("Lower Shoulder " + (float) calc.lowerShoulder(channel));

        t= findViewById (R.id.uppershoulderText);
        t.setText("Upper Shoulder " + (float) calc.upperShoulder(channel));


    }
}
