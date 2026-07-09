package crystal.evergun.client;

import net.fabricmc.api.ClientModInitializer;

public class EVERgunClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
        Animation.register();
	}
}