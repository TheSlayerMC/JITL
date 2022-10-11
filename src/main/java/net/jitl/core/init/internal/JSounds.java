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

    private static RegistryObject<SoundEvent> registerSound(String name) {
        return REGISTRY.register(name, () -> new SoundEvent(JITL.rl(name)));
    }
}