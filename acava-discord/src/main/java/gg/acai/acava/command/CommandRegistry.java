package gg.acai.acava.command;

import gg.acai.acava.function.Action;
import gg.acai.acava.io.Closeable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.EventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Clouke
 * @since 03.12.2022 11:54
 * © Acava - All Rights Reserved
 *
 * Householder class for all commands, with registration and execution.
 */
public final class CommandRegistry implements Closeable {

    private final JDA jda;
    private final Map<Class<? extends ICommand>, ICommand> commands;

    public CommandRegistry(JDA jda) {
        this.jda = jda;
        this.commands = new HashMap<>();

        // Listen for slash command interactions
        jda.addEventListener((EventListener) event -> {
            if (event instanceof SlashCommandInteractionEvent) {
                SlashCommandInteractionEvent interaction = (SlashCommandInteractionEvent) event;
                this.commands.values().stream()
                        .filter(command -> command.getCommand().getName().equalsIgnoreCase(interaction.getName()))
                        .findFirst()
                        .ifPresent(command -> {
                            Action<SlashCommandInteractionEvent> action = command
                                    .getCommand()
                                    .getAction();

                            action.accept(interaction);
                        });
            }
        });
    }

    /**
     * Registers a command to the registry.
     *
     * @param command {@link ICommand} to register.
     */
    public void register(ICommand command) {
        this.commands.put(command.getClass(), command);
        update();
    }

    /**
     * Registers a command directly from {@link Command}.
     *
     * @param command {@link Command} to register.
     */
    public void register(Command command) {
        register(() -> command);
    }

    /**
     * Registers a command directly with its raw class
     *
     * @param rawClass {@link Class} of the command to register.
     */
    public void register(Class<? extends ICommand> rawClass) {
        try {
            register(rawClass.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets a command from the registry.
     *
     * @param rawClass {@link Class} of the command.
     * @return {@link ICommand} if found, otherwise null.
     */
    public Command get(Class<? extends ICommand> rawClass) {
        return this.commands
                .get(rawClass)
                .getCommand();
    }

    /**
     * Updates the commands on the Discord API.
     */
    public void update() {
        this.commands.values().forEach(command -> jda
                .upsertCommand(command.getCommand().getData())
                .queue());
    }

    @Override
    public void close() {
        synchronized (commands) {
            this.commands.clear();
        }
    }
}
