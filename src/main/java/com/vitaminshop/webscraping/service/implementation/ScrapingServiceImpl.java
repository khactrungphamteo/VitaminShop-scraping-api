package com.vitaminshop.webscraping.service.implementation;

import com.vitaminshop.webscraping.dto.ProductDto;
import com.vitaminshop.webscraping.service.ScrapingService;
import org.springframework.beans.factory.annotation.Value;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
public class ScrapingServiceImpl implements ScrapingService {
    @Value("${website.url}")
    String url = null;

    @Override
    public Set<ProductDto> extractProducts() throws IOException {
        Set<ProductDto> productDtos = new HashSet<>();
        Document document = Jsoup.connect(url).get();
        Elements products = document.getElementsByClass("product-title-set");

        for (Element product : products) {
            ProductDto productDto = new ProductDto();


        }

        return productDtos;
    }
}
