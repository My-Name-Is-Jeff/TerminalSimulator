package mynameisjeff.fakehypixel.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

object LocrawCommand : CommandExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        sender.sendMessage("{\"server\":\"yourMom\",\"gametype\":\"SKYBLOCK\",\"mode\":\"dungeon\",\"map\":\"Dungeon\"}")
        return true
    }
}