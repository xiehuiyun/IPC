package cn.jhc.startdemo.common.base;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import cn.jhc.startdemo.App;
import cn.jhc.startdemo.common.bean.ResultEntity;
import cn.jhc.startdemo.common.bean.Token;
import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static cn.jhc.startdemo.Constants.API.GET_TOKEN_URL;
import static cn.jhc.startdemo.common.utils.StrUtil.MD5;
import static cn.jhc.startdemo.common.utils.StrUtil.getDesURLEncodeString;
import static cn.jhc.startdemo.common.utils.StrUtil.getMD5Key;

/**
 * Created by Administrator on 2017/9/5.
 */

public class BaseRepo {

    private final ValidateService mService;
    private final Gson mGson = new Gson();

    public BaseRepo() {
        mService = App.getRetrofit().create(ValidateService.class);
    }

    /**
     * 组合必要信息，合成post表单数据Map
     *
     * @param key          请求key
     * @param businessJson 业务数据，json格式
     * @param token        当前请求的token
     * @return 合成的post表单数据Map
     * @throws Exception des加密异常，json序列化异常
     */
    protected Map<String, String> compose(String key, String businessJson, String token) throws Exception {
        LinkedHashMap<String, String> generalData = new LinkedHashMap<>();
        generalData.put("key", key);
        generalData.put("sign", MD5(businessJson + getMD5Key()).toLowerCase());
        generalData.put("token", token);
        generalData.put("data", businessJson);

        Map<String, String> params = new HashMap<>();
        params.put("data", getDesURLEncodeString(mGson.toJson(generalData)));
        return params;
    }

    /**
     * 获取令牌
     *
     * @return 令牌Observable
     */
    protected Observable<ResultEntity<Token>> fetchToken(String key) {
        Map<String, String> rawData = new HashMap<>();
        rawData.put("key", key);
        rawData.put("sign", MD5(key + getMD5Key()).toLowerCase());
        String data = mGson.toJson(rawData);

        Map<String, String> params = new HashMap<>();
        try {
            params.put("data", getDesURLEncodeString(data));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mService.fetchToken(params);
    }

    interface ValidateService {
        /**
         * 获取token
         *
         * @return 登录结果 observable
         */
        @FormUrlEncoded
        @POST(GET_TOKEN_URL)
        Observable<ResultEntity<Token>> fetchToken(@FieldMap Map<String, String> map);
    }

}
