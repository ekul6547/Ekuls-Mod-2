package com.ekul.ekulMod2.worldGen;

import java.util.Random;

import com.ekul.ekulMod2.main.EkulBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class EkulGenOreGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		switch (world.provider.getDimension()) {
		case 1:
			generateEnd(world, random, chunkX,chunkZ);
			break;
		case 0:
			generateOverworld(world, random, chunkX,chunkZ);
			break;
		case -1:
			generateNether(world, random, chunkX,chunkZ);
			break;
		}
	}

	public void generateEnd(World world, Random rand, int x, int z) {

	}

	public void generateOverworld(World world, Random rand, int x, int z) {
		generateOre(EkulBlocks.block_ekuliumOre,world,rand,x,z,3,10,70, 32, 128, Blocks.STONE);
		generateOre(EkulBlocks.block_runicOre,world,rand,x,z,3,4,200, 0, 128, Blocks.GRAVEL);
	}

	public void generateNether(World world, Random rand, int x, int z) {
		generateOre(EkulBlocks.block_netherEkuliumOre,world,rand,x,z,4,16,50, 32, 128, Blocks.NETHERRACK);
		generateOre(EkulBlocks.block_netherRunicOre,world,rand,x,z,4,16,50, 0, 128, Blocks.SOUL_SAND);
		generateOre(EkulBlocks.block_runicOre,world,rand,x,z,1,4,10, 0, 64, Blocks.GRAVEL);
	}

	public void generateOre(Block block, World world, Random random, int chunkX, int chunkZ, int MinVienSize, int MaxVienSize, int chance, int minY, int maxY, Block generateIn){
		int vienSize = MinVienSize + random.nextInt(MaxVienSize-MinVienSize);
		int heightRange = maxY-minY;
		WorldGenMinable gen = new WorldGenMinable(block.getDefaultState(),vienSize, BlockMatcher.forBlock(generateIn));
		for(int i = 0; i < chance; i++){
			int xRand = chunkX*16+random.nextInt(16);
			int yRand = random.nextInt(heightRange)+minY;
			int zRand = chunkZ*16+random.nextInt(16);
			gen.generate(world, random, new BlockPos(xRand, yRand, zRand));
		}
	}

}
