package com.challenge.bennho.a30days.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.activities.GraphActivity;
import com.challenge.bennho.a30days.activities.HistoryActivity;
import com.challenge.bennho.a30days.activities.MainActivity;
import com.challenge.bennho.a30days.activities.MyActivity;
import com.challenge.bennho.a30days.activities.SettingsActivity;
import com.challenge.bennho.a30days.activities.TutorialActivity;
import com.challenge.bennho.a30days.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sionglengho on 6/1/17.
 */

public class DrawerHelper implements ListView.OnItemClickListener {


    private DrawerLayout layoutDrawer;
    private ListView listViewDrawer;
    private MyActivity activity;
    private CustomDrawerAdapter drawerAdapter;
    private List<String> drawItemList;
    private User user;

    public DrawerHelper(MyActivity activity) {
        this.activity = activity;
        user = new User(activity);
        user.reload();
    }

    public void show(){
        layoutDrawer = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        if(layoutDrawer != null){
            drawItemList = Arrays.asList(activity.getResources().getStringArray(R.array.drawItems));

            listViewDrawer = (ListView) activity.findViewById(R.id.left_drawer);
            drawerAdapter = new CustomDrawerAdapter(activity,drawItemList);

            listViewDrawer.setAdapter(drawerAdapter);
            listViewDrawer.setOnItemClickListener(this);

            ActionBarDrawerToggle actionBarDrawerToggle =
                    new ActionBarDrawerToggle(activity, layoutDrawer, activity.getToolbar(),
                            R.string.accept, R.string.accept) {

                        @Override
                        public void onDrawerClosed(View drawerView) {
                            super.onDrawerClosed(drawerView);
                        }

                        @Override
                        public void onDrawerOpened(View drawerView) {
                            super.onDrawerOpened(drawerView);
                        }
                    };

            layoutDrawer.addDrawerListener(actionBarDrawerToggle);
            actionBarDrawerToggle.syncState();
        }
    }

