package lab2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by max on 23.03.15.
 * Have fun!
 *
 * 3 addresses
 * 20 bits
 * ADD, MOV, XOR
 * Flags: Overflow Flag, Carry Flag, Sign Flag
 */
public class ProcessorModel {

    public static final int BITS_NUMBER = 20;

    int[] R, M;

    boolean overflowFlag;
    boolean signFlag;
    boolean carryFlag;

    {
        R = new int[4];
        M = new int[4];
    }


    public boolean parseLine(String line) {
        if (line == null || !line.matches(LexemeType.COMMAND_LINE.getRegex())) {
            printErrorMessage();
            return false;
        }
        LexemeType commandType = LexemeType.getCommandLexeme(line);
        LexemeArgument[] args = LexemeType.getArgumentLexemes(line);
        clearFlags();
        castArgs(args);
        printCommand(commandType, args);
        doCommand(commandType, args);
        printInternalMemory();
        return true;
    }

    public void doCommand(LexemeType command, LexemeArgument[] args) {
        switch (command) {
            case ADD:
                add(args);
                break;
            case MOV:
                mov(args);
                break;
            case XOR:
                xor(args);
                break;
        }
        castInternalMemory();
    }

    public void printErrorMessage() {
        System.out.println("WRONG COMMAND");
    }

    private void printCommand(LexemeType command, LexemeArgument[] args) {
        int operationCode = getCommandTypeId(command) * 18 + getArgumentTypeId(args[0]) * 9 +
                getArgumentTypeId(args[1]) * 3 + getArgumentTypeId(args[2]);
        System.out.printf("%s=(%s) %s=(%s), %s=(%s), %s=(%s)\n\n",
                command.getName(), toBinaryString(operationCode),
                args[0].toString(), toBinaryString(getValue(args[0])),
                args[1].toString(), toBinaryString(getValue(args[1])),
                args[2].toString(), toBinaryString(getValue(args[2])));

    }

    public void printInternalMemory() {
        for (int i = 0; i < 4; i++) {
            System.out.println("R" + (i + 1) + " = " + toBinaryString(R[i]) + "(" + R[i] + ")");
        }
        System.out.println();
        for (int i = 0; i < 4; i++) {
            System.out.println("M" + (i + 1) + " = " + toBinaryString(M[i]) + "(" + M[i] + ")");
        }
        System.out.println();
        System.out.println("OF = " + overflowFlag);
        System.out.println("CF = " + carryFlag);
        System.out.println("SF = " + signFlag);
    }

    private void mov(LexemeArgument[] args) {
        if (args[1].type == LexemeType.NUMBER) {
            int value = getValue(args[1]);
            movTo(args[0], value);
            signFlag = signFlagCheck(value);
            return;
        }
        int value = getValue(args[2]);
        movTo(args[1], value);
        movTo(args[0], value);
        signFlag = signFlagCheck(value);
    }

    public void add(LexemeArgument[] args) {
        int value1 = getValue(args[1]);
        int value2 = getValue(args[2]);
        int sum = value1 + value2;
        if (carryFlagCheck(sum)) {
            carryFlag = true;
        }
        overflowFlag = overflowFlagCheck(value1, value2);
        signFlag = signFlagCheck(sum);
        movTo(args[0], castValue(sum));
    }

    public void xor(LexemeArgument[] args) {
        int xor = getValue(args[1]) ^ getValue(args[2]);
        signFlag = signFlagCheck(xor);
        movTo(args[0], castValue(xor));
    }

    private void movTo(LexemeArgument arg, int value) {
        switch (arg.type) {
            case REGISTER:
                R[arg.value] = value;
                break;
            case MEMORY:
                M[arg.value] = value;
                break;
        }
    }

    private int getValue(LexemeArgument arg) {
        switch (arg.type) {
            case REGISTER:
                return R[arg.value];
            case MEMORY:
                return M[arg.value];
            case NUMBER:
                return arg.value;
        }
        return 0;
    }

    private void castInternalMemory() {
        for (int i = 0; i < 4; i++) {
            R[i] = castValue(R[i]);
            M[i] = castValue(M[i]);
        }
    }

    private void castArgs(LexemeArgument[] args) {
        for (LexemeArgument arg : args) {
            if (arg.type == LexemeType.NUMBER) {
                int value = getValue(arg);
                if (carryFlagCheck(value)) {
                    carryFlag = true;
                }
                arg.value = castValue(value);
            }
        }
    }

    private int castValue(int value) {
        value &= ((1 << (BITS_NUMBER)) - 1);
        if (((1 << (BITS_NUMBER - 1)) & value) != 0) {
            value |= (((1 << (32 - BITS_NUMBER)) - 1) << BITS_NUMBER);
        }
        return value;
    }
    
    private String toBinaryString(int value) {
        StringBuilder result = new StringBuilder(25);
        value &= ((1 << BITS_NUMBER) - 1);
        String stringValue = Integer.toBinaryString(value);
        for (int i = 0; i < BITS_NUMBER - stringValue.length(); i++) {
            result.append('0');
        }
        result.append(stringValue);
        for (int i = BITS_NUMBER / 4 - 1; i > 0; i--) {
            result.insert(4 * i, ' ');
        }
        return result.toString();
    }

    private int getArgumentTypeId(LexemeArgument arg) {
        switch (arg.type) {
            case REGISTER:
                return 0;
            case MEMORY:
                return 1;
            case NUMBER:
                return 2;
        }
        return -1;
    }

    private int getCommandTypeId(LexemeType type) {
        switch (type) {
            case MOV:
                return 0;
            case ADD:
                return 1;
            case XOR:
                return 2;
        }
        return -1;
    }

    private boolean overflowFlagCheck(int value1, int value2) {
        int sum = castValue(value1 + value2);
        return (value1 > 0 && value2 > 0 && sum < 0) ||
                (value1 < 0 && value2 < 0 && sum > 0);
    }

    private boolean carryFlagCheck(int value) {
        return value >= (1 << BITS_NUMBER) || value < -(1 << BITS_NUMBER);
    }

    private boolean signFlagCheck(int value) {
        return castValue(value) < 0;
    }

    private void clearFlags() {
        overflowFlag = false;
        carryFlag = false;
        signFlag = false;
    }

    public static void main(String[] args) {
        ProcessorModel model = new ProcessorModel();
        Scanner scanner;
        if (args.length > 0) {
            try {
                scanner = new Scanner(new File(args[0]));
            } catch (FileNotFoundException e) {
                System.out.println("Could now open the file");
                return;
            }
        } else {
            scanner = new Scanner(System.in);
        }
        int i = 1;
        while (scanner.hasNext()) {
            System.out.print("[" + i++ + "]: ");
            String line = scanner.nextLine();
            model.parseLine(line);
            System.out.println();
        }
    }
}
