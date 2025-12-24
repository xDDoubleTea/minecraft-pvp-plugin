package me.practice.practice_server.model;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import java.util.ArrayList;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.Color;

public class BridgeKit {
  private int ironSwordSlot;
  private int[] blockSlot;
  private int goldenAppleSlot;
  private int bowSlot;
  private int arrowSlot;
  private int pickaxeSlot;

  public ArrayList<ItemStack> getItemData(int color_code) {
    ArrayList<ItemStack> itemData = new ArrayList<>();

    ItemStack ironSword = new ItemStack(Material.IRON_SWORD);
    ItemMeta ironSwordMeta = ironSword.getItemMeta();
    ironSwordMeta.spigot().setUnbreakable(true);
    ironSword.setItemMeta(ironSwordMeta);
    itemData.add(ironSword);

    ItemStack blocks;
    if (color_code == 0) {
      // Blue
      blocks = new ItemStack(Material.STAINED_CLAY, 64, (byte) 11);
    } else {
      // Red
      blocks = new ItemStack(Material.STAINED_CLAY, 64, (byte) 14);
    }

    ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
    ItemMeta pickaxeMeta = pickaxe.getItemMeta();
    pickaxeMeta.addEnchant(Enchantment.DIG_SPEED, 2, true);
    pickaxeMeta.spigot().setUnbreakable(true);
    pickaxe.setItemMeta(pickaxeMeta);

    ItemStack goldenApple = new ItemStack(Material.GOLDEN_APPLE, 8);

    ItemStack bow = new ItemStack(Material.BOW);
    ItemMeta bowMeta = bow.getItemMeta();
    bowMeta.spigot().setUnbreakable(true);
    bow.setItemMeta(bowMeta);

    ItemStack arrow = new ItemStack(Material.ARROW, 1);

    itemData.add(blocks);
    itemData.add(pickaxe);
    itemData.add(bow);
    itemData.add(goldenApple);
    itemData.add(arrow);

    return itemData;
  }

  public ItemStack[] getArmor(int color) {
    Color colorcode = Color.WHITE;
    if (color == 0) {
      colorcode = Color.BLUE;
    } else if (color == 1) {
      colorcode = Color.RED;
    }
    ItemStack[] armor = {
        new ItemStack(Material.LEATHER_BOOTS, 1),
        new ItemStack(Material.LEATHER_LEGGINGS, 1),
        new ItemStack(Material.LEATHER_CHESTPLATE, 1)

    };

    ArrayList<LeatherArmorMeta> armor_meta = new ArrayList<LeatherArmorMeta>();
    for (int i = 0; i < 3; i++) {
      armor_meta.add((LeatherArmorMeta) armor[i].getItemMeta());
    }

    for (int i = 0; i < 3; i++) {
      armor_meta.get(i).spigot().setUnbreakable(true);
      armor_meta.get(i).setColor(colorcode);
      armor[i].setItemMeta(armor_meta.get(i));
    }
    return armor;
  }

  public int getIronSwordSlot() {
    return ironSwordSlot;
  }

  public int[] getBlockSlot() {
    return blockSlot;
  }

  public int getGoldenAppleSlot() {
    return goldenAppleSlot;
  }

  public int getBowSlot() {
    return bowSlot;
  }

  public int getArrowSlot() {
    return arrowSlot;
  }

  public int getPickaxeSlot() {
    return pickaxeSlot;
  }

  public void setIronSwordSlot(int ironSwordSlot) {
    this.ironSwordSlot = ironSwordSlot;
  }

  public void setBlockSlot(int[] blockSlot) {
    this.blockSlot = blockSlot;
  }

  public void setGoldenAppleSlot(int goldenAppleSlot) {
    this.goldenAppleSlot = goldenAppleSlot;
  }

  public void setBowSlot(int bowSlot) {
    this.bowSlot = bowSlot;
  }

  public void setArrowSlot(int arrowSlot) {
    this.arrowSlot = arrowSlot;
  }

  public void setPickaxeSlot(int pickaxeSlot) {
    this.pickaxeSlot = pickaxeSlot;
  }

  public void setItemData(int ironSwordSlot, int[] blockSlot, int pickaxeSlot, int goldenAppleSlot, int arrowSlot,
      int bowSlot) {
    // Sets the slots of the items.
    this.setArrowSlot(arrowSlot);
    this.setBlockSlot(blockSlot);
    this.setPickaxeSlot(pickaxeSlot);
    this.setBowSlot(bowSlot);
    this.setIronSwordSlot(ironSwordSlot);
    this.setGoldenAppleSlot(goldenAppleSlot);
  }

}
