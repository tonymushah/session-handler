package mg.tonymushah.itu.clustering.manager;

import java.util.Iterator;

import org.apache.catalina.SessionListener;
import org.apache.catalina.session.StandardSessionFacade;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import jakarta.servlet.http.HttpSession;
import mg.tonymushah.itu.clustering.entities.SessionEntity;

public class CustomSession extends AbstractCustomSession {

    private SessionEntity entity;

    protected ObjectNode data;
    protected final ObjectMapper mapper = new ObjectMapper();

    @Override
    public long getCreationTimeInternal() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCreationTimeInternal'");
    }
    
    public ObjectNode getData() {
        return data;
    }

    public CustomSession() {
        this.setData(mapper.createObjectNode());
    }

    public void setData(ObjectNode data) {
        this.data = data;
    }

    @Override
    public long getThisAccessedTime() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getThisAccessedTime'");
    }

    @Override
    public long getThisAccessedTimeInternal() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getThisAccessedTimeInternal'");
    }

    @Override
    public long getLastAccessedTime() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLastAccessedTime'");
    }

    @Override
    public long getLastAccessedTimeInternal() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLastAccessedTimeInternal'");
    }

    @Override
    public long getIdleTime() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getIdleTime'");
    }

    @Override
    public long getIdleTimeInternal() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getIdleTimeInternal'");
    }

    @Override
    public HttpSession getSession() {
        return new StandardSessionFacade(new CustomHttpSession(this));
    }

    @Override
    public void access() {
        this.setValid(true);
    }

    @Override
    public void addSessionListener(SessionListener listener) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addSessionListener'");
    }

    @Override
    public void endAccess() {
        this.setValid(false);
    }

    @Override
    public void expire() {
        this.setValid(false);
    }

    @Override
    public Object getNote(String name) {
        return this.data.get(name);
    }

    @Override
    public Iterator<String> getNoteNames() {
        return this.data.fieldNames();
    }

    @Override
    public void recycle() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'recycle'");
    }

    @Override
    public void removeNote(String name) {
        this.data.remove(name);
        this.updateThis();
    }

    @Override
    public void removeSessionListener(SessionListener listener) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeSessionListener'");
    }

    @Override
    public void setNote(String name, Object value) {
        this.data.set(name, mapper.valueToTree(value));
        this.updateThis();
    }

    @Override
    public void tellChangedSessionId(String newId, String oldId, boolean notifySessionListeners,
            boolean notifyContainerListeners) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tellChangedSessionId'");
    }

    @Override
    public boolean isAttributeDistributable(String name, Object value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isAttributeDistributable'");
    }

}
