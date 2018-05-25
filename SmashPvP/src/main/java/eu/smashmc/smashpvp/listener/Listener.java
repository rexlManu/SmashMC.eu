/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2018.
 */
package eu.smashmc.smashpvp.listener;

        import org.bukkit.Bukkit;
        import org.bukkit.event.HandlerList;
        import org.bukkit.plugin.PluginManager;
        import org.bukkit.plugin.java.JavaPlugin;

/******************************************************************************************
 *    Urheberrechtshinweis
 *    Copyright © Emmanuel Lampe 2018
 *    Erstellt: 21.05.2018 / 15:31
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,
 *    bei Emmanuel Lampe. Alle Rechte vorbehalten.
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,
 *    öffentlichen Zugänglichmachung oder andere Nutzung
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Emmanuel Lampe.
 ******************************************************************************************/

public class Listener implements IListener {

    private final JavaPlugin javaPlugin;
    private final PluginManager pluginManager;

    public Listener(final JavaPlugin javaPlugin) {
        this(javaPlugin, Bukkit.getPluginManager());
    }

    public Listener(final JavaPlugin javaPlugin, final PluginManager pluginManager) {
        this.javaPlugin = javaPlugin;
        this.pluginManager = pluginManager;
        this.register();
    }

    @Override
    public void register() {
        this.pluginManager.registerEvents(this, this.javaPlugin);
    }

    @Override
    public void unregister() {
        HandlerList.unregisterAll(this);
    }

    @Override
    public void reload() {
        this.unregister();
        this.register();
    }
}
