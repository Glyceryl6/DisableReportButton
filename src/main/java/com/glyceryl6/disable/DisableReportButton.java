package com.glyceryl6.disable;

import com.glyceryl6.disable.event.DisableWidgets;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(DisableReportButton.MODID)
public class DisableReportButton {

    public static final String MODID = "disable_report_button";

    public DisableReportButton() {
        MinecraftForge.EVENT_BUS.register(new DisableWidgets());
        MinecraftForge.EVENT_BUS.register(this);
    }

}