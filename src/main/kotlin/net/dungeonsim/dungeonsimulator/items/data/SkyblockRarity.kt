package net.dungeonsim.dungeonsimulator.items.data

import kotlinx.serialization.Serializable

@Serializable
enum class SkyblockRarity(private val value: Int) {
    COMMON(0),
    UNCOMMON(1),
    RARE(2),
    Epic(3),
    LEGENDARY(4),
    MYTHIC(5);
}