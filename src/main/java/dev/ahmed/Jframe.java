package dev.ahmed;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Ahmed Bughra
 * @create 2022-12-21  8:51 PM
 */
public class Jframe {

    public static void Ui() {
        // Create a frame
        JFrame frame = new JFrame("Show Ip Information App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.DARK_GRAY);

        // Create a text field for the input string
        JTextField inputField = new JTextField(20);
        panel.add(inputField);
        inputField.setText("localhost");

        // Create a button
        JButton button = new JButton("Ip Infos");
        panel.add(button);
        JButton button1 = new JButton("Whois Infos");
        panel.add(button1);
        JButton button2 = new JButton("Port Scanner");
        panel.add(button2);
        JButton button3 = new JButton("Ping");
        panel.add(button3);

        // Create a text area for the output
        JTextArea outputArea = new JTextArea(25, 56);
        outputArea.setText(Utils.getYourMac());

        // Wrap the text field in a scroll pane
        JScrollPane scrollPane = new JScrollPane(outputArea);
        panel.add(scrollPane,BorderLayout.SOUTH);

        // Add the panel to the frame
        frame.add(panel);

        // Pack and show the frame
        frame.pack();
        frame.setSize(700,500);
        frame.setResizable(false);
        frame.setVisible(true);

        // Add an action listener to the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the input string
                String input = inputField.getText();

                // Clear the output area
                outputArea.setText("");

                // Split the string into characters
                try {
                    outputArea.append("IP Adresss:\t" + Utils.getIpInfos(input).get("query").toString() + "\n");
                    outputArea.append("Country:\t" + Utils.getIpInfos(input).get("country").toString() + "\n");
                    outputArea.append("City: \t" + Utils.getIpInfos(input).get("city").toString() + "\n");
                    outputArea.append("Organization:\t" + Utils.getIpInfos(input).get("org").toString() + "\n");
                    outputArea.append("ISP: \t" + Utils.getIpInfos(input).get("isp").toString() + "\n");
                    outputArea.append("Longitude: \t" + Utils.getIpInfos(input).get("lon").toString() + "\n");
                    outputArea.append("Latitude: \t" + Utils.getIpInfos(input).get("lat").toString() + "\n");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

        });

        // Add an action listener to the button1
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the input string
                String input = inputField.getText();

                // Clear the output area
                outputArea.setText("");


                // Split the string into characters
                try {
                    outputArea.append("IP Adresss:\t" + Utils.getWhoisInfo(input)+ "\n");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

        });

        // Add an action listener to the button2
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the input string
                String input = inputField.getText();

                // Clear the output area
                outputArea.setText("");


                // Split the string into characters
                try {
                    outputArea.append(Utils.portScannerMultiThread(input).toString());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

        });

        // Add an action listener to the button3
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the input string
                String input = inputField.getText();

                // Clear the output area
                outputArea.setText("");


                // Split the string into characters
                try {
                    outputArea.append(Utils.pingIpAddress(input));
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

        });
    }
}
