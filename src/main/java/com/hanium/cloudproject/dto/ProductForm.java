package com.hanium.cloudproject.dto;

import com.hanium.cloudproject.entity.Product;
import lombok.AllArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@ToString
public class ProductForm {
    private Long product_id;
    private String product_name;
    private String product_category;
    private int product_price;
    private int product_details;
    private boolean product_status;

    public Product toEntity() {  //엔티티 Product 객체
        return new Product();
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setProduct_category(String product_category) {
        this.product_category = product_category;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    public void setProduct_details(int product_details) {
        this.product_details = product_details;
    }

    public void setProduct_status(boolean product_status) {
        this.product_status = product_status;
    }
}
