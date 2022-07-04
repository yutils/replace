package com.yujing;

import com.yujing.utlis.ChangeFile;

import java.io.File;

/**
 * 修改build.gradle文件中的 gradle 和 kotlin版本
 *
 * @author yujing 2020年5月20日10:59:29
 */
/*
打包jar：
  1.项目右键 ——> 构建模块
  2.文件菜单 ——> 项目结构 ——> 点击构建（工作）——> 点击+ ——> 选择jar ——> 选择来自具有依赖项的模块 ——> 选择main函数，选择提取到目标jar --> 点击应用
  3.菜单构建 ——> 构建模块'replace'
  4.生成的jar目录：build\artifacts\replace_jar\***.jar

使用方法：
call java -jar -Dfile.encoding=utf-8 replace.jar
 */
public class Main {
  public static void main(String... args) {
    if (args.length > 0) ChangeFile.configureFile = new File(args[0]);
    ChangeFile.change();
  }
}
