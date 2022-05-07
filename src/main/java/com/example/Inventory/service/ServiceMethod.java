package com.example.Inventory.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.Inventory.exceptions.InventoryCollectionException;
import com.example.Inventory.model.InventoryDTO;
import com.example.Inventory.repository.InventoryRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceMethod implements InventoryService {

    @Autowired
    private InventoryRepo InventoryRepo;

    @Override
    public List<InventoryDTO> getAllItems() {
        List<InventoryDTO> Items = InventoryRepo.findAll();
        if (Items.size() > 0) {
            return Items;
        }else {
            return new ArrayList<InventoryDTO>();
        }
    }

    @Override
    public InventoryDTO getSingleItem(String id) throws InventoryCollectionException {
        Optional<InventoryDTO> ItemOptional = InventoryRepo.findById(id);
        if (!ItemOptional.isPresent()) {
            throw new InventoryCollectionException(InventoryCollectionException.NotFoundException(id));
        }else {
            return ItemOptional.get();
        }
    }

    @Override
    public void createItem(InventoryDTO Item) throws InventoryCollectionException{
        Optional<InventoryDTO> ItemOptional= InventoryRepo.findByItem(Item.getOp());
        if(ItemOptional.isPresent())
        {
            throw new InventoryCollectionException(InventoryCollectionException.ItemAlreadyExists());
        }
        else
        {
            Item.setCreateOn(new Date(System.currentTimeMillis()));
            InventoryRepo.save(Item);
        }

    }

    @Override
    public void updateItem(String id, InventoryDTO Item) throws InventoryCollectionException{
        Optional<InventoryDTO> ItemWithId = InventoryRepo.findById(id);
        Optional<InventoryDTO> ItemWithSameName = InventoryRepo.findByItem(Item.getOp());
        if(ItemWithId.isPresent())
        {
            if(ItemWithSameName.isPresent() && !ItemWithSameName.get().getId().equals(id))
            {

                throw new InventoryCollectionException(InventoryCollectionException.ItemAlreadyExists());
            }
            InventoryDTO ItemToUpdate=ItemWithId.get();

            ItemToUpdate.setOp(Item.getOp());
            ItemToUpdate.setDesc(Item.getDesc());
            ItemToUpdate.setStatus(Item.getStatus());
            ItemToUpdate.setUpdateOn(new Date(System.currentTimeMillis()));
            InventoryRepo.save(ItemToUpdate);
        }
        else
        {
            throw new InventoryCollectionException(InventoryCollectionException.NotFoundException(id));
        }
    }

    @Override
    public void deleteItemById(String id) throws InventoryCollectionException{
        Optional<InventoryDTO> ItemOptional = InventoryRepo.findById(id);
        if(!ItemOptional.isPresent())
        {
            throw new InventoryCollectionException(InventoryCollectionException.NotFoundException(id));
        }
        else
        {
            InventoryRepo.deleteById(id);
        }

    }
}
