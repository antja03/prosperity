package net.melonclient.minecraft189.transformer.transformers;

import org.objectweb.asm.Type;
import net.melonclient.minecraft189.transformer.Transformer;
import net.minecraft.entity.Entity;
import org.objectweb.asm.tree.ClassNode;

public class EntityClassTransformer extends Transformer {
    public EntityClassTransformer() {
        super(Entity.class.getName());
    }

    @Override
    public ClassNode transform(ClassNode node) {

        node.interfaces.add(Type.getInternalName(net.melonclient.minecraftapi.api.minecraft.entity.Entity.class));

        return node;
    }
}
