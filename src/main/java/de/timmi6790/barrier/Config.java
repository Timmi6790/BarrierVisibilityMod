package de.timmi6790.barrier;

import lombok.Data;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Data
public class Config {
    private final Cache cache;
    private final Configuration configuration;

    private boolean visibleBarrierBlocks;

    public Config(final Cache cache, final Configuration configuration) {
        this.cache = cache;
        this.configuration = configuration;

        this.loadConfiguration();
    }

    public void loadConfiguration() {
        this.visibleBarrierBlocks = this.configuration.getBoolean(
                "visibleBarrierBlocks",
                Configuration.CATEGORY_GENERAL,
                false,
                ""
        );

        this.cache.setVisibleBarrier(this.visibleBarrierBlocks);
        if (this.configuration.hasChanged()) {
            this.configuration.save();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangeEvent(final ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equalsIgnoreCase(Reference.MODID)) {
            this.loadConfiguration();
        }
    }
}
