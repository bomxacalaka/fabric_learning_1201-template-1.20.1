package net.jorge.learningmod;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static net.minecraft.server.command.CommandManager.*;

public class FabricLearning implements ModInitializer {
    public static final String MOD_ID = "fabric_learning_1201";
    long startTime = System.nanoTime();
    long timeElapsed = 0;
    long endTime = 0;
    long pausedTime = System.nanoTime();
    boolean paused = false;
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    private final Timer timer = new Timer();

    @Override
    public void onInitialize() {
        LOGGER.info("Hello Fabric world!");
        // Teleport the player who executed this command 2 blocks upwards
//		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("foo")
//				.executes(context -> {
//					// Teleport the player who executed this command 2 blocks upwards
//					context.getSource().getPlayer().teleport(context.getSource().getPlayer().getX(), context.getSource().getPlayer().getY() + 2, context.getSource().getPlayer().getZ());
//					return 1;
//				})
//		));

        // Place a block at the player's position
//		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("foo")
//				.executes(context -> {
//					// Place a block at the player's position
//					context.getSource().getWorld().setBlockState(context.getSource().getPlayer().getBlockPos(), net.minecraft.block.Blocks.DIAMOND_BLOCK.getDefaultState());
//					return 1;
//				})
//		));

        // Create a Timer instance


        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->
                dispatcher.register(CommandManager.literal("timer")
                        .then(CommandManager.literal("reset")
                                .executes(context -> {
                                    // Reset timer using Timer instance
                                    timer.reset();
                                    context.getSource().getPlayer().sendMessage(Text.of("Timer reset."), false);
                                    return 1;
                                })
                        )
                        .then(CommandManager.literal("pause")
                                .executes(context -> {
                                    // Pause timer using Timer instance
                                    timer.pause();
                                    context.getSource().getPlayer().sendMessage(Text.of("Timer paused."), false);
                                    return 1;
                                })
                        )
                        .then(CommandManager.literal("resume")
                                .executes(context -> {
                                    // Resume timer using Timer instance
                                    timer.resume();
                                    context.getSource().getPlayer().sendMessage(Text.of("Timer resumed."), false);
                                    return 1;
                                })
                        )
                        .then(CommandManager.literal("show")
                                .executes(context -> {
                                    // Show timer using Timer instance
                                    timer.show(context.getSource());
                                    return 1;
                                })
                        )
                )
        );
    }
}

