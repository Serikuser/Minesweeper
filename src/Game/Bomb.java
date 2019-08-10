package Game;

import Utils.Coord;
import Utils.Field;
import Utils.Matrix;

public class Bomb {
    private Matrix bombMap;
    private int bombCount;

    Bomb(int bombCount) {
        this.bombCount = bombCount;
    }

    public void start(Setup game) {
        bombMap = new Matrix(Field.ZERO, game);
        for (int i = 0; i < bombCount; i++)
            placeBomb(game);
    }

    Field get(Coord coord) {
        return bombMap.get(coord);
    }

    private void placeBomb(Setup game) {
        while (true) {
            Coord coord = game.getRanges().getRandomCoord();
            if (Field.BOMB == bombMap.get(coord))
                continue;
            bombMap.set(coord, Field.BOMB);
            expandNumbers(game, coord);
            break;
        }
    }

    private void expandNumbers(Setup game, Coord coord) {
        for (Coord around : game.getRanges().getAroundCoords(coord))
            if (Field.BOMB != bombMap.get(around))
                bombMap.set(around, bombMap.get(around).getNextNumber());
    }

    public int getBombsNumber() {
        return bombCount;
    }
}

