package develop.Marvel.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Comics {

    @Id
    private String name;

    private String image;

    private String description;

    @ManyToMany
    private Set< Character > characterSet = new HashSet<>();

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
