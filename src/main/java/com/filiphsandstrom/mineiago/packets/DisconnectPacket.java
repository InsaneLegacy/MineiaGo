package com.filiphsandstrom.mineiago.packets;

import com.filiphsandstrom.mineiago.PacketRegistry;
import com.whirvis.jraknet.RakNetPacket;

public class DisconnectPacket extends DataPacket {
    private boolean hideDisconnectionScreen;
    public boolean getHideDisconnectionScreen() {
        return hideDisconnectionScreen;
    }
    public void setHideDisconnectionScreen(boolean h) {
        hideDisconnectionScreen = h;
    }

    private String message = "Disconnected!";
    public String getMessage() {
        return message;
    }
    public void setMessage(String m) {
        message = m;
    }

    public DisconnectPacket() {
        super(PacketRegistry.NetworkType.DISCONNECT_PACKET);
    }

    public DisconnectPacket(RakNetPacket packet) {
        super(packet);
    }

    @Override
    public void encode() {
        writeBoolean(hideDisconnectionScreen);

        if (!hideDisconnectionScreen) {
            writeString(message);
        }
    }
}