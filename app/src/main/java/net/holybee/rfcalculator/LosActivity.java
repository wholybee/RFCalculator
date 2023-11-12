package net.holybee.rfcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class LosActivity extends AppCompatActivity {

    private LosViewModel mViewModel;
    private RadioButton rbHeightFt, rbHeightM, rbDistM, rbDistKm, rbDistMile, rbDistNM;
    private TextView heightUnitText, radioUnitText, losUnitText;
    private EditText losDistanceEditText, radioDistanceEditText;
    private EditText heightEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_los);
        mViewModel = new ViewModelProvider(this).get(LosViewModel.class);
        rbHeightFt = findViewById(R.id.los_feet_radioButton);
        rbHeightM = findViewById(R.id.los_meter_radioButton);
        rbDistM = findViewById(R.id.los_distance_meter_radioButton);
        rbDistKm = findViewById(R.id.los_distance_km_radioButton);
        rbDistMile = findViewById(R.id.los_distance_mile_radioButton);
        rbDistNM = findViewById(R.id.los_distance_nautical_mile_radioButton);
        heightUnitText = findViewById(R.id.losHeightUnitTextView);
        radioUnitText = findViewById(R.id.losRadioUnitTextView);
        losUnitText = findViewById(R.id.losDistanceUnitTextView);
        heightEditText = findViewById(R.id.losHeightEditText);
        losDistanceEditText = findViewById(R.id.losDistanceEditText);
        radioDistanceEditText = findViewById(R.id.losRadioEditText);

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

    public void heightUnitChanged (View view) {
        int id = view.getId();
        if (id == R.id.los_meter_radioButton) {
            mViewModel.heightUnit = LosViewModel.METER;
            heightUnitText.setText("m");
        }
        if (id == R.id.los_feet_radioButton) {
            mViewModel.heightUnit = LosViewModel.FEET;
            heightUnitText.setText("ft");
        }
    }

    public void distanceUnitChanged (View view) {
        int id = view.getId();
        if (id == R.id.los_distance_meter_radioButton){
            mViewModel.distanceUnit = LosViewModel.METER;
            losUnitText.setText("m");
            radioUnitText.setText("m");

        }
        if (id == R.id.los_distance_km_radioButton) {
            mViewModel.distanceUnit = LosViewModel.KILOMETER;
            losUnitText.setText("km");
            radioUnitText.setText("km");
        }
        if (id == R.id.los_distance_mile_radioButton) {
            mViewModel.distanceUnit = LosViewModel.MILE;
            losUnitText.setText("mi");
            radioUnitText.setText("mi");
        }
        if (id == R.id.los_distance_nautical_mile_radioButton) {
            mViewModel.distanceUnit = LosViewModel.NAUTICALMILE;
            losUnitText.setText("nm");
            radioUnitText.setText("nm");
        }
        if (heightEditText.toString().length() > 0) {
            clickCalculate(view);
        }
    }


    public void clickCalculate (View view) {
        try {
            BigDecimal height = new BigDecimal(heightEditText.getText().toString());
            BigDecimal losDistance = mViewModel.calculateLos(height);
            BigDecimal radioDistance = mViewModel.calculateRadio(height);
            losDistanceEditText.setText(String.valueOf(losDistance));
            radioDistanceEditText.setText(String.valueOf(radioDistance));

        } catch (Exception e) {
            showToast("Invalid Number Entered.\n(Division by zero?)");
        }
    }

    private void setRadioButtons() {
        switch (mViewModel.distanceUnit) {
            case LosViewModel.METER: {
                rbDistM.setChecked(true);
                radioUnitText.setText("m");
                losUnitText.setText("m");
                break;
            }
            case LosViewModel.KILOMETER: {
                rbDistKm.setChecked(true);
                radioUnitText.setText("km");
                losUnitText.setText("km");
                break;
            }
            case LosViewModel.MILE: {
                rbDistMile.setChecked(true);
                radioUnitText.setText("mi");
                losUnitText.setText("mi");
                break;
            }
            case LosViewModel.NAUTICALMILE: {
                rbDistNM.setChecked(true);
                radioUnitText.setText("nm");
                losUnitText.setText("nm");
                break;
            }
        }
        switch (mViewModel.heightUnit) {
            case LosViewModel.METER: {
                rbHeightM.setChecked(true);
                heightUnitText.setText("m");
                break;
            }
            case LosViewModel.FEET: {
                rbHeightFt.setChecked(true);
                heightUnitText.setText("ft");
            }
        }
    }
    private void showToast (String s) {
        Toast.makeText(this,s, Toast.LENGTH_LONG).show();
    }
}