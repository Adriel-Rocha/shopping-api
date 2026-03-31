package com.adriel.shopping_api.dto;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopDTO {
    
    private String userIdentifier;
    private Float total;
    private Date date;
    private List<ItemDTO> items;
}
