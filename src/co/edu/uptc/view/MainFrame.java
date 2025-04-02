package co.edu.uptc.view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import java.util.ArrayList;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextField;

public class MainFrame extends JFrame{

    private JPanel loginPanel;
    private JPanel receptionistMenuPanel;
    private JPanel adminMenuPanel;

    public MainFrame(){
        super("Parking UPTC");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createLoginPanel();
        crateReceptionistMenuPanel();
        createVehicleEntryPanel(2);
        //JTextField textField = new JTextField();
        //textField.setText(regex);
        //JButton button = new JButton("ingresar[action]");
        //ClaseEsa.add(LoginPanel);

        setVisible(true);
    }

    private void createLoginPanel(){
        loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        GridBagConstraints config = new GridBagConstraints();

        config.gridx = 0;
        config.gridy = 0;
        config.gridwidth = 2;
        config.insets = new Insets(10, 10, 10, 10);
        config.anchor = GridBagConstraints.CENTER;
        loginPanel.add(new JLabel("Señor ingrese su NIT y contraseña para Ingresar al sistema"), config);

        config.gridx = 0;
        config.gridy = 1;
        config.gridwidth = 1;
        config.anchor = GridBagConstraints.LINE_END;
        loginPanel.add(new JLabel("NIT"), config);

        config.gridx = 1;
        config.gridy = 1;
        config.anchor = GridBagConstraints.LINE_START;
        loginPanel.add(new TextField(20), config);

        config.gridx = 0;
        config.gridy = 2;
        config.anchor = GridBagConstraints.LINE_END;
        loginPanel.add(new JLabel("Contraseña"), config);

        config.gridx = 1;
        config.gridy = 2;
        config.anchor = GridBagConstraints.LINE_START;
        loginPanel.add(new TextField(20), config);

        config.gridx = 0;
        config.gridy = 3;
        config.gridwidth = 2;
        config.anchor = GridBagConstraints.CENTER;
        loginPanel.add(new JButton("Ingresar"), config);

        getContentPane().add(loginPanel);
    }

    private void crateReceptionistMenuPanel(){
        receptionistMenuPanel = new JPanel();
        receptionistMenuPanel.setLayout(new GridBagLayout());
        GridBagConstraints config = new GridBagConstraints();

        JButton vehicleEntryButton = new JButton("Ingresó de  vehículo");
        JButton vehicleExitButton = new JButton("Salida de vehículo");
        JButton disponibleSpaces = new JButton("Espacios disponibles");
        JButton logOutButton = new JButton("Cerrar sesión");
        JPanel buttonBar = new JPanel();
        buttonBar.setLayout(new BoxLayout(buttonBar, BoxLayout.X_AXIS));
        buttonBar.add(vehicleEntryButton);
        buttonBar.add(vehicleExitButton);
        buttonBar.add(disponibleSpaces);
        buttonBar.add(logOutButton);

        config.gridx = 0;
        config.gridy = 0;
        config.gridwidth = 2;
        config.insets = new Insets(10, 10, 5, 10);
        config.anchor = GridBagConstraints.CENTER;
        receptionistMenuPanel.add(buttonBar, config);
        
        JPanel sectionPanel = new JPanel();
        sectionPanel.setLayout(new CardLayout());
        sectionPanel.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
        
        config.gridy = 1;
        config.weightx = 1;
        config.weighty = 1;
        config.fill = GridBagConstraints.BOTH;
        config.insets = new Insets(5, 10, 10, 10);
        receptionistMenuPanel.add(sectionPanel, config);


        getContentPane().add(receptionistMenuPanel);
    }

    private JPanel createVehicleEntryPanel(int disponibleSpaces){
        JPanel vehicleEntryPanel = new JPanel();
        vehicleEntryPanel.setLayout(new GridBagLayout());
        GridBagConstraints config = new GridBagConstraints();

        config.gridx = 0;
        config.gridy = 0;
        config.gridwidth = 2;
        config.insets = new Insets(10, 10, 5, 10);
        config.anchor = GridBagConstraints.CENTER;
        vehicleEntryPanel.add(new JLabel("Plazas disponibles: "+ disponibleSpaces));

        config.gridx = 0;
        config.gridy = 1;
        config.gridwidth = 1;
        config.insets = new Insets(10, 10, 5, 10);
        config.anchor = GridBagConstraints.CENTER;
        vehicleEntryPanel.add(new JLabel("Digite la placa del vehículo que ingresa: "));

        config.gridx = 1;
        config.gridy = 1;
        config.anchor = GridBagConstraints.CENTER;
        vehicleEntryPanel.add(new TextField(20), config);

        config.gridx = 0;
        config.gridy = 3;
        config.gridwidth = 2;
        config.anchor = GridBagConstraints.CENTER;
        vehicleEntryPanel.add(new JButton("Ingresar"), config);

        return vehicleEntryPanel;

    }

    private void createVehicleExit(){

    }

    private void createDisponibleSpaces(){

    }

    private void crateAdminMenuPanel(){
        adminMenuPanel = new JPanel();
        adminMenuPanel.setLayout(new GridBagLayout());
        GridBagConstraints config = new GridBagConstraints();

        JButton vehicleEntryButton = new JButton("Ingresó de  vehículo");
        JButton vehicleExitButton = new JButton("Salida de vehículo");
        JButton disponibleSpaces = new JButton("Espacios disponibles");
        JButton logOutButton = new JButton("Cerrar sesión");
        JPanel buttonBar = new JPanel();
        buttonBar.setLayout(new BoxLayout(buttonBar, BoxLayout.X_AXIS));
        buttonBar.add(vehicleEntryButton);
        buttonBar.add(vehicleExitButton);
        buttonBar.add(disponibleSpaces);
        buttonBar.add(logOutButton);

        config.gridx = 0;
        config.gridy = 0;
        config.gridwidth = 2;
        config.insets = new Insets(10, 10, 10, 10);
        config.anchor = GridBagConstraints.CENTER;
        adminMenuPanel.add(buttonBar, config);

        getContentPane().add(adminMenuPanel);
    }

    public ArrayList<String> getInputs() {
        return null;
    }

    public void showLoginPanel(){
        
    }

    public void showReceptionistMenu(){

    }

    public void showAdminMenu(){

    }
}
