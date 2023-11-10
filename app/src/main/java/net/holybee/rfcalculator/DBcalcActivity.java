package net.holybee.rfcalculator;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


public class DBcalcActivity extends AppCompatActivity {
    private DBcalcViewModel mViewModel;

    // private StringBuilder builder;
    private static final String TAG = DBcalcActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rfcalc);

        mViewModel = new ViewModelProvider(this).get(DBcalcViewModel.class);
        TextView wattview = findViewById(R.id.wattView);
        TextView dbwview = findViewById(R.id.dbwView);
        TextView dbmview = findViewById(R.id.dbmView);
        TextView text = findViewById(R.id.input);
        wattview.setText(mViewModel.Watt);
        dbwview.setText(mViewModel.dbW);
        dbmview.setText(mViewModel.dbm);
        text.setText(mViewModel.builder);
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


    public void dbCalcKeyPress(View view) {

        int id = view.getId();
        if (id == R.id.addButton) {
            mViewModel.builder.append(" + ");
        } else if (id == R.id.subButton) {
            mViewModel.builder.append(" - ");
        } else if (id == R.id.negbutton) {
            mViewModel.builder.append("-");
        } else if (id == R.id.dbButton) {
            mViewModel.builder.append("db");
        } else if (id == R.id.dbmButton) {
            mViewModel.builder.append("dbm");
        } else if (id == R.id.dbWButton) {
            mViewModel.builder.append("dbW");
        } else if (id == R.id.zerobutton) {
            mViewModel.builder.append("0");
        } else if (id == R.id.oneButton) {
            mViewModel.builder.append("1");
        } else if (id == R.id.twoButton) {
            mViewModel.builder.append("2");
        } else if (id == R.id.threeButton) {
            mViewModel.builder.append("3");
        } else if (id == R.id.fourButton) {
            mViewModel.builder.append("4");
        } else if (id == R.id.fiveButton) {
            mViewModel.builder.append("5");
        } else if (id == R.id.sixButton) {
            mViewModel.builder.append("6");
        } else if (id == R.id.sevenButton) {
            mViewModel.builder.append("7");
        } else if (id == R.id.eightButton) {
            mViewModel.builder.append("8");
        } else if (id == R.id.nineButton) {
            mViewModel.builder.append("9");
        } else if (id == R.id.decButton) {
            mViewModel.builder.append(".");
        } else if (id == R.id.clearButton){
            mViewModel.builder = new StringBuilder();
        } else if (id == R.id.equalButton) {
            String str = mViewModel.builder.toString();
            String[] strs = str.split("\\s");
            TextView wattview = findViewById(R.id.wattView);
            TextView dbwview = findViewById(R.id.dbwView);
            TextView dbmview = findViewById(R.id.dbmView);
            TextView text = findViewById(R.id.input);

            for (int i=0;i<strs.length;i++) {
                Log.e("strs",strs[i]);
            }
            Power p = new Power(strs);
            if (p.watt().floatValue() >= 0) {


                String watts = p.watt().toPlainString() + " W";
                wattview.setText(watts);
                mViewModel.Watt = watts;

                String dbws = p.dbW().toPlainString() + " dbW";
                dbwview.setText(dbws);
                mViewModel.dbW = dbws;
                
                String dbms = p.dbm().toPlainString() + " dbm";
                dbmview.setText(dbms);
                mViewModel.dbm=dbms;

                // clear builder
                mViewModel.builder = new StringBuilder();
                mViewModel.builder.append(p.watt().toPlainString());

            } else {
                wattview.setText("Error");
                dbmview.setText(" ");
                dbwview.setText(" ");
                text.setText("Error in Entry");
                mViewModel.builder = new StringBuilder();
                return;

            }
        }
        TextView text = findViewById(R.id.input);
        HorizontalScrollView scrollView = findViewById(R.id.scrollView);
        text.setText(mViewModel.builder.toString());

       scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(View.FOCUS_RIGHT);
            }
        });

        }

}
