package net.melonclient.minecraft189.transformer.transformers;

import net.melonclient.minecraft189.transformer.Transformer;
import net.melonclient.minecraft189.utils.asm.MethodNodeUtils;
import net.melonclient.minecraftapi.api.minecraft.font.TextRenderer;
import net.minecraft.client.gui.FontRenderer;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;

public class FontRendererTransformer extends Transformer {
    public FontRendererTransformer() {
        super(FontRenderer.class.getName());
    }

    /*
        fun draw(text: String, x: Float, y: Float, color: Int)
    fun drawWithShadow(text: String, x: Float, y: Float, color: Int)
    fun getCharacterWidth(character: Char, fontType: Int): Float
    fun getCharacterHeight(character: Char, fontType: Int): Float
    fun getStringWidth(text: String): Float
    fun getStringHeight(text: String): Float
     */
    @Override
    public ClassNode transform(ClassNode node) {
        node.interfaces.add(Type.getInternalName(TextRenderer.class));
        genDrawMethod(node, "draw", "(Ljava/lang/String;FFI)V", "drawString", "(Ljava/lang/String;III)I", true);
        genDrawMethod(node, "drawWithShadow", "(Ljava/lang/String;FFI)V", "drawStringWithShadow", "(Ljava/lang/String;FFI)I", false);
        genGetMethod(node, "getStringWidth", "(Ljava/lang/String;)F", "getStringWidth", "(Ljava/lang/String;)I", false);
        genGetMethod(node, "getCharacterWidth", "(CI)F", "getCharWidthFloat", "(C)F", true);
        genHeightMethod(node, "getStringHeight");
        genHeightMethod(node, "getCharacterHeight");

        return node;
    }

    private void genHeightMethod(ClassNode node, String methodName) {

        MethodNode methodNode = MethodNodeUtils.newMethodNode(methodName, "(Ljava/lang/String;)F");
        InsnList insnList = new InsnList();
        insnList.add(new LdcInsnNode(9f));
        insnList.add(new InsnNode(Opcodes.FRETURN));
        methodNode.instructions.add(insnList);
        node.methods.add(methodNode);
    }

    private void genDrawMethod(ClassNode node, String methodName, String methodDesc, String actualMethodName, String actualMethodDesc, boolean cast) {
        MethodNode methodNode = MethodNodeUtils.newMethodNode(methodName, methodDesc);

        InsnList insnList = new InsnList();
        insnList.add(new VarInsnNode(Opcodes.ALOAD, 0)); // this.
        insnList.add(new VarInsnNode(Opcodes.ALOAD, 1)); // text
        insnList.add(new VarInsnNode(Opcodes.FLOAD, 2)); // x
        if (cast)
            insnList.add(new InsnNode(Opcodes.F2I));
        insnList.add(new VarInsnNode(Opcodes.FLOAD, 3)); // y
        if (cast)
            insnList.add(new InsnNode(Opcodes.F2I));
        insnList.add(new VarInsnNode(Opcodes.ILOAD, 4)); // color
        insnList.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, node.name, actualMethodName, actualMethodDesc, false)); // this.draw(text, x, y, color);
        insnList.add(new InsnNode(Opcodes.RETURN));
        methodNode.instructions.add(insnList);
        node.methods.add(methodNode);
    }

    private void genGetMethod(ClassNode node, String methodName, String methodDesc, String actualMethodName, String actualMethodDesc, boolean character) {
        MethodNode methodNode = MethodNodeUtils.newMethodNode(methodName, methodDesc);

        InsnList insnList = new InsnList();
        insnList.add(new VarInsnNode(Opcodes.ALOAD, 0)); // this.
        insnList.add(new VarInsnNode(character ? Opcodes.ILOAD : Opcodes.ALOAD, 1));
        insnList.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, node.name, actualMethodName, actualMethodDesc, false)); // this.getStringWidth(text);
        if(!character) {
            insnList.add(new InsnNode(Opcodes.I2F));
        }
        insnList.add(new InsnNode(Opcodes.FRETURN));
        methodNode.instructions.add(insnList);
        node.methods.add(methodNode);
    }
}
