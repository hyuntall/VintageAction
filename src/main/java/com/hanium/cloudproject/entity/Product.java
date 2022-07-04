package com.hanium.cloudproject.entity;

import lombok.AllArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity //어노테이션 해줘야 DB가 해당 객체를 인식 가능
@AllArgsConstructor
@ToString
public class Product {

    @Id //고유의 대표값 식별 id를 지정.
    @GeneratedValue //번호 순서대로 자동생성
    private Long product_id;

    @Column
    private String product_name;

    @Column
    private String product_category;

    @Column
    private int product_price;

    @Column
    private int product_details;

    @Column
    private boolean product_status;

    public Product() {

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