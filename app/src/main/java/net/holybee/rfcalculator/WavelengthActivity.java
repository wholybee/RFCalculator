package net.holybee.rfcalculator;

import androidx.appcompat.app.AppCompatActivity;
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

public class WavelengthActivity extends AppCompatActivity {
    private final String TAG = "Wavelength";
    private WaveLengthViewModel mViewModel;
    private RadioButton rbHZ, rbMHZ, rbKHZ, rbGHZ, rbMM, rbCM, rbM, rbKM;

    private TextView freqUnitText, wavelengthUnitText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wavelength);

        mViewModel = new ViewModelProvider(this).get(WaveLengthViewModel.class);

        rbHZ = findViewById(R.id.hz_radioButton);
        rbMHZ = findViewById(R.id.MHz_radioButton);
        rbKHZ = findViewById(R.id.kHz_radioButton);
        rbGHZ = findViewById(R.id.GHz_radioButton);
        rbMM = findViewById(R.id.mm_radioButton);
        rbCM = findViewById(R.id.cm_radioButton);
        rbM = findViewById(R.id.meter_radioButton);
        rbKM = findViewById(R.id.kilometer_radioButton);
        freqUnitText = findViewById(R.id.freq_unit_textView);
        wavelengthUnitText = findViewById(R.id.wave_unit_textView);

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

    public void freqUnitChanged (View view) {
        int id = view.getId();
        if (id == R.id.hz_radioButton) {
            mViewModel.frequencyUnit = WaveLengthViewModel.HERTZ;
            freqUnitText.setText("Hz");
        }
        if (id == R.id.kHz_radioButton) {
            mViewModel.frequencyUnit = WaveLengthViewModel.KILOHERTZ;
            freqUnitText.setText("kHz");
        }
        if (id == R.id.MHz_radioButton) {
            mViewModel.frequencyUnit = WaveLengthViewModel.MEGAHERTZ;
            freqUnitText.setText("MHz");
        }
        if (id == R.id.GHz_radioButton) {
            mViewModel.frequencyUnit = WaveLengthViewModel.GIGAHERTZ;
            freqUnitText.setText("GHz");
        }
        EditText waveEditText = findViewById(R.id.wave_EditText);

        if (waveEditText.getText().toString().length() > 0) {
            clickWaveCalc(view);
        }
        Log.i(TAG, String.valueOf(mViewModel.frequencyUnit));
    }

    public void wavelengthUnitChanged (View view) {
        int id = view.getId();
        if (id == R.id.mm_radioButton) {
            mViewModel.wavelengthUnit = WaveLengthViewModel.MILLIMETER;
            wavelengthUnitText.setText("mm");
        }
        if (id == R.id.cm_radioButton) {
            mViewModel.wavelengthUnit = WaveLengthViewModel.CENTIMETER;
            wavelengthUnitText.setText("cm");
        }
        if (id == R.id.meter_radioButton) {
            mViewModel.wavelengthUnit = WaveLengthViewModel.METER;
            wavelengthUnitText.setText("m");
        }
        if (id == R.id.kilometer_radioButton) {
            mViewModel.wavelengthUnit = WaveLengthViewModel.KILOMETER;
            wavelengthUnitText.setText("km");
        }
        EditText freqEditText = findViewById(R.id.freq_EditText);
        if (freqEditText.getText().toString().length() > 0 ) {
            clickFreqCalc(view);
        }
    }

    public void clickFreqCalc (View v) {
        try {
            EditText freqEditText = findViewById(R.id.freq_EditText);
            EditText waveEditText = findViewById(R.id.wave_EditText);
            BigDecimal frequency = new BigDecimal(freqEditText.getText().toString());
            BigDecimal wavelength = mViewModel.calculateWavelength(frequency);
            waveEditText.setText(String.valueOf(wavelength));
        } catch (Exception e) {
            Log.e(TAG,e.getMessage());
            showToast("Invalid Number Entered.\n(Division by zero?)");
        }
    }

    public void clickWaveCalc (View v) {
        try {
            EditText freqEditText = findViewById(R.id.freq_EditText);
            EditText waveEditText = findViewById(R.id.wave_EditText);
            BigDecimal wavelength = new BigDecimal(waveEditText.getText().toString());
            BigDecimal frequency = mViewModel.calculateFrequency(wavelength);
            freqEditText.setText(String.valueOf(frequency));
        } catch (Exception e) {
            showToast("Invalid Number Entered.\n(Division by zero?)");
        }
    }

    private void setRadioButtons () {
        Log.i(TAG, String.valueOf(mViewModel.frequencyUnit));
        switch (mViewModel.frequencyUnit) {
            case WaveLengthViewModel.HERTZ: {
                rbHZ.setChecked(true);
                freqUnitText.setText("Hz");
                Log.d(TAG,"hertz");
                break;
            }
            case WaveLengthViewModel.KILOHERTZ: {
                rbKHZ.setChecked(true);
                freqUnitText.setText("kHz");
                Log.d(TAG,"kilo");
                break;
            }
            case WaveLengthViewModel.MEGAHERTZ: {
                rbMHZ.setChecked(true);
                freqUnitText.setText("MHz");
                Log.d(TAG,"mega");
                break;
            }
            case WaveLengthViewModel.GIGAHERTZ: {
                rbGHZ.setChecked(true);
                freqUnitText.setText("GHz");
                Log.d(TAG,"giga");
                break;
            }
        }
        switch (mViewModel.wavelengthUnit) {
            case WaveLengthViewModel.MILLIMETER: {
                rbMM.setChecked(true);
                wavelengthUnitText.setText("mm");
                break;
            }
            case WaveLengthViewModel.CENTIMETER: {
                rbCM.setChecked(true);
                wavelengthUnitText.setText("cm");
                break;
            }
            case WaveLengthViewModel.METER: {
                rbM.setChecked(true);
                wavelengthUnitText.setText("m");
                break;
            }
            case WaveLengthViewModel.KILOMETER: {
                rbKM.setChecked(true);
                wavelengthUnitText.setText("km");
            }
        }
    }
    private void showToast (String s) {
        Toast.makeText(this,s, Toast.LENGTH_LONG).show();
    }
}