package mynameisjeff.fakehypixel.terminalsimulator.gui.terminals

import mynameisjeff.fakehypixel.terminalsimulator.utils.grayGlass
import org.bukkit.Bukkit
import org.bukkit.DyeColor
import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack


class SelectAll : AbstractTerminal() {

    companion object {
        val pool = arrayOf(Material.WOOL, Material.STAINED_GLASS, Material.STAINED_GLASS_PANE, Material.STAINED_CLAY, Material.INK_SACK)
    }

    val color = DyeColor.values().random()
    override val screen: Inventory = Bukkit.createInventory(this, 54, "Select all the ${color.name.replace("_", " ")} items!")
    val allValid = hashMapOf<Int, Boolean>()

    init {
        val items = ArrayDeque<ItemStack>()
        val wrongColors = DyeColor.values().filter { it != color }
        val solutions = (0..((4..7).random())).mapTo(items) {
            val material = pool.random()
            val c = (if (material == Material.INK_SACK) color.dyeData else color.data).toShort()
            ItemStack(material, 1, c).apply {
                itemMeta = itemMeta.apply {
                    displayName = color.name.replace("_", " ")
                    addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES)
                }
            }
        }
        val junk = (0 until 28 - solutions.size).mapTo(items) {
            val material = pool.random()
            val wrongColor = wrongColors.random()
            val c = (if (material == Material.INK_SACK) wrongColor.dyeData else wrongColor.data).toShort()
            ItemStack(material, 1, c).apply {
                itemMeta = itemMeta.apply {
                    displayName = wrongColor.name.replace("_", " ")
                    addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES)
                }
            }
        }

        items.shuffle()

        for (i in 0 until screen.size) {
            if (i < 9 || i > 44 || i % 9 == 0 || i % 9 == 8) screen.setItem(i, grayGlass)
            else {
                val item = items.removeFirst()
                screen.setItem(i, item)
                if (item.type != Material.INK_SACK && item.durability == color.data.toShort()) allValid[i] = false
                else if (item.durability == color.dyeData.toShort()) allValid[i] = false
            }
        }
    }
}