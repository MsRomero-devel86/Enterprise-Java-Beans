/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServices;

import java.io.StringReader;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Meghan
 */
@WebService(serviceName = "DriverTest")
public class DriverTest {

    @EJB
    private writeToDatabaseLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    /**
     * Web service operation
     */
    @WebMethod(operationName = "driverJSON")
    public String driverJSON(@WebParam(name = "infoFromDriver") String infoFromDriver) {
              JsonParser parser = Json.createParser(new StringReader(infoFromDriver));
        while (parser.hasNext())
        {
            JsonParser.Event event = parser.next();
            switch(event)
            {
                case START_ARRAY:
                case END_ARRAY:
                case START_OBJECT:
                case END_OBJECT:
                case VALUE_FALSE:
                case VALUE_NULL:
                case VALUE_TRUE:
                    System.out.println(event.toString());
                    break;
                case KEY_NAME:
                    System.out.println(event.toString() + " " + parser.getString() + " - ");
                    break;
                case VALUE_STRING:
                case VALUE_NUMBER:
                    System.out.println(event.toString() + " " + parser.getString());
                    break;
            }
        }
        return "Ok";
    }
    
}
