package net.elytraautopilot.config;

import me.lortseam.completeconfig.api.ConfigEntries;
import me.lortseam.completeconfig.api.ConfigEntry;
import me.lortseam.completeconfig.api.ConfigGroup;
import me.lortseam.completeconfig.data.Config;
import me.lortseam.completeconfig.data.ConfigOptions;

import java.util.ArrayList;
import java.util.List;

@ConfigEntries(includeAll = true)
public class ModConfig extends Config {
    public ModConfig(String modId) {
        super(ConfigOptions.mod(modId));
    }

    @Transitive
    @ConfigEntries(includeAll = true)
    public static class gui implements ConfigGroup {
        public static boolean showgui = true;
        @ConfigEntry.Slider
        @ConfigEntry.BoundedInteger(min = 0, max = 300)
        public static int guiScale = 100;
        public static int guiX = 5;
        public static int guiY = 5;
    }

    @Transitive
    @ConfigEntries(includeAll = true)
    public static class flightprofile implements ConfigGroup {
        @ConfigEntry.Slider
        @ConfigEntry.BoundedInteger(min = 0, max = 500)
        public static int maxHeight = 360;
        @ConfigEntry.Slider
        @ConfigEntry.BoundedInteger(min = 0, max = 500)
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
    }

    @Transitive
    @ConfigEntries(includeAll = true)
    public static class advanced implements ConfigGroup {
        @ConfigEntry.Slider
        @ConfigEntry.BoundedInteger(min = 1, max = 20)
        public static int groundCheckTicks = 1;
        public static double pullUpAngle = -46.633514;
        public static double pullDownAngle = 37.19872;
        public static double pullUpMinVelocity = 1.9102669;
        public static double pullDownMaxVelocity = 2.3250866;
        public static double pullUpSpeed = 2.1605124;
        public static double pullDownSpeed = 0.20545267;
        public static List<String> flyLocations = new ArrayList<>();
    }
}
