package io.merculet.wxbot.netty;

import android.util.ArrayMap;
import android.util.Log;

import io.merculet.wxbot.netty.proto.OneTestProto;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class ClientInBoundHandler extends SimpleChannelInboundHandler<OneTestProto.OneTest> {
    private ArrayMap<Integer, OnReceiveListener> receiveListenerHolder;

    public ClientInBoundHandler() {
        receiveListenerHolder = new ArrayMap<>();
    }

    public void holdListener(OneTestProto.OneTest test, OnReceiveListener onReceiveListener) {
        receiveListenerHolder.put(test.getTestId(), onReceiveListener);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, OneTestProto.OneTest protoTest) throws Exception {
        Log.e("ClientInBoundHandler", "aaron channelRead0 protoTest id:" + protoTest.getTestId() + " title:" + protoTest.getTitle() + " content:" + protoTest.getContent());
        if (receiveListenerHolder.containsKey(protoTest.getTestId())) {
            OnReceiveListener listener = receiveListenerHolder.get(protoTest.getTestId());
            if (listener != null) {
                listener.handleReceive(protoTest);
            }
        }
    }
}
