package condesaGUI;

import DbModel.Condeso;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CondesosCRUDGUI {
    private static final int WIDTH = 1100;
    private static final int HEIGHT = 1000;
    public JFrame frame = new JFrame();
    private JTable tablaCondesos;
    private JPanel editCondeso;
    private JPanel listaDeCondesos;
    private JFormattedTextField nombreText;
    private JRadioButton masculinoButton;
    private JLabel sexoLabel;
    private JRadioButton femeninoButton;
    private JComboBox cargoBox;
    private JLabel cargoLabel;
    private JComboBox contratoBox;
    private JLabel nombreLabel;
    private JLabel contratoLabel;
    private JButton agregarButton;
    private JButton actualizarButton;
    private JButton borrarButton;
    private JLabel nivelLabel;
    private JComboBox nivelBox;
    private JRadioButton maturinoButton;
    private JLabel tipoLabel;
    private JRadioButton vespertinoButton;
    private JLabel cajaLabel;
    private JRadioButton siButton;
    private JRadioButton noButton;
    private JLabel contratacionLabel;
    private JLabel tipeOfDateLabel;
    private JTextField fechaText;
    private List<Condeso> condesos;
    private int columns;
    private int rows;

    public CondesosCRUDGUI(){
        fechaText.setFont(new Font("Arial", Font.PLAIN, 30));
        tipeOfDateLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        tablaCondesos.setFont(new Font("Arial", Font.PLAIN, 40));
        editCondeso.setFont(new Font("Arial", Font.PLAIN, 40));
        listaDeCondesos.setFont(new Font("Arial", Font.PLAIN, 40));
        nivelLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        cajaLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        contratacionLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        contratoLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        nombreLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        sexoLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        tipoLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        agregarButton.setFont(new Font("Arial", Font.PLAIN, 40));
        actualizarButton.setFont(new Font("Arial", Font.PLAIN, 40));
        borrarButton.setFont(new Font("Arial", Font.PLAIN, 40));
        cargoLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        siButton.setFont(new Font("Arial", Font.PLAIN, 30));
        noButton.setFont(new Font("Arial", Font.PLAIN, 30));
        vespertinoButton.setFont(new Font("Arial", Font.PLAIN, 30));
        maturinoButton.setFont(new Font("Arial", Font.PLAIN, 30));
        masculinoButton.setFont(new Font("Arial", Font.PLAIN, 30));
        femeninoButton.setFont(new Font("Arial", Font.PLAIN, 30));
        nombreText.setFont(new Font("Arial", Font.PLAIN, 30));
        contratoBox.setFont(new Font("Arial", Font.PLAIN, 30));
        cargoBox.setFont(new Font("Arial", Font.PLAIN, 30));
        nivelBox.setFont(new Font("Arial", Font.PLAIN, 30));

        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"Si",
                        "No"};
                int n = JOptionPane.showOptionDialog(frame,
                        "Quieres borrar este condeso ? \n"
                        + "Se borrará permanentemente ",
                        "Borrar Condeso",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.WARNING_MESSAGE,
                        null,
                        options,
                        options[1]);
                System.out.println(n);
                if(n == 0){
//Borrar
                }else{
//Sino nel
                }
            }
        });
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        siButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isSelectedCaja(true);
            }
        });
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isSelectedCaja(false);
            }
        });
    }

    public void isSelectedCaja(boolean caja){
        if(caja){
            noButton.setSelected(false);
        }else{
            siButton.setSelected(false);
        }
    }

    public void start(){
        //createTableContents();
        frame.setTitle("Información de Condesos");
        frame.setContentPane(new CondesosCRUDGUI().listaDeCondesos);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        //frame.setSize(CondesosCRUDGUI.WIDTH, CondesosCRUDGUI.HEIGHT);
        frame.setVisible(true);

    }

    private void createTableContents() {
        DefaultTableModel model = new DefaultTableModel();
        String[] columnNames = {"Nombre", "Antigüedad", "Tipo", "Nivel", "Fijos", "Caja",
            "Matutino", "Vespertino"};
        model.setColumnCount(8);
        model.setColumnIdentifiers(columnNames);
        String Nombre = "Nombre";
        model.addColumn(Nombre);
        List<Condeso> condesos = DbController.HibernateCrud.GetAllCondesos();
        for (Condeso c : condesos) {
            Object[] o = new Object[6];
            o[0] = c.getNombre();
            o[1] = c.getAntiguedad();
            o[2] = c.getTipo();
            o[3] = c.getLevel();
            o[4] = c.isFijos();
            o[5] = c.isCaja();
            model.addRow(o);
        }
        tablaCondesos.setModel(model);
       // tablaCondesos.setColumnModel();
    }
}

