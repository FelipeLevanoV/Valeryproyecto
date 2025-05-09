package service;

import model.Estudiante;
import model.EstudianteDAO;
import java.util.List;

public class EstudianteService {
    private final EstudianteDAO dao = new EstudianteDAO();

    public void insertar(Estudiante est) throws Exception {
        dao.insertar(est);
    }

    public void modificar(Estudiante est) throws Exception {
        dao.modificar(est);
    }

    public void eliminar(int id) throws Exception {
        dao.eliminarLogico(id);
    }

    public List<Estudiante> listar(boolean activos) throws Exception {
        return dao.listar(activos);
    }
}