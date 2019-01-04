package io.merculet.wxbot.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @Author Aaron
 * @Date 2019/1/4
 * @Email aaron@magicwindow.cn
 * @Description
 */
public class ClientChannelHandler extends ChannelInitializer<SocketChannel> {
    private ClientInBoundHandler mDispatcher;

    ClientChannelHandler(ClientInBoundHandler boundHandler) {
        mDispatcher = boundHandler;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("decoder", new ClientProtobufDecoder());
        pipeline.addLast("encoder", new ClientProtobufDecoder());

        pipeline.addLast("handler", mDispatcher);
    }
}
