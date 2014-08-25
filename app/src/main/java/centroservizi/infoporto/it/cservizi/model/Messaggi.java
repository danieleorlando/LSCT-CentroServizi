package centroservizi.infoporto.it.cservizi.model;

public class Messaggi {

    String from_id;
    String to_id;
    String created_at;
    String subject;
    String message;
    String status;

    public Messaggi(String from_id, String to_id, String created_at, String subject, String message, String status) {
        this.from_id = from_id;
        this.to_id = to_id;
        this.created_at = created_at;
        this.subject = subject;
        this.message = message;
        this.status = status;
    }

    public Messaggi() {

    }

    public String getFrom_id() {
        return from_id;
    }

    public void setFrom_id(String from_id) {
        this.from_id = from_id;
    }

    public String getTo_id() {
        return to_id;
    }

    public void setTo_id(String to_id) {
        this.to_id = to_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
