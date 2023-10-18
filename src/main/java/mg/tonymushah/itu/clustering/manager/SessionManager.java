package mg.tonymushah.itu.clustering.manager;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.sql.Connection;

import org.apache.catalina.Context;
import org.apache.catalina.Manager;
import org.apache.catalina.Session;
import org.apache.catalina.SessionIdGenerator;
import org.apache.catalina.session.ManagerBase;
import org.apache.catalina.util.LifecycleMBeanBase;

public class SessionManager implements Manager {
    protected Connection dbConnection;
    protected Context context;
    protected SessionIdGenerator sessionIdGenerator;

    @Override
    public Context getContext() {
        return this.context;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public SessionIdGenerator getSessionIdGenerator() {
        return this.sessionIdGenerator;
    }

    @Override
    public void setSessionIdGenerator(SessionIdGenerator sessionIdGenerator) {
        this.sessionIdGenerator = sessionIdGenerator;
    }

    @Override
    public long getSessionCounter() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSessionCounter'");
    }

    @Override
    public void setSessionCounter(long sessionCounter) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setSessionCounter'");
    }

    @Override
    public int getMaxActive() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMaxActive'");
    }

    @Override
    public void setMaxActive(int maxActive) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setMaxActive'");
    }

    @Override
    public int getActiveSessions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getActiveSessions'");
    }

    @Override
    public long getExpiredSessions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getExpiredSessions'");
    }

    @Override
    public void setExpiredSessions(long expiredSessions) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setExpiredSessions'");
    }

    @Override
    public int getRejectedSessions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRejectedSessions'");
    }

    @Override
    public int getSessionMaxAliveTime() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSessionMaxAliveTime'");
    }

    @Override
    public void setSessionMaxAliveTime(int sessionMaxAliveTime) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setSessionMaxAliveTime'");
    }

    @Override
    public int getSessionAverageAliveTime() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSessionAverageAliveTime'");
    }

    @Override
    public int getSessionCreateRate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSessionCreateRate'");
    }

    @Override
    public int getSessionExpireRate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSessionExpireRate'");
    }

    @Override
    public void add(Session session) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addPropertyChangeListener'");
    }

    @Override
    public void changeSessionId(Session session, String newId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'changeSessionId'");
    }

    @Override
    public Session createEmptySession() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createEmptySession'");
    }

    @Override
    public Session createSession(String sessionId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createSession'");
    }

    @Override
    public Session findSession(String id) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findSession'");
    }

    @Override
    public Session[] findSessions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findSessions'");
    }

    @Override
    public void load() throws ClassNotFoundException, IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'load'");
    }

    @Override
    public void remove(Session session) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public void remove(Session session, boolean update) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removePropertyChangeListener'");
    }

    @Override
    public void unload() throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'unload'");
    }

    @Override
    public void backgroundProcess() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'backgroundProcess'");
    }

    @Override
    public boolean willAttributeDistribute(String name, Object value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'willAttributeDistribute'");
    }

    @Override
    public void setNotifyBindingListenerOnUnchangedValue(boolean notifyBindingListenerOnUnchangedValue) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setNotifyBindingListenerOnUnchangedValue'");
    }

    @Override
    public void setNotifyAttributeListenerOnUnchangedValue(boolean notifyAttributeListenerOnUnchangedValue) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setNotifyAttributeListenerOnUnchangedValue'");
    }

    @Override
    public void setSessionActivityCheck(boolean sessionActivityCheck) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setSessionActivityCheck'");
    }

    @Override
    public void setSessionLastAccessAtStart(boolean sessionLastAccessAtStart) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setSessionLastAccessAtStart'");
    }

}
