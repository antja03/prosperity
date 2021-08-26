package net.melonclient.minecraft189.transformer.transformers;

import org.objectweb.asm.Type;
import net.minecraft.client.Minecraft;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.util.ListIterator;

public class MinecraftClassTransformer extends Transformer {

    public MinecraftClassTransformer() {
        super(Minecraft.class.getName());
    }

    @Override
    public ClassNode transform(ClassNode node) {
        for (MethodNode method : node.methods) {
            startGenCode(method);
            gameTickGenCode(method);
        }
        node.interfaces.add(Type.getInternalName(MinecraftHook.class));
        MethodNodeUtils.setMethodGen(node, "setSession", "(Ljava/lang/String;)V", "session", "Ljava/lang/String;", true, Opcodes.ALOAD);

        return node;
    }
    private void gameTickGenCode(MethodNode methodNode) {
        if(methodNode.name.startsWith("runTick")) {
            InsnList insnList = new InsnList();
//            GameTickEvent.INSTANCE.dispatch();
            insnList.add(new FieldInsnNode(Opcodes.GETSTATIC, "net/melonclient/client/api/event/impl/GameTickEvent", "INSTANCE", "Lnet/melonclient/client/api/event/impl/GameTickEvent;"));
            insnList.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "net/melonclient/client/api/event/Event", "dispatch", "()V", false));
            methodNode.instructions.insert(methodNode.instructions.getFirst(), insnList);
        }
    }

    private void startGenCode(MethodNode method) {
        if (method.name.startsWith("startG")) {
            for (ListIterator<AbstractInsnNode> it = method.instructions.iterator(); it.hasNext(); ) {
                AbstractInsnNode insnNode = it.next();
                if (insnNode instanceof LdcInsnNode && ((LdcInsnNode) insnNode).cst instanceof String && ((LdcInsnNode) insnNode).cst.equals("Post startup")) {
                    insnNode = insnNode.getNext();
                    InsnList insnList = new InsnList();
                    insnList.add(new FieldInsnNode(Opcodes.GETSTATIC, "net/melonclient/minecraft189/ClientInitializer", "INSTANCE", "Lnet/melonclient/minecraft189/ClientInitializer;"));
                    insnList.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "net/melonclient/minecraft189/ClientInitializer", "startup", "()V", false));
                    method.instructions.insert(insnNode, insnList);
                    break;
                }

            }
        }
    }
}
