package net.jorge.learningmod;

import net.fabricmc.api.ClientModInitializer;
public class FabricLearningClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FabricLearning.LOGGER.info("Hello Fabric world! (from the client)");
    }
}
