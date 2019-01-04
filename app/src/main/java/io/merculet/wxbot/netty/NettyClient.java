package io.merculet.wxbot.netty;


import android.util.Log;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import io.merculet.wxbot.netty.proto.OneTestProto;
import io.merculet.wxbot.util.Config;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;


public class NettyClient {

    private static final String TAG = "NettyClient";

    private InetSocketAddress mServerAddress;
    private Bootstrap mBootstrap;
    private Channel mChannel;
    private EventLoopGroup mWorkerGroup;
    private OnServerConnectListener onServerConnectListener;
    private ClientInBoundHandler mDispatcher;

    private static NettyClient INSTANCE;

    private NettyClient() {
        mDispatcher = new ClientInBoundHandler();
    }

    public static NettyClient getInstance() {
        if (INSTANCE == null) {
            synchronized (NettyClient.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NettyClient();
                }
            }
        }
        return INSTANCE;
    }

    //增加心跳、重连机制
    public void connect(final InetSocketAddress socketAddress) {
        Log.i(TAG, "aaron NettyClient connect111");
        if (mChannel != null && mChannel.isActive()) {
            return;
        }
        Log.i(TAG, "aaron NettyClient connect222");

        mServerAddress = socketAddress;

        if (mBootstrap == null) {
            mWorkerGroup = new NioEventLoopGroup();
            mBootstrap = new Bootstrap();

            mBootstrap.group(mWorkerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ClientChannelHandler(mDispatcher))
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000);
        }

        ChannelFuture future = mBootstrap.connect(mServerAddress);
        future.addListener(mConnectFutureListener);
    }

    public void connect(final InetSocketAddress socketAddress, OnServerConnectListener onServerConnectListener) {
        Log.i(TAG, "aaron NettyClient connect111");
        if (mChannel != null && mChannel.isActive()) {
            return;
        }
        Log.i(TAG, "aaron NettyClient connect222");

        mServerAddress = socketAddress;
        this.onServerConnectListener = onServerConnectListener;

        if (mBootstrap == null) {
            mWorkerGroup = new NioEventLoopGroup();
            mBootstrap = new Bootstrap();

            mBootstrap.group(mWorkerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ClientChannelHandler(mDispatcher))
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000);
        }

        ChannelFuture future = mBootstrap.connect(mServerAddress);
        future.addListener(mConnectFutureListener);
    }

    private ChannelFutureListener mConnectFutureListener = new ChannelFutureListener() {
        @Override
        public void operationComplete(ChannelFuture channelFuture) throws Exception {
            if (channelFuture.isSuccess()) {
                mChannel = channelFuture.channel();
                if (onServerConnectListener != null) {
                    onServerConnectListener.onConnectSuccess();
                }
                Log.i(TAG, "ChannelFutureListener: 连接成功!");
            } else {
                if (onServerConnectListener != null) {
                    onServerConnectListener.onConnectFailed();
                }

                //增加心跳、重连机制
                final EventLoop loop = channelFuture.channel().eventLoop();
                loop.schedule(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG, "服务端链接不上，开始重连操作...");
                        connect(new InetSocketAddress(Config.ADDRESS, Config.PORT_NUMBER));
                    }
                }, 1L, TimeUnit.SECONDS);
            }

        }
    };

    public synchronized void send(OneTestProto.OneTest msg, OnReceiveListener listener) {
        if (mChannel == null) {
            Log.e(TAG, "aaron send: channel is null");
            return;
        }

        if (!mChannel.isWritable()) {
            Log.e(TAG, "aaron send: channel is not Writable");
            return;
        }

        if (!mChannel.isActive()) {
            Log.e(TAG, "aaron send: channel is not active!");
            return;
        }
        Log.e(TAG, "aaron send: msg" + msg);

        mDispatcher.holdListener(msg, listener);
        if (mChannel != null) {
            mChannel.writeAndFlush(msg);
        }

    }
}