package lab1;

import lab1.classmakers.double_.*;
import lab1.classmakers.float_.*;
import lab1.classmakers.integer.EmptyLoopIntTestGenerator;
import lab1.classmakers.integer.*;
import lab1.classmakers.long_.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by max on 11.03.15.
 * Have fun!
 */
public class ClassGenerator {

    public static void main(String[] args) throws IOException {
        ClassGenerator gc = new ClassGenerator();
        gc.writeEmptyLoopTest("lab1/functions/integer/EmptyLoopIntTest.class");
        gc.writePlusIntTest("lab1/functions/integer/PlusIntTest.class");
        gc.writeMinusIntTest("lab1/functions/integer/MinusIntTest.class");
        gc.writeMultiplyIntTest("lab1/functions/integer/MultiplyIntTest.class");
        gc.writeDivideIntTest("lab1/functions/integer/DivideIntTest.class");
        gc.writeModuleIntTest("lab1/functions/integer/ModuleIntTest.class");

        gc.writeEmptyLoopDoubleTest("lab1/functions/double_/EmptyLoopDoubleTest.class");
        gc.writePlusDoubleTest("lab1/functions/double_/PlusDoubleTest.class");
        gc.writeMinusDoubleTest("lab1/functions/double_/MinusDoubleTest.class");
        gc.writeMultiplyDoubleTest("lab1/functions/double_/MultiplyDoubleTest.class");
        gc.writeDivideDoubleTest("lab1/functions/double_/DivideDoubleTest.class");
        gc.writeModuleDoubleTest("lab1/functions/double_/ModuleDoubleTest.class");

        gc.writeEmptyLoopLongTest("lab1/functions/long_/EmptyLoopLongTest.class");
        gc.writePlusLongTest("lab1/functions/long_/PlusLongTest.class");
        gc.writeMinusLongTest("lab1/functions/long_/MinusLongTest.class");
        gc.writeMultiplyLongTest("lab1/functions/long_/MultiplyLongTest.class");
        gc.writeDivideLongTest("lab1/functions/long_/DivideLongTest.class");
        gc.writeModuleLongTest("lab1/functions/long_/ModuleLongTest.class");

        gc.writeEmptyLoopFloatTest("lab1/functions/float_/EmptyLoopFloatTest.class");
        gc.writePlusFloatTest("lab1/functions/float_/PlusFloatTest.class");
        gc.writeMinusFloatTest("lab1/functions/float_/MinusFloatTest.class");
        gc.writeMultiplyFloatTest("lab1/functions/float_/MultiplyFloatTest.class");
        gc.writeDivideFloatTest("lab1/functions/float_/DivideFloatTest.class");
        gc.writeModuleFloatTest("lab1/functions/float_/ModuleFloatTest.class");
    }

    public void writeEmptyLoopTest(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        os.write(EmptyLoopIntTestGenerator.dump());
    }

    public void writePlusIntTest(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        os.write(PlusIntTestGenerator.dump());
    }

    public void writeMinusIntTest(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        os.write(MinusIntTestGenerator.dump());
    }

    public void writeMultiplyIntTest(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        os.write(MultiplyIntTestGenerator.dump());
    }

    public void writeDivideIntTest(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        os.write(DivideIntTestGenerator.dump());
    }

    public void writeModuleIntTest(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        os.write(ModuleIntTestGenerator.dump());
    }

    public void writeEmptyLoopDoubleTest(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        os.write(EmptyLoopDoubleTestGenerator.dump());
    }

    public void writePlusDoubleTest(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        os.write(PlusDoubleTestGenerator.dump());
    }

    public void writeMinusDoubleTest(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        os.write(MinusDoubleTestGenerator.dump());
    }

    public void writeMultiplyDoubleTest(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        os.write(MultiplyDoubleTestGenerator.dump());
    }

    public void writeDivideDoubleTest(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        os.write(DivideDoubleTestGenerator.dump());
    }

    public void writeModuleDoubleTest(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        os.write(ModuleDoubleTestGenerator.dump());
    }

    public void writeEmptyLoopLongTest(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        os.write(EmptyLoopLongTestGenerator.dump());
    }

    public void writePlusLongTest(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        os.write(PlusLongTestGenerator.dump());
    }

    public void writeMinusLongTest(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        os.write(MinusLongTestGenerator.dump());
    }

    public void writeMultiplyLongTest(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        os.write(MultiplyLongTestGenerator.dump());
    }

    public void writeDivideLongTest(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        os.write(DivideLongTestGenerator.dump());
    }

    public void writeModuleLongTest(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        os.write(ModuleLongTestGenerator.dump());
    }

    public void writeEmptyLoopFloatTest(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        os.write(EmptyLoopFloatTestGenerator.dump());
    }

    public void writePlusFloatTest(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        os.write(PlusFloatTestGenerator.dump());
    }

    public void writeMinusFloatTest(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        os.write(MinusFloatTestGenerator.dump());
    }

    public void writeMultiplyFloatTest(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        os.write(MultiplyFloatTestGenerator.dump());
    }

    public void writeDivideFloatTest(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        os.write(DivideFloatTestGenerator.dump());
    }

    public void writeModuleFloatTest(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        os.write(ModuleFloatTestGenerator.dump());
    }

}
