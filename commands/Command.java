package commands;

/**
 * Базовый интерфейс для всех команд (Command Pattern).
 */
public interface Command {

    void execute(String args);
    String getDescription();
    String getName();
    
}
