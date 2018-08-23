package com.lxq.spider;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Product {
    private String id;
    private String title;
    private String headImg;
    private double price;
    private long weekSaleNum;
    private long totalSaleNum;
    private String link;
}
