package io.merculet.wxbot.netty;

import com.google.protobuf.MessageLite;

import io.merculet.wxbot.netty.proto.OneTestProto;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 参考ProtobufVarint32LengthFieldPrepender 和 ProtobufEncoder
 */
@ChannelHandler.Sharable
public class ClientProtobufEncoder extends MessageToByteEncoder<MessageLite> {

    @Override
    protected void encode(
            ChannelHandlerContext ctx, MessageLite msg, ByteBuf out) throws Exception {


        byte[] body = msg.toByteArray();
        byte[] header = encodeHeader(msg, body.length);

        out.writeBytes(header);
        out.writeBytes(body);

    }

    private byte[] encodeHeader(MessageLite msg, int bodyLength) {
        byte messageType = 0x0f;

        if (msg instanceof OneTestProto.OneTest) {
            messageType = 0x00;
        } else {
            messageType = 0x00;
        }
//        else if (msg instanceof Test.ProtoTest) {
//            messageType = 0x01;
//        }

        byte[] header = new byte[4];
        header[0] = (byte) (bodyLength & 0xff);
        header[1] = (byte) ((bodyLength >> 8) & 0xff);
        header[2] = messageType;
        header[3] = 0; // 保留字段

        return header;

    }
}