package com.challenge.bennho.a30days.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v4.widget.NestedScrollView;
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
import com.challenge.bennho.a30days.enums.AnalyticEvent;
import com.challenge.bennho.a30days.helpers.AdsMediation;
import com.challenge.bennho.a30days.helpers.Analytics;
import com.challenge.bennho.a30days.helpers.MealsInputter;
import com.challenge.bennho.a30days.helpers.ProVersionHelpers;
import com.challenge.bennho.a30days.helpers.RunnableArgs;
import com.challenge.bennho.a30days.helpers.Strings;
import com.challenge.bennho.a30days.models.DishModel;
import com.challenge.bennho.a30days.models.IngredientModel;
import com.challenge.bennho.a30days.models.MealDayModel;
import com.challenge.bennho.a30days.models.User;


public class MealActivity extends MyActivity {

    private User user;
    private RecyclerView recycleViewItems;
    private MealAdapter mealAdapter;
    private MealDayModel mealDayModel;
    private MealsInputter mealsInputter;
    private TextView txtTip, txtViewShoppingPeriod, txtDailyCalories, txtLossCalories, txtMealCalories;
    private LinearLayout layoutViewIngredients, layoutIngredients;
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
        txtDailyCalories = (TextView) findViewById(R.id.txtDailyCalories);
        txtMealCalories = (TextView) findViewById(R.id.txtMealCalories);
        txtLossCalories = (TextView) findViewById(R.id.txtLossCalories) ;
        txtViewShoppingPeriod = (TextView) findViewById(R.id.txtViewShoppingPeriod);
        layoutViewIngredients = (LinearLayout) findViewById(R.id.layoutViewIngredients);
        layoutIngredients = (LinearLayout) findViewById(R.id.layoutIngredients);

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

        setTitle(String.format(getString(R.string.avty_meal_title), String.valueOf(dayPlan)));
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(dayPlan > 0){
            checkCanViewMealPlan();
        }
    }

    private void checkCanViewMealPlan(){

        user = new User(this);
        user.reload();

        if(dayPlan < 8){
            showMealPlan();
        }
        else{
            getProVersionHelpers().isProPurchased(new RunnableArgs<Boolean>() {
                @Override
                public void run() {
                    if(this.getFirstArg()){
                        showMealPlan();
                    }
                    else{
                        purchasePro();
                        txtTip.setText(getString(R.string.locked));
                        txtViewShoppingPeriod.setText(getString(R.string.locked));
                    }
                }
            });
        }
    }

    private void showMealPlan(){
        int startDay, endDay;
        if(dayPlan <= 7){
            startDay = 1;
            endDay = 7;
        }
        else if(dayPlan <= 15){
            startDay = 8;
            endDay = 15;
        }
        else if(dayPlan <= 23){
            startDay = 16;
            endDay = 23;
        }
        else{
            startDay = 24;
            endDay = 30;
        }

        txtViewShoppingPeriod.setText(String.format(getString(R.string.avty_meal_shopping_list_day_x_to_day_x),
                String.valueOf(startDay), String.valueOf(endDay)));

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

        Analytics.logEvent(AnalyticEvent.ViewMeal, "MealDay" + dayPlan);
    }

    private void update(String todayTip){
        txtTip.setText(todayTip);
        txtDailyCalories.setText(String.valueOf(mealsInputter.caloriesNeeded(user)));

        int totalMealCalories=0;
        for(DishModel dishModel: mealDayModel.getDishesMap().values()){
            totalMealCalories += dishModel.getCalories();
        }
        txtMealCalories.setText(String.valueOf(totalMealCalories));

        int totalCaloriesLoss = mealsInputter.caloriesNeeded(user) - totalMealCalories;

        txtLossCalories.setText(String.valueOf(totalCaloriesLoss));


        layoutIngredients.removeAllViews();

        IngredientModel.IngredientType previousIngredientType = IngredientModel.IngredientType.empty;

        LinearLayout layoutTableIngredients = null;
        TableLayout layoutTable = null;

        for(IngredientModel ingredient : mealsInputter.getIngredientsByDayNumber(dayPlan)){
            if(previousIngredientType != ingredient.getIngredientType()){
                layoutTableIngredients = (LinearLayout) getLayoutInflater().inflate(R.layout.table_ingredients, null);
                layoutTable = (TableLayout) layoutTableIngredients.findViewById(R.id.layoutTable);
                TextView txtTitle = (TextView) layoutTableIngredients.findViewById(R.id.txtTitle);

                txtTitle.setText(ingredient.getIngredientType().toString(this));
                previousIngredientType = ingredient.getIngredientType();

                layoutIngredients.addView(layoutTableIngredients);
            }

            TableRow tableRow = (TableRow) getLayoutInflater().inflate(R.layout.table_row_ingredient, null);

            TextView txtIngredient = (TextView) tableRow.findViewById(R.id.txtIngredient);
            TextView txtPortion = (TextView) tableRow.findViewById(R.id.txtPortion);
            txtIngredient.setText(ingredient.getName());
            txtPortion.setText(ingredient.getQuantity());

            layoutTable.addView(tableRow);
        }
    }

    private void showIngredients(){
        scrollView.smoothScrollTo(0, layoutIngredients.getTop());
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
            private TextView txtMealType, txtMealName, txtMealInstruction, txtItemCalories;

            public DishItemViewHolder(final Context context, View itemView) {
                super(itemView);

                imgViewDish = (ImageView) itemView.findViewById(R.id.imgViewDish);

                txtMealType = (TextView) itemView.findViewById(R.id.txtMealType);
                txtMealName = (TextView) itemView.findViewById(R.id.txtMealName);
                txtMealInstruction = (TextView) itemView.findViewById(R.id.txtMealInstruction);
                txtItemCalories = (TextView) itemView.findViewById(R.id.txtItemCalories);

            }

            public void updateDesign(MealDayModel.MealType mealType, DishModel dishModel){
                String mealTypeString = "";
                switch (mealType){
                    case breakfast:
                        mealTypeString = getString(R.string.avty_meal_breakfast);
                        break;
                    case morning_snack:
                        mealTypeString = getString(R.string.avty_meal_morning_snack);
                        break;
                    case lunch:
                        mealTypeString = getString(R.string.avty_meal_lunch);
                        break;
                    case evening_snack:
                        mealTypeString = getString(R.string.avty_meal_evening_snack);
                        break;
                    case dinner:
                        mealTypeString = getString(R.string.avty_meal_dinner);
                        break;
                }

                txtMealType.setText(mealTypeString);
                imgViewDish.setImageResource(dishModel.getImageResourceId());
                txtMealName.setText(dishModel.getDishName());
                txtMealInstruction.setText(dishModel.getInstruction());
                txtItemCalories.setText(String.format(getString(R.string.x_kcal),String.valueOf(dishModel.getCalories())));
            }
        }

    }

}
