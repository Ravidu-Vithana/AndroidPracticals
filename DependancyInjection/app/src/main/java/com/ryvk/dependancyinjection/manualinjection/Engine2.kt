package com.ryvk.dependancyinjection.manualinjection

class Engine2(
    override val name: String,
    override val horsepower: Int,
    override val torque: Int
) : Engine {
}