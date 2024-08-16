package MAUserInterface;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;
import MABusinessLogic.MAAlimentoBL;
import MABusinessLogic.MAHormigaBL;
import MADataAccess.MADTO.MAAlimentoDTO;
import MADataAccess.MADTO.MAHormigaDTO;

public class EcuaFaunaApp extends JFrame {

    // Variables estáticas globales
    private static String cedula = "1725250342";
    private static String nombres = "Alvaro Gonzalo Montalvan Rios";

    // Panel para mostrar la tabla
    private JPanel pnlTabla;
    private JComboBox<String> comboGenoAlimento;
    private JComboBox<String> comboIngestaNativa;
    private MAAlimentoBL alimentoBL;
    private MAHormigaBL maHormigaBL;

    // Constructor de la interfaz
    public EcuaFaunaApp() {
        alimentoBL = new MAAlimentoBL();
        maHormigaBL = new MAHormigaBL();

        setTitle("EcuaFauna 2K24A");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel principal (con BoxLayout para seccionar horizontalmente)
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Sección 1: Panel superior para cédula y nombres (centralizado)
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new GridLayout(2, 2, 10, 10));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Espaciado

        panelSuperior.add(new JLabel("Cédula:", SwingConstants.CENTER));
        JTextField cedulaField = new JTextField(cedula);
        cedulaField.setEditable(false);
        cedulaField.setHorizontalAlignment(JTextField.CENTER);
        panelSuperior.add(cedulaField);

        panelSuperior.add(new JLabel("Nombres:", SwingConstants.CENTER));
        JTextField nombresField = new JTextField(nombres);
        nombresField.setEditable(false);
        nombresField.setHorizontalAlignment(JTextField.CENTER);
        panelSuperior.add(nombresField);

