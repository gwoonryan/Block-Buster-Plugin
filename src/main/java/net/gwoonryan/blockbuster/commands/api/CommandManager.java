package net.gwoonryan.blockbuster.commands.api;

import net.gwoonryan.blockbuster.BlockBuster;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Set;

public class CommandManager {
    private static final ArrayList<Command> commands = new ArrayList<>();

    public static void init(){
        BlockBuster.logger.info("Command manager starting.");
        registerAllCommands();
    }
    private static void registerAllCommands(){
        Reflections reflections = new Reflections("net.gwoonryan.blockbuster.commands"); // Replace with your package name
        Set<Class<? extends Command>> commandClasses = reflections.getSubTypesOf(Command.class);

        for (Class<? extends Command> commandClass : commandClasses) {
            try {
                // Find a no-args constructor
                if (Modifier.isAbstract(commandClass.getModifiers())){
                    BlockBuster.logger.info("Skipping abstract command class: " + commandClass.getSimpleName());
                    continue;
                }
                Constructor<? extends Command> constructor = commandClass.getDeclaredConstructor();
                constructor.setAccessible(true);


                // Instantiate and auto-register
                commands.add(constructor.newInstance());
                BlockBuster.logger.info("Registered command: " + commandClass.getSimpleName());
            } catch (Exception e) {
                BlockBuster.logger.info("Failed to register command: " + commandClass.getSimpleName());
                e.printStackTrace();
            }
        }
    }
}
