package net.jitl.common.entity.boss;

import net.jitl.core.data.loot.LootHelper;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;

public class BossCrystal extends Entity implements IEntityAdditionalSpawnData {
    
    private final NonNullList<ItemStack> storedItems = NonNullList.create();
    private Type type;

    public BossCrystal(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void defineSynchedData() { }

    public static BossCrystal create(Level world, BlockPos pos, Type type, List<ItemStack> items) {
        BossCrystal crystal = new BossCrystal(JEntities.BOSS_CRYSTAL_TYPE.get(), world);
        crystal.storedItems.addAll(items);
        crystal.setPos(pos.getX(), pos.getX(), pos.getZ());
        crystal.type = type;
        return crystal;
    }

    public static BossCrystal create(ServerLevel world, BlockPos pos, Type type, @Nullable ServerEntity player, ResourceLocation lootTable, long lootTableSeed) {
        return create(world, pos, type, genItemList(world, player, lootTable, lootTableSeed));
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {
        ContainerHelper.loadAllItems(compound, storedItems);
        type = getTypeFromIndex(compound.getInt("type"));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {
        ContainerHelper.saveAllItems(compound, storedItems);
        compound.putInt("type", type.ordinal());
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        if (!this.level.isClientSide) {
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

    public static List<ItemStack> genItemList(ServerLevel world, @Nullable ServerEntity player, ResourceLocation lootTable, long lootTableSeed) {
        RandomSource random = RandomSource.create();

        return LootHelper.genFromLootTable(lootTable, world, random, builder -> {
            if (player != null) {
                builder.withLuck(1);
            }
        });
    }

    public ResourceLocation getTexture() {
        return type.getTexture();
    }

    private Type getTypeFromIndex(int index) {
        Type[] types = Type.values();
        return types[index % types.length];
    }

    @Override
    public void writeSpawnData(FriendlyByteBuf buffer) {
        buffer.writeInt(type.ordinal());
    }

    @Override
    public void readSpawnData(FriendlyByteBuf buffer) {
        this.type = getTypeFromIndex(buffer.readInt());
    }

    public enum Type {
        COMMON(JITL.rl("textures/entity/crystal/common.png")),
        NETHER(JITL.rl("textures/entity/crystal/nether.png")),
        BOIL(JITL.rl("textures/entity/crystal/boil.png")),
        EUCA(JITL.rl("textures/entity/crystal/euca.png")),
        DEPTHS(JITL.rl("textures/entity/crystal/depths.png")),
        CORBA(JITL.rl("textures/entity/crystal/corba.png")),
        TERRANIA(JITL.rl("textures/entity/crystal/terrania.png")),
        CLOUDIA(JITL.rl("textures/entity/crystal/cloudia.png")),
        SENTERIAN(JITL.rl("textures/entity/crystal/senterian.png")),
        FROZEN(JITL.rl("textures/entity/crystal/frozen.png"));

        private final ResourceLocation crystalTexture;

        Type(ResourceLocation crystalTexture) {
            this.crystalTexture = crystalTexture;
        }

        public ResourceLocation getTexture() {
            return crystalTexture;
        }
    }
}