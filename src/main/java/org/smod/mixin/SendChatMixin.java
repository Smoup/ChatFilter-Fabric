package org.smod.mixin;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import org.smod.ModConfig;
import org.smod.client.ChatFilterClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ClientPlayNetworkHandler.class)
public class SendChatMixin {
    @Inject(method = "sendPacket", at = @At("HEAD"), cancellable = true)
    public void onSendMessage(Packet<?> packet, CallbackInfo ci) {
        if (packet.getClass().equals(ChatMessageC2SPacket.class)) {
            ChatMessageC2SPacket c2SPacket = (ChatMessageC2SPacket) packet;
            if (c2SPacket.getChatMessage().contains(".smod")) {
                ci.cancel();
                List<String> args = List.of(c2SPacket.getChatMessage().split(" "));
                if (args.size() <= 1) {
                    sendAllowedCmd();
                    return;
                }
                String arg1 = args.get(1).toLowerCase();
                if (arg1.contains("reload")) {
                    ModConfig.loadConfig();
                    ChatFilterClient.sendClientMSG("Конфиг перезагружен");
                } else {
                    sendAllowedCmd();
                }
            }
        }

    }

    private void sendAllowedCmd() {
        ChatFilterClient.sendClientMSG("Для перезагрузки конфига используйте .smod reload");
    }
}
