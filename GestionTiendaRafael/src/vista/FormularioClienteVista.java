package vista;

import dao.implementacion.ClienteDAOImplementacion;
import java.time.LocalDate;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelo.entidades.Cliente;
import modelo.entidades.Direccion;
import modelo.entidades.Persona;
import modelo.enums.TipoCliente;

public class FormularioClienteVista extends javax.swing.JDialog {

    private final JFrame parent;
    private final Cliente cliente;
    private final ClienteDAOImplementacion clienteDAO;
    private Cliente clienteRegistrado;

    // Constructor corregido para aceptar parámetros
    public FormularioClienteVista(JFrame parent, Cliente cliente) {
        super(parent, true); // Hacerlo modal respecto al padre
        this.parent = parent;
        this.cliente = cliente;
        this.clienteDAO = new ClienteDAOImplementacion();
        initComponents();
        setLocationRelativeTo(parent); // Centrar ventana

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
        lblTitulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Nombre:");

        jLabel2.setText("Calle:");

        jLabel3.setText("C.P:");

        jLabel5.setText("Apellido:");

        jLabel6.setText("Numero");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel4.setText("Telefono:");

        jLabel8.setText("Ciudad");

        txtCiudadCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCiudadClienteActionPerformed(evt);
            }
        });

        jLabel9.setText("Estado:");

        jLabel7.setText("Email:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(13, 13, 13)
                                .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jLabel6)
                                .addGap(37, 37, 37)
                                .addComponent(txtNumeroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(13, 13, 13)
                                .addComponent(txtApellidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jLabel4)
                                .addGap(31, 31, 31)
                                .addComponent(txtTelefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(33, 33, 33)
                                .addComponent(txtCalleCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jLabel8)
                                .addGap(43, 43, 43)
                                .addComponent(txtCiudadCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(41, 41, 41)
                                .addComponent(txtCpCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(txtEstadoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(29, 29, 29)
                                .addComponent(txtEmailCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(btnCancelar)
                                .addGap(56, 56, 56)
                                .addComponent(btnGuardar))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addComponent(lblTitulo)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtNumeroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtApellidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtTelefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtCalleCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtCiudadCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtCpCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtEstadoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtEmailCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancelar)
                    .addComponent(btnGuardar))
                .addGap(31, 31, 31))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed
    public Cliente getCliente() {
        return clienteRegistrado;
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
            // Agregar Cliente
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
                clienteRegistrado = nuevoCliente; // Asignar al cliente registrado
                dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al agregar el cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Actualizar Cliente
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
                clienteRegistrado = cliente; // Asignar al cliente actualizado
                cargarDatosCliente();
                dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al actualizar el cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtCiudadClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCiudadClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCiudadClienteActionPerformed
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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormularioClienteVista dialog = new FormularioClienteVista(new javax.swing.JFrame(), null); // Pasa 'null' para agregar un nuevo cliente
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
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
