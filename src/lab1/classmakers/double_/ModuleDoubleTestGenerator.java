package lab1.classmakers.double_;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Label;
import jdk.internal.org.objectweb.asm.MethodVisitor;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

/**
 * Created by max on 11.03.15.
 * Have fun!
 */
public class ModuleDoubleTestGenerator {

    public static byte[] dump() {
        ClassWriter cw = new ClassWriter(0);
        MethodVisitor mv;

        cw.visit(V1_5,
                ACC_PUBLIC + ACC_SUPER,
                "lab1/functions/double_/ModuleDoubleTest",
                null,
                "java/lang/Object",
                new String[] { "lab1/functions/FunctionTest" });

        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL,
                    "java/lang/Object",
                    "<init>",
                    "()V");
            mv.visitInsn(RETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }

        {
            mv = cw.visitMethod(ACC_PUBLIC, "process", "(I)V", null, null);
            mv.visitInsn(ICONST_0);
            mv.visitVarInsn(ISTORE, 2);
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitVarInsn(ILOAD, 2);
            mv.visitVarInsn(ILOAD, 1);
            Label l1 = new Label();
            mv.visitJumpInsn(IF_ICMPGE, l1);
            for (int i = 0; i < 5000; i++) {
                mv.visitInsn(DCONST_0);
                mv.visitInsn(DCONST_1);
                mv.visitInsn(DREM);
                mv.visitVarInsn(DSTORE, 3);
            }
            mv.visitIincInsn(2, 1);
            mv.visitJumpInsn(GOTO, l0);
            mv.visitLabel(l1);
            mv.visitInsn(RETURN);
            mv.visitMaxs(10, 10);
        }

        return cw.toByteArray();
    }

}
