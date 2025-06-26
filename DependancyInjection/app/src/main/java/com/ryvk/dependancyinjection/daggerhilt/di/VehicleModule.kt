package com.ryvk.dependancyinjection.daggerhilt.di

import com.ryvk.dependancyinjection.daggerhilt.Engine
import com.ryvk.dependancyinjection.daggerhilt.HiLux
import com.ryvk.dependancyinjection.daggerhilt.LandCruiser
import com.ryvk.dependancyinjection.daggerhilt.Vehicle
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Named

@Module
@InstallIn(ActivityComponent::class)
object VehicleModule {
    @Provides
    @Named("HiLux")
    fun provideHiLux(@Named("HiLux") engine: Engine): Vehicle {
        return HiLux(engine)
    }
    @Provides
    @Named("LandCruiser")
    fun provideLandCruiser(@Named("LandCruiser") engine: Engine): Vehicle {
        return LandCruiser(engine)
    }
}