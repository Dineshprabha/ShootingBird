package com.dineshprabha.shootingbird;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private boolean isMute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GameActivity.class));
            }
        });

        TextView highScoreText = findViewById(R.id.highScoreTv);

        SharedPreferences prefs = getSharedPreferences("game", MODE_PRIVATE);
        highScoreText.setText("HighScore: " +prefs.getInt("highScore", 0));

        isMute = prefs.getBoolean("isMute", false);

        ImageView volumeCtrl = findViewById(R.id.volumeCtrl);

        if (isMute){
            volumeCtrl.setImageResource(R.drawable.ic_volume_off_24);
        }else {
            volumeCtrl.setImageResource(R.drawable.ic_volume_up_24);
        }

        volumeCtrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isMute = !isMute;
                if (isMute){
                    volumeCtrl.setImageResource(R.drawable.ic_volume_off_24);
                }else {
                    volumeCtrl.setImageResource(R.drawable.ic_volume_up_24);
                }

                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("isMute", isMute);
                editor.apply();
            }
        });
    }
}