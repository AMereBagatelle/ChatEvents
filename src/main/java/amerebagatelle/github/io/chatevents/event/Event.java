package amerebagatelle.github.io.chatevents.event;

import com.google.gson.JsonObject;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
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
    private Random random = new Random();

    public void processEvent(String message) {
        switch (mode) {
            case "contains":
                if(message.contains(matches)) respondToEvent();
                break;

            case "equals":
                if(message.equals(matches)) respondToEvent();
                break;
        }
    }

    public void respondToEvent() {
        MinecraftClient mc = MinecraftClient.getInstance();
        switch (responseType) {
            case "message":
                mc.player.sendChatMessage(responses[random.nextInt()%responses.length]);
                break;

            case "sound":
                mc.world.playSound(mc.player.getBlockPos(), SoundEvents.BLOCK_NOTE_BLOCK_CHIME, SoundCategory.MASTER, 1.0f, 0.5f, false);
        }
    }
}
