package com.ryvk.dependancyinjection.manualinjection

class Main {
    fun startProcess(){
        val engine1 = Engine1("V6", 500, 400)
        val hilux = HiLux(engine1)
        hilux.startEngine()
        val engine2 = Engine2("V8", 600, 500)
        val landCruiser = LandCruiser(engine2)
        landCruiser.startEngine()
    }
}