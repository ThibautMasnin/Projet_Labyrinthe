package View;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import Model.Couloir;
import Model.Pion;
import Model.PositionInsertion;
import Model.Joueur;

import java.util.ArrayList;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

public interface JComponentBuilder {

    public static JFrame frameBuilder() {
        JFrame frame = new JFrame("Labyrinthe");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(-8, 0);
        frame.setSize((int)dim.getWidth()+16,(int)dim.getHeight()-32);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setLayout(new BorderLayout());
        frame.setIconImage(imageIconBuilder("Images/icon.png").getImage());
        return frame;
    }

	/** RETOURNE UN PANE AVEC BACKGROUND **/
    public static JLayeredPane paneBuilder(String backgroundUrl, JComponent component) {
        JLayeredPane pane = new JLayeredPane();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        JPanel background = new JPanel();
        background.add(new JLabel(imageIconBuilder(backgroundUrl)));
        background.setBounds(0, 0, (int)dim.getWidth(), (int)dim.getHeight());

        component.setBounds(0, 0, (int)dim.getWidth(), (int)dim.getHeight());
        component.setOpaque(false);

        pane.add(background, 0, 0);
        pane.add(component, 1, 0);
        
        return pane;
    }
    
	/** RETOURNE UN BOUTON STYLISE **/
    public static JButton buttonBuilder(String name, ActionListener listenner) {
		JButton button = new JButton();
		button.setOpaque(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setBorder(null);
        button.setSize(328, 70);
        button.setName(name);
        button.setIcon(imageIconBuilder("Boutons/btn" + name + ".png"));
        button.addActionListener(listenner);
        return button;
    }

    public static JButton buttonFlecheBuilder(PositionInsertion pos, ActionListener listenner, PositionInsertion origine) {
		JButton button = new JButton();
		button.setOpaque(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setBorder(null);
        button.setSize(328, 70);
        button.setName(pos.name());
        button.setIcon(imageIconBuilder("Boutons/btnFleche" + pos.name().charAt(0) + ".png"));
        button.addActionListener(listenner);
        button.setEnabled(origine==null || !origine.equals(pos));
        return button;
    }
    
	/** RETOURNE UN BOUTON COULOIR **/
    public static JButton couloirSuppBuilder(Couloir couloir, ActionListener listenner, boolean selected) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		JButton button = new JButton();
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setBorder(null);
        button.setSize(dim.height/12, dim.height/12);
        button.setName("supp" + couloir.getOrientation().name().charAt(0));
        ImageIcon icon = imageIconBuilder("Couloirs/" + couloir.getForme().name() + couloir.getOrientation().name().charAt(0) + ".png", dim.height/12, dim.height/12);
        if(couloir.getObjectif() != null) {
            icon = mergeImage(icon, imageIconBuilder("Objectifs/" + couloir.getObjectif().name().toLowerCase() + ".png", dim.height/12, dim.height/12));
        }
        if(!selected) {
            icon = mergeImage(icon, imageIconBuilder("Couloirs/filtre.png", dim.height/12, dim.height/12));
        }
        button.setIcon(icon);
        button.addActionListener(listenner);
        return button;
    }
    public static JButton couloirBuilder(int i, int j, ArrayList<Joueur> joueurs, Couloir couloir, ActionListener listenner, boolean atteignable) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        JButton button;
		button = new JButton();
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setBorder(null);
        button.setSize(dim.height/12, dim.height/12);
        button.setName(i+","+j);
        ImageIcon icon = imageIconBuilder("Couloirs/" + couloir.getForme().name() + couloir.getOrientation().name().charAt(0) + ".png", dim.height/12, dim.height/12);
        if(couloir.getObjectif() != null) {
            icon = mergeImage(icon, imageIconBuilder("Objectifs/" + couloir.getObjectif().name().toLowerCase() + ".png", dim.height/12, dim.height/12));
        }
        else {
            for(Joueur joueur : joueurs) {
                if(i==joueur.getPion().getPositionInitiale().getX() && j==joueur.getPion().getPositionInitiale().getY()) {
                    icon = mergeImage(icon, imageIconBuilder("Couloirs/spawn" + joueur.getPion().getCouleur().name() + ".png", dim.height/12, dim.height/12));
                }
            }
        }
        for(Pion pion : couloir.getPions()) {
            icon = mergeImage(icon, imageIconBuilder("Couloirs/pion" + pion.getCouleur().name() + ".png", dim.height/12, dim.height/12));
        }
        if(!atteignable) {
            icon = mergeImage(icon, imageIconBuilder("Couloirs/filtre.png", dim.height/12, dim.height/12));
        }
        button.setIcon(icon);
        button.addActionListener(listenner);
        return button;
    }

