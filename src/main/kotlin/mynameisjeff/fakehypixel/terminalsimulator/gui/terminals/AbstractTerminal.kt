package mynameisjeff.fakehypixel.terminalsimulator.gui.terminals

import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder

abstract class AbstractTerminal : InventoryHolder {
    val startTime = System.currentTimeMillis()
    protected abstract val screen: Inventory

    override fun getInventory(): Inventory = screen
}