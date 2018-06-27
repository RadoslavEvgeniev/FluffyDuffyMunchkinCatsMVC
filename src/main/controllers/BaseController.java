package controllers;

public abstract class BaseController {

    public String view(String view) {
        return "template:" + view;
    }

    public String redirect(String view) {
        return "redirect:" + view;
    }
}
