package com.adriel.shopping_api.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.adriel.shopping_api.dto.ShopReportDTO;
import com.adriel.shopping_api.model.Shop;

public interface ReportRepository {
    
    public List<Shop> getShopByFilters(Date dateInit, Date dateEnd, BigDecimal valorMinimo);

    public ShopReportDTO getReportByDate(Date dateInit, Date dateEnd);
}
