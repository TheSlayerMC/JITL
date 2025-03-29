package net.jitl.core.init.internal;

import net.jitl.core.init.JITL;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class JSounds {

    public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(Registries.SOUND_EVENT, JITL.MOD_ID);
    public static final DeferredRegister<JukeboxSong> JUKEBOX_SONG = DeferredRegister.create(Registries.JUKEBOX_SONG, JITL.MOD_ID);

    public static final DeferredHolder<SoundEvent, SoundEvent> ICE_CRYSTAL_BREAK = registerSound("block.ice_crystal.break");
    public static final DeferredHolder<SoundEvent, SoundEvent> TOAST = registerSound("music.toast");
    public static final DeferredHolder<SoundEvent, SoundEvent> TOAST_SPECIAL = registerSound("music.toast_special");

    public static final DeferredHolder<SoundEvent, SoundEvent> ICICLE_BREAK = registerSound("block.icicle.break");
    public static final DeferredHolder<SoundEvent, SoundEvent> ICICLE_DIG = registerSound("block.icicle.dig");

    public static final DeferredHolder<SoundEvent, SoundEvent> MUD_BLOCK_BREAK = registerSound("block.mud.break");
    public static final DeferredHolder<SoundEvent, SoundEvent> MUD_BLOCK_DIG = registerSound("block.mud.dig");

    public static final DeferredHolder<SoundEvent, SoundEvent> EPIC_SOUND_0 = registerSound("misc.epic_sound_0");
    public static final DeferredHolder<SoundEvent, SoundEvent> BASIC_DIALOG = registerSound("misc.dialog_basic");

    public static final DeferredHolder<SoundEvent, SoundEvent> POTTERY_BREAK = registerSound("block.pottery.break");
    public static final DeferredHolder<SoundEvent, SoundEvent> POTTERY_DIG = registerSound("block.pottery.dig");
    public static final DeferredHolder<SoundEvent, SoundEvent> BOTTLE_PLUG = registerSound("items.bottle_plug");

    public static final DeferredHolder<SoundEvent, SoundEvent> GRASSY_PERMAFROST_BREAK = registerSound("block.grassy_permafrost.break");
    public static final DeferredHolder<SoundEvent, SoundEvent> GRASSY_PERMAFROST_DIG = registerSound("block.grassy_permafrost.dig");

    public static final DeferredHolder<SoundEvent, SoundEvent> CRUMBLED_PERMAFROST_BREAK = registerSound("block.crumbled_permafrost.break");
    public static final DeferredHolder<SoundEvent, SoundEvent> CRUMBLED_PERMAFROST_DIG = registerSound("block.crumbled_permafrost.dig");

    public static final DeferredHolder<SoundEvent, SoundEvent> PERMAFROST_BREAK = registerSound("block.permafrost.break");
    public static final DeferredHolder<SoundEvent, SoundEvent> PERMAFROST_DIG = registerSound("block.permafrost.dig");

    public static final DeferredHolder<SoundEvent, SoundEvent> LUNIUM_ORE_BREAK = registerSound("block.lunium_ore.break");
    public static final DeferredHolder<SoundEvent, SoundEvent> LUNIUM_BLOCK_BREAK = registerSound("block.lunium_block.break");
    public static final DeferredHolder<SoundEvent, SoundEvent> LUNIUM_BLOCK_DIG = registerSound("block.lunium_block.dig");

    public static final DeferredHolder<SoundEvent, SoundEvent> SHADIUM_ORE_BREAK = registerSound("block.shadium_ore.break");
    public static final DeferredHolder<SoundEvent, SoundEvent> SHADIUM_BLOCK_BREAK = registerSound("block.shadium_block.break");
    public static final DeferredHolder<SoundEvent, SoundEvent> SHADIUM_BLOCK_DIG = registerSound("block.shadium_block.dig");

    public static final DeferredHolder<SoundEvent, SoundEvent> BRICK_BLOCK_BREAK = registerSound("block.brick.break");
    public static final DeferredHolder<SoundEvent, SoundEvent> BRICK_BLOCK_DIG = registerSound("block.brick.dig");

    public static final DeferredHolder<SoundEvent, SoundEvent> FUMICE_BLOCK_BREAK = registerSound("block.fumice.break");
    public static final DeferredHolder<SoundEvent, SoundEvent> FUMICE_BLOCK_DIG = registerSound("block.fumice.dig");

    public static final DeferredHolder<SoundEvent, SoundEvent> RUNE_ACTIVATE = registerSound("block.rune.activate");
    public static final DeferredHolder<SoundEvent, SoundEvent> ESSENCIA_ALTAR_ACTIVATE = registerSound("block.essencia_altar.activate");

    public static final DeferredHolder<SoundEvent, SoundEvent> CRYSTAL_APPLE_FREEZE = registerSound("items.crystal_apple.freeze");
    public static final DeferredHolder<SoundEvent, SoundEvent> CRYSTAL_APPLE_UNFREEZE = registerSound("items.crystal_apple.unfreeze");

    public static final DeferredHolder<SoundEvent, SoundEvent> STAFF_0 = registerSound("items.staff.staff_0");
    public static final DeferredHolder<SoundEvent, SoundEvent> LOOT = registerSound("items.loot");
    public static final DeferredHolder<SoundEvent, SoundEvent> MINERS_PEARL = registerSound("items.miners_pearl");
    public static final DeferredHolder<SoundEvent, SoundEvent> HAMMER = registerSound("items.hammer");
    public static final DeferredHolder<SoundEvent, SoundEvent> CANNON = registerSound("items.cannon");

    public static final DeferredHolder<SoundEvent, SoundEvent> HONGO_AMBIENT = registerSound("entities.hongo_ambient");
    public static final DeferredHolder<SoundEvent, SoundEvent> HONGO_HURT = registerSound("entities.hongo_hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> HONGO_DEATH = registerSound("entities.hongo_death");
    public static final DeferredHolder<SoundEvent, SoundEvent> HONGO_SPORE_RELEASE = registerSound("entities.hongo_spore_release");

    public static final DeferredHolder<SoundEvent, SoundEvent> GLUMP_AMBIENT = registerSound("entities.glump_ambient");
    public static final DeferredHolder<SoundEvent, SoundEvent> GLUMP_HURT = registerSound("entities.glump_hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> GLUMP_DEATH = registerSound("entities.glump_death");

    public static final DeferredHolder<SoundEvent, SoundEvent> BLAZIER_IDLE = registerSound("entities.blazier_idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> BLAZIER_HURT = registerSound("entities.blazier_hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> BLAZIER_DEATH = registerSound("entities.blazier_death");

    public static final DeferredHolder<SoundEvent, SoundEvent> CALCIA_IDLE = registerSound("entities.calcia");
    public static final DeferredHolder<SoundEvent, SoundEvent> CALCIA_HURT = registerSound("entities.calcia_hurt");

    public static final DeferredHolder<SoundEvent, SoundEvent> FROZEN_TROLL_AMBIENT = registerSound("entities.frozen_troll_ambient");
    public static final DeferredHolder<SoundEvent, SoundEvent> FROZEN_TROLL_HURT = registerSound("entities.frozen_troll_hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> FROZEN_TROLL_DEATH = registerSound("entities.frozen_troll_death");
    public static final DeferredHolder<SoundEvent, SoundEvent> FROZEN_TROLL_INTRIGUED = registerSound("entities.frozen_troll_intrigued");

    public static final DeferredHolder<SoundEvent, SoundEvent> FROZEN_GUARDIAN_DEATH = registerSound("entities.frozen_guardian_death");

    public static final DeferredHolder<SoundEvent, SoundEvent> ROCKITE_SMASHER_HURT = registerSound("entities.rockite_smasher_hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> ROCKITE_SMASHER_DEATH = registerSound("entities.rockite_smasher_death");

    public static final DeferredHolder<SoundEvent, SoundEvent> ILLAGER_MECH_STEP = registerSound("entities.illager_mech_step");
    public static final DeferredHolder<SoundEvent, SoundEvent> ILLAGER_MECH_HURT = registerSound("entities.illager_mech_hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> ILLAGER_MECH_DEATH = registerSound("entities.illager_mech_death");
    public static final DeferredHolder<SoundEvent, SoundEvent> ILLAGER_MECH_THROW = registerSound("entities.illager_mech_throw");

    public static final DeferredHolder<SoundEvent, SoundEvent> SENTRY_AMBIENT = registerSound("entities.sentry_ambient");

    public static final DeferredHolder<SoundEvent, SoundEvent> KNIFE = registerSound("entities.knife");
    public static final DeferredHolder<SoundEvent, SoundEvent> PIERCER = registerSound("entities.piercer");
    public static final DeferredHolder<SoundEvent, SoundEvent> PIERCER_RETURN = registerSound("entities.piercer_return");

    public static final DeferredHolder<SoundEvent, SoundEvent> MENU_MUSIC = registerSound("music.menu");
    public static final DeferredHolder<SoundEvent, SoundEvent> TOWER_THEME = registerSound("music.guardian_tower");
    public static final DeferredHolder<SoundEvent, SoundEvent> TEMPLE_GUARDIAN_MUSIC = registerSound("music.temple_guardian");
    public static final DeferredHolder<SoundEvent, SoundEvent> EUCA_AMBIENCE = registerSound("music.euca.ambience");
    public static final DeferredHolder<SoundEvent, SoundEvent> GOLD_PLAINS_MUSIC = registerSound("music.biome.gold_plains");

    public static final DeferredHolder<SoundEvent, SoundEvent> EUCA_MUSIC = registerSound("music.dim.euca");
    public static final DeferredHolder<SoundEvent, SoundEvent> BOIL_MUSIC = registerSound("music.dim.boil");
    public static final DeferredHolder<SoundEvent, SoundEvent> CLOUDIA_MUSIC = registerSound("music.dim.cloudia");
    public static final DeferredHolder<SoundEvent, SoundEvent> CORBA_MUSIC = registerSound("music.dim.corba");
    public static final DeferredHolder<SoundEvent, SoundEvent> DEPTHS_MUSIC = registerSound("music.dim.depths");
    public static final DeferredHolder<SoundEvent, SoundEvent> FROZEN_MUSIC = registerSound("music.dim.frozen");
    public static final DeferredHolder<SoundEvent, SoundEvent> TERRANIA_MUSIC = registerSound("music.dim.terrania");

    public static final DeferredHolder<SoundEvent, SoundEvent> HAUNT_MUSKIE_2 = registerSound("music.haunt_muskie_2");
    public static final DeferredHolder<SoundEvent, SoundEvent> SNOWFLAKESSS = registerSound("music.snowflakesss");

    public static final DeferredHolder<SoundEvent, SoundEvent> UNDERWATER_WORLD = registerSound("music.record.underwater_world");
    public static final DeferredHolder<SoundEvent, SoundEvent> EUCA_DISC_1 = registerSound("music.record.euca_1");
    public static final DeferredHolder<SoundEvent, SoundEvent> EUCA_DISC_2 = registerSound("music.record.euca_2");
    public static final DeferredHolder<SoundEvent, SoundEvent> EUCA_DISC_3 = registerSound("music.record.euca_3");
    public static final DeferredHolder<SoundEvent, SoundEvent> FROZEN_DISC_1 = registerSound("music.record.frozen_1");
    public static final DeferredHolder<SoundEvent, SoundEvent> BOIL_DISC_1 = registerSound("music.record.boil_1");

    public static final DeferredHolder<SoundEvent, SoundEvent> BOSS_DEATH = registerSound("entities.boss.death");
    public static final DeferredHolder<SoundEvent, SoundEvent> COIN_PICKUP = registerSound("misc.coin_pickup");
    public static final DeferredHolder<SoundEvent, SoundEvent> CRYSTAL_ERROR = registerSound("misc.crystal_error");
    public static final DeferredHolder<SoundEvent, SoundEvent> CRYSTAL_PICKUP = registerSound("misc.crystal_pickup");

    public static final DeferredHolder<SoundEvent, SoundEvent> EUCA_PORTAL = registerSound("misc.portal.euca");
    public static final DeferredHolder<SoundEvent, SoundEvent> FROZEN_PORTAL = registerSound("misc.portal.frozen");
    public static final DeferredHolder<SoundEvent, SoundEvent> BOIL_PORTAL = registerSound("misc.portal.boil");
    public static final DeferredHolder<SoundEvent, SoundEvent> DEPTHS_PORTAL = registerSound("misc.portal.depths");
    public static final DeferredHolder<SoundEvent, SoundEvent> CORBA_PORTAL = registerSound("misc.portal.corba");
    public static final DeferredHolder<SoundEvent, SoundEvent> TERRANIA_PORTAL = registerSound("misc.portal.terrania");
    public static final DeferredHolder<SoundEvent, SoundEvent> CLOUDIA_PORTAL = registerSound("misc.portal.cloudia");
    public static final DeferredHolder<SoundEvent, SoundEvent> SENTERIAN_PORTAL = registerSound("misc.portal.senterian");

    public static final DeferredHolder<SoundEvent, SoundEvent> ROCK = registerSound("rock");
    public static final DeferredHolder<SoundEvent, SoundEvent> CAVE_MOB = registerSound("cave_mob");
    public static final DeferredHolder<SoundEvent, SoundEvent> BASE_MOB_HURT = registerSound("base_mob_hurt");

    public static final DeferredHolder<SoundEvent, SoundEvent> TERRA_SLUG = registerSound("entities.terraslug");
    public static final DeferredHolder<SoundEvent, SoundEvent> TERRA_SLUG_HURT = registerSound("entities.terraslug_hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> TERRA_SLUG_DEATH = registerSound("entities.terraslug_death");

    public static final DeferredHolder<SoundEvent, SoundEvent> SORCERER_DEATH = registerSound("entities.sorcerer_death");
    public static final DeferredHolder<SoundEvent, SoundEvent> SORCERER_HURT = registerSound("entities.sorcerer_hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> SORCERER = registerSound("entities.sorcerer");

    public static final DeferredHolder<SoundEvent, SoundEvent> ROBOT = registerSound("entities.robot");
    public static final DeferredHolder<SoundEvent, SoundEvent> ROBOT_HURT = registerSound("entities.robot_hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> ROBOT_DEATH = registerSound("entities.robot_death");

    public static final DeferredHolder<SoundEvent, SoundEvent> FERRET = registerSound("entities.ferret");
    public static final DeferredHolder<SoundEvent, SoundEvent> FERRET_HURT = registerSound("entities.ferret_hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> FERRET_DEATH = registerSound("entities.ferret_death");

    public static final DeferredHolder<SoundEvent, SoundEvent> SPIKED_BEAST = registerSound("entities.spiked_beast");
    public static final DeferredHolder<SoundEvent, SoundEvent> SPIKED_BEAST_HURT = registerSound("entities.spiked_beast_hurt");

    public static final DeferredHolder<SoundEvent, SoundEvent> SPYCLOPS = registerSound("entities.spyclops");
    public static final DeferredHolder<SoundEvent, SoundEvent> SPYCLOPS_HURT = registerSound("entities.spyclops_hurt");

    public static final DeferredHolder<SoundEvent, SoundEvent> TURTLE = registerSound("entities.turtle");
    public static final DeferredHolder<SoundEvent, SoundEvent> TURTLE_HURT = registerSound("entities.turtle_hurt");

    public static final DeferredHolder<SoundEvent, SoundEvent> SMALL_HONGO = registerSound("entities.small_hongo");
    public static final DeferredHolder<SoundEvent, SoundEvent> SMALL_HONGO_HURT = registerSound("entities.small_hongo_hurt");

    public static final DeferredHolder<SoundEvent, SoundEvent> REAPER = registerSound("entities.reaper");
    public static final DeferredHolder<SoundEvent, SoundEvent> REAPER_HURT = registerSound("entities.reaper_hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> SAND_CRAWLER = registerSound("entities.sand_crawler");
    public static final DeferredHolder<SoundEvent, SoundEvent> DEPTHS_HUNTER = registerSound("entities.depths_hunter");
    public static final DeferredHolder<SoundEvent, SoundEvent> DEPTHS_HUNTER_HURT = registerSound("entities.depths_hunter_hurt");

    public static final DeferredHolder<SoundEvent, SoundEvent> OKOLOO = registerSound("entities.okoloo");
    public static final DeferredHolder<SoundEvent, SoundEvent> OKOLOO_HURT = registerSound("entities.okoloo_hurt");

    public static final DeferredHolder<SoundEvent, SoundEvent> DYNASTER = registerSound("entities.dynaster");
    public static final DeferredHolder<SoundEvent, SoundEvent> DYNASTER_HURT = registerSound("entities.dynaster_hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> DYNASTER_DEATH = registerSound("entities.dynaster_death");
    public static final DeferredHolder<SoundEvent, SoundEvent> SHIMMERER = registerSound("entities.shimmerer");
    public static final DeferredHolder<SoundEvent, SoundEvent> SHIMMERER_HURT = registerSound("entities.shimmerer_hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> SHIMMERER_DEATH = registerSound("entities.shimmerer_death");
    public static final DeferredHolder<SoundEvent, SoundEvent> BUSH = registerSound("entities.bush");
    public static final DeferredHolder<SoundEvent, SoundEvent> BUSH_HURT = registerSound("entities.bush_hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> BUSH_DEATH = registerSound("entities.bush_death");
    public static final DeferredHolder<SoundEvent, SoundEvent> WRAITH = registerSound("entities.wraith");
    public static final DeferredHolder<SoundEvent, SoundEvent> WRAITH_DEATH = registerSound("entities.wraith_death");
    public static final DeferredHolder<SoundEvent, SoundEvent> WRAITH_HURT = registerSound("entities.wraith_hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> OVERSEER = registerSound("entities.overseer");
    public static final DeferredHolder<SoundEvent, SoundEvent> OVERSEER_HURT = registerSound("entities.overseer_hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> OVERSEER_DEATH = registerSound("entities.overseer_death");

    public static final DeferredHolder<SoundEvent, SoundEvent> STINKY = registerSound("entities.stinky");
    public static final DeferredHolder<SoundEvent, SoundEvent> STINKY_HURT = registerSound("entities.stinky_hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> STINKY_DEATH = registerSound("entities.stinky_death");

    public static final DeferredHolder<SoundEvent, SoundEvent> BIRD = registerSound("entities.bird");
    public static final DeferredHolder<SoundEvent, SoundEvent> BIRD_HURT = registerSound("entities.bird_hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> BIRD_DEATH = registerSound("entities.bird_death");

    public static final DeferredHolder<SoundEvent, SoundEvent> LAVASNAKE_IDLE = registerSound("entities.lavasnake_idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> LAVASNAKE_HURT = registerSound("entities.lavasnake_hurt");

    public static final DeferredHolder<SoundEvent, SoundEvent> SENTRY_ALTAR_ACTIVATE = registerSound("misc.sentry_altar_activate");
    public static final DeferredHolder<SoundEvent, SoundEvent> SENTRY_ALTAR_DEACTIVATE = registerSound("misc.sentry_altar_deactivate");

    public static final DeferredHolder<SoundEvent, SoundEvent> OBELISK_IDLE = registerSound("misc.obelisk_idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> OBELISK_OPEN = registerSound("misc.obelisk_open");

    public static final DeferredHolder<JukeboxSong, JukeboxSong> UNDERWATER_WORLD_JUKEBOX = registerJukeboxSound("record.underwater_world", UNDERWATER_WORLD, 132);
    public static final DeferredHolder<JukeboxSong, JukeboxSong> GOLD_PLAINS_JUKEBOX = registerJukeboxSound("record.gold_plains", GOLD_PLAINS_MUSIC, 132);
    public static final DeferredHolder<JukeboxSong, JukeboxSong> EUCA_DISC_1_JUKEBOX = registerJukeboxSound("record.euca_1", EUCA_DISC_1, 56);
    public static final DeferredHolder<JukeboxSong, JukeboxSong> EUCA_DISC_2_JUKEBOX = registerJukeboxSound("record.euca_2", EUCA_DISC_2, 60);
    public static final DeferredHolder<JukeboxSong, JukeboxSong> EUCA_DISC_3_JUKEBOX = registerJukeboxSound("record.euca_3", EUCA_DISC_3, 60);
    public static final DeferredHolder<JukeboxSong, JukeboxSong> FROZEN_DISC_1_JUKEBOX = registerJukeboxSound("record.frozen_1", FROZEN_DISC_1, 174);
    public static final DeferredHolder<JukeboxSong, JukeboxSong> BOIL_DISC_1_JUKEBOX = registerJukeboxSound("record.boil_1", BOIL_DISC_1, 157);

    private static DeferredHolder<SoundEvent, SoundEvent> registerSound(String name) {
        return REGISTRY.register(name, () -> createSoundEvent(name));
    }

    private static SoundEvent createSoundEvent(String soundPath) {
        return SoundEvent.createVariableRangeEvent(JITL.rl(soundPath));
    }

    private static DeferredHolder<JukeboxSong, JukeboxSong> registerJukeboxSound(String name, DeferredHolder<SoundEvent, SoundEvent> song, int seconds) {
        int output = 13;
        return JUKEBOX_SONG.register(name, () -> new JukeboxSong(song, Component.translatable(Util.makeDescriptionId("jukebox_song", song.getKey().location())), seconds, output));
    }
}