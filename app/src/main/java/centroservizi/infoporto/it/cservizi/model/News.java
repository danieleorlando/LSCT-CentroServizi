package centroservizi.infoporto.it.cservizi.model;

public class News {

    String text;
    String title;
    String uid;
    String modification_date;
    String description;
    String likes;

    public News(String text, String title, String uid, String modification_date, String description, String likes) {
        this.text = text;
        this.title = title;
        this.uid = uid;
        this.modification_date = modification_date;
        this.description = description;
        this.likes = likes;
    }

    public News() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getModification_date() {
        return modification_date;
    }

    public void setModification_date(String modification_date) {
        this.modification_date = modification_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }
}
