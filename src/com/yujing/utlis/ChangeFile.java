package com.yujing.utlis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yujing.bean.FileRep;
import com.yujing.bean.Json;
import com.yujing.bean.Rep;

import java.io.File;
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
      configureFile = new File(currentDir +  "/replace.json");
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
      cs.add(
          new FileRep(
              "**/*文件名1",
              new Rep(new String[] {"旧的字符串1", "旧的字符串2"}, "新的字符串1"),
              new Rep(new String[] {"旧的字符串3", "旧的字符串4"}, "新的字符串2")));
      cs.add(
          new FileRep(
              "文件名2",
              new Rep(new String[] {"旧的字符串1", "旧的字符串2"}, "新的字符串1"),
              new Rep(new String[] {"旧的字符串3", "旧的字符串4"}, "新的字符串2")));
      Json json = new Json();
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
    File dir;
    if ("/".equals(json.getDir())) {
      dir = new File(currentDir);
    } else {
      dir = new File(json.getDir());
    }
    System.out.println("修改的目录：" + dir.getPath());
    System.out.println("开始扫描，请稍后...");
    // 遍历文件
    YFileUtil.getFileAll(
        dir,
        file -> {
          if (file.isFile()) {
            for (FileRep item : json.getFileReps()) {
              // 如果包含通配符，地址这样 "fileName": "**/*.ovpn"  或  "**/vpn7?.ovpn" 或者 "**/ccd/vpn??"
              if (item.getFileName().contains("*") || item.getFileName().contains("?")) {
                PathMatcher matcher =
                    FileSystems.getDefault().getPathMatcher("glob:" + item.getFileName());
                // 匹配成功，读取整个文件，并修改
                if (matcher.matches(file.toPath())) replace(file, item.getReps());
              } else {
                // 不包含通配符，直接匹配，读取整个文件，并修改
                if (item.getFileName().equals(file.getName())) replace(file, item.getReps());
              }
            }
          }
          return false;
        });
  }

  // 修改
  public static void replace(File file, List<Rep> reps) {
    boolean changed = false;
    if (json.isShowLogFindFile()) System.out.println("目标文件：" + file.getPath());
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
      if (json.isShowLogSaveFile()) System.out.println("保存：" + file.getPath() + "\n");
    }
  }
}
