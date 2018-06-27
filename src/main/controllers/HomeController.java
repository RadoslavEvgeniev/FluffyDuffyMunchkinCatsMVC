package controllers;

import app.summer.api.Controller;
import app.summer.api.GetMapping;

@Controller
public class HomeController extends BaseController {

    @GetMapping(route = "/")
    public String index() {
        return super.view("index");
    }
}
