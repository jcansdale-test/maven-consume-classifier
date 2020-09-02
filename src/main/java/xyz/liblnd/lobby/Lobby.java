package xyz.liblnd.lobby;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.liblnd.lobby.commands.SetSpawnCmd;
import xyz.liblnd.lobby.config.Config;
import xyz.liblnd.lobby.listeners.SpawnListener;
import xyz.liblnd.prismarine.libs.acf.PaperCommandManager;

public final class Lobby extends JavaPlugin
{
    private Config conf;

    @Override
    public void onEnable()
    {
        this.conf = new Config(this);

        getServer().getPluginManager().registerEvents(new SpawnListener(this), this);

        PaperCommandManager commandManager = new PaperCommandManager(this);
        commandManager.registerCommand(new SetSpawnCmd());
    }

    public Config getConf()
    {
        return conf;
    }
}
