package com.challenge.bennho.a30days.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.controls.BottomBar;
import com.challenge.bennho.a30days.controls.ImageCircularFood;
import com.challenge.bennho.a30days.models.FoodModel;
import com.challenge.bennho.a30days.models.HistoryRecord;

import java.util.ArrayList;

public class HistoryActivity extends MyActivity {

    private BottomBar bottomBar;
    private ArrayList<HistoryRecord> historyRecords;
    private HistoryAdapter historyAdapter;
    private RecyclerView recycleViewItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recycleViewItems = (RecyclerView) findViewById(R.id.recycleViewItems);
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setCurrentSelectedPageIndex(1);

        populate();

        recycleViewItems = (RecyclerView) findViewById(R.id.recycleViewItems);
        recycleViewItems.setNestedScrollingEnabled(false);
        historyAdapter = new HistoryAdapter();
        recycleViewItems.setAdapter(historyAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
       // layoutManager.setAutoMeasureEnabled(false);
        recycleViewItems.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.history_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.graph) {
            Intent intent = new Intent(this, GraphActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    private void populate(){
        historyRecords = new ArrayList();
        HistoryRecord historyRecord = new HistoryRecord();
        historyRecord.setCompletedExercise(true);
        historyRecord.setCaloriesBurnt(3000);
        historyRecord.setDayNumber(1);
        historyRecord.setExerciseTimeMs(500 * 1000);
        historyRecord.setRecordUnixTime(System.currentTimeMillis());

        ArrayList<FoodModel> foodModels = new ArrayList();
        foodModels.add(new FoodModel(FoodModel.FoodType.french_fries));
        foodModels.add(new FoodModel(FoodModel.FoodType.french_fries));
        foodModels.add(new FoodModel(FoodModel.FoodType.french_fries));
        historyRecord.setFoodModels(foodModels);
        historyRecords.add(historyRecord);


        HistoryRecord historyRecord2 = new HistoryRecord();
        historyRecord2.setCompletedExercise(true);
        historyRecord2.setCaloriesBurnt(3000);
        historyRecord2.setDayNumber(1);
        historyRecord2.setExerciseTimeMs(500 * 1000);
        historyRecord2.setRecordUnixTime(System.currentTimeMillis());

        ArrayList<FoodModel> foodModels2 = new ArrayList();
        foodModels2.add(new FoodModel(FoodModel.FoodType.french_fries));
        foodModels2.add(new FoodModel(FoodModel.FoodType.french_fries));
        foodModels2.add(new FoodModel(FoodModel.FoodType.french_fries));
        historyRecord2.setFoodModels(foodModels2);
        historyRecords.add(historyRecord2);
    }



    private class HistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_history_item, parent, false);
            return new HistoryItemViewHolder(HistoryActivity.this, view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((HistoryItemViewHolder) holder).updateDesign(historyRecords.get(position));
        }

        @Override
        public int getItemCount() {
            return 2;
            //return historyRecords.size();
        }

        public class HistoryItemViewHolder extends RecyclerView.ViewHolder{

            private ImageCircularFood imageViewFood1, imageViewFood2, imageViewFood3;
            private TextView txtItemSeparatorThick, txtItemSeparatorThin;

            public HistoryItemViewHolder(final Context context, View itemView) {
                super(itemView);

                imageViewFood1 = (ImageCircularFood) itemView.findViewById(R.id.imgViewFood1);
                imageViewFood2 = (ImageCircularFood) itemView.findViewById(R.id.imgViewFood2);
                imageViewFood3 = (ImageCircularFood) itemView.findViewById(R.id.imgViewFood3);
                txtItemSeparatorThin = (TextView) itemView.findViewById(R.id.txtItemSeparatorThin);
                txtItemSeparatorThick = (TextView) itemView.findViewById(R.id.txtItemSeparatorThick);
            }

            public void updateDesign(HistoryRecord historyRecord){

                for(int i = 0; i < 3; i++){
                    FoodModel foodModel = null;
                    if(i <= historyRecord.getFoodModels().size()){
                        foodModel = historyRecord.getFoodModels().get(i);
                    }

                    ImageCircularFood imageCircularFood = null;
                    if(i == 0){
                        imageCircularFood = imageViewFood1;
                    }
                    else if(i == 1){
                        imageCircularFood = imageViewFood2;
                    }
                    else if(i == 2){
                        imageCircularFood = imageViewFood3;
                    }

                    imageCircularFood.setVisibility(foodModel == null ? View.GONE : View.VISIBLE);
                    imageCircularFood.setFood(foodModel);

                }

                txtItemSeparatorThick.setVisibility(View.VISIBLE);
                txtItemSeparatorThin.setVisibility(View.GONE);

            }


        }

    }




















}
