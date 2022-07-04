package com.hanium.cloudproject.repository;


import com.hanium.cloudproject.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product/*관리대상 Entity*/, Long/*대표값id 타입*/> {
}
