package com.nmid.calculate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Aaron
 * @description
 * @date 2020/5/21 11:20 PM
 */
public class error {
    private JButton confirm;
    public static JFrame frame;

    public error() {
        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Index.frame.setVisible(true);
                frame.dispose();

            }
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("少做作业多睡觉");
        frame.setContentPane(new error().error);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel error;
}
