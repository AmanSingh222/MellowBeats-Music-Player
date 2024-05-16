package com.example.mellowbeats.CustomClass;



import static com.example.mellowbeats.CustomClass.Music.SetPotionsOfSong;
import static com.example.mellowbeats.CustomClass.Music.favouriteChecker;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mellowbeats.ActivityUI.PlaySongActivity;
import com.example.mellowbeats.R;
import com.example.mellowbeats.UI.NowPlayingFragment;

public class NotificationReceiver  extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null && intent.getAction() != null) {
            switch (intent.getAction()) {
                case ApplicationClass.PREVIOUS:
                   prevNextSong(false,context);
                    break;
                case ApplicationClass.PLAY:
                  if(PlaySongActivity.isPlaying){
                      pauseMusic();
                  }
                  else {
                      playMusic();
                  }
                    break;
                case ApplicationClass.NEXT:
                    prevNextSong(true,context);
                    break;
                case ApplicationClass.EXIT:

                    PlaySongActivity.musicService.stopForeground(true);
                    PlaySongActivity.musicService =null;
//                    assert false;
                    PlaySongActivity.musicService.mediaPlayer.release();
                    System.exit(10);
                    break;
            }
        }
    }

    private void playMusic() {
        PlaySongActivity.isPlaying = true;
        PlaySongActivity.musicService.mediaPlayer.start();
        PlaySongActivity.musicService.showNotification(R.drawable.baseline_pause_circle_24,1F);
        PlaySongActivity.binding.PlayPauseBtn.setImageResource(R.drawable.baseline_pause_circle_24);


        }

        private void pauseMusic() {
            PlaySongActivity.isPlaying = false;
            PlaySongActivity.musicService.mediaPlayer.pause();
            PlaySongActivity.musicService.showNotification(R.drawable.baseline_play_circle_24,0F);
            PlaySongActivity.binding.PlayPauseBtn.setImageResource(R.drawable.baseline_play_circle_24);


    }

    @SuppressLint("CheckResult")
    private void prevNextSong(boolean increment, Context context) {
        SetPotionsOfSong(increment);

        MusicService.createMediaPlayer();
        Glide.with(context)
                .load(PlaySongActivity.musicListPA.get(PlaySongActivity.songPosition).getArtUri())
                .apply(new RequestOptions().placeholder(R.drawable.icon_music).centerCrop())
                .into(PlaySongActivity.binding.SongImg);
        PlaySongActivity.binding.songNameMV.setText(PlaySongActivity.musicListPA.get(PlaySongActivity.songPosition).getTitle());
        Glide.with(context)
                .load(PlaySongActivity.musicListPA.get(PlaySongActivity.songPosition).getArtUri())
                .into(NowPlayingFragment.binding.SongImgNP);
          NowPlayingFragment.binding.SongNameNP.setText(PlaySongActivity.musicListPA.get(PlaySongActivity.songPosition).getTitle());

          PlaySongActivity.fIndex= favouriteChecker(PlaySongActivity.musicListPA.get(PlaySongActivity.songPosition).getId());
          if(PlaySongActivity.isFavourite){
             PlaySongActivity.binding.FavouriteBtn.setImageResource(R.drawable.favorite);
          }
          else {
              PlaySongActivity.binding.FavouriteBtn.setImageResource(R.drawable.white_favorite);
          }


        playMusic();
    }



}
