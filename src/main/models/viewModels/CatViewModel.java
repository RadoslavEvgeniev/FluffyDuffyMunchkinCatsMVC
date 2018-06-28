package models.viewModels;

public class CatViewModel {

    private String catName;

    private String catBreed;

    private String catColor;

    public CatViewModel() {
    }

    public String getCatName() {
        return this.catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatBreed() {
        return this.catBreed;
    }

    public void setCatBreed(String catBreed) {
        this.catBreed = catBreed;
    }

    public String getCatColor() {
        return this.catColor;
    }

    public void setCatColor(String catColor) {
        this.catColor = catColor;
    }
}
