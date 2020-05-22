package com.nmid.calculate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Aaron
 * @description
 * @date 2020/5/22 12:05 PM
 */
public class success {
    private static JFrame frame;
    private JButton 确认Button;
    private JPanel success;

    public success() {
        确认Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Index.frame.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("橙子很开心!");
        frame.setContentPane(new success().success);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
