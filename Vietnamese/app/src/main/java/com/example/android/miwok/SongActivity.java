package com.example.android.miwok;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tali on 23.11.17.
 */

public class SongActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private MediaPlayer.OnCompletionListener onCompletionListener=new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            realeaseResource();
        }
    };
    private AudioManager audioManager;
    private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener=new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int i) {
            switch(i){
                case AudioManager.AUDIOFOCUS_LOSS:
                    mediaPlayer.stop();
                    realeaseResource();
                    break;
                case AudioManager.AUDIOFOCUS_GAIN:
                    mediaPlayer.start();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    mediaPlayer.pause();
                    mediaPlayer.seekTo(0);
            }
        }
    };
    private void realeaseResource(){
        if(mediaPlayer !=null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer=null;
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final List<Word> listItem = new ArrayList<>(Arrays.asList(
                new Word("Cuộc sống hoa hồng","La vie en rose",R.raw.la_vie_en_rose),
                new Word("Chúc ngủ ngon","Good night",R.raw.chuc_be_ngu_ngon),
                new Word("Chúc mừng sinh nhật","Happy birthday",R.raw.chuc_mung_sinh_nhat),
                new Word("Một con vịt","A duck",R.raw.mot_con_vit),
                new Word("Con heo đất","A saving pig",R.raw.con_heo_dat),
                new Word("Tập đếm","Learn to count",R.raw.tap_dem),
                new Word("Cả nhà thương nhau","A family",R.raw.ca_nha_thuong_nhau),
                new Word("Ba ngọn nến","3 candles",R.raw.ba_ngon_nen),
                new Word("Chúc mừng năm mới","Happy new year",R.raw.chuc_mung_nam_moi),
                new Word("Cháu lên ba","When I am 3",R.raw.chau_len_ba),
                new Word("Em gái mưa","A duck",R.raw.em_gai_mua)
        ));
        WordAdapter itemAdapter=new WordAdapter(this,listItem,R.color.category_song,false,true);
        ListView listView= (ListView) findViewById(R.id.list_songs);
        listView.setAdapter(itemAdapter);

        audioManager= (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                realeaseResource();
                Word word=listItem.get(i);
                ImageView image= (ImageView) view.findViewById(R.id.button);
                int id= (Integer) image.getTag();
                if(id==R.drawable.ic_play_circle_outline_black_24dp){
                    image.setTag(R.drawable.ic_pause_black_24dp);
                    image.setImageResource(R.drawable.ic_pause_black_24dp);
                    int result=audioManager.requestAudioFocus(onAudioFocusChangeListener,
                            AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                    if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                        mediaPlayer=MediaPlayer.create(SongActivity.this,word.getAudioNumber());
                        mediaPlayer.start();
                        mediaPlayer.setOnCompletionListener(onCompletionListener);
                    }
                }else{
                    image.setImageResource(R.drawable.ic_play_circle_outline_black_24dp);
                    image.setTag(R.drawable.ic_play_circle_outline_black_24dp);
                    realeaseResource();
                }


            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        realeaseResource();
    }
}
