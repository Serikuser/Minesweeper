package Game;

import Utils.Coord;
import Utils.Field;
import Utils.Matrix;

class Flag {
    private Matrix flagMap;

    private int closedFields;

    void start(Setup game) {
        flagMap = new Matrix(Field.ClOSED, game);
        closedFields=game.getRanges().getSize().x*game.getRanges().getSize().y;
    }

    Field get(Coord coord) {
        return flagMap.get(coord);
    }

    void setOpenedField(Coord coord) {
        flagMap.set(coord, Field.OPENED);
        closedFields--;
    }

    private void setFlagField(Coord coord) {
        flagMap.set(coord, Field.FLAGGED);
    }

    private void setQuestionField(Coord coord) {
        flagMap.set(coord, Field.QUESTION); }

    void switchFlagField(Coord coord) {
        switch (flagMap.get(coord)) {
            case FLAGGED:
                setClosedField(coord);
                break;
            case ClOSED:
                setFlagField(coord);
                break;
            case QUESTION:
                setFlagField(coord);
                break;
        }
    }

    void switchFlagFieldtoQuestion(Coord coord) {
        switch (flagMap.get(coord)) {
            case QUESTION:
                setClosedField(coord);
                break;
            case ClOSED:
                setQuestionField(coord);
                break;
        }
    }

    private void setClosedField(Coord coord) {
        flagMap.set(coord, Field.ClOSED);
    }

    int getClosedFields() {
        return closedFields;
    }


    void setBombedFiled(Coord coord) {
        flagMap.set(coord, Field.BOMBED);
    }

    void setOpenedtoBombed(Coord coord) {
        flagMap.set(coord, Field.BOMBED);
        if (flagMap.get(coord)==Field.ClOSED)
            flagMap.set(coord,Field.OPENED);
    }

    void setOpenedtoNoBomb(Coord coord) {
        if (flagMap.get(coord)==Field.FLAGGED)
            flagMap.set(coord,Field.NOBOMB);
    }

     int getCountOfFlagedBoxesAround(Coord coord,Setup game) {
        int count = 0;
        for (Coord around : game.getRanges().getAroundCoords(coord))
            if (flagMap.get(around) == Field.FLAGGED)
                count++;
        return count;
    }
}
