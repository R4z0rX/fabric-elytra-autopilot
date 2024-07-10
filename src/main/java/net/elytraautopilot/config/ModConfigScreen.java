package net.elytraautopilot.config;

import com.google.gson.GsonBuilder;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.config.ConfigEntry;
import dev.isxander.yacl3.config.GsonConfigInstance;
import dev.isxander.yacl3.gui.controllers.TickBoxController;
import dev.isxander.yacl3.gui.controllers.cycling.EnumController;
import dev.isxander.yacl3.gui.controllers.slider.FloatSliderController;
import dev.isxander.yacl3.gui.controllers.slider.IntegerSliderController;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.List;

public class ModConfig {
    public static final GsonConfigInstance<ModConfig> INSTANCE = new GsonConfigInstance<>(ModConfig.class, FabricLoader.getInstance().getConfigDir().resolve("mod-config.json"), GsonBuilder::setPrettyPrinting);

    @ConfigEntry public static GuiConfig gui = new GuiConfig();
    @ConfigEntry public static FlightProfileConfig flightprofile = new FlightProfileConfig();
    @ConfigEntry public static AdvancedConfig advanced = new AdvancedConfig();

    public static class GuiConfig {
        @ConfigEntry public boolean showgui = true;
        @ConfigEntry @BoundedInteger(min = 0, max = 300) public int guiScale = 100;
        @ConfigEntry public int guiX = 5;
        @ConfigEntry public int guiY = 5;
    }

    public static class FlightProfileConfig {
        @ConfigEntry @BoundedInteger(min = 0, max = 500) public int maxHeight = 360;
        @ConfigEntry @BoundedInteger(min = 0, max = 500) public int minHeight = 180;
        @ConfigEntry public boolean autoLanding = true;
        @ConfigEntry public String playSoundOnLanding = "minecraft:block.note_block.pling";
        @ConfigEntry public double autoLandSpeed = 3;
        @ConfigEntry public double turningSpeed = 3;
        @ConfigEntry public double takeOffPull = 10;
        @ConfigEntry public boolean riskyLanding = false;
        @ConfigEntry public boolean poweredFlight = false;
        @ConfigEntry public boolean elytraHotswap = true;
        @ConfigEntry public boolean fireworkHotswap = true;
        @ConfigEntry public boolean emergencyLand = true;
    }

    public static class AdvancedConfig {
        @ConfigEntry @BoundedInteger(min = 1, max = 20) public int groundCheckTicks = 1;
        @ConfigEntry public double pullUpAngle = -46.633514;
        @ConfigEntry public double pullDownAngle = 37.19872;
        @ConfigEntry public double pullUpMinVelocity = 1.9102669;
        @ConfigEntry public double pullDownMaxVelocity = 2.3250866;
        @ConfigEntry public double pullUpSpeed = 2.1605124;
        @ConfigEntry public double pullDownSpeed = 0.20545267;
        @ConfigEntry public List<String> flyLocations = new ArrayList<>();
    }

