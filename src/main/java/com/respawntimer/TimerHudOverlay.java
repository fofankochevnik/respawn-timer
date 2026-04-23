package com.respawntimer;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.client.Minecraft;

public class TimerHudOverlay implements LayeredDraw.Layer {

    private static final int COLOR_NORMAL = 0xFFFFFFFF;
    private static final int COLOR_WARNING = 0xFFFF3333;
    private static final int PADDING = 8;

    @Override
    public void render(GuiGraphics guiGraphics, net.minecraft.client.DeltaTracker deltaTracker) {
        if (!TimerManager.isActive()) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.options.hideGui) return;

        int seconds = TimerManager.getRemainingSeconds();
        int minutes = seconds / 60;
        int secs = seconds % 60;
        String text = String.format("%d:%02d", minutes, secs);

        int color = TimerManager.isWarning() ? COLOR_WARNING : COLOR_NORMAL;
        int textWidth = mc.font.width(text);
        int screenWidth = guiGraphics.guiWidth();

        int x = screenWidth - textWidth - PADDING;
        int y = PADDING;

        guiGraphics.drawString(mc.font, text, x, y, color, true);
    }
}
