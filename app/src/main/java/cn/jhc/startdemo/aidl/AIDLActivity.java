package cn.jhc.startdemo.aidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import java.util.List;

import butterknife.OnClick;
import cn.jhc.startdemo.R;
import cn.jhc.startdemo.common.base.BaseActivity;
import cn.jhc.startdemo.contentProvider.ContentProviderActivity;
import cn.jhc.startdemo.message.MessageService;

public class AIDLActivity extends BaseActivity {

    private IBookManager manager;
    private IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            if (manager == null) {
                return;
            }
            manager.asBinder().unlinkToDeath(deathRecipient, 0);
            manager = null;

            //断开连接后重新连接
            Intent intent = new Intent(AIDLActivity.this, AIDLService.class);
            bindService(intent, connection1, BIND_AUTO_CREATE);
        }
    };

    private ServiceConnection connection1 = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            manager = IBookManager.Stub.asInterface(iBinder);
            try {
                List<Book> list = manager.getBooks();
                Log.i("AIDLActivity", "query book list,list type:" + list.getClass().getCanonicalName());
                Log.i("AIDLActivity", "query book list :" + list.toString());
                //添加数据
                manager.addBook(new Book(3, "Book3"));

                list = manager.getBooks();
                Log.i("AIDLActivity", "query book list,list type:" + list.getClass().getCanonicalName());
                Log.i("AIDLActivity", "query book list :" + list.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };


    //____________________________*****************************________________________________
    private Messenger mService = new Messenger(new MessagerHandler());

    private class MessagerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    //接收service发送的消息
                    Log.i("MessageService", "receive msg from service :" + msg.getData().getString("reply"));
                    break;
            }
        }
    }

    private ServiceConnection connection2 = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            //发送消息给服务器
            Messenger messenger = new Messenger(iBinder);
            Message message = Message.obtain(null, 0);
            Bundle bundle = new Bundle();
            bundle.putString("msg", "this is client.");
            message.setData(bundle);
            //并且将mService传递过去。使得MessageService给AIDLActivity发送消息时有Messaenger,这样service发送的消息可以在AIDLActivity中的MessagerHandler中接受消息
            message.replyTo = mService;
            try {
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected int getLayoutid() {
        return R.layout.activity_aidl;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection1);
        unbindService(connection2);
    }


    @OnClick({R.id.aidl_bind_aidl_service, R.id.aidl_bind_message_service, R.id.move_to_contentprovider})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.aidl_bind_aidl_service:
                Intent intent = new Intent(this, AIDLService.class);
                bindService(intent, connection1, BIND_AUTO_CREATE);
                break;
            case R.id.aidl_bind_message_service:
                Intent intent1 = new Intent(this, MessageService.class);
                bindService(intent1, connection2, BIND_AUTO_CREATE);
                break;
            case R.id.move_to_contentprovider:
                Intent intent2 = new Intent(this, ContentProviderActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
