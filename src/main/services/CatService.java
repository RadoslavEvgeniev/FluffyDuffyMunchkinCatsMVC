package services;

import entities.Cat;
import models.bindingModels.CatCreateBindingModel;
import models.viewModels.CatViewModel;
import repositories.CatRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CatService {

    private CatRepository catRepository;

    public CatService() {
        this.catRepository = new CatRepository();
    }

    public void saveCat(CatCreateBindingModel catCreateBindingModel) {
        Cat cat = new Cat();
        cat.setCatName(catCreateBindingModel.getCatName());
        cat.setCatBreed(catCreateBindingModel.getCatBreed());
        cat.setCatColor(catCreateBindingModel.getCatColor());

        this.catRepository.saveCat(cat);
    }

    public List<CatViewModel> findAllCats() {
        List<CatViewModel> cats = new ArrayList<>();
        this.catRepository.findAllCats().forEach(cat -> {
            CatViewModel catViewModel = new CatViewModel();
            catViewModel.setCatName(cat.getCatName());
            catViewModel.setCatBreed(cat.getCatBreed());
            catViewModel.setCatColor(cat.getCatColor());
            cats.add(catViewModel);
        });

        return cats;
    }

    public CatViewModel findCatByName(String name) {
        Cat cat = this.catRepository.findCatByName(name);
        CatViewModel catViewModel = new CatViewModel();
        catViewModel.setCatName(cat.getCatName());
        catViewModel.setCatBreed(cat.getCatBreed());
        catViewModel.setCatColor(cat.getCatColor());

        return catViewModel;
    }
}
