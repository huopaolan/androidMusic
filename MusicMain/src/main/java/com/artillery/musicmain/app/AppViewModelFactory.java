package com.artillery.musicmain.app;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.ViewModel;

import com.artillery.musicbase.base.ViewModelFactory;
import com.artillery.musicmain.data.MusicRepository;
import com.artillery.musicmain.viewmodel.MusicMainViewModel;
import com.artillery.musicmain.viewmodel.MusicPlayViewModel;

/**
 * @author ArtilleryOrchid
 */
public class AppViewModelFactory extends ViewModelFactory {
    @SuppressLint("StaticFieldLeak")
    private static volatile AppViewModelFactory INSTANCE;
    private final Application mApplication;
    private final MusicRepository mRepository;

    public static AppViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (AppViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AppViewModelFactory(application, Injection.provideRepository());
                }
            }
        }
        return INSTANCE;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }

    private AppViewModelFactory(Application application, MusicRepository repository) {
        super(application);
        mApplication = application;
        mRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MusicPlayViewModel.class)) {
            return (T) new MusicPlayViewModel(mApplication, mRepository);
        } else if (modelClass.isAssignableFrom(MusicMainViewModel.class)) {
            return (T) new MusicMainViewModel(mApplication, mRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
