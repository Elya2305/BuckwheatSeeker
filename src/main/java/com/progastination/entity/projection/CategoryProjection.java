package com.progastination.entity.projection;

public interface CategoryProjection {
    String getIdentifiers();

    int getTotalCount();

    String getTitle();

    String getImage();

    String getShops();

    String getParent();
}
