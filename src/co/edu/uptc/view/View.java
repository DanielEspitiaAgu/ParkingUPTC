package co.edu.uptc.view;

import javax.smartcardio.Card;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import co.edu.uptc.presenter.Presenter;

import java.util.ArrayList;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener{

    private JPanel loginPanel;
    private JPanel receptionistMenuPanel;
    private JPanel adminMenuPanel;
    private JPanel sectionPanel;

    public View(){
        super("Parking UPTC");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new CardLayout());
        createLoginPanel();
        createSectionPanel();
        //JTextField textField = new JTextField();
        //textField.setText(regex);
        //JButton button = new JButton("ingresar[action]");
        //ClaseEsa.add(LoginPanel);

        setVisible(true);
    }

    private void createSectionPanel(){
        sectionPanel = new JPanel();
        sectionPanel.setLayout(new CardLayout());
        sectionPanel.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
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
        loginPanel.add(new JTextField(20), config);

        config.gridx = 0;
        config.gridy = 2;
        config.anchor = GridBagConstraints.LINE_END;
        loginPanel.add(new JLabel("Contraseña"), config);

        config.gridx = 1;
        config.gridy = 2;
        config.anchor = GridBagConstraints.LINE_START;
        loginPanel.add(new JPasswordField(20), config);

        config.gridx = 0;
        config.gridy = 3;
        config.gridwidth = 2;
        config.anchor = GridBagConstraints.CENTER;
        JButton button = new JButton("Ingresar");
        button.addActionListener(this);
        loginPanel.add(button, config);

        getContentPane().add(loginPanel, "Login Panel");
    }

    private void createReceptionistMenuPanel(){
        loginPanel.setVisible(false);
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
        
        config.gridy = 1;
        config.weightx = 1;
        config.weighty = 1;
        config.fill = GridBagConstraints.BOTH;
        config.insets = new Insets(5, 10, 10, 10);

        createInitReceptionistPanel("Daniel");
        createVehicleEntryPanel(213123);
        receptionistMenuPanel.add(sectionPanel, config);
        getContentPane().add(receptionistMenuPanel, "Receptionist Panel");
    }

    private void createInitReceptionistPanel(String name){
        JPanel initPanel = new JPanel();
        initPanel.setLayout(new GridBagLayout());
        GridBagConstraints config = new GridBagConstraints();

        config.gridx = 0;
        config.gridy = 0;
        config.gridwidth = 1;
        config.insets = new Insets(10, 10, 5, 10);
        config.anchor = GridBagConstraints.CENTER;
        initPanel.add(new JLabel("Recepcionista: "+name+", Bienvenido al sistema de ParkingUPTC!"), config);

        config.gridx = 0;
        config.gridy = 1;
        config.gridwidth = 1;
        config.insets = new Insets(10, 10, 5, 10);
        config.anchor = GridBagConstraints.CENTER;
        initPanel.add(new JLabel("Señor/a usuario, selecciona alguna de las opciones del menú"), config);

        sectionPanel.add(initPanel, "Init Panel");
    }

    private void createVehicleEntryPanel(int disponibleSpaces){
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

        sectionPanel.add(vehicleEntryPanel, "Vehicle Entry Panel");
    }

    private void createVehicleExitPanel(){

    }

    private void createDisponibleSpacesPanel(){

    }

    private void createAdminMenuPanel(){
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

        getContentPane().add(adminMenuPanel, "Admin Panel");
    }

    public ArrayList<String> getInputs() {
        return null;
    }

    public void showLoginPanel(){
        
    }

    public void showReceptionistMenu(){
        ((CardLayout)(getContentPane().getLayout())).show(getContentPane(), "Receptionist Panel");
    }

    public void showAdminMenu(){

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Component component: loginPanel.getComponents()) {
            if (component instanceof JButton) {
                if (((JButton)(e.getSource())).getText()=="Ingresar") {
                    createReceptionistMenuPanel();
                    createAdminMenuPanel();
                    showReceptionistMenu();
                }else{
                    continue;
                }
            }
        }
        for (Component component : receptionistMenuPanel.getComponents()) {
            if (component instanceof JPanel) {
                for (Component component2 : ((JPanel)(component)).getComponents()) {
                    if (component2 instanceof JButton) {
                        if (((JButton)(e.getSource())).getText()=="Ingresó de vehículo") {
                            createVehicleEntryPanel(213123);
                            ((CardLayout)(sectionPanel).getLayout()).show(sectionPanel, "Vehicle Entry Panel");
                        }
                        if (((JButton)(e.getSource())).getText()=="") {
                    
                        }
                        if (((JButton)(e.getSource())).getText()=="") {
                    
                        }
                        if (((JButton)(e.getSource())).getText()=="") {
                    
                        }
                        if (((JButton)(e.getSource())).getText()=="") {
                    
                        }
                        if (((JButton)(e.getSource())).getText()=="") {
                    
                        }
                    }
                }
            }
        }
        for (Component component : adminMenuPanel.getComponents()) {
            if (component instanceof JPanel) {
                for (Component component2 : ((JPanel)(component)).getComponents()) {
                    if (component2 instanceof JButton) {
                        if (((JButton)(e.getSource())).getText()=="") {
                            
                        }
                        if (((JButton)(e.getSource())).getText()=="") {
                    
                        }
                        if (((JButton)(e.getSource())).getText()=="") {
                    
                        }
                        if (((JButton)(e.getSource())).getText()=="") {
                    
                        }
                        if (((JButton)(e.getSource())).getText()=="") {
                    
                        }
                    }
                }
            }
        }
    }
}
