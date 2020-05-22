package com.nmid.calculate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Aaron
 * @description
 * @date 2020/5/21 8:14 PM
 */
public class Index {
    public static JFrame frame;
    private JButton 确认Button;
    private JCheckBox add;
    private JCheckBox reduce;
    private JCheckBox multi;
    private JCheckBox division;
    private JTextField ceilingu;
    private JTextField varnum;
    private JPanel all;
    private JCheckBox point;
    private JTextField ceilingd;
    private JTextField probnum;
    private JTextField pointnum;

    //存储数字
    private int ceilingU;
    private int ceilingD;
    private int varNum;
    private int proNum;
    private int pointNum;
    //存储是否勾选
    boolean addFlat = false;
    boolean reduceFlat = false;
    boolean multiFlat = false;
    boolean divisFlat = false;
    boolean pointFlat = false;
    //异常变量,true为通过，无异常
    private boolean exception = true;
    //正则表达式
    //匹配所有自然数
    private String regex1 = "^-?\\d+$";
    //匹配正数
    private String regex2 = "^[0-9]*[1-9][0-9]*$";

    /**
     * 1.设置加减乘除的取反操作
     * 2.获取数字的上下限
     * 3.获取变量的上限
     * 4.获取题目数量
     */
    public Index() {
        getBoolean();
        action();

    }

    //获取上下限、变量个数、题目数量
    private void action() {
        //获取
        确认Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //如果加减乘除一个没有选
                if (!(addFlat || reduceFlat || multiFlat || divisFlat)) {
                    exception = false;
                    error();
                }
                getNum();
                expression();
            }
        });
    }

    private void expression() {
        //初始化操作符号
        Operator.initialtOperatorBase(addFlat, reduceFlat, multiFlat, divisFlat);
        //定义自己的list
        List<String> problems = new MyList<String>();
        int pow = 0;
        if (pointFlat) {
            pow = (int) Math.pow(10, pointNum);
        }
        for (int i = 0; i < proNum; i++) {
            StringBuilder expression = new StringBuilder();
            boolean start = true;
            expression.append(i + 1 + ": ");
            for (int j = 0; j < varNum - 1; j++) {
                if (pointFlat) {
                    if (start) {
                        expression.append(Math.floor((Math.random() * (ceilingU - ceilingD) + ceilingD) * pow) / pow);
                        start = false;
                    }
                    //选择运算符及操作数字
                    expression.append(Operator.getOperator() + Math.floor((Math.random() * (ceilingU - ceilingD) + ceilingD) * pow) / pow);
                } else {
                    if (start) {
                        expression.append((int) (Math.random() * (ceilingU - ceilingD) + ceilingD));
                        start = false;
                    }
                    expression.append(Operator.getOperator() + (int) (Math.random() * (ceilingU - ceilingD) + ceilingD));
                }
            }
            expression.append(" = \r\n");
            problems.add(expression.toString());
        }
        out2File(problems);
        //跳转成功
        if (exception) {
            frame.setVisible(false);
            success.main(null);
        }

    }

    private void out2File(List<String> problems) {

        try {
            File file = new File("assignments.txt");
            if (!file.exists()) {
                file.exists();
            }
            file.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(problems.toString());
            out.flush();
            out.close();
        } catch (IOException e) {
            exception = false;
            e.printStackTrace();
        }
        Operator.operation.clear();
    }

    private void getNum() {
        //1.数字上限
        String ceilingUtext = ceilingu.getText();
        boolean utextB = Pattern.matches(regex1, ceilingUtext);
        //2.数字下限
        String ceilingDtext = ceilingd.getText();
        boolean dtextB = Pattern.matches(regex1, ceilingDtext);
        //3.获取多少个变量
        String varnumText = varnum.getText();
        boolean varnumTextB = Pattern.matches(regex2, varnumText);
        //4.获取题目数量
        String probnumText = probnum.getText();
        boolean probnumTextB = Pattern.matches(regex2, probnumText);
        //5.如果勾选小数，获取位数
        Boolean ponintB = true;
        String pointText = "";
        if (pointFlat) {
            pointText = pointnum.getText();
            boolean pointTextB = Pattern.matches(regex2, pointText);
            if (!pointTextB) {
                ponintB = false;
            }
        }
        //判断是否为纯数字,是则转为int类型
        if (utextB && dtextB && varnumTextB && probnumTextB && ponintB) {
            ceilingU = Integer.parseInt(ceilingUtext);
            ceilingD = Integer.parseInt(ceilingDtext);
            //如果上限小于或者等于下限
            if (ceilingU < ceilingD || ceilingU == ceilingD) {
                error();
            }
            varNum = Integer.parseInt(varnumText);
            proNum = Integer.parseInt(probnumText);
            if (pointFlat) {
                pointNum = Integer.parseInt(pointText);
            }
        } else {
            error();
        }
    }

    //打开异常面板
    private void error() {
        exception = false;
        frame.setVisible(false);
        error.main(null);
    }

    //获取checkBox里面的布尔值
    private void getBoolean() {
        add.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //取反操作
                add.setBorderPaintedFlat(!add.isBorderPaintedFlat());
                addFlat = add.isBorderPaintedFlat();
                super.mousePressed(e);
            }
        });
        reduce.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                reduce.setBorderPaintedFlat(!reduce.isBorderPaintedFlat());
                reduceFlat = reduce.isBorderPaintedFlat();
                super.mousePressed(e);
            }
        });
        multi.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                multi.setBorderPaintedFlat(!multi.isBorderPaintedFlat());
                multiFlat = multi.isBorderPaintedFlat();
                super.mousePressed(e);
            }
        });
        division.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                division.setBorderPaintedFlat(!division.isBorderPaintedFlat());
                divisFlat = division.isBorderPaintedFlat();
                super.mousePressed(e);
            }
        });
        point.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                point.setBorderPaintedFlat(!point.isBorderPaintedFlat());
                pointFlat = point.isBorderPaintedFlat();
                super.mousePressed(e);
            }
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("好好学习，天天向上！");
        frame.setContentPane(new Index().all);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
