package com.example.mysangeet;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PlaySong extends AppCompatActivity {
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaplayer.stop();
        mediaplayer.release();
    }

    TextView songName;
    ImageView previous,play,next;
    String contextName;
    int position;
    SeekBar durationBar;
    ArrayList<File>songs;
    MediaPlayer mediaplayer;
    Thread updateSeek;
    Uri uri;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);
        songName=findViewById(R.id.songName);
        previous=findViewById(R.id.previous);
        play=findViewById(R.id.play);
        next=findViewById(R.id.next);
        durationBar=findViewById(R.id.durationBar);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        songs=(ArrayList)bundle.getParcelableArrayList("songList");
        contextName=bundle.getString("currentSong");
        songName.setText(contextName);
        songName.setSelected(true);
        position=intent.getIntExtra("position",0);
        uri= Uri.parse(songs.get(position).toString());
        mediaplayer=MediaPlayer.create(PlaySong.this,uri);
        mediaplayer.start();
        durationBar.setMax(mediaplayer.getDuration());

        durationBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int x = (int) Math.ceil(i/1000f);

                if (x==0 && mediaplayer != null && !mediaplayer.isPlaying()) {
                    play.setImageDrawable(PlaySong.this.getDrawable(R.drawable.play));
                    durationBar.setProgress(0);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (mediaplayer != null && mediaplayer.isPlaying()) {
                    mediaplayer.seekTo(seekBar.getProgress());
                }
            }
        });
        updateSeek =new Thread()
        {
            @Override
            public void run() {
                int currentPosition=0;
                try {
                    while(mediaplayer!=null && currentPosition<mediaplayer.getDuration())
                    {
                        currentPosition=mediaplayer.getCurrentPosition();
                        durationBar.setProgress(currentPosition);
                        sleep(1000);
                    }
                    if(mediaplayer != null && currentPosition>=mediaplayer.getDuration())
                    {
                        currentPosition=0;
                        durationBar.setProgress(currentPosition);
                        play.setImageResource(R.drawable.play);
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        };
        updateSeek.start();
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaplayer.isPlaying())
                {
                    play.setImageResource(R.drawable.play);
                    mediaplayer.pause();
                }
                else{
                    play.setImageResource(R.drawable.pause);
                    mediaplayer.start();
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaplayer.stop();
                mediaplayer.release();
                if(position!=0)
                {
                    position-=1;
                }
                else{
                    position=songs.size()-1;
                }
                songName.setText(songs.get(position).getName().replace(".mp3",""));
                uri= Uri.parse(songs.get(position).toString());
                mediaplayer=MediaPlayer.create(PlaySong.this,uri);
                mediaplayer.start();
                play.setImageResource(R.drawable.pause);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaplayer.stop();
                mediaplayer.release();
                if(position!=songs.size()-1)
                {
                    position+=1;
                }
                else{
                    position=0;
                }
                songName.setText(songs.get(position).getName().replace(".mp3",""));
                uri= Uri.parse(songs.get(position).toString());
                mediaplayer=MediaPlayer.create(PlaySong.this,uri);
                mediaplayer.start();
                play.setImageResource(R.drawable.pause);
            }
        });
    }
}