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
        return "LexemeArgument{" +
                "type=" + type +
                ", value=" + value +
                '}';
    }
}
