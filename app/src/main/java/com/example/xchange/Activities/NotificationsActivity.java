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

import java.util.ArrayList;
import java.util.Calendar;

public class NotificationsActivity extends AppCompatActivity {


    private static final String TAG = "NotificationActivity";
    private LineChart mchart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        mchart = (LineChart) findViewById(R.id.linechart);
//        mchart.setOnChartGestureListener(NotificationsActivity.this);
//        mchart.setOnChartValueSelectedListener(NotificationsActivity.this);

        mchart.setDragEnabled(true);
        mchart.setScaleEnabled(false);

        ArrayList<Entry> yValues = new ArrayList<>();

        double Value = 3.89f;
        int Year = 2010;

        for (; Year <= Calendar.getInstance().get(Calendar.YEAR) + 1; Value += 0.1, Year++) {
            yValues.add(new Entry(Year, (float) Value));
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

        mchart.setData(data);


        // sendEmail(Value);
    }


    protected void sendEmail(Double value) {
        Log.i("Send email", "");
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
