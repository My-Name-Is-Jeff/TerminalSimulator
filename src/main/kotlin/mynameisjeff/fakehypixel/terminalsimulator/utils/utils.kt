package mynameisjeff.fakehypixel.terminalsimulator.utils

import org.bukkit.DyeColor
import org.bukkit.Material
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.ChatColor


val grayGlass = createDyedItem(" ", Material.STAINED_GLASS_PANE, DyeColor.GRAY)

val redGlass = createDyedItem(" ", Material.STAINED_GLASS_PANE, DyeColor.RED)

val greenGlass = createDyedItem(" ", Material.STAINED_GLASS_PANE, DyeColor.LIME)

val whiteGlass = createDyedItem(" ", Material.STAINED_GLASS_PANE, DyeColor.WHITE)

val offGlass = createDyedItem("${ChatColor.RED}Off", Material.STAINED_GLASS_PANE, DyeColor.RED)

val onGlass = createDyedItem("${ChatColor.GREEN}On", Material.STAINED_GLASS_PANE, DyeColor.LIME)

fun createDyedItem(name: String, material: Material, color: DyeColor, amount: Int = 1): ItemStack = ItemStack(material, amount, (if (material == Material.INK_SACK) color.dyeData else color.data).toShort()).apply {
    itemMeta = itemMeta.apply {
        addItemFlags(ItemFlag.HIDE_ENCHANTS)
        displayName = name
    }
}
