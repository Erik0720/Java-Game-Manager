import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class MenuBoard extends JComponent {

    private int width = 700;
    private int height = 500;
    private int padding = 22;
    private int image_size = 200;
    private int i = 0;
    private Image imageTTT;
    private Image imageKniffel;
    private Image imageC4;
    private JTabbedPane tabbedPane;


    public MenuBoard(JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
        initiate();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.LIGHT_GRAY);
    }

    public void readImages() throws IOException {
        imageC4 = ImageIO.read(new File("src/Assets/Images/ConnectFour.png"));       // Read in the image
        imageKniffel = ImageIO.read(new File("src/Assets/Images/kniffel.png"));      // Read in the image
        imageTTT = ImageIO.read(new File("src/Assets/Images/tic-tac-toe-game.png")); // Read in the image

        imageC4 = imageC4.getScaledInstance(image_size, image_size, Image.SCALE_SMOOTH);               // Scale the image
        imageKniffel = imageKniffel.getScaledInstance(image_size, image_size, Image.SCALE_SMOOTH);    // Scale the image
        imageTTT = imageTTT.getScaledInstance(image_size, image_size, Image.SCALE_SMOOTH);            // Scale the image
    }

    public void initiate() {
        try {
            readImages();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon iconC4 = new ImageIcon(imageC4);      // Create an ImageIcon from the image
        ImageIcon iconKniffel = new ImageIcon(imageKniffel);    // Create an ImageIcon from the image
        ImageIcon iconTTT = new ImageIcon(imageTTT);        // Create an ImageIcon from the image

        JLabel labelC4 = new JLabel(iconC4);
        JLabel labelKniffel = new JLabel(iconKniffel);
        JLabel labelTTT = new JLabel(iconTTT);

        MouseAdapter listenerC4 = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("C4");
                tabbedPane.setSelectedIndex(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                labelC4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                labelC4.setBorder(null);
            }
        };
        MouseAdapter listenerKniffel = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Kniffel");
                tabbedPane.setSelectedIndex(1);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                labelKniffel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                labelKniffel.setBorder(null);
            }
        };
        MouseAdapter listenerTTT = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("TTT");
                tabbedPane.setSelectedIndex(2);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                labelTTT.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                labelTTT.setBorder(null);
            }
        };

        JLabel labelText = new JLabel("<HTML><u>Choose a game:<u><HTML>");   // Create a label with text
        labelText.setFont(new Font("Arial", Font.BOLD, 20));    // Set font
        this.add(labelText);
        labelText.setBounds(2 * padding + image_size + 25, 50, 200, 50);    // Set location
        labelText.setForeground(Color.WHITE);   // Set color to white

        this.add(labelC4);
        labelC4.setBounds(padding, 125, 200, 200);

        this.add(labelKniffel);
        labelKniffel.setBounds(2 * padding + image_size, 125, 200, 200);

        this.add(labelTTT);
        labelTTT.setBounds(3 * padding + 2 * image_size, 125, 200, 200);

        labelC4.addMouseListener(listenerC4);
        labelKniffel.addMouseListener(listenerKniffel);
        labelTTT.addMouseListener(listenerTTT);
    }
}
