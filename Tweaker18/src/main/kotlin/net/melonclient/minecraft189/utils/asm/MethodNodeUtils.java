package net.melonclient.minecraft189.utils.asm;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.util.ArrayList;

public class MethodNodeUtils {

    public static void getMethodGen(ClassNode classNode, String methodName, String methodDesc, String fieldName, String fieldDesc, int returnOpcode) {

        MethodNode methodNode = newMethodNode(methodName, methodDesc);

        InsnList insnList = new InsnList();
        insnList.add(new VarInsnNode(Opcodes.ALOAD, 0));
        insnList.add(new FieldInsnNode(Opcodes.GETFIELD, classNode.name, fieldName, fieldDesc));
        insnList.add(new InsnNode(returnOpcode));
        methodNode.instructions.add(insnList);
        classNode.methods.add(methodNode);
    }

    public static void setMethodGen(ClassNode classNode, String methodName, String methodDesc, String fieldName, String fieldDesc, boolean isFinal, int opcode) {
        if (isFinal) {
            for (FieldNode field : classNode.fields) {
                if (field.name.equals(fieldName)) {
                    field.access = field.access & ~Opcodes.ACC_FINAL;
                    break;
                }
            }
        }

        MethodNode methodNode = newMethodNode(methodName, methodDesc);

        InsnList insnList = new InsnList();
        insnList.add(new VarInsnNode(Opcodes.ALOAD, 0));
        insnList.add(new VarInsnNode(opcode, 1));
        insnList.add(new FieldInsnNode(Opcodes.PUTFIELD, classNode.name, fieldName, fieldDesc));
        insnList.add(new InsnNode(Opcodes.RETURN));
        methodNode.instructions.add(insnList);
        classNode.methods.add(methodNode);
    }

    public static MethodNode newMethodNode(String methodName, String methodDesc) {
        MethodNode methodNode = new MethodNode();
        methodNode.exceptions = new ArrayList<>();
        methodNode.name = methodName;
        methodNode.desc = methodDesc;
        methodNode.access = Opcodes.ACC_PUBLIC;
        return methodNode;
    }

}
