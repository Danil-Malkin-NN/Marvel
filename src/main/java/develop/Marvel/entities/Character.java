package develop.Marvel.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Character {

    @Id
    private String name;

    @ManyToMany
    private Set< Comics > comicsSet = new HashSet<>();

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

}
