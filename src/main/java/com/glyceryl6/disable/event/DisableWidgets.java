package com.glyceryl6.disable.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Widget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.reporting.ChatReportScreen;
import net.minecraft.client.gui.screens.social.SocialInteractionsScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DisableWidgets {

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void disableWidgets(ScreenEvent event) {
        Screen screen = event.getScreen();
        Minecraft mc = Minecraft.getInstance();
        if (screen instanceof ChatReportScreen ||
            screen instanceof SocialInteractionsScreen) {
            screen.clearWidgets();
            event.setCanceled(true);
        }

        if (mc.socialInteractionsToast != null) {
            mc.socialInteractionsToast.hide();
            mc.getTutorial().removeTimedToast(mc.socialInteractionsToast);
            mc.socialInteractionsToast = null;
        }

        for (Widget widget : screen.renderables) {
            if (widget instanceof Button button) {
                String str = "menu.playerReporting";
                MutableComponent component = Component.translatable(str);
                Button.OnPress onPress = (b) -> mc.setScreen(new SocialInteractionsScreen());
                if (button.onPress == onPress || button.getMessage().copy().equals(component)) {
                    screen.removeWidget(button);
                }
            }
        }
    }

}