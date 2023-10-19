package mg.tonymushah.itu.clustering.manager;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.catalina.Manager;
import org.apache.catalina.Session;
import org.apache.catalina.SessionListener;
import org.apache.catalina.session.StandardSessionFacade;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;

public class CustomSession implements Session {

    protected Manager manager;
    protected Principal principal;
    protected boolean isValid;
    protected ObjectMapper mapper = new ObjectMapper();
    protected String id;
    @Override
    public String getAuthType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAuthType'");
    }

    @Override
    public void setAuthType(String authType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setAuthType'");
    }

    @Override
    public long getCreationTime() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCreationTime'");
    }

    @Override
    public long getCreationTimeInternal() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCreationTimeInternal'");
    }

    @Override
    public void setCreationTime(long time) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCreationTime'");
    }

    @Override
    public String getId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getId'");
    }

    @Override
    public String getIdInternal() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getIdInternal'");
    }

    @Override
    public void setId(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setId'");
    }

    @Override
    public void setId(String id, boolean notify) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setId'");
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
    public Manager getManager() {
        return manager;
    }

    @Override
    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @Override
    public int getMaxInactiveInterval() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMaxInactiveInterval'");
    }

    @Override
    public void setMaxInactiveInterval(int interval) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setMaxInactiveInterval'");
    }

    @Override
    public void setNew(boolean isNew) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setNew'");
    }

    @Override
    public Principal getPrincipal() {
        return principal;
    }

    @Override
    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }

    @Override
    public HttpSession getSession() {
        final CustomSession mySession = this;
        return new HttpSession() {
            
            @Override
            public long getCreationTime() {
                return mySession.getCreationTime();
            }

            @Override
            public String getId() {
                return mySession.getId();
            }

            @Override
            public long getLastAccessedTime() {
                return mySession.getLastAccessedTime();
            }

            @Override
            public ServletContext getServletContext() {
                return null;
            }

            @Override
            public void setMaxInactiveInterval(int interval) {
                mySession.setMaxInactiveInterval(interval);
            }

            @Override
            public int getMaxInactiveInterval() {
                return mySession.getMaxInactiveInterval();
            }

            @Override
            public Object getAttribute(String name) {
                return mySession.getNote(name);
            }

            @Override
            public Enumeration<String> getAttributeNames() {
                Iterable<String> noteNames = () -> mySession.getNoteNames();
                Stream<String> stream = StreamSupport.stream(noteNames.spliterator(), false);
                return Collections.enumeration(stream.collect(Collectors.toSet())) ;
            }

            @Override
            public void setAttribute(String name, Object value) {
                mySession.setNote(name, value);
            }

            @Override
            public void removeAttribute(String name) {
                mySession.removeNote(name);
            }

            @Override
            public void invalidate() {
                mySession.expire();
            }

            @Override
            public boolean isNew() {
                return false;
            }
            
        };
    }

    @Override
    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }

    @Override
    public boolean isValid() {
        return this.isValid;
    }

    @Override
    public void access() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'access'");
    }

    @Override
    public void addSessionListener(SessionListener listener) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addSessionListener'");
    }

    @Override
    public void endAccess() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'endAccess'");
    }

    @Override
    public void expire() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'expire'");
    }

    @Override
    public Object getNote(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNote'");
    }

    @Override
    public Iterator<String> getNoteNames() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNoteNames'");
    }

    @Override
    public void recycle() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'recycle'");
    }

    @Override
    public void removeNote(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeNote'");
    }

    @Override
    public void removeSessionListener(SessionListener listener) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeSessionListener'");
    }

    @Override
    public void setNote(String name, Object value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setNote'");
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
