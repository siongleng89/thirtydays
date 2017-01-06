package com.challenge.bennho.a30days.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.controls.ImageCircularFood;
import com.challenge.bennho.a30days.controls.LayoutSummary;
import com.challenge.bennho.a30days.helpers.AdsMediation;
import com.challenge.bennho.a30days.helpers.DateTimeUtils;
import com.challenge.bennho.a30days.helpers.OverlayBuilder;
import com.challenge.bennho.a30days.helpers.RealmHelper;
import com.challenge.bennho.a30days.models.FoodModel;
import com.challenge.bennho.a30days.models.HistoryRecord;
import com.challenge.bennho.a30days.models.User;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class HistoryActivity extends MyActivity {

    private HistoryAdapter historyAdapter;
    private RecyclerView recycleViewItems;
    private LayoutSummary layoutSummary;
    private User user;
    private RealmHelper realmHelper;
    private RealmResults<HistoryRecord> historyRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        onLayoutSet();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AdsMediation.showInterstitial(this);

        layoutSummary = (LayoutSummary) findViewById(R.id.layoutSummary);
        recycleViewItems = (RecyclerView) findViewById(R.id.recycleViewItems);

        user = new User(this);
        user.reload();

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

    @Override
    protected void onStart() {
        super.onStart();
        realmHelper = new RealmHelper(this);
        getHistoryRecords();
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
                historyRecords = element;
                onHistoryRecordsRetrievedSuccess();
            }
        });
    }

    private void onHistoryRecordsRetrievedSuccess(){
        historyAdapter.notifyDataSetChanged();
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
            if(historyRecords == null || position >= historyRecords.size()){
                ((HistoryItemViewHolder) holder).updateDesignEmpty(position);
            }
            else{
                ((HistoryItemViewHolder) holder).updateDesign(historyRecords.get(position), position);
            }
        }

        @Override
        public int getItemCount() {
            if(historyRecords == null || historyRecords.size() == 0){
                return 2;
            }
            else{
                return historyRecords.size();
            }
        }

        public class HistoryItemViewHolder extends RecyclerView.ViewHolder{

            private TextView txtDayPlan, txtDate, txtDuration, txtCalories, txtIndex;
            private ImageView imgViewComplete, imgViewIncomplete;
            private ImageView imgViewDelete, imgViewShare;
            private ImageCircularFood imageViewFood1, imageViewFood2, imageViewFood3;
            private TextView txtItemSeparatorThick, txtItemSeparatorThin;
            private TextView txtDateTitle, txtPlanTitle, txtCompletedTitle,
                        txtDurationTitle, txtCaloriesTitle, txtKcalTitle, txtEqual,
                        txtNoRecords;
            private HistoryRecord historyRecord;


            public HistoryItemViewHolder(final Context context, View itemView) {
                super(itemView);

                txtIndex = (TextView) itemView.findViewById(R.id.txtIndex);
                txtDayPlan = (TextView) itemView.findViewById(R.id.txtPlanDay);
                txtDate = (TextView) itemView.findViewById(R.id.txtDate);
                txtDuration = (TextView) itemView.findViewById(R.id.txtDuration);
                txtCalories = (TextView) itemView.findViewById(R.id.txtCalories);
                imgViewComplete = (ImageView) itemView.findViewById(R.id.imgViewComplete);
                imgViewIncomplete = (ImageView) itemView.findViewById(R.id.imgViewIncomplete);
                imageViewFood1 = (ImageCircularFood) itemView.findViewById(R.id.imgViewFood1);
                imageViewFood2 = (ImageCircularFood) itemView.findViewById(R.id.imgViewFood2);
                imageViewFood3 = (ImageCircularFood) itemView.findViewById(R.id.imgViewFood3);
                imgViewDelete = (ImageView) itemView.findViewById(R.id.imgViewDelete);
                imgViewShare = (ImageView) itemView.findViewById(R.id.imgViewShare);
                txtItemSeparatorThin = (TextView) itemView.findViewById(R.id.txtItemSeparatorThin);
                txtItemSeparatorThick = (TextView) itemView.findViewById(R.id.txtItemSeparatorThick);

                txtDateTitle = (TextView) itemView.findViewById(R.id.txtDateTitle);
                txtPlanTitle = (TextView) itemView.findViewById(R.id.txtPlanTitle);
                txtCompletedTitle = (TextView) itemView.findViewById(R.id.txtCompletedTitle);
                txtDurationTitle = (TextView) itemView.findViewById(R.id.txtDurationTitle);
                txtCaloriesTitle = (TextView) itemView.findViewById(R.id.txtCaloriesTitle);
                txtKcalTitle = (TextView) itemView.findViewById(R.id.txtKcalTitle);
                txtEqual = (TextView) itemView.findViewById(R.id.txtEqual);
                txtNoRecords = (TextView) itemView.findViewById(R.id.txtNoRecords);

                imgViewDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        OverlayBuilder.build(HistoryActivity.this)
                                .setOverlayType(OverlayBuilder.OverlayType.OkCancel)
                                .setTitle("Delete History Record")
                                .setContent("Are you sure you want to delete this record? " +
                                        "Total exercise time and total calories recorded until now will be affected.")
                                .setRunnables(new Runnable() {
                                    @Override
                                    public void run() {
                                        user.minusCaloriesBurnt(historyRecord.getCaloriesBurnt());

                                        //last record
                                        if(historyRecords.size() == 1){
                                            user.minusRunningSecs(Integer.MAX_VALUE);
                                        }
                                        else{
                                            user.minusRunningSecs(historyRecord.getExerciseTimeMs() / 1000);
                                        }


                                        realmHelper.deleteHistoryRecord(historyRecord);
                                        layoutSummary.update(user);
                                    }
                                })
                                .show();
                    }
                });

            }

            public void updateDesign(HistoryRecord historyRecord, int position){
                this.historyRecord = historyRecord;


                txtIndex.setVisibility(View.VISIBLE);
                txtDayPlan.setVisibility(View.VISIBLE);
                txtDate.setVisibility(View.VISIBLE);
                txtDuration.setVisibility(View.VISIBLE);
                txtCalories.setVisibility(View.VISIBLE);
                imgViewComplete.setVisibility(View.VISIBLE);
                imgViewIncomplete.setVisibility(View.VISIBLE);
                imageViewFood1.setVisibility(View.VISIBLE);
                imageViewFood2.setVisibility(View.VISIBLE);
                imageViewFood3.setVisibility(View.VISIBLE);
                imgViewDelete.setVisibility(View.VISIBLE);
                imgViewShare.setVisibility(View.VISIBLE);

                txtDateTitle.setVisibility(View.VISIBLE);
                txtPlanTitle.setVisibility(View.VISIBLE);
                txtCompletedTitle.setVisibility(View.VISIBLE);
                txtDurationTitle.setVisibility(View.VISIBLE);
                txtCaloriesTitle.setVisibility(View.VISIBLE);
                txtKcalTitle.setVisibility(View.VISIBLE);
                txtEqual.setVisibility(View.VISIBLE);
                txtNoRecords.setVisibility(View.GONE);




                txtIndex.setText("#" + (historyRecords.size() - position));
                txtDate.setText(DateTimeUtils.convertUnixMsToDateTimeString(HistoryActivity.this,
                        historyRecord.getRecordUnixTime()));
                txtDayPlan.setText(String.format("Day %s", historyRecord.getDayNumber()));

                int minutes = (int) Math.ceil(historyRecord.getExerciseTimeMs() / 1000 / 60);

                txtDuration.setText(String.format("%s min(s)", minutes));
                txtCalories.setText(String.valueOf((int) Math.ceil(historyRecord.getCaloriesBurnt())));
                if(historyRecord.isCompletedExercise()){
                    imgViewComplete.setVisibility(View.VISIBLE);
                    imgViewIncomplete.setVisibility(View.GONE);
                }
                else{
                    imgViewComplete.setVisibility(View.GONE);
                    imgViewIncomplete.setVisibility(View.VISIBLE);
                }

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

            public void updateDesignEmpty(int position){
                txtIndex.setVisibility(View.INVISIBLE);
                txtDayPlan.setVisibility(View.INVISIBLE);
                txtDate.setVisibility(View.INVISIBLE);
                txtDuration.setVisibility(View.INVISIBLE);
                txtCalories.setVisibility(View.INVISIBLE);
                imgViewComplete.setVisibility(View.INVISIBLE);
                imgViewIncomplete.setVisibility(View.INVISIBLE);
                imageViewFood1.setVisibility(View.INVISIBLE);
                imageViewFood2.setVisibility(View.INVISIBLE);
                imageViewFood3.setVisibility(View.INVISIBLE);
                imgViewDelete.setVisibility(View.INVISIBLE);
                imgViewShare.setVisibility(View.INVISIBLE);

                txtDateTitle.setVisibility(View.INVISIBLE);
                txtPlanTitle.setVisibility(View.INVISIBLE);
                txtCompletedTitle.setVisibility(View.INVISIBLE);
                txtDurationTitle.setVisibility(View.INVISIBLE);
                txtCaloriesTitle.setVisibility(View.INVISIBLE);
                txtKcalTitle.setVisibility(View.INVISIBLE);
                txtEqual.setVisibility(View.INVISIBLE);
                txtItemSeparatorThin.setVisibility(View.VISIBLE);
                txtItemSeparatorThick.setVisibility(View.GONE);

                if(position == 0) txtNoRecords.setVisibility(View.VISIBLE);
            }


        }

    }




















}
