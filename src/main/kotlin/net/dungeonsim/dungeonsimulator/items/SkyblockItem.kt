package net.dungeonsim.dungeonsimulator.items

import kotlinx.serialization.Serializable
import net.dungeonsim.dungeonsimulator.items.data.SkyblockItemData
import net.dungeonsim.dungeonsimulator.items.data.SkyblockRarity
import org.bukkit.Material
import org.bukkit.inventory.ItemStack


@Serializable
abstract class SkyblockItem(){
    abstract val name: String
    abstract val rarity: SkyblockRarity
    abstract val material: Material
    open var reforgeable: Boolean = true
    val data = SkyblockItemData();
    abstract fun createItem(): ItemStack
    //efg
}