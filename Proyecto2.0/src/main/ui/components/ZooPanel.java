package ui.components;

import model.TipoElemento;
import model.Zoo;
import model.animals.Animal;
import model.food.Comida;
import model.habitats.Habitat;
import ui.DraggableComponent;
import util.AnimacionMovimiento;
import util.Constants;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static model.TipoElemento.*;

public class ZooPanel extends JPanel {
    private final Zoo zoo;
    private final int PANEL_WIDTH = 900;
    private final int PANEL_HEIGHT = 600;
    private final int HABITAT_AREA_HEIGHT = 600;
    private final int ALERT_HEIGHT = 100;
    private Point previewLocation;
    private DraggableComponent draggedComponent;
    private boolean showGrid = true;
    private final int GRID_ROWS = 2;
    private final int GRID_COLS = 3;
    private final int GRID_SIZE_WIDTH = PANEL_WIDTH / GRID_COLS;  // 300 pixels de ancho
    private final int GRID_SIZE_HEIGHT = HABITAT_AREA_HEIGHT / GRID_ROWS; // 300px por fila
    private final JTextArea alertArea;
    private final List<AnimacionMovimiento> animaciones;


    public ZooPanel() {
        zoo = Zoo.getInstance();
        zoo.setZooPanel(this);
        animaciones = new ArrayList<>();
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(Color.WHITE);

        // Panel de alertas
        alertArea = new JTextArea(4, 40);
        alertArea.setEditable(false);
        alertArea.setBackground(new Color(255, 240, 240));
        alertArea.setForeground(Color.RED);
        alertArea.setFont(new Font("Arial", Font.BOLD, 14));
        alertArea.setMargin(new Insets(5, 5, 5, 5));

        JScrollPane alertScroll = new JScrollPane(alertArea);
        alertScroll.setPreferredSize(new Dimension(PANEL_WIDTH, ALERT_HEIGHT));
        alertScroll.setBorder(BorderFactory.createTitledBorder("Alertas de Animales Hambrientos"));

        add(alertScroll, BorderLayout.SOUTH);
    }


    public void actualizarAlertas(Set<String> alertas) {
        SwingUtilities.invokeLater(() -> {
            StringBuilder texto = new StringBuilder();
            for (String alerta : alertas) {
                texto.append(alerta).append("\n");
            }
            alertArea.setText(texto.toString());
            alertArea.setCaretPosition(0);
        });
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Activar antialiasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Dibujar el área de hábitats
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, PANEL_WIDTH, HABITAT_AREA_HEIGHT);

        // Dibujar la cuadrícula
        dibujarCuadricula(g2d);

        // Dibujar hábitats
        for (Habitat habitat : zoo.getHabitats()) {
            habitat.dibujar(g2d);
        }

        // Dibujar animales
        for (Map.Entry<Habitat, List<Animal>> entry : zoo.getAnimalesPorHabitat().entrySet()) {
            for (Animal animal : entry.getValue()) {
                animal.dibujar(g2d);
            }
        }

        // Dibujar comida
        for (Map.Entry<Habitat, List<Comida>> entry : zoo.getComidaPorHabitat().entrySet()) {
            for (Comida comida : entry.getValue()) {
                if (!comida.isConsumida()) {
                    comida.dibujar(g2d);
                }
            }
        }

        // Dibujar preview durante el drag
        if (previewLocation != null && draggedComponent != null) {
            Point gridPoint = snapToGrid(previewLocation);
            drawDragPreview(g2d, gridPoint, draggedComponent.getTipo());
        }

