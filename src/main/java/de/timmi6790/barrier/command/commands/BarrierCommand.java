package de.timmi6790.barrier.command.commands;


import de.timmi6790.barrier.Cache;
import de.timmi6790.barrier.MessageBuilder;
import de.timmi6790.barrier.command.AbstractCommand;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.EnumChatFormatting;

public class BarrierCommand extends AbstractCommand {
    private final Cache cache;

    public BarrierCommand(final Cache cache) {
        super("barrier");
        this.setPrefix("Debug");

        this.cache = cache;
    }

    @Override
    public void onCommand(final ICommandSender sender, final String[] args) {
        this.cache.setVisibleBarrier(!this.cache.isVisibleBarrier());
        if (this.cache.isVisibleBarrier()) {
            this.tell(
                    new MessageBuilder("Barrier blocks are now visible.", EnumChatFormatting.GRAY)
            );
        } else {
            this.tell(
                    new MessageBuilder("We are back to normal.", EnumChatFormatting.GRAY)
            );
        }

        // Reload all blocks
        Minecraft.getMinecraft().renderGlobal.loadRenderers();
    }
}
