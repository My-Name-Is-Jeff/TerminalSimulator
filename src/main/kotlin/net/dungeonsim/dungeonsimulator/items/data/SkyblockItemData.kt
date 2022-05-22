package net.dungeonsim.dungeonsimulator.items.data

import kotlinx.serialization.Serializable

@Serializable
data class SkyblockItemData(
    var baseDamage:Long = 0,
    var baseStrength:Long = 0,
    var baseTrueDefense:Long = 0,
    var baseHealth:Long = 0,
    var baseIntel:Long = 0,
    var baseDefense:Long = 0,
    var baseCritChance:Long = 0,
    var baseCritDamage:Long = 0,
    var baseAttackSpeed:Long = 0,
    var baseSpeed:Long = 0,
    var baseSeaCreatureChance:Long = 0,
    var baseMagicFind:Long = 0,
    var basePetLuck:Long = 0,
    var baseAbilityDamage:Long = 0,
    var baseFerocity:Long = 0,
    var reforgeName: String? = null,
    var reforgeStats: SkyblockReforgeData? = null,

)
