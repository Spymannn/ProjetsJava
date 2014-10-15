package StreetFighterFenetre.view;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;
/**
 *
 * @author Spymannn
 * Paneau qui me permet d'afficher les contrôles du jeu
 */
public class ControlsPanel extends Pan{
    private JLabel controls;
    private JButton back;
    private ImageIcon backI,controlsI;
    
    public ControlsPanel(){
        ImageIcon logoo = new ImageIcon("img\\logo.png");
        JLabel logo = new JLabel();
        logo.setIcon(logoo);
        logo.setMaximumSize(new Dimension(700,362));
        backI = new ImageIcon("img/boutons/back.png");
        controlsI = new ImageIcon("img/fondEtLogo/fondControls.png");
        back = new JButton();
        controls = new JLabel();
        
 
        back.setIcon(backI);
        controls.setIcon(controlsI);
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(logo, gbc);

        gbc.gridy = 1;
        this.add(controls, gbc);
        
        gbc.gridy = 2;
        this.add(back, gbc);
        
        back.setOpaque(false);
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setBorder(null);
        back.setPreferredSize(new Dimension(200,50));
    }
    public JButton getBack(){
        return back;
    }
    
}
