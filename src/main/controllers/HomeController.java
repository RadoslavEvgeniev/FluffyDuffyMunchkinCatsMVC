package controllers;

import app.broccolina.solet.HttpSoletRequest;
import app.summer.api.Controller;
import app.summer.api.GetMapping;
import app.summer.api.Model;

@Controller
public class HomeController extends BaseController {

    @GetMapping(route = "/")
    public String index(HttpSoletRequest request, Model model) {
        if (super.isLoggedIn(request)) {
            return super.redirect(request, model, "/home");
        }

        return super.view(request, model, "index");
    }

    @GetMapping(route = "/home")
    public String home(HttpSoletRequest request, Model model) {
        if (!super.isLoggedIn(request)) {
            return super.redirect(request, model, "/");
        }

        return super.view(request, model, "home");
    }
}
