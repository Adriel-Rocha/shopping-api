package com.adriel.shopping_api.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopReportDTO {
    
    private Integer count;

    private BigDecimal total;

    private BigDecimal mean;
}
