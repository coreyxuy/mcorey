package com.itcorey.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.itcorey.util.FileUtils;
import com.itcorey.util.imgUtils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import javax.activation.MimetypesFileTypeMap;

/**
 * @author corey
 * @Description
 * @create 2019-12-25 11:27
 */
public class FormUpdate {

    /**
     * 上传图文消息内的图片获取URL
     * @param args
     */
    public static void main(String[] args) {
        String url = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=28_-xV0sPqc4nsCBmKAfMwlfq30GDMwUGTUaTNCYcLQEa0eELOVmScidGFVL-Ouhb2qQ_PS_TiiPztSTdJ_osolm-6B5eC4wed-0UvuUKc_wTTsC_jSob18ir-eWcZH9J7caB4mgcS5MU1n4vJVVRBdAFDGCM";
        byte[] fileStream = imgUtils.getFileStream(url);
        File file = new File(Arrays.toString(fileStream));
        String jsonStr = FormUpdate.formUpload(url, file);
        String returnUrl = "";
        if(jsonStr.indexOf("errcode") == -1) {
            JSONObject jsonObject = JSON.parseObject(jsonStr);
            System.out.println(jsonObject.get("url").toString());
        }
    }


    /**
     * 上传图片
     * @param urlStr
     * @param file
     * @return
     */
    public static String formUpload(String urlStr, File file) {
        String res = "";
        HttpURLConnection conn = null;
        String BOUNDARY = "---------------------------123821742118716"; // boundary就是request头和上传文件内容的分隔符
        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(30000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
            OutputStream out = new DataOutputStream(conn.getOutputStream());

            // file
            String inputName = "";
            String filename = file.getName();
            String contentType = new MimetypesFileTypeMap().getContentType(file);
            if (filename.endsWith(".png")) {
                contentType = "image/png";
            }
            if (contentType == null || contentType.equals("")) {
                contentType = "application/octet-stream";
            }

            StringBuffer strBuf = new StringBuffer();
            strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
            strBuf.append(
                    "Content-Disposition: form-data; name=\"" + inputName + "\"; filename=\"" + filename + "\"\r\n");
            strBuf.append("Content-Type:" + contentType + "\r\n\r\n");
            out.write(strBuf.toString().getBytes());
            DataInputStream in = new DataInputStream(new FileInputStream(file));
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
            in.close();
            byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
            out.write(endData);
            out.flush();
            out.close();

            // 读取返回数据
            StringBuffer strBuf2 = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                strBuf2.append(line).append("\n");
            }
            res = strBuf2.toString();
            reader.close();
            reader = null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
                conn = null;
            }
        }
        return res;
    }
}