	/** RETOURNE UNE BOX VERTICALE **/
    public static Box vboxBuilder(JComponent... components) {
        Box vBox = Box.createVerticalBox();
        for(JComponent component : components) {
            vBox.add(horizontalCenter(component));
        }
        return vBox;
    }
    public static Box vboxBuilder(int padding, JComponent... components) {
        Box vBox = Box.createVerticalBox();
        for(JComponent component : components) {
            vBox.add(Box.createVerticalStrut(padding));
            vBox.add(horizontalCenter(component));
        }
        return vBox;
    }
    
	/** RETOURNE UNE BOX HORIZONTALE **/
    public static Box hboxBuilder(JComponent... components) {
        Box vBox = Box.createHorizontalBox();
        for(JComponent component : components) {
            vBox.add(verticalCenter(component));
        }
        return vBox;
    }
    public static Box hboxBuilder(int padding,  JComponent... components) {
        Box vBox = Box.createHorizontalBox();
        for(JComponent component : components) {
            vBox.add(Box.createHorizontalStrut(padding));
            vBox.add(verticalCenter(component));
        }
        return vBox;
    }

	/** RETOURNE LE COMPONENT DANS UNE BOX CENTREE HORIZONTALEMENT **/
    public static Box horizontalCenter(JComponent component) {
        Box hBox = Box.createHorizontalBox();
        hBox.add(Box.createHorizontalGlue());
        hBox.add(component);
        hBox.add(Box.createHorizontalGlue());
        return hBox;
    }

	/** RETOURNE LE COMPONENT DANS UNE BOX CENTREE VERTICALEMENT **/
    public static Box verticalCenter(JComponent component) {
        Box vBox = Box.createVerticalBox();
        vBox.add(Box.createVerticalGlue());
        vBox.add(component);
        vBox.add(Box.createVerticalGlue());
        return vBox;
    }
    
	/** RETOURNE UNE BOX HORIZONTALE DE LA TAILLE DEMANDEE **/
    public static Box horizontalSpace(int space) {
        Box hBox = Box.createHorizontalBox();
        hBox.add(Box.createHorizontalStrut(space));
        return hBox;
    }

	/** RETOURNE UNE BOX VERTICALE DE LA TAILLE DEMANDEE **/
    public static Box verticalSpace(int space) {
        Box vBox = Box.createVerticalBox();
        vBox.add(Box.createVerticalStrut(space));
        return vBox;
    }
    
	/** RETOURNE UN TEXT STYLISE **/
    public static JTextPane textBuilder(String text, int pos) {
        JTextPane textPane = new JTextPane();
        textPane.setText(text);
        textPane.setFont(new Font("Arial", Font.PLAIN, 24));
		textPane.setForeground(Color.WHITE);
        textPane.setEditable(false);
        textPane.setOpaque(false);
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet align = new SimpleAttributeSet();
        StyleConstants.setAlignment(align, pos);
        doc.setParagraphAttributes(0, doc.getLength(), align, false);
        return textPane;
	}
    public static JTextPane textBuilder(String text, int pos, int width, int height) {
        JTextPane textPane = new JTextPane();
        textPane.setText(text);
        textPane.setFont(new Font("Arial", Font.PLAIN, 24));  
		textPane.setForeground(Color.WHITE);
        textPane.setEditable(false);
        textPane.setOpaque(false);
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet align = new SimpleAttributeSet();
        StyleConstants.setAlignment(align, pos);
        doc.setParagraphAttributes(0, doc.getLength(), align, false);
        textPane.setPreferredSize(new Dimension(width,height));
        return textPane;
	}
    
