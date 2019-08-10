package Game;

import Utils.Coord;
import Utils.Field;
import Utils.States;


public class Setup {
    private Ranges ranges = new Ranges();
    private Bomb bomb;
    private Flag flag;

    private States state;

    public Setup(int cols, int rows, int bombs) {
        this.ranges.setSize(new Coord(cols, rows));
        bomb = new Bomb(bombs);
        flag = new Flag();

    }

    public Field getField(Coord coord) {
        if (flag.get(coord) == Field.OPENED)
            return bomb.get(coord);
        else
            return flag.get(coord);
    }

    public Ranges getRanges() {
        return ranges;
    }

    public void start(Setup game) {
        bomb.start(game);
        flag.start(game);

        state = States.PLAYING;
    }

    public void pressLeftButton(Coord coord, Setup game) {
        if (gameOver()) return;
        openField(coord, game);
        winCheck();
    }

    public void pressMiddleButton(Coord coord, Setup newGame) {
        if (gameOver()) return;
        flag.switchFlagFieldtoQuestion(coord);
    }

    public void pressRightButton(Coord coord, Setup game) {
        if (gameOver()) return;
        flag.switchFlagField(coord);
    }

    private boolean gameOver() {
        if (state == States.PLAYING)
            return false;
        return true;
    }

    public States getState() {
        return state;
    }

    private void winCheck() {
        if (state == States.PLAYING)
            if (flag.getClosedFields() == bomb.getBombsNumber())
                state = States.WIN;
    }

    private void openField(Coord coord, Setup game) {
        switch (flag.get(coord)) {
            case OPENED:
                setOpenedToClosedBoxesAroundNumber(coord, game);
                return;
            case FLAGGED:
                return;
            case ClOSED:
                switch (bomb.get(coord)) {
                    case BOMB:
                        openedBomb(coord, game);
                        return;
                    case ZERO:
                        openFieldAround(coord, game);
                        return;
                    default:
                        flag.setOpenedField(coord);
                        return;
                }
        }
    }

    private void openedBomb(Coord bombed, Setup game) {
        state = States.EXPLOSION;
        flag.setBombedFiled(bombed);
        for (Coord coord : game.getRanges().getAllcoords())
            if (bomb.get(coord) == Field.BOMB)
                flag.setOpenedtoBombed(coord);
            else
                flag.setOpenedtoNoBomb(coord);
    }
    private void openFieldAround(Coord coord, Setup game) {
        flag.setOpenedField(coord);
        for (Coord arround : game.getRanges().getAroundCoords(coord))
            openField(arround, game);

    }

    private void setOpenedToClosedBoxesAroundNumber (Coord coord,Setup game)
    {
        if (bomb.get(coord) != Field.BOMB)
            if (flag.getCountOfFlagedBoxesAround(coord,game) == bomb.get(coord).getNumber())
                for (Coord around : game.getRanges().getAroundCoords(coord))
                    if (flag.get(around) == Field.ClOSED)
                        openField(around,game);
    }
}
