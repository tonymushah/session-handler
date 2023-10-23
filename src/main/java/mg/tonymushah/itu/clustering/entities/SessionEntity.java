package mg.tonymushah.itu.clustering.entities;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.apache.catalina.Session;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import mg.tonymushah.itu.clustering.manager.CustomSession;

@Entity
public class SessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String data;
    private long insertDate;
    private boolean isExpired;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getInsertDate() {
        return new Date(insertDate);
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate.getTime();
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
        Optional.ofNullable(id).ifPresent(id_ -> {
            this.id = id_;
        });
    }

    public SessionEntity() {
    }

    public SessionEntity(String id, String data, Date insertDate, boolean isExpired) {
        this.setId(id);
        this.setData(data);
        this.setInsertDate(insertDate);
        this.setExpired(isExpired);
    }

    public Session toSession() throws JsonMappingException, JsonProcessingException {
        CustomSession session = new CustomSession(this);
        return session;
    }

    public ObjectNode getDataObjectNode() throws JsonMappingException, JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode data = mapper.createObjectNode();
        JsonNode data_JsonNode = mapper.readTree(this.data);
        var noteNames = data_JsonNode.fields();
        while (noteNames.hasNext()){
            Map.Entry<String,JsonNode> field = noteNames.next();
            data.set(field.getKey(), mapper.valueToTree(field.getValue()));
        }
        return data;
    }
    /*public SessionEntity(Session session) throws JsonProcessingException {
        this.setId(Optional.ofNullable(session.getId()).orElse(null));
        this.setExpired(!session.isValid());
        this.setInsertDate(new Date(session.getCreationTime()));
        
        this.setData(mapper.writeValueAsString(data));
    }*/
}
