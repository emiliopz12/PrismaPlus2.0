package com.prismaplus.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.prismaplus.DrawerActivity;
import com.prismaplus.R;
import com.prismaplus.herlpers.PreferencesManager;

public class SplashActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    PreferencesManager preferencesManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        preferencesManager = PreferencesManager.getInstance();
        new Handler().postDelayed(() -> {
            String token = preferencesManager.getStringValue(SplashActivity.this,"rememberUser");
            Intent mainIntent;
            if(token.equals("")){
                mainIntent = new Intent(SplashActivity.this,LoginActivity.class);
            }else{
                mainIntent = new Intent(SplashActivity.this, DrawerActivity.class);
            }
            startActivity(mainIntent);
            finish();
        }, SPLASH_DISPLAY_LENGTH);
    }
}
