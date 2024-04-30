package com.example.productmanagement.Entities;

import com.google.zxing.common.BitMatrix;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String Ref;
    private String name;
    private String provider;
    private int quantity;
    private double unit_cost;
    private double unit_price;
    @Lob
    private byte[] image;
    @Lob
    private byte[] QRcode;

    @ManyToOne(fetch =FetchType.EAGER)
    private Category Category;

}
