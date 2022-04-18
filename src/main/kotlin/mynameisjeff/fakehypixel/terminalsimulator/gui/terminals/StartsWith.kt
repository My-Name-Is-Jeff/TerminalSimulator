package mynameisjeff.fakehypixel.terminalsimulator.gui.terminals

import mynameisjeff.fakehypixel.terminalsimulator.utils.grayGlass
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import kotlin.collections.ArrayDeque


class StartsWith : AbstractTerminal() {

    companion object {
        val charSet = ('A'..'W').filter { it != 'Q' && it != 'K' && it != 'T' && it != 'U'}
        val pool by lazy {
            val inv = Bukkit.createInventory(null, 9)
            Material.values().filter {
                inv.setItem(0, ItemStack(it))
                return@filter inv.getItem(0) != null
            }
        }
    }

    val char: Char = charSet.random()
    override val screen: Inventory = Bukkit.createInventory(this, 54, "What starts with: '$char'?")
    val allValid = hashMapOf<Int, Boolean>()

    init {

        val validPool = pool.filter { it.name.startsWith(char) }
        val junkPool = pool.filter { !validPool.contains(it) }
        val items = ArrayDeque<Material>()
        val solutions = (0..((3..11).random())).mapTo(items) {
            validPool.random()
        }
        val junk = (0 until 28 - solutions.size).mapTo(items) {
            junkPool.random()
        }

        items.shuffle()

        for (i in 0 until screen.size) {
            if (i < 9 || i > 44 || i % 9 == 0 || i % 9 == 8) screen.setItem(i, grayGlass)
            else {
                val item = items.removeFirst()
                screen.setItem(i, ItemStack(item).apply {
                    itemMeta = itemMeta.apply {
                    addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES)
                    displayName = item.name.replaceFirstChar { it.titlecase() }
                }})
                if (item.name.startsWith(char)) allValid[i] = false
            }
        }
    }
}