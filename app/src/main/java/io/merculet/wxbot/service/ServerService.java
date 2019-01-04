package io.merculet.wxbot.service;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.net.InetSocketAddress;

import io.merculet.wxbot.netty.NettyClient;
import io.merculet.wxbot.netty.OnServerConnectListener;
import io.merculet.wxbot.util.Config;


public class ServerService extends Service {
    private static final String TAG = "ServerService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //onStartCommand方法在我们调用starService开启服务时被执行
                // 开始执行后台任务
                NettyClient.getInstance()
                        .connect(new InetSocketAddress(Config.ADDRESS, Config.PORT_NUMBER), new OnServerConnectListener() {
                            @Override
                            public void onConnectSuccess() {
                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Log.i(TAG, "aaron connect run");
                                    }
                                });

                            }

                            @Override
                            public void onConnectFailed() {
                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Log.i(TAG, "aaron connect run failed");
                                    }
                                });

                            }
                        });
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        NettyClient.getInstance().shutDown();
    }


    private static class NettyClientTask extends AsyncTask<String, Void, Boolean> {
        /**
         * The system calls this to perform work in a worker thread and
         * delivers it the parameters given to AsyncTask.execute()
         */
        protected Boolean doInBackground(String... urls) {
            NettyClient.getInstance()
                    .connect(new InetSocketAddress(Config.ADDRESS, Config.PORT_NUMBER), new OnServerConnectListener() {
                        @Override
                        public void onConnectSuccess() {
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    Log.i(TAG, "aaron connect success");
                                }
                            });

                        }

                        @Override
                        public void onConnectFailed() {
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    Log.i(TAG, "aaron connect  failed");
                                }
                            });

                        }
                    });
            return null;
        }

        /**
         * The system calls this to perform work in the UI thread and delivers
         * the result from doInBackground()
         */
        protected void onPostExecute(Boolean result) {
        }
    }
}