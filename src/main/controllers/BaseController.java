package controllers;

import app.broccolina.solet.HttpSoletRequest;
import app.summer.api.Model;

public abstract class BaseController {

    protected boolean isLoggedIn(HttpSoletRequest request) {
        return (request.getSession() != null) && (request.getSession().getAttributes().containsKey("user-id"));
    }

    protected String view(HttpSoletRequest request, Model model, String view) {
        return "template:" + view;
    }

    protected String error(Model model) {
        return "template:error";
    }

    protected String redirect(HttpSoletRequest request, Model model, String view) {
        return "redirect:" + view;
    }
}
