package com.example.xchange.Activities.Menu;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.xchange.Activities.ExchangeActivity;
import com.example.xchange.Activities.HistoryActivity;
import com.example.xchange.Activities.HomePageActivity;
import com.example.xchange.Activities.NotificationsActivity;
import com.example.xchange.Activities.ProfileActivity;
import com.example.xchange.R;

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
        //ImageView exchangePic = (ImageView) findViewById(R.id.exchangepic);
        ImageView notificationsPic = (ImageView) findViewById(R.id.notificationspic);
        ImageView profilePic = (ImageView) findViewById(R.id.profilepic);

        homePic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), "Homepic is clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent (v.getContext(), HomePageActivity.class);
                v.getContext().startActivity (intent);

            }
        });

        historyPic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), "HistoryPic is clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent (v.getContext(), HistoryActivity.class);
                v.getContext().startActivity (intent);
            }
        });

        /*exchangePic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(v.getContext(), "Exchange is clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent (v.getContext(), ExchangeActivity.class);
                v.getContext().startActivity (intent);
            }
        });*/

        notificationsPic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), "NotificationsPic is clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent (v.getContext(), NotificationsActivity.class);
                v.getContext().startActivity (intent);
            }
        });

        profilePic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(v.getContext(), "ProfilePic is clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent (v.getContext(), ProfileActivity.class);
                v.getContext().startActivity (intent);
            }
        });
    }
}
