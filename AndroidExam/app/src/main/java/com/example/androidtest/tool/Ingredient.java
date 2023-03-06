package com.example.androidtest.tool;
import java.net.URLEncoder;

public class Ingredient {
    public static String ingredient(String path,String ak,String sk){
        String url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/classify/ingredient";
        String result="";
        try {
            byte[] imgData = FileUtil.readFileByBytes(path);
            String imgStr = Base64.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");
            String param = "image=" + imgParam;
            String accessToken = Access_Token_Get.getAuth(ak,sk);
            result = com.example.androidtest.tool.HttpUtil.post(url, accessToken, param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
