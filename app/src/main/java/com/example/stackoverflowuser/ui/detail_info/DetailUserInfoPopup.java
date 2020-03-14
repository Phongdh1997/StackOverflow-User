package com.example.stackoverflowuser.ui.detail_info;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.cardview.widget.CardView;

import com.example.stackoverflowuser.R;

public class DetailUserInfoPopup {
    private View popupView;
    private PopupWindow popupWindow;

    public DetailUserInfoPopup(Context context) {
        popupView = LayoutInflater.from(context).inflate(R.layout.detail_user_info_layout, null, false);
        popupWindow = new PopupWindow(popupView,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                true);
        popupWindow.setElevation(2.3f);

        // init view
        CardView cardView = popupView.findViewById(R.id.card);

        // add action
        cardView.setOnTouchListener((v, event) -> true);    // disable card view touch

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }

    public void showPopup() {
        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
    }
}
