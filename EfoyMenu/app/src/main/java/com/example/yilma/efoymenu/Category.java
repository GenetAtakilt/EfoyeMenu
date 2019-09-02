package com.example.yilma.efoymenu;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Category extends AppCompatActivity implements View.OnClickListener{
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.in,R.anim.out);
        setContentView(R.layout.activity_category);

        Button pizza_btn = (Button)findViewById(R.id.pizza_btn);
        Button hot_btn = (Button)findViewById(R.id.hot_btn);
        Button shots_btn = (Button)findViewById(R.id.shots_btn);
        Button salad_btn = (Button)findViewById(R.id.salad_btn);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),"fonts/days.ttf");
        pizza_btn.setTypeface(custom_font);
        hot_btn.setTypeface(custom_font);
        shots_btn.setTypeface(custom_font);
        salad_btn.setTypeface(custom_font);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pizza_btn:
                i = new Intent(Category.this, MainActivity.class);
                i.putExtra("key","Pizza");
                startActivity(i);
                break;
            case R.id.hot_btn:
                i = new Intent(Category.this, MainActivity.class);
                i.putExtra("key","Hot Beverages");
                startActivity(i);
                break;
            case R.id.salad_btn:
                i = new Intent(Category.this, MainActivity.class);
                i.putExtra("key","Salads");
                startActivity(i);
                break;
            case R.id.shots_btn:
                i = new Intent(Category.this, MainActivity.class);
                i.putExtra("key","Shots");
                startActivity(i);
                break;
        }
    }
}
