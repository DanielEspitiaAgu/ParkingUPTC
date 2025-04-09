package co.edu.uptc.view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
import java.util.Date;
import co.edu.uptc.presenter.Presenter;
import netscape.javascript.JSException;

import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class View extends JFrame{

    private JPanel loginPanel;
    private JPanel receptionistMenuPanel;
    private JPanel adminMenuPanel;
    private JPanel receptionistSectionPanel;
    private JPanel adminSectionPanel;
    private JPanel reportPanel;
    private JOptionPane jOptionPane;
    private JLabel disponibleSpacesLabel;
    private JLabel disponibleSpacesLabel2;
    private Color charcoalBlack;
    private Color goldenYellow;
    private Color lightCream;

    public View(){
        super("Parking UPTC");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new CardLayout());
        jOptionPane = new JOptionPane();
        disponibleSpacesLabel = new JLabel();
        disponibleSpacesLabel2 = new JLabel();
        charcoalBlack = new Color(16, 16, 16);
        goldenYellow = new Color(255, 208, 55);
        lightCream = new Color(250, 245, 223);
        createLoginPanel();
        createAdminMenuPanel();
        createReceptionistMenuPanel();
        setVisible(true);
    }

    private void createReceptionistSectionPanel(){
        receptionistSectionPanel = new JPanel();
        receptionistSectionPanel.setLayout(new CardLayout());
        receptionistSectionPanel.setBorder(new MatteBorder(2, 2, 2, 2, charcoalBlack));
    }

    private void createAdminSectionPanel(){
        adminSectionPanel = new JPanel();
        adminSectionPanel.setLayout(new CardLayout());
        adminSectionPanel.setBackground(lightCream);
        adminSectionPanel.setBorder(new MatteBorder(2, 2, 2, 2, charcoalBlack));
        reportPanel = new JPanel();
        adminSectionPanel.add(reportPanel, "Report Panel");
    }

    private void createLoginPanel(){
        loginPanel = new JPanel();
        loginPanel.setBackground(lightCream);
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
        JTextField idField = new JTextField(20);
        idField.setText("[0-9]+");
        loginPanel.add(idField, config);

        config.gridx = 0;
        config.gridy = 2;
        config.anchor = GridBagConstraints.LINE_END;
        loginPanel.add(new JLabel("Contraseña"), config);

        config.gridx = 1;
        config.gridy = 2;
        config.anchor = GridBagConstraints.LINE_START;
        JPasswordField passwordField = new JPasswordField(20);
        loginPanel.add(passwordField, config);

        config.gridx = 0;
        config.gridy = 3;
        config.gridwidth = 2;
        config.anchor = GridBagConstraints.CENTER;
        JButton button = new JButton("Ingresar");
        ButtonSummitControl loginSummitControl = new ButtonSummitControl(button, loginPanel);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Presenter.getInstance().login(idField.getText(), passwordField.getText());
               idField.setText("");
               passwordField.setText("");
            }
        });
        loginPanel.add(button, config);

        getContentPane().add(loginPanel, "Login Panel");
    }

    private void createReceptionistMenuPanel(){
        loginPanel.setVisible(false);
        receptionistMenuPanel = new JPanel();
        receptionistMenuPanel.setBackground(lightCream);
        receptionistMenuPanel.setLayout(new GridBagLayout());
        GridBagConstraints config = new GridBagConstraints();

        JButton vehicleEntryButton = new JButton("Ingreso de vehiculo");
        vehicleEntryButton.setBackground(goldenYellow);
        vehicleEntryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)(receptionistSectionPanel).getLayout()).show(receptionistSectionPanel, "Vehicle Entry Panel");
                updatedisponibleSpacesLabel();
            }
            
        });
        JButton vehicleExitButton = new JButton("Salida de vehículo");
        vehicleExitButton.setBackground(goldenYellow);
        vehicleExitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)(receptionistSectionPanel).getLayout()).show(receptionistSectionPanel, "Vehicle Exit Panel");
            }
        });
        JButton disponibleSpaces = new JButton("Espacios disponibles");
        disponibleSpaces.setBackground(goldenYellow);
        disponibleSpaces.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {   
                ((CardLayout)(receptionistSectionPanel).getLayout()).show(receptionistSectionPanel, "Disponible Spaces Panel");
                updatedisponibleSpacesLabel();
            }
        });
        JButton logOutButton = new JButton("Cerrar sesión");
        logOutButton.setBackground(goldenYellow);
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createCurrentReceptionistReportPanel(Presenter.getInstance().generateCurrentReceptionistReport());
                ((CardLayout)(receptionistSectionPanel).getLayout()).show(receptionistSectionPanel, "Current Receptionist Report Panel");
            }
        });
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

        createReceptionistSectionPanel();
        createVehicleEntryPanel();
        createVehicleExitPanel();
        createDisponibleSpacesPanel();
        receptionistMenuPanel.add(receptionistSectionPanel, config);
        getContentPane().add(receptionistMenuPanel, "Receptionist Panel");
    }

    private void createVehicleEntryPanel(){
        JPanel vehicleEntryPanel = new JPanel();
        vehicleEntryPanel.setLayout(new GridBagLayout());
        GridBagConstraints config = new GridBagConstraints();

        config.gridx = 0;
        config.gridy = 0;
        config.insets = new Insets(10, 10, 10, 10);
        config.anchor = GridBagConstraints.CENTER;
        vehicleEntryPanel.add(disponibleSpacesLabel, config);

        config.gridy = 1;
        config.anchor = GridBagConstraints.CENTER;
        vehicleEntryPanel.add(new JLabel("Digite la placa del vehículo que ingresa: "), config);

        config.gridx = 0;
        config.gridy = 2;
        config.anchor = GridBagConstraints.CENTER;
        JTextField vehicleEntry = new JTextField(20);
        vehicleEntry.setText("[A-Z]{3}(\\d{3}|\\d{2}[A-Z])");
        vehicleEntryPanel.add(vehicleEntry, config);

        config.gridy = 3;
        config.gridwidth = 2;
        config.anchor = GridBagConstraints.CENTER;
        JButton generateTicketButton = new JButton("Generar ticket");
        ButtonSummitControl loginSummitControl = new ButtonSummitControl(generateTicketButton, vehicleEntryPanel);
        generateTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Presenter.getInstance().generateEntryTicket(vehicleEntry.getText());
                vehicleEntry.setText("");
            }
        });
        vehicleEntryPanel.add(generateTicketButton, config);

        receptionistSectionPanel.add(vehicleEntryPanel, "Vehicle Entry Panel");
    }

    private void createGenerateEntryTicketPanel(ArrayList<String> ticketInformation) {
        JPanel generateEntryTicketPanel = new JPanel();
        generateEntryTicketPanel.setLayout(new GridBagLayout());
        GridBagConstraints config = new GridBagConstraints();

        config.gridx = 0;
        config.gridy = 0;
        config.gridwidth = 2;
        config.insets = new Insets(10, 10, 10, 10);
        config.anchor = GridBagConstraints.CENTER;
        generateEntryTicketPanel.add(new JLabel("Se ha registrado el vehículo con éxito"), config);

        JPanel entryTicketPanel = new JPanel();
        entryTicketPanel.setLayout(new GridBagLayout());
        entryTicketPanel.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));

        config.gridx = 0;
        config.gridy = 0;
        config.gridwidth = 1;
        config.insets = new Insets(10, 10, 10, 10);
        config.anchor = GridBagConstraints.CENTER;
        entryTicketPanel.add(new JLabel(""+ticketInformation.get(0)), config);

        entryTicketPanel.setLayout(new GridBagLayout());
        config.gridy = 1;
        config.anchor = GridBagConstraints.CENTER;
        entryTicketPanel.add(new JLabel("Fecha: "+ ticketInformation.get(1)), config);

        config.gridy = 2;
        config.anchor = GridBagConstraints.CENTER;
        entryTicketPanel.add(new JLabel("Vehículo: "+ ticketInformation.get(2)), config);

        config.gridy = 3;
        config.anchor = GridBagConstraints.CENTER;
        entryTicketPanel.add(new JLabel("Hora: "+ ticketInformation.get(3)), config);
        
        config.gridy = 1;
        config.gridwidth = 2;
        generateEntryTicketPanel.add(entryTicketPanel, config);

        config.gridy = 2;
        config.anchor = GridBagConstraints.CENTER;
        config.gridwidth = 1;
        JButton printTicketButton = new JButton("Imprimir ticket");
        printTicketButton.setBackground(goldenYellow);
        printTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSimpleMessage("Impresora", "Se ha impreso el ticket.");
            }
        });
        generateEntryTicketPanel.add(printTicketButton, config);

        config.gridx = 1;
        config.anchor = GridBagConstraints.CENTER;
        JButton anotherVehicleButton= new JButton("Ingresar otro vehículo");
        anotherVehicleButton.setBackground(goldenYellow);
        anotherVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatedisponibleSpacesLabel();
                ((CardLayout)(receptionistSectionPanel).getLayout()).show(receptionistSectionPanel, "Vehicle Entry Panel");
            }
        });
        generateEntryTicketPanel.add(anotherVehicleButton, config);
        receptionistSectionPanel.add(generateEntryTicketPanel, "Generate Entry Ticket Panel");
    }

    private void createVehicleExitPanel() {
        JPanel exitVehiclePanel = new JPanel();
        exitVehiclePanel.setLayout(new GridBagLayout());
        GridBagConstraints config = new GridBagConstraints();
        config.insets = new Insets(10, 10, 10, 10);
        config.anchor = GridBagConstraints.CENTER;
    
        config.gridx = 0;
        config.gridy = 0;
        config.gridwidth = 1;
        exitVehiclePanel.add(new JLabel("Digite la placa del vehículo que desea salir: "), config);
    
        config.gridy = 1;
        JTextField plateField = new JTextField(20);
        plateField.setText("[A-Z]{3}(\\d{3}|\\d{2}[A-Z])");
        exitVehiclePanel.add(plateField, config);
    
        config.gridy = 2;
        JButton consultButton = new JButton("Consultar");
        ButtonSummitControl consultSummitControl = new ButtonSummitControl(consultButton, exitVehiclePanel);
        consultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double cost = Presenter.getInstance().vehicleExitCost(plateField.getText());
                if(cost==-1){
                    showErrorMessage("Error", "No existe un vehículo dentro del parqueadero con ese número de placa actualmente.");
                }else{
                    createConsultResultTicketPanel(plateField.getText(), cost);
                    ((CardLayout)(receptionistSectionPanel.getLayout())).show(receptionistSectionPanel, "Consult Result Ticket Panel");
                    plateField.setText("");
                }
            }
        });
        exitVehiclePanel.add(consultButton, config);
    
        receptionistSectionPanel.add(exitVehiclePanel, "Vehicle Exit Panel");
    }

    private void createConsultResultTicketPanel(String plate, double cost) {
        JPanel consultResultTicketPanel = new JPanel();
        consultResultTicketPanel.setLayout(new GridBagLayout());
        GridBagConstraints config = new GridBagConstraints();
        config.insets = new Insets(10, 10, 10, 10);
        config.anchor = GridBagConstraints.CENTER;
    
        config.gridx = 0;
        config.gridy = 0;
        config.gridwidth = 2;
        consultResultTicketPanel.add(new JLabel("El valor a pagar por el vehículo " + plate + " es: $" + cost), config);
    
        config.gridy = 1;
        config.gridwidth = 1;
        JTextField receivedAmountField = new JTextField(10);
        receivedAmountField.setText("[0-9]+");
        consultResultTicketPanel.add(new JLabel("Valor recibido"), config);
        
        config.gridx = 1;
        consultResultTicketPanel.add(receivedAmountField, config);
    
        config.gridx = 0;
        config.gridy = 2;
        config.gridwidth = 2;
        consultResultTicketPanel.add(new JLabel("El método de pago solo debe ser en efectivo"), config);
    
        config.gridy = 3;
        config.gridwidth = 1;
        JButton correctPlateButton = new JButton("Corregir Placa");
        correctPlateButton.setBackground(goldenYellow);
        correctPlateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)(receptionistSectionPanel).getLayout()).show(receptionistSectionPanel, "Vehicle Exit Panel");
            }
        });
        consultResultTicketPanel.add(correctPlateButton, config);

        config.gridx = 1;
        JButton calculateAmountButton = new JButton("Calcular monto");
        ButtonSummitControl calculateAmountSummitControl = new ButtonSummitControl(calculateAmountButton, consultResultTicketPanel);
        calculateAmountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Presenter.getInstance().consolidateVehicleExit(plate, Double.parseDouble(receivedAmountField.getText()))){
                    createGenerateExitTicketPanel(plate, cost, Double.parseDouble(receivedAmountField.getText()));
                    ((CardLayout)(receptionistSectionPanel).getLayout()).show(receptionistSectionPanel, "Generate Exit Ticket Panel");
                }else{
                    showErrorMessage("Error", "Comprobar que el monto ingresado no es suficiente.");
                }
            }
        });
        
        consultResultTicketPanel.add(calculateAmountButton, config);
        receptionistSectionPanel.add(consultResultTicketPanel, "Consult Result Ticket Panel");
    }

    private void createGenerateExitTicketPanel(String plate, double cost, double receivedAmount) {
        JPanel generateExitTicketPanel = new JPanel();
        generateExitTicketPanel.setLayout(new GridBagLayout());
        GridBagConstraints config = new GridBagConstraints();
        config.insets = new Insets(10, 10, 10, 10);
        config.anchor = GridBagConstraints.CENTER;
    
        config.gridx = 0;
        config.gridy = 0;
        config.gridwidth = 2;
        generateExitTicketPanel.add(new JLabel("El valor a pagar por el vehículo " + plate + " es: $" + cost), config);
    
        config.gridy = 1;
        config.gridwidth = 1;
        generateExitTicketPanel.add(new JLabel("Valor recibido: $"+receivedAmount), config);
    
        config.gridx = 0;
        config.gridy = 2;
        config.gridwidth = 2;
        generateExitTicketPanel.add(new JLabel("Cambio: $"+Presenter.getInstance().getTicketChange(plate)), config); 

        config.gridy = 3;
        config.gridwidth = 1;
        JButton anotherVehicleButton = new JButton("Salida de otro Vehículo");
        anotherVehicleButton.setBackground(goldenYellow);
        anotherVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)(receptionistSectionPanel).getLayout()).show(receptionistSectionPanel, "Vehicle Exit Panel");
            }
        });
        generateExitTicketPanel.add(anotherVehicleButton, config);
    
        config.gridx = 1;
        JButton generateReceiptButton = new JButton("Generar Recibo");
        generateReceiptButton.setBackground(goldenYellow);
        generateReceiptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createExitTicketPanel(Presenter.getInstance().generateExitTicket(plate));
                ((CardLayout)(receptionistSectionPanel).getLayout()).show(receptionistSectionPanel, "Exit Ticket Receipt Panel");
            }
        });
        generateExitTicketPanel.add(generateReceiptButton, config);
    
        receptionistSectionPanel.add(generateExitTicketPanel, "Generate Exit Ticket Panel");
    }

    private void createExitTicketPanel(ArrayList<String> ticketInformation) {
        JPanel exitTicketReceiptPanel = new JPanel();
        exitTicketReceiptPanel.setLayout(new GridBagLayout());
        exitTicketReceiptPanel.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
        GridBagConstraints config = new GridBagConstraints();
        config.insets = new Insets(10, 10, 10, 10);
        config.fill = GridBagConstraints.VERTICAL;
    
        JPanel receiptPanel = new JPanel();
        receiptPanel.setLayout(new GridBagLayout());
        receiptPanel.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
        config.insets = new Insets(5, 5, 5, 5);
        
        config.gridx = 0;
        config.gridy = 0;
        receiptPanel.add(new JLabel(ticketInformation.get(0)), config);
        
        config.gridy = 1;
        receiptPanel.add(new JLabel("Vehículo: " + ticketInformation.get(1) + "      Fecha: " + ticketInformation.get(2)), config);

        config.gridy = 2;
        receiptPanel.add(new JLabel("Hora de entrada: " + ticketInformation.get(3) + "      Hora de salida: " + ticketInformation.get(4)), config);

        config.gridy = 3;
        receiptPanel.add(new JLabel("Costo: $" + ticketInformation.get(5) + "      Cambio: $" + ticketInformation.get(6)), config);

        config.gridy = 4;
        receiptPanel.add(new JLabel("Valor recibido: $" + ticketInformation.get(7)), config);

        config.gridy = 5;
        receiptPanel.add(new JLabel("¡Tenga un buen viaje!"), config);
        
        config.gridy = 0;
        exitTicketReceiptPanel.add(receiptPanel, config);
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridBagLayout());
        config.gridy = 0;
        config.gridwidth = 1;
        
        config.gridx =  0;
        JButton printReceiptButton = new JButton("Imprimir Recibo");
        printReceiptButton.setBackground(goldenYellow);
        buttonsPanel.add(printReceiptButton, config);
        printReceiptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSimpleMessage("Impresora", "Se ha impreso el recibo.");
            }
        });
        
        config.gridx =  1;
        JButton anotherVehicleButton = new JButton("Salida de otro Vehículo");
        anotherVehicleButton.setBackground(goldenYellow);
        buttonsPanel.add(anotherVehicleButton, config);
        anotherVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)(receptionistSectionPanel.getLayout())).show(receptionistSectionPanel, "Vehicle Exit Panel");
            }
        });

        config.gridx =  0;
        config.gridy = 1;
        exitTicketReceiptPanel.add(buttonsPanel, config);
        
        receptionistSectionPanel.add(exitTicketReceiptPanel, "Exit Ticket Receipt Panel");
    }

    private void createDisponibleSpacesPanel(){
        JPanel disponibleSpacesPanel = new JPanel();
        disponibleSpacesPanel.setLayout(new GridBagLayout());
        GridBagConstraints config = new GridBagConstraints();
        config.insets = new Insets(5, 5, 5, 5);

        config.gridx = 0;
        config.gridy = 0;
        config.gridwidth = 2;
        disponibleSpacesPanel.add(disponibleSpacesLabel2, config);

        receptionistSectionPanel.add(disponibleSpacesPanel, "Disponible Spaces Panel");
    }

    private void createCurrentReceptionistReportPanel(ArrayList<String> reportInformation){
        JPanel currentReceptionistReportPanel = new JPanel();
        currentReceptionistReportPanel.setLayout(new GridBagLayout());
        GridBagConstraints config = new GridBagConstraints();
        config.insets = new Insets(5, 5, 5, 5);
        
        config.gridx = 0;
        config.gridy = 0;
        config.anchor = GridBagConstraints.CENTER;
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Fecha", reportInformation.get(0)}, 0);
        JTable table = new JTable(tableModel);
        table.setEnabled(false);
        table.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 100));
        String[] rowName = new String[]{"Hora de inicio","Hora de salida","Tiempo de actividad","Num. Vehículos ingresados","Dinero recaudado"};
        currentReceptionistReportPanel.add(scrollPane,config);

        for (int i = 0; i < rowName.length && (i+1)<reportInformation.size(); i++) {
            tableModel.addRow(new String[]{rowName[i],reportInformation.get(i+1)});        
        }  

        config.gridy = 1;
        JButton logoutButton = new JButton("Cerrar sesión");
        logoutButton.setBackground(goldenYellow);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)(getContentPane()).getLayout()).show(getContentPane(), "Login Panel");  
            }
            
        });
        currentReceptionistReportPanel.add(logoutButton,config);
        receptionistSectionPanel.add(currentReceptionistReportPanel, "Current Receptionist Report Panel");
    }

    private void createAdminMenuPanel(){
        adminMenuPanel = new JPanel();
        adminMenuPanel.setBackground(lightCream);
        adminMenuPanel.setLayout(new GridBagLayout());
        GridBagConstraints config = new GridBagConstraints();

        JButton registParkingButton = new JButton("Registrar parqueadero");
        registParkingButton.setBackground(goldenYellow);
        registParkingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)(adminSectionPanel).getLayout()).show(adminSectionPanel, "Register Parking Panel");
            }
        });
        JButton createReceptionistButton = new JButton("Crear recepcionista");
        createReceptionistButton.setBackground(goldenYellow);
        createReceptionistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)(adminSectionPanel).getLayout()).show(adminSectionPanel, "Register Receptionist Panel");
            }
        });
        JButton modifyReceptionistButton = new JButton("Modificar recepcionista");
        modifyReceptionistButton.setBackground(goldenYellow);
        modifyReceptionistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)(adminSectionPanel).getLayout()).show(adminSectionPanel, "Edit Receptionist Panel");
                
            }
        });
        JButton generateSalesReportButton = new JButton("Generar reporte de ventas");
        generateSalesReportButton.setBackground(goldenYellow);
        generateSalesReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)(adminSectionPanel).getLayout()).show(adminSectionPanel, "Select Date Panel");
            }
        });
        JButton logOutButton = new JButton("Cerrar sesión");
        logOutButton.setBackground(goldenYellow);
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JOptionPane.showConfirmDialog(getContentPane(), "¿Estás seguro de que deseas cerrar sesión?")==JOptionPane.YES_OPTION){
                    Presenter.getInstance().logout();
                    ((CardLayout)(getContentPane()).getLayout()).show(getContentPane(), "Login Panel");  
                }
            }
        });
        JPanel buttonBar = new JPanel();
        buttonBar.setLayout(new BoxLayout(buttonBar, BoxLayout.X_AXIS));
        buttonBar.add(registParkingButton);
        buttonBar.add(createReceptionistButton);
        buttonBar.add(modifyReceptionistButton);
        buttonBar.add(generateSalesReportButton);
        buttonBar.add(logOutButton);

        config.gridx = 0;
        config.gridy = 0;
        config.gridwidth = 2;
        config.insets = new Insets(10, 10, 10, 10);
        config.anchor = GridBagConstraints.CENTER;
        adminMenuPanel.add(buttonBar, config);

        config.gridy = 1;
        config.weightx = 1;
        config.weighty = 1;
        config.fill = GridBagConstraints.BOTH;
        config.insets = new Insets(5, 10, 10, 10);

        createAdminSectionPanel();
        createRegisterParkingPanel();
        createRegisterRecepcionistPanel();
        createEditReceptionistPanel();
        createSelectDatePanel();
        adminMenuPanel.add(adminSectionPanel, config);

        getContentPane().add(adminMenuPanel, "Admin Panel");
    }

    private void updateInitPanel(String greeting, JPanel sectionPanel){
        JPanel initPanel = new JPanel();
        initPanel.setLayout(new GridBagLayout());
        GridBagConstraints config = new GridBagConstraints();

        config.gridx = 0;
        config.gridy = 0;
        config.gridwidth = 1;
        config.insets = new Insets(10, 10, 5, 10);
        config.anchor = GridBagConstraints.CENTER;
        initPanel.add(new JLabel(greeting+", Bienvenido al sistema de ParkingUPTC!"), config);

        config.gridx = 0;
        config.gridy = 1;
        config.gridwidth = 1;
        config.insets = new Insets(10, 10, 5, 10);
        config.anchor = GridBagConstraints.CENTER;
        initPanel.add(new JLabel("Señor/a usuario, selecciona alguna de las opciones del menú"), config);

        sectionPanel.add(initPanel, "Init Panel");
    }

    public void createRegisterParkingPanel() {
        JPanel registerParkingPanel = new JPanel();
        registerParkingPanel.setLayout(new GridBagLayout());
        GridBagConstraints config = new GridBagConstraints();
        config.insets = new Insets(5, 5, 5, 5);
        config.fill = GridBagConstraints.HORIZONTAL;
    
        JLabel parkingNameLabel = new JLabel("Nombre de parqueadero:");
        JTextField parkingNameField = new JTextField(20);
        config.gridx = 0;
        config.gridy = 0;
        config.anchor = GridBagConstraints.LINE_END;
        registerParkingPanel.add(parkingNameLabel, config);

        config.gridx = 1;
        config.anchor = GridBagConstraints.LINE_START;
        registerParkingPanel.add(parkingNameField, config);

        JLabel addressLabel = new JLabel("Dirección:");
        JTextField addressField = new JTextField(20);
        config.gridx = 0;
        config.gridy = 1;
        config.anchor = GridBagConstraints.LINE_END;
        registerParkingPanel.add(addressLabel, config);

        config.gridx = 1;
        config.anchor = GridBagConstraints.LINE_START;
        registerParkingPanel.add(addressField, config);

        JLabel spacesLabel = new JLabel("Número de espacios:");
        JTextField spacesField = new JTextField(20);
        spacesField.setText("[1-9][0-9]*");
        config.gridx = 0;
        config.gridy = 2;
        config.anchor = GridBagConstraints.LINE_END;
        registerParkingPanel.add(spacesLabel, config);

        config.gridx = 1;
        config.anchor = GridBagConstraints.LINE_START;
        registerParkingPanel.add(spacesField, config);

        config.gridy = 3;
        config.gridx = 0;
        config.anchor = GridBagConstraints.LINE_END;
        registerParkingPanel.add(new JLabel("Día"), config);
        
        String[] weekDays = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo", "Festivo"};
        JComboBox<String> dayComboBox = new JComboBox<>(weekDays);
        config.gridx = 1;
        config.anchor = GridBagConstraints.LINE_START;
        registerParkingPanel.add(dayComboBox, config);

        ArrayList<Component> restrictComponents = new ArrayList<Component>();

        config.gridy++;
        config.gridx = 0;
        registerParkingPanel.add(new JLabel("Hora de apertura"), config);
        JTextField openingHour = new JTextField(10);
        openingHour.setText("(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]");
        config.gridx = 1;
        registerParkingPanel.add(openingHour, config);
        restrictComponents.add(openingHour);
        
        config.gridy++;
        config.gridx = 0;
        registerParkingPanel.add(new JLabel("Hora de cierre"), config);
        JTextField closingHour = new JTextField(10);
        closingHour.setText("(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]");
        config.gridx = 1;
        registerParkingPanel.add(closingHour, config);
        restrictComponents.add(closingHour);

        config.fill = GridBagConstraints.HORIZONTAL;
        config.gridy = 7;
        config.gridx = 0;
        config.gridwidth = 2;
        config.anchor = GridBagConstraints.CENTER;
        
        String[] columnNames = {"Día", "Fecha de apertura", "Fecha de cierre"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        table.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 100));
        registerParkingPanel.add(scrollPane, config);
        
        JPanel buttonsJPanel = new JPanel(new GridBagLayout());

        config.gridwidth = 1;
        config.gridx=0;
        config.gridy = 1;
        config.fill = GridBagConstraints.VERTICAL;
        config.anchor = GridBagConstraints.CENTER;
        JButton saveConfigButton = new JButton("Guardar configuración");
        ButtonSummitControl saveConfigSummitControl = new ButtonSummitControl(saveConfigButton, restrictComponents);
        saveConfigButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isValid = true;
                for(int i = 0; i < tableModel.getRowCount(); i++){
                    if (tableModel.getValueAt(i, 0).equals((String)dayComboBox.getSelectedItem())) 
                    isValid = false;
                }
                if(isValid){
                    tableModel.addRow(new String[]{(String)dayComboBox.getSelectedItem(), openingHour.getText(), closingHour.getText()});  
                }
            }
        });
        buttonsJPanel.add(saveConfigButton, config);
        
        config.gridx=1;
        JButton clearTableButton = new JButton("Limpiar tabla");
        clearTableButton.setBackground(goldenYellow);
        clearTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.setRowCount(0);
            }
        });
        buttonsJPanel.add(clearTableButton, config);
        
        config.gridx = 0;
        config.gridy = 0;
        config.gridwidth = 2;
        JLabel label = new JLabel("<html><font color='#00298B'>Las horas se deben ingresar como 09:00 o 10:00</font></html>");	 
        buttonsJPanel.add(label, config);
        
        config.gridy = 6;
        config.gridx = 0;
        config.gridwidth = 2; 
        config.insets = new Insets(0, 0, 0, 0);
        registerParkingPanel.add(buttonsJPanel,config);
        
        config.insets = new Insets(5, 5, 5, 5);
        config.gridx=0;
        config.gridwidth=2;
        config.fill = GridBagConstraints.CENTER;
        config.anchor = GridBagConstraints.CENTER;
        config.gridy = 8;
        JButton inputButton = new JButton("Ingresar");
        ButtonSummitControl inputSummitControl = new ButtonSummitControl(inputButton, registerParkingPanel);
        inputButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> scheduleList = new ArrayList<String>();
                for(int i = 0; i < tableModel.getRowCount(); i++){
                    scheduleList.add(tableModel.getValueAt(i, 0).toString()+"-"+tableModel.getValueAt(i, 1).toString()+"-"+tableModel.getValueAt(i, 2).toString());
                }
                if (tableModel.getRowCount()>=1) {
                    if(Presenter.getInstance().registerParking(parkingNameField.getText(), addressField.getText(), Integer.parseInt(spacesField.getText()), scheduleList)){
                        showSimpleMessage("Parqueadero registrado", "El parqueadero ha sido registrado con éxito.\n "+Presenter.getInstance().getParkingInfo());
                        tableModel.setRowCount(0);
                        parkingNameField.setText("");
                        addressField.setText("");    
                        spacesField.setText("");
                        openingHour.setText("");
                        closingHour.setText("");
                    }else{
                        showErrorMessage("Error", "No se pudo registrar el parqueadero. Verifique si ya existe un parqueadero.");
                    }
                }else{
                    showErrorMessage("Error", "Debe ingresar al menos 1 días de apertura y cierre.");
                }
            }
        });
        registerParkingPanel.add(inputButton, config);
    
        adminSectionPanel.add(registerParkingPanel, "Register Parking Panel");
    }

    public void createRegisterRecepcionistPanel() {
        JPanel registerReceptionistPanel = new JPanel();
        registerReceptionistPanel.setLayout(new GridBagLayout());
        GridBagConstraints config = new GridBagConstraints();
        config.insets = new Insets(5, 5, 5, 5);
        config.fill = GridBagConstraints.HORIZONTAL;

        config.gridx = 0;
        config.gridy = 0;
        config.anchor = GridBagConstraints.LINE_END;
        JLabel documentLabel = new JLabel("Documento de identidad:");
        registerReceptionistPanel.add(documentLabel, config);
        
        JTextField documentField = new JTextField(20);
        documentField.setText("[0-9]+");
        config.gridx = 1;
        config.anchor = GridBagConstraints.LINE_START;
        registerReceptionistPanel.add(documentField, config);

        config.gridx = 0;
        config.gridy = 1;
        config.anchor = GridBagConstraints.LINE_END;
        JLabel firstNameLabel = new JLabel("Nombres:");
        registerReceptionistPanel.add(firstNameLabel, config);
        
        JTextField firstNameField = new JTextField(20);
        firstNameField.setText("[a-zA-Z]+");
        config.gridx = 1;
        config.anchor = GridBagConstraints.LINE_START;
        registerReceptionistPanel.add(firstNameField, config);

        config.gridx = 0;
        config.gridy = 2;
        config.anchor = GridBagConstraints.LINE_END;
        JLabel lastNameLabel = new JLabel("Apellidos:");
        registerReceptionistPanel.add(lastNameLabel, config);
        
        JTextField lastNameField = new JTextField(20);
        lastNameField.setText("[a-zA-Z]+");
        config.gridx = 1;
        config.anchor = GridBagConstraints.LINE_START;
        registerReceptionistPanel.add(lastNameField, config);

        config.gridx = 0;
        config.gridy = 3;
        config.anchor = GridBagConstraints.LINE_END;
        JLabel phoneLabel = new JLabel("Número de teléfono:");
        registerReceptionistPanel.add(phoneLabel, config);
        
        JTextField phoneField = new JTextField(20);
        phoneField.setText("[0-9]+");
        config.gridx = 1;
        config.anchor = GridBagConstraints.LINE_START;
        registerReceptionistPanel.add(phoneField, config);

        config.gridx = 0;
        config.gridy = 4;
        config.anchor = GridBagConstraints.LINE_END;
        JLabel addressLabel = new JLabel("Dirección:");
        registerReceptionistPanel.add(addressLabel, config);
        
        JTextField addressField = new JTextField(20);
        config.gridx = 1;
        config.anchor = GridBagConstraints.LINE_START;
        registerReceptionistPanel.add(addressField, config);

        config.gridx = 0;
        config.gridy = 5;
        config.anchor = GridBagConstraints.LINE_END;
        JLabel emailLabel = new JLabel("E-Mail:");
        registerReceptionistPanel.add(emailLabel, config);
        
        JTextField emailField = new JTextField(20);
        emailField.setText("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        config.gridx = 1;
        config.anchor = GridBagConstraints.LINE_START;
        registerReceptionistPanel.add(emailField, config);
    
        config.gridx = 0;
        config.gridy = 6;
        config.gridwidth = 2;
        config.anchor = GridBagConstraints.CENTER;
        config.fill = GridBagConstraints.CENTER;

        JButton inputButton = new JButton("Ingresar");
        ButtonSummitControl inputSummitControl = new ButtonSummitControl(inputButton, registerReceptionistPanel);
        inputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Presenter.getInstance().registerReceptionist(documentField.getText(), emailField.getText(), firstNameField.getText(), lastNameField.getText(), phoneField.getText(), addressField.getText())){
                    showSimpleMessage("Registro Exitoso", "El recepcionista ha sido registrado con éxito.\n"+Presenter.getInstance().getReceptionistInfo(documentField.getText())+ "\nSu clave es su nombre + su documento de identidad");
                    documentField.setText("");
                    emailField.setText("");
                    firstNameField.setText("");
                    lastNameField.setText("");
                    phoneField.setText("");
                    addressField.setText("");
                }else{
                    showErrorMessage("Error", "No se pudo registrar el recepcionista. Por favor, revise los datos introducidos o compruebe si exite un parqueadero");
                }
            }
        });
        registerReceptionistPanel.add(inputButton, config);
    
        adminSectionPanel.add(registerReceptionistPanel, "Register Receptionist Panel");
    }

    public void createEditReceptionistPanel() {
        JPanel editReceptionistPanel = new JPanel();
        editReceptionistPanel.setLayout(new GridBagLayout());
        GridBagConstraints config = new GridBagConstraints();
        config.insets = new Insets(5, 5, 5, 5);
        config.fill = GridBagConstraints.HORIZONTAL;
    
        config.gridx = 0;
        config.gridy = 0;
        config.gridwidth = 2;
        JLabel selectLabel = new JLabel("Seleccione el recepcionista:");
        editReceptionistPanel.add(selectLabel, config);
    
        config.gridx = 0;
        config.gridy = 3;
        editReceptionistPanel.add(new JSeparator(), config);
        
        config.gridy = 4;
        JLabel modifyLabel = new JLabel("<html>Digite los datos a modificar del recepcionista (<font color='#00298B'>debe seleccionar un recepcionista</font>)</html>: ");
        editReceptionistPanel.add(modifyLabel, config);
        
        config.gridy = 1;
        JList<String> receptionistDropdown = new JList<>(new String[]{"ingrese un nombre"});
        receptionistDropdown.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(receptionistDropdown.getSelectedValue()!=null){
                    modifyLabel.setText("Digite los datos a modificar del recepcionista ("+receptionistDropdown.getSelectedValue()+"): ");
                }else{
                    modifyLabel.setText("<html>aDigite los datos a modificar del recepcionista (<font color='#00298B'>debe seleccionar un recepcionista</font>): </html>");
                }
            }
            
        });
        receptionistDropdown.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(receptionistDropdown);
        scrollPane.setPreferredSize(new Dimension(200, 70));
        editReceptionistPanel.add(scrollPane, config);

        config.gridy = 2;
        config.gridwidth = 1;
        config.gridx = 0;
        config.anchor = GridBagConstraints.LINE_END;
        editReceptionistPanel.add(new JLabel("Nombre del recepcionista:"), config);
    
        config.gridx = 1;
        config.anchor = GridBagConstraints.LINE_START;
        JTextField nameField = new JTextField(20);
        nameField.getDocument().addDocumentListener(new DocumentAdapter() {
            @Override
            public void onChange(DocumentEvent e) {
                uptdateList(nameField.getText(), receptionistDropdown);
            }
        });
        editReceptionistPanel.add(nameField, config);

        config.gridy = 5;
        config.gridx = 0;
        config.gridwidth = 1;
        config.anchor = GridBagConstraints.LINE_END;
        editReceptionistPanel.add(new JLabel("Documento de identidad:"), config);
    
        config.gridx = 1;
        config.anchor = GridBagConstraints.LINE_START;
        JTextField documentField = new JTextField(20);
        documentField.setText("[0-9]+");
        editReceptionistPanel.add(documentField, config);

        config.gridy = 6;
        config.gridx = 0;
        editReceptionistPanel.add(new JLabel("Contraseña:"), config);
    
        config.gridx = 1;
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setText("[A-Za-z0-9]{8}");
        editReceptionistPanel.add(passwordField, config);
        
        config.gridy = 7;
        config.gridx = 0;
        editReceptionistPanel.add(new JLabel("Confirmar contraseña:"), config);
        
        config.gridx = 1;
        JPasswordField confirmPasswordField = new JPasswordField(20);
        confirmPasswordField.setText("[A-Za-z0-9]{8}");
        editReceptionistPanel.add(confirmPasswordField, config);
    
        config.gridy = 8;
        config.gridx = 0;
        config.gridwidth = 2;
        JLabel passwordNote = new JLabel("<html><font color='#00298B'>Tenga en cuenta que la nueva contraseña no debe ser repetida ni tener caracteres especiales y debe ser de 8 caracteres.</font></html>");
        editReceptionistPanel.add(passwordNote, config);

        config.gridy = 9;
        config.anchor = GridBagConstraints.CENTER;
        config.fill = GridBagConstraints.CENTER ;
        JButton acceptButton = new JButton("Aceptar");
        ButtonSummitControl acceptSummitControl = new ButtonSummitControl(acceptButton, editReceptionistPanel);
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (passwordField.getText().equals(confirmPasswordField.getText())) {
                    try{
                        String[] trim = receptionistDropdown.getSelectedValue().split(":");
                        Presenter.getInstance().modifyReceptionist(trim[1], documentField.getText(), passwordField.getText());
                    }catch (PatternSyntaxException ex){
                        showErrorMessage("Error", "Debe escoger un recepcionista.");
                    }catch (NullPointerException ex){
                        showErrorMessage("Error", "Debe escoger un recepcionista.");
                    }
                }else{
                    showErrorMessage("Error", "Las contraseñas no coinciden.");
                }
            }
            
        });
        editReceptionistPanel.add(acceptButton, config);
    
        adminSectionPanel.add(editReceptionistPanel, "Edit Receptionist Panel");
    }
    
    public void createSelectDatePanel() {
        JPanel selectDatePanel = new JPanel();
        selectDatePanel.setLayout(new GridBagLayout());
        GridBagConstraints config = new GridBagConstraints();
        config.insets = new Insets(5, 5, 5, 5);
        config.fill = GridBagConstraints.VERTICAL;

        config.gridy = 0;
        config.gridx = 0;
        config.gridwidth = 4;
        JLabel title = new JLabel("Ingrese la fecha para consultar el reporte:");
        selectDatePanel.add(title, config);
    
        config.fill = GridBagConstraints.HORIZONTAL;
        config.gridy = 1;
        JLabel selectLabel = new JLabel("Fecha:");
        selectDatePanel.add(selectLabel, config);

        config.gridwidth = 1;
        config.gridx = 0;
        config.gridy = 2;
        JTextField dayField = new JTextField(20);
        dayField.setText("(0?[1-9]|[12][0-9]|3[01])");
        selectDatePanel.add(dayField, config);

        config.gridy = 3;
        selectDatePanel.add(new JLabel("Día:"), config);

        config.gridy = 2;
        config.gridx = 1;
        JTextField monthField = new JTextField(20);
        monthField.setText("(0?[1-9]|1[0-2])");
        selectDatePanel.add(monthField, config);

        config.gridy = 3;
        selectDatePanel.add(new JLabel("Mes:"), config);


        config.gridx = 3;
        config.gridy = 2;
        JTextField yearField = new JTextField(20);
        yearField.setText("[0-9][0-9][0-9][0-9]");
        selectDatePanel.add(yearField, config);
        
        config.gridy = 3;
        selectDatePanel.add(new JLabel("Año:"), config);

        config.gridy = 4;
        config.gridx = 0;
        config.gridwidth = 4;
        config.fill = GridBagConstraints.VERTICAL;
        JButton button = new JButton("Consultar");
        ButtonSummitControl buttonSummitControl = new ButtonSummitControl(button, selectDatePanel);
        button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        LocalDate date = LocalDate.of(Integer.parseInt(yearField.getText()), Integer.parseInt(monthField.getText()), Integer.parseInt(dayField.getText()));
                        createReportPanel(date);
                        dayField.setText("");
                        monthField.setText("");
                        yearField.setText("");
                    }catch(DateTimeException ex){
                        showErrorMessage("Error", "La fecha ingresada no es válida.");
                    }
                }
            }
        );
        selectDatePanel.add(button, config);

        adminSectionPanel.add(selectDatePanel, "Select Date Panel");
        
    }
    

    public void createReportPanel(LocalDate date) {
        reportPanel.setLayout(new GridBagLayout());
        reportPanel.removeAll();
        
        GridBagConstraints config = new GridBagConstraints();
        config.insets = new Insets(5, 5, 5, 5);
        config.fill = GridBagConstraints.CENTER;

        reportPanel.add(new JLabel("Informe de ventas "+date.getDayOfMonth()+"/"+date.getMonthValue()+"/"+date.getYear()), config);
        
        config.gridy = 1;
        DefaultTableModel tableModelPR = new DefaultTableModel(new String[]{"Total de ingresos", "Número de vehículos"}, 0);
        JTable parkingReportTable = new JTable(tableModelPR);
        parkingReportTable.getTableHeader().setReorderingAllowed(false);
        parkingReportTable.setEnabled(false);
        tableModelPR.addRow(Presenter.getInstance().generateParkingReport(date));
        JScrollPane scrollPanePR = new JScrollPane(parkingReportTable);
        scrollPanePR.setPreferredSize(new Dimension(400, 100));
        reportPanel.add(scrollPanePR, config);

        config.gridy = 2;
        DefaultTableModel tableModelRR = new DefaultTableModel(new String[]{"Recepcionista", "Ingresos", "Ingreso de vehículos"}, 0);
        JTable parkingReceptionistTable = new JTable(tableModelRR);
        parkingReceptionistTable.getTableHeader().setReorderingAllowed(false);
        parkingReceptionistTable.setEnabled(false);
        String[][] receptionistReport = Presenter.getInstance().generateReceptionistReport(date);
        for(int i = 0; i < receptionistReport.length; i++){
            tableModelRR.addRow(receptionistReport[i]);
        }
        JScrollPane scrollPaneRR = new JScrollPane(parkingReceptionistTable);
        scrollPaneRR.setPreferredSize(new Dimension(400, 100));
        reportPanel.add(scrollPaneRR, config);

        config.gridy = 3;
        JButton acceptButton = new JButton("Aceptar");
        acceptButton.setBackground(goldenYellow);
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)(adminSectionPanel.getLayout())).show(adminSectionPanel, "Select Date Panel");
            }
        });
        reportPanel.add(acceptButton, config);

        ((CardLayout)(adminSectionPanel.getLayout())).show(adminSectionPanel, "Report Panel");
    }
    
    public void showLoginPanel(){
        
    }

    public void showReceptionistMenu(String greeting){
        updateInitPanel(greeting, receptionistSectionPanel);
        ((CardLayout)(receptionistSectionPanel.getLayout())).show(receptionistSectionPanel, "Init Panel");
        ((CardLayout)(getContentPane().getLayout())).show(getContentPane(), "Receptionist Panel");
    }

    public void showVehicleEntryPanel(){
        ((CardLayout)(receptionistSectionPanel.getLayout())).show(receptionistSectionPanel, "Vehicle Entry Panel");
    }

    public void showGenerateEntryTicketPanel(ArrayList<String> entryTicketInfo){
        createGenerateEntryTicketPanel(entryTicketInfo);
        ((CardLayout)(receptionistSectionPanel.getLayout())).show(receptionistSectionPanel, "Generate Entry Ticket Panel");
    }

    public void showErrorMessage(String title, String message){
        JOptionPane.showMessageDialog(getContentPane(), message, title, JOptionPane.ERROR_MESSAGE);
    }

    public void showSimpleMessage(String title, String message){
        JOptionPane.showMessageDialog(getContentPane(), message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public void showWarningMessage(String title, String message){
        JOptionPane.showMessageDialog(getContentPane(), message, title, JOptionPane.WARNING_MESSAGE);
    }

    public void showAdminMenu(String greeting){
        updateInitPanel(greeting, adminSectionPanel);
        ((CardLayout)(adminSectionPanel.getLayout())).show(adminSectionPanel, "Init Panel");
        ((CardLayout)(getContentPane().getLayout())).show(getContentPane(), "Admin Panel");
    }
    
    private void uptdateList(String name, JList<String> jList) {
        ArrayList<String> results = new ArrayList<String>();
        for (String n : Presenter.getInstance().getReceptionistList()) {
            boolean match = true;
            if (name.length() <= n.length()) {
                for(int i = 0; i < name.length() && i<n.length() && match; i++) {
                    if (n.toLowerCase().charAt(i) != name.toLowerCase().charAt(i)) 
                        match = false;
                }    
                if (match) 
                    results.add(n);
            }
        }
        if (results.size() == 0){
            jList.setListData(new String[]{"No se encontraron recepcionistas con ese nombre."});
            jList.setEnabled(false);
        }else {
            String[] resultsArray = new String[results.size()];
            for (int i = 0; i < results.size(); i++)
                resultsArray[i] = results.get(i);
            jList.setEnabled(true);
            jList.setListData(resultsArray);
        }
    }

    private void updatedisponibleSpacesLabel(){
        disponibleSpacesLabel.setText("El número de plazas disponibles es: "+Presenter.getInstance().getFreeSpaces());
        disponibleSpacesLabel2.setText("El número de plazas disponibles es: "+Presenter.getInstance().getFreeSpaces());
        if(Presenter.getInstance().getFreeSpaces()<5)
            showWarningMessage("Advertencia", "Quedan menos de 5 plazas disponibles.");
    }
}
