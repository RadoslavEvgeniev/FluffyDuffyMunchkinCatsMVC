package controllers;

import app.broccolina.solet.HttpSoletRequest;
import app.summer.api.*;
import entities.Cat;
import models.bindingModels.CatCreateBindingModel;
import models.viewModels.CatViewModel;
import repositories.CatRepository;
import services.CatService;

import java.util.List;

@Controller
public class CatController extends BaseController {

    private CatService catService;

    public CatController() {
        this.catService = new CatService();
    }

    @GetMapping(route = "/cats/create")
    public String create(HttpSoletRequest request, Model model) {
        return super.view(request, model, "cats/create");
    }

    @PostMapping(route = "/cats/create")
    public String createResult(HttpSoletRequest request, Model model, CatCreateBindingModel bindingModel) {
        if (bindingModel.getCatName() == null || bindingModel.getCatBreed() == null || bindingModel.getCatColor() == null) {
            return super.view(request, model, "cats/create");
        }

        this.catService.saveCat(bindingModel);

        return super.redirect(request, model, "/cats/profile/" + bindingModel.getCatName());
    }

    @GetMapping(route = "/cats/all")
    public String all(HttpSoletRequest request, Model model) {
        StringBuilder result = new StringBuilder();
        List<CatViewModel> allCats = this.catService.findAllCats();

        if (allCats.size() > 0) {
            allCats.forEach(cat -> {
                result.append("<a href=\"/cats/profile/").append(cat.getCatName()).append("\">").append(cat.getCatName()).append("</a><br />");
            });
        } else {
            result.append("There are no cats.").append("<a href=\"/cats/create\">Create some!").append("</a><br />");
        }

        model.addAttributes("allCats", result.toString());
        return view(request, model, "cats/all");
    }

    @GetMapping(route = "/cats/profile/{catName}")
    public String profile(@PathVariable(name = "catName") String catName, HttpSoletRequest request, Model model) {
        CatViewModel cat = this.catService.findCatByName(catName);
        StringBuilder catDetailsSb = new StringBuilder();
        if (cat == null) {
            catDetailsSb.append(String.format("Cat, with name %s was not found.", catName));
        } else {
            catDetailsSb.append(String.format("<h2>Cat - %s</h2></br>", cat.getCatName()));
            catDetailsSb.append(String.format("<h3>Breed - %s</h3></br>", cat.getCatBreed()));
            catDetailsSb.append(String.format("<h3>Color - %s</h3></br>", cat.getCatColor()));
        }

        model.addAttributes("catDetails", catDetailsSb.toString());
        return view(request, model, "cats/profile");
    }
}
