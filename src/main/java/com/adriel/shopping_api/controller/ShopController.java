package com.adriel.shopping_api.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adriel.shopping_api.dto.ShopDTO;
import com.adriel.shopping_api.dto.ShopReportDTO;
import com.adriel.shopping_api.service.ShopService;

@RestController
@RequestMapping("/shopping")
public class ShopController {
    
    @Autowired
    private ShopService shopService;

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

    @GetMapping("/search")
    public List<ShopDTO> getShopByFilter(@RequestParam(name = "dateInit", required = true)
                                        @DateTimeFormat(pattern = "dd/MM/yyyy") Date dateInit,
                                        @RequestParam(name = "dateEnd", required = false)
                                        @DateTimeFormat(pattern = "dd/MM/yyyy") Date dateEnd,
                                        @RequestParam(name = "valorMinimo", required = false)
                                        BigDecimal valorMinimo) {

        return shopService.getShopsByFilter(dateInit, dateEnd, valorMinimo);
    }

    @GetMapping("/report")
    public ShopReportDTO getReportByDate(@RequestParam(name = "dateInit", required = true)
                                        @DateTimeFormat(pattern = "dd/MM/yyyy") Date dateInit,
                                        @RequestParam(name = "dateEnd", required = true)
                                        @DateTimeFormat(pattern = "dd/MM/yyyy") Date dateEnd) {

        return shopService.getReportByDate(dateInit, dateEnd);
    }
    
}