    public static Screen makeScreen(Screen parent) {
        return YetAnotherConfigLib.create(INSTANCE, (defaults, config, builder) -> {
            var categoryBuilder = ConfigCategory.createBuilder()
                    .name(Text.translatable("modconfig.title"));

            // GUI Config
            var guiGroup = OptionGroup.createBuilder()
                    .name(Text.translatable("modconfig.group.gui.title"));
            guiGroup.option(Option.createBuilder(boolean.class)
                    .name(Text.translatable("modconfig.opt.showgui.title"))
                    .binding(defaults.gui.showgui, () -> config.gui.showgui, val -> config.gui.showgui = val)
                    .controller(TickBoxController::new)
                    .build());
            guiGroup.option(Option.createBuilder(int.class)
                    .name(Text.translatable("modconfig.opt.guiScale.title"))
                    .binding(defaults.gui.guiScale, () -> config.gui.guiScale, val -> config.gui.guiScale = val)
                    .controller(opt -> new IntegerSliderController(opt, 0, 300, 1, val -> Text.translatable("modconfig.format.percentage", val)))
                    .build());
            guiGroup.option(Option.createBuilder(int.class)
                    .name(Text.translatable("modconfig.opt.guiX.title"))
                    .binding(defaults.gui.guiX, () -> config.gui.guiX, val -> config.gui.guiX = val)
                    .controller(IntegerSliderController::new)
                    .build());
            guiGroup.option(Option.createBuilder(int.class)
                    .name(Text.translatable("modconfig.opt.guiY.title"))
                    .binding(defaults.gui.guiY, () -> config.gui.guiY, val -> config.gui.guiY = val)
                    .controller(IntegerSliderController::new)
                    .build());
            categoryBuilder.group(guiGroup.build());

            // Flight Profile Config
            var flightProfileGroup = OptionGroup.createBuilder()
                    .name(Text.translatable("modconfig.group.flightprofile.title"));
            flightProfileGroup.option(Option.createBuilder(int.class)
                    .name(Text.translatable("modconfig.opt.maxHeight.title"))
                    .binding(defaults.flightprofile.maxHeight, () -> config.flightprofile.maxHeight, val -> config.flightprofile.maxHeight = val)
                    .controller(opt -> new IntegerSliderController(opt, 0, 500, 1, val -> Text.translatable("modconfig.format.height", val)))
                    .build());
            flightProfileGroup.option(Option.createBuilder(int.class)
                    .name(Text.translatable("modconfig.opt.minHeight.title"))
                    .binding(defaults.flightprofile.minHeight, () -> config.flightprofile.minHeight, val -> config.flightprofile.minHeight = val)
                    .controller(opt -> new IntegerSliderController(opt, 0, 500, 1, val -> Text.translatable("modconfig.format.height", val)))
                    .build());
            flightProfileGroup.option(Option.createBuilder(boolean.class)
                    .name(Text.translatable("modconfig.opt.autoLanding.title"))
                    .binding(defaults.flightprofile.autoLanding, () -> config.flightprofile.autoLanding, val -> config.flightprofile.autoLanding = val)
                    .controller(TickBoxController::new)
                    .build());
            flightProfileGroup.option(Option.createBuilder(String.class)
                    .name(Text.translatable("modconfig.opt.playSoundOnLanding.title"))
                    .binding(defaults.flightprofile.playSoundOnLanding, () -> config.flightprofile.playSoundOnLanding, val -> config.flightprofile.playSoundOnLanding = val)
                    .controller(opt -> new EnumController<>(opt, List.of("minecraft:block.note_block.pling", "custom:sound.example")))
                    .build());
            flightProfileGroup.option(Option.createBuilder(double.class)
                    .name(Text.translatable("modconfig.opt.autoLandSpeed.title"))
                    .binding(defaults.flightprofile.autoLandSpeed, () -> config.flightprofile.autoLandSpeed, val -> config.flightprofile.autoLandSpeed = val)
                    .controller(FloatSliderController::new)
                    .build());
            flightProfileGroup.option(Option.createBuilder(double.class)
                    .name(Text.translatable("modconfig.opt.turningSpeed.title"))
                    .binding(defaults.flightprofile.turningSpeed, () -> config.flightprofile.turningSpeed, val -> config.flightprofile.turningSpeed = val)
                    .controller(FloatSliderController::new)
                    .build());
            flightProfileGroup.option(Option.createBuilder(double.class)
                    .name(Text.translatable("modconfig.opt.takeOffPull.title"))
                    .binding(defaults.flightprofile.takeOffPull, () -> config.flightprofile.takeOffPull, val -> config.flightprofile.takeOffPull = val)
                    .controller(FloatSliderController::new)
                    .build());
            flightProfileGroup.option(Option.createBuilder(boolean.class)
                    .name(Text.translatable("modconfig.opt.riskyLanding.title"))
                    .binding(defaults.flightprofile.riskyLanding, () -> config.flightprofile.riskyLanding, val -> config.flightprofile.riskyLanding = val)
                    .controller(TickBoxController::new)
                    .build());
            flightProfileGroup.option(Option.createBuilder(boolean.class)
                    .name(Text.translatable("modconfig.opt.poweredFlight.title"))
                    .binding(defaults.flightprofile.poweredFlight, () -> config.flightprofile.poweredFlight, val -> config.flightprofile.poweredFlight = val)
                    .controller(TickBoxController::new)
                    .build());
            flightProfileGroup.option(Option.createBuilder(boolean.class)
                    .name(Text.translatable("modconfig.opt.elytraHotswap.title"))
                    .binding(defaults.flightprofile.elytraHotswap, () -> config.flightprofile.elytraHotswap, val -> config.flightprofile.elytraHotswap = val)
                    .controller(TickBoxController::new)
                    .build());
            flightProfileGroup.option(Option.createBuilder(boolean.class)
                    .name(Text.translatable("modconfig.opt.fireworkHotswap.title"))
                    .binding(defaults.flightprofile.fireworkHotswap, () -> config.flightprofile.fireworkHotswap, val -> config.flightprofile.fireworkHotswap = val)
                    .controller(TickBoxController::new)
                    .build());
            flightProfileGroup.option(Option.createBuilder(boolean.class)
                    .name(Text.translatable("modconfig.opt.emergencyLand.title"))
                    .binding(defaults.flightprofile.emergencyLand, () -> config.flightprofile.emergencyLand, val -> config.flightprofile.emergencyLand = val)
                    .controller(TickBoxController::new)
                    .build());
            categoryBuilder.group(flightProfileGroup.build());

            // Advanced Config
            var advancedGroup = OptionGroup.createBuilder()
                    .name(Text.translatable("modconfig.group.advanced.title"));
            advancedGroup.option(Option.createBuilder(int.class)
                    .name(Text.translatable("modconfig.opt.groundCheckTicks.title"))
                    .binding(defaults.advanced.groundCheckTicks, () -> config.advanced.groundCheckTicks, val -> config.advanced.groundCheckTicks = val)
                    .controller(opt -> new IntegerSliderController(opt, 1, 20, 1, val -> Text.translatable("modconfig.format.ticks", val)))
                    .build());
            advancedGroup.option(Option.createBuilder(double.class)
                    .name(Text.translatable("modconfig.opt.pullUpAngle.title"))
                    .binding(defaults.advanced.pullUpAngle, () -> config.advanced.pullUpAngle, val -> config.advanced.pullUpAngle = val)
                    .controller(FloatSliderController::new)
                    .build());
            advancedGroup.option(Option.createBuilder(double.class)
                    .name(Text.translatable("modconfig.opt.pullDownAngle.title"))
                    .binding(defaults.advanced.pullDownAngle, () -> config.advanced.pullDownAngle, val -> config.advanced.pullDownAngle = val)
                    .controller(FloatSliderController::new)
                    .build());
            advancedGroup.option(Option.createBuilder(double.class)
                    .name(Text.translatable("modconfig.opt.pullUpMinVelocity.title"))
                    .binding(defaults.advanced.pullUpMinVelocity, () -> config.advanced.pullUpMinVelocity, val -> config.advanced.pullUpMinVelocity = val)
                    .controller(FloatSliderController::new)
                    .build());
            advancedGroup.option(Option.createBuilder(double.class)
                    .name(Text.translatable("modconfig.opt.pullDownMaxVelocity.title"))
                    .binding(defaults.advanced.pullDownMaxVelocity, () -> config.advanced.pullDownMaxVelocity, val -> config.advanced.pullDownMaxVelocity = val)
                    .controller(FloatSliderController::new)
                    .build());
            advancedGroup.option(Option.createBuilder(double.class)
                    .name(Text.translatable("modconfig.opt.pullUpSpeed.title"))
                    .binding(defaults.advanced.pullUpSpeed, () -> config.advanced.pullUpSpeed, val -> config.advanced.pullUpSpeed = val)
                    .controller(FloatSliderController::new)
                    .build());
            advancedGroup.option(Option.createBuilder(double.class)
                    .name(Text.translatable("modconfig.opt.pullDownSpeed.title"))
                    .binding(defaults.advanced.pullDownSpeed, () -> config.advanced.pullDownSpeed, val -> config.advanced.pullDownSpeed = val)
                    .controller(FloatSliderController::new)
                    .build());
            advancedGroup.option(Option.createBuilder(List.class)
                    .name(Text.translatable("modconfig.opt.flyLocations.title"))
                    .binding(defaults.advanced.flyLocations, () -> config.advanced.flyLocations, val -> config.advanced.flyLocations = val)
                    .controller(opt -> new ListController<>(opt, Text.translatable("modconfig.format.flyLocation"), () -> ""))
                    .build());
            categoryBuilder.group(advancedGroup.build());

            builder.category(categoryBuilder.build());
        }).generateScreen(parent);
    }
}
