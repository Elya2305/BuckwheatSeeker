package com.progastination.service.impl;

import com.progastination.dto.ImgDto;
import com.progastination.dto.ProductDto;
import com.progastination.service.BuckwheatService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class BuckwheatServiceImpl implements BuckwheatService {
    private static final String AUCHAN_URL = "https://auchan.zakaz.ua/uk/products/04820101710259/krupa-grechka-khutorok-800g-ukrayina/";
    private static final String AGUSIK_URL = "https://agusik.com.ua/grechnevaya/18228-khutorok-krupa-grechnevaya-khutorok-800g-4820211661410.html";
    private static final String PRODUCTOFF_URL = "https://produktoff.com/bakaleya-2/krupy-bobovye-25/krupa-grechnevaya-khutorok-800g-668223.html";

    private static final String AUCHAN_IMG = "https://img3.zakaz.ua/src.1592867662.ad72436478c_2020-06-24_Tatiana/src.1592867662.SNCPSG10.obj.0.1.jpg.oe.jpg.pf.jpg.350nowm.jpg.350x.jpg";
    private static final String AGUSIK_IMG = "https://img3.zakaz.ua/src.1592867662.ad72436478c_2020-06-24_Tatiana/src.1592867662.SNCPSG10.obj.0.1.jpg.oe.jpg.pf.jpg.350nowm.jpg.350x.jpg";
    private static final String PRODUCTOFF_IMG = "https://img3.zakaz.ua/src.1592867662.ad72436478c_2020-06-24_Tatiana/src.1592867662.SNCPSG10.obj.0.1.jpg.oe.jpg.pf.jpg.350nowm.jpg.350x.jpg";

    private static final String AUCHAN = "Ашан";
    private static final String AGUSIK = "Агусик";
    private static final String PRODUCTOFF = "PRODUCTOFF";

    private static final String CLASS = "class";
    private static final String PRICE_AUCHAN = "jsx-3642073353 Price__value_title";
    private static final String PRICE_AGUSIK = "our_price_display";
    private static final String PRICE_PRODUCTOFF = "price-cell__default";
    private final static String LETTER_PATTERN = "[^\\d]";



    public List<ProductDto> buckwheatSearch() {
        List<ProductDto> result = new ArrayList<>();
        try {
            result.add(getProduct(AUCHAN_URL, AUCHAN_IMG, AUCHAN, PRICE_AUCHAN));
            result.add(getProduct(AGUSIK_URL, AGUSIK_IMG, AGUSIK, PRICE_AGUSIK));
            result.add(getProduct(PRODUCTOFF_URL, PRODUCTOFF_IMG, PRODUCTOFF, PRICE_PRODUCTOFF));
        } catch (IOException e) {
            throw new RuntimeException("Jsoup connection failed");
        }
        return result;
    }

    private ProductDto getProduct(String url, String img, String title, String price) throws IOException {
        Document document = Jsoup.connect(url).get();
        ProductDto product = new ProductDto();
        product.setImg_url(img);
        product.setShop(title);
        product.setWebUrl(url);
        Elements elements = document.getElementsByAttributeValueContaining(CLASS, price);
        product.setPrice(Double.valueOf(elements.get(0).text().replaceAll(LETTER_PATTERN, "")));
        return product;
    }
}
