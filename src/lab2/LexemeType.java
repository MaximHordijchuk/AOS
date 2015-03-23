package lab2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by max on 23.03.15.
 * Have fun!
 */
public enum LexemeType {

    ADD("(ADD)"),
    MOV("(MOV)"),
    XOR("(XOR)"),
    SEPARATOR("(,| )"),
    REGISTER("(R[1-4])"),
    MEMORY("(M[1-4])"),
    NUMBER("([0-9]+)"),
    COMMAND("(ADD|MOV|XOR)"),
    ARGUMENT("(" + REGISTER + "|" + MEMORY + "|" + NUMBER + ")"),
    COMMAND_LINE("(" + COMMAND.getRegex() +
            SEPARATOR + ARGUMENT + SEPARATOR + ARGUMENT + SEPARATOR + ARGUMENT + "\\n" + ")"),
    ERROR("ERROR");

    private final String regex;
    private final Pattern pattern;

    LexemeType(String regex) {
        this.regex = regex;
        this.pattern = Pattern.compile(regex);
    }

    @Override
    public String toString() {
        return regex;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public String getRegex() {
        return regex;
    }

    public static LexemeType getCommandLexeme(String line) {
        if (line == null) {
            return ERROR;
        }
        Matcher matcher = COMMAND.getPattern().matcher(line);
        String lexeme = "";
        if (matcher.find()) {
            lexeme = matcher.group();
        }
        if (lexeme.matches(ADD.toString())) {
            return ADD;
        }
        if (lexeme.matches(MOV.toString())) {
            return MOV;
        }
        if (lexeme.matches(XOR.toString())) {
            return XOR;
        }
        return ERROR;
    }

    public static LexemeArgument[] getArgumentLexemes(String line) {
        if (line == null) {
            return null;
        }
        Matcher matcher = ARGUMENT.getPattern().matcher(line);
        LexemeArgument[] args = new LexemeArgument[3];
        for (int i = 0; i < 3 && matcher.find(); i++) {
            String lexeme = matcher.group();
            if (lexeme.matches(REGISTER.toString())) {
                args[i] = new LexemeArgument(REGISTER, lexeme.charAt(1) - '0');
            } else if (lexeme.matches(MEMORY.toString())) {
                args[i] = new LexemeArgument(MEMORY, lexeme.charAt(1) - '0');
            } else if (lexeme.matches(NUMBER.toString())) {
                args[i] = new LexemeArgument(NUMBER, Integer.parseInt(lexeme));
            } else {
                return null;
            }

        }
        return args;
    }
}