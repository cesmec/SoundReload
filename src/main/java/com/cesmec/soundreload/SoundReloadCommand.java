package com.cesmec.soundreload;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

public class SoundReloadCommand implements ICommand {
    @Override
    public String getName() {
        return "soundreload";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "soundreload";
    }

    @Override
    public List<String> getAliases() {
        return Collections.emptyList();
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        try {
            SoundManager manager = getSoundManager();
            if (manager != null)
                manager.reloadSoundSystem();

            sender.sendMessage(new TextComponentString("[SoundReload] Sound System reloaded"));
        } catch (Exception e) {
            Style redText = new Style().setColor(TextFormatting.RED);
            sender.sendMessage(new TextComponentString("[SoundReload] " + e.toString()).setStyle(redText));
        }
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
        return Collections.emptyList();
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    @Override
    public int compareTo(ICommand o) {
        return 0;
    }

    private SoundManager getSoundManager() throws IllegalAccessException {
        SoundHandler soundHandler = Minecraft.getMinecraft().getSoundHandler();
        Field[] fields = SoundHandler.class.getDeclaredFields();

        for (Field field : fields) {
            if (field.getType().equals(SoundManager.class)) {
                field.setAccessible(true);
                return (SoundManager) field.get(soundHandler);
            }
        }

        return null;
    }
}
