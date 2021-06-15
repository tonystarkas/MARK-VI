package WEB;

import Duombaze.UserUtils;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.Properties;
@Controller
public class UserController {
    Gson gson = new Gson();
    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String createUser(@RequestBody String request) throws SQLException, ClassNotFoundException {
        Gson parser = new Gson();
        Properties data = parser.fromJson(request, Properties.class);
        String user = data.getProperty("vardas");
        String pass = data.getProperty("slaptazodis");
        UserUtils.createUser(user,pass);
        return parser.toJson("Kleintas sukurtas");
    }
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String loginUser(@RequestBody String request) throws SQLException, ClassNotFoundException {
        Properties data = gson.fromJson(request, Properties.class);
        String user = data.getProperty("vardas");
        String pass = data.getProperty("slaptazodis");
        UserUtils.empLogin(user,pass);
        if(UserUtils.returnSuccess()){
            return "Sekmingai prisijungta";
        }
        else{
            return "Neteisingi duomenys";
        }

    }
}
