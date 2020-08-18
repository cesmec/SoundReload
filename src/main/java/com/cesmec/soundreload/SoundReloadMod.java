package com.cesmec.soundreload;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = SoundReloadMod.MODID, name = SoundReloadMod.NAME, version = SoundReloadMod.VERSION, clientSideOnly = true)
public class SoundReloadMod
{
    public static final String MODID = "soundreload";
    public static final String NAME = "Sound Reload Mod";
    public static final String VERSION = "1.0";

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        ClientCommandHandler.instance.registerCommand(new SoundReloadCommand());
    }
}
