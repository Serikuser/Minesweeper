package Game;

import Utils.Coord;

import java.util.ArrayList;
import java.util.Random;

public class Ranges {

    private Coord size;
    private ArrayList<Coord> allcoords;
    private Random random = new Random();

    public void setSize(Coord size) {
        this.size = size;
        allcoords = new ArrayList<Coord>();
        for (int y = 0; y < size.y; y++) {
            for (int x = 0; x < size.x; x++)
                allcoords.add(new Coord(x, y));
        }
    }

    public Coord getSize() {
        return size;
    }

    public ArrayList<Coord> getAllcoords() {
        return allcoords;
    }

    public Coord getRandomCoord() {
        return new Coord(
                random.nextInt(size.x),
                random.nextInt(size.y));
    }

    private boolean inRange(Coord coord) {
        return coord.x >= 0 && coord.x < size.x
                && coord.y >= 0 && coord.y < size.y;
    }

    public ArrayList<Coord> getAroundCoords(Coord coord) {
        Coord around;
        ArrayList<Coord> list = new ArrayList<Coord>();
        for (int x = coord.x - 1; x <= coord.x + 1; x++)
            for (int y = coord.y - 1; y <= coord.y + 1; y++)
                if (inRange(around = new Coord(x, y)))
                    if (!around.equals(coord))
                        list.add(around);
        return list;

    }
}

