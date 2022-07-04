package com.yujing.bean;

import java.util.List;

public class Json {
    String dir = "/";//目录
    boolean showLogFindFile = false;//显示找到 目标文件 日志
    boolean showLogNoFindOld = false;//显示找到 没有找到要替换的字符串 日志
    boolean showLogChange = true;//显示 修改日志 日志
    boolean showLogSaveFile = true;//显示 保持文件 日志
    List<FileRep> FileReps;//文件对比详情

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public List<FileRep> getFileReps() {
        return FileReps;
    }

    public void setFileReps(List<FileRep> fileReps) {
        FileReps = fileReps;
    }

    public boolean isShowLogFindFile() {
        return showLogFindFile;
    }

    public void setShowLogFindFile(boolean showLogFindFile) {
        this.showLogFindFile = showLogFindFile;
    }

    public boolean isShowLogNoFindOld() {
        return showLogNoFindOld;
    }

    public void setShowLogNoFindOld(boolean showLogNoFindOld) {
        this.showLogNoFindOld = showLogNoFindOld;
    }

    public boolean isShowLogChange() {
        return showLogChange;
    }

    public void setShowLogChange(boolean showLogChange) {
        this.showLogChange = showLogChange;
    }

    public boolean isShowLogSaveFile() {
        return showLogSaveFile;
    }

    public void setShowLogSaveFile(boolean showLogSaveFile) {
        this.showLogSaveFile = showLogSaveFile;
    }
}
