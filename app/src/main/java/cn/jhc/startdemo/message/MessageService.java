package cn.jhc.startdemo.message;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;


public class MessageService extends Service {
    private Messenger messenger = new Messenger(new MessagerHandler());

    public MessageService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    private class MessagerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    Log.i("MessageService", "receive msg from client :" + msg.getData().getString("msg"));
                    try {
                        Messenger client = msg.replyTo;
                        Message message = Message.obtain(null, 1);
                        Bundle b = new Bundle();
                        b.putString("reply", "this message is from service.");
                        message.setData(b);
                        client.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }
}
