package metest.api;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.common.util.FakePlayer;

import java.util.UUID;

/**
 * Modifies some properties and methods of the MinecraftForge's FakePlayer class
 * to be able to be used as a testing tool.
 */
public class TestPlayer extends FakePlayer {

    public String lastMessage = "";

    public TestPlayer(MinecraftServer server, String name) {
        super(server.worldServers[0], new GameProfile(UUID.randomUUID(), name));
    }

    @Override
    public boolean canCommandSenderUseCommand(int i, String s) {
        return true;
    }

    @Override
    public void addChatMessage(IChatComponent chatComponent) {
        lastMessage = chatComponent.getUnformattedText();
    }
}
