package develop.Marvel.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Comics {

    @Id
    private String name;

    @ManyToMany
    private Set< Character > characterSet = new HashSet<>();

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
