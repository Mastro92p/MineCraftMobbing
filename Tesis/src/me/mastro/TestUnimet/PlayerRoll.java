package me.mastro.TestUnimet;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class PlayerRoll {
	
	public String Roll;
	public ItemStack item;
	public String Rank;
	public List<ItemStack> itemList;
	
	public PlayerRoll(){
		
	}
	
	public PlayerRoll(String roll, ItemStack item, String rank, TYPE type) {
		super();
		this.Roll = roll;
		this.item = item;
		this.Rank = rank;
		createItemList(type);
		
	}
	
	public PlayerRoll(String roll, ItemStack item, String rank) {
		super();
		this.Roll = roll;
		this.item = item;
		this.Rank = rank;
	}
	
	public PlayerRoll(String roll, TYPE type, String rank) {
		super();
		this.Roll = roll;
		this.Rank = rank;
		itemList = new ArrayList<ItemStack>();
		this.createItemList(type);
		
	}
	
	public void createItemList(TYPE type){
		
		switch(type){
			case CAPTAIN:
				itemList.add(new ItemStack(Material.IRON_SWORD,1));
				itemList.add(new ItemStack(Material.CHAINMAIL_BOOTS,1));
				itemList.add(new ItemStack(Material.CHAINMAIL_HELMET,1));
				itemList.add(new ItemStack(Material.CHAINMAIL_CHESTPLATE,1));
				itemList.add(new ItemStack(Material.CHAINMAIL_LEGGINGS,1));
				itemList.add(new ItemStack(Material.WATCH,1));
				itemList.add(new ItemStack(Material.COMPASS, 2));
				itemList.add(new ItemStack(Material.IRON_INGOT,10));
				itemList.add(new ItemStack(Material.WHEAT,64));
				itemList.add(new ItemStack(Material.STICK,32));
				itemList.add(new ItemStack(Material.COAL,32));
				itemList.add(new ItemStack(Material.EMPTY_MAP,1));
				break;
			case HEALER:
				itemList.add(new ItemStack(Material.IRON_AXE,1));
				itemList.add(new ItemStack(Material.CHAINMAIL_BOOTS,1));
				itemList.add(new ItemStack(Material.CHAINMAIL_HELMET,1));
				itemList.add(new ItemStack(Material.CHAINMAIL_CHESTPLATE,1));
				itemList.add(new ItemStack(Material.CHAINMAIL_LEGGINGS,1));
				itemList.add(new ItemStack(Material.GOLDEN_APPLE, 15));
				itemList.add(new ItemStack(Material.WORKBENCH,1));
				itemList.add(new ItemStack(Material.BREAD, 45));
				itemList.add(new ItemStack(Material.APPLE, 30));
				itemList.add(new ItemStack(Material.PAPER, 18));
				itemList.add(new ItemStack(Material.POTION, 18));
				itemList.add(new ItemStack(Material.WHEAT,64));
				break;
			case DEFENDER:
				itemList.add(new ItemStack(Material.BOW,1));
				itemList.add(new ItemStack(Material.ARROW,64));
				itemList.add(new ItemStack(Material.ARROW,64));
				itemList.add(new ItemStack(Material.BREAD, 15));
				itemList.add(new ItemStack(Material.IRON_BOOTS,1));
				itemList.add(new ItemStack(Material.IRON_HELMET,1));
				itemList.add(new ItemStack(Material.IRON_CHESTPLATE,1));
				itemList.add(new ItemStack(Material.IRON_LEGGINGS,1));
				itemList.add(new ItemStack(Material.WHEAT,64));
				break;
			case FIGTHER:
				itemList.add(new ItemStack(Material.IRON_SWORD,1));
				itemList.add(new ItemStack(Material.IRON_BOOTS,1));
				itemList.add(new ItemStack(Material.IRON_HELMET,1));
				itemList.add(new ItemStack(Material.BREAD, 15));
				itemList.add(new ItemStack(Material.IRON_CHESTPLATE,1));
				itemList.add(new ItemStack(Material.IRON_LEGGINGS,1));
				itemList.add(new ItemStack(Material.SHIELD,1));
				itemList.add(new ItemStack(Material.WHEAT,64));
				break;
			case BUILDER:
				itemList.add(new ItemStack(Material.IRON_PICKAXE,1));
				itemList.add(new ItemStack(Material.IRON_SPADE,1));
				itemList.add(new ItemStack(Material.GOLD_BOOTS,1));
				itemList.add(new ItemStack(Material.GOLD_HELMET,1));
				itemList.add(new ItemStack(Material.GOLD_CHESTPLATE,1));
				itemList.add(new ItemStack(Material.GOLD_LEGGINGS,1));
				itemList.add(new ItemStack(Material.WORKBENCH,1));
				itemList.add(new ItemStack(Material.FURNACE,1));
				itemList.add(new ItemStack(Material.IRON_INGOT,48));
				itemList.add(new ItemStack(Material.STICK,64));
				itemList.add(new ItemStack(Material.WHEAT,20));
				break;
				
            default:
                System.out.println("Roll no assigned");
                break;
			
		}
		
	}
	
	public String getRoll() {
		return Roll;
	}
	public void setRoll(String roll) {
		Roll = roll;
	}
	public ItemStack getItem() {
		return item;
	}
	public void setItem(ItemStack item) {
		this.item = item;
	}
	public String getRank() {
		return Rank;
	}
	public void setRank(String rank) {
		this.Rank = rank;
	}
	
	
	

}
