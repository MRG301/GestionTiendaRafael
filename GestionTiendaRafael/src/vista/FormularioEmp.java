package vista;

import dao.implementacion.DireccionDAOImplementacion;
import dao.implementacion.EmpleadoDAOImplementacion;
import dao.implementacion.PersonaDAOImplementacion;
import dao.implementacion.UsuarioDAOImplementacion;
import dao.interfaces.DireccionDAO;
import dao.interfaces.PersonaDAO;
import dao.interfaces.UsuarioDAO;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import modelo.entidades.Direccion;
import modelo.entidades.Empleado;
import modelo.entidades.Persona;
import modelo.entidades.Usuario;
import modelo.enums.Rol;
import util.Sesion;

public class FormularioEmp extends javax.swing.JPanel {

    private final GestionEmpleados gestionEmpleados;
    private final Empleado empleado; // Puede ser null para agregar
    private final EmpleadoDAOImplementacion empleadoDAO;

    private final UsuarioDAO usuarioDAO;
    private final PersonaDAO personaDAO;
    private final DireccionDAO direccionDAO;

    public FormularioEmp(GestionEmpleados gestionEmpleados, Empleado empleado) {
        this.gestionEmpleados = gestionEmpleados;
        this.empleado = empleado;
        this.empleadoDAO = new EmpleadoDAOImplementacion();
        this.direccionDAO = new DireccionDAOImplementacion();
        this.personaDAO = new PersonaDAOImplementacion();
        this.usuarioDAO = new UsuarioDAOImplementacion();
        initComponents();
        cargarPuestos();

        if (empleado != null) {
            cargarDatosEmpleado();
            lblTitulo.setText("Editar Empleado");
        } else {
            lblTitulo.setText("Agregar Empleado");
        }
    }

    private void cargarPuestos() {
        Usuario usuarioActual = Sesion.getUsuarioActual();
        cmbPuesto.removeAllItems();
        for (Rol rol : Rol.values()) {
            if (usuarioActual.getRoles().contains(Rol.SUPERADMIN) || rol != Rol.SUPERADMIN) {
                cmbPuesto.addItem(rol);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        txtSalario = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cmbPuesto = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtEstadoEmp = new javax.swing.JTextField();
        txtEmailEmp = new javax.swing.JTextField();
        txtNumeroEmp = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        txtCalleEmp = new javax.swing.JTextField();
        txtNombreEmp = new javax.swing.JTextField();
        txtTelefonoEmp = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtCodigoEmp = new javax.swing.JTextField();
        txtCiudadEmp = new javax.swing.JTextField();
        txtApellidoEmp = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtUsuarioEmp = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtContrasenaEmp = new javax.swing.JPasswordField();

        jLabel11.setText("Estado");

        jLabel13.setText("Puesto:");

        jLabel3.setText("Nombre:");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        txtSalario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSalarioActionPerformed(evt);
            }
        });

        jLabel12.setText("Salario");

        jLabel9.setText("Ciudad");

        cmbPuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPuestoActionPerformed(evt);
            }
        });

        jLabel8.setText("Numero");

        jLabel7.setText("Calle");

        jLabel10.setText("C.P:");

        jLabel5.setText("Email:");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        txtNombreEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreEmpActionPerformed(evt);
            }
        });

        jLabel6.setText("Telefono");

        jLabel4.setText("Apellido:");

        jLabel1.setText("Usuario");

        txtUsuarioEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioEmpActionPerformed(evt);
            }
        });

        jLabel2.setText("Contraseña");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addComponent(lblTitulo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addComponent(btnCancelar))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(93, 93, 93)
                                .addComponent(btnGuardar))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(cmbPuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtTelefonoEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtEmailEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtApellidoEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(31, 31, 31)
                                        .addComponent(txtNombreEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(40, 40, 40)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCalleEmp, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                                    .addComponent(txtUsuarioEmp))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel2))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtNumeroEmp, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                            .addComponent(txtCiudadEmp)
                            .addComponent(txtCodigoEmp)
                            .addComponent(txtEstadoEmp)
                            .addComponent(txtSalario)
                            .addComponent(txtContrasenaEmp))))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8)
                    .addComponent(txtNombreEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumeroEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel9)
                    .addComponent(txtApellidoEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCiudadEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel10)
                    .addComponent(txtEmailEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigoEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel11)
                    .addComponent(txtTelefonoEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEstadoEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCalleEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtSalario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUsuarioEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtContrasenaEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbPuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnGuardar))
                .addGap(19, 19, 19))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        java.awt.Window parentWindow = SwingUtilities.getWindowAncestor(this);
        if (parentWindow != null) {
            parentWindow.dispose(); // Cierra la ventana contenedora
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtSalarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSalarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSalarioActionPerformed

    private void txtNombreEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreEmpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreEmpActionPerformed
    private void cargarDatosEmpleado() {
        if (empleado != null) {
            // Cargar datos personales
            txtNombreEmp.setText(empleado.getDatosPersonales().getNombre());
            txtApellidoEmp.setText(empleado.getDatosPersonales().getApellido());
            txtEmailEmp.setText(empleado.getDatosPersonales().getEmail());
            txtTelefonoEmp.setText(empleado.getDatosPersonales().getTelefono());

            // Cargar datos de dirección
            Direccion direccion = empleado.getDatosPersonales().getDireccion();
            txtCalleEmp.setText(direccion.getCalle());
            txtNumeroEmp.setText(direccion.getNumero());
            txtCiudadEmp.setText(direccion.getCiudad());
            txtCodigoEmp.setText(direccion.getCodigoPostal());
            txtEstadoEmp.setText(direccion.getEstado());

            // Cargar datos laborales
            cmbPuesto.setSelectedItem(empleado.getRol());
            txtSalario.setText(String.valueOf(empleado.getSalario()));

            // Cargar datos de usuario
            txtUsuarioEmp.setText(empleado.getUsuario().getUsername());
            // Por razones de seguridad, es mejor no mostrar la contraseña
            // txtContrasenaEmp.setText(""); // Dejar vacío

            // Si deseas permitir la edición de la contraseña, considera solicitar al usuario que ingrese una nueva
            // y solo actualizar la contraseña si el campo no está vacío
        }
    }
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        Rol rolSeleccionado = (Rol) cmbPuesto.getSelectedItem();

        // Validaciones de campos vacíos
        if (txtNombreEmp.getText().trim().isEmpty()
                || txtApellidoEmp.getText().trim().isEmpty()
                || txtEmailEmp.getText().trim().isEmpty()
                || txtTelefonoEmp.getText().trim().isEmpty()
                || txtCalleEmp.getText().trim().isEmpty()
                || txtNumeroEmp.getText().trim().isEmpty()
                || txtCiudadEmp.getText().trim().isEmpty()
                || txtCodigoEmp.getText().trim().isEmpty()
                || txtEstadoEmp.getText().trim().isEmpty()
                || txtSalario.getText().trim().isEmpty()
                || txtUsuarioEmp.getText().trim().isEmpty()
                || txtContrasenaEmp.getPassword().length == 0) {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar formato de salario
        double salario;
        try {
            salario = Double.parseDouble(txtSalario.getText().trim());
            if (salario < 0) {
                throw new NumberFormatException("El salario no puede ser negativo.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un salario válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener username y contraseña
        String username = txtUsuarioEmp.getText().trim();
        String password = new String(txtContrasenaEmp.getPassword()).trim();

        // Verificar si el username ya existe
        try {
            Usuario usuarioExistente = usuarioDAO.obtenerUsuarioPorUsername(username);
            if (usuarioExistente != null) {
                JOptionPane.showMessageDialog(this, "El nombre de usuario ya existe. Por favor, elige otro.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al verificar el nombre de usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear objeto Direccion
        Direccion direccion = new Direccion(
                txtCalleEmp.getText().trim(),
                txtNumeroEmp.getText().trim(),
                txtCiudadEmp.getText().trim(),
                txtCodigoEmp.getText().trim(),
                txtEstadoEmp.getText().trim()
        );

        // Crear objeto Persona
        Persona datosPersonales = new Persona(
                txtNombreEmp.getText().trim(),
                txtApellidoEmp.getText().trim(),
                txtEmailEmp.getText().trim(),
                txtTelefonoEmp.getText().trim(),
                direccion
        );

        // Crear objeto Usuario
        Usuario usuario = new Usuario(
                username,
                password, // La contraseña se encriptará en el método agregarUsuario
                Set.of(rolSeleccionado)
        );

        // Crear objeto Empleado
        Empleado nuevoEmpleado = new Empleado(
                datosPersonales,
                rolSeleccionado.name(),
                salario,
                rolSeleccionado,
                usuario
        );

        // Guardar el Empleado en la base de datos
        try {
            empleadoDAO.agregarEmpleado(nuevoEmpleado);
            JOptionPane.showMessageDialog(this, "Empleado agregado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            gestionEmpleados.cargarDatosTabla(); // Refrescar la tabla en la vista principal
            cerrarFormulario();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al agregar el empleado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed
    private void cerrarFormulario() {
        java.awt.Window parentWindow = SwingUtilities.getWindowAncestor(this);
        if (parentWindow != null) {
            parentWindow.dispose();
        }
    }
    private void cmbPuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPuestoActionPerformed

    }//GEN-LAST:event_cmbPuestoActionPerformed

    private void txtUsuarioEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioEmpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioEmpActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<modelo.enums.Rol> cmbPuesto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtApellidoEmp;
    private javax.swing.JTextField txtCalleEmp;
    private javax.swing.JTextField txtCiudadEmp;
    private javax.swing.JTextField txtCodigoEmp;
    private javax.swing.JPasswordField txtContrasenaEmp;
    private javax.swing.JTextField txtEmailEmp;
    private javax.swing.JTextField txtEstadoEmp;
    private javax.swing.JTextField txtNombreEmp;
    private javax.swing.JTextField txtNumeroEmp;
    private javax.swing.JTextField txtSalario;
    private javax.swing.JTextField txtTelefonoEmp;
    private javax.swing.JTextField txtUsuarioEmp;
    // End of variables declaration//GEN-END:variables
}
