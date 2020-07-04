package amerebagatelle.github.io.chatevents;

import amerebagatelle.github.io.chatevents.event.EventManager;
import net.fabricmc.api.ClientModInitializer;

public class ChatEvents implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EventManager.INSTANCE.loadEvents();
    }
}
