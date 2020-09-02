package xyz.liblnd.lobby.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import xyz.liblnd.lobby.Lobby;
import xyz.liblnd.lobby.config.Config;

public class SpawnListener implements Listener
{
    private final Config config;

    public SpawnListener(Lobby plugin)
    {
        this.config = plugin.getConf();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        if(config.getSpawnLocation() == null)
            return;

        Player player = event.getPlayer();

        player.teleport(config.getSpawnLocation());
        event.setJoinMessage(config.getJoinMessage().replace("%player%", player.getDisplayName()));

        // Set the selected slot to the middle
        player.getInventory().setHeldItemSlot(4);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event)
    {
        Player player = event.getPlayer();
        event.setQuitMessage(config.getLeaveMessage().replace("%player%", player.getDisplayName()));
    }
}
