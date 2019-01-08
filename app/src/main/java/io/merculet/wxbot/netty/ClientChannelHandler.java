package io.merculet.wxbot.netty;

import java.util.concurrent.TimeUnit;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

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
        //增加心跳、重连机制
        pipeline.addLast("ping", new IdleStateHandler(30, 20, 30 * 10, TimeUnit.SECONDS));
        pipeline.addLast("decoder", new ClientProtobufDecoder());
        pipeline.addLast("encoder", new ClientProtobufDecoder());

        pipeline.addLast("handler", mDispatcher);
    }
}
