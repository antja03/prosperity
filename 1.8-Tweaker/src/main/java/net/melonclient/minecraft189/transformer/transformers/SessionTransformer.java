package net.melonclient.minecraft189.transformer.transformers;

import net.melonclient.minecraft189.transformer.Transformer;
import net.melonclient.minecraft189.utils.asm.MethodNodeUtils;
import net.minecraft.util.Session;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;

public class SessionTransformer extends Transformer {
    public SessionTransformer() {
        super(Session.class.getName());
    }

    @Override
    public ClassNode transform(ClassNode node) {
        node.interfaces.add(Type.getInternalName(net.melonclient.minecraftapi.api.minecraft.auth.Session.class));
        MethodNodeUtils.getMethodGen(node, "getSessionId", "()Ljava/lang/String;", "token", "Ljava/lang/String;");
        MethodNodeUtils.getMethodGen(node, "getUuid", "()Ljava/lang/String;", "playerID", "Ljava/lang/String;");
        MethodNodeUtils.setMethodGen(node, "setSessionId", "(Ljava/lang/String;)V", "token", "Ljava/lang/String;", true);
        MethodNodeUtils.setMethodGen(node, "setUuid", "(Ljava/lang/String;)V", "playerID", "Ljava/lang/String;", true);
        MethodNodeUtils.setMethodGen(node, "setUsername", "(Ljava/lang/String;)V", "username", "Ljava/lang/String;", true);
        return node;
    }


}
