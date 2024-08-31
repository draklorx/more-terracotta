package net.fabricmc.example;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;

public class ModBlocks {

    public static String[] dye_colours = new String[] {"white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "light_gray", "cyan", "purple", "blue", "brown", "green", "red", "black"};
    private static final AbstractBlock.Settings concreteSettings = AbstractBlock.Settings.copy(Blocks.WHITE_CONCRETE).requiresTool();
    private static final AbstractBlock.Settings terracottaSettings = AbstractBlock.Settings.copy(Blocks.TERRACOTTA).requiresTool();

    public static void registerBlock(String name, Block block, RegistryKey<ItemGroup> blockGroup){
        registerBlockItem(name, block, blockGroup);
        Registry.register(Registries.BLOCK, Identifier.of("moreterracotta", name), block);
    }

    public static void registerBlockItem(String name, Block block, RegistryKey<ItemGroup> blockGroup){

        BlockItem blockItem =  new BlockItem(block, new Item.Settings());

        Registry.register(Registries.ITEM, Identifier.of("moreterracotta", name), blockItem);

        ItemGroupEvents.modifyEntriesEvent(blockGroup).register(content -> content.add(blockItem));
    }

    public static void generateSlabs(String[] dye_colours){
        for(String colour:dye_colours) {
            String terracotta_name = colour + "_terracotta_slab";
            registerBlock(terracotta_name, new SlabBlock(terracottaSettings), ModItemGroups.TERRACOTTA_GROUP);

            String concrete_name = colour + "_concrete_slab";
            registerBlock(concrete_name, new SlabBlock(concreteSettings), ModItemGroups.CONCRETE_GROUP);
        }
        registerBlock("terracotta_slab", new SlabBlock(terracottaSettings), ModItemGroups.TERRACOTTA_GROUP);
    }

    public static void generateStairs(String[] dye_colours){
        for(String colour:dye_colours) {
            String terracotta_name = colour + "_terracotta_stairs";
            registerBlock(terracotta_name, new StairsBlock(Blocks.TERRACOTTA.getDefaultState(),
                    terracottaSettings), ModItemGroups.TERRACOTTA_GROUP);

            String concrete_name = colour + "_concrete_stairs";
            registerBlock(concrete_name, new StairsBlock(Blocks.WHITE_CONCRETE.getDefaultState(),
                    concreteSettings), ModItemGroups.CONCRETE_GROUP);
        }
        registerBlock("terracotta_stairs", new StairsBlock(Blocks.TERRACOTTA.getDefaultState(),
                terracottaSettings), ModItemGroups.TERRACOTTA_GROUP);
    }

    public static void generateWalls(String[] dye_colours){

        for(String colour:dye_colours) {
            String terracotta_name = colour + "_terracotta_wall";
            registerBlock(terracotta_name, new WallBlock(
                    terracottaSettings), ModItemGroups.TERRACOTTA_GROUP);

            String concrete_name = colour + "_concrete_wall";
            registerBlock(concrete_name, new WallBlock(
                    concreteSettings), ModItemGroups.CONCRETE_GROUP);
        }

        registerBlock("terracotta_wall", new WallBlock(terracottaSettings), ModItemGroups.TERRACOTTA_GROUP);
    }

    public static void registerModBlocks(){

        generateSlabs(dye_colours);
        generateStairs(dye_colours);
        generateWalls(dye_colours);

    }
}
