package WEB;

import Duombaze.CategoryUtils;
import SubKlases.Kategorijos;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

@Controller
public class CategoryController {
    Gson gson = new Gson();
    @RequestMapping(value = "/categories/categoryList", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getCategories() throws SQLException, ClassNotFoundException {
        List<Kategorijos> categories= CategoryUtils.getAllCategories();
        return gson.toJson(categories);
    }
    @RequestMapping(value = "/categories/categoryAdd", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String addCat(@RequestBody String request) throws SQLException, ClassNotFoundException {
        Gson parser = new Gson();
        Properties data = parser.fromJson(request, Properties.class);
        String name = data.getProperty("pavadinimas");
        String user = data.getProperty("kurejas");
        CategoryUtils.add(name,user);
        return parser.toJson("category added");
    }
    @RequestMapping(value = "/categories/categoryDel", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String deleteCat(@RequestParam("id") int id) throws SQLException, ClassNotFoundException {//url/categoryDel?id=8
        CategoryUtils.delete(id);
        return gson.toJson("deleted ");
    }
    @RequestMapping(value = "/categories/categoryUpdate", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateCat(@RequestBody String request) throws SQLException, ClassNotFoundException {
        Gson parser = new Gson();
        Properties data = parser.fromJson(request, Properties.class);
        String name = data.getProperty("pavadinimas");
        int id = Integer.parseInt(data.getProperty("kategorijos_id"));
        CategoryUtils.update(name,id);
        return "updated";
    }
}

