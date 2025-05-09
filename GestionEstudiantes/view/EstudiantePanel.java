package view;

import controller.EstudianteController;
import model.Estudiante;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class EstudiantePanel extends JPanel {
    private JTextField txtNombre, txtApellido, txtCorreo;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnInsertar, btnModificar, btnEliminar, btnListar, btnMostrarEliminados;
    private EstudianteController controller = new EstudianteController();
    private int selectedId = -1;

    public EstudiantePanel() {
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(4, 2));
        txtNombre = new JTextField();
        txtApellido = new JTextField();
        txtCorreo = new JTextField();

        form.add(new JLabel("Nombre:"));
        form.add(txtNombre);
        form.add(new JLabel("Apellido:"));
        form.add(txtApellido);
        form.add(new JLabel("Correo:"));
        form.add(txtCorreo);

        add(form, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[]{"ID", "Nombre", "Apellido", "Correo"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel botones = new JPanel();
        btnInsertar = new JButton("Insertar");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar Lógico");
        btnListar = new JButton("Listar Activos");
        btnMostrarEliminados = new JButton("Mostrar Eliminados");

        botones.add(btnInsertar);
        botones.add(btnModificar);
        botones.add(btnEliminar);
        botones.add(btnListar);
        botones.add(btnMostrarEliminados);
        add(botones, BorderLayout.SOUTH);

        btnInsertar.addActionListener(e -> insertar());
        btnModificar.addActionListener(e -> modificar());
        btnEliminar.addActionListener(e -> eliminar());
        btnListar.addActionListener(e -> listar(true));
        btnMostrarEliminados.addActionListener(e -> listar(false));

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = table.getSelectedRow();
                selectedId = Integer.parseInt(table.getValueAt(fila, 0).toString());
                txtNombre.setText(table.getValueAt(fila, 1).toString());
                txtApellido.setText(table.getValueAt(fila, 2).toString());
                txtCorreo.setText(table.getValueAt(fila, 3).toString());
            }
        });
    }

    private void insertar() {
        try {
            Estudiante est = new Estudiante(0, txtNombre.getText(), txtApellido.getText(), txtCorreo.getText(), true);
            controller.insertar(est);
            listar(true);
            limpiar();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void modificar() {
        if (selectedId == -1) return;
        try {
            Estudiante est = new Estudiante(selectedId, txtNombre.getText(), txtApellido.getText(), txtCorreo.getText(), true);
            controller.modificar(est);
            listar(true);
            limpiar();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void eliminar() {
        if (selectedId == -1) return;
        try {
            controller.eliminar(selectedId);
            listar(true);
            limpiar();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void listar(boolean activos) {
        try {
            tableModel.setRowCount(0);
            List<Estudiante> lista = controller.listar(activos);
            for (Estudiante e : lista) {
                tableModel.addRow(new Object[]{e.getId(), e.getNombre(), e.getApellido(), e.getCorreo()});
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void limpiar() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtCorreo.setText("");
        selectedId = -1;
    }
}