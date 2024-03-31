package net.jitl.core.init.internal;

import net.jitl.core.init.JITL;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class JSounds {

    public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, JITL.MODID);

    public static final RegistryObject<SoundEvent> ICE_CRYSTAL_BREAK = registerSound("block.ice_crystal.break");
    public static final RegistryObject<SoundEvent> TOAST = registerSound("music.toast");
    public static final RegistryObject<SoundEvent> TOAST_SPECIAL = registerSound("music.toast_special");

    public static final RegistryObject<SoundEvent> ICICLE_BREAK = registerSound("block.icicle.break");
    public static final RegistryObject<SoundEvent> ICICLE_DIG = registerSound("block.icicle.dig");

    public static final RegistryObject<SoundEvent> MUD_BLOCK_BREAK = registerSound("block.mud.break");
    public static final RegistryObject<SoundEvent> MUD_BLOCK_DIG = registerSound("block.mud.dig");

    public static final RegistryObject<SoundEvent> EPIC_SOUND_0 = registerSound("misc.epic_sound_0");
    public static final RegistryObject<SoundEvent> BASIC_DIALOG = registerSound("misc.dialog_basic");

    public static final RegistryObject<SoundEvent> POTTERY_BREAK = registerSound("block.pottery.break");
    public static final RegistryObject<SoundEvent> POTTERY_DIG = registerSound("block.pottery.dig");
    public static final RegistryObject<SoundEvent> BOTTLE_PLUG = registerSound("items.bottle_plug");

    public static final RegistryObject<SoundEvent> GRASSY_PERMAFROST_BREAK = registerSound("block.grassy_permafrost.break");
    public static final RegistryObject<SoundEvent> GRASSY_PERMAFROST_DIG = registerSound("block.grassy_permafrost.dig");

    public static final RegistryObject<SoundEvent> CRUMBLED_PERMAFROST_BREAK = registerSound("block.crumbled_permafrost.break");
    public static final RegistryObject<SoundEvent> CRUMBLED_PERMAFROST_DIG = registerSound("block.crumbled_permafrost.dig");

    public static final RegistryObject<SoundEvent> PERMAFROST_BREAK = registerSound("block.permafrost.break");
    public static final RegistryObject<SoundEvent> PERMAFROST_DIG = registerSound("block.permafrost.dig");

    public static final RegistryObject<SoundEvent> LUNIUM_ORE_BREAK = registerSound("block.lunium_ore.break");
    public static final RegistryObject<SoundEvent> LUNIUM_BLOCK_BREAK = registerSound("block.lunium_block.break");
    public static final RegistryObject<SoundEvent> LUNIUM_BLOCK_DIG = registerSound("block.lunium_block.dig");

    public static final RegistryObject<SoundEvent> SHADIUM_ORE_BREAK = registerSound("block.shadium_ore.break");
    public static final RegistryObject<SoundEvent> SHADIUM_BLOCK_BREAK = registerSound("block.shadium_block.break");
    public static final RegistryObject<SoundEvent> SHADIUM_BLOCK_DIG = registerSound("block.shadium_block.dig");

    public static final RegistryObject<SoundEvent> BRICK_BLOCK_BREAK = registerSound("block.brick.break");
    public static final RegistryObject<SoundEvent> BRICK_BLOCK_DIG = registerSound("block.brick.dig");

    public static final RegistryObject<SoundEvent> FUMICE_BLOCK_BREAK = registerSound("block.fumice.break");
    public static final RegistryObject<SoundEvent> FUMICE_BLOCK_DIG = registerSound("block.fumice.dig");

    public static final RegistryObject<SoundEvent> RUNE_ACTIVATE = registerSound("block.rune.activate");
    public static final RegistryObject<SoundEvent> ESSENCIA_ALTAR_ACTIVATE = registerSound("block.essencia_altar.activate");

    public static final RegistryObject<SoundEvent> CRYSTAL_APPLE_FREEZE = registerSound("items.crystal_apple.freeze");
    public static final RegistryObject<SoundEvent> CRYSTAL_APPLE_UNFREEZE = registerSound("items.crystal_apple.unfreeze");

    public static final RegistryObject<SoundEvent> STAFF_0 = registerSound("items.staff.staff_0");
    public static final RegistryObject<SoundEvent> LOOT = registerSound("items.loot");
    public static final RegistryObject<SoundEvent> MINERS_PEARL = registerSound("items.miners_pearl");

    public static final RegistryObject<SoundEvent> HONGO_AMBIENT = registerSound("entities.hongo_ambient");
    public static final RegistryObject<SoundEvent> HONGO_HURT = registerSound("entities.hongo_hurt");
    public static final RegistryObject<SoundEvent> HONGO_DEATH = registerSound("entities.hongo_death");
    public static final RegistryObject<SoundEvent> HONGO_SPORE_RELEASE = registerSound("entities.hongo_spore_release");

    public static final RegistryObject<SoundEvent> GLUMP_AMBIENT = registerSound("entities.glump_ambient");
    public static final RegistryObject<SoundEvent> GLUMP_HURT = registerSound("entities.glump_hurt");
    public static final RegistryObject<SoundEvent> GLUMP_DEATH = registerSound("entities.glump_death");

    public static final RegistryObject<SoundEvent> BLAZIER_IDLE = registerSound("entities.blazier_idle");
    public static final RegistryObject<SoundEvent> BLAZIER_HURT = registerSound("entities.blazier_hurt");
    public static final RegistryObject<SoundEvent> BLAZIER_DEATH = registerSound("entities.blazier_death");

    public static final RegistryObject<SoundEvent> CALCIA_IDLE = registerSound("entities.calcia");
    public static final RegistryObject<SoundEvent> CALCIA_HURT = registerSound("entities.calcia_hurt");

    public static final RegistryObject<SoundEvent> FROZEN_TROLL_AMBIENT = registerSound("entities.frozen_troll_ambient");
    public static final RegistryObject<SoundEvent> FROZEN_TROLL_HURT = registerSound("entities.frozen_troll_hurt");
    public static final RegistryObject<SoundEvent> FROZEN_TROLL_DEATH = registerSound("entities.frozen_troll_death");
    public static final RegistryObject<SoundEvent> FROZEN_TROLL_INTRIGUED = registerSound("entities.frozen_troll_intrigued");

    public static final RegistryObject<SoundEvent> FROZEN_GUARDIAN_DEATH = registerSound("entities.frozen_guardian_death");

    public static final RegistryObject<SoundEvent> ROCKITE_SMASHER_HURT = registerSound("entities.rockite_smasher_hurt");
    public static final RegistryObject<SoundEvent> ROCKITE_SMASHER_DEATH = registerSound("entities.rockite_smasher_death");

    public static final RegistryObject<SoundEvent> ILLAGER_MECH_STEP = registerSound("entities.illager_mech_step");
    public static final RegistryObject<SoundEvent> ILLAGER_MECH_HURT = registerSound("entities.illager_mech_hurt");
    public static final RegistryObject<SoundEvent> ILLAGER_MECH_DEATH = registerSound("entities.illager_mech_death");
    public static final RegistryObject<SoundEvent> ILLAGER_MECH_THROW = registerSound("entities.illager_mech_throw");

    public static final RegistryObject<SoundEvent> SENTRY_AMBIENT = registerSound("entities.sentry_ambient");

    public static final RegistryObject<SoundEvent> KNIFE = registerSound("entities.knife");
    public static final RegistryObject<SoundEvent> PIERCER = registerSound("entities.piercer");
    public static final RegistryObject<SoundEvent> PIERCER_RETURN = registerSound("entities.piercer_return");

    public static final RegistryObject<SoundEvent> MENU_MUSIC = registerSound("music.menu");
    public static final RegistryObject<SoundEvent> TOWER_THEME = registerSound("music.guardian_tower");
    public static final RegistryObject<SoundEvent> TEMPLE_GUARDIAN_MUSIC = registerSound("music.temple_guardian");
    public static final RegistryObject<SoundEvent> EUCA_AMBIENCE = registerSound("music.euca.ambience");
    public static final RegistryObject<SoundEvent> GOLD_PLAINS_MUSIC = registerSound("music.biome.gold_plains");

    public static final RegistryObject<SoundEvent> HAUNT_MUSKIE_2 = registerSound("music.haunt_muskie_2");
    public static final RegistryObject<SoundEvent> SNOWFLAKESSS = registerSound("music.snowflakesss");

    public static final RegistryObject<SoundEvent> UNDERWATER_WORLD = registerSound("music.record.underwater_world");
    public static final RegistryObject<SoundEvent> EUCA_DISC_1 = registerSound("music.record.euca_1");
    public static final RegistryObject<SoundEvent> EUCA_DISC_2 = registerSound("music.record.euca_2");
    public static final RegistryObject<SoundEvent> EUCA_DISC_3 = registerSound("music.record.euca_3");
    public static final RegistryObject<SoundEvent> FROZEN_DISC_1 = registerSound("music.record.frozen_1");
    public static final RegistryObject<SoundEvent> BOIL_DISC_1 = registerSound("music.record.boil_1");

    public static final RegistryObject<SoundEvent> BOSS_DEATH = registerSound("entities.boss.death");
    public static final RegistryObject<SoundEvent> COIN_PICKUP = registerSound("misc.coin_pickup");
    public static final RegistryObject<SoundEvent> CRYSTAL_ERROR = registerSound("misc.crystal_error");
    public static final RegistryObject<SoundEvent> CRYSTAL_PICKUP = registerSound("misc.crystal_pickup");

    //BELOW

    public static final RegistryObject<SoundEvent> ROCK = registerSound("rock");
    public static final RegistryObject<SoundEvent> CAVE_MOB = registerSound("cave_mob");
    public static final RegistryObject<SoundEvent> BASE_MOB_HURT = registerSound("base_mob_hurt");

    public static final RegistryObject<SoundEvent> TERRA_SLUG = registerSound("entities.terraslug");
    public static final RegistryObject<SoundEvent> TERRA_SLUG_HURT = registerSound("entities.terraslug_hurt");
    public static final RegistryObject<SoundEvent> TERRA_SLUG_DEATH = registerSound("entities.terraslug_death");

    public static final RegistryObject<SoundEvent> SORCERER_DEATH = registerSound("entities.sorcerer_death");
    public static final RegistryObject<SoundEvent> SORCERER_HURT = registerSound("entities.sorcerer_hurt");
    public static final RegistryObject<SoundEvent> SORCERER = registerSound("entities.sorcerer");

    public static final RegistryObject<SoundEvent> ROBOT = registerSound("entities.robot");
    public static final RegistryObject<SoundEvent> ROBOT_HURT = registerSound("entities.robot_hurt");
    public static final RegistryObject<SoundEvent> ROBOT_DEATH = registerSound("entities.robot_death");

    public static final RegistryObject<SoundEvent> SPIKED_BEAST = registerSound("entities.spiked_beast");
    public static final RegistryObject<SoundEvent> SPIKED_BEAST_HURT = registerSound("entities.spiked_beast_hurt");

    public static final RegistryObject<SoundEvent> SPYCLOPS = registerSound("entities.spyclops");
    public static final RegistryObject<SoundEvent> SPYCLOPS_HURT = registerSound("entities.spyclops_hurt");

    public static final RegistryObject<SoundEvent> TURTLE = registerSound("entities.turtle");
    public static final RegistryObject<SoundEvent> TURTLE_HURT = registerSound("entities.turtle_hurt");

    public static final RegistryObject<SoundEvent> SMALL_HONGO = registerSound("entities.small_hongo");
    public static final RegistryObject<SoundEvent> SMALL_HONGO_HURT = registerSound("entities.small_hongo_hurt");

    public static final RegistryObject<SoundEvent> REAPER = registerSound("entities.reaper");
    public static final RegistryObject<SoundEvent> REAPER_HURT = registerSound("entities.reaper_hurt");
    public static final RegistryObject<SoundEvent> SAND_CRAWLER = registerSound("entities.sand_crawler");
    public static final RegistryObject<SoundEvent> DEPTHS_HUNTER = registerSound("entities.depths_hunter");
    public static final RegistryObject<SoundEvent> DEPTHS_HUNTER_HURT = registerSound("entities.depths_hunter_hurt");

    public static final RegistryObject<SoundEvent> OKOLOO = registerSound("entities.okoloo");
    public static final RegistryObject<SoundEvent> OKOLOO_HURT = registerSound("entities.okoloo_hurt");

    public static final RegistryObject<SoundEvent> DYNASTER = registerSound("entities.dynaster");
    public static final RegistryObject<SoundEvent> DYNASTER_HURT = registerSound("entities.dynaster_hurt");
    public static final RegistryObject<SoundEvent> DYNASTER_DEATH = registerSound("entities.dynaster_death");
    public static final RegistryObject<SoundEvent> SHIMMERER = registerSound("entities.shimmerer");
    public static final RegistryObject<SoundEvent> SHIMMERER_HURT = registerSound("entities.shimmerer_hurt");
    public static final RegistryObject<SoundEvent> SHIMMERER_DEATH = registerSound("entities.shimmerer_death");
    public static final RegistryObject<SoundEvent> BUSH = registerSound("entities.bush");
    public static final RegistryObject<SoundEvent> BUSH_HURT = registerSound("entities.bush_hurt");
    public static final RegistryObject<SoundEvent> BUSH_DEATH = registerSound("entities.bush_death");
    public static final RegistryObject<SoundEvent> WRAITH = registerSound("entities.wraith");
    public static final RegistryObject<SoundEvent> WRAITH_DEATH = registerSound("entities.wraith_death");
    public static final RegistryObject<SoundEvent> WRAITH_HURT = registerSound("entities.wraith_hurt");
    public static final RegistryObject<SoundEvent> OVERSEER = registerSound("entities.overseer");
    public static final RegistryObject<SoundEvent> OVERSEER_HURT = registerSound("entities.overseer_hurt");
    public static final RegistryObject<SoundEvent> OVERSEER_DEATH = registerSound("entities.overseer_death");

    public static final RegistryObject<SoundEvent> STINKY = registerSound("entities.stinky");
    public static final RegistryObject<SoundEvent> STINKY_HURT = registerSound("entities.stinky_hurt");
    public static final RegistryObject<SoundEvent> STINKY_DEATH = registerSound("entities.stinky_death");

    public static final RegistryObject<SoundEvent> BIRD = registerSound("entities.bird");
    public static final RegistryObject<SoundEvent> BIRD_HURT = registerSound("entities.bird_hurt");
    public static final RegistryObject<SoundEvent> BIRD_DEATH = registerSound("entities.bird_death");

    public static final RegistryObject<SoundEvent> LAVASNAKE_IDLE = registerSound("entities.lavasnake_idle");
    public static final RegistryObject<SoundEvent> LAVASNAKE_HURT = registerSound("entities.lavasnake_hurt");

    public static final RegistryObject<SoundEvent> SENTRY_ALTAR_ACTIVATE = registerSound("misc.sentry_altar_activate");
    public static final RegistryObject<SoundEvent> SENTRY_ALTAR_DEACTIVATE = registerSound("misc.sentry_altar_deactivate");

    public static final RegistryObject<SoundEvent> OBELISK_IDLE = registerSound("misc.obelisk_idle");
    public static final RegistryObject<SoundEvent> OBELISK_OPEN = registerSound("misc.obelisk_open");

    private static RegistryObject<SoundEvent> registerSound(String name) {
        return REGISTRY.register(name, () -> createSoundEvent(name));
    }

    private static SoundEvent createSoundEvent(String soundPath) {
        return SoundEvent.createVariableRangeEvent(JITL.rl(soundPath));
    }
}