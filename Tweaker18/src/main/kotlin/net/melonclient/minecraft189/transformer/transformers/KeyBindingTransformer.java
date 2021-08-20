package net.melonclient.minecraft189.transformer.transformers;

import net.melonclient.minecraft189.transformer.Transformer;
import net.melonclient.minecraft189.utils.asm.MethodNodeUtils;
import net.minecraft.client.settings.KeyBinding;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;

public class KeyBindingTransformer extends Transformer {
    public KeyBindingTransformer() {
        super(KeyBinding.class.getName());
    }

    @Override
    public ClassNode transform(ClassNode node) {
        MethodNodeUtils.getMethodGen(node, "getPressed", "()Z", "pressed", "Z", Opcodes.IRETURN);
        MethodNodeUtils.setMethodGen(node, "setPressed", "(Z)V", "pressed", "Z", false, Opcodes.ILOAD);
        return node;
    }
}
