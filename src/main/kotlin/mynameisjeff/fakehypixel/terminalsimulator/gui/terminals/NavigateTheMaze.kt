package mynameisjeff.fakehypixel.terminalsimulator.gui.terminals

import mynameisjeff.fakehypixel.terminalsimulator.gui.terminals.NavigateTheMaze.Space.*
import mynameisjeff.fakehypixel.terminalsimulator.utils.grayGlass
import mynameisjeff.fakehypixel.terminalsimulator.utils.greenGlass
import mynameisjeff.fakehypixel.terminalsimulator.utils.redGlass
import mynameisjeff.fakehypixel.terminalsimulator.utils.whiteGlass
import org.bukkit.Bukkit
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import kotlin.random.Random

class NavigateTheMaze : AbstractTerminal() {

    companion object {
        val routes: Array<Array<Space>> = arrayOf(
            arrayOf(
                EMPTY, EMPTY, EMPTY, START, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, PATH, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, PATH, PATH, PATH, PATH, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, PATH, EMPTY, EMPTY,
                EMPTY, PATH, PATH, PATH, PATH, PATH, PATH, EMPTY, EMPTY,
                EMPTY, END, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY
            ),
            arrayOf(
                EMPTY, END, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, START, EMPTY,
                EMPTY, PATH, EMPTY, PATH, PATH, PATH, EMPTY, PATH, EMPTY,
                EMPTY, PATH, EMPTY, PATH, EMPTY, PATH, EMPTY, PATH, EMPTY,
                EMPTY, PATH, EMPTY, PATH, EMPTY, PATH, EMPTY, PATH, EMPTY,
                EMPTY, PATH, PATH, PATH, EMPTY, PATH, PATH, PATH, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
            ),
            arrayOf(
                EMPTY, EMPTY, END, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, PATH, PATH, EMPTY, EMPTY, PATH, PATH, PATH, EMPTY,
                EMPTY, PATH, EMPTY, EMPTY, PATH, PATH, EMPTY, PATH, EMPTY,
                EMPTY, PATH, EMPTY, PATH, PATH, EMPTY, EMPTY, PATH, EMPTY,
                EMPTY, PATH, PATH, PATH, EMPTY, EMPTY, PATH, PATH, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, START, EMPTY, EMPTY,
            ),
            arrayOf(
                EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                END, PATH, PATH, PATH, PATH, PATH, PATH, PATH, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, PATH, EMPTY,
                START, PATH, EMPTY, PATH, PATH, PATH, EMPTY, PATH, EMPTY,
                EMPTY, PATH, PATH, PATH, EMPTY, PATH, PATH, PATH, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
            ),
            arrayOf(
                EMPTY, EMPTY, EMPTY, EMPTY, START, PATH, PATH, PATH, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, PATH, EMPTY,
                EMPTY, PATH, PATH, PATH, PATH, PATH, PATH, PATH, EMPTY,
                EMPTY, PATH, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, PATH, PATH, PATH, PATH, EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY, END, EMPTY, EMPTY, EMPTY, EMPTY,
            ),
            arrayOf(
                EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                END, PATH, PATH, PATH, PATH, PATH, PATH, PATH, PATH,
                EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, PATH,
                EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, PATH,
                START, PATH, PATH, PATH, PATH, PATH, PATH, PATH, PATH,
                EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
            )
        )
    }

    override val screen: Inventory = Bukkit.createInventory(this, 54, "Navigate the maze!")
    val path = Random.nextInt(0, routes.size)

    init {
        val glass = (1..21).mapTo(ArrayDeque()) {
            if (Random.nextInt(0, 3) == 0) greenGlass else redGlass
        }
        glass.shuffle()

        for (i in 0 until screen.size) {
            screen.setItem(i, routes[path][i].item)
        }
    }

    enum class Space(val item: ItemStack) {
        EMPTY(grayGlass),
        PATH(whiteGlass),
        START(greenGlass),
        END(redGlass)
    }
}