        // Sección 2: Panel central para el botón, logo y tabla
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BorderLayout(10, 10));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Espaciado

        // Subpanel para la imagen y el botón en la parte superior del panel central
        JPanel subPanelCentral = new JPanel();
        subPanelCentral.setLayout(new BorderLayout());

        JLabel logoHormiga = new JLabel(new ImageIcon("hormiga.png")); // Cambia el path a la imagen correcta
        subPanelCentral.add(logoHormiga, BorderLayout.WEST);

        JButton crearHormigaBtn = new JButton("Crear Hormiga Larva");
        crearHormigaBtn.setPreferredSize(new Dimension(150, 30)); // Ajustar tamaño del botón
        subPanelCentral.add(crearHormigaBtn, BorderLayout.EAST);

        panelCentral.add(subPanelCentral, BorderLayout.NORTH);

        // Sección para la tabla
        pnlTabla = new JPanel();
        pnlTabla.setLayout(new BorderLayout());
        panelCentral.add(pnlTabla, BorderLayout.CENTER);

        // Sección 3: Panel inferior para botones y combobox (con bordes redondeados)
        JPanel panelInferior = new JPanel(new GridLayout(3, 2, 10, 10));
        panelInferior.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Espaciado

        comboGenoAlimento = new JComboBox<>();
        comboGenoAlimento.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));

        comboIngestaNativa = new JComboBox<>();
        comboIngestaNativa.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));

        JButton alimentarGenoAlimentoBtn = new JButton("Alimentar con Genoalimento");
        alimentarGenoAlimentoBtn.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));

        JButton alimentarIngestaNativaBtn = new JButton("Alimentar con Ingesta Nativa");
        alimentarIngestaNativaBtn.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));

        JButton eliminarBtn = new JButton("Eliminar");
        eliminarBtn.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));

        JButton guardarBtn = new JButton("Guardar");
        guardarBtn.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));

        // Añadir componentes a la sección inferior
        panelInferior.add(comboGenoAlimento);
        panelInferior.add(alimentarGenoAlimentoBtn);
        panelInferior.add(comboIngestaNativa);
        panelInferior.add(alimentarIngestaNativaBtn);
        panelInferior.add(eliminarBtn);
        panelInferior.add(guardarBtn);

        // Añadir las secciones al panel principal
        panelPrincipal.add(panelSuperior);
        panelPrincipal.add(panelCentral);
        panelPrincipal.add(panelInferior);

        // Añadir el panel principal a la ventana
        add(panelPrincipal, BorderLayout.CENTER);

        // Cargar la tabla de hormigas y los datos de alimentos al iniciar la interfaz
        try {
            showHormigaTable();
            loadAlimentos();
        } catch (Exception e) {
            e.printStackTrace();
        }

        

        // Añadir la funcionalidad al botón "Crear Hormiga Larva"
        crearHormigaBtn.addActionListener(e -> {
            int respuesta = JOptionPane.showConfirmDialog(null, 
                "¿Está seguro de crear una Hormiga Larva?", 
                "Confirmar creación", 
                JOptionPane.YES_NO_OPTION);
        
            if (respuesta == JOptionPane.YES_OPTION) {
                // Crear un nuevo objeto MAHormigaDTO con valores por defecto
                MAHormigaDTO nuevaHormiga = new MAHormigaDTO();
                nuevaHormiga.setMaTipoHormiga("Larva");
                nuevaHormiga.setMaIdHormiga(generarNuevoId()); // Genera un nuevo ID según el orden
        
                // Crear campos para ingresar sexo y provincia
                JTextField sexoField = new JTextField();
                JTextField provinciaField = new JTextField();
        
                // Crear el panel con los campos de entrada
                JPanel panel = new JPanel(new GridLayout(2, 2));
                panel.add(new JLabel("Sexo:"));
                panel.add(sexoField);
                panel.add(new JLabel("Provincia:"));
                panel.add(provinciaField);
        
                // Mostrar el diálogo para ingresar los datos
                int result = JOptionPane.showConfirmDialog(null, panel, "Ingresar Datos de Hormiga", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    // Asignar los valores ingresados a la nueva hormiga
                    nuevaHormiga.setMaSexoNombre(sexoField.getText());
                    nuevaHormiga.setMaProvinciaNombre(provinciaField.getText());
        
                    // Mostrar la hormiga en la consola
                    System.out.println("Hormiga creada: " + nuevaHormiga.toString());
        
                    try {
                        // Crear una instancia de MAHormigaBL
                        MAHormigaBL hBl = new MAHormigaBL();
        
                        // Insertar la nueva hormiga
                        boolean exito = hBl.add(new MAHormigaDTO(
                            0, // Id por defecto (se generará automáticamente)
                            "Larva", // Tipo de hormiga por defecto
                            sexoField.getText(), // Sexo ingresado por el usuario
                            provinciaField.getText(), // Provincia ingresada por el usuario
                            null, null, null, null, null // Otros campos nulos por defecto
                        ));
        
                        if (exito) {
                            // Mostrar todas las hormigas en la consola
                            for (MAHormigaDTO h : hBl.getAll()) {
                                System.out.println(h.toString());
                            }
                            
                            // Actualizar la tabla
                            showHormigaTable();
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al crear la hormiga", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Ocurrió un error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        
        

    }

    // Método para generar un nuevo ID
    private int generarNuevoId() {
        int ultimoId = 0;
        try {
            List<MAHormigaDTO> hormigas = maHormigaBL.getAll();
            for (MAHormigaDTO hormiga : hormigas) {
                if (hormiga.getMaIdHormiga() > ultimoId) {
                    ultimoId = hormiga.getMaIdHormiga();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ultimoId + 1;
    }

    private void showHormigaTable() throws Exception {
        // Obtener datos de la base de datos
        List<MAHormigaDTO> hormigas = maHormigaBL.getAll(); // Asumiendo que tienes un BL para obtener los datos
    
        String[] header = {"IdHormiga", "TipoHormiga", "Sexo", "Provincia", "Geno Alimento", "Ingesta Nativa"};
        Object[][] data = new Object[hormigas.size()][header.length];
        int index = 0;
    
        for (MAHormigaDTO hormiga : hormigas) {
            data[index][0] = hormiga.getMaIdHormiga(); // IdHormiga
            data[index][1] = hormiga.getMaTipoHormiga(); // TipoHormiga
            data[index][2] = hormiga.getMaSexoNombre(); // Sexo
            data[index][3] = hormiga.getMaProvinciaNombre(); // Provincia
            data[index][4] = hormiga.getMaGenoAlimentoNombre(); // Geno Alimento
            data[index][5] = hormiga.getMaIngestaNativaNombre(); // Ingesta Nativa
            index++;
        }
    
        JTable table = new JTable(data, header);
        table.setShowHorizontalLines(true);
        table.setGridColor(Color.lightGray);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);
        table.setPreferredScrollableViewportSize(new Dimension(700, 150)); // Ajustar tamaño según número de columnas
        table.setFillsViewportHeight(true);
    
        pnlTabla.removeAll(); // Limpiar el panel para refrescar
        JScrollPane scrollPane = new JScrollPane(table);
        pnlTabla.add(scrollPane, BorderLayout.CENTER);
    
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int col = 0;
                int row = table.getSelectedRow();
                if (row != -1) {
                    String strIdHormiga = table.getModel().getValueAt(row, col).toString();
                    int idHormiga = Integer.parseInt(strIdHormiga);
                    try {
                        loadCombosFromHormiga(idHormiga);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    
        pnlTabla.revalidate(); // Refrescar el panel para mostrar la nueva tabla
        pnlTabla.repaint();
    }
    
    // Métodos adicionales para cargar alimentos y otros componentes

    private void loadAlimentos() throws Exception {
        List<MAAlimentoDTO> alimentos = alimentoBL.getAll(); // Asumiendo que tienes un BL para obtener los datos

        comboGenoAlimento.removeAllItems();
        comboIngestaNativa.removeAllItems();

        for (MAAlimentoDTO alimento : alimentos) {
            comboGenoAlimento.addItem(alimento.getMaGenoAlimentoNombre());
            comboIngestaNativa.addItem(alimento.getMaIngestaNativaNombre());
        }
    }

    private void loadCombosFromHormiga(int idHormiga) throws Exception {
        MAHormigaDTO hormiga = maHormigaBL.getBy(idHormiga); // Asumiendo que tienes un método getById en el BL

        // Cargar los combos correspondientes basados en los datos de la hormiga seleccionada
        comboGenoAlimento.setSelectedItem(hormiga.getMaGenoAlimentoNombre());
        comboIngestaNativa.setSelectedItem(hormiga.getMaIngestaNativaNombre());
    }

    
}




