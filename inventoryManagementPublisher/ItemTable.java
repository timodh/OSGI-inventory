package com.inventorymanagementPublisher;

import java.util.ArrayList;
import java.util.List;


public class ItemTable implements Dao{
	
	private List<ItemDTO> itemTable;
	
	private static int itemId;
	
	public ItemTable() {
		itemId = 1;
		itemTable = new ArrayList<>();
	}
	
	@Override
	public ItemDTO get(int id) {
		
		for(ItemDTO item : itemTable) {
			if(item.getId() == id) {
				return item;
			}
		}
		return null;
	}

	@Override
	public List<ItemDTO> getAll() {
		return itemTable;
	}

	@Override
	public void create(ItemDTO itemdto) {
		
		//We generate an id locally before inserting to a table to ensure that the Id's added this session are unique
		
		itemdto.setId(itemId);
		itemTable.add(itemdto);
		itemId++;
	}

	@Override
	public void update(ItemDTO itemdto) {
		
		//Since we generate item id's when inserting into the table locally,
		//We cannot call the create() method.
		
		//Finds the original item and removes it
		//Adds the new item
		//Since id's are the same it's essentially a replacement
		
		itemTable.remove(findItemById(itemdto.getId()));
		itemTable.add(itemdto);
		itemTable.sort(itemdto);
		
		
	}

	@Override
	public void delete(int id) {
		
		itemTable.remove(findItemById(id));
		
		//bad implementation since this doesn't handle a null case, but
		//if findItemById returns a null then i guess the item doesn't exist?
		
		}
	
	@Override
	public boolean check(int id) {
		for(ItemDTO item : itemTable) {
			if (item.getId() == id) {
				return true;
			}
		}
		return false;
	}

	private ItemDTO findItemById(int id) {
		for(ItemDTO item : itemTable) {
			if(item.getId() == id) {
				return item;
			}
		}
		return null;
	}
	
}
