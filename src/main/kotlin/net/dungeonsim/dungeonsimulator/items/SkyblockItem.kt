package net.dungeonsim.dungeonsimulator.items

import kotlinx.serialization.Serializable
import net.dungeonsim.dungeonsimulator.items.data.SkyblockRarity


@Serializable
abstract class SkyblockItem(){
    abstract val name: String
    abstract val rarity: SkyblockRarity
    open var reforgeable: Boolean = true

}