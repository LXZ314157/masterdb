package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.config.FtpConfig;
import com.icicle.masterdb.service.FtpService;
import com.icicle.masterdb.util.ExcelUtil;
import com.icicle.masterdb.util.LogUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

/**
 * @author liurenhua
 */
@Service
public class FtpServiceImpl implements FtpService {
    @Resource
    private FtpConfig ftpConfig;

    private final String SEPARATOR = "/";

    @Override
    public FTPClient getConnection() {
        FTPClient ftp = new FTPClient();
        try {
            ftp.connect(ftpConfig.getHost(), ftpConfig.getPort());
            ftp.login(ftpConfig.getUsername(), ftpConfig.getPassword());
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            ftp.enterLocalPassiveMode();
        } catch (IOException e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
        }
        return ftp;
    }

    @Override
    public void closeConnection(FTPClient ftpClient) {
        if (ftpClient != null) {
            try {
                ftpClient.logout();
                ftpClient.disconnect();
            } catch (IOException e) {
                LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            }
        }
    }


    @Override
    public boolean uploadFile(FTPClient ftp, InputStream inputStream, String path, String fileName) {
        //如果上传的文件路径为空，则默认放到一个随机的路径下面
        if (StringUtils.isBlank(path)) {
            path = ExcelUtil.getRandomPath();
        }
        //如果链接不存在，则创建客户端对象
        if (ftp == null) {
            ftp = getConnection();
        }
        try {
            ftp.enterLocalPassiveMode();
            String base = String.format("%s/%s", ftpConfig.getBasepath(), path);
            //检查上传路径是否存在 如果不存在返回false,不存在时创建路径
            boolean flag = ftp.changeWorkingDirectory(base);
            if (!flag) {
                mkDir(ftp, base);
            }
            ftp.changeWorkingDirectory(base);
            //指定上传文件的类型  二进制文件
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            //设置增大缓存区，缓冲区写满的时候一次性写入，减少IO操作
            ftp.setBufferSize(1024*1024);
            BufferedInputStream input = new BufferedInputStream(inputStream);
            //第一个参数是文件名
            return ftp.storeFile(fileName, input);
        } catch (SocketException e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
        } catch (IOException e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public boolean uploadFile(MultipartFile file, String imageUrl) {
        if (file == null || StringUtils.isBlank(imageUrl)) {
            return false;
        }
        FTPClient ftp = getConnection();
        boolean success = false;

        String[] t = imageUrl.split(SEPARATOR);
        String path = "";
        if (imageUrl.contains(SEPARATOR)) {
            path = imageUrl.substring(0, imageUrl.lastIndexOf(SEPARATOR));
        }

        try {
            success = uploadFile(ftp, file.getInputStream(), path, t[t.length - 1]);
        } catch (IOException e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
        }
        closeConnection(ftp);
        return success;
    }

    /**
     * 循环创建文件夹，解决makeDirectory方法一次只能创建一层目录的问题
     *
     * @param ftp
     * @param path
     */
    private void mkDir(FTPClient ftp, String path) {
        if (StringUtils.isBlank(path)) {
            return;
        }
        if (ftp == null) {
            ftp = getConnection();
        }
        ftp.enterLocalPassiveMode();
        String[] s = path.split(SEPARATOR);
        String pathName = "";
        for (String t : s) {
            if (StringUtils.isBlank(t)) {
                continue;
            }
            pathName = String.format("%s/%s", pathName, t);
            try {
                ftp.makeDirectory(pathName);
            } catch (Exception e) {
                LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            }
        }
    }
}
