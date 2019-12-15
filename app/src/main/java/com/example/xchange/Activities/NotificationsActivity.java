package com.example.xchange.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.xchange.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class NotificationsActivity extends AppCompatActivity {


    private static final String TAG = "NotificationActivity";
    private LineChart mchart;
    private ArrayList<String> euro_values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);


        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            ArrayList<String> euro_values = null;
        } else {
            ArrayList<String> euro_values = extras.getStringArrayList("Value_Set");

            int year = Integer.valueOf(euro_values.get(euro_values.size() - 1));

            mchart = (LineChart) findViewById(R.id.linechart);

            mchart.setDragEnabled(true);
            mchart.setScaleEnabled(false);

            ArrayList<Entry> yValues = new ArrayList<>();

            //int counter = 0;
            for (int i = 0; i < euro_values.size() - 1; i++) {
                float conv_val = Float.valueOf(euro_values.get(i));
                yValues.add(new Entry(i, conv_val));
//                    counter++;
//                    if (counter == 12)
//                    {
//                        counter = 0;
//                        year++;
//                    }
            }


            LineDataSet line_data = new LineDataSet(yValues, "Euro Values");
            line_data.setFillAlpha(110);
            line_data.setColor(Color.RED);
            line_data.setLineWidth(3f);
            line_data.setValueTextSize(16f);
            line_data.setValueTextColor(Color.MAGENTA);

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(line_data);

            LineData data = new LineData(dataSets);

            float zoom_x_val = euro_values.size() / 10 * 2;
            mchart.setScaleMinima(zoom_x_val, 1f);

            mchart.setData(data);

            //sendEmail(euro_values.get(euro_values.size() - 2));
        }
    }


    protected void sendEmail(String value) {
        Log.i("Send email", "NEW VALUE : " + value);
        String[] TO = {""};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "NEW VALUE DETECTED");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "The new value of euro is " + value);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(NotificationsActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

}
