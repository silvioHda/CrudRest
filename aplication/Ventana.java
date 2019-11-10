

package aplication;

import crudclientrestful.MyClient;
import crudclientrestful.Users;
import javax.swing.JFrame;

/**
 *
 * @author hp, Silvio Hermida
 */
public class Ventana {
    public static void main(String[] args) {
        JFrame f = new JFrame("Crud User");
        Users users = new Users();
        PanelPrincipal panel = new PanelPrincipal(users);
        MyClient prueba = new MyClient();
        Oyente oyente = new Oyente(users, panel, prueba);
        f.add(panel);
        f.setSize(300, 300);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(3);
        panel.addEventos(oyente);
        f.setVisible(true);
    }
}
