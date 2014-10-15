package StreetFighterFenetre.view;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Spymannn
 * Panneau des settings
 */
public class SettingsPanel extends Pan{
    private JButton controls,rules,hs,histo,back;
    
    public SettingsPanel(){
        controls = new JButton();
        rules = new JButton();
        hs = new JButton();
        back = new JButton();
        histo = new JButton();
        
        ImageIcon c = new ImageIcon("img\\boutons\\controls.png");
        ImageIcon r = new ImageIcon("img\\boutons\\rules.png");
        ImageIcon h = new ImageIcon("img\\boutons\\hs.png");
        ImageIcon b = new ImageIcon("img\\boutons\\back.png");
        ImageIcon hi = new ImageIcon("img/boutons/historique.png");
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(controls,gbc);
       
        gbc.gridy = 1;
        this.add(rules,gbc);
        
        gbc.gridy = 2;
        this.add(hs,gbc);
        gbc.gridy = 4;
        this.add(histo, gbc);
        gbc.gridy = 5;
        this.add(back,gbc);
        
        controls.setOpaque(false);
        controls.setContentAreaFilled(false);
        controls.setBorderPainted(false);
        controls.setBorder(null);
        controls.setPreferredSize(new Dimension(200,50));
        
        rules.setOpaque(false);
        rules.setContentAreaFilled(false);
        rules.setBorderPainted(false);
        rules.setBorder(null);
        rules.setPreferredSize(new Dimension(200,50));
        
        hs.setOpaque(false);
        hs.setContentAreaFilled(false);
        hs.setBorderPainted(false);
        hs.setBorder(null);
        hs.setPreferredSize(new Dimension(250,50));
        
        back.setOpaque(false);
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setBorder(null);
        back.setPreferredSize(new Dimension(200,50));
        
        histo.setOpaque(false);
        histo.setContentAreaFilled(false);
        histo.setBorderPainted(false);
        histo.setBorder(null);
        histo.setPreferredSize(new Dimension(250,50));
        
        controls.setIcon(c);
        rules.setIcon(r);
        hs.setIcon(h);
        back.setIcon(b);
        histo.setIcon(hi);
        
        
    }

    public JButton getControls() {
        return controls;
    }

    public JButton getRules() {
        return rules;
    }

    public JButton getHs() {
        return hs;
    }

    public JButton getBack() {
        return back;
    }
    
    public JButton getHisto(){
        return histo;
    }
       
    
}
