package com.yujing.bean;

/**
 * 替换
 */
public class Rep {
    String[] old;//旧的字符串
    String rep;//新的字符串

    public Rep() {
    }

    public Rep(String[] old, String rep) {
        this.old = old;
        this.rep = rep;
    }

    public String[] getOld() {
        return old;
    }

    public void setOld(String[] old) {
        this.old = old;
    }

    public String getRep() {
        return rep;
    }

    public void setRep(String rep) {
        this.rep = rep;
    }
}
