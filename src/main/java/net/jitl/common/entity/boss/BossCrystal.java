package net.jitl.common.entity.boss;

import net.jitl.core.init.JITL;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Iterator;
import java.util.List;

public class BossCrystal extends Entity implements IEntityAdditionalSpawnData, GeoEntity {
    
    private final NonNullList<ItemStack> storedItems = NonNullList.create();
    private String type;
    private ResourceLocation table;

    public BossCrystal(EntityType<? extends BossCrystal> pEntityType, Level pLevel, Type t, ResourceLocation loot) {
        this(pEntityType, pLevel);
        setType(t);
        setLootTable(loot);
    }

    public BossCrystal(EntityType<? extends BossCrystal> entityEntityType, Level level) {
        super(entityEntityType, level);
    }

    @Override
    protected void defineSynchedData() { }

    public void setType(Type type) {
        this.type = type.getName();
    }

    public void setLootTable(ResourceLocation table) {
        this.table = table;
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {
        ContainerHelper.loadAllItems(compound, storedItems);
        type = compound.getString("type");
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {
        ContainerHelper.saveAllItems(compound, storedItems);
        compound.putString("type", type);
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        if (!this.level.isClientSide) {

            LootTables ltManager = this.getLevel().getServer().getLootTables();
            LootTable lt = ltManager.get(table);
            ItemStack sword = new ItemStack(Items.DIAMOND_SWORD);
            sword.enchant(Enchantments.MOB_LOOTING, 3);
            sword.enchant(Enchantments.FIRE_ASPECT, 1);
            Vec3 position = new Vec3(getX(), getY(), getZ());
            LootContext.Builder builder = (new LootContext.Builder((ServerLevel) this.level))
                    .withParameter(LootContextParams.TOOL, sword)
                    .withParameter(LootContextParams.ORIGIN, position);
            LootContext ctx = builder.create(LootContextParamSets.FISHING);
            List<ItemStack> generated = lt.getRandomItems(ctx);
            storedItems.addAll(generated);

            for (Iterator<ItemStack> iterator = storedItems.iterator(); iterator.hasNext(); ) {
                ItemStack itemStack = iterator.next();
                if (player.addItem(itemStack)) {
                    iterator.remove();
                } else {
                    //ChatUtils.sendColored(player, TextFormatting.RED, new TextComponentTranslation("msg.journey.boss_crystal.not_enough_space"));
                    //playSound(JSounds.CRYSTAL_ERROR, 1.0F, 1.0F);
                    break;
                }
            }

            if (storedItems.isEmpty()) {
                //playSound(JourneySounds.CRYSTAL_ALL_RETRIEVED, 1.0F, 1.0F);
                remove(RemovalReason.DISCARDED);
            }
        }
        return InteractionResult.SUCCESS;
    }

    public ResourceLocation getTexture() {
        return JITL.rl("textures/entity/crystal/" + type + ".png");
    }

    @Override
    public void writeSpawnData(FriendlyByteBuf buffer) {
        buffer.writeUtf(type);
    }

    @Override
    public void readSpawnData(FriendlyByteBuf buffer) {
        //this.type = buffer.readUtf();
    }

    private final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.bosscrystal.idle");
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 5, state -> state.setAndContinue(IDLE)));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    public enum Type {
        COMMON("common"),
        NETHER("nether"),
        BOIL("boil"),
        EUCA("euca"),
        DEPTHS("depths"),
        CORBA("corba"),
        TERRANIA("terrania"),
        CLOUDIA("cloudia"),
        SENTERIAN("senterian"),
        FROZEN("frozen");

        private final String name;

        Type(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}