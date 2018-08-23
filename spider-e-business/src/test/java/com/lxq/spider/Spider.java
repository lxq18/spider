package com.lxq.spider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Spider {
    /**
     * 周排行
     * @throws IOException
     */
    @Test
    public void testWeekSaleSort() throws IOException {
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.170 Safari/537.36";
        //获取编辑推荐页
        //模拟火狐浏览器
        Document document = Jsoup.connect("https://list.tmall.com/search_product.htm?spm=a3204.7139825.1996500281.7.4OAMXu&s=0&user_id=1955345225&area_code=330100&cat=50514010&active=1&style=g&acm=lb-zebra-27092-331837.1003.4.467596&search_condition=1&sort=s&scm=1003.4.lb-zebra-27092-331837.OTHER_14440802694962_467596&n=40")
                .userAgent(userAgent)
                .get();
        Element weekSortElement = document.select("#filterForm")
                .select("div")
                .select("ul.filter-sort")
                .select("li:nth-child(4)")
                .select("a")
                .first();
        String weekUrl = weekSortElement.absUrl("href");

        Document weekDocument = Jsoup.connect(weekUrl)
                .userAgent(userAgent)
                .get();
        List<Product> productList = new ArrayList<>();
        Elements productListElements = weekDocument.select("#J_ProductList").select("li.product");
        for (Element e : productListElements) {
            String id = e.attr("data-itemid");
            Element productElement = e.select("div.productInfo").first();
            Product product = new Product();
            productList.add(product);

            //#J_ProductList > li:nth-child(1) > div > h3 > a
            //#J_ProductList > li:nth-child(1) > div > div.item-summary > div.item-sum > strong
            //#J_ProductList > li:nth-child(1) > div > div.item-summary > div.item-price > span > strong
            String img = productElement.select("div.product-img").select("a").select("img").attr("abs:src");
            String title = productElement.select("h3").select("a").text();
            String link = productElement.select("h3").select("a").attr("abs:href");
            long weekSaleNum = Long.parseLong(
                    (productElement.select("div.item-summary").select("div.item-sum").select("strong").text()));
            double price = Double.parseDouble(
                    (productElement.select("div.item-summary").select("div.item-price").select("span").select("strong").text()));

            product.setId(id)
                    .setTitle(title)
                    .setLink(link)
                    .setWeekSaleNum(weekSaleNum)
                    .setPrice(price)
                    .setHeadImg(img);

            System.out.println(product);
        }

    }

    /**
     * 总销量排行
     * @throws IOException
     */
    @Test
    public void testTotalSaleSort() throws IOException {
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.170 Safari/537.36";
        //获取编辑推荐页
        //模拟火狐浏览器
        Document document = Jsoup.connect("https://list.tmall.com/search_product.htm?spm=a3204.7139825.1996500281.7.4OAMXu&s=0&user_id=1955345225&area_code=330100&cat=50514010&active=1&style=g&acm=lb-zebra-27092-331837.1003.4.467596&search_condition=1&sort=s&scm=1003.4.lb-zebra-27092-331837.OTHER_14440802694962_467596&n=40")
                .userAgent(userAgent)
                .get();
        Element weekSortElement = document.select("#filterForm")
                .select("div")
                .select("ul.filter-sort")
                .select("li.now")
                .select("a")
                .first();
        String weekUrl = weekSortElement.absUrl("href");

        Document weekDocument = Jsoup.connect(weekUrl)
                .userAgent(userAgent)
                .get();
        List<Product> productList = new ArrayList<>();
        Elements productListElements = weekDocument.select("#J_ProductList").select("li.product");
        for (Element e : productListElements) {
            String id = e.attr("data-itemid");
            Element productElement = e.select("div.productInfo").first();
            Product product = new Product();
            productList.add(product);

            //#J_ProductList > li:nth-child(1) > div > h3 > a
            //#J_ProductList > li:nth-child(1) > div > div.item-summary > div.item-sum > strong
            //#J_ProductList > li:nth-child(1) > div > div.item-summary > div.item-price > span > strong
            String img = productElement.select("div.product-img").select("a").select("img").attr("abs:src");
            String title = productElement.select("h3").select("a").text();
            String link = productElement.select("h3").select("a").attr("abs:href");
            long weekSaleNum = Long.parseLong(
                    (productElement.select("div.item-summary").select("div.item-sum").select("strong").text()));
            double price = Double.parseDouble(
                    (productElement.select("div.item-summary").select("div.item-price").select("span").select("strong").text()));

            product.setId(id)
                    .setTitle(title)
                    .setLink(link)
                    .setWeekSaleNum(weekSaleNum)
                    .setPrice(price)
                    .setHeadImg(img);

            System.out.println(product);
        }

    }
}
