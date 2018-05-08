package com.leaflet.gameapp.presentation.ui.util;

import com.leaflet.gameapp.R;
import com.leaflet.gameapp.domain.models.Node;

/**
 * Created by yasser.ibrahim on 5/8/2018.
 */

public class Initializer {
    public static Node[][] generate(int rows, int columns) {
        Node[][] nodes = new Node[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                nodes[row][column] = new Node(row, column, getDrawable(rows, columns, row, column), null);
            }
        }
        return nodes;
    }

    public static int getDrawable(int rows, int columns, int row, int column) {
        int index = ((rows * (row + 1)) - (columns - (column + 1)) - 1) % ((rows * columns) / 2);
        switch (index) {
            case 1:
                return R.drawable.face_1;
            case 2:
                return R.drawable.face_2;
            case 3:
                return R.drawable.face_3;
            case 4:
                return R.drawable.face_4;
            case 5:
                return R.drawable.face_5;
            case 6:
                return R.drawable.face_6;
            case 7:
                return R.drawable.face_7;
            case 8:
                return R.drawable.face_8;
            case 9:
                return R.drawable.face_9;
            case 10:
                return R.drawable.face_10;
            case 11:
                return R.drawable.face_11;
            case 12:
                return R.drawable.face_12;
        }

        return R.drawable.face_1;
    }

    public static int[] drawables = {
            R.drawable.face_1, R.drawable.face_2, R.drawable.face_3, R.drawable.face_4,
            R.drawable.face_5, R.drawable.face_6, R.drawable.face_7, R.drawable.face_8,
            R.drawable.face_9, R.drawable.face_10, R.drawable.face_11, R.drawable.face_12
    };
}
