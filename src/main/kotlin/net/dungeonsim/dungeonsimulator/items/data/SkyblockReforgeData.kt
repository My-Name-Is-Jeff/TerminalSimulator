package net.dungeonsim.dungeonsimulator.items.data

import kotlinx.serialization.Serializable

@Serializable
data class SkyblockReforgeData(
    var strength: Long = 0,
    var trueDefense: Long = 0,
    var health: Long = 0,
    var intelligence: Long = 0,
    var defense: Long = 0,
    var critChance: Long = 0,
    var critDamage: Long = 0,
    var attackSpeed: Long = 0,
    var speed: Long = 0,
    var seaCreatureChance: Long = 0,
    var magicFind: Long = 0,
    var petLuck: Long = 0,
    var abilityDamage: Long = 0,
    var ferocity: Long = 0,
)