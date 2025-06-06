package com.cell.spring6.circular_dependency;

public class Wife {
    private String name;
    private Husband husband;

    public void Wife () {

    }

    /*public Wife(String name, Husband husband) {
        this.name = name;
        this.husband = husband;
    }*/

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setHusband(Husband husband) {
        this.husband = husband;
    }

    // toString()方法重写时需要注意：不能直接输出husband，输出husband.getName()。要不然会出现递归导致的栈内存溢出错误。
    @Override
    public String toString() {
        return "Wife{" +
                "name='" + name + '\'' +
                ", husband=" + husband.getName() +
                '}';
    }
}
