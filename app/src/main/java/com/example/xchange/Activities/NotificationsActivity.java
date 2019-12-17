package com.example.xchange.Activities;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.xchange.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class NotificationsActivity extends AppCompatActivity {


    private static final String TAG = "NotificationActivity";
    private LineChart mchart;
    private ArrayList<String> euro_values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        // Grab the dataset sended from History activity

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            ArrayList<String> euro_values = null;
        } else {
            ArrayList<String> euro_values = extras.getStringArrayList("Value_Set");

            mchart = (LineChart) findViewById(R.id.linechart);

            mchart.setDragEnabled(true);
            mchart.setScaleEnabled(false);


            //Values for LineChart diagram

            ArrayList<Entry> yValues = new ArrayList<>();

            for (int i = 0; i < euro_values.size(); i++) {
                float conv_val = Float.valueOf(euro_values.get(i));
                yValues.add(new Entry(i, conv_val));
            }

            //Settings for plotting

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
            //mchart.animateXY(4000,3000, Easing.EasingOption.EaseInOutBounce,Easing.EasingOption.EaseInExpo);

            //Show the actual diagram.

            mchart.setData(data);

        }
    }
}
