package com.hamsoace.AppForAll;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.TextView;

public class DropDownItemClickListener implements android.widget.AdapterView.OnItemClickListener {

    String TAG = "DropDownItemClickListener";

    //Click listener for the dropdown item
    @Override
    public void onItemClick(AdapterView<?> arg0, View v, int arg2, long arg3) {
        Context mContext = v.getContext();
        EnterMarks enterMarks = ((EnterMarks) mContext);

        Animation fadeInAnimation = AnimationUtils.loadAnimation(v.getContext(),android.R.anim.fade_in);
        fadeInAnimation.setDuration(5);
        v.startAnimation(fadeInAnimation);

        enterMarks.popupWindow.dismiss();

        String selectedItemText = ((TextView) v).getTag().toString();
        enterMarks.CO.setText(selectedItemText);
    }
}