        // Dibujar borde del área de hábitats
        g2d.setColor(Color.BLACK);
        g2d.drawRect(0, 0, PANEL_WIDTH - 1, HABITAT_AREA_HEIGHT - 1);
    }
    private void dibujarContenido(Graphics2D g) {
        // Activar antialiasing para mejor calidad de dibujo
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Dibujar fondo
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT - ALERT_HEIGHT);

        // Dibujar la cuadrícula si está activa
        if (showGrid) {
            dibujarCuadricula(g);
        }

        // Dibujar hábitats
        for (Habitat habitat : zoo.getHabitats()) {
            habitat.dibujar(g);
        }

        // Dibujar animales
        for (Map.Entry<Habitat, List<Animal>> entry : zoo.getAnimalesPorHabitat().entrySet()) {
            for (Animal animal : entry.getValue()) {
                animal.dibujar(g);
            }
        }

        // Dibujar comida
        for (Map.Entry<Habitat, List<Comida>> entry : zoo.getComidaPorHabitat().entrySet()) {
            for (Comida comida : entry.getValue()) {
                if (!comida.isConsumida()) {
                    comida.dibujar(g);
                }
            }
        }

        // Dibujar preview durante el drag
        if (previewLocation != null && draggedComponent != null) {
            Point gridPoint = snapToGrid(previewLocation);
            drawDragPreview(g, gridPoint, draggedComponent.getTipo());
        }

        // Dibujar bordes del panel
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, PANEL_WIDTH - 1, PANEL_HEIGHT - ALERT_HEIGHT - 1);
    }
    private void dibujarInformacionDebug(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 12));

        // Mostrar número de hábitats y animales
        int y = 20;
        g.drawString("Hábitats: " + zoo.getHabitats().size(), 10, y);
        y += 15;
        g.drawString("Animales totales: " + zoo.getTotalAnimales(), 10, y);

        // Mostrar coordenadas del mouse si está disponible
        Point mousePos = getMousePosition();
        if (mousePos != null) {
            y += 15;
            g.drawString(String.format("Mouse: (%d, %d)", mousePos.x, mousePos.y), 10, y);
        }
    }
    private boolean isDebugMode() {
        return false;
    }

    private void dibujarCuadricula(Graphics2D g) {
        g.setColor(new Color(200, 200, 200));

        // Líneas verticales
        for (int x = 0; x <= GRID_COLS; x++) {
            int xPos = x * GRID_SIZE_WIDTH;
            g.drawLine(xPos, 0, xPos, HABITAT_AREA_HEIGHT);
        }

        // Líneas horizontales
        for (int y = 0; y <= GRID_ROWS; y++) {
            int yPos = y * GRID_SIZE_HEIGHT;
            g.drawLine(0, yPos, PANEL_WIDTH, yPos);
        }
    }
    public Point snapToGrid(Point p) {
        int col = Math.min(Math.max(p.x / GRID_SIZE_WIDTH, 0), GRID_COLS - 1);
        int row = Math.min(Math.max(p.y / GRID_SIZE_HEIGHT, 0), GRID_ROWS - 1);
        return new Point(col * GRID_SIZE_WIDTH, row * GRID_SIZE_HEIGHT);
    }


    public boolean esUbicacionValida(Point p) {
        if (p.y >= HABITAT_AREA_HEIGHT) return false;

        int col = p.x / GRID_SIZE_WIDTH;
        int row = p.y / GRID_SIZE_HEIGHT;
        return col >= 0 && col < GRID_COLS && row >= 0 && row < GRID_ROWS;
    }

    public void setShowGrid(boolean show) {
        this.showGrid = show;
        repaint();
    }

    public Point getGridPosition(Point mousePosition) {
        return snapToGrid(mousePosition);
    }

    private void drawDragPreview(Graphics2D g, Point location, TipoElemento tipo) {
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));

        switch (tipo) {
            case HABITAT:
                g.setColor(new Color(0, 255, 0, 100));
                g.fillRect(location.x, location.y, GRID_SIZE_WIDTH, GRID_SIZE_HEIGHT);
                g.setColor(new Color(0, 0, 0, 100));
                g.drawRect(location.x, location.y, GRID_SIZE_WIDTH, GRID_SIZE_HEIGHT);
                break;
            case ANIMAL:
                g.setColor(new Color(255, 0, 0, 100));
                g.fillOval(location.x + GRID_SIZE_WIDTH/4, location.y + GRID_SIZE_HEIGHT/4,
                        GRID_SIZE_WIDTH/2, GRID_SIZE_HEIGHT/2);
                break;
            case COMIDA:
                g.setColor(new Color(0, 0, 255, 100));
                g.fillRect(location.x + GRID_SIZE_WIDTH/3, location.y + GRID_SIZE_HEIGHT/3,
                        GRID_SIZE_WIDTH/3, GRID_SIZE_HEIGHT/3);
                break;
        }

        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    }    public void setDraggedComponent(DraggableComponent component) {
        this.draggedComponent = component;
    }

    public void updatePreviewLocation(Point location) {
        this.previewLocation = location;
        repaint();
    }

    public void clearPreview() {
        this.previewLocation = null;
        this.draggedComponent = null;
        repaint();
    }
}
