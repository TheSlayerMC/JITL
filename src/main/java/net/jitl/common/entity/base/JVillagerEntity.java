package net.jitl.common.entity.base;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.npc.Npc;
import net.minecraft.world.entity.npc.villager.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;

public abstract class JVillagerEntity extends JVillagerMob implements Npc, Merchant, Enemy, GeoEntity {

    private Player playerEntity;
    protected MerchantOffers offers;
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public JVillagerEntity(EntityType<? extends JVillagerMob> type, Level worldIn) {
        super(type, worldIn);
    }

    @Override
    public void setTradingPlayer(@Nullable Player player) {
        playerEntity = player;
    }

    @Nullable
    @Override
    public Player getTradingPlayer() {
        return playerEntity;
    }

    protected abstract void controller(AnimatableManager.ControllerRegistrar controllers);

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controller(controllers);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Nullable
    protected abstract Int2ObjectMap<VillagerTrades.ItemListing[]> getVillagerTrades();

    @Override
    protected void updateTrades(ServerLevel serverLevel) {

    }
//
//    protected void addTrades(ServerLevel level, MerchantOffers offers, VillagerTrades.ItemListing[] trades) {
//        Set<Integer> set = Sets.newHashSet();
//        for (int i = 0; i < trades.length; ++i) {
//            set.add(i);
//        }
//        for (int int1 : set) {
//            VillagerTrades.ItemListing villagerTrades = trades[int1];
//            MerchantOffer merchantoffer = villagerTrades.getOffer(level, this, random);
//            if (merchantoffer != null) {
//                offers.add(merchantoffer);
//            }
//        }
//    }

    protected void addOffersFromItemListings(ServerLevel p_480295_, MerchantOffers p_479439_, VillagerTrades.ItemListing[] p_479360_, int p_481834_) {
        ArrayList<VillagerTrades.ItemListing> arraylist = Lists.newArrayList(p_479360_);
        int i = 0;

        while (i < p_481834_ && !arraylist.isEmpty()) {
            MerchantOffer merchantoffer = arraylist.remove(this.random.nextInt(arraylist.size())).getOffer(p_480295_, this, this.random);
            if (merchantoffer != null) {
                p_479439_.add(merchantoffer);
                i++;
            }
        }
    }

    @Override
    public void overrideOffers(@Nullable MerchantOffers offers) { }

    @Override
    public void notifyTrade(MerchantOffer offer) {
        offer.increaseUses();
        if (offer.shouldRewardExp()) {
            int i = 3 + random.nextInt(4);
            double y = getY() + getBbHeight() / 2;
            level().addFreshEntity(new ExperienceOrb(level(), getX(), y, getZ(), i));
        }
    }


    @Override
    public @NotNull InteractionResult mobInteract(@NotNull Player playerEntity, @NotNull InteractionHand playerHand) {
        if(isAlive() && this.playerEntity == null && canTrade()) {
            startTrading(playerEntity);
        } else {
            return super.mobInteract(playerEntity, playerHand);
        }
        return super.mobInteract(playerEntity, playerHand);
    }

    public void startTrading(Player player) {
        this.setTradingPlayer(player);
        this.openTradingScreen(player, this.getDisplayName(), 1);
    }


    public boolean canTrade() {
        return true;
    }

    @Override
    public void notifyTradeUpdated(@NotNull ItemStack stack) { }

    public @NotNull Level getLevel() {
        return level();
    }

    @Override
    public int getVillagerXp() {
        return 0;
    }

    @Override
    public void overrideXp(int xpIn) { }

    @Override
    public boolean showProgressBar() {
        return false;
    }

    @Override
    public SoundEvent getNotifyTradeSound() {
        return null;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public boolean isClientSide() {
        return this.getLevel().isClientSide();
    }
}