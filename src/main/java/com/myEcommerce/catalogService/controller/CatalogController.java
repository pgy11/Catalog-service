package com.myEcommerce.catalogService.controller;

import com.myEcommerce.catalogService.jpa.CatalogEntity;
import com.myEcommerce.catalogService.service.CatalogService;
import com.myEcommerce.catalogService.vo.ResponseCatalog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/catalog-service")
public class CatalogController {
    private Environment env;
    private CatalogService catalogService;

    @Autowired
    public CatalogController(Environment env, CatalogService catalogService) {
        this.env = env;
        this.catalogService = catalogService;
    }

    @GetMapping("/health_check")
    public String status() {
        return String.format("it's working in catalog service on port %s", env.getProperty("local.server.port"));
    }

    @GetMapping("/catalogs")
    public ResponseEntity<List<ResponseCatalog>> getCatalogs() {
        Iterable<CatalogEntity> catalogList = catalogService.getAllCatalogs();
        List<ResponseCatalog> result = new ArrayList<>();
        catalogList.forEach(v -> {
            ResponseCatalog responseCatalog = ResponseCatalog.builder()
                    .productId(v.getProductId())
                    .productName(v.getProductName())
                    .stock(v.getStock())
                    .unitPrice(v.getUnitPrice())
                    .createdAt(v.getCreatedAt())
                    .build();
            result.add(responseCatalog);
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
