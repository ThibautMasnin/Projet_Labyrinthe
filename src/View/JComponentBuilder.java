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

import java.awt.*;
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
        //frame.setIconImage(imageIconBuilder("Images/icon.png").getImage());
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
        component.setOpaque(false);
        return hBox;
    }

	/** RETOURNE LE COMPONENT DANS UNE BOX CENTREE VERTICALEMENT **/
    public static Box verticalCenter(JComponent component) {
        Box vBox = Box.createVerticalBox();
        vBox.add(Box.createVerticalGlue());
        vBox.add(component);
        vBox.add(Box.createVerticalGlue());
        component.setOpaque(false);
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
}
