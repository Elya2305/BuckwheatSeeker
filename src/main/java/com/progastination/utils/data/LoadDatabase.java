package com.progastination.utils.data;

import com.progastination.utils.data.impl.CategoryInitImpl;
import com.progastination.utils.data.impl.ProductInitImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoadDatabase {
    private final InitDbService categoryInit;
    private final InitDbService productInit;

    public LoadDatabase(@Qualifier(value = CategoryInitImpl.CATEGORY_INITIALIZER) InitDbService categoryInit,
                        @Qualifier(value = ProductInitImpl.PRODUCT_INITIALIZER) InitDbService productInit) {
        this.categoryInit = categoryInit;
        this.productInit = productInit;

        if (productInit.isEmpty()) {
            loadDb();
        }
    }

    @Scheduled(cron = "0 0 0 * * *") // 12:00
    public void loadDb() {
        log.info("Starting to update data...");
        productInit.clear();
        categoryInit.clear();

        categoryInit.init();
        productInit.init();
        log.info("Ending to update data...");
    }
}
