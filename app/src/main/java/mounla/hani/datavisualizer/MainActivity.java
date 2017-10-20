package mounla.hani.datavisualizer;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.rey.material.widget.Button;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    int colors [] = {Color.rgb(192, 255, 140), Color.rgb(255, 247, 140), Color.rgb(255, 208, 140),
            Color.rgb(140, 234, 255), Color.rgb(255, 140, 157),
            Color.rgb(193, 37, 82), Color.rgb(255, 102, 0), Color.rgb(245, 199, 0),
            Color.rgb(106, 150, 31), Color.rgb(179, 100, 53)};

    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        barChart = (BarChart)findViewById(R.id.barChart);


        Button b = (Button)findViewById(R.id.mainButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),DataList.class));
//                DataGrapper DG = new DataGrapper();
//                List<BarEntry> entries = DG.getData();
//                fillChart(entries);
            }
        });
    }

    private void fillChart(List<BarEntry> entries) {

        List<BarEntry> barEntries = entries;
        Legend l = barChart.getLegend();

        int i =1;
        for (BarEntry category: entries)
        {
            if(category.getX()!= 0 )
                barEntries.add(category);
        }


        BarDataSet barDataSet = new BarDataSet(barEntries, "Data");
        barDataSet.setColors();
        final BarData barData = new BarData(barDataSet);

        Description d = barChart.getDescription();
        d.setEnabled(false);

        barChart.setData(barData);
        barChart.invalidate();
        barChart.animateY(2000);

    }
}
