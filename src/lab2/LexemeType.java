package lab2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by max on 23.03.15.
 * Have fun!
 */
public enum LexemeType {

    ADD("ADD", "(ADD)"),
    MOV("MOV", "(MOV)"),
    XOR("XOR", "(XOR)"),
    SEPARATOR("SEPARATOR", "(,? *)"),
    REGISTER("REGISTER", "(R[1-4])"),
    MEMORY("MEMORY", "(M[1-4])"),
    NUMBER("NUMBER", "-?(([1-9][0-9]{0,8})|0)"),
    COMMAND("COMMAND", "(ADD|MOV|XOR)"),
    REG_MEM("REGISTER_OR_MEMORY", "(" + REGISTER + "|" + MEMORY + ")"),
    ARGUMENT("ARGUMENT", "(" + REGISTER + "|" + MEMORY + "|" + NUMBER + ")"),
    COMMAND_LINE("COMMAND_LINE", "(" + COMMAND +
            SEPARATOR + REG_MEM + SEPARATOR + ARGUMENT + SEPARATOR + ARGUMENT + ")"),
    ERROR("ERROR", "ERROR");

    private final String name;
    private final String regex;
    private final Pattern pattern;

    LexemeType(String name, String regex) {
        this.name = name;
        this.regex = regex;
        this.pattern = Pattern.compile(regex);
    }

    @Override
    public String toString() {
        return regex;
    }

    public String getName() {
        return name;
    }

    public String getRegex() {
        return regex;
    }

    public Pattern getPattern() {
        return pattern;
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
        if (lexeme.matches(ADD.getRegex())) {
            return ADD;
        }
        if (lexeme.matches(MOV.getRegex())) {
            return MOV;
        }
        if (lexeme.matches(XOR.getRegex())) {
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
            if (lexeme.matches(REGISTER.getRegex())) {
                args[i] = new LexemeArgument(REGISTER, lexeme.charAt(1) - '1');
            } else if (lexeme.matches(MEMORY.getRegex())) {
                args[i] = new LexemeArgument(MEMORY, lexeme.charAt(1) - '1');
            } else if (lexeme.matches(NUMBER.getRegex())) {
                args[i] = new LexemeArgument(NUMBER, Integer.parseInt(lexeme));
            } else {
                return null;
            }

        }
        return args;
    }
}