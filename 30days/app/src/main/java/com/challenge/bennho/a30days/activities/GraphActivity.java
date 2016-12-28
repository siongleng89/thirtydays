package com.challenge.bennho.a30days.activities;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.challenge.bennho.a30days.R;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

public class GraphActivity extends AppCompatActivity {

    private GraphView graphView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        GraphView graph = (GraphView) findViewById(R.id.graph);

        graph.getGridLabelRenderer().setGridColor(ContextCompat.getColor(GraphActivity.this,
                R.color.colorPrimaryDark));
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(31);

        graph.getGridLabelRenderer().setHorizontalLabelsColor(ContextCompat.getColor(GraphActivity.this,
                R.color.colorPrimaryDark));
        graph.getGridLabelRenderer().setVerticalLabelsColor(ContextCompat.getColor(GraphActivity.this,
                R.color.colorPrimaryDark));

        graph.getGridLabelRenderer().setHighlightZeroLines(true);

        DataPoint points[] = new DataPoint[30];
        for (int i = 0; i < 30; i++){
            points[i] = new DataPoint(i, i * 1000);
        }

        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(points);
        graph.addSeries(series);

        series.setSpacing(30);

        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return ContextCompat.getColor(GraphActivity.this, R.color.colorPrimary);
            }
        });

        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    // show normal x values
                    return super.formatLabel(value, isValueX);
                } else {

                    if(value > 1000){
                        return super.formatLabel(value/1000, isValueX) + "k";
                    }
                    else {
                        return super.formatLabel(value, isValueX);
                    }
                }
            }
        });

    }
}
