package net.jitl.common.entity.jmerchent;

import net.jitl.client.stats.ClientPlayerStats;
import net.jitl.common.capability.stats.PlayerStats;
import net.jitl.core.init.internal.JDataAttachments;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.BeaconMenu;
import net.minecraft.world.item.ItemStack;

public class SentacoinMerchantOffer {

    private final int sentacoin_cost;
    private final ItemStack result;

    public SentacoinMerchantOffer(int coins, ItemStack result) {
        this.sentacoin_cost = coins;
        this.result = result;
    }

    public SentacoinMerchantOffer(CompoundTag tag) {
        this.sentacoin_cost = tag.getInt("sentacoin_cost");
        this.result = ItemStack.of(tag.getCompound("result"));
    }

    private SentacoinMerchantOffer(SentacoinMerchantOffer other) {
        this.sentacoin_cost = other.sentacoin_cost;
        this.result = other.result;
    }

    public int getSentacoinCost() {
        return this.sentacoin_cost;
    }

    public ItemStack getResult() {
        return this.result;
    }

    public ItemStack assemble() {
        return this.result.copy();
    }

    public CompoundTag createTag() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("sentacoin_cost", this.sentacoin_cost);
        tag.put("result", this.result.save(new CompoundTag()));
        return tag;
    }

    public boolean satisfiedBy() {
        return ClientPlayerStats.getSentacoins() >= sentacoin_cost;
    }

    public SentacoinMerchantOffer copy() {
        return new SentacoinMerchantOffer(this);
    }

    public boolean take(Player player) {
        BeaconMenu
        PlayerStats stats = player.getData(JDataAttachments.PLAYER_STATS);
        if(stats.getSentacoins() >= this.sentacoin_cost) {
            stats.useSentacoins(this.sentacoin_cost);
            stats.update(player);
            System.out.println("TAKEN: " + stats.getSentacoins());
            return true;
        } else {
            return false;
        }
    }
}
