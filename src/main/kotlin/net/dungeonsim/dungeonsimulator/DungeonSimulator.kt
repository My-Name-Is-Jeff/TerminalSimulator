package net.dungeonsim.dungeonsimulator

import dev.cobblesword.nachospigot.Nacho
import mynameisjeff.fakehypixel.FakeHypixel
import mynameisjeff.fakehypixel.terminalsimulator.TermSim
import org.bukkit.plugin.java.JavaPlugin

@Suppress("unused")
class DungeonSimulator : JavaPlugin() {
    override fun onEnable() {
        listOf(
            TermSim
        ).forEach { server.pluginManager.registerEvents(it, this) }
        Nacho.get().registerPacketListener(FakeHypixel)
        FakeHypixel.setScoreboard()
    }
}