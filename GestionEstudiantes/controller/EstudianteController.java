package controller;

import model.Estudiante;
import service.EstudianteService;
import java.util.List;

public class EstudianteController {
    private final EstudianteService service = new EstudianteService();

    public void insertar(Estudiante est) throws Exception {
        service.insertar(est);
    }

    public void modificar(Estudiante est) throws Exception {
        service.modificar(est);
    }

    public void eliminar(int id) throws Exception {
        service.eliminar(id);
    }

    public List<Estudiante> listar(boolean activos) throws Exception {
        return service.listar(activos);
    }
}