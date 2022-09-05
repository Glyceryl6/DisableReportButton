package com.glyceryl6.disable.mixin;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.settings.KeyMappingLookup;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyMapping.class)
public class MixinKeyMapping {

    @Shadow @Final private static KeyMappingLookup MAP;

    @Inject(method = "releaseAll", at = @At("HEAD"))
    private static void releaseAll(CallbackInfo ci) {
        Minecraft mc = Minecraft.getInstance();
        MAP.remove(mc.options.keySocialInteractions);
    }

}