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

import MADataAccess.MADTO.MAHormigaDTO;
import MAFramework.MAException;

public class MAHormigaDAO extends MASQLiteDataHelper implements MAIDAO<MAHormigaDTO> {

    @Override
    public boolean create(MAHormigaDTO entity) throws Exception {
        String query = "INSERT INTO MAHormiga (TipoHormiga, IdCatalogoSexo, IdCatalogoProvincia, IdCatalogoGenoAlimento, IdCatalogoIngestaNativa) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            
            // Obtener ID de Sexo
            String sexoQuery = "SELECT IdCatalogo FROM MACatalogo WHERE Nombre = ?";
            PreparedStatement sexoPstmt = conn.prepareStatement(sexoQuery);
            sexoPstmt.setString(1, entity.getMaSexoNombre());
            ResultSet sexoRs = sexoPstmt.executeQuery();
            int sexoId = 0;
            if (sexoRs.next()) {
                sexoId = sexoRs.getInt(1);
            }
            
            // Obtener ID de Provincia
            String provinciaQuery = "SELECT IdCatalogo FROM MACatalogo WHERE Nombre = ?";
            PreparedStatement provinciaPstmt = conn.prepareStatement(provinciaQuery);
            provinciaPstmt.setString(1, entity.getMaProvinciaNombre());
            ResultSet provinciaRs = provinciaPstmt.executeQuery();
            int provinciaId = 0;
            if (provinciaRs.next()) {
                provinciaId = provinciaRs.getInt(1);
            }
            
            // Obtener ID de Geno Alimento
            String genoAlimentoQuery = "SELECT IdCatalogo FROM MACatalogo WHERE Nombre = ?";
            PreparedStatement genoAlimentoPstmt = conn.prepareStatement(genoAlimentoQuery);
            genoAlimentoPstmt.setString(1, entity.getMaGenoAlimentoNombre());
            ResultSet genoAlimentoRs = genoAlimentoPstmt.executeQuery();
            int genoAlimentoId = 0;
            if (genoAlimentoRs.next()) {
                genoAlimentoId = genoAlimentoRs.getInt(1);
            }
            
            // Obtener ID de Ingesta Nativa
            String ingestaNativaQuery = "SELECT IdCatalogo FROM MACatalogo WHERE Nombre = ?";
            PreparedStatement ingestaNativaPstmt = conn.prepareStatement(ingestaNativaQuery);
            ingestaNativaPstmt.setString(1, entity.getMaIngestaNativaNombre());
            ResultSet ingestaNativaRs = ingestaNativaPstmt.executeQuery();
            int ingestaNativaId = 0;
            if (ingestaNativaRs.next()) {
                ingestaNativaId = ingestaNativaRs.getInt(1);
            }
            
            pstmt.setString(1, entity.getMaTipoHormiga());
            pstmt.setInt(2, sexoId);
            pstmt.setInt(3, provinciaId);
            pstmt.setInt(4, genoAlimentoId);
            pstmt.setInt(5, ingestaNativaId);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new MAException(e.getMessage(), getClass().getName(), "create()");
        }
    }

    @Override
    public List<MAHormigaDTO> readAll() throws Exception {
        List<MAHormigaDTO> lst = new ArrayList<>();
        String query = 
            "SELECT m.IdHormiga, m.TipoHormiga, s.Nombre AS SexoNombre, p.Nombre AS ProvinciaNombre, g.Nombre AS GenoAlimentoNombre, i.Nombre AS IngestaNativaNombre, m.Estado, m.FechaCreacion, m.FechaModificacion " +
            "FROM MAHormiga m " +
            "JOIN MACatalogo s ON m.IdCatalogoSexo = s.IdCatalogo " +
            "JOIN MACatalogo p ON m.IdCatalogoProvincia = p.IdCatalogo " +
            "JOIN MACatalogo g ON m.IdCatalogoGenoAlimento = g.IdCatalogo " +
            "JOIN MACatalogo i ON m.IdCatalogoIngestaNativa = i.IdCatalogo " +
            "WHERE m.Estado = 'A'";
        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                MAHormigaDTO m = new MAHormigaDTO(
                    rs.getInt(1), // IdHormiga
                    rs.getString(2), // TipoHormiga
                    rs.getString(3), // SexoNombre
                    rs.getString(4), // ProvinciaNombre
                    rs.getString(5), // GenoAlimentoNombre
                    rs.getString(6), // IngestaNativaNombre
                    rs.getString(7), // Estado
                    rs.getString(8), // FechaCreacion
                    rs.getString(9)  // FechaModificacion
                );
                lst.add(m);
            }
        } catch (SQLException e) {
            throw new MAException(e.getMessage(), getClass().getName(), "readAll()");
        }
        return lst;
    }

    @Override
    public boolean update(MAHormigaDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = "UPDATE MAHormiga SET TipoHormiga = ?, IdCatalogoSexo = ?, IdCatalogoProvincia = ?, IdCatalogoGenoAlimento = ?, IdCatalogoIngestaNativa = ?, Estado = ?, FechaModificacion = ? WHERE IdHormiga = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getMaIdHormiga());
            pstmt.setString(2, entity.getMaSexoNombre());
            pstmt.setString(3, entity.getMaProvinciaNombre());
            pstmt.setString(4, entity.getMaGenoAlimentoNombre());
            pstmt.setString(5, entity.getMaIngestaNativaNombre());
            pstmt.setString(6, dtf.format(now).toString());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new MAException(e.getMessage(), getClass().getName(), "update()");
        }
    }

    @Override
    public boolean delete(int id) throws Exception {
        String query = "UPDATE MAHormiga SET Estado = 'X' WHERE IdHormiga = ?";
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
    public MAHormigaDTO readBy(Integer id) throws Exception {
        MAHormigaDTO m = new MAHormigaDTO();
        String query = 
            "SELECT m.IdHormiga, m.TipoHormiga, s.Nombre AS SexoNombre, p.Nombre AS ProvinciaNombre, g.Nombre AS GenoAlimentoNombre, i.Nombre AS IngestaNativaNombre, m.Estado, m.FechaCreacion, m.FechaModificacion " +
            "FROM MAHormiga m " +
            "JOIN MACatalogo s ON m.IdCatalogoSexo = s.IdCatalogo " +
            "JOIN MACatalogo p ON m.IdCatalogoProvincia = p.IdCatalogo " +
            "JOIN MACatalogo g ON m.IdCatalogoGenoAlimento = g.IdCatalogo " +
            "JOIN MACatalogo i ON m.IdCatalogoIngestaNativa = i.IdCatalogo " +
            "WHERE m.IdHormiga = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                m = new MAHormigaDTO(
                    rs.getInt(1), // IdHormiga
                    rs.getString(2), // TipoHormiga
                    rs.getString(3), // SexoNombre
                    rs.getString(4), // ProvinciaNombre
                    rs.getString(5), // GenoAlimentoNombre
                    rs.getString(6), // IngestaNativaNombre
                    rs.getString(7), // Estado
                    rs.getString(8), // FechaCreacion
                    rs.getString(9)  // FechaModificacion
                );
            }
        } catch (SQLException e) {
            throw new MAException(e.getMessage(), getClass().getName(), "readBy()");
        }
        return m;
    }
}

