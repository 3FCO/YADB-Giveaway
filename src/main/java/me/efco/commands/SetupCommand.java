package me.efco.commands;

import me.efco.commands.interfaces.AbstractCommand;
import me.efco.data.DBConnection;
import me.efco.entities.Helper;
import me.efco.entities.ServerInfo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;

import java.awt.*;
import java.util.List;

public class SetupCommand extends AbstractCommand {
    public SetupCommand(String name, String description, List<Permission> permissions, List<OptionData> options, List<SubcommandData> subcommands) {
        super(name, description, permissions, options, subcommands);
    }

    @Override
    public void onExecution(SlashCommandInteractionEvent event) {
        ServerInfo serverInfo = DBConnection.getInstance().getServerInfo(event.getGuild().getIdLong());

        if (!event.getMember().isOwner()) {
            event.reply(Helper.getInstance().quickEmbedMessageBuilder("This command can only be executed by administrators")).setEphemeral(true).queue();
            return;
        }

        Role role;
        if (event.getOption("role") == null) {
            role = event.getGuild().createRole()
                    .setName("Giveaway Master")
                    .setColor(Color.PINK)
                    .submit().join();
        } else {
            role = event.getOption("role").getAsRole();
        }


        if (serverInfo == null) {
            DBConnection.getInstance().insertServerInfo(event.getGuild().getIdLong(), role.getIdLong());
        } else {
            DBConnection.getInstance().updateRole(event.getGuild().getIdLong(),role.getIdLong());
        }

        event.reply(Helper.getInstance().quickEmbedMessageBuilder("Giveaway role has been set to ``" + role.getName() + "``.\nThey can create/manage giveaways")).setEphemeral(true).queue();
    }
}
