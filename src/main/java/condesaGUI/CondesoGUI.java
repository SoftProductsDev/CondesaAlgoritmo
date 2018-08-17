package condesaGUI;

import javax.swing.*;

public class CondesoGUI extends JPanel{
    private JPanel condeso;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JTextField textField2;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JComboBox comboBox2;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4;
    private JTextField textField3;
    private JComboBox comboBox3;
    private JButton anadirButton;

    public void start() {
        JFrame frame = new JFrame();
        frame.setContentPane(new CondesoGUI().condeso);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
