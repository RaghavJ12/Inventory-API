package com.example.Inventory.controller;

import com.example.Inventory.exceptions.InventoryCollectionException;
import com.example.Inventory.model.InventoryDTO;
import com.example.Inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//import javax.validation.ConstraintViolationException;

@RestController
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/inventory")
    public ResponseEntity<?> createItem(@RequestBody InventoryDTO items) {
        try {
            inventoryService.createItem(items);
            return new ResponseEntity<InventoryDTO>(items, HttpStatus.OK);
        }
        catch (InventoryCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("/inventory")
    public ResponseEntity<?> getAllItems(){
        List<InventoryDTO> inventoryList = inventoryService.getAllItems();
        if(inventoryList.size()>0)
            return new ResponseEntity<List<InventoryDTO>>(inventoryList, HttpStatus.OK);
        else{
            return new ResponseEntity<>("No items found",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/inventory/{id}")
    public ResponseEntity<?> getSingleItem(@PathVariable("id") String id){
        try {
            return new ResponseEntity<>(inventoryService.getSingleItem(id), HttpStatus.OK);
        } catch (InventoryCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/inventory/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id) throws InventoryCollectionException{
        try{
            inventoryService.deleteItemById(id);
            return new ResponseEntity<>("Successfully deleted item with id "+id, HttpStatus.OK);
        }
        catch (InventoryCollectionException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/inventory/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody InventoryDTO item)
    {
        try {
            inventoryService.updateItem(id,item);
            return new ResponseEntity<>("Updated movie with id "+id+"", HttpStatus.OK);
        }
        catch (InventoryCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

}
