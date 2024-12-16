package ui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        try {
            // Cargar el GIF usando ImageIcon para manejar la animación
            ImageIcon imageIcon = new ImageIcon(getClass().getResource("/images/background.gif"));
            backgroundImage = imageIcon.getImage();
        } catch (Exception e) {
            System.err.println("Error cargando background.gif: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void inicializarComponentes() {
        // Panel principal con imagen de fondo
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    // Dibujar el GIF escalado para llenar el panel
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }

                // Añadir un overlay semitransparente para mejorar la legibilidad del texto
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(new Color(0, 0, 0, 100));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(null);

        // Panel para el título con fondo semitransparente
        JPanel tituloPanel = crearPanelTitulo();
        tituloPanel.setBounds(100, 100, 600, 200);
        mainPanel.add(tituloPanel);

        // Botón de inicio
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

        // Título principal
        JLabel titulo = new JLabel("Zoo Simulator");
        titulo.setFont(new Font("Arial", Font.BOLD, 60));
        titulo.setForeground(Color.WHITE);
        panel.add(titulo, gbc);

        // Subtítulo
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

        // Hacer el botón semitransparente
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

        // Efectos hover
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

        // Acción del botón
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
