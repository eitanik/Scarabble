package test;


import java.util.Arrays;
import java.util.Objects;

public class Word {

    Tile[] tiles;
    int row, col;
    boolean vertical;
    int length;

    public Word(Tile[] tiles, int row, int col, boolean vertical) {
        this.tiles = tiles.clone();
        this.row = row;
        this.col = col;
        this.vertical = vertical;
        this.length = tiles.length;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return row == word.row && col == word.col && vertical == word.vertical && length == word.length && Arrays.equals(tiles, word.tiles);
    }

    public void setTiles(Tile[] tilesArr) {
        this.tiles = tiles.clone();
    }

    public Tile[] getTiles() {

        Tile [] copy = tiles.clone();
        return copy;

    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isVertical() {
        return vertical;
    }
}
