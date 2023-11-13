package net.holybee.rfcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class PathLossActivity extends AppCompatActivity {
    private PathLossViewModel mViewModel;
    private RadioButton rbDistM, rbDistKm, rbDistMile, rbDistNM;
    private RadioButton rbHz, rbKHz, rbMHz, rbGHz;
    private TextView frequencyUnitText, distanceUnitText;
    private EditText frequencyEditText,distanceEditText, lossEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_loss);
        mViewModel = new ViewModelProvider(this).get(PathLossViewModel.class);
        rbDistM = findViewById(R.id.fsplMeterRadioButton);
        rbDistKm = findViewById(R.id.fsplKmRadioButton);
        rbDistMile = findViewById(R.id.fsplMileRadioButton);
        rbDistNM = findViewById(R.id.fsplNauticalMileRadioButton);
        rbHz = findViewById(R.id.fsplHzRadioButton);
        rbKHz = findViewById(R.id.fsplkHzRadioButton);
        rbMHz = findViewById(R.id.fsplMHzRadioButton);
        rbGHz = findViewById(R.id.fsplGHzRadioButton);
        frequencyUnitText = findViewById(R.id.fsplFrequencyUnitTextView);
        distanceUnitText = findViewById(R.id.fsplDistanceUnitTextView);
        frequencyEditText = findViewById(R.id.fsplFrequencyExitText);
        distanceEditText = findViewById(R.id.fsplDistanceEditText);
        lossEditText = findViewById(R.id.fspsPathLossEditText);

        setRadioButtons();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //  getMenuInflater().inflate(R.menu.rfcalc, menu);
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

    public void fsplDistanceUnitChanged (View view) {
        int id = view.getId();
        if (id == R.id.fsplMeterRadioButton){
            mViewModel.distanceUnit = PathLossViewModel.METER;
            distanceUnitText.setText("m");

        }
        if (id == R.id.fsplKmRadioButton) {
            mViewModel.distanceUnit = PathLossViewModel.KILOMETER;
            distanceUnitText.setText("km");

        }
        if (id == R.id.fsplMileRadioButton) {
            mViewModel.distanceUnit = PathLossViewModel.MILE;
            distanceUnitText.setText("mi");

        }
        if (id == R.id.fsplNauticalMileRadioButton) {
            mViewModel.distanceUnit = PathLossViewModel.NAUTICALMILE;
            distanceUnitText.setText("nm");

        }
        if (distanceEditText.getText().toString().length() > 0 && frequencyEditText.getText().toString().length() > 0) {
            fsplClickCalculate(view);
        }
    }

    public void fsplFrequencyUnitChanged (View view) {
        int id = view.getId();
        Log.e("fspl","Frequency changed");
        if (id == R.id.fsplHzRadioButton) {
            mViewModel.frequencyUnit = PathLossViewModel.HERTZ;
            frequencyUnitText.setText("Hz");
        }

        if (id == R.id.fsplkHzRadioButton) {
            mViewModel.frequencyUnit = PathLossViewModel.KILOHERTZ;
            frequencyUnitText.setText("kHz");
        }

        if (id == R.id.fsplMHzRadioButton) {
            mViewModel.frequencyUnit = PathLossViewModel.MEGAHERTZ;
            frequencyUnitText.setText("MHz");
        }

        if (id == R.id.fsplGHzRadioButton) {
            mViewModel.frequencyUnit = PathLossViewModel.GIGAHERTZ;
            frequencyUnitText.setText("GHz");
        }
        if (distanceEditText.getText().toString().length() > 0 && frequencyEditText.getText().toString().length() > 0) {
            fsplClickCalculate(view);
        }
    }


    public void fsplClickCalculate (View view) {
        try {
            BigDecimal distance = new BigDecimal(distanceEditText.getText().toString());
            BigDecimal frequency = new BigDecimal(frequencyEditText.getText().toString());
            BigDecimal loss = mViewModel.calculateLoss(frequency, distance);
            lossEditText.setText(String.valueOf(loss));
        } catch (Exception e) {
            Log.e ("FSPL", e.toString());
        }
    }

    private void setRadioButtons() {
        switch (mViewModel.frequencyUnit) {
            case PathLossViewModel.HERTZ: {
                rbHz.setChecked(true);
                frequencyUnitText.setText("Hz");
                break;
            }
            case PathLossViewModel.KILOHERTZ: {
                rbKHz.setChecked(true);
                frequencyUnitText.setText("kHz");
                break;

            }
            case PathLossViewModel.MEGAHERTZ: {
                rbMHz.setChecked(true);
                frequencyUnitText.setText("MHz");
                break;
            }
            case PathLossViewModel.GIGAHERTZ: {
                rbGHz.setChecked(true);
                frequencyUnitText.setText("GHz");
                break;
            }
        }

        switch (mViewModel.distanceUnit) {
            case PathLossViewModel.METER: {
                rbDistM.setChecked(true);
                distanceUnitText.setText("m");
                break;
            }

            case PathLossViewModel.KILOMETER: {

                rbDistKm.setChecked(true);
                distanceUnitText.setText("km");
                break;

            }

            case PathLossViewModel.MILE: {
                rbDistMile.setChecked(true);
                distanceUnitText.setText("mi");
                break;
            }

            case PathLossViewModel.NAUTICALMILE: {
                rbDistNM.setChecked(true);
                distanceUnitText.setText("nm");
                break;
            }
        }

    }

    private void showToast (String s) {
        Toast.makeText(this,s, Toast.LENGTH_LONG).show();
    }
}