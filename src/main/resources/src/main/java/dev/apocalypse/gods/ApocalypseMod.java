package dev.apocalypse.gods;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.world.World;

public class ApocalypseMod implements ModInitializer {

    @Override
    public void onInitialize() {

        CommandRegistrationCallback.EVENT.register(
            (dispatcher, registryAccess, environment) -> {

                dispatcher.register(
                    CommandManager.literal("apocalypse")
                    .requires(source -> source.hasPermissionLevel(2))
                    .executes(context -> {

                        var source = context.getSource();
                        var world = source.getWorld();

                        double x = source.getPosition().x;
                        double y = source.getPosition().y;
                        double z = source.getPosition().z;

                        world.createExplosion(
                            null,
                            x, y, z,
                            30.0F,
                            World.ExplosionSourceType.TNT
                        );

                        source.sendFeedback(
                            () -> net.minecraft.text.Text.literal(
                                "§4☄ APOCALYPSE ACTIVATED ☄"
                            ),
                            true
                        );

                        return 1;
                    })
                );
            }
        );
    }
}
