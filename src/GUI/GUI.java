package GUI;

import Client.Client;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUI extends JFrame{
    private JPanel startPanel;
    private JTextField textField1;
    private JButton openImageButton;
    private JTextField textField2;
    private JTextField textField3;
    private JButton tryToSendButton;
    private JRadioButton SSLRadioButton;
    private JTextField certPath;
    private JButton openCertButton;
    private JCheckBox SSLCheckBox;
    private JLabel image;
    private String imagePath;
    private String certPathString;
    public GUI() {
        this.setContentPane(startPanel);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        openCertButton.setEnabled(false);
        certPath.setEnabled(false);
        openImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser file = new JFileChooser();
                file.setCurrentDirectory(new File(""));
                file.showOpenDialog(file);
                imagePath = file.getSelectedFile().getAbsolutePath();
                textField1.setText(imagePath);
            }
        });
        tryToSendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(SSLCheckBox.isSelected())
                    new Client(textField2.getText(),Integer.parseInt(textField3.getText()),imagePath,certPathString);
                else
                    new Client(textField2.getText(),Integer.parseInt(textField3.getText()),imagePath);
            }
        });
        openCertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser file = new JFileChooser();
                file.setCurrentDirectory(new File(""));
                file.showOpenDialog(file);
                certPathString = file.getSelectedFile().getAbsolutePath();
                certPath.setText(certPathString);
            }
        });
        SSLCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCertButton.setEnabled(SSLCheckBox.isSelected());
                certPath.setEnabled(SSLCheckBox.isSelected());
            }
        });
    }
}
