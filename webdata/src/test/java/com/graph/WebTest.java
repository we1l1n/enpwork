package com.graph;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ACT-NJ on 2017/7/16.
 */
public class WebTest {
    public static void main(String[] args) {
        HttpGet re = new HttpGet("http://comment.news.163.com/api/v1/products/a2869674571f77b5a0867c3d71db5856/threads/CPLAC6O400018AOQ/comments/newList?offset=0&limit=30&headLimit=5&tailLimit=1&ibc=newswap");
        try {
            HttpResponse response = HttpClients.createDefault().execute(re);
            if(response.getStatusLine().getStatusCode()==200) {
                String s = EntityUtils.toString(response.getEntity(),"GBK");
                JSONObject jsonObject = JSONObject.parseObject(s);
                String st = jsonObject.getString("commentIds");
                System.out.println(st);
                Pattern pattern = Pattern.compile("\"(.+?)\"");
                Matcher matcher = pattern.matcher(st);
                while(matcher.find()){
                    String level = matcher.group(1);
                    if(level.split(",").length<2)
                        continue;
                    System.out.println(level);
                }
            }
            } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
