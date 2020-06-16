

// Alondra Sánchez Molina

public class UI_Login extends javax.swing.JFrame {
    
    public UI_Login() {
        initComponents();
        this.setTitle("Iniciar sesión");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }
    Cliente c =  new Cliente();
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lp_inicioSesion = new javax.swing.JLayeredPane();
        lb_bienvenida = new javax.swing.JLabel();
        lb_usuario = new javax.swing.JLabel();
        txt_usuario = new javax.swing.JTextField();
        lb_pass = new javax.swing.JLabel();
        btn_IniciarSesion = new javax.swing.JButton();
        pass_Contra = new javax.swing.JPasswordField();
        btn_AddUser = new javax.swing.JButton();
        lb_registro = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lp_inicioSesion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(183, 213, 212), 4));

        lb_bienvenida.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        lb_bienvenida.setText("Bienvenido al sistema...");

        lb_usuario.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        lb_usuario.setText("Usuario:");

        txt_usuario.setFont(new java.awt.Font("Cambria", 0, 15)); // NOI18N
        txt_usuario.setMaximumSize(new java.awt.Dimension(180, 36));
        txt_usuario.setMinimumSize(new java.awt.Dimension(180, 36));
        txt_usuario.setPreferredSize(new java.awt.Dimension(180, 36));

        lb_pass.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        lb_pass.setText("Contraseña:");

        btn_IniciarSesion.setBackground(new java.awt.Color(58, 99, 110));
        btn_IniciarSesion.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        btn_IniciarSesion.setForeground(new java.awt.Color(255, 255, 255));
        btn_IniciarSesion.setText("Iniciar Sesión");
        btn_IniciarSesion.setBorderPainted(false);
        btn_IniciarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_IniciarSesion.setFocusPainted(false);
        btn_IniciarSesion.setName("Generar Ruta"); // NOI18N
        btn_IniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_IniciarSesionActionPerformed(evt);
            }
        });

        pass_Contra.setFont(new java.awt.Font("Cambria", 0, 15)); // NOI18N
        pass_Contra.setToolTipText("");

        btn_AddUser.setBackground(new java.awt.Color(58, 99, 110));
        btn_AddUser.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        btn_AddUser.setForeground(new java.awt.Color(255, 255, 255));
        btn_AddUser.setText("+");
        btn_AddUser.setBorderPainted(false);
        btn_AddUser.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_AddUser.setFocusPainted(false);
        btn_AddUser.setName("Generar Ruta"); // NOI18N
        btn_AddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AddUserActionPerformed(evt);
            }
        });

        lb_registro.setFont(new java.awt.Font("Cambria", 0, 10)); // NOI18N
        lb_registro.setText("Registrarse");

        lp_inicioSesion.setLayer(lb_bienvenida, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lp_inicioSesion.setLayer(lb_usuario, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lp_inicioSesion.setLayer(txt_usuario, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lp_inicioSesion.setLayer(lb_pass, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lp_inicioSesion.setLayer(btn_IniciarSesion, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lp_inicioSesion.setLayer(pass_Contra, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lp_inicioSesion.setLayer(btn_AddUser, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lp_inicioSesion.setLayer(lb_registro, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout lp_inicioSesionLayout = new javax.swing.GroupLayout(lp_inicioSesion);
        lp_inicioSesion.setLayout(lp_inicioSesionLayout);
        lp_inicioSesionLayout.setHorizontalGroup(
            lp_inicioSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lp_inicioSesionLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(lp_inicioSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(lp_inicioSesionLayout.createSequentialGroup()
                        .addGroup(lp_inicioSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_pass, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lb_usuario, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(43, 43, 43)
                        .addGroup(lp_inicioSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pass_Contra)
                            .addComponent(txt_usuario, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE))
                        .addContainerGap(84, Short.MAX_VALUE))
                    .addGroup(lp_inicioSesionLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_IniciarSesion)
                        .addGap(82, 82, 82)
                        .addGroup(lp_inicioSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lb_registro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_AddUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(61, 61, 61))))
            .addGroup(lp_inicioSesionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_bienvenida)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        lp_inicioSesionLayout.setVerticalGroup(
            lp_inicioSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lp_inicioSesionLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lb_bienvenida)
                .addGap(31, 31, 31)
                .addGroup(lp_inicioSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_usuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(lp_inicioSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_pass, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pass_Contra, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(lp_inicioSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lp_inicioSesionLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_IniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(lp_inicioSesionLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(lb_registro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_AddUser)))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lp_inicioSesion)
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lp_inicioSesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_IniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_IniciarSesionActionPerformed
        String user = txt_usuario.getText();
        String pass = pass_Contra.getText();
        
        if(c.mensajes(user,pass,1)== 1){
            txt_usuario.setText("");
            pass_Contra.setText("");
        }
    }//GEN-LAST:event_btn_IniciarSesionActionPerformed

    private void btn_AddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AddUserActionPerformed
        String user = txt_usuario.getText();
        String pass = pass_Contra.getText();
        
        if(c.mensajes(user,pass,0)== 1){
            txt_usuario.setText("");
            pass_Contra.setText("");
        }
    }//GEN-LAST:event_btn_AddUserActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_AddUser;
    private javax.swing.JButton btn_IniciarSesion;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lb_bienvenida;
    private javax.swing.JLabel lb_pass;
    private javax.swing.JLabel lb_registro;
    private javax.swing.JLabel lb_usuario;
    private javax.swing.JLayeredPane lp_inicioSesion;
    private javax.swing.JPasswordField pass_Contra;
    private javax.swing.JTextField txt_usuario;
    // End of variables declaration//GEN-END:variables
}
