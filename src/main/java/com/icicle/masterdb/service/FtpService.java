package com.icicle.masterdb.service;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author liurenhua
 */
public interface FtpService {

    /**
     * 上传文件到FTP,如果上传多个文件，将使用同一个连接，此时将ftp作为参数，传进来
     *
     * @param ftp
     * @param inputStream
     * @param path
     * @param fileName
     * @return 文件是否上传成功
     */
    boolean uploadFile(FTPClient ftp, InputStream inputStream, String path, String fileName);

    /**
     * 上传一个带路径和文件名的图片如 model/17/AAA******.jpg 也可以是 AAA***.jpg
     * 后者则会产生一个随机的路径
     *
     * @param file
     * @param imageUrl
     * @return 上传文件是否成功
     */
    boolean uploadFile(MultipartFile file, String imageUrl);

    /**
     * 获取一个FTP链接
     *
     * @return
     */
    FTPClient getConnection();

    /**
     * 关闭一个Ftp连接
     *
     * @param ftpClient
     */
    void closeConnection(FTPClient ftpClient);

}
