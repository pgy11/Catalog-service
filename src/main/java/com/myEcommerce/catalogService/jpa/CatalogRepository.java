package com.myEcommerce.catalogService.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface CatalogRepository extends CrudRepository<CatalogEntity, Long> {

}
