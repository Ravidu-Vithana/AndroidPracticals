package com.ryvk.dependancyinjection.manualinjection

class LandCruiser(engine: Engine) : Vehicle(engine) {
    override fun startEngine(): String {
        return "pheeeeewww pheeeeww"
    }
}