package com.example.androidtest.tool;


import com.example.androidtest.R;
import com.example.androidtest.listview.Result;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;

public class Plant {
    //植物识别
        public  static String plant(String path, String ak, String sk) {
            // 请求url
            String url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/plant";
            String result="";
            try {
                // 本地文件路径
                byte[] imgData = FileUtil.readFileByBytes(path);
                String imgStr = Base64.encode(imgData);
                String imgParam = URLEncoder.encode(imgStr, "UTF-8");
                String param = "image=" + imgParam;
                String accessToken = Access_Token_Get.getAuth(ak, sk);
                result = HttpUtil.post(url, accessToken, param);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }
}
