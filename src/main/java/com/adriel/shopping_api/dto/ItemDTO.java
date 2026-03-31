package com.adriel.shopping_api.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ItemDTO {
    
    private String productIdentifier;

    private BigDecimal price;

}
