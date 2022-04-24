package com.myEcommerce.catalogService.jpa;

import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@ToString
@Table(name = "catalog")
public class CatalogEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120, unique = true, name = "product_id")
    private String productId;

    @Column(nullable = false, name = "product_name")
    private String productName;

    @Column(nullable = false, name = "stock")
    private Integer stock;

    @Column(nullable = false, name = "unit_price")
    private Integer unitPrice;

    @Column(nullable = false, updatable = false, insertable = false, name = "created_at")
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private Date createdAt;

}
