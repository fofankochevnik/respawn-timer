package com.respawntimer;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;

@Mod(RespawnTimerMod.MODID)
public class RespawnTimerMod {

    public static final String MODID = "respawn_timer";

    public RespawnTimerMod(IEventBus modEventBus) {
        modEventBus.addListener(this::registerGuiLayers);
        NeoForge.EVENT_BUS.register(this);
    }

    private void registerGuiLayers(RegisterGuiLayersEvent event) {
        event.registerAboveAll(
            ResourceLocation.fromNamespaceAndPath(MODID, "timer_hud"),
            new TimerHudOverlay()
        );
    }

    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof net.minecraft.world.entity.player.Player) {
            Minecraft mc = Minecraft.getInstance();
            if (mc.player != null && event.getEntity().getUUID().equals(mc.player.getUUID())) {
                TimerManager.startTimer();
            }
        }
    }
}
