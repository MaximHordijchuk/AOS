package lab2;

/**
 * Created by max on 23.03.15.
 * Have fun!
 */
public class LexemeArgument {
    public LexemeType type;
    public int value;

    public LexemeArgument(LexemeType type, int value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        switch (type) {
            case REGISTER:
                return "R" + (value + 1);
            case MEMORY:
                return "M" + (value + 1);
            default:
                return Integer.toString(value);
        }
    }
}
