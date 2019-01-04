package io.merculet.wxbot.netty;

import com.google.protobuf.MessageLite;

import java.util.List;

import io.merculet.wxbot.netty.proto.OneTestProto;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * 参考ProtobufVarint32FrameDecoder 和 ProtobufDecoder
 */

public class ClientProtobufDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        while (in.readableBytes() > 4) { // 如果可读长度小于包头长度，退出。
            in.markReaderIndex();

            // 获取包头中的body长度
            byte low = in.readByte();
            byte high = in.readByte();
            short s0 = (short) (low & 0xff);
            short s1 = (short) (high & 0xff);
            s1 <<= 8;
            short length = (short) (s0 | s1);

            // 获取包头中的protobuf类型
            byte dataType = in.readByte();
            // 获取包头中的保留字段,使readerIndex后移
            in.readByte();


            // 如果可读长度小于body长度，恢复读指针，退出。
            if (in.readableBytes() < length) {
                in.resetReaderIndex();
                return;
            }

            // 读取body
            ByteBuf bodyByteBuf = in.readBytes(length);

            byte[] array;
            int offset;

            int readableLen = bodyByteBuf.readableBytes();
            if (bodyByteBuf.hasArray()) {
                array = bodyByteBuf.array();
                offset = bodyByteBuf.arrayOffset() + bodyByteBuf.readerIndex();
            } else {
                array = new byte[readableLen];
                bodyByteBuf.getBytes(bodyByteBuf.readerIndex(), array, 0, readableLen);
                offset = 0;
            }

            //反序列化
            MessageLite result = decodeBody(dataType, array, offset, readableLen);
            out.add(result);
        }
    }

    private MessageLite decodeBody(byte dataType, byte[] array, int offset, int length) throws Exception {
        if (dataType == 0x00) {
            return OneTestProto.OneTest.getDefaultInstance().
                    getParserForType().parseFrom(array, offset, length);

        }
//        else if (dataType == 0x01) {
//            return OptionTickOuterClass.OptionTick.getDefaultInstance().
//                    getParserForType().parseFrom(array, offset, length);
//        }

        return null; // or throw exception
    }
}