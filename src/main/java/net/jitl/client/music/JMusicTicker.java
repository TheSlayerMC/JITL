package net.jitl.client.music;

import net.jitl.common.world.dimension.Dimensions;
import net.jitl.common.world.dimension.JBiomeRegistry;
import net.jitl.core.helper.JMusic;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;
import java.util.Random;

@Mod.EventBusSubscriber(modid = JITL.MODID, value = Dist.CLIENT)
public class JMusicTicker {

    private static final Minecraft MINECRAFT = Minecraft.getInstance();
    private static SoundInstance currentTrack;
    private static JMusic shouldPlayTrack;
    private static int timeToNext;
    private static final RandomSource RANDOM = RandomSource.create();

    @SubscribeEvent
    public static void musicTick(TickEvent.ClientTickEvent musicEvent) {
        if (musicEvent.phase == TickEvent.Phase.START) {
            shouldPlayTrack = null; //make sure the music stops alongside whatever was playing it
        } else if (!MINECRAFT.isPaused()) {
            if ((currentTrack == null) != (shouldPlayTrack == null)) { //make sure both either are or aren't null
                switchTracks();
            }
            if (currentTrack != null) { //needs to be checked again since previous statement might have made one of them null, causing a crash
                if (currentTrack.getLocation() != shouldPlayTrack.getEvent().getLocation()) { //make sure the music that is playing is actually the correct one
                    switchTracks();
                }
                MINECRAFT.getMusicManager().nextSongDelay = 100; //freeze vanilla music counter so only jitl music will play. Sorry, C418! :D
                if (!MINECRAFT.getSoundManager().isActive(currentTrack)) { //music loop
                    if (timeToNext <= 0) {
                        MINECRAFT.getSoundManager().play(currentTrack);
                        timeToNext = Mth.nextInt(RANDOM, shouldPlayTrack.getMin(), shouldPlayTrack.getMax());
                        MINECRAFT.getMusicManager().stopPlaying(); //kills vanilla music
                    } else {
                        timeToNext--;
                    }
                }
            }
        }
    }

    public static void addTrack(JMusic track) {
        if (shouldPlayTrack == null || track.getMusicImportance() > shouldPlayTrack.getMusicImportance()) {
            shouldPlayTrack = track;
        }
    }

    private static void switchTracks() {
        MINECRAFT.getSoundManager().stop(currentTrack);
        if (shouldPlayTrack != null) {
            currentTrack = SimpleSoundInstance.forMusic(shouldPlayTrack.getEvent());
        } else {
            currentTrack = null;
            Music vanillaMusic = MINECRAFT.getSituationalMusic();
            MINECRAFT.getMusicManager().nextSongDelay = (Mth.nextInt(RANDOM, 0, vanillaMusic.getMinDelay() / 2)); //recreates a vanilla music swap
        }
    }

    public static boolean isMusicPlaying(SoundEvent event) {
        return (currentTrack != null && event.getLocation() == currentTrack.getLocation() && MINECRAFT.getSoundManager().isActive(currentTrack));
    }
}