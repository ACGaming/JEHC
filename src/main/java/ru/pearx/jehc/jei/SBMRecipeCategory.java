package ru.pearx.jehc.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeCategory;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;

/**
 * Created by mrAppleXZ on 20.05.17 23:54.
 */
public class SBMRecipeCategory extends BlankRecipeCategory<BlankRecipeWrapper>
{
    public final String uid;
    private final String title;
    private final IDrawable bg;

    public SBMRecipeCategory(String unloc, String uid, IGuiHelper helper, String png)
    {
        this.uid = uid;
        title = I18n.translateToLocalFormatted(unloc);
        bg = helper.createDrawable(new ResourceLocation("harvestcraft", "textures/gui/" + png + ".png"), 32, 0, 112, 76);
    }

    @Override
    public String getUid()
    {
        return uid;
    }

    @Override
    public String getTitle()
    {
        return title;
    }

    @Override
    public IDrawable getBackground()
    {
        return bg;
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, BlankRecipeWrapper recipeWrapper, IIngredients ingredients)
    {
        recipeLayout.getItemStacks().init(0, false, 40, 25);
        recipeLayout.getItemStacks().set(0, ingredients.getOutputs(ItemStack.class).get(0));
        recipeLayout.getItemStacks().init(1, true, 80, 47);
        recipeLayout.getItemStacks().set(1, ingredients.getInputs(ItemStack.class).get(0));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void drawExtras(Minecraft minecraft)
    {
        String s = I18n.translateToLocalFormatted("jehc.price");
        int width = minecraft.fontRendererObj.getStringWidth(s);
        minecraft.fontRendererObj.drawString(s, 67 + ((37 - width) / 2), 25 + ((18 - minecraft.fontRendererObj.FONT_HEIGHT) / 2), Color.BLACK.getRGB());
    }
}
