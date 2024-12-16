package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

public class PantallaInicio extends JFrame {
    private static final int ANCHO = 800;
    private static final int ALTO = 600;
    private Image backgroundImage;

    public PantallaInicio() {
        setTitle("Zoo Simulator");
        setSize(ANCHO, ALTO);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        cargarImagenFondo();
        inicializarComponentes();
    }

    private void cargarImagenFondo() {
       try{
           File file = new File("src/main/resources/images/background.gif");
           if (file.exists()) {
               backgroundImage = ImageIO.read(file);
               return;
           }
        } catch (Exception e) {
            System.err.println("Error cargando background.gif: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void inicializarComponentes() {
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }

                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(new Color(0, 0, 0, 100));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(null);

        JPanel tituloPanel = crearPanelTitulo();
        tituloPanel.setBounds(100, 100, 600, 200);
        mainPanel.add(tituloPanel);

        JButton botonIniciar = crearBotonIniciar();
        botonIniciar.setBounds(300, 400, 200, 60);
        mainPanel.add(botonIniciar);

        setContentPane(mainPanel);
    }

    private JPanel crearPanelTitulo() {
        JPanel panel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(new Color(0, 0, 0, 150));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            }
        };
        panel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel titulo = new JLabel("Zoo Simulator");
        titulo.setFont(new Font("Arial", Font.BOLD, 60));
        titulo.setForeground(Color.WHITE);
        panel.add(titulo, gbc);

        gbc.insets = new Insets(10, 0, 0, 0);
        JLabel subtitulo = new JLabel("¡Gestiona tu propio zoológico!");
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 24));
        subtitulo.setForeground(Color.WHITE);
        panel.add(subtitulo, gbc);

        return panel;
    }

    private JButton crearBotonIniciar() {
        JButton boton = new JButton("Iniciar Simulación");
        boton.setFont(new Font("Arial", Font.BOLD, 20));
        boton.setForeground(Color.WHITE);
        boton.setBackground(new Color(46, 139, 87, 200));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);

        boton.setUI(new BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(0, 0, 0, 150));
                g2d.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 10, 10);
                super.paint(g, c);
            }
        });

        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(new Color(34, 139, 34, 230));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                boton.setBackground(new Color(46, 139, 87, 200));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        boton.addActionListener(e -> {
            dispose();
            SwingUtilities.invokeLater(() -> {
                ZooSimulator simulator = new ZooSimulator();
                simulator.setVisible(true);
            });
        });

        return boton;
    }
}
