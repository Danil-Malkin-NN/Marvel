package develop.Marvel.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Comics {

    @Id
    private String name;

    private String image;

    private String description = "";

    private String tag = "default";

    @ManyToMany
    @JoinTable(name="characters_comics",
            joinColumns=@JoinColumn(name="comics_name"),
            inverseJoinColumns=@JoinColumn(name="characters_name"))
    private Set< Character > characterSet = new HashSet<>();

    public Comics(String name, String tag, String description) {
        this.name = name;
        this.description = description;
        this.tag = tag;
    }

    public Comics(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Comics() {
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set< Character > getCharacterSet() {
        return characterSet;
    }

    public void setCharacterSet(Set< Character > characterSet) {
        this.characterSet = characterSet;
    }

    public void addCharacter(Character character) {
        characterSet.add(character);
    }
}
