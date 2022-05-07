package com.example.Inventory.model;

import org.springframework.data.annotation.Id;

import java.util.Date;
import org.jetbrains.annotations.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="Items")
public class InventoryDTO {
    @Id
    private String id;
    @NotNull("Operation cannot be null")
    private String op;
    @NotNull("Description cannot be null")
    private String desc;
    private Boolean status;
    private Date createOn;
    private Date updateOn;
}
