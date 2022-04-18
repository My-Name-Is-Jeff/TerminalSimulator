package mynameisjeff.fakehypixel.terminalsimulator.commands

import mynameisjeff.fakehypixel.terminalsimulator.gui.TerminalSelector
import mynameisjeff.fakehypixel.terminalsimulator.gui.terminals.*
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object TerminalCommand : CommandExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        sender as Player
        when (args.getOrNull(0)) {
            "startswith" -> sender.openInventory(StartsWith().inventory)
            "selectall" -> sender.openInventory(SelectAll().inventory)
            "clickinorder" -> sender.openInventory(ClickInOrder().inventory)
            "correctallpanes", "correctallthepanes" -> sender.openInventory(CorrectThePanes().inventory)
            "navigatethemaze" -> sender.openInventory(NavigateTheMaze().inventory)
            else -> sender.openInventory(TerminalSelector().inventory)
        }
        return true
    }
}