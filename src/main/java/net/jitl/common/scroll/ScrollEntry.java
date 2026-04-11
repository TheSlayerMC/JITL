package net.jitl.common.scroll;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ScrollEntry {

    private final String id;
    private final String titleKey;

    @Nullable
    private final String commentKey;

    private final List<IDescComponent> desc = new ArrayList<>();
    private final int x;
    private final int y;

    public ScrollEntry(String id, String titleKey, @Nullable String commentKey, List<IDescComponent> desc, int x, int y) {
        this.x = x;
        this.y = y;
        this.titleKey = titleKey;
        this.commentKey = commentKey;

        this.desc.add(new UnderHeaderDescComponent());
        this.desc.addAll(desc);

        this.id = Objects.requireNonNullElseGet(id, () -> this.titleKey.toLowerCase().replace(' ', '_'));
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