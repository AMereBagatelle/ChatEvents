package amerebagatelle.github.io.chatevents.event;

import amerebagatelle.github.io.chatevents.mixin.NarratorManagerFake;
import com.google.gson.JsonObject;
import com.mojang.text2speech.Narrator;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.NarratorManager;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

import java.util.Random;

@Environment(EnvType.CLIENT)
public class Event {
    public String matches;
    public String mode;
    public String responseType;
    public String[] responses;
    private final Random random = new Random();

    public void processEvent(String message) {
        switch (mode) {
            case "contains":
                if(message.contains(matches)) respondToEvent(message);
                break;

            case "equals":
                if(message.equals(matches)) respondToEvent(message);
                break;

            case "regex":
                if(message.matches(matches)) respondToEvent(message);

            case "all":
                respondToEvent(message);
        }
    }

    public void respondToEvent(String message) {
        MinecraftClient mc = MinecraftClient.getInstance();
        switch (responseType) {
            case "message":
                mc.player.sendChatMessage(responses[random.nextInt()%responses.length]);
                break;

            case "sound":
                mc.world.playSound(mc.player.getBlockPos(), SoundEvents.BLOCK_NOTE_BLOCK_PLING, SoundCategory.MASTER, 1.0f, 0.5f, false);
                break;

            case "notify":
                break;

            case "narrate":
                Narrator narrator = ((NarratorManagerFake)NarratorManager.INSTANCE).getNarrator();
                narrator.clear();
                narrator.say(message, true);
                break;
        }
    }
}
