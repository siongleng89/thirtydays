package com.challenge.bennho.a30days.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.helpers.AdsMediation;
import com.challenge.bennho.a30days.helpers.UserPhotoHelpers;

import java.io.File;

public class GalleryActivity extends MyActivity {


    private RecyclerView recycleViewItems;
    private GalleryAdapter galleryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        onLayoutSet();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(getString(R.string.avty_gallery_title));

        AdsMediation.showInterstitial(this);

        recycleViewItems = (RecyclerView) findViewById(R.id.recycleViewItems);
        recycleViewItems.setNestedScrollingEnabled(false);
        galleryAdapter = new GalleryAdapter();
        recycleViewItems.setAdapter(galleryAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycleViewItems.setLayoutManager(layoutManager);

    }


    private class GalleryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_gallery_item, parent, false);
            return new GalleryItemViewHolder(GalleryActivity.this, view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((GalleryItemViewHolder) holder).updateDesign(position + 1, position);
        }

        @Override
        public int getItemCount() {
            return 30;
        }

        public class GalleryItemViewHolder extends RecyclerView.ViewHolder{

            private Context context;
            private TextView txtDayNumber;
            private ImageView imgViewPhoto;
            private LinearLayout layoutNoPhoto;
            private RelativeLayout layoutMain;


            public GalleryItemViewHolder(final Context context, View itemView) {
                super(itemView);

                this.context = context;
                txtDayNumber = (TextView) itemView.findViewById(R.id.txtDayNumber);
                imgViewPhoto = (ImageView) itemView.findViewById(R.id.imgViewPhoto);
                layoutNoPhoto = (LinearLayout) itemView.findViewById(R.id.layoutNoPhoto);
                layoutMain = (RelativeLayout) itemView.findViewById(R.id.layoutMain);

            }

            public void updateDesign(final int day, int position){

                txtDayNumber.setText(String.format(context.getString(R.string.avty_photo_title), String.valueOf(day)));

                File photoFile = UserPhotoHelpers.getDayPhotoThumbnailFilePath(context, day);
                imgViewPhoto.setImageURI(null);
                if(photoFile.exists() && photoFile.length() > 0){
                    layoutNoPhoto.setVisibility(View.GONE);
                    imgViewPhoto.setVisibility(View.VISIBLE);
                    imgViewPhoto.setImageURI(Uri.fromFile(photoFile));
                }
                else{
                    layoutNoPhoto.setVisibility(View.VISIBLE);
                    imgViewPhoto.setVisibility(View.GONE);
                }

                layoutMain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, PhotoActivity.class);
                        intent.putExtra("dayPlan", day);
                        context.startActivity(intent);
                    }
                });

            }




        }

    }

}
