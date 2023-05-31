package com.yujing.utlis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yujing.bean.FileRep;
import com.yujing.bean.Json;
import com.yujing.bean.Rep;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.List;

/**
 * 修改替换文件内容
 *
 * @author yujing 2020年10月14日16:35:45
 */
public class ChangeFile {
    public static File configureFile;
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    static String currentDir;
    static Json json;

    static {
        try {
            // 参数为空,获取当前目录
            currentDir = new File("").getCanonicalPath();
            configureFile = new File(currentDir + "/replace.json");
            System.out.println("当前目录：" + currentDir);
        } catch (Exception e) {
            System.out.println("获取当前失败：" + e.getMessage());
        }
    }

    // 修改Gradle
    public static void change() {
        System.out.println("配置文件：" + configureFile.getAbsoluteFile().getPath());
        if (!configureFile.exists()) {
            System.out.println("配置文件不存在，自动创建！");
            List<FileRep> cs = new ArrayList<>();
            cs.add(new FileRep(
                    "**/*文件名1",
                    new Rep(new String[]{"旧的字符串1", "旧的字符串2"}, "新的字符串1")));
            cs.add(new FileRep(
                    "文件名2",
                    new Rep(new String[]{"旧的字符串1", "旧的字符串2"}, "新的字符串1"),
                    new Rep(new String[]{"旧的字符串3", "旧的字符串4"}, "新的字符串2")));

            List<String> ignores = new ArrayList<>();
            ignores.add("/test1/");
            ignores.add("/test2/build.gradle");
            Json json = new Json();
            json.setIgnores(ignores);
            json.setFileReps(cs);
            YFileUtil.stringToFile(configureFile, gson.toJson(json));
            System.out.println("创建完成,请修改配置后重新运行。\n");
            return;
        }
        String configureFileString = YFileUtil.fileToString(configureFile);
        // System.out.println("读取到配置：" + configureFileString);
        try {
            json = gson.fromJson(configureFileString, Json.class);
            System.out.println("读取配置成功！！");
        } catch (Exception e) {
            System.out.println("读取配置失败，请检查配置。\n" + e.getMessage());
            return;
        }
        // ------------------------------------------------
        File rootDir;
        if ("/".equals(json.getDir())) {
            rootDir = new File(currentDir);
        } else {
            rootDir = new File(json.getDir());
        }
        System.out.println("修改的目录：" + rootDir.getPath());
        System.out.println("开始扫描，请稍后...");
        // 遍历文件
        FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File file) {
                //相对目录
                String relative = file.getPath().substring(rootDir.getPath().length());
                relative = relative.replace("\\", "/");

                //是否忽略
                boolean isIgnore = false;
                for (String ignore : json.getIgnores()) {
                    ignore = ignore.replace("\\", "/");
                    //头部是/
                    if (ignore.charAt(0) == '/') {
                        if (relative.startsWith(ignore)) {
                            if (json.isShowLogIgnores())
                                System.out.println((file.isDirectory() ? "忽略文件夹:" : "忽略文件：") + relative);
                            isIgnore = true;
                            break;
                        }
                    }
                    //头部是*
                    if (ignore.charAt(0) == '*') {
                        if (relative.endsWith(ignore)) {
                            System.out.println((file.isDirectory() ? "忽略文件夹:" : "忽略文件：") + relative);
                            isIgnore = true;
                            break;
                        }
                    }
                    //都不是
                    if (ignore.charAt(0) != '/' && ignore.charAt(0) != '*') {
                        if (relative.startsWith(ignore)) {
                            System.out.println((file.isDirectory() ? "忽略文件夹:" : "忽略文件：") + relative);
                            isIgnore = true;
                            break;
                        }
                    }
                }
                if (isIgnore) {
                    return false;
                }

                if (file.isDirectory()) {
                    file.listFiles(this);
                } else {
                    if (file.isFile()) {
                        for (FileRep item : json.getFileReps()) {
                            // 如果包含通配符，地址这样 "fileName": "**/*.ovpn"  或  "**/vpn7?.ovpn" 或者 "**/ccd/vpn??"
                            if (item.getFileName().contains("*") || item.getFileName().contains("?")) {
                                PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:" + item.getFileName());
                                // 匹配成功，读取整个文件，并修改
                                if (matcher.matches(file.toPath())) {
                                    replace(relative, file, item.getReps());
                                }
                            } else {
                                // 不包含通配符，直接匹配，读取整个文件，并修改
                                if (item.getFileName().equals(file.getName())) {
                                    replace(relative, file, item.getReps());
                                }
                            }
                        }
                    }
                }
                return false;
            }
        };
        rootDir.listFiles(filter);
    }

    // 修改
    public static void replace(String relative, File file, List<Rep> reps) {
        boolean changed = false;
        if (json.isShowLogFindFile()) System.out.println("目标文件：" + relative);

        // 读取整个文件，并修改
        String fileString = YFileUtil.fileToString(file);
        if (fileString == null || fileString.isEmpty()) return;
        for (Rep r : reps) {
            for (String old : r.getOld()) {
                if (fileString.contains(old)) {
                    fileString = fileString.replace(old, r.getRep());
                    if (json.isShowLogChange()) System.out.println("修改：" + old + "——>" + r.getRep());
                    changed = true;
                } else {
                    if (json.isShowLogNoFindOld()) System.out.println("没有找到：" + old);
                }
            }
        }
        if (changed) {
            YFileUtil.stringToFile(file, fileString);
            if (json.isShowLogSaveFile()) System.out.println("保存：" + relative + "\n");
        }
    }
}
