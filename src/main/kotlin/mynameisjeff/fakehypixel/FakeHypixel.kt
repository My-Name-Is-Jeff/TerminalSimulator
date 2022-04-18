package mynameisjeff.fakehypixel

import dev.cobblesword.nachospigot.protocol.PacketListener
import io.netty.buffer.Unpooled
import mynameisjeff.fakehypixel.commands.LocrawCommand
import net.minecraft.server.v1_8_R3.Packet
import net.minecraft.server.v1_8_R3.PacketDataSerializer
import net.minecraft.server.v1_8_R3.PacketPlayOutCustomPayload
import net.minecraft.server.v1_8_R3.PlayerConnection
import org.bukkit.Bukkit
import org.bukkit.command.PluginCommand
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
import org.bukkit.entity.Player
import org.bukkit.scoreboard.DisplaySlot

object FakeHypixel : PacketListener {

    val getOnlinePlayers: () -> Collection<Player> = Bukkit::getOnlinePlayers
    val fakePacket = PacketPlayOutCustomPayload("MC|Brand", PacketDataSerializer(Unpooled.buffer()).a("Hypixel BungeeCoord (1.0.0)"))

    init {
        (Bukkit.getCommandMap().getCommand("locraw") as PluginCommand).executor = LocrawCommand
    }

    fun setScoreboard() {
        val scoreboard = Bukkit.getScoreboardManager().mainScoreboard
        val obj = scoreboard.getObjective(DisplaySlot.SIDEBAR) ?: scoreboard.registerNewObjective("SKYBLOCK", "dummy").apply { displaySlot = DisplaySlot.SIDEBAR }
        obj.getScore("Cleared: 100%").apply {
            score = 1
        }
        obj.getScore("The Catacombs (F7)").apply {
            score = 2
        }

        getOnlinePlayers().forEach {
            it as CraftPlayer
            it.handle.playerConnection.sendPacket(PacketPlayOutCustomPayload("MC|Brand", PacketDataSerializer(Unpooled.buffer()).a("Hypixel BungeeCoord (1.0.0)")))
        }
    }

    override fun onSentPacket(connection: PlayerConnection, packet: Packet<*>): Boolean {
        if (packet is PacketPlayOutCustomPayload && packet.channel == "MC|Brand") {
            packet.payload = fakePacket.payload
        }
        return true
    }

    private val channelAccessor = PacketPlayOutCustomPayload::class.java.getDeclaredField("a").apply { isAccessible = true }
    private val payloadAccessor = PacketPlayOutCustomPayload::class.java.getDeclaredField("b").apply { isAccessible = true }

    var PacketPlayOutCustomPayload.channel
        get() = channelAccessor.get(this) as String
        set(value) = channelAccessor.set(this, value)

    var PacketPlayOutCustomPayload.payload
        get() = payloadAccessor.get(this) as PacketDataSerializer
        set(value) = payloadAccessor.set(this, value)
}