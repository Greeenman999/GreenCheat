package de.greenman999.greencheat.commands;

import de.greenman999.greencheat.Greencheat;
import de.greenman999.greencheat.command.ICommandExecutor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ReloadCommand implements ICommandExecutor {
    @Override
    public boolean onCommand(String cmd, String[] args) {
        if(cmd.equalsIgnoreCase(".reload")) {
            try {
                final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
                executorService.schedule(() -> {
                    Greencheat.getPluginLoader().disable();
                    Greencheat.getPluginLoader().unload();
                    Greencheat.getPluginLoader().load();
                    Greencheat.getPluginLoader().enable();
                    MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(Text.of("§aReloaded plugins!"));
                }, 2, TimeUnit.SECONDS);
            }catch(Exception e) {
                System.out.println(e);
            }
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "Reloads all plugins";
    }

    @Override
    public String getSyntax() {
        return ".reload";
    }

    @Override
    public String getCmdName() {
        return "Reload";
    }
}
