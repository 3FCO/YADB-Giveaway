package me.efco.events;

import me.efco.Bot;
import me.efco.commands.SlashCommandManager;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BotReadyEvent extends ListenerAdapter {
    @Override
    public void onReady(ReadyEvent event) {
        for (Guild guild : Bot.getApi().getGuilds()) {
            SlashCommandManager.getInstance().updateAllCommands(guild);
        }
    }
}
