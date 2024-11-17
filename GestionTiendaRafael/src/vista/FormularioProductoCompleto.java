package vista;

import dao.implementacion.ProductoDAOImplementacion;
import javax.swing.JOptionPane;
import modelo.entidades.Producto;
import modelo.enums.CategoriaTienda;

public class FormularioProductoCompleto extends javax.swing.JFrame {

    private GestionProducto gestionProducto;
    private Producto producto;
    private ProductoDAOImplementacion productoDAO;

    public FormularioProductoCompleto(GestionProducto gestionProducto, Producto producto) {
        this.gestionProducto = gestionProducto; // Asignar correctamente la referencia
        this.producto = producto;
        this.productoDAO = new ProductoDAOImplementacion();
        initComponents();
        setLocationRelativeTo(null); // Centrar ventana

        // Llenar el JComboBox de categorías
        cargarCategorias();

        if (producto != null) {
            lblTitulo.setText("Editar Producto");
            cargarDatosProducto();
            txtIdProducto.setEditable(false); // No permitir editar el ID al modificar un producto
        } else {
            lblTitulo.setText("Agregar Producto");
            txtIdProducto.setEditable(true); // Permitir ingresar el ID solo al agregar
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtIdProducto = new javax.swing.JTextField();
        txtNombreProducto = new javax.swing.JTextField();
        txtDescripcionProducto = new javax.swing.JTextField();
        txtPrecioProducto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtStockProducto = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnCancelarProducto = new javax.swing.JButton();
        btnGuardarProducto = new javax.swing.JButton();
        cmbCategoria = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Id:");

        jLabel2.setText("Nombre:");

        jLabel3.setText("Descripcion:");

        jLabel4.setText("Precio:");

        txtIdProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdProductoActionPerformed(evt);
            }
        });

        jLabel5.setText("Stock:");

        jLabel6.setText("Categoria");

        btnCancelarProducto.setText("Cancelar");
        btnCancelarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarProductoActionPerformed(evt);
            }
        });

        btnGuardarProducto.setText("Guardar");
        btnGuardarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCancelarProducto)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtDescripcionProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(4, 4, 4))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(4, 4, 4))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(4, 4, 4))))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(btnGuardarProducto))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPrecioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtStockProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtPrecioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtStockProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addComponent(btnGuardarProducto))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDescripcionProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(35, 35, 35)
                        .addComponent(btnCancelarProducto)))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void cargarDatosProducto() {
        // Verifica que 'producto' no sea null antes de intentar acceder a sus métodos
        if (producto != null) {
            txtIdProducto.setText(String.valueOf(producto.getIdProducto()));
            txtNombreProducto.setText(producto.getNombre());
            txtDescripcionProducto.setText(producto.getDescripcion());
            txtPrecioProducto.setText(String.valueOf(producto.getPrecio()));
            txtStockProducto.setText(String.valueOf(producto.getStock()));
            cmbCategoria.setSelectedItem(producto.getCategoria());
        }
    }
    private void btnGuardarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarProductoActionPerformed
        if (txtIdProducto.getText().trim().isEmpty() || txtNombreProducto.getText().trim().isEmpty()
                || txtDescripcionProducto.getText().trim().isEmpty()
                || txtPrecioProducto.getText().trim().isEmpty()
                || txtStockProducto.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificar si hay una categoría seleccionada
        if (cmbCategoria.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona una categoría para el producto.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar que el ID, precio y stock sean números válidos
        long idProducto;
        double precio;
        int stock;
        try {
            idProducto = Long.parseLong(txtIdProducto.getText().trim());
            precio = Double.parseDouble(txtPrecioProducto.getText().trim());
            stock = Integer.parseInt(txtStockProducto.getText().trim());

            // Validar que el ID sea positivo
            if (idProducto <= 0) {
                JOptionPane.showMessageDialog(this, "El ID debe ser un número positivo.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validar si es un nuevo producto que el ID no esté en uso
            if (producto == null && productoDAO.obtenerProductoPorId(idProducto) != null) {
                JOptionPane.showMessageDialog(this, "El ID ingresado ya está en uso. Por favor, ingresa un ID único.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa valores numéricos válidos para ID, Precio y Stock.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear o actualizar producto
        CategoriaTienda categoria = (CategoriaTienda) cmbCategoria.getSelectedItem();
        Producto productoAGuardar;

        if (producto == null) {
            // Crear producto nuevo con el ID ingresado
            productoAGuardar = new Producto(
                    idProducto, // ID ingresado por el usuario
                    txtNombreProducto.getText().trim(),
                    txtDescripcionProducto.getText().trim(),
                    precio,
                    stock,
                    categoria
            );

            boolean creado = productoDAO.agregarProducto(productoAGuardar);
            if (creado) {
                JOptionPane.showMessageDialog(this, "Producto agregado exitosamente.");
                if (gestionProducto != null) {
                    gestionProducto.cargarDatosTabla(); // Actualizar la tabla en GestionProductos
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al agregar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Actualizar producto existente
            producto.setNombre(txtNombreProducto.getText().trim());
            producto.setDescripcion(txtDescripcionProducto.getText().trim());
            producto.setPrecio(precio);
            producto.setStock(stock);
            producto.setCategoria(categoria);

            boolean actualizado = productoDAO.actualizarProducto(producto);
            if (actualizado) {
                JOptionPane.showMessageDialog(this, "Producto actualizado exitosamente.");
                if (gestionProducto != null) {
                    gestionProducto.cargarDatosTabla(); // Actualizar la tabla en GestionProductos
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnGuardarProductoActionPerformed
    private void cargarCategorias() {
        // Limpiar el JComboBox
        cmbCategoria.removeAllItems();

        // Asumimos que tienes una enumeración CategoriaTienda que tiene las categorías disponibles
        for (CategoriaTienda categoria : CategoriaTienda.values()) {
            cmbCategoria.addItem(categoria);
        }
    }
    private void txtIdProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdProductoActionPerformed
    private void cargarCategoriasComboBox() {
        cmbCategoria.removeAllItems();
        for (CategoriaTienda categoria : CategoriaTienda.values()) {
            cmbCategoria.addItem(categoria);
        }
    }
    private void btnCancelarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarProductoActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarProductoActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormularioProductoCompleto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioProductoCompleto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioProductoCompleto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioProductoCompleto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GestionProducto gestionProductos = new GestionProducto();
                new FormularioProductoCompleto(gestionProductos, null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelarProducto;
    private javax.swing.JButton btnGuardarProducto;
    private javax.swing.JComboBox<CategoriaTienda> cmbCategoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtDescripcionProducto;
    private javax.swing.JTextField txtIdProducto;
    private javax.swing.JTextField txtNombreProducto;
    private javax.swing.JTextField txtPrecioProducto;
    private javax.swing.JTextField txtStockProducto;
    // End of variables declaration//GEN-END:variables
}
