/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudclientrestful;

import java.io.File;
import java.util.Date;
import java.util.List;
import javax.ws.rs.core.GenericType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author hp
 */
public class CrudClientRestful {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Prueba prueba = new Prueba();
        
        
//            GenericType<List<Country>> gType = new GenericType<List<Country>>() {
//        };
//        List<Country> countrys = (List<Country>) client.findAll_XML(gType);

            Date date = new Date(1997, 2, 25);
            Users user = new Users("silvio@mail.com", "silvio", "1234");
            
            
            
           
            prueba.create_XML(user);
            System.out.println(""+prueba.countREST());
            prueba.close();
        
           
        
        
        //prueba.create_XML(prueba);
    }
    
}
