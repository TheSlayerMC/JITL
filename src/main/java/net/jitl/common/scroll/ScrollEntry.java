package net.jitl.common.scroll;

import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ScrollEntry {

    private final ItemStack displayedItem;
    private final String id;
    private final String titleKey;

    @Nullable
    private final String commentKey;

    private final List<IDescComponent> desc = new ArrayList<>();
    private final int x;
    private final int y;

    public ScrollEntry(String id, String titleKey, @Nullable String commentKey, ItemStack displayedItem, List<IDescComponent> desc, int x, int y) {
        this.displayedItem = displayedItem;
        this.x = x;
        this.y = y;
        this.titleKey = titleKey;
        this.commentKey = commentKey;

        this.desc.add(new UnderHeaderDescComponent());
        this.desc.addAll(desc);

        if (id == null) {
            this.id = this.titleKey.toLowerCase().replace(' ', '_');
        } else {
            this.id = id;
        }
    }

    public ItemStack getDisplayedItem() {
        return displayedItem;
    }

    public String getId() {
        return id;
    }

    public String getTitleKey() {
        return titleKey;
    }

    public List<IDescComponent> getDesc() {
        return desc;
    }

    public void addDescComponent(IDescComponent descComponent) {
        desc.add(descComponent);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public @Nullable String getCommentKey() {
        return commentKey;
    }

    public boolean hasComment() {
        return commentKey != null;
    }

}