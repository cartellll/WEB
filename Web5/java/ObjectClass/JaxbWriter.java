/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjectClass;

import javax.xml.bind.*;
import java.io.File;

/**
 *
 * @author baran
 */
public class JaxbWriter  {
    
    JaxbWriter() throws JAXBException
    {
        User user4=new User();
        user4.setID(1);
        user4.setName("Ivan");
        user4.setLogin("cartel");
        user4.setPassword("11s1");
        
        JAXBContext context=JAXBContext.newInstance(User.class);
        Marshaller marsh=context.createMarshaller();
        marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marsh.marshal(user4, new File("result.xml"));
    }
}
