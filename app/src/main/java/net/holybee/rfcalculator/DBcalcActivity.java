package net.holybee.rfcalculator;


import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class DBcalcActivity extends AppCompatActivity {

    private StringBuilder builder;
    private static final String TAG = DBcalcActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        builder = new StringBuilder();  // String Builder for creating expression
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rfcalc);

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
            builder.append(" + ");
        } else if (id == R.id.subButton) {
            builder.append(" - ");
        } else if (id == R.id.negbutton) {
            builder.append("-");
        } else if (id == R.id.dbButton) {
            builder.append("db");
        } else if (id == R.id.dbmButton) {
            builder.append("dbm");
        } else if (id == R.id.dbWButton) {
            builder.append("dbW");
        } else if (id == R.id.zerobutton) {
            builder.append("0");
        } else if (id == R.id.oneButton) {
            builder.append("1");
        } else if (id == R.id.twoButton) {
            builder.append("2");
        } else if (id == R.id.threeButton) {
            builder.append("3");
        } else if (id == R.id.fourButton) {
            builder.append("4");
        } else if (id == R.id.fiveButton) {
            builder.append("5");
        } else if (id == R.id.sixButton) {
            builder.append("6");
        } else if (id == R.id.sevenButton) {
            builder.append("7");
        } else if (id == R.id.eightButton) {
            builder.append("8");
        } else if (id == R.id.nineButton) {
            builder.append("9");
        } else if (id == R.id.decButton) {
            builder.append(".");
        } else if (id == R.id.clearButton){
            builder = new StringBuilder();
        } else if (id == R.id.equalButton) {
            String str = builder.toString();
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

                String dbws = p.dbW().toPlainString() + " dbW";
                dbwview.setText(dbws);

                String dbms = p.dbm().toPlainString() + " dbm";
                dbmview.setText(dbms);

                // clear builder
                builder = new StringBuilder();
                builder.append(p.watt().toPlainString());

            } else {
                wattview.setText("Error");
                dbmview.setText(" ");
                dbwview.setText(" ");
                text.setText("Error in Entry");
                builder = new StringBuilder();
                return;

            }
        }
        TextView text = findViewById(R.id.input);
        HorizontalScrollView scrollView = findViewById(R.id.scrollView);
        text.setText(builder.toString());

       scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(View.FOCUS_RIGHT);
            }
        });

        }

}
