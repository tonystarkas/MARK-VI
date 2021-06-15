package WEB;
import Duombaze.CategoryUtils;
import Duombaze.FinanceUtils;
import SubKlases.Finansai;
import SubKlases.Kategorijos;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.swing.text.html.ListView;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
@Controller
public class FinanceController {
    Gson gson = new Gson();
    @RequestMapping(value = "/finances/createProfit", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String createProfit(@RequestBody String request) throws SQLException, ClassNotFoundException {
        Gson parser = new Gson();
        Properties data = parser.fromJson(request, Properties.class);
        String name = data.getProperty("pavadinimas");
        double amount = Double.parseDouble(data.getProperty("suma"));
        int id = Integer.parseInt(data.getProperty("kategorijos_id"));
        FinanceUtils.add(name,amount,id);
        return "added";
    }
    @RequestMapping(value = "/finances/createExpense", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String createExpense(@RequestBody String request) throws SQLException, ClassNotFoundException {
        Gson parser = new Gson();
        Properties data = parser.fromJson(request, Properties.class);
        String name = data.getProperty("pavadinimas");
        double amount = Double.parseDouble(data.getProperty("suma"));
        int id = Integer.parseInt(data.getProperty("kategorijos_id"));
        FinanceUtils.add(name,amount,id);
        return "added";
    }
    @RequestMapping(value = "/finances/deleteFinance", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String deleteFinance(@RequestParam("id") int id) throws SQLException, ClassNotFoundException {//url/deleteFinance?id=8
       FinanceUtils.delete(id);
        return "deleted";
    }
    @RequestMapping(value = "/finances/financeUpdate", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateCat(@RequestBody String request) throws SQLException, ClassNotFoundException {
        Gson parser = new Gson();
        Properties data = parser.fromJson(request, Properties.class);
        String name = data.getProperty("pavadinimas");
        double amount = Double.parseDouble(data.getProperty("suma"));
        int id = Integer.parseInt(data.getProperty("finansu_id"));
        FinanceUtils.update(name,amount,id);
        return "updated";
    }
    @RequestMapping(value = "/finances/showFinances", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String showFinances(@RequestParam("id") int id) throws SQLException, ClassNotFoundException {
        //int id = Integer.parseInt(data.getProperty("kategorijos_id"));
        List<Finansai> finansai = FinanceUtils.getFinances(id);
        return gson.toJson(finansai);
    }
}
