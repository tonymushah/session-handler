package mg.tonymushah.itu.clustering.entities;

import java.util.Date;
import java.util.HashMap;

import org.apache.catalina.Manager;
import org.apache.catalina.Session;
import org.apache.catalina.session.StandardSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class SessionEntity {
    @Id
    private String id;
    private String data;
    private Date insertDate;
    private boolean isExpired;
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public Date getInsertDate() {
        return insertDate;
    }
    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }
    public boolean isExpired() {
        return isExpired;
    }
    public void setExpired(boolean isExpired) {
        this.isExpired = isExpired;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public SessionEntity() {
    }
    public SessionEntity(String id, String data, Date insertDate, boolean isExpired) {
        this.id = id;
        this.data = data;
        this.insertDate = insertDate;
        this.isExpired = isExpired;
    }
    public Session toSession(){
        StandardSession session = new StandardSession(null);
        session.setId(this.id);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode dataMap = mapper.createObjectNode();
        
        return session;
    }
}
