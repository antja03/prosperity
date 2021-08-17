package net.melonclient.minecraft189.transformer;

import net.minecraft.launchwrapper.IClassTransformer;
import org.apache.commons.io.FileUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import java.io.File;
import java.io.IOException;

public abstract class Transformer implements IClassTransformer {
    private final String name;

    public Transformer(String name) {
        this.name = name;
    }

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {

        if (this.name.equals(name)) {
            byte[] bytes = classWriter(transform(classParser(basicClass)));
            if (name.contains("Minecraft")) {
                try {
                    FileUtils.writeByteArrayToFile(new File("Minecraft.class"), bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("[MinecraftAPI]: Transformed " + name + " with " + this.getClass().getName());
            return bytes;
        }
        return basicClass;
    }

    public abstract ClassNode transform(ClassNode node);

    private ClassNode classParser(byte[] bytes) {
        ClassReader classReader = new ClassReader(bytes);
        ClassNode node = new ClassNode();
        classReader.accept(node, ClassReader.EXPAND_FRAMES);
        return node;
    }

    private byte[] classWriter(ClassNode node) {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        node.accept(classWriter);
        return classWriter.toByteArray();
    }

}
