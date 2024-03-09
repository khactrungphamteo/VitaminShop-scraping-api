package com.vitaminshop.webscraping.controller;

import com.vitaminshop.webscraping.dto.ProductDto;
import com.vitaminshop.webscraping.service.ScrapingService;
import com.vitaminshop.webscraping.service.implementation.ScrapingServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Set;

@RestController
@RequestMapping(path = "/")
public class ScrapingController {
    private final ScrapingServiceImpl scrapingService;

    public ScrapingController(ScrapingServiceImpl scrapingService) {
        this.scrapingService = scrapingService;
    }

    @GetMapping(path = "/products")
    public Set<ProductDto> getProducts() throws IOException {
        return this.scrapingService.extractProducts();
    }
}
