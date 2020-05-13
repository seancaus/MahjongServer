package com.sean.server.socket;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PackDecoder extends ByteToMessageDecoder {

    private static final int LENGTH = 4;

    /**
        totalLength|itemLength|itemData|ItemLength|ItemData
        4byte      |****
     */



    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        Object decoded = decode(ctx, in);
        if (decoded != null) {
            out.add(decoded);
        }
    }

    protected Object decode(@SuppressWarnings("UnusedParameters") ChannelHandlerContext ctx, ByteBuf in)
            throws Exception {
        int readableBytes = in.readableBytes();
        if (readableBytes < LENGTH) return null;

        in.markReaderIndex();

        int totalLength = in.readInt();
        if (readableBytes < totalLength) {
            in.resetReaderIndex();
            return null;
        }

        return in.readRetainedSlice(totalLength);
    }
}
