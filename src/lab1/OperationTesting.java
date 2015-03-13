package lab1;

import lab1.functions.FunctionTest;
import lab1.functions.double_.*;
import lab1.functions.integer.*;
import lab1.functions.long_.*;
import lab1.functions.float_.*;

/**
 * Created by max on 05.03.15.
 * Have fun!
 */

public class OperationTesting {

    public static final int DEFAULT_COUNT = 200000;
    public static final int OPERATIONS_COUNT = 5000;

    private int count;
    private long emptyLoopTime;

    {
        System.out.println("Boosting processor...");
        boostProcessor();
    }

    public OperationTesting() {
        count = DEFAULT_COUNT;
    }

    enum Operation {
        PLUS,
        MINUS,
        MULTIPLY,
        DIVIDE,
        MOD,
        EMPTY
    }

    long testFunction(FunctionTest function) {
        long startTime = System.currentTimeMillis();
        function.process(count);
        long finishTime = System.currentTimeMillis();
        return finishTime - startTime - emptyLoopTime;
    }

    long intTest(Operation operation) {
        switch (operation) {
            case PLUS:
                return testFunction(new PlusIntTest());
            case MINUS:
                return testFunction(new MinusIntTest());
            case MULTIPLY:
                return testFunction(new MultiplyIntTest());
            case DIVIDE:
                return testFunction(new DivideIntTest());
            case MOD:
                return testFunction(new ModuleIntTest());
            case EMPTY:
                return testFunction(new EmptyLoopIntTest());
        }
        return 0;
    }

    long doubleTest(Operation operation) {
        switch (operation) {
            case PLUS:
                return testFunction(new PlusDoubleTest());
            case MINUS:
                return testFunction(new MinusDoubleTest());
            case MULTIPLY:
                return testFunction(new MultiplyDoubleTest());
            case DIVIDE:
                return testFunction(new DivideDoubleTest());
            case MOD:
                return testFunction(new ModuleDoubleTest());
            case EMPTY:
                return testFunction(new EmptyLoopDoubleTest());
        }
        return 0;
    }

    long longTest(Operation operation) {
        switch (operation) {
            case PLUS:
                return testFunction(new PlusLongTest());
            case MINUS:
                return testFunction(new MinusLongTest());
            case MULTIPLY:
                return testFunction(new MultiplyLongTest());
            case DIVIDE:
                return testFunction(new DivideLongTest());
            case MOD:
                return testFunction(new ModuleLongTest());
            case EMPTY:
                return testFunction(new EmptyLoopLongTest());
        }
        return 0;
    }

    long floatTest(Operation operation) {
        switch (operation) {
            case PLUS:
                return testFunction(new PlusFloatTest());
            case MINUS:
                return testFunction(new MinusFloatTest());
            case MULTIPLY:
                return testFunction(new MultiplyFloatTest());
            case DIVIDE:
                return testFunction(new DivideFloatTest());
            case MOD:
                return testFunction(new ModuleFloatTest());
            case EMPTY:
                return testFunction(new EmptyLoopFloatTest());
        }
        return 0;
    }

    public void printResults() {
        printIntResults();
        printLongResults();
        printDoubleResults();
        printFloatResults();
    }

    public void printFormat(String type, String operation, long time) {
        int speed = (int)(DEFAULT_COUNT * OPERATIONS_COUNT / time / 1000);
        System.out.printf("%6s %4s %4dMps %s\n", type, operation, speed, getSharps(speed / 10));
    }

    public String getSharps(int percentage) {
        StringBuilder sharps = new StringBuilder();
        for (int i = 0; i < percentage * 30 / 100; i++) {
            sharps.append('#');
        }
        return sharps.toString();
    }

    public void printIntResults() {
        String type = "int";
        emptyLoopTime = 0;
        emptyLoopTime = intTest(Operation.EMPTY);
        printFormat(type, "loop", emptyLoopTime);
        printFormat(type, "+", intTest(Operation.PLUS));
        printFormat(type, "-", intTest(Operation.MINUS));
        printFormat(type, "*", intTest(Operation.MULTIPLY));
        printFormat(type, "/", intTest(Operation.DIVIDE));
        printFormat(type, "%", intTest(Operation.MOD));
    }

    public void printLongResults() {
        String type = "long";
        emptyLoopTime = 0;
        emptyLoopTime = longTest(Operation.EMPTY);
        printFormat(type, "loop", emptyLoopTime);
        printFormat(type, "+", longTest(Operation.PLUS));
        printFormat(type, "-", longTest(Operation.MINUS));
        printFormat(type, "*", longTest(Operation.MULTIPLY));
        printFormat(type, "/", longTest(Operation.DIVIDE));
        printFormat(type, "%", longTest(Operation.MOD));
    }

    public void printDoubleResults() {
        String type = "double";
        emptyLoopTime = 0;
        emptyLoopTime = doubleTest(Operation.EMPTY);
        printFormat(type, "loop", emptyLoopTime);
        printFormat(type, "+", doubleTest(Operation.PLUS));
        printFormat(type, "-", doubleTest(Operation.MINUS));
        printFormat(type, "*", doubleTest(Operation.MULTIPLY));
        printFormat(type, "/", doubleTest(Operation.DIVIDE));
        printFormat(type, "%", doubleTest(Operation.MOD));
    }

    public void printFloatResults() {
        String type = "float";
        emptyLoopTime = 0;
        emptyLoopTime = floatTest(Operation.EMPTY);
        printFormat(type, "loop", emptyLoopTime);
        printFormat(type, "+", floatTest(Operation.PLUS));
        printFormat(type, "-", floatTest(Operation.MINUS));
        printFormat(type, "*", floatTest(Operation.MULTIPLY));
        printFormat(type, "/", floatTest(Operation.DIVIDE));
        printFormat(type, "%", floatTest(Operation.MOD));
    }

    public void boostProcessor() {
        long a, b, c;
        for (long i = 0; i < 20000000000L; i++) {
            a = i / 2;
            b = i + 2;
            c = 2 * a + b;
            b = c + a;
        }
    }

    public static void main(String[] args) {
        OperationTesting testing = new OperationTesting();
        testing.printResults();
    }

}
