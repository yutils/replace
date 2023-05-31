package com.yujing.utlis;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件工具类
 *
 * @author 余静 2018年5月15日19:00:17
 */
@SuppressWarnings({"WeakerAccess", "unused", "ResultOfMethodCallIgnored"})
public class YFileUtil {
    /**
     * string转file
     *
     * @param file 文件
     * @param str  要转换的string
     */
    public static void stringToFile(File file, String str) {
        byteToFile(str.getBytes(), file);
    }

    /**
     * string转file
     *
     * @param file    文件
     * @param str     要转换的String
     * @param charset 字符集
     */
    public static void stringToFile(File file, String str, Charset charset) {
        byteToFile(str.getBytes(charset), file);
    }

    /**
     * file转string
     *
     * @param file 文件
     * @return 转出后的String
     */
    public static String fileToString(File file) {
        byte[] data = fileToByte(file);
        if (data == null)
            return null;
        return new String(data);
    }


    /**
     * bytes转file
     *
     * @param bytes byte[]
     * @param file  转出后的String
     * @return 是否成功
     */
    public static boolean byteToFile(byte bytes[], File file) {
        if (!file.getParentFile().exists()) // 如果位置不存在
            file.getParentFile().mkdirs();
        if (file.exists())
            file.delete();
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            out.write(bytes);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("No Find File");
            return false;
        } catch (IOException e) {
            System.out.println("IO Error");
            return false;
        }
        return true;
    }

    /**
     * file转bytes
     *
     * @param file 文件
     * @return 结果byte[]
     */
    public static byte[] fileToByte(File file) {
        if (file == null || !file.exists()) {
            return null;
        }
        FileInputStream stream = null;
        ByteArrayOutputStream out = null;
        try {
            stream = new FileInputStream(file);
            out = new ByteArrayOutputStream((int) file.length());
            byte[] b = new byte[1024 * 4];
            int n;
            while ((n = stream.read(b)) != -1)
                out.write(b, 0, n);
            return out.toByteArray();
        } catch (IOException ignored) {
        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException ignored) {
            }
        }
        return null;
    }


    /**
     * 删除文件或文件夹
     *
     * @param filePath 文件夹路径
     * @return 若删除成功，则返回True；反之，则返回False
     */
    public static boolean delFile(String filePath) {
        File file = new File(filePath);
        if (file.isFile()) {
            return file.delete();
        } else if (file.isDirectory()) {
            if (file.listFiles().length == 0) {
                return file.delete();
            } else {
                int zFiles = file.listFiles().length;
                File[] delFile2 = file.listFiles();
                for (int i = 0; i < zFiles; i++) {
                    if (delFile2[i].isDirectory()) {
                        delFile(delFile2[i].getAbsolutePath());
                    }
                    delFile2[i].delete();
                }
                return file.delete();
            }
        } else {
            return false;
        }
    }


    /**
     * 复制文件/文件夹 若要进行文件夹复制，请勿将目标文件夹置于源文件夹中
     *
     * @param source   源文件（夹）
     * @param target   目标文件（夹）
     * @param isFolder 若进行文件夹复制，则为True；反之为False
     * @throws IOException IOException
     */
    public static void copy(String source, String target, boolean isFolder) throws IOException {
        if (isFolder) {
            new File(target).mkdirs();
            File a = new File(source);
            String[] file = a.list();
            File temp;
            for (String aFile : file) {
                if (source.endsWith(File.separator)) {
                    temp = new File(source + aFile);
                } else {
                    temp = new File(source + File.separator + aFile);
                }
                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(target + File.separator + temp.getName());
                    byte[] b = new byte[1024];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if (temp.isDirectory()) {
                    copy(source + File.separator + aFile, target + File.separator + aFile, true);
                }
            }
        } else {
            File oldFile = new File(source);
            if (oldFile.exists()) {
                InputStream inputStream = new FileInputStream(source);
                File file = new File(target);
                file.getParentFile().mkdirs();
                file.createNewFile();
                FileOutputStream outputStream = new FileOutputStream(file);
                byte[] buffer = new byte[1024];
                int byteRead;
                while ((byteRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, byteRead);
                }
                inputStream.close();
                outputStream.close();
                file.setLastModified(oldFile.lastModified());
            }
        }
    }

    /**
     * 获取文件夹下全部文件
     *
     * @param dir 读取的文件夹
     * @return 文件列表
     */
    public static List<File> getFileAll(File dir) {
        final List<File> fileAll = new ArrayList<>();
        getFileAll(dir, file -> {
            if (!file.isDirectory())
                fileAll.add(file);
            return false;
        });
        return fileAll;
    }

    /**
     * 递归获取dir文件夹下全部文件
     *
     * @param dir        读取的文件夹
     * @param fileFilter 回调读取到的每一个文件或者文件夹
     */
    public static void getFileAll(File dir, final FileFilter fileFilter) {
        FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isDirectory())
                    file.listFiles(this);
                fileFilter.accept(file);
                return false;
            }
        };
        dir.listFiles(filter);
    }
}
