package net.elytraautopilot.config;

import dev.isxander.yacl3.api.*;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class ModConfigOld {

    public static boolean showgui = true;
    public static int guiScale = 100;
    public static int guiX = 5;
    public static int guiY = 5;

    public static int maxHeight = 360;
    public static int minHeight = 180;
    public static boolean autoLanding = true;
    public static String playSoundOnLanding = "minecraft:block.note_block.pling";
    public static double autoLandSpeed = 3;
    public static double turningSpeed = 3;
    public static double takeOffPull = 10;
    public static boolean riskyLanding = false;
    public static boolean poweredFlight = false;
    public static boolean elytraHotswap = true;
    public static boolean fireworkHotswap = true;
    public static boolean emergencyLand = true;

    public static int groundCheckTicks = 1;
    public static double pullUpAngle = -46.633514;
    public static double pullDownAngle = 37.19872;
    public static double pullUpMinVelocity = 1.9102669;
    public static double pullDownMaxVelocity = 2.3250866;
    public static double pullUpSpeed = 2.1605124;
    public static double pullDownSpeed = 0.20545267;
    public static List<String> flyLocations = new ArrayList<>();

    public static Screen createConfigScreen(Screen parent) {
        return YetAnotherConfigLib.createBuilder()
                .title(Component.literal("Elytra Autopilot Config"))
                .category(ConfigCategory.createBuilder()
                        .name(Component.literal("GUI Settings"))
                        .option(Option.<Boolean>createBuilder()
                                .name(Component.literal("Show GUI"))
                                .binding(true, () -> showgui, newVal -> showgui = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .option(Option.<Integer>createBuilder()
                                .name(Component.literal("GUI Scale"))
                                .binding(100, () -> guiScale, newVal -> guiScale = newVal)
                                .controller(SliderControllerBuilder.create()
                                        .min(0)
                                        .max(300))
                                .build())
                        .option(Option.<Integer>createBuilder()
                                .name(Component.literal("GUI X Position"))
                                .binding(5, () -> guiX, newVal -> guiX = newVal)
                                .controller(IntegerFieldControllerBuilder::create)
                                .build())
                        .option(Option.<Integer>createBuilder()
                                .name(Component.literal("GUI Y Position"))
                                .binding(5, () -> guiY, newVal -> guiY = newVal)
                                .controller(IntegerFieldControllerBuilder::create)
                                .build())
                        .build())
                .category(ConfigCategory.createBuilder()
                        .name(Component.literal("Flight Profile Settings"))
                        .option(Option.<Integer>createBuilder()
                                .name(Component.literal("Max Height"))
                                .binding(360, () -> maxHeight, newVal -> maxHeight = newVal)
                                .controller(SliderControllerBuilder.create()
                                        .min(0)
                                        .max(500))
                                .build())
                        .option(Option.<Integer>createBuilder()
                                .name(Component.literal("Min Height"))
                                .binding(180, () -> minHeight, newVal -> minHeight = newVal)
                                .controller(SliderControllerBuilder.create()
                                        .min(0)
                                        .max(500))
                                .build())
                        .option(Option.<Boolean>createBuilder()
                                .name(Component.literal("Auto Landing"))
                                .binding(true, () -> autoLanding, newVal -> autoLanding = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .option(Option.<String>createBuilder()
                                .name(Component.literal("Play Sound On Landing"))
                                .binding("minecraft:block.note_block.pling", () -> playSoundOnLanding, newVal -> playSoundOnLanding = newVal)
                                .controller(TextFieldControllerBuilder::create)
                                .build())
                        .option(Option.<Double>createBuilder()
                                .name(Component.literal("Auto Land Speed"))
                                .binding(3.0, () -> autoLandSpeed, newVal -> autoLandSpeed = newVal)
                                .controller(DoubleFieldControllerBuilder::create)
                                .build())
                        .option(Option.<Double>createBuilder()
                                .name(Component.literal("Turning Speed"))
                                .binding(3.0, () -> turningSpeed, newVal -> turningSpeed = newVal)
                                .controller(DoubleFieldControllerBuilder::create)
                                .build())
                        .option(Option.<Double>createBuilder()
                                .name(Component.literal("Take Off Pull"))
                                .binding(10.0, () -> takeOffPull, newVal -> takeOffPull = newVal)
                                .controller(DoubleFieldControllerBuilder::create)
                                .build())
                        .option(Option.<Boolean>createBuilder()
                                .name(Component.literal("Risky Landing"))
                                .binding(false, () -> riskyLanding, newVal -> riskyLanding = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .option(Option.<Boolean>createBuilder()
                                .name(Component.literal("Powered Flight"))
                                .binding(false, () -> poweredFlight, newVal -> poweredFlight = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .option(Option.<Boolean>createBuilder()
                                .name(Component.literal("Elytra Hotswap"))
                                .binding(true, () -> elytraHotswap, newVal -> elytraHotswap = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .option(Option.<Boolean>createBuilder()
                                .name(Component.literal("Firework Hotswap"))
                                .binding(true, () -> fireworkHotswap, newVal -> fireworkHotswap = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .option(Option.<Boolean>createBuilder()
                                .name(Component.literal("Emergency Land"))
                                .binding(true, () -> emergencyLand, newVal -> emergencyLand = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .build())
                .category(ConfigCategory.createBuilder()
                        .name(Component.literal("Advanced Settings"))
                        .option(Option.<Integer>createBuilder()
                                .name(Component.literal("Ground Check Ticks"))
                                .binding(1, () -> groundCheckTicks, newVal -> groundCheckTicks = newVal)
                                .controller(SliderControllerBuilder.create()
                                        .min(1)
                                        .max(20))
                                .build())
                        .option(Option.<Double>createBuilder()
                                .name(Component.literal("Pull Up Angle"))
                                .binding(-46.633514, () -> pullUpAngle, newVal -> pullUpAngle = newVal)
                                .controller(DoubleFieldControllerBuilder::create)
                                .build())
                        .option(Option.<Double>createBuilder()
                                .name(Component.literal("Pull Down Angle"))
                                .binding(37.19872, () -> pullDownAngle, newVal -> pullDownAngle = newVal)
                                .controller(DoubleFieldControllerBuilder::create)
                                .build())
                        .option(Option.<Double>createBuilder()
                                .name(Component.literal("Pull Up Min Velocity"))
                                .binding(1.9102669, () -> pullUpMinVelocity, newVal -> pullUpMinVelocity = newVal)
                                .controller(DoubleFieldControllerBuilder::create)
                                .build())
                        .option(Option.<Double>createBuilder()
                                .name(Component.literal("Pull Down Max Velocity"))
                                .binding(2.3250866, () -> pullDownMaxVelocity, newVal -> pullDownMaxVelocity = newVal)
                                .controller(DoubleFieldControllerBuilder::create)
                                .build())
                        .option(Option.<Double>createBuilder()
                                .name(Component.literal("Pull Up Speed"))
                                .binding(2.1605124, () -> pullUpSpeed, newVal -> pullUpSpeed = newVal)
                                .controller(DoubleFieldControllerBuilder::create)
                                .build())
                        .option(Option.<Double>createBuilder()
                                .name(Component.literal("Pull Down Speed"))
                                .binding(0.20545267, () -> pullDownSpeed, newVal -> pullDownSpeed = newVal)
                                .controller(DoubleFieldControllerBuilder::create)
                                .build())
                        .option(Option.<List<String>>createBuilder()
                                .name(Component.literal("Fly Locations"))
                                .binding(new ArrayList<>(), () -> flyLocations, newVal -> flyLocations = newVal)
                                .controller(ListFieldControllerBuilder::create)
                                .build())
                        .build())
                .build()
                .generateScreen(parent);
    }
}
