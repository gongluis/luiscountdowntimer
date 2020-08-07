package com.cardinfolink.luiscountdowntimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LuisCountDownTimer luisCountDownTimer = findViewById(R.id.ct_count);
        luisCountDownTimer.start(5 * 60 * 1000, new LuisCountDownTimer.OnFinishCallBack() {
            @Override
            public void onCountDownFinish() {
                Toast.makeText(MainActivity.this, "我执行结束了", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
