package co.edu.uptc.view;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;

public class ButtonSummitControl  {
    JButton button;
    ArrayList<JComponent> components;
    ArrayList<JComponent> redyComponents;
    ArrayList<String> componentsRegex;
    JLabel label;

    public ButtonSummitControl(JButton button, JPanel panel) {
        this.button = button;
        button.setEnabled(false);
        components = new ArrayList<JComponent>();
        componentsRegex = new ArrayList<String>();
        redyComponents = new ArrayList<JComponent>();
        label = new JLabel();
        
        panel.add(label);
        getRestrictions(panel);
    }

    public JButton getButton() {
        return button;
    }

    public void setButton(JButton button) {
        this.button = button;
    }

    public ArrayList<JComponent> getComponents() {
        return components;
    }

    public void setComponents(ArrayList<JComponent> components) {
        this.components = components;
    }

    public ArrayList<JComponent> getRedyComponents() {
        return redyComponents;
    }

    public void setRedyComponents(ArrayList<JComponent> redyComponents) {
        this.redyComponents = redyComponents;
    }

    public ArrayList<String> getComponentsRegex() {
        return componentsRegex;
    }

    public void setComponentsRegex(ArrayList<String> componentsRestrictions) {
        this.componentsRegex = componentsRestrictions;
    }

    private void getRestrictions(JPanel panel) {
        for (Component component : panel.getComponents()) {
            if (component instanceof JTextField) {
                asignListeners((JTextField) component);
            }
            if (component instanceof JPasswordField) {
                asignListeners((JPasswordField) component);
            }
        }
    }

    private void asignListeners(JTextField field) {
        if (field.getText().isEmpty())
            return;
        componentsRegex.add(field.getText());
        components.add(field);
        field.setText("");
        field.getDocument().addDocumentListener(new DocumentAdapter() {
            @Override
            public void onChange(DocumentEvent e) {
                int index = components.indexOf(field);
                if (index >= 0 && field.getText().matches(componentsRegex.get(index))) {
                    if (!redyComponents.contains(field)) {
                        redyComponents.add(field);
                    }
                } else {
                    redyComponents.remove(field);
                }
                checkRestrictions();
            }
        });
    }

    private void checkRestrictions() {
        if(redyComponents.size() == components.size()){
            button.setEnabled(true);
            label.setText("ehhh! Ta bien");
        }
        else{
            button.setEnabled(false);
            label.setText("ehhh! Ta mal, en algo");
        }
        label.setSize(label.getPreferredSize());
    }

    
}
