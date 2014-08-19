package centroservizi.infoporto.it.cservizi.model;

public class Eventi {

    String event_url;
    String end_date;
    String modification_date;
    String title;
    String description;
    String contact_email;
    String contact_name;
    String contact_phone;
    String start_date;
    String uid;

    public Eventi(String event_url, String end_date, String modification_date, String title, String description, String contact_email, String contact_name, String contact_phone, String start_date, String uid) {
        this.event_url = event_url;
        this.end_date = end_date;
        this.modification_date = modification_date;
        this.title = title;
        this.description = description;
        this.contact_email = contact_email;
        this.contact_name = contact_name;
        this.contact_phone = contact_phone;
        this.start_date = start_date;
        this.uid = uid;
    }

    public Eventi() {

    }

    public String getEvent_url() {
        return event_url;
    }

    public void setEvent_url(String event_url) {
        this.event_url = event_url;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
