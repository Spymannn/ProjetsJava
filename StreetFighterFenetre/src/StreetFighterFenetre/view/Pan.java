package StreetFighterFenetre.view;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
/**
 *
 * @author Spymannn
 * Panneau de fond d'écran pour lequel tous les panel hérite
 */
public abstract class Pan extends JPanel{
    public Pan(){}
    
    public void paintComponent(Graphics g) {
        try {
            Image fond = ImageIO.read(new File("img/fond3.jpg"));
            g.drawImage(fond, 0, 0, this.getWidth(), this.getHeight(), this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
}
