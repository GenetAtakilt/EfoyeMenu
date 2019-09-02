package com.example.yilma.efoymenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash extends AppCompatActivity {
    Animation animFadein;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView slogo = (ImageView)findViewById(R.id.imageView3);
        animFadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        slogo.startAnimation(animFadein);

                Thread logoTimer = new Thread() {
                    public void run() {
                        try {
                            sleep(3200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            Intent i = new Intent(Splash.this, Category.class);
                            startActivity(i);
                        }
                    }
                };
        logoTimer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
