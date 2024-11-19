package vista;

import dao.implementacion.EmpleadoDAOImplementacion;
import dao.implementacion.UsuarioDAOImplementacion;
import dao.interfaces.UsuarioDAO;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import modelo.entidades.Direccion;
import modelo.entidades.Empleado;
import modelo.entidades.Persona;
import util.Sesion;
import modelo.entidades.Usuario;
import modelo.enums.Rol;

public class GestionEmpleados extends javax.swing.JFrame {

    private EmpleadoDAOImplementacion empleadoDAO;
    private Usuario usuarioActual;
    private UsuarioDAO usuarioDAO;

    public GestionEmpleados() {
        // Obtener el usuario actual de la sesión
        usuarioActual = Sesion.getUsuarioActual();
        this.usuarioDAO = new UsuarioDAOImplementacion();

        // Verificar permisos
        if (usuarioActual == null
                || (!usuarioActual.getRoles().contains(Rol.GESTIONEMPLEADOS)
                && !usuarioActual.getRoles().contains(Rol.SUPERADMIN))) {
            JOptionPane.showMessageDialog(this,
                    "No tienes permisos para acceder a esta vista.",
                    "Acceso Denegado",
                    JOptionPane.ERROR_MESSAGE);
            dispose(); // Cerrar la ventana actual
            return;
        }

        empleadoDAO = new EmpleadoDAOImplementacion();
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Gestión de Empleados - " + usuarioActual.getUsername());
        cargarDatosTabla();

        // Añadir listener al campo de búsqueda
        txtBuscarEmpleado.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                buscarEmpleado();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                buscarEmpleado();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                buscarEmpleado();
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnAgregarEmp = new javax.swing.JButton();
        btnEditarEmp = new javax.swing.JButton();
        btnEliminarEmp = new javax.swing.JButton();
        btnCancelarEmp = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTablaEmp = new javax.swing.JTable();
        btnRegresar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtBuscarEmpleado = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Gestion de Empleados");

        btnAgregarEmp.setText("Agregar");
        btnAgregarEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarEmpActionPerformed(evt);
            }
        });

        btnEditarEmp.setText("Editar");
        btnEditarEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarEmpActionPerformed(evt);
            }
        });

        btnEliminarEmp.setText("Eliminar");
        btnEliminarEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarEmpActionPerformed(evt);
            }
        });

        btnCancelarEmp.setText("Cancelar");
        btnCancelarEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarEmpActionPerformed(evt);
            }
        });

        tblTablaEmp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Apellido", "Email", "Telefono", "Direccion", "Puesto", "Salario"
            }
        ));
        jScrollPane1.setViewportView(tblTablaEmp);

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        jLabel2.setText("Buscar Empleado:");

        txtBuscarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarEmpleadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(btnAgregarEmp)
                .addGap(18, 18, 18)
                .addComponent(btnEditarEmp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelarEmp)
                .addGap(18, 18, 18)
                .addComponent(btnEliminarEmp)
                .addGap(21, 21, 21))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(204, 204, 204)
                        .addComponent(btnRegresar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBuscarEmpleado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(179, 179, 179)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtBuscarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarEmp)
                    .addComponent(btnEditarEmp)
                    .addComponent(btnCancelarEmp)
                    .addComponent(btnEliminarEmp))
                .addGap(18, 18, 18)
                .addComponent(btnRegresar)
                .addGap(34, 34, 34))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarEmpActionPerformed
        int filaSeleccionada = tblTablaEmp.getSelectedRow();
        if (filaSeleccionada != -1) {
            int idEmpleado = (int) tblTablaEmp.getValueAt(filaSeleccionada, 0);
            try {
                Empleado empleado = empleadoDAO.obtenerEmpleadoPorId(idEmpleado);
                if (empleado != null) {
                    JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Editar Empleado", true);
                    FormularioEmp formularioEmp = new FormularioEmp(this, empleado);
                    dialog.add(formularioEmp);
                    dialog.pack();
                    dialog.setLocationRelativeTo(this);
                    dialog.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo encontrar el empleado seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al obtener el empleado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un empleado para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarEmpActionPerformed

    private void btnCancelarEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarEmpActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarEmpActionPerformed

    private void btnEliminarEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarEmpActionPerformed
        int filaSeleccionada = tblTablaEmp.getSelectedRow();
        if (filaSeleccionada != -1) {
            int empleadoId = (int) tblTablaEmp.getValueAt(filaSeleccionada, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar este empleado?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    empleadoDAO.eliminarEmpleado(empleadoId);
                    JOptionPane.showMessageDialog(this, "Empleado eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error al eliminar el empleado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    // Cargar datos de la tabla independientemente del resultado
                    cargarDatosTabla();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un empleado para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarEmpActionPerformed

    private void btnAgregarEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarEmpActionPerformed

        JFrame frame = new JFrame("Agregar Empleado");
        FormularioEmp formularioEmpleado = new FormularioEmp(GestionEmpleados.this, null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(formularioEmpleado); // Agregar FormularioEmp al contenido del JFrame
        frame.pack();
        frame.setLocationRelativeTo(null); // Centrar la ventana
        frame.setVisible(true);
    }//GEN-LAST:event_btnAgregarEmpActionPerformed

    private void txtBuscarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarEmpleadoActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed

        // Abrir AdministracionVista
        SwingUtilities.invokeLater(() -> {
            new AdministracionVista().setVisible(true);
        });
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestionEmpleados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarEmp;
    private javax.swing.JButton btnCancelarEmp;
    private javax.swing.JButton btnEditarEmp;
    private javax.swing.JButton btnEliminarEmp;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblTablaEmp;
    private javax.swing.JTextField txtBuscarEmpleado;
    // End of variables declaration//GEN-END:variables

    public void cargarDatosTabla() {
        DefaultTableModel model = (DefaultTableModel) tblTablaEmp.getModel();
        model.setRowCount(0);

        try {
            List<Empleado> empleados = empleadoDAO.obtenerTodosLosEmpleados();
            for (Empleado empleado : empleados) {
                Persona persona = empleado.getDatosPersonales();
                Direccion direccion = persona.getDireccion();
                Usuario usuario = empleado.getUsuario();
                Object[] fila = {
                    empleado.getId(),
                    persona.getNombre(),
                    persona.getApellido(),
                    persona.getEmail(),
                    persona.getTelefono(),
                    direccion.getCalle() + " " + direccion.getNumero() + ", " + direccion.getCiudad() + ", " + direccion.getCodigoPostal() + ", " + direccion.getEstado(),
                    empleado.getPuesto(),
                    empleado.getSalario(),
                    usuario.getUsername() // Incluye el nombre de usuario si lo deseas
                };
                model.addRow(fila);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarEmpleado() {
        String termino = txtBuscarEmpleado.getText().trim();
        if (termino.isEmpty()) {
            cargarDatosTabla(); // Cargar todos los empleados si el término está vacío
            return;
        }

        DefaultTableModel model = (DefaultTableModel) tblTablaEmp.getModel();
        model.setRowCount(0); // Limpiar la tabla

        try {
            List<Empleado> empleados;
            if (esNumero(termino)) {
                empleados = empleadoDAO.buscarEmpleadosPorId(Integer.parseInt(termino));
            } else {
                empleados = empleadoDAO.buscarEmpleadosPorNombre(termino);
            }

            for (Empleado empleado : empleados) {
                Persona persona = empleado.getDatosPersonales();
                Direccion direccion = persona.getDireccion();
                Object[] fila = {
                    empleado.getId(),
                    persona.getNombre(),
                    persona.getApellido(),
                    persona.getEmail(),
                    persona.getTelefono(),
                    direccion.getCalle() + " " + direccion.getNumero() + ", " + direccion.getCiudad() + ", " + direccion.getCodigoPostal() + ", " + direccion.getEstado(),
                    empleado.getPuesto(),
                    empleado.getSalario(),};
                model.addRow(fila);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al buscar empleados: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean esNumero(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
