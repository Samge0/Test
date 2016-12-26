package com.getui.test;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.socks.library.KLog;

public class DemoApplication extends Application {

    private static final String TAG = "GetuiSdkDemo";

    private static DemoHandler handler;
    public static GetuiSdkDemoActivity demoActivity;
    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    /**
     * 应用未启动, 个推 service已经被唤醒,保存在该时间段内离线消息(此时 GetuiSdkDemoActivity.tLogView == null)
     */
    public static StringBuilder payloadData = new StringBuilder();

    @Override
    public void onCreate() {
        super.onCreate();
        KLog.init(true);

        // 上下文
        mContext=getApplicationContext();

        Log.d(TAG, "DemoApplication onCreate");

        if (handler == null) {
            handler = new DemoHandler();
        }
    }

    public static void sendMessage(Message msg) {
        handler.sendMessage(msg);
    }

    public static class DemoHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    if (demoActivity != null) {
                        payloadData.append((String) msg.obj);
                        payloadData.append("\n");
                        if (GetuiSdkDemoActivity.tLogView != null) {
                            GetuiSdkDemoActivity.tLogView.append(msg.obj + "\n");
                        }
                    }
                    break;

                case 1:
                    if (demoActivity != null) {
                        if (GetuiSdkDemoActivity.tLogView != null) {
                            GetuiSdkDemoActivity.tView.setText((String) msg.obj);
                        }
                    }
                    break;
            }
        }
    }
}
