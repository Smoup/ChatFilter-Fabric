package org.smod.mixin;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.ChatMessages;
import net.minecraft.text.OrderedText;
import net.minecraft.text.StringVisitable;
import org.smod.MsgHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(ChatMessages.class)
public class ChatNewMessage {
    @Inject(method = "breakRenderedChatMessageLines", at = @At("RETURN"), cancellable = true)
    private static void onNewMessage(StringVisitable message, int width,
                                     TextRenderer textRenderer, CallbackInfoReturnable<List<OrderedText>> cir) {
        if (MsgHandler.check(message.getString())) {
            cir.setReturnValue(new ArrayList<>());
        }
    }

}
