package com.example.Inventory.repository;

import com.example.Inventory.model.InventoryDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepo extends MongoRepository<InventoryDTO, String>{
    @Query("{'items': ?0}")
    Optional<InventoryDTO> findByItem(String Inventory);
}
