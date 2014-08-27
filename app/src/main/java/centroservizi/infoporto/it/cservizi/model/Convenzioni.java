package centroservizi.infoporto.it.cservizi.model;

/**
 * Created by Daniele on 19/08/2014.
 */
public class Convenzioni {

    String end;
    String description;
    String modification_date;
    String title;
    String start;
    String contact_email;
    String contact_name;
    String contact_phone;
    String uid;
    String likes;

    public Convenzioni(String end, String description, String modification_date, String title, String start, String contact_email, String contact_name, String contact_phone, String uid, String likes) {
        this.end = end;
        this.description = description;
        this.modification_date = modification_date;
        this.title = title;
        this.start = start;
        this.contact_email = contact_email;
        this.contact_name = contact_name;
        this.contact_phone = contact_phone;
        this.uid = uid;
        this.likes = likes;
    }

    public Convenzioni(){

    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModification_date() {
        return modification_date;
    }

    public void setModification_date(String modification_date) {
        this.modification_date = modification_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getContact_email() {
        return contact_email;
    }

    public void setContact_email(String contact_email) {
        this.contact_email = contact_email;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }
}
