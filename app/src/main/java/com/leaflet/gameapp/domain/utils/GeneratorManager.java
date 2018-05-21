package com.leaflet.gameapp.domain.utils;

import com.leaflet.gameapp.domain.communicator.ImageViewHandler;
import com.leaflet.gameapp.domain.communicator.ResourceHandler;
import com.leaflet.gameapp.domain.models.Level;
import com.leaflet.gameapp.domain.models.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author interactive
 */
public class GeneratorManager {

    private final Random random;
    private ResourceHandler handler;
    private int rows, columns;
    private int minimum;

    public GeneratorManager(ResourceHandler handler) {
        this.handler = handler;
        this.random = new Random();
    }

    public Node[][] generate(Level level, ImageViewHandler imageHandler) {
        Node[][] nodes = new Node[level.getRows()][level.getColumns()];
        this.rows = level.getRows();
        this.columns = level.getColumns();
        this.minimum = 0;

        List<Integer> singleRows = generateSingleDimension(level.getRows());
        List<Integer> singleColumns = generateSingleDimension(level.getColumns());
        List<Integer> selected = new ArrayList<>();
        int value = 1;
        int maximumValue = level.getCalculatedDimension() / 2;
        int index;

        for (int i = 0; i < rows; i++) {
            selected.clear();
            for (short j = 0; j < columns; j++) {
                index = selectRandomIndex(singleColumns, selected);
                nodes[singleRows.get(i)][index] = new Node(singleRows.get(i), index, handler.getDrawable((value % maximumValue) + 1), imageHandler.getImageView(singleRows.get(i) + 1, index + 1));
                value++;
            }
        }

        return nodes;
    }

    private int selectRandomIndex(List<Integer> shorts, List<Integer> selected) {
        int index, value;
        do {
            index = generateRandom(shorts.size(), minimum);
            value = shorts.get(index);
        } while (selected.contains(value));

        selected.add(value);
        return value;
    }

    private List<Integer> generateSingleDimension(int maximum) {
        List<Integer> list = new ArrayList<>();
        int value;
        int counter = 0;
        while (counter < maximum) {
            value = generateRandom(maximum, minimum);
            if (!list.contains(value)) {
                list.add(value);
                counter++;
            }
        }
        return list;
    }

    private int generateRandom(int maximum, int minimum) {
        return random.nextInt(maximum - minimum) + minimum;
    }
}