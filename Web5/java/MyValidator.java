
import auto.Database;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


@ManagedBean
@SessionScoped
public class MyValidator implements Validator{
    
  @EJB
  CustomerEJB customerEJB;
    private Pattern pattern;
    

    @Override
    public void validate(FacesContext context, UIComponent component,
                         Object value) throws ValidatorException
    {

	pattern = Pattern.compile(customerEJB.getCarsString());
        Matcher matcher = pattern.matcher(value.toString());
        if (matcher.matches()) {
            FacesMessage msg = new FacesMessage("Машина с таким ID уже существует.", 
                                                                "Invalid E-mail format.");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
        }
    }
}
