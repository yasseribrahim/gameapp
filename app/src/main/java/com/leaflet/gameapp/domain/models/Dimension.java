package com.leaflet.gameapp.domain.models;

/**
 * Created by yasser.ibrahim on 5/3/2018.
 */

public enum Dimension {
    DIMENSION_2_BY_3(2, 3),
    DIMENSION_3_BY_4(3, 4),
    DIMENSION_4_BY_5(4, 5);

    private int dimensionX;
    private int dimensionY;

    Dimension(int dimensionX, int dimensionY) {
        this.dimensionX = dimensionX;
        this.dimensionY = dimensionY;
    }

    public int getDimensionX() {
        return dimensionX;
    }

    public int getDimensionY() {
        return dimensionY;
    }
}
