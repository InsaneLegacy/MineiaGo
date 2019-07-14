package com.filiphsandstrom.bedrockproxy.packets;

import com.filiphsandstrom.bedrockproxy.Compression;
import com.filiphsandstrom.bedrockproxy.PacketRegistry;
import com.filiphsandstrom.bedrockproxy.raknet.RakNetPacket;

import java.util.zip.DataFormatException;

public class BatchPacket extends DataPacket {
    public BatchPacket() {
        super(PacketRegistry.NetworkType.BATCH_PACKET);
    }

    public BatchPacket(RakNetPacket packet) {
        super(packet);
    }

    @Override
    public void decode() {
        if (buffer().readableBytes() < 2) return;

        try {
            setBuffer(Compression.inflate(buffer()));
        } catch (DataFormatException e) {
            e.printStackTrace();
            return;
        }

        if (buffer().readableBytes() == 0) {
            throw new RuntimeException("Decoded BatchPacket payload is empty");
        }

        buffer().readerIndex(2);
        while (buffer().readerIndex() < buffer().readableBytes()) {
            PacketRegistry.handlePacket(new RakNetPacket(readBytes()), getPlayer());
        }
    }
}
