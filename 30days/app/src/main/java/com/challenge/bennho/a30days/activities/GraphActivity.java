package com.challenge.bennho.a30days.activities;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.helpers.RealmHelper;
import com.challenge.bennho.a30days.models.HistoryRecord;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.HashMap;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class GraphActivity extends MyActivity {

    private GraphView graphView;
    private RealmHelper realmHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        setAdsLayout();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        realmHelper = new RealmHelper(this);

        graphView = (GraphView) findViewById(R.id.graph);

        graphView.getGridLabelRenderer().setGridColor(ContextCompat.getColor(GraphActivity.this,
                R.color.colorAction));
        graphView.getViewport().setXAxisBoundsManual(true);
        graphView.getViewport().setMinX(0);
        graphView.getViewport().setMaxX(31);

        graphView.getGridLabelRenderer().setHorizontalLabelsColor(ContextCompat.getColor(GraphActivity.this,
                R.color.colorAction));
        graphView.getGridLabelRenderer().setVerticalLabelsColor(ContextCompat.getColor(GraphActivity.this,
                R.color.colorAction));

        graphView.getGridLabelRenderer().setHighlightZeroLines(true);

        getHistoryRecords();

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

    @Override
    protected void onStop() {
        super.onStop();
        realmHelper.dispose();
    }


    private void getHistoryRecords(){
        realmHelper.getAllHistoryRecordsByDateDesc(new RealmChangeListener<RealmResults<HistoryRecord>>() {
            @Override
            public void onChange(RealmResults<HistoryRecord> element) {
                HashMap<Integer, Integer> dailyBurntMap = new HashMap<Integer, Integer>();

                for(int i = 0; i < 30; i++){
                    dailyBurntMap.put(i, 0);
                }

                for(HistoryRecord historyRecord : element){
                    int currentBurnt = dailyBurntMap.get(historyRecord.getDayNumber() - 1);
                    currentBurnt += historyRecord.getCaloriesBurnt();
                    dailyBurntMap.put(historyRecord.getDayNumber() - 1, currentBurnt);
                }


                DataPoint points[] = new DataPoint[30];
                for (int i = 0; i < 30; i++){
                    points[i] = new DataPoint(i, dailyBurntMap.get(i));
                }

                BarGraphSeries<DataPoint> series = new BarGraphSeries<>(points);
                graphView.addSeries(series);

                series.setSpacing(30);

                series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return ContextCompat.getColor(GraphActivity.this, R.color.colorAccent);
                    }
                });

                graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
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
        });
    }

}
