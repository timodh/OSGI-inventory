package com.inventorymanagementPublisher;

import java.util.List;

public interface Dao {

	ItemDTO get(int id);
	
	List<ItemDTO> getAll();
	
	void create(ItemDTO itemdto);
	
	void update(ItemDTO itemdto);
	
	void delete(int id);
	
	boolean check(int id);
}
