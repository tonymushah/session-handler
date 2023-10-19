package mg.tonymushah.itu.clustering.manager;

import java.util.Collections;
import java.util.Enumeration;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;

public class CustomHttpSession implements HttpSession {
    protected final CustomSession mySession;

    public CustomHttpSession(CustomSession mySession) {
        this.mySession = mySession;
    }

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
        return Collections.enumeration(stream.collect(Collectors.toSet()));
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
        return mySession.isNew();
    }

}
