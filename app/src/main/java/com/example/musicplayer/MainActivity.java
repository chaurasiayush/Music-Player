package com.example.musicplayer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    ImageView cd ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.dmusic);
        mediaPlayer.setLooping(true);
        cd = findViewById(R.id.cdCover);

    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void playPause(View view){
        Button btn = (Button)view;

        if(mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            btn.setBackgroundResource(R.drawable.play);
            cd.animate().cancel();
        }
        else {
            mediaPlayer.start();
            btn.setBackgroundResource(R.drawable.pauseb);
            cd.animate().setInterpolator(new LinearInterpolator()).setDuration(180000).rotation(10800);
        }
    }

    public void toggle(Button btn, int status){

        if(status == 1)
            btn.animate().scaleX(0).scaleY(0).alpha(.3f).setDuration(500);
        else if(status == 2)
            btn.animate().scaleX(1).scaleY(1).alpha(1).setDuration(500);
    }

    public void seekForward(View view){
        int time = 0;
        time = mediaPlayer.getCurrentPosition();
        mediaPlayer.seekTo(time + 10000);
    }

    public void seekBackward(View view) {
        int time = 0;
        time = mediaPlayer.getCurrentPosition();
        mediaPlayer.seekTo(time - 10000);
    }
}
