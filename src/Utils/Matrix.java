package Utils;

import Game.Setup;

public class Matrix {
    private Field[][] matrixMap;
    private Coord currentSize;

    public Matrix(Field defaults, Setup game) {
        this.currentSize = game.getRanges().getSize();
        matrixMap = new Field[game.getRanges().getSize().x][game.getRanges().getSize().y];
        for (Coord coord : game.getRanges().getAllcoords())
            matrixMap[coord.x][coord.y] = defaults;
    }

    private boolean inRange(Coord coord) {
        return coord.x >= 0 && coord.x < this.currentSize.x
                && coord.y >= 0 && coord.y < this.currentSize.y;
    }

    public Field get(Coord coord) {
        if (inRange(coord))
            return matrixMap[coord.x][coord.y];
        return null;
    }

    public Field set(Coord coord, Field field) {
        if (inRange(coord))
            return matrixMap[coord.x][coord.y] = field;
        return null;
    }
}
