package com.example.Inventory.exceptions;

public class InventoryCollectionException extends Exception{
    private static final long serialVersionUID = 1L;

    public InventoryCollectionException(String message) {
        super(message);
    }

    public static String NotFoundException(String id) {
        return "Item with "+id+" not found!";
    }

    public static String ItemAlreadyExists() {
        return "Item with given name already exists";
    }
}
