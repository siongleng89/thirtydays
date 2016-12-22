package com.challenge.bennho.a30days.controls;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.challenge.bennho.a30days.R;

/**
 * Created by Dell-PC on 23/12/2016.
 */

public class Practice extends RelativeLayout {

    private Context context;

    public Practice(Context context) {
        super(context);
    }

    public Practice(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_practice, this, true);
    }
}
