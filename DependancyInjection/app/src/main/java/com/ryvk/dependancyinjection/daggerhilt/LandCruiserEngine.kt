package com.ryvk.dependancyinjection.daggerhilt

import javax.inject.Inject

class LandCruiserEngine @Inject constructor(
    override val name: String,
    override val horsepower: Int,
    override val torque: Int
) : Engine {
}