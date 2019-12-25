package com.itcorey.fileHelpers;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileOutputStream;
import java.io.File;
import java.io.OutputStream;

/**
 * @author corey
 * @Description
 * @create 2019-12-13 17:28
 */
public class FileHelpers {

    private static Logger logger = LoggerFactory.getLogger(FileHelpers.class);

    /**
     * 解析base64格式图片，并保存到临时目录
     *
     * @param base64Str base64格式图片
     * @param file 临时文件对象
     * @return File Object
     * @throws Exception
     */
    public static void decodeBase64ToFile(File file,String base64Str) throws Exception {

        OutputStream out = null;
        try {
            int prefixIndex = base64Str.indexOf(",");
            byte[] buffer = Base64.decodeBase64(base64Str.substring(prefixIndex + 1));
            out = new FileOutputStream(file);
            out.write(buffer);
            out.flush();
        } catch (Exception e) {
            logger.info("decodeBase64ToFile raise exception:" + e.getMessage());
            throw new Exception(e.getMessage());
        } finally {
            if (out!=null) {
                out.close();
            }
        }
    }
}
