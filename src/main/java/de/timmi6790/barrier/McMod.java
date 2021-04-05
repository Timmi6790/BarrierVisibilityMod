package de.timmi6790.barrier;

import de.timmi6790.barrier.command.commands.BarrierCommand;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

@Mod(
        modid = Reference.MODID,
        name = Reference.NAME,
        version = Reference.VERSION,
        acceptedMinecraftVersions = "1.8.9",
        guiFactory = "de.timmi6790.barrier.gui.GuiFactory",
        clientSideOnly = true
)
@Log4j2
@Getter
@Setter
public class McMod {
    @Getter
    @Mod.Instance(value = Reference.MODID)
    private static McMod instance;

    private final Cache cache = new Cache();
    private Configuration configuration;
    private String configDirectory;

    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
        this.configDirectory = event.getModConfigurationDirectory().toString();
        final File path = new File(this.configDirectory + File.separator + Reference.MODID + ".cfg");
        this.configuration = new Configuration(path);
    }

    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(
                new BarrierCommand(this.cache)
        );

        MinecraftForge.EVENT_BUS.register(
                new Config(this.cache, this.configuration)
        );
    }
}
