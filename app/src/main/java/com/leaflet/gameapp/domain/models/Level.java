package com.leaflet.gameapp.domain.models;

import com.leaflet.gameapp.R;

/**
 * Created by yasser.ibrahim on 5/3/2018.
 */

public enum Level {
    LEVEL_EASY(1, 3, 2, R.layout.fragment_level_easy),
    LEVEL_MEDIUM(2, 3, 4, R.layout.fragment_level_medium),
    LEVEL_HARD(3, 5, 4, R.layout.fragment_level_hard);

    private int id;
    private int rows;
    private int columns;
    private int resource;

    Level(int id, int rows, int columns, int resource) {
        this.id = id;
        this.rows = rows;
        this.columns = columns;
        this.resource = resource;
    }

    public int getId() {
        return id;
    }

    public int getRows() {
        return rows;
    }

    public int getCalculatedDimension() {
        return rows * columns;
    }

    public int getResource() {
        return resource;
    }

    public int getColumns() {
        return columns;
    }

    public static Level parse(int id) {
        switch (id) {
            case 1:
                return LEVEL_EASY;
            case 2:
                return LEVEL_MEDIUM;
            default:
                return LEVEL_HARD;
        }
    }
}
