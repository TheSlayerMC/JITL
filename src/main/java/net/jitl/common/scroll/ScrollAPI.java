package net.jitl.common.scroll;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ScrollAPI {

    private static final Map<String, ScrollCategory> CATEGORY_MAP = new LinkedHashMap<>();
    private static final Map<String, ScrollEntry> ENTRY_MAP = new HashMap<>();

    public static void registerCategory(ScrollCategory scrollCategory) {
        CATEGORY_MAP.put(scrollCategory.getCategoryKey(), scrollCategory);
    }

    public static ScrollEntry registerEntry(String existentCategoryName, ScrollEntry scrollEntry) {
        if (existentCategoryName == null || !CATEGORY_MAP.containsKey(existentCategoryName)) {
            throw new IndexOutOfBoundsException("Attempt to register Scroll Entry \"" + scrollEntry.getId() + "\" to nonexistent Category \"" + existentCategoryName + "\".\n\tAvailable Category Names: " + CATEGORY_MAP.toString());
        }

        CATEGORY_MAP.get(existentCategoryName).addEntryToCategory(scrollEntry);

        if (ENTRY_MAP.put(scrollEntry.getId(), scrollEntry) != null) {
            throw new IllegalStateException("Scroll Entry with id " + scrollEntry.getId() + " already exists!");
        }

        return scrollEntry;
    }

    @Nullable
    public static ScrollEntry getEntry(String id) {
        return ENTRY_MAP.get(id);
    }

}