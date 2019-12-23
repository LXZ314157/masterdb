package com.icicle.masterdb.util;

import cn.com.icicle.messageclient.service.MessageClientService;
import cn.com.icicle.messageclient.service.impl.MessageClientServiceImpl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.icicle.masterdb.core.MessageRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Base64Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author liurenhua
 */
public class SyncHelper {
    /**
     * @param path   请求路径
     * @param object 请求参数
     * @return
     */

    public static Boolean synData(String path, Object object) {
        if (object == null || StringUtils.isBlank(path)) {
            return false;
        }
        String data;
        if (object instanceof String) {
            data = object.toString();
        } else {
            data = JSON.toJSONString(object, SerializerFeature.WriteDateUseDateFormat);
        }
        if (data == null) {
            return false;
        }
        MessageClientService client = new MessageClientServiceImpl();
        try {
            boolean flag =  client.send(path, data);
            LogUtil.getLogger(SyncHelper.class).info(StringUtil.gsFormat("插件同步记录：(time:{0},path:{1},object:{2},result:{3})",DateUtil.stringNow(), path, data,flag));
            return flag;
        } catch (Exception ex) {
            LogUtil.getLogger(SyncHelper.class).error(
                    StringUtil.gsFormat("synData(path:{0}, object: {1}, error: {2})",
                            path, data, ex.getMessage()), ex);
            return false;
        }


    }

    public static Boolean syncDept2Yxt(Map<String,String> map,String url){
        String ousUrl = map.get("ousUrl");
        String messagecenter = map.get("messagecenter");
        String key = map.get("key");
        String body = map.get("body");

        String fullPath = ousUrl.concat(messagecenter);
        MessageRequest request = new MessageRequest();
        String id = UUID.randomUUID().toString();
        request.setId(id);
        request.setIsEncrypt(false);
        request.setBody(body);
        String Authorization1 = Base64Utils.encodeToString(key.getBytes());
        Map<String,String> map1 = new HashMap<>();
        map1.put("Authorization",Authorization1);
        map1.put("Content-Type","application/json;charset=UTF-8");
        String params =JSON.toJSONString(request);
        String response1 = HttpRequestUtil.postRequest(fullPath, params,map1);
        Boolean ret = JSON.parseObject(response1,Boolean.class);
        return false;
    }


}