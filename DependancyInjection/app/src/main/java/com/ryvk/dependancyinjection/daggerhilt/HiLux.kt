package com.ryvk.dependancyinjection.daggerhilt

import javax.inject.Inject
import javax.inject.Named

class HiLux @Inject constructor(@Named("HiLux") val engine: Engine) : Vehicle(engine) {
    override fun startEngine(): String {
        return "Vroom Vroom"
    }
}