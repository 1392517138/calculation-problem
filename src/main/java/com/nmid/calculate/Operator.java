package com.nmid.calculate;

import com.sun.org.apache.regexp.internal.RE;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Aaron
 * @description
 * @date 2020/5/22 12:00 AM
 */
public enum Operator {
    ADD(1,"+"),
    REDUCE(2,"-"),
    MULTIPLICATION(3,"×"),
    DIVISION(4,"÷");
    private int code;
    private String name;
    //重写toString
    public static LinkedList<Integer> operation = new LinkedList<Integer>();
    Operator(int code, String name) {
        this.code = code;
        this.name = name;
    }



    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
    public static void initialtOperatorBase(boolean add,boolean reduce,boolean multi,boolean divise){
        if (add){
            operation.add(1);
        }
        if (reduce){
            operation.add(2);
        }
        if (multi){
            operation.add(3);
        }
        if (divise){
            operation.add(4);
        }
    }
    public static String getOperator(){
        //产生随机索引
        int i = (int)(Math.random()*operation.size());
        System.out.println(i);
        switch (operation.get(i)){
            case 1:
                return ADD.name;
            case 2:
                return REDUCE.name;
            case 3:
                return MULTIPLICATION.name;
            case 4:
                return DIVISION.name;
            default:
                return " #error# ";
        }
    }
}
