package com.example.xchange;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MenuView extends LinearLayout {


    public MenuView(Context context) {
        super(context);
        init();

    }

    public MenuView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MenuView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.menu_view, this);

        ImageView homePic = (ImageView) findViewById(R.id.homepic);
        ImageView historyPic = (ImageView) findViewById(R.id.historypic);
        ImageView exchangePic = (ImageView) findViewById(R.id.exchangepic);
        ImageView notificationsPic = (ImageView) findViewById(R.id.notificationspic);
        ImageView profilePic = (ImageView) findViewById(R.id.profilepic);

        homePic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Homepic is clicked", Toast.LENGTH_SHORT).show();
            }
        });

        historyPic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "HistoryPic is clicked", Toast.LENGTH_SHORT).show();
            }
        });

        exchangePic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Exchange is clicked", Toast.LENGTH_SHORT).show();
            }
        });

        notificationsPic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "NotificationsPic is clicked", Toast.LENGTH_SHORT).show();
            }
        });

        profilePic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "ProfilePic is clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
