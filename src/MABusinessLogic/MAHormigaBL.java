package MABusinessLogic;

import java.util.List;
import MADataAccess.MAHormigaDAO;
import MADataAccess.MADTO.MAHormigaDTO;


public class MAHormigaBL {
    private MAHormigaDAO maHormigaDAO = new MAHormigaDAO();

    // Constructor
    public MAHormigaBL() {}

    // Obtener todos los registros
    public  List<MAHormigaDTO> getAll() throws Exception {
        List<MAHormigaDTO> lst = maHormigaDAO.readAll();
        // Puedes realizar alguna transformación si es necesario, por ejemplo:
        for (MAHormigaDTO hormigaDTO : lst) {
            hormigaDTO.setMaTipoHormiga(hormigaDTO.getMaTipoHormiga().toUpperCase());
            // También puedes transformar otros campos si es necesario
        }
        return lst;
    }

    // Obtener un registro por ID
    public MAHormigaDTO getBy(int idHormiga) throws Exception {
        return maHormigaDAO.readBy(idHormiga);
    }

    // Agregar un nuevo registro
    public boolean add(MAHormigaDTO nuevaHormiga) throws Exception {
        return maHormigaDAO.create(nuevaHormiga); // Llamada al método create del DAO
    }
    
    

    // Actualizar un registro existente
    public boolean update(MAHormigaDTO hormigaDTO) throws Exception {
        return maHormigaDAO.update(hormigaDTO);
    }

    // Eliminar un registro por ID
    public boolean delete(int idHormiga) throws Exception {
        return maHormigaDAO.delete(idHormiga);
    }

    // Obtener el conteo de registros
    // public Integer getRowCount() throws Exception {
    //     return maHormigaDAO.getRowCount();
    // }
    
}
