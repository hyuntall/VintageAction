package com.hanium.cloudproject.controller;

import com.hanium.cloudproject.dto.ProductForm;
import com.hanium.cloudproject.entity.Product;
import com.hanium.cloudproject.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j //(Simple logging facad For java) 로깅을 위한 어노테이션
public class ProductController {

    @Autowired//스프링 부트가 미리 생성해놓은 객체를 자동 연결
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products/new")
    public String newproductForm(){
        return "products/new";
    }

    @PostMapping("/products/create")
    public String createproduct(ProductForm form){
        log.info(form.toString());

        //1.DTO를 Entity로 변환.
        Product product = form.toEntity();
        log.info(product.toString());

        //2.Repository가 Entity를 DB안에 저장하게끔.
        Product saved = productRepository.save(product);
        log.info(saved.toString());
        /*productRepository CrudRepository extend.
        Crud 기본제공되는 기능 사용할 수 있음*/
        return "";
    }
}
