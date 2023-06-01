package org.smod.client;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import org.smod.ModConfig;

public class ChatFilterClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModConfig.loadConfig();
    }

    public static void sendClientMSG(String message) {
        MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(Text.of(message));
    }
}
