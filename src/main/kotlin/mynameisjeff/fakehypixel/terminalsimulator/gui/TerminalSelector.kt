package mynameisjeff.fakehypixel.terminalsimulator.gui

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack

class TerminalSelector : InventoryHolder {

    private val screen = Bukkit.createInventory(this, InventoryType.HOPPER, "Terminal Selector")
    init {
        screen.setItem(0, ItemStack(Material.NETHER_STAR).apply {
            itemMeta = itemMeta.apply {
                displayName = "Click in Order"
                lore = listOf("/terminal clickinorder")
            }
        })
        screen.setItem(1, ItemStack(Material.NETHER_STAR).apply {
            itemMeta = itemMeta.apply {
                displayName = "Starts With Letter"
                lore = listOf("/terminal startswith")
            }
        })
        screen.setItem(2, ItemStack(Material.NETHER_STAR).apply {
            itemMeta = itemMeta.apply {
                displayName = "Select All Color"
                lore = listOf("/terminal selectall")
            }
        })
        screen.setItem(3, ItemStack(Material.NETHER_STAR).apply {
            itemMeta = itemMeta.apply {
                displayName = "Correct All The Panes"
                lore = listOf("/terminal correctallthepanes")
            }
        })
        screen.setItem(4, ItemStack(Material.NETHER_STAR).apply {
            itemMeta = itemMeta.apply {
                displayName = "Navigate the Maze"
                lore = listOf("/terminal navigatethemaze")
            }
        })
    }

    override fun getInventory(): Inventory = screen
}