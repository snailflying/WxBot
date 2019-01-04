package io.merculet.wxbot.netty;


import android.util.Log;

import java.net.InetSocketAddress;

import io.merculet.wxbot.netty.proto.OneTestProto;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
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
        public void operationComplete(ChannelFuture pChannelFuture) throws Exception {
            if (pChannelFuture.isSuccess()) {
                mChannel = pChannelFuture.channel();
                if (onServerConnectListener != null) {
                    onServerConnectListener.onConnectSuccess();
                }
                Log.i(TAG, "operationComplete: connected!");
            } else {
                if (onServerConnectListener != null) {
                    onServerConnectListener.onConnectFailed();
                }
                Log.i(TAG, "operationComplete: connect failed!");
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