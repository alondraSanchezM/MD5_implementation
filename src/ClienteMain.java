

//Alondra Sánchez Molina

public class ClienteMain {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new UI_Login().setVisible(true);
        }});
    }
}
