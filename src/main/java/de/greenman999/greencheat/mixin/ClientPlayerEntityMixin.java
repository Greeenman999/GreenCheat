package de.greenman999.greencheat.mixin;

import de.greenman999.greencheat.Greencheat;
import de.greenman999.greencheat.pluginsystem.IPlugin;
import de.greenman999.greencheat.pluginsystem.PluginLoader;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Environment(EnvType.CLIENT)
@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {

    private final PluginLoader<IPlugin> pluginLoader = Greencheat.getPluginLoader();

    @Inject(at = @At("HEAD"),
            method = "sendChatMessage(Ljava/lang/String;)V",
            cancellable = true)
    private void onAddMessage(String message, CallbackInfo ci) {
        if(message.startsWith(".")) {
            String[] args = message.split(" ");
            String cmd = args[0];
            args = ArrayUtils.remove(args,0);
            pluginLoader.onCommand(cmd, args);
            ci.cancel();
        }
    }

}
