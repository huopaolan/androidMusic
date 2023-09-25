package com.artillery.musicmain.ui.music;

import android.app.Application;

import androidx.annotation.NonNull;

import com.artillery.musicbase.base.BaseViewModel;
import com.artillery.musicbase.binding.command.BindingCommand;
import com.artillery.musicmain.data.MusicRepository;

/**
 * @author ArtilleryOrchid
 */
public class MusicMainViewModel extends BaseViewModel<MusicRepository> {
    public BindingCommand pre;
    public BindingCommand play;
    public BindingCommand next;
    public MusicRepository mMusicRepository;

    public MusicMainViewModel(@NonNull Application application, MusicRepository model) {
        super(application, model);
        mMusicRepository = model;
        mMusicRepository.bindMusicService();
    }
    @Override
    public void onDestroy() {
        mMusicRepository.unBindMusicService();
        MusicRepository.destroyInstance();
        onCleared();
    }
}
