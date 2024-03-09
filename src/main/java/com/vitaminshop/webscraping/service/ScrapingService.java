package com.vitaminshop.webscraping.service;

import com.vitaminshop.webscraping.dto.ProductDto;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.Set;

public interface ScrapingService {
    Set<ProductDto> extractProducts() throws IOException;
}
