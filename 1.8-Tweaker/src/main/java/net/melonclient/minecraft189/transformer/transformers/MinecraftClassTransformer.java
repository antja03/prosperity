package net.melonclient.minecraft189.transformer.transformers;

import net.melonclient.minecraft189.interfaces.MinecraftHook;
import net.melonclient.minecraft189.utils.asm.MethodNodeUtils;
import org.objectweb.asm.Type;
import net.melonclient.minecraft189.impl.minecraft.MinecraftApi;
import net.melonclient.minecraft189.transformer.Transformer;
import net.melonclient.minecraftapi.MinecraftApiInterfacingAgent;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;
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

        }
        node.interfaces.add(Type.getInternalName(MinecraftHook.class));
        MethodNodeUtils.setMethodGen(node, "setSession", "(Ljava/lang/String;)V", "session", "Ljava/lang/String;", true);

        return node;
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
