package com.itcorey.util;

import com.alibaba.fastjson.JSON;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author corey
 * @Description
 * @create 2019-12-13 17:46
 */
public class imgUtils {

    /**
     * 得到http 图片地址的byte[]
     * @param url
     * @return
     */
    public static byte[] getFileStream(String url) {
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)httpUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();//通过输入流获取图片数据
            byte[] btImg = readInputStream(inStream);//得到图片的二进制数据
            return btImg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从输入流中获取数据
     * @param inStream
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while( (len=inStream.read(buffer)) != -1 ){
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }


    public static void main(String[] args) {
        String url = "http://img.blibao.com/upload/550912/member/logo/2019101722202926276-2019092917274672639-21766605.jpg";
        System.out.println("fileStream"+ JSON.toJSONString(getFileStream(url)));
    }

}

