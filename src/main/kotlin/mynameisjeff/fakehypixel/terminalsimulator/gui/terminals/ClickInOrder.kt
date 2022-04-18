package mynameisjeff.fakehypixel.terminalsimulator.gui.terminals

import mynameisjeff.fakehypixel.terminalsimulator.utils.grayGlass
import mynameisjeff.fakehypixel.terminalsimulator.utils.redGlass
import org.bukkit.Bukkit
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack


class ClickInOrder : AbstractTerminal() {

    var index = 1
    override val screen: Inventory = Bukkit.createInventory(this, 36, "Click in order!")

    init {
        val glass = (1..14).mapTo(ArrayDeque()) {
            ItemStack(redGlass).apply {
                amount = it
                itemMeta = itemMeta.apply {
                    displayName = "$it"
                }
            }
        }
        glass.shuffle()

        for (i in 0 until screen.size) {
            if (i < 9 || i > 26 || i % 9 == 0 || i % 9 == 8) screen.setItem(i, grayGlass)
            else {
                val item = glass.removeFirst()
                screen.setItem(i, item)
            }
        }
    }
}