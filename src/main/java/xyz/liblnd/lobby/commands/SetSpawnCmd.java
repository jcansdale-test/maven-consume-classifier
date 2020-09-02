package xyz.liblnd.lobby.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import xyz.liblnd.lobby.Lobby;
import xyz.liblnd.prismarine.libs.acf.BaseCommand;
import xyz.liblnd.prismarine.libs.acf.annotation.CommandAlias;
import xyz.liblnd.prismarine.libs.acf.annotation.CommandPermission;
import xyz.liblnd.prismarine.libs.acf.annotation.Default;
import xyz.liblnd.prismarine.libs.acf.annotation.Dependency;

@CommandAlias("setspawn")
@CommandPermission("lobby.command.setspawn")
public class SetSpawnCmd extends BaseCommand
{
    @Dependency
    private Lobby plugin;

    @Default
    public void set(Player player)
    {
        plugin.getConf().setSpawnLocation(player.getLocation());
        player.sendMessage(ChatColor.GREEN + "Your location has been set as the new spawn point");
    }
}
