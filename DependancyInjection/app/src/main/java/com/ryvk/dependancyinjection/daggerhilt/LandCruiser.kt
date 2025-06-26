package com.ryvk.dependancyinjection.daggerhilt

import javax.inject.Inject
import javax.inject.Named

class LandCruiser @Inject constructor(@Named("LandCruiser") val engine: Engine) : Vehicle(engine) {
    override fun startEngine(): String {
        return "pheeeeewww pheeeeww"
    }
}