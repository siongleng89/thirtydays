package com.challenge.bennho.a30days.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.util.Pair;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.controls.ImageCircularFood;
import com.challenge.bennho.a30days.helpers.AdsMediation;
import com.challenge.bennho.a30days.helpers.DateTimeUtils;
import com.challenge.bennho.a30days.helpers.Logs;
import com.challenge.bennho.a30days.helpers.MealsInputter;
import com.challenge.bennho.a30days.helpers.NotificationShower;
import com.challenge.bennho.a30days.helpers.OverlayBuilder;
import com.challenge.bennho.a30days.models.DishModel;
import com.challenge.bennho.a30days.models.FoodModel;
import com.challenge.bennho.a30days.models.HistoryRecord;
import com.challenge.bennho.a30days.models.MealDayModel;
import com.challenge.bennho.a30days.models.User;

import org.w3c.dom.Text;


public class MealActivity extends MyActivity {

    private RecyclerView recycleViewItems;
    private MealAdapter mealAdapter;
    private MealDayModel mealDayModel;
    private MealsInputter mealsInputter;
    private TextView txtTip;
    private LinearLayout layoutViewIngredients;
    private TableLayout tableIngredients;
    private NestedScrollView scrollView;
    private int dayPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        onLayoutSet();

        AdsMediation.showInterstitial(this);

        scrollView = (NestedScrollView) findViewById(R.id.scrollView);
        txtTip = (TextView) findViewById(R.id.txtTip);
        layoutViewIngredients = (LinearLayout) findViewById(R.id.layoutViewIngredients);
        tableIngredients = (TableLayout) findViewById(R.id.tableIngredients);

        onNewIntent(getIntent());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setListeners();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        dayPlan = 1;
        if(intent != null){
            dayPlan = intent.getIntExtra("dayPlan", 1);
        }

        setTitle(String.format("Day %s Meal Plan", dayPlan));

        mealsInputter = new MealsInputter(this);
        mealDayModel = mealsInputter.getMealByDayNumber(dayPlan);
        update(mealsInputter.getTipByDayNumber(dayPlan));

        recycleViewItems = (RecyclerView) findViewById(R.id.recycleViewItems);
        recycleViewItems.setNestedScrollingEnabled(false);
        mealAdapter = new MealAdapter();
        recycleViewItems.setAdapter(mealAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // layoutManager.setAutoMeasureEnabled(false);
        recycleViewItems.setLayoutManager(layoutManager);
    }

    private void update(String todayTip){
        txtTip.setText(todayTip);

        for(int i = 1; i < tableIngredients.getChildCount(); i++){
            tableIngredients.removeViewAt(i);
        }

        for(Pair<String, String> ingredient : mealsInputter.getIngredientsByDayNumber(dayPlan)){
            TableRow tableRow = (TableRow) getLayoutInflater().inflate(R.layout.table_row_ingredient, null);

            TextView txtIngredient = (TextView) tableRow.findViewById(R.id.txtIngredient);
            TextView txtPortion = (TextView) tableRow.findViewById(R.id.txtPortion);
            txtIngredient.setText(ingredient.first);
            txtPortion.setText(ingredient.second);

            tableIngredients.addView(tableRow);
        }
    }

    private void showIngredients(){
        scrollView.smoothScrollTo(0, tableIngredients.getTop());
    }

    private void setListeners(){
        layoutViewIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showIngredients();
            }
        });
    }

    private class MealAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_meal_item, parent, false);
            return new MealAdapter.DishItemViewHolder(MealActivity.this, view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            DishModel dishModel = null;
            MealDayModel.MealType mealType = null;

            if(position == 0){
                mealType = MealDayModel.MealType.breakfast;
            }
            else if(position == 1){
                mealType = MealDayModel.MealType.morning_snack;
            }
            else if(position == 2){
                mealType = MealDayModel.MealType.lunch;
            }
            else if(position == 3){
                mealType = MealDayModel.MealType.evening_snack;
            }
            else if(position == 4){
                mealType = MealDayModel.MealType.dinner;
            }

            dishModel = mealDayModel.getDishByMealType(mealType);
            ((DishItemViewHolder) holder).updateDesign(mealType, dishModel);
        }

        @Override
        public int getItemCount() {
            return 5;
        }

        public class DishItemViewHolder extends RecyclerView.ViewHolder{

            private ImageView imgViewDish;
            private TextView txtMealType, txtMealName, txtMealInstruction;

            public DishItemViewHolder(final Context context, View itemView) {
                super(itemView);

                imgViewDish = (ImageView) itemView.findViewById(R.id.imgViewDish);

                txtMealType = (TextView) itemView.findViewById(R.id.txtMealType);
                txtMealName = (TextView) itemView.findViewById(R.id.txtMealName);
                txtMealInstruction = (TextView) itemView.findViewById(R.id.txtMealInstruction);

            }

            public void updateDesign(MealDayModel.MealType mealType, DishModel dishModel){
                String mealTypeString = "";
                switch (mealType){
                    case breakfast:
                        mealTypeString = "Breakfast";
                        break;
                    case morning_snack:
                        mealTypeString = "Morning Snack";
                        break;
                    case lunch:
                        mealTypeString = "Lunch";
                        break;
                    case evening_snack:
                        mealTypeString = "Evening Snack";
                        break;
                    case dinner:
                        mealTypeString = "Dinner";
                        break;
                }

                txtMealType.setText(mealTypeString);
                imgViewDish.setImageResource(dishModel.getImageResourceId());
                txtMealName.setText(dishModel.getDishName());
                txtMealInstruction.setText(dishModel.getInstruction());
            }
        }

    }

}
