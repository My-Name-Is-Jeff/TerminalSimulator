package mynameisjeff.fakehypixel.terminalsimulator

import com.destroystokyo.paper.Title
import io.netty.buffer.Unpooled
import mynameisjeff.fakehypixel.commands.LocrawCommand
import mynameisjeff.fakehypixel.terminalsimulator.commands.TerminalCommand
import mynameisjeff.fakehypixel.terminalsimulator.gui.TerminalSelector
import mynameisjeff.fakehypixel.terminalsimulator.gui.terminals.*
import mynameisjeff.fakehypixel.terminalsimulator.utils.*
import net.minecraft.server.v1_8_R3.PacketDataSerializer
import net.minecraft.server.v1_8_R3.PacketPlayOutCustomPayload
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.PluginCommand
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
import org.bukkit.enchantments.Enchantment
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scoreboard.DisplaySlot

object TermSim : Listener {

    init {
        (Bukkit.getCommandMap().getCommand("terminal") as PluginCommand).executor = TerminalCommand
    }

    @EventHandler
    fun onClick(e: InventoryClickEvent) {
        val holder = e.clickedInventory?.holder ?: return
        val player = e.view.player as CraftPlayer
        if (e.clickedInventory != e.inventory) return
        when (holder) {
            is TerminalSelector -> {
                val command = e.currentItem?.itemMeta?.lore?.firstOrNull() ?: "/terminal "
                if (command.startsWith("/terminal ")) {
                    player.closeInventory()
                    Bukkit.dispatchCommand(player, command.drop(1))
                }
                e.isCancelled = true
            }
            is StartsWith -> {
                if (holder.allValid.contains(e.slot)) {
                    holder.inventory.getItem(e.slot).addUnsafeEnchantment(Enchantment.WATER_WORKER, 1)
                    holder.allValid[e.slot] = true
                    if (holder.allValid.values.all { it }) {
                        player.sendMessage("${ChatColor.AQUA}Starts with took ${System.currentTimeMillis() - holder.startTime}")
                        player.closeInventory()
                        val text = "${player.name} ${ChatColor.GREEN}activated a terminal! (${ChatColor.RED}?${ChatColor.GREEN}/?)"
                        player.sendMessage(text)
                        player.sendTitle(Title("", text))
                    }
                } else player.closeInventory()
                e.isCancelled = true
            }
            is SelectAll -> {
                if (holder.allValid.contains(e.slot)) {
                    holder.inventory.getItem(e.slot).addUnsafeEnchantment(Enchantment.WATER_WORKER, 1)
                    holder.allValid[e.slot] = true
                    if (holder.allValid.values.all { it }) {
                        player.sendMessage("${ChatColor.AQUA}Select all took ${System.currentTimeMillis() - holder.startTime}")
                        player.closeInventory()
                        val text = "${player.name} ${ChatColor.GREEN}activated a terminal! (${ChatColor.RED}?${ChatColor.GREEN}/?)"
                        player.sendMessage(text)
                        player.sendTitle(Title("", text))
                    }
                } else player.closeInventory()
                e.isCancelled = true
            }
            is ClickInOrder -> {
                if (e.currentItem?.amount == holder.index) {
                    e.currentItem = ItemStack(greenGlass).apply { amount = e.currentItem.amount }
                    if (++holder.index > 14) {
                        player.sendMessage("${ChatColor.AQUA}Click in order took ${System.currentTimeMillis() - holder.startTime}")
                        player.closeInventory()
                        val text = "${player.name} ${ChatColor.GREEN}activated a terminal! (${ChatColor.RED}?${ChatColor.GREEN}/?)"
                        player.sendMessage(text)
                        player.sendTitle(Title("", text))
                    }
                }
                e.isCancelled = true
            }
            is CorrectThePanes -> {
                if (offGlass.isSimilar(e.currentItem)) e.currentItem = onGlass
                else if (onGlass.isSimilar(e.currentItem)) e.currentItem = offGlass
                if ((0 until e.inventory.size).none { offGlass.isSimilar(e.inventory.getItem(it)) }) {
                    player.sendMessage("${ChatColor.AQUA}Correct all took ${System.currentTimeMillis() - holder.startTime}")
                    player.closeInventory()
                    val text = "${player.name} ${ChatColor.GREEN}activated a terminal! (${ChatColor.RED}?${ChatColor.GREEN}/?)"
                    player.sendMessage(text)
                    player.sendTitle(Title("", text))
                }
                e.isCancelled = true
            }
            is NavigateTheMaze -> {
                if (whiteGlass.isSimilar(e.currentItem)) {
                    val i = e.slot
                    val inv = e.inventory
                    if ((0..3).any {
                        when (it) {
                            0 -> i % 9 != 8 && i != 53 && inv.getItem(i + 1)?.isSimilar(greenGlass) == true
                            1 -> i % 9 != 0 && i != 0 && inv.getItem(i - 1)?.isSimilar(greenGlass) == true
                            2 -> i <= 44 && inv.getItem(i + 9)?.isSimilar(greenGlass) == true
                            3 -> i >= 9 && inv.getItem(i - 9)?.isSimilar(greenGlass) == true
                            else -> false
                        }
                    }) e.currentItem = greenGlass

                    if ((0 until e.inventory.size).none { whiteGlass.isSimilar(e.inventory.getItem(it)) }) {
                        player.sendMessage("${ChatColor.AQUA}Navigate maze took ${System.currentTimeMillis() - holder.startTime}")
                        player.closeInventory()
                        val text = "${player.name} ${ChatColor.GREEN}activated a terminal! (${ChatColor.RED}?${ChatColor.GREEN}/?)"
                        player.sendMessage(text)
                        player.sendTitle(Title("", text))
                    }

                    e.isCancelled = true
                }
            }
        }
    }

}