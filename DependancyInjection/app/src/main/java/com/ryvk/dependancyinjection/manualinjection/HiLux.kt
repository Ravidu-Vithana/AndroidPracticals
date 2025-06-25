package com.ryvk.dependancyinjection.manualinjection

class HiLux(engine: Engine) : Vehicle(engine) {
    override fun startEngine(): String {
        return "Vroom Vroom"
    }
}