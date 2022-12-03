package gg.acai.acava.command;

import gg.acai.acava.function.Action;
import lombok.Getter;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

/**
 * @author Clouke
 * @since 03.12.2022 11:56
 * © Acava - All Rights Reserved
 */
@Getter
public class Command {

    private String name;
    private SlashCommandData data;
    private Action<SlashCommandInteractionEvent> action;

    public static Command newCommand() {
        return new Command();
    }

    public Command data(SlashCommandData data) {
        this.name = data.getName();
        this.data = data;
        return this;
    }

    public Command data(String name, String description) {
        this.name = name;
        this.data = Commands.slash(name, description);
        return this;
    }

    public Command data(String name) {
        return data(name, "N/A");
    }

    public Command action(Action<SlashCommandInteractionEvent> action) {
        this.action = action;
        return this;
    }


}
