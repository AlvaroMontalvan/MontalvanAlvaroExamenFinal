import MAUserInterface.EcuaFaunaApp;
import javax.swing.*;

import MABusinessLogic.MAAlimentoBL;
import MABusinessLogic.MAHormigaBL;
import MADataAccess.MADTO.MAAlimentoDTO;
import MADataAccess.MADTO.MAHormigaDTO;


public class App {
    public static void main(String[] args) throws Exception {
        

        try {
            //    MAHormigaBL hBl = new MAHormigaBL();
            //   hBl.add(new MAHormigaDTO(0, "agresiva", "fdfd",
            //    "quidto", "fdf",  "hgh", null, null, null  ));
            //   for(MAHormigaDTO h :hBl.getAll())
            //       System.out.println(h.toString());

            // MAHormigaBL maHormigaBL = new MAHormigaBL();
            
            // IDs de los registros que se desean eliminar
            // int[] idsParaEliminar = {0, 7, 8};
            
            // Eliminar los registros uno por uno
            // for (int id : idsParaEliminar) {
            //     boolean resultado = maHormigaBL.delete(id);
            //     if (resultado) {
            //         System.out.println("Registro con ID " + id + " eliminado exitosamente.");
            //     } else {
            //         System.out.println("Error al eliminar el registro con ID " + id + ".");
            //     }
            // }
            
            // Imprimir todos los registros restantes para verificar la eliminación
            // for (MAHormigaDTO hormiga : maHormigaBL.getAll()) {
            //     System.out.println(hormiga.toString());
            // }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // try {
        //     MAAlimentoBL aBl = new MAAlimentoBL();
        //     aBl.add(new MAAlimentoDTO(0, "Alimento 1", "Descr", null, null, null));
        //     for(MAAlimentoDTO a : aBl.getAll())
        //         System.out.println(a.toString());
            


        // } catch (Exception e) {
        // }
        

        SwingUtilities.invokeLater(() -> new EcuaFaunaApp().setVisible(true));
    }
}
