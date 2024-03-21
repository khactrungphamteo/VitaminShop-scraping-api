package com.vitaminshop.webscraping.service.implementation;

import com.vitaminshop.webscraping.dto.ProductDto;
import com.vitaminshop.webscraping.service.ScrapingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class ScrapingServiceImpl implements ScrapingService {
    Logger logger = LoggerFactory.getLogger(ScrapingServiceImpl.class);
    @Value("${website.url}")
    String url = null;

    @Override
    public List<ProductDto> extractProducts() throws IOException {
        List<ProductDto> productDtos = new ArrayList<>();
        Elements products = null;
        StringBuilder vitaminUrl = new StringBuilder(url);
        for (int currentPage = 1; currentPage <= 5; currentPage++) {
            vitaminUrl.append("?currentPage=");
            vitaminUrl.append(currentPage);
            Document document = Jsoup.connect(String.valueOf(vitaminUrl)).get();
            products = document.getElementsByClass("product-tile-set");

            for (Element product : products) {
                try {
                    String imageLinkText = "";
                    ProductDto productDto = new ProductDto();
                    Element description = product.getElementsByClass("description").get(0);
                    Element price = product.getElementsByClass("price").get(0);
                    Element imageLink = product.getElementsByClass("img-responsive").get(0);
                    imageLinkText = imageLink.attr("src");
                    if (imageLinkText.isEmpty()) {
                        imageLinkText = imageLink.attr("data-src");
                    }

                    productDto.setDescription(description.text());
                    productDto.setPrice(price.text());
                    productDto.setImageLink(imageLinkText);
                    productDtos.add(productDto);
                } catch (Exception e) {
                    this.logger.error(String.valueOf(e));
                }
            }

            vitaminUrl = new StringBuilder(url);
        }

        return productDtos;
    }
}
