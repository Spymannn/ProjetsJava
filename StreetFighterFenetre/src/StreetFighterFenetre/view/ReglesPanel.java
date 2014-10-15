package StreetFighterFenetre.view;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;
/**
 *
 * @author Spymannn
 * panneau des règles du JEU 
 */
public class ReglesPanel extends Pan{
    private JButton back;
    private JLabel regles;
    private ImageIcon backI,reglesI;
    
    public ReglesPanel(){
        back = new JButton();
        regles = new JLabel();
        
        backI = new ImageIcon("img/boutons/back.png");
        reglesI = new ImageIcon("img/fondEtLogo/regles.png");
        
        back.setIcon(backI);
        regles.setIcon(reglesI);
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(regles, gbc);
       
        gbc.gridy = 1;
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
