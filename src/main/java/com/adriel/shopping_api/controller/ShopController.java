package com.adriel.shopping_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adriel.shopping_api.dto.ShopDTO;
import com.adriel.shopping_api.service.ShopService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/shopping")
@RequiredArgsConstructor
public class ShopController {
    
    private final ShopService shopService;

    @GetMapping
    public List<ShopDTO> getShops() {
        return shopService.getAll();
    }

    @GetMapping("/shopByUser/{userIdentifier}")
    public List<ShopDTO> getShopsByUser(@PathVariable String userIdentifier) {
        return shopService.getByUser(userIdentifier);
    }

    @GetMapping("/shopByDate")
    public List<ShopDTO> getShopByDate(@RequestBody ShopDTO shopDTO) {
        return shopService.getByDate(shopDTO);
    }

    @GetMapping("/{id}")
    public ShopDTO findById(@PathVariable Long id) {
        return shopService.findById(id);
    }

    @PostMapping
    public ShopDTO newShop(@RequestBody ShopDTO shopDTO) {
        return shopService.save(shopDTO);
    }


}
