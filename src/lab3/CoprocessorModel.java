package lab3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by max on 26.04.15.
 * Have fun!
 */
public class CoprocessorModel {
    LinkedList<MyFloat> stack = new LinkedList<MyFloat>();
    MyFloat x;

    public CoprocessorModel() {
        for (int i = 0; i < 8; i++) {
            stack.addLast(new MyFloat(new Random().nextDouble()));
        }
        x = new MyFloat(new Random().nextDouble());
    }

    public void fild(double value) {
        stack.set(0, new MyFloat(value));
    }

    public void fild(MyFloat value) {
        stack.set(0, new MyFloat(value));
    }

    public void filp(double value) {
        stack.addFirst(new MyFloat(value));
        stack.removeLast();
    }

    public void filp(MyFloat value) {
        stack.addFirst(new MyFloat(value));
        stack.removeLast();
    }

    public void fist() {
        x = stack.getFirst();
    }

    public void fistp() {
        x = stack.getFirst();
        stack.removeFirst();
        stack.addLast(new MyFloat(new Random().nextDouble()));
    }

    public void dub() {
        stack.addFirst(new MyFloat(stack.getFirst()));
        stack.removeLast();
    }

    public void swap() {
        MyFloat temp = stack.get(0);
        stack.set(0, stack.get(1));
        stack.set(1, temp);
    }

    public void add() {
        stack.set(0, stack.get(0).add(stack.get(1)));
    }

    public void sub() {
        stack.set(0, stack.get(0).sub(stack.get(1)));
    }

    public void mult() {
        stack.set(0, stack.get(0).mult(stack.get(1)));
    }

    public void div() {
        stack.set(0, stack.get(0).div(stack.get(1)));
    }

    public void printState() {
        System.out.println("STACK:");
        for (int i = 0; i < stack.size(); i++) {
            System.out.println(i + " : " + stack.get(i));
        }
        System.out.println("X : " + x);
    }

    public void doCommand(String[] args) {
        try {
            if (args.length == 2 && args[0].equals("FILD")) {
                if (args[1].equals("X")) {
                    fild(x);
                } else {
                    fild(Double.parseDouble(args[1]));
                }
            } else if (args.length == 2 && args[0].equals("FILP")) {
                if (args[1].equals("X")) {
                    filp(x);
                } else {
                    filp(Double.parseDouble(args[1]));
                }
            } else if (args.length == 1 && args[0].equals("FIST")) {
                fist();
            } else if (args.length == 1 && args[0].equals("FISTP")) {
                fistp();
            } else if (args.length == 1 && args[0].equals("DUB")) {
                dub();
            } else if (args.length == 1 && args[0].equals("SWAP")) {
                swap();
            } else if (args.length == 1 && args[0].equals("DUB")) {
                dub();
            } else if (args.length == 1 && args[0].equals("ADD")) {
                add();
            } else if (args.length == 1 && args[0].equals("SUB")) {
                sub();
            } else if (args.length == 1 && args[0].equals("MULT")) {
                mult();
            } else if (args.length == 1 && args[0].equals("DIV")) {
                div();
            } else {
                System.out.println("Unsupported command");
            }
        } catch (NumberFormatException ex) {
            System.out.println("Unsupported command");
        }
    }

    public static void main(String[] args) {
        CoprocessorModel model = new CoprocessorModel();
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
            String[] command = line.split(" ");
            if (command.length > 2) {
                System.out.println("Unsupported command");
            }
            System.out.println(line);
            model.doCommand(command);
            model.printState();
        }
    }

}
