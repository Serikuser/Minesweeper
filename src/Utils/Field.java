package Utils;

public enum Field {
    ZERO,
    NUM1,
    NUM2,
    NUM3,
    NUM4,
    NUM5,
    NUM6,
    NUM7,
    NUM8,
    BOMB,
    OPENED,
    ClOSED,
    FLAGGED,
    BOMBED,
    NOBOMB,
    QUESTION;
    public Object image;

    public Field getNextNumber() {
        return Field.values()[this.ordinal() + 1];
    }
    public int getNumber(){
        return this.ordinal();
    }
}
