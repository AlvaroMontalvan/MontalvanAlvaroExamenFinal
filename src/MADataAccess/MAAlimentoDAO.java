package MADataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import MADataAccess.MADTO.MAAlimentoDTO;
import MAFramework.MAException;

public class MAAlimentoDAO extends MASQLiteDataHelper implements MAIDAO<MAAlimentoDTO> {

    @Override
    public boolean create(MAAlimentoDTO entity) throws Exception {
        String query = "INSERT INTO MAAlimento (IdCatalogoIngestaNativa, IdCatalogoGenoAlimento) VALUES (?, ?)";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getMaIngestaNativaNombre());
            pstmt.setString(2, entity.getMaGenoAlimentoNombre());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new MAException(e.getMessage(), getClass().getName(), "create()");
        }
    }

    @Override
    public List<MAAlimentoDTO> readAll() throws Exception {
        List<MAAlimentoDTO> lst = new ArrayList<>();
        String query = 
            "SELECT a.IdAlimento, i.Nombre AS IngestaNativaNombre, g.Nombre AS GenoAlimentoNombre, a.Estado, a.FechaCreacion, a.FechaModificacion " +
            "FROM MAAlimento a " +
            "JOIN MACatalogo i ON a.IdCatalogoIngestaNativa = i.IdCatalogo " +
            "JOIN MACatalogo g ON a.IdCatalogoGenoAlimento = g.IdCatalogo " +
            "WHERE a.Estado = 'A'";
        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                MAAlimentoDTO a = new MAAlimentoDTO(
                    rs.getInt(1), // IdAlimento
                    rs.getString(2), // IngestaNativaNombre
                    rs.getString(3), // GenoAlimentoNombre
                    rs.getString(4), // Estado
                    rs.getString(5), // FechaCreacion
                    rs.getString(6)  // FechaModificacion
                );
                lst.add(a);
            }
        } catch (SQLException e) {
            throw new MAException(e.getMessage(), getClass().getName(), "readAll()");
        }
        return lst;
    }

    @Override
    public boolean update(MAAlimentoDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = "UPDATE MAAlimento SET IdCatalogoIngestaNativa = ?, IdCatalogoGenoAlimento = ?, Estado = ?, FechaModificacion = ? WHERE IdAlimento = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getMaIngestaNativaNombre());
            pstmt.setString(2, entity.getMaGenoAlimentoNombre());
            pstmt.setString(3, entity.getMaEstado());
            pstmt.setString(4, dtf.format(now));
            pstmt.setInt(5, entity.getMaIdAlimento());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new MAException(e.getMessage(), getClass().getName(), "update()");
        }
    }

    @Override
    public boolean delete(int id) throws Exception {
        String query = "UPDATE MAAlimento SET Estado = 'X' WHERE IdAlimento = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new MAException(e.getMessage(), getClass().getName(), "delete()");
        }
    }

    @Override
    public MAAlimentoDTO readBy(Integer id) throws Exception {
        MAAlimentoDTO a = new MAAlimentoDTO();
        String query = 
            "SELECT a.IdAlimento, i.Nombre AS IngestaNativaNombre, g.Nombre AS GenoAlimentoNombre, a.Estado, a.FechaCreacion, a.FechaModificacion " +
            "FROM MAAlimento a " +
            "JOIN MACatalogo i ON a.IdCatalogoIngestaNativa = i.IdCatalogo " +
            "JOIN MACatalogo g ON a.IdCatalogoGenoAlimento = g.IdCatalogo " +
            "WHERE a.IdAlimento = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                a = new MAAlimentoDTO(
                    rs.getInt(1), // IdAlimento
                    rs.getString(2), // IngestaNativaNombre
                    rs.getString(3), // GenoAlimentoNombre
                    rs.getString(4), // Estado
                    rs.getString(5), // FechaCreacion
                    rs.getString(6)  // FechaModificacion
                );
            }
        } catch (SQLException e) {
            throw new MAException(e.getMessage(), getClass().getName(), "readBy()");
        }
        return a;
    }
}
