package co.edu.uptc.view;

import java.awt.CardLayout;
import java.util.ArrayList;
import javax.swing.JPanel;

public class PanelGroup {
    private CardLayout cardLayout;
    private View view;

    public PanelGroup(CardLayout cardLayout, View view) {
        this.cardLayout = cardLayout;
        this.view = view;
        
    }

}
