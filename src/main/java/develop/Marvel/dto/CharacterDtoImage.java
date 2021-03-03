package develop.Marvel.dto;

public class CharacterDtoImage {

    String name;

    String image;

    String tag;

    String description;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CharacterDtoImage(String name, String image, String description) {
        this.name = name;
        this.image = image;
        this.description = description;
    }

    public CharacterDtoImage() {
    }
}
