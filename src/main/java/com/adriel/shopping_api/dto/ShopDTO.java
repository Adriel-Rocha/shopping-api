package com.adriel.shopping_api.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ShopDTO {
    
    private String userIdentifier;

    private BigDecimal total;

    private Date date;

    private List<ItemDTO> items;
}