	/** RETOURNE UN LABEL STYLISE **/
    public static JLabel labelBuilder(String text, int pos) {
        JLabel label = new JLabel(text, pos);
		label.setFont(new Font("Viner Hand ITC", Font.PLAIN, 32));
		label.setForeground(Color.WHITE);
        return label;
	}
    public static JLabel labelBuilder(String text, int pos, int width) {
        JLabel label = new JLabel(text, pos);
		label.setFont(new Font("Viner Hand ITC", Font.PLAIN, 32));
		label.setForeground(Color.WHITE);
        label.setPreferredSize(new Dimension(width,40));
        return label;
	}
    public static JLabel labelBuilder(String text, int pos, Color color) {
        JLabel label = new JLabel(text, pos);
		label.setFont(new Font("Viner Hand ITC", Font.PLAIN, 32));
		label.setForeground(color);
        return label;
	}
    
	/** RETOURNE UN SPINNER STYLISE **/
    public static JSpinner spinnerBuilder(int min, int max, int byDefault) {
        JSpinner spinner = new JSpinner();
        spinner.setModel(new SpinnerNumberModel(byDefault, min, max, 1));
        spinner.setPreferredSize(new Dimension(150,30));  
        return spinner;
	}  
    

    /** RETOURNE L'IMAGEICON CORRESPONDANT A L'URL **/
    public static ImageIcon imageIconBuilder(String url) {
        return new ImageIcon(JComponentBuilder.class.getResource("/Resources/" + url));
    }

    /** RETOURNE L'IMAGEICON CORRESPONDANT A L'URL AVEC LA TAILLE DEMANDEE **/
    public static ImageIcon imageIconBuilder(String url, int width, int height) {
        ImageIcon original = new ImageIcon(JComponentBuilder.class.getResource("/Resources/" + url));
        Image resized = original.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resized);
    }

    /** RETOURNE L'IMAGEICON QUI SUPERPOSE LES 2 EN PARAMETRES **/
    public static ImageIcon mergeImage(ImageIcon icon1, ImageIcon icon2){ 
        BufferedImage image1 = new BufferedImage(icon1.getImage().getWidth(null), icon1.getImage().getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D Gr1 = image1.createGraphics();
        Gr1.drawImage(icon1.getImage(), 0, 0, null);
        Gr1.dispose();
        BufferedImage image2 = new BufferedImage(icon2.getImage().getWidth(null), icon2.getImage().getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D Gr2 = image2.createGraphics();
        Gr2.drawImage(icon2.getImage(), 0, 0, null);
        Gr2.dispose();

        Graphics2D g2d = image1.createGraphics(); 
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                        RenderingHints.VALUE_ANTIALIAS_ON); 
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, 
                        RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY); 
        g2d.drawImage(image2, 0, 0, null); 
        g2d.dispose(); 
      
        return new ImageIcon(image1) ; 
    }

    public static ImageIcon joinImage(ImageIcon icon1,ImageIcon icon2) {
        BufferedImage img1 = new BufferedImage(icon1.getImage().getWidth(null), icon1.getImage().getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D Gr1 = img1.createGraphics();
        Gr1.drawImage(icon1.getImage(), 0, 0, null);
        Gr1.dispose();
        BufferedImage img2 = new BufferedImage(icon2.getImage().getWidth(null), icon2.getImage().getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D Gr2 = img2.createGraphics();
        Gr2.drawImage(icon2.getImage(), 0, 0, null);
        Gr2.dispose();

        //do some calculate first
        int offset  = 5;
        int width = img1.getWidth()+img2.getWidth()+offset;
        int height = Math.max(img1.getHeight(),img2.getHeight())+offset;
        //create a new buffer and draw two image into the new image
        BufferedImage newImage = new BufferedImage(width,height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = newImage.createGraphics();
        Color oldColor = g2.getColor();
        //fill background
        g2.setPaint(Color.WHITE);
        g2.fillRect(0, 0, width, height);
        //draw image
        g2.setColor(oldColor);
        g2.drawImage(img1, null, 0, 0);
        g2.drawImage(img2, null, img1.getWidth()+offset, 0);
        g2.dispose();

        return new ImageIcon(newImage);
    }
}
