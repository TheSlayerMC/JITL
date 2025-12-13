package net.jitl.common.scroll;

import net.minecraft.resources.Identifier;

import java.util.LinkedHashMap;

public class ScrollCategory {

    private final int categorySize;
    private final String categoryKey;
    private final Identifier backgroundTexture;
    private final String id;

    private final LinkedHashMap<String, ScrollEntry> entryList = new LinkedHashMap<>();

    public ScrollCategory(String categoryKey, Identifier backgroundTexture) {
        this(categoryKey, backgroundTexture, 2);
    }


    public ScrollCategory(String categoryKey, Identifier backgroundTexture, int categorySize) {
        this.categorySize = categorySize;
        this.categoryKey = categoryKey;
        this.backgroundTexture = backgroundTexture;
        this.id = this.categoryKey.toUpperCase().replace(' ', '_');
    }

    public int getCategorySize() {
        return categorySize;
    }

    public String getCategoryKey() {
        return categoryKey;
    }

    public LinkedHashMap<String, ScrollEntry> getEntryList() {
        return entryList;
    }

    public void addEntryToCategory(ScrollEntry ScrollEntry) {
        entryList.put(ScrollEntry.getId(), ScrollEntry);
    }

    public ScrollEntry getEntry(String entryID) {
        if (!entryList.containsKey(entryID)) {
            throw new IndexOutOfBoundsException("Attempt to get nonexistent Entry \"" + entryID + "\" in \"" + categoryKey + "\" Category.\n\tAvailable Entry Names: " + entryList.toString());
        }
        return entryList.get(entryID);
    }

    public Identifier getBackgroundTexture() {
        return backgroundTexture;
    }

    public String getId() {
        return id;
    }

    public int getEntryCount() {
        return entryList.size();
    }
}