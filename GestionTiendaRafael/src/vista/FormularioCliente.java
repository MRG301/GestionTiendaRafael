package vista;

import dao.implementacion.ClienteDAOImplementacion;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import modelo.entidades.Cliente;
import modelo.entidades.Direccion;
import modelo.entidades.Persona;
import modelo.enums.TipoCliente;
import util.Sesion;

public class FormularioCliente extends javax.swing.JFrame {

    private GestionCliente gestionCliente;  // Asegurarse de que esta referencia se asigne correctamente.
    private Cliente cliente;
    private ClienteDAOImplementacion clienteDAO;

    // Constructor corregido para aceptar parámetros
    public FormularioCliente(GestionCliente gestionCliente, Cliente cliente) {
        this.gestionCliente = gestionCliente;  // Corregido para asignar la referencia correctamente.
        this.cliente = cliente;
        this.clienteDAO = new ClienteDAOImplementacion();
        initComponents();
        setLocationRelativeTo(null); // Centrar ventana

        if (cliente != null) {
            lblTitulo.setText("Editar Cliente");
            cargarDatosCliente();
        } else {
            lblTitulo.setText("Agregar Cliente");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        txtApellidoCliente = new javax.swing.JTextField();
        txtCalleCliente = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNumeroCliente = new javax.swing.JTextField();
        txtTelefonoCliente = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtEmailCliente = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCiudadCliente = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtEstadoCliente = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCpCliente1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(194, 24, -1, -1));

        jLabel1.setText("Nombre:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, -1));

        jLabel2.setText("Calle:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, -1, -1));

        jLabel3.setText("C.P:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, -1));
        getContentPane().add(txtNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 71, -1));
        getContentPane().add(txtApellidoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 71, -1));
        getContentPane().add(txtCalleCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 71, -1));

        jLabel5.setText("Apellido:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));

        jLabel6.setText("Numero");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, -1, -1));
        getContentPane().add(txtNumeroCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, 71, -1));
        getContentPane().add(txtTelefonoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, 71, -1));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, -1, -1));

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 250, -1, -1));

        jLabel4.setText("Telefono:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, -1, -1));
        getContentPane().add(txtEmailCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, 70, -1));

        jLabel8.setText("Ciudad");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, -1, -1));

        txtCiudadCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCiudadClienteActionPerformed(evt);
            }
        });
        getContentPane().add(txtCiudadCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 130, 70, -1));

        jLabel9.setText("Estado:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, 40, -1));
        getContentPane().add(txtEstadoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 170, 70, -1));

        jLabel7.setText("Email:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, -1, -1));
        getContentPane().add(txtCpCliente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 70, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed
    private void cargarDatosCliente() {
        Persona persona = cliente.getDatosPersonales();
        Direccion direccion = persona.getDireccion();

        txtNombreCliente.setText(persona.getNombre());
        txtApellidoCliente.setText(persona.getApellido());
        txtEmailCliente.setText(persona.getEmail());
        txtTelefonoCliente.setText(persona.getTelefono());
        txtCalleCliente.setText(direccion.getCalle());
        txtNumeroCliente.setText(direccion.getNumero());
        txtCiudadCliente.setText(direccion.getCiudad());
        txtCpCliente1.setText(direccion.getCodigoPostal());
        txtEstadoCliente.setText(direccion.getEstado());
    }
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (txtNombreCliente.getText().trim().isEmpty() || txtApellidoCliente.getText().trim().isEmpty()
                || txtEmailCliente.getText().trim().isEmpty() || txtTelefonoCliente.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Direccion direccion = new Direccion(
                txtCalleCliente.getText().trim(),
                txtNumeroCliente.getText().trim(),
                txtCiudadCliente.getText().trim(),
                txtCpCliente1.getText().trim(),
                txtEstadoCliente.getText().trim()
        );

        Persona datosPersonales = new Persona(
                0, // ID se asignará en la base de datos
                txtNombreCliente.getText().trim(),
                txtApellidoCliente.getText().trim(),
                txtEmailCliente.getText().trim(),
                txtTelefonoCliente.getText().trim(),
                direccion
        );

        if (cliente == null) {
            TipoCliente tipoCliente = TipoCliente.REGULAR; // Asumiendo un tipo de cliente por defecto
            LocalDate fechaUltimaActualizacion = LocalDate.now();

            Cliente nuevoCliente = new Cliente(
                    0, // ID se asignará en la base de datos
                    datosPersonales,
                    tipoCliente,
                    fechaUltimaActualizacion
            );

            try {
                clienteDAO.agregarCliente(nuevoCliente);
                JOptionPane.showMessageDialog(this, "Cliente agregado exitosamente.");
                if (gestionCliente != null) {
                    gestionCliente.cargarDatosTabla();
                }
                dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al agregar el cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            cliente.getDatosPersonales().setNombre(txtNombreCliente.getText().trim());
            cliente.getDatosPersonales().setApellido(txtApellidoCliente.getText().trim());
            cliente.getDatosPersonales().setEmail(txtEmailCliente.getText().trim());
            cliente.getDatosPersonales().setTelefono(txtTelefonoCliente.getText().trim());
            cliente.getDatosPersonales().getDireccion().setCalle(txtCalleCliente.getText().trim());
            cliente.getDatosPersonales().getDireccion().setNumero(txtNumeroCliente.getText().trim());
            cliente.getDatosPersonales().getDireccion().setCiudad(txtCiudadCliente.getText().trim());
            cliente.getDatosPersonales().getDireccion().setCodigoPostal(txtCpCliente1.getText().trim());
            cliente.getDatosPersonales().getDireccion().setEstado(txtEstadoCliente.getText().trim());

            try {
                clienteDAO.actualizarCliente(cliente);
                JOptionPane.showMessageDialog(this, "Cliente actualizado exitosamente.");
                if (gestionCliente != null) {
                    gestionCliente.cargarDatosTabla();
                }
                dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al actualizar el cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtCiudadClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCiudadClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCiudadClienteActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GestionCliente gestionCliente = new GestionCliente();
                new FormularioCliente(gestionCliente, null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtApellidoCliente;
    private javax.swing.JTextField txtCalleCliente;
    private javax.swing.JTextField txtCiudadCliente;
    private javax.swing.JTextField txtCpCliente1;
    private javax.swing.JTextField txtEmailCliente;
    private javax.swing.JTextField txtEstadoCliente;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNumeroCliente;
    private javax.swing.JTextField txtTelefonoCliente;
    // End of variables declaration//GEN-END:variables
}
