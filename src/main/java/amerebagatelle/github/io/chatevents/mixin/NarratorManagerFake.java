package amerebagatelle.github.io.chatevents.mixin;

import com.mojang.text2speech.Narrator;
import net.minecraft.client.util.NarratorManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(NarratorManager.class)
public interface NarratorManagerFake {
    @Accessor
    Narrator getNarrator();
}
