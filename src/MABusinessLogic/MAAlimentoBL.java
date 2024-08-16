package MABusinessLogic;

import java.util.List;
import MADataAccess.MAAlimentoDAO;
import MADataAccess.MADTO.MAAlimentoDTO;

public class MAAlimentoBL {
    private MAAlimentoDAO maAlimentoDAO = new MAAlimentoDAO();

    // Constructor      
    public MAAlimentoBL() {}

    // Obtener todos los registros
    public List<MAAlimentoDTO> getAll() throws Exception {
        List<MAAlimentoDTO> lst = maAlimentoDAO.readAll();
        // Puedes realizar alguna transformación si es necesario
        return lst;
    }

    // Obtener un registro por ID
    public MAAlimentoDTO getBy(int idAlimento) throws Exception {
        return maAlimentoDAO.readBy(idAlimento);
    }

    // Crear un nuevo registro
    public boolean add(MAAlimentoDTO alimento) throws Exception {
        return maAlimentoDAO.create(alimento);
    }

    // Actualizar un registro existente
    public boolean update(MAAlimentoDTO alimento) throws Exception {
        return maAlimentoDAO.update(alimento);
    }

    // Eliminar un registro (lógica, cambiando el estado a 'X')
    public boolean delete(int idAlimento) throws Exception {
        return maAlimentoDAO.delete(idAlimento);
    }
}