package gg.acai.acava.command;

/**
 * @author Clouke
 * @since 03.12.2022 11:55
 * © Acava - All Rights Reserved
 */
@FunctionalInterface
public interface ICommand {

    /**
     * Gets the command of this command handler.
     *
     * @return Returns the command.
     */
    Command getCommand();

}
