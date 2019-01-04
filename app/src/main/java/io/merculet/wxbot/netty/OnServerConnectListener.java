package io.merculet.wxbot.netty;

public interface OnServerConnectListener {
    void onConnectSuccess();
    void onConnectFailed();
}
