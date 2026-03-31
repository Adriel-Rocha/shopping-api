package com.adriel.shopping_api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopItemRequestDTO {

    private Long productId;
    private Integer quantity;
}