    public void refreshDayCounter(){
        if(layoutDrawer == null) return;

        Threadings.runInBackground(new Runnable() {
            @Override
            public void run() {
                user.reload();
                Threadings.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        drawerAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = null;
        if(position == 0){
            intent = new Intent(activity, MainActivity.class);
            layoutDrawer.closeDrawer(Gravity.LEFT);
        }
        else{
            String previousItem = "";
            boolean isMenuTitle = previousItem.equals("-");
            String item = drawItemList.get(position);

            if(!isMenuTitle){
                if(item.equals(activity.getString(R.string.drawer_item_history_list))){
                    intent = new Intent(activity, HistoryActivity.class);
                }
                else if(item.equals(activity.getString(R.string.drawer_item_history_graph))){
                    intent = new Intent(activity, GraphActivity.class);
                }
                else if(item.equals(activity.getString(R.string.drawer_item_exercise_tutorial))){
                    intent = new Intent(activity, TutorialActivity.class);
                }
                else if(item.equals(activity.getString(R.string.drawer_item_settings))){
                    intent = new Intent(activity, SettingsActivity.class);
                }
                else if(item.equals(activity.getString(R.string.drawer_item_pro_version))){
                    activity.purchasePro();
                }
                else if(item.equals(activity.getString(R.string.drawer_item_share))){
                    shareApps();
                }
                else if(item.equals(activity.getString(R.string.drawer_item_rate))){
                    rateApps();
                }
                else if(item.equals(activity.getString(R.string.drawer_item_contact))){
                    contactUs();
                }
                else if(item.equals(activity.getString(R.string.drawer_item_about))){
                    aboutUs();
                }
            }
            else{
                return;
            }
        }

        if(intent != null){
            activity.startActivity(intent);
        }


        Threadings.delay(500, new Runnable() {
            @Override
            public void run() {
                layoutDrawer.closeDrawer(Gravity.LEFT);
            }
        });

    }

    private void shareApps(){
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Download 30 days running weight loss + diet to shape your body today! https://play.google.com/store/apps/details?id=com.challenge.bennho.a30days";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "30 days running weight loss");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        activity.startActivityForResult(Intent.createChooser(sharingIntent, "Share"), 123);
    }

    private void rateApps(){
        final String appPackageName = activity.getPackageName(); // getPackageName() from Context or Activity object
        try {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    private void contactUs(){
        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"benjandho@gmail.com"});
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "30 days running weight loss support");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "");

        activity.startActivity(Intent.createChooser(emailIntent, "Contact us"));
    }

    private void aboutUs(){
        PackageInfo pInfo = null;
        try {
            pInfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0);
            String version = pInfo.versionName;

            OverlayBuilder.build(activity)
                    .setOverlayType(OverlayBuilder.OverlayType.OkOnly)
                    .setTitle(activity.getString(R.string.app_name))
                    .setContent(String.format("Version: %s", version))
                    .show();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private class CustomDrawerAdapter extends ArrayAdapter<String> {

        Context context;
        List<String> drawerItemList;

        public CustomDrawerAdapter(Context context,
                                   List<String> listItems) {
                super(context, R.layout.layout_drawer_item, listItems);
                this.context = context;
                this.drawerItemList = listItems;

            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // TODO Auto-generated method stub

                DrawerItemHolder drawerHolder;
            View view = convertView;

            String drawerItem = this.drawerItemList.get(position);
            String previousItem = "";
            boolean isDayCount = false, isMenuTitle = false, isSpacing = false;

            if(position == 0){
                isDayCount = true;
            }
            else if(position > 0){
                previousItem = this.drawerItemList.get(position - 1);
            }

            isMenuTitle = previousItem.equals("-");
            isSpacing = Strings.isEmpty(drawerItem);

            if (view == null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                drawerHolder = new DrawerItemHolder();

                view = inflater.inflate(R.layout.layout_drawer_item, parent, false);
                drawerHolder.txtTitle = (TextView) view.findViewById(R.id.txtTitle);
                drawerHolder.txtDayCounter = (TextView) view.findViewById(R.id.txtDayCounter);
                drawerHolder.txtDayText = (TextView) view.findViewById(R.id.txtDayText);
                drawerHolder.txtItemContent = (TextView) view.findViewById(R.id.txtItemContent);
                drawerHolder.imgIcon = (ImageView) view.findViewById(R.id.imgIcon);
                drawerHolder.layoutSpacing = (LinearLayout) view.findViewById(R.id.layoutSpacing);
                drawerHolder.layoutDay = (RelativeLayout) view.findViewById(R.id.layoutDay);
                drawerHolder.layoutTitle = (LinearLayout) view.findViewById(R.id.layoutTitle);
                drawerHolder.layoutItem = (LinearLayout) view.findViewById(R.id.layoutItem);

                view.setTag(drawerHolder);

            } else {
                drawerHolder = (DrawerItemHolder) view.getTag();

            }

            //is a dummy title marker only
            if(drawerItem.equals("-")){
                drawerHolder.layoutDay.setVisibility(View.GONE);
                drawerHolder.layoutTitle.setVisibility(View.GONE);
                drawerHolder.layoutItem.setVisibility(View.GONE);
                drawerHolder.layoutSpacing.setVisibility(View.GONE);
            }
            else if(isDayCount){
                drawerHolder.layoutDay.setVisibility(View.VISIBLE);
                drawerHolder.layoutTitle.setVisibility(View.GONE);
                drawerHolder.layoutItem.setVisibility(View.GONE);
                drawerHolder.layoutSpacing.setVisibility(View.GONE);

                int userMaxDay = user.getCurrentDay();
                drawerHolder.txtDayCounter.setText(String.format("%s/30", userMaxDay - 1));
                drawerHolder.txtDayText.setText(String.format("Go to Day %s Plan", Math.min(userMaxDay, 30)));
            }
            else if(isMenuTitle){
                drawerHolder.layoutDay.setVisibility(View.GONE);
                drawerHolder.layoutTitle.setVisibility(View.VISIBLE);
                drawerHolder.layoutItem.setVisibility(View.GONE);
                drawerHolder.layoutSpacing.setVisibility(View.GONE);

                drawerHolder.txtTitle.setText(drawerItem);
            }
            else if(isSpacing){
                drawerHolder.layoutDay.setVisibility(View.GONE);
                drawerHolder.layoutTitle.setVisibility(View.GONE);
                drawerHolder.layoutItem.setVisibility(View.GONE);
                drawerHolder.layoutSpacing.setVisibility(View.VISIBLE);
            }
            else{
                drawerHolder.layoutDay.setVisibility(View.GONE);
                drawerHolder.layoutTitle.setVisibility(View.GONE);
                drawerHolder.layoutItem.setVisibility(View.VISIBLE);
                drawerHolder.layoutSpacing.setVisibility(View.GONE);

                drawerHolder.txtItemContent.setText(drawerItem);

                int drawableRes = 0;
                if(drawerItem.equals(activity.getString(R.string.drawer_item_history_list))){
                    drawableRes = R.drawable.history_icon;
                }
                else if(drawerItem.equals(activity.getString(R.string.drawer_item_history_graph))){
                    drawableRes = R.drawable.graph_icon;
                }
                else if(drawerItem.equals(activity.getString(R.string.drawer_item_exercise_tutorial))){
                    drawableRes = R.drawable.tutorial_icon;
                }
                else if(drawerItem.equals(activity.getString(R.string.drawer_item_settings))){
                    drawableRes = R.drawable.settings_icon;
                }
                else if(drawerItem.equals(activity.getString(R.string.drawer_item_pro_version))){
                    drawableRes = R.drawable.pro_version_lock_icon;
                }
                else if(drawerItem.equals(activity.getString(R.string.drawer_item_rate))){
                    drawableRes = R.drawable.rate_us_icon;
                }
                else if(drawerItem.equals(activity.getString(R.string.drawer_item_contact))){
                    drawableRes = R.drawable.contact_us_icon;
                }
                else if(drawerItem.equals(activity.getString(R.string.drawer_item_about))){
                    drawableRes = R.drawable.about_us_icon;
                }
                else if(drawerItem.equals(activity.getString(R.string.drawer_item_share))){
                    drawableRes = R.drawable.share_icon;
                }
                drawerHolder.imgIcon.setImageResource(drawableRes);

            }

            return view;
        }

        private class DrawerItemHolder {
            LinearLayout layoutTitle, layoutItem, layoutSpacing;
            RelativeLayout layoutDay;
            TextView txtTitle, txtDayCounter, txtDayText, txtItemContent;
            ImageView imgIcon;
        }
    }



}
