package net.holybee.rfcalculator;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class VSWRActivity extends AppCompatActivity {


    private final VswrCalc calc = VswrCalc.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vswr);
     /*   if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        } */

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
      //  getMenuInflater().inflate(R.menu.vswr, menu);
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



    public void vswrClickVswr (View v) {
       EditText reflEditText = findViewById(R.id.vswr_refcoText);
       EditText rlEditText = findViewById(R.id.vswr_ReturnText);
       EditText vswrEditText = findViewById(R.id.vswr_vswrText);
       EditText vswrForwardPowerText  = findViewById(R.id.vswr_forwardPowerText);
       EditText vswrReversePowerText = findViewById(R.id.vswr_reversePowerText);

        try {
            float vswr = Float.parseFloat(vswrEditText.getText().toString());
            if (vswr < 1) throw new NumberFormatException("Vswr must be at least 1");


            calc.vswrCalcFromVswr(vswr);

            display(vswrEditText, reflEditText, rlEditText);
            vswrForwardPowerText.setText("");
            vswrReversePowerText.setText("");

        } catch (NumberFormatException e) {
            vswrEditText.setText(R.string.error);
        }
    }
    public void vswrClickPower (View v) {

        EditText reflEditText = findViewById(R.id.vswr_refcoText);
        EditText rlEditText = findViewById(R.id.vswr_ReturnText);
        EditText vswrEditText = findViewById(R.id.vswr_vswrText);
        EditText vswrForwardPowerText  = findViewById(R.id.vswr_forwardPowerText);
        EditText vswrReversePowerText = findViewById(R.id.vswr_reversePowerText);

        try {
            float fwd = Float.parseFloat(vswrForwardPowerText.getText().toString());
            float rev = Float.parseFloat(vswrReversePowerText.getText().toString());

            calc.vswrCalcFromPower(fwd,rev);

            display(vswrEditText, reflEditText, rlEditText);



        } catch (NumberFormatException e) {
            vswrForwardPowerText.setText(R.string.error);
            vswrReversePowerText.setText(R.string.error);
        }
    }
    public void vswrClickRl (View v) {
        EditText reflEditText = findViewById(R.id.vswr_refcoText);
        EditText rlEditText = findViewById(R.id.vswr_ReturnText);
        EditText vswrEditText = findViewById(R.id.vswr_vswrText);
        EditText vswrForwardPowerText  = findViewById(R.id.vswr_forwardPowerText);
        EditText vswrReversePowerText = findViewById(R.id.vswr_reversePowerText);

        try {
            float rl = Float.parseFloat(rlEditText.getText().toString());

            calc.vswrCalcFromRL(rl);

            display(vswrEditText, reflEditText, rlEditText);
            vswrForwardPowerText.setText("");
            vswrReversePowerText.setText("");

        } catch (NumberFormatException e) {
            rlEditText.setText(R.string.error);
        }
    }
    public void vswrClickRC (View v ) {
        EditText reflEditText = findViewById(R.id.vswr_refcoText);
        EditText rlEditText = findViewById(R.id.vswr_ReturnText);
        EditText vswrEditText = findViewById(R.id.vswr_vswrText);
        EditText vswrForwardPowerText  = findViewById(R.id.vswr_forwardPowerText);
        EditText vswrReversePowerText = findViewById(R.id.vswr_reversePowerText);

        try {
            float refl = Float.parseFloat(reflEditText.getText().toString());

            calc.vswrCalcFromRC(refl);

            display(vswrEditText, reflEditText, rlEditText);
            vswrForwardPowerText.setText("");
            vswrReversePowerText.setText("");

        } catch (NumberFormatException e) {
            reflEditText.setText(R.string.error);
        }
    }

    private void display (EditText vswrEditText, EditText reflEditText, EditText rlEditText) {
        String vswr = Float.toString((float) calc.getVSWR());
        vswrEditText.setText(vswr);

        String rc = Float.toString((float) calc.getReflectionCo());
        reflEditText.setText(rc);

        String rl = Float.toString((float) calc.getReturnLoss());
        rlEditText.setText(rl);

    }
public void clearText (View view) {
    int id = view.getId();
    EditText text = findViewById(id);
    text.setText("");
    }


}
