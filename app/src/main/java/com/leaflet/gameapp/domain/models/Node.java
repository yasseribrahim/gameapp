package com.leaflet.gameapp.domain.models;

import android.widget.ImageView;

/**
 * Created by yasser.ibrahim on 5/8/2018.
 */

public class Node {
    private int row;
    private int column;
    private int resource;
    private ImageView image;
    private boolean visible;
    private boolean cleared;

    public Node() {
        this(-1, -1, -1, null);
    }

    public Node(int row, int column, int resource, ImageView image) {
        this.row = row;
        this.column = column;
        this.resource = resource;
        this.image = image;
        this.cleared = row == -1 || column == -1;
        this.visible = false;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
        this.cleared = row == -1;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
        this.cleared = column == -1;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public void clear() {
        this.row = -1;
        this.column = -1;
        this.resource = -1;
        this.image = null;
        this.cleared = true;
        this.visible = false;
    }

    public boolean isCleared() {
        return cleared;
    }

    public void fill(Node node) {
        this.row = node.row;
        this.column = node.column;
        this.resource = node.resource;
        this.image = node.image;
        this.cleared = node.row == -1 || node.column == -1;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return resource == node.resource;
    }

    @Override
    public int hashCode() {
        return resource;
    }

    @Override
    public String toString() {
        return "Node{" +
                "row=" + row +
                ", column=" + column +
                ", resource=" + resource +
                '}';
    }
}
