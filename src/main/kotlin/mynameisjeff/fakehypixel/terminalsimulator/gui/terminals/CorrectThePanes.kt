package mynameisjeff.fakehypixel.terminalsimulator.gui.terminals

import mynameisjeff.fakehypixel.terminalsimulator.utils.grayGlass
import mynameisjeff.fakehypixel.terminalsimulator.utils.offGlass
import mynameisjeff.fakehypixel.terminalsimulator.utils.onGlass
import org.bukkit.Bukkit
import org.bukkit.inventory.Inventory
import kotlin.random.Random

class CorrectThePanes : AbstractTerminal() {

    override val screen: Inventory = Bukkit.createInventory(this, 45, "Correct all the panes!")

    init {
        val glass = (1..15).mapTo(ArrayDeque()) {
            if (Random.nextInt(0, 3) == 0) onGlass else offGlass
        }
        glass.shuffle()

        for (i in 0 until screen.size) {
            if (i < 9 || i > 35 || i % 9 <= 1 || i % 9 >= 7) screen.setItem(i, grayGlass)
            else {
                val item = glass.removeFirst()
                screen.setItem(i, item)
            }
        }
    }
}