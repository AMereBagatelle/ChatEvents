package amerebagatelle.github.io.chatevents.event;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Map;

@Environment(EnvType.CLIENT)
public class EventManager {
    public static final EventManager INSTANCE = new EventManager();
    public static final Gson gson = new Gson();
    public ArrayList<Event> events = new ArrayList<>();
    public static final Logger LOGGER = LogManager.getLogger();

    public void loadEvents() {
        LOGGER.info("ChatEvents is attempting to load your events!");
        try {
            File eventFile = new File(FabricLoader.getInstance().getConfigDirectory(), "chatevents.json");
            JsonObject eventJson = gson.fromJson(new String(Files.readAllBytes(eventFile.toPath())), JsonObject.class);
            for (Map.Entry<String, JsonElement> entry : eventJson.entrySet()) {
                try {
                    Event event = gson.fromJson(entry.getValue(), Event.class);
                    events.add(event);
                } catch (JsonSyntaxException e) {
                    LOGGER.warn(String.format("Could not load event %s, make sure that it is correct.", entry.getKey()));
                }
            }
        } catch (IOException e) {
            LOGGER.warn("Couldn't load any events.");
        }
    }

    public void processChatMessage(String message) {
        for (Event event : events) {
            event.processEvent(message);
        }
    }
}
