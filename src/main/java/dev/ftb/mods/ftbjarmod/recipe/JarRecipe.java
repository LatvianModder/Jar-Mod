package dev.ftb.mods.ftbjarmod.recipe;

import dev.ftb.mods.ftbjarmod.heat.Temperature;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LatvianModder
 */
public class JarRecipe implements Recipe<NoInventory> {
	private final ResourceLocation id;
	private final String group;
	public Temperature temperature;
	public int time;
	public final List<ItemIngredientPair> inputItems;
	public final List<FluidStack> inputFluids;
	public final List<ItemStack> outputItems;
	public final List<FluidStack> outputFluids;
	public boolean canRepeat;

	public JarRecipe(ResourceLocation i, String g) {
		id = i;
		group = g;
		temperature = Temperature.NONE;
		time = 200;
		inputItems = new ArrayList<>();
		inputFluids = new ArrayList<>();
		outputItems = new ArrayList<>();
		outputFluids = new ArrayList<>();
		canRepeat = true;
	}

	@Override
	public boolean matches(NoInventory inv, Level world) {
		return true;
	}

	@Override
	public ItemStack assemble(NoInventory inv) {
		return ItemStack.EMPTY;
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return true;
	}

	@Override
	public ItemStack getResultItem() {
		return ItemStack.EMPTY;
	}

	@Override
	public ResourceLocation getId() {
		return id;
	}

	@Override
	public String getGroup() {
		return group;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return FTBJarModRecipeSerializers.JAR.get();
	}

	@Override
	public RecipeType<?> getType() {
		return FTBJarModRecipeSerializers.JAR_TYPE;
	}

	public boolean isAvailableFor(Player player) {
		return true;
	}

	public boolean hasItems() {
		return !inputItems.isEmpty() || !outputItems.isEmpty();
	}

	public boolean hasFluids() {
		return !inputFluids.isEmpty() || !outputFluids.isEmpty();
	}
}