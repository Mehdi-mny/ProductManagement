package com.example.productmanagement.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.sql.Blob;
import java.util.Base64;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String provider;
    private int quantity;
    private double unit_cost;
    private double unit_price;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;
    @Lob
    private String qrcode;
    @ManyToOne
    private Category category;


}
