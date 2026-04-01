package com.adriel.shopping_api.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.adriel.shopping_api.model.Item;
import com.adriel.shopping_api.model.Shop;
import com.adriel.shopping_api.dto.ItemDTO;
import com.adriel.shopping_api.dto.ShopDTO;
import com.adriel.shopping_api.dto.ShopReportDTO;
import com.adriel.shopping_api.repository.ShopRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopService {

    @Qualifier("shopRepository")
    private final ShopRepository shopRepository;


    public List<ShopDTO> getAll() {
        return shopRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<ShopDTO> getByUser(String userIdentifier) {
        return shopRepository.findAllByUserIdentifier(userIdentifier)
                .stream()
                .map(this::mapToResponse)
                .toList();

    }

    public List<ShopDTO> getByDate(ShopDTO shopDTO) {
        return shopRepository.findAllByDateGreaterThanEqual(shopDTO.getDate())
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ShopDTO findById(Long productId) {
        Shop shop = shopRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        return mapToResponse(shop);
    }


    public ShopDTO save(ShopDTO shopDTO) {

        BigDecimal total = shopDTO.getItems()
                            .stream()
                            .map(item -> item.getPrice())
                            .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<Item> items = shopDTO.getItems()
                            .stream()
                            .map(itemDTO -> Item.builder()
                                .productIdentifier(itemDTO.getProductIdentifier())
                                .price(itemDTO.getPrice())
                                .build())
                            .toList();
        
        Shop shop = Shop.builder()
                    .userIdentifier(shopDTO.getUserIdentifier())
                    .total(total)
                    .date(new Date())
                    .items(items)
                    .build();

        Shop saved = shopRepository.save(shop);

        return mapToResponse(saved);
        
    }

    public List<ShopDTO> getShopsByFilter(Date dateInit, Date dateEnd, BigDecimal valorMinimo) {
        return shopRepository.getShopByFilters(dateInit, dateEnd, valorMinimo)
                .stream()
                .map(this::mapToResponse).toList();
    }

    public ShopReportDTO getReportByDate(Date dateInit, Date dateEnd) {
        return shopRepository.getReportByDate(dateInit, dateEnd);
    }


    private ShopDTO mapToResponse(Shop shop) {
        return ShopDTO.builder()
                .userIdentifier(shop.getUserIdentifier())
                .total(shop.getTotal())
                .date(shop.getDate())
                .items(shop.getItems() != null ? 
                                                shop.getItems().stream().map(item -> ItemDTO.builder()
                                                                                    .productIdentifier(item.getProductIdentifier())
                                                                                    .price(item.getPrice())
                                                                                    .build())
                                                                                    .toList()
                                                : new ArrayList<>())
                .build();
    }
}
