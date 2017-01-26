package com.challenge.bennho.a30days.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.challenge.bennho.a30days.R;
import com.challenge.bennho.a30days.helpers.UserPhotoHelpers;

import java.io.File;

/**
 * Created by sionglengho on 26/1/17.
 */

public class FragmentPhotoItem extends Fragment {

    private LinearLayout layoutTakePhoto;
    private ImageView imgViewPhoto;
    private PhotoItemListener photoItemListener;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.layout_photo_fragment, container, false);
        Bundle args = getArguments();

        layoutTakePhoto = (LinearLayout) rootView.findViewById(R.id.layoutTakePhoto);
        imgViewPhoto = (ImageView) rootView.findViewById(R.id.imgViewPhoto);

        int dayPlan = args.getInt("dayPlan", 1);

        File file = UserPhotoHelpers.getDayPhotoImageFilePath(getContext(), dayPlan);
        imgViewPhoto.setImageURI(null);
        if(file.exists() && file.length() > 0){
            imgViewPhoto.setImageURI(Uri.fromFile(file));
            layoutTakePhoto.setVisibility(View.GONE);
        }
        else {
            layoutTakePhoto.setVisibility(View.VISIBLE);
        }

        setListeners();

        return rootView;
    }

    private void setListeners(){
        layoutTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(photoItemListener != null) photoItemListener.requestTakePhoto();
            }
        });
    }

    public void setPhotoItemListener(PhotoItemListener photoItemListener) {
        this.photoItemListener = photoItemListener;
    }

    public interface PhotoItemListener{
        void requestTakePhoto();
    }


}
