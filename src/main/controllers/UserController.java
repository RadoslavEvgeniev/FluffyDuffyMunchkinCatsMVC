package controllers;

import app.broccolina.solet.HttpSoletRequest;
import app.javache.http.HttpSession;
import app.javache.http.HttpSessionImpl;
import app.summer.api.Controller;
import app.summer.api.GetMapping;
import app.summer.api.Model;
import app.summer.api.PostMapping;
import models.bindingModels.UserLoginBindingModel;
import models.bindingModels.UserRegisterBindingModel;
import services.UserService;

@Controller
public class UserController extends BaseController {

    private UserService userService;

    public UserController() {
        this.userService = new UserService();
    }

    @GetMapping(route = "/users/register")
    public String register(HttpSoletRequest request, Model model) {
        return super.view(request, model, "users/register");
    }

    @PostMapping(route = "/users/register")
    public String registerConfirm(HttpSoletRequest request, Model model, UserRegisterBindingModel userRegisterBindingModel) {
        this.userService.saveUser(userRegisterBindingModel);

        return super.redirect(request, model, "/users/login");
    }

    @GetMapping(route = "/users/login")
    public String login(HttpSoletRequest request, Model model) {
        return super.view(request, model, "users/login");
    }

    @PostMapping(route = "/users/login")
    public String loginConfirm(HttpSoletRequest request, Model model, UserLoginBindingModel userLoginBindingModel) {
        if (!this.userService.userIsExistent(userLoginBindingModel) || !this.userService.userPasswordMatches(userLoginBindingModel)) {
            model.addAttributes("error", "Username and/or password mismatch!");
            return super.error(model);
        }

        HttpSession session = new HttpSessionImpl();
        session.addAttribute("user-id", this.userService.getUserId(userLoginBindingModel));
        request.setSession(session);

        model.addAttributes("username", userLoginBindingModel.getUsername());

        return super.redirect(request, model, "/");
    }
}
