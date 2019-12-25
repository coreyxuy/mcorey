/*
package com.itcorey.test;

import org.springframework.http.HttpEntity;

import java.io.File;
import java.io.FileOutputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
*/
/**
 * @author corey
 * @Description
 * @create 2019-12-11 20:53
 */

/*
public class DEMO {
/*********************************************/
/*
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Map;

    public class WeixinMediaUtils {

        private final Logger logger = LogManager.getLogger(ServiceWeixinMedias.class);

        */
         /**
         * 卡券 上传图片接口
         *
         * @param accessToken 开放平台/或者公众平台的accessToken
         * @param imgStream   图片base64字符串
         * @return 上传成功后，返回的图片Url
         *//*

        public static Map<String, Object> uploadimg(String accessToken, String imgStream) {
            File tempFile = null;
            CloseableHttpClient httpClient = null;
            CloseableHttpResponse response = null;
            try {
                String url = String.format("%s%s",
                        "https://api.weixin.qq.com/cgi-bin/media/uploadimg",
                        "ACCESS_TOKEN="+accessToken))
            );
                tempFile = File.createTempFile("weixin_media", ".jpg");
                FileHelpers.decodeBase64ToFile(tempFile, imgStream);

                HttpPost httpPost = new HttpPost(url);
                FileBody fileBody = new FileBody(tempFile);
                HttpEntity httpEntity = MultipartEntityBuilder.create().addPart("buffer", fileBody).build();
                httpPost.setEntity(httpEntity);
                httpClient = HttpClients.createDefault();
                response = httpClient.execute(httpPost);
                HttpEntity responseEntity = response.getEntity();
                if (responseEntity != null) {
                    String responseEntityStr = EntityUtils.toString(response.getEntity());
                    System.out.println(responseEntityStr);
                    System.out.println("Response content length: " + responseEntity.getContentLength());
                    return JsonHelpers.deserializeToMap(responseEntityStr).orElseThrow(() -> new Exception("uploadimg failed,response realizeToMap raise Exception"));
                } else {
                    throw new Exception(String.format("call weixin interface fail,file 【%s】 upload fail", tempFile.getAbsolutePath()));
                }
            } catch (Throwable t) {
                t.printStackTrace();
                throw new Exception(t.getMessage());
            } finally {
                if (tempFile!=null) {
                    tempFile.delete();
                }
                if (httpClient!=null) {
                    HttpClientUtils.closeQuietly(httpClient);
                }
                if (response!=null) {
                    HttpClientUtils.closeQuietly(response);
                }
            }
        }








    }
*/
