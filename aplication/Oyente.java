package aplication;

import crudclientrestful.MyClient;
import crudclientrestful.Users;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.core.GenericType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author hp, Silvio Hermida
 */
public class Oyente implements ActionListener {

    private Users user;
    private PanelPrincipal panel;
    private MyClient prueba;
    private Users userTemporal;
    private Users userVerificar;
    String id = "";

    public Oyente(Users user, PanelPrincipal panel, MyClient prueba) {
        this.user = user;
        this.panel = panel;
        this.prueba = prueba;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComponent origen = (JComponent) e.getSource();
        String nombre = origen.getName();
        switch (nombre) {
            case "btnRegistrar":
                String name = panel.getCampoName().getText();
                String email = panel.getCampoEmail().getText();
                String pass = panel.getPass().getText();
                if (name.isEmpty()||email.isEmpty()||pass.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese todos los datos");
                }else{
                user = new Users(email, name, pass);

                prueba.create_XML(user);

                JOptionPane.showMessageDialog(null, "Registo de usuario exitoso");
                panel.getCampoName().setText("");
                panel.getCampoEmail().setText("");
                panel.getPass().setText("");
                }
                break;
            case "btnBuscar":
                id = panel.getCampoId().getText();
                userTemporal = prueba.find_XML(Users.class, id);
                if (userTemporal==null) {
                    JOptionPane.showMessageDialog(null, "No se encontro registro");
                }else{
                panel.getTextName().setText(userTemporal.getName());
                panel.getTextEmail().setText(userTemporal.getEmail());
                panel.getTextPass().setText(userTemporal.getPassword());
                }
                break;
            case "btnActualizar":
                String nameT = panel.getTextName().getText();
                String emailT = panel.getTextEmail().getText();
                String passT = panel.getTextPass().getText();
                userTemporal.setName(nameT);
                userTemporal.setEmail(emailT);
                userTemporal.setPassword(passT);
                prueba.edit_XML(userTemporal, id);
                JOptionPane.showMessageDialog(null, "Actualización de usuario exitoso");
                panel.getCampoId().setText("");
                panel.getTextName().setText("");
                panel.getTextEmail().setText("");
                panel.getTextPass().setText("");
                break;
            case "btnEliminar":
                String idE = panel.getCampoIdDelete().getText();
                userVerificar = prueba.find_XML(Users.class, idE);
                if (userVerificar==null) {
                    JOptionPane.showMessageDialog(null, "No se encontro registro");
                }else{
                int op = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el usuario?", "Elimianr usuario", JOptionPane.YES_NO_OPTION);
                if (op == JOptionPane.YES_OPTION) {
                    prueba.remove(idE);
                    JOptionPane.showMessageDialog(null, "Eliminación de usuario exitoso");
                    panel.getCampoIdDelete().setText("");
                } else {
                    panel.getCampoIdDelete().setText("");
                }
                }
                break;
            case "btnCancelar1":
                panel.getCampoName().setText("");
                panel.getCampoEmail().setText("");
                panel.getPass().setText("");
                break;
            case "btnCancelar2":
                panel.getTextName().setText("");
                panel.getTextEmail().setText("");
                panel.getTextPass().setText("");
                panel.getCampoId().setText("");
                break;
            case "btnListar":
                GenericType<List<Users>> gType = new GenericType<List<Users>>() {
                };
                List<Users> uss = (List<Users>) prueba.findAll_XML(gType);
                //Numero de columnas de la tabla
                System.out.println("oprimio");
                DefaultTableModel modelo
                        = new DefaultTableModel();
                panel.getTabla().setModel(modelo);
                modelo.addColumn("Id");
                modelo.addColumn("Nombre");
                modelo.addColumn("Email");    
                modelo.addColumn("Password");
                for (Users s : uss) {
                    Object datos[] = new Object[4];
                    datos[0] = s.getId();
                    datos[1] = s.getName();
                    datos[2] = s.getEmail();
                    datos[3] = s.getPassword();
                    modelo.addRow(datos);
                    datos = null;

                }

                break;
            default:
                throw new AssertionError();
        }

    }

}
