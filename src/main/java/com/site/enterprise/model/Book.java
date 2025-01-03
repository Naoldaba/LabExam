package com.site.enterprise.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class Book {
    private int id;
    private String title;
    private String author;
    private Double price;

    public Book(String title, String author, Double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }
}

