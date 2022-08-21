package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoreTerracotta implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("modid");

	// Register Our Item Groups
	public static final ItemGroup TERRACOTTA_GROUP = FabricItemGroupBuilder.build(
			new Identifier("moreterracotta", "terracotta_group"),
			() -> new ItemStack(Blocks.TERRACOTTA));

	// Register Our Item Groups
	public static final ItemGroup CONCRETE_GROUP = FabricItemGroupBuilder.build(
			new Identifier("moreterracotta", "concrete_group"),
			() -> new ItemStack(Blocks.WHITE_CONCRETE));

	public static void registerBlock(String name, Block block, ItemGroup blockGroup){
		registerBlockItem(name, block, blockGroup);
		Registry.register(Registry.BLOCK, new Identifier("moreterracotta", name), block);
	}

	public static void registerBlockItem(String name, Block block, ItemGroup blockGroup){
		Registry.register(Registry.ITEM, new Identifier("moreterracotta", name),
				new BlockItem(block, new FabricItemSettings().group(blockGroup)));
	}

	public String[] dye_colours = new String[] {"white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "light_gray", "cyan", "purple", "blue", "brown", "green", "red", "black"};

	@Override
	public void onInitialize() {

		//generate slabs
		for(String colour:dye_colours) {
			String terracotta_name = colour + "_terracotta_slab";
			registerBlock(terracotta_name, new SlabBlock(FabricBlockSettings.of(Material.STONE).strength(4.0f)), MoreTerracotta.TERRACOTTA_GROUP);

			String concrete_name = colour + "_concrete_slab";
			registerBlock(concrete_name, new SlabBlock(FabricBlockSettings.of(Material.STONE).strength(4.0f)), MoreTerracotta.CONCRETE_GROUP);
		}
		registerBlock("terracotta_slab", new SlabBlock(FabricBlockSettings.of(Material.STONE).strength(4.0f)), MoreTerracotta.TERRACOTTA_GROUP);

		//generate stairs

		for(String colour:dye_colours) {
			String terracotta_name = colour + "_terracotta_stairs";
			registerBlock(terracotta_name, new StairsBlock(Blocks.TERRACOTTA.getDefaultState(),
					FabricBlockSettings.of(Material.STONE).strength(4.0f)), MoreTerracotta.TERRACOTTA_GROUP);

			String concrete_name = colour + "_concrete_stairs";
			registerBlock(concrete_name, new StairsBlock(Blocks.WHITE_CONCRETE.getDefaultState(),
					FabricBlockSettings.of(Material.STONE).strength(4.0f)), MoreTerracotta.CONCRETE_GROUP);
		}
		registerBlock("terracotta_stairs", new StairsBlock(Blocks.TERRACOTTA.getDefaultState(),
				FabricBlockSettings.of(Material.STONE).strength(4.0f)), MoreTerracotta.TERRACOTTA_GROUP);

	}
}
