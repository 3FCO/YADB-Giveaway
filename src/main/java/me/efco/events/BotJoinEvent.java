package me.efco.events;

import me.efco.commands.SlashCommandManager;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BotJoinEvent extends ListenerAdapter {
    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        SlashCommandManager.getInstance().updateAllCommands(event.getGuild());
    }
}
