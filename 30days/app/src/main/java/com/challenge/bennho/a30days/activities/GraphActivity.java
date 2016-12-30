package com.challenge.bennho.a30days.activities;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.challenge.bennho.a30days.R;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

public class GraphActivity extends MyActivity {

    private GraphView graphView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        GraphView graph = (GraphView) findViewById(R.id.graph);

        graph.getGridLabelRenderer().setGridColor(ContextCompat.getColor(GraphActivity.this,
                R.color.colorAction));
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(31);

        graph.getGridLabelRenderer().setHorizontalLabelsColor(ContextCompat.getColor(GraphActivity.this,
                R.color.colorAction));
        graph.getGridLabelRenderer().setVerticalLabelsColor(ContextCompat.getColor(GraphActivity.this,
                R.color.colorAction));

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
                return ContextCompat.getColor(GraphActivity.this, R.color.colorAccent);
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

    @Override
    protected void onPause() {
        super.onPause();
        this.overridePendingTransition(0, 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.graph_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.history) {
            Intent intent = new Intent(this, HistoryActivity.class);
            startActivity(intent);
            this.overridePendingTransition(0, 0);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
