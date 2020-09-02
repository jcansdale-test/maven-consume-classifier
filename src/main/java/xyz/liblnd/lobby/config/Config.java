package xyz.liblnd.lobby.config;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitScheduler;
import xyz.liblnd.lobby.Lobby;
import xyz.liblnd.prismarine.libs.acf.ACFBukkitUtil;

import static java.lang.Double.parseDouble;
import static java.lang.Float.parseFloat;

public class Config
{
    private final Lobby plugin;

    private final FileConfiguration config;

    public Config(Lobby plugin)
    {
        plugin.saveDefaultConfig();
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }

    public String getJoinMessage()
    {
        return color(config.getString("Messages.Join", ""));
    }

    public String getLeaveMessage()
    {
        return color(config.getString("Messages.Leave", ""));
    }

    public Location getSpawnLocation()
    {
        String locString = config.getString("OnSpawnJoinLocation", null);

        if(locString == null)
            return null;

        String[] parts = locString.split(";");

        return new Location(Bukkit.getWorld(parts[0]), // world
                parseDouble(parts[1]), // x
                parseDouble(parts[2]), // y
                parseDouble(parts[3]), // z
                parseFloat(parts[4]), // yaw
                parseFloat(parts[5])); // pitch
    }

    public void setSpawnLocation(Location location)
    {
        String locString = location.getWorld().getName() + ";" + location.getX() + ";" + location.getY() +
                ";" + location.getZ() + ";" + location.getYaw() + ";" + location.getPitch();

        config.set("OnSpawnJoinLocation", locString);
        save();
    }

    void save()
    {
        BukkitScheduler scheduler = plugin.getServer().getScheduler();
        scheduler.runTaskAsynchronously(plugin, plugin::saveConfig);
    }

    private String color(String msg)
    {
        return ACFBukkitUtil.color(msg);
    }
}
