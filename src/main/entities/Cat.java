package entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "cats")
public class Cat {

    private String id;

    private String catName;

    private String catBreed;

    private String catColor;

    public Cat() {
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false, unique = true)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "cat_name")
    public String getCatName() {
        return this.catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    @Column(name = "cat_breed")
    public String getCatBreed() {
        return this.catBreed;
    }

    public void setCatBreed(String catBreed) {
        this.catBreed = catBreed;
    }

    @Column(name = "cat_color")
    public String getCatColor() {
        return this.catColor;
    }

    public void setCatColor(String catColor) {
        this.catColor = catColor;
    }
}
