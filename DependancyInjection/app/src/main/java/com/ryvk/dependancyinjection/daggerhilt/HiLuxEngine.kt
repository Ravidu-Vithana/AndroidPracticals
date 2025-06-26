package com.ryvk.dependancyinjection.daggerhilt

import javax.inject.Inject

class HiLuxEngine @Inject constructor(
    override val name: String,
    override val horsepower: Int,
    override val torque: Int
) : Engine {
}