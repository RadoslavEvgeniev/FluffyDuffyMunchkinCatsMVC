package controllers;

import app.broccolina.solet.HttpSoletRequest;
import app.summer.api.Model;

public abstract class BaseController {

    public String view(HttpSoletRequest request, Model model, String view) {
        return "template:" + view;
    }

    public String redirect(HttpSoletRequest request, Model model, String view) {
        return "redirect:" + view;
    }
}
