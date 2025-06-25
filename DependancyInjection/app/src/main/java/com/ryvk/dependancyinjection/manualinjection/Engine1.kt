package com.ryvk.dependancyinjection.manualinjection

class Engine1(
    override val name: String,
    override val horsepower: Int,
    override val torque: Int
) : Engine {
}