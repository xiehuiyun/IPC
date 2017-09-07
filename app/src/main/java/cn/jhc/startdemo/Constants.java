package cn.jhc.startdemo;

/**
 * Created by Administrator on 2017/9/5.
 */

public interface Constants {

    interface API {
        String GET_TOKEN_URL = "/Token.ashx";//令牌
        String MESSAGE = "user/User_News_Get.ashx ";//新闻消息
    }

    interface ACTION_KEY {
        String ACTION_MESSAGE = "user_news";//新闻类型
    }

    interface IPC {
        int MSG_FROM_CLIENT = 0;
        int MSG_FROM_SERVICE = 1;
    }

}
