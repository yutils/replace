package com.yujing.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 文件替换详情信息
 */
public class FileRep {
    String fileName;
    List<Rep> reps =new ArrayList<>();

    public FileRep() {
    }

    public FileRep(String fileName, List<Rep> res) {
        this.fileName = fileName;
        this.reps = res;
    }

    public FileRep(String fileName, Rep... rs) {
        this.fileName = fileName;
        reps.addAll(Arrays.asList(rs));
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<Rep> getReps() {
        return reps;
    }

    public void setReps(List<Rep> reps) {
        this.reps = reps;
    }
}
