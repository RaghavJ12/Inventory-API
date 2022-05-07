package com.example.Inventory.service;

import com.example.Inventory.exceptions.InventoryCollectionException;
import com.example.Inventory.model.InventoryDTO;

import java.util.List;

public interface InventoryService {
    public List<InventoryDTO> getAllItems();

    public InventoryDTO getSingleItem(String id) throws InventoryCollectionException;

    public void createItem(InventoryDTO Inventory) throws InventoryCollectionException;

    public void updateItem(String id, InventoryDTO Inventory) throws InventoryCollectionException;

    public void deleteItemById(String id) throws InventoryCollectionException;
}
