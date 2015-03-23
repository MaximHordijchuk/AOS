package lab2;

/**
 * Created by max on 23.03.15.
 * Have fun!
 *
 * ADD, MOV, XOR
 */
public class ProcessorModel {

    int[] R, M;

    {
        R = new int[4];
        M = new int[4];
    }



    public boolean parseLine(String line) {
        System.out.println(LexemeType.COMMAND_LINE.getRegex());
        if (line == null || !line.matches(LexemeType.COMMAND_LINE.getRegex())) {
            return false;
        }
        LexemeType commandType = LexemeType.getCommandLexeme(line);
        LexemeArgument[] args = LexemeType.getArgumentLexemes(line);
        doCommand(commandType, args);
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
            default:
                printErrorMessage();
        }
    }

    public void printErrorMessage() {
        System.out.println("WRONG COMMAND");
    }

    public void add(LexemeArgument[] args) {

    }

    public void mov(LexemeArgument[] args) {

    }

    public void xor(LexemeArgument[] args) {

    }

    public static void main(String[] args) {
        ProcessorModel model = new ProcessorModel();
        model.parseLine("ADD R1,2,M2\n");
    }
}
