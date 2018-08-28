package condesaGUI;

import javax.swing.*;

public class plantillasGUI {
    public JFrame frame = new JFrame();
    private JPanel plantillasNuevasTable;
    private JPanel plantillasAnteriores;
    private JPanel main;
    private JComboBox nombresBox;
    private JTable plantillasViejasTable;
    private JButton borrarButton;
    private JLabel nombreLabel;
    private JComboBox diaBob;
    private JLabel diaLabel;
    private JTable diaTable;
    private JFormattedTextField formattedTextField1;
    private JTable plantillaNuevaTable;
    private JButton agregarButton;
    private JFormattedTextField HHFormattedTextField;
    private JButton agregarPlantillaButton;

    public plantillasGUI(){
        //diaTable.setColumnModel();
        //diaTable.setModel();
    }
    public void start(){
        frame.setTitle("Plantillas");
        frame.setContentPane(new plantillasGUI().main);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        //frame.setSize(CondesosCRUDGUI.WIDTH, CondesosCRUDGUI.HEIGHT);
        frame.setVisible(true);
    }
}
