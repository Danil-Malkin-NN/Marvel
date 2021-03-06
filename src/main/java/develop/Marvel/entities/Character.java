package develop.Marvel.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Character {


    @Id
    private String name;

    @ManyToMany
    @JoinTable(name="characters_comics",
            joinColumns=@JoinColumn(name="characters_name"),
            inverseJoinColumns=@JoinColumn(name="comics_name"))
    private Set< Comics > comicsSet = new HashSet<>();

    private String tag = "default";

    private String image;

    private String description = "";

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void addComics(Comics comics) {
        comicsSet.add(comics);
    }

    public Set< Comics > getComicsSet() {
        return comicsSet;
    }

    public void setComicsSet(Set< Comics > comicsSet) {
        this.comicsSet = comicsSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character(String name) {
        this.name = name;
    }

    public Character() {
    }

    public Character(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Character(String name, String tag, String description) {
        this.name = name;
        this.tag = tag;
        this.description = description;
    }
}
