package net.jitl.core.data;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class JItemModelProvider extends ItemModelProvider {

    public JItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, JITL.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(JItems.SAPPHIRE);
    }

    public ItemModelBuilder basicItem(RegistryObject<Item> item) {
        return getBuilder(item.getId().getPath())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(JITL.MODID, "item/" + item.getId().getPath()));
    }

    public ItemModelBuilder handheld(RegistryObject<Item> item) {
        return getBuilder(item.getId().getPath())
                .parent(new ModelFile.UncheckedModelFile("item/handheld"))
                .texture("layer0", new ResourceLocation(JITL.MODID, "item/" + item.getId().getPath()));
    }
}
