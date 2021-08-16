package net.melonclient.minecraft189.transformer.transformers;

import org.objectweb.asm.Type;
import net.melonclient.minecraft189.transformer.Transformer;
import net.melonclient.minecraftapi.api.minecraft.entity.LivingEntity;
import net.minecraft.entity.EntityLivingBase;
import org.objectweb.asm.tree.ClassNode;

public class EntityLivingBaseTransformer extends Transformer {
    public EntityLivingBaseTransformer() {
        super(EntityLivingBase.class.getName());
    }

    @Override
    public ClassNode transform(ClassNode node) {
        for (String anInterface : node.interfaces) {
            System.out.println(anInterface);
        }
        node.interfaces.add(Type.getInternalName(LivingEntity.class));

        return node;
    }
}
