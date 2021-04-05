package de.timmi6790.barrier.mixins.blocks;


import de.timmi6790.barrier.Cache;
import de.timmi6790.barrier.McMod;
import lombok.Getter;
import net.minecraft.block.BlockBarrier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBarrier.class)
public class MixinBlockBarrier {
    @Getter(lazy = true)
    private final Cache cache = McMod.getInstance().getCache();

    @Inject(method = "getRenderType", at = @At("HEAD"), cancellable = true)
    public void getRenderType(final CallbackInfoReturnable<Integer> cir) {
        if (this.getCache().isVisibleBarrier()) {
            cir.setReturnValue(3);
        }
    }
}
