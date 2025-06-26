package com.ryvk.dependancyinjection.daggerhilt.di

import com.ryvk.dependancyinjection.daggerhilt.Engine
import com.ryvk.dependancyinjection.daggerhilt.HiLuxEngine
import com.ryvk.dependancyinjection.daggerhilt.LandCruiserEngine
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Named

@Module
@InstallIn(ActivityComponent::class)
object EngineModule {
    @Provides
    @Named("HiLux")
    fun provideHiLuxEngine(): Engine {
        return HiLuxEngine("V6", 500, 400)
    }

    @Provides
    @Named("LandCruiser")
    fun provideLandCruiserEngine(): Engine {
        return LandCruiserEngine("V8", 500, 400)
    }
}