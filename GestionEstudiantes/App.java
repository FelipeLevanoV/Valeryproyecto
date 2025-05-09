import javax.swing.*;
import view.EstudiantePanel;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Gestión de Estudiantes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.add(new EstudiantePanel());
        frame.setVisible(true);
    }
}