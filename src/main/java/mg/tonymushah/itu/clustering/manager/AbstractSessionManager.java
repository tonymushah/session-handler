package mg.tonymushah.itu.clustering.manager;

import java.security.Principal;

import org.apache.catalina.Context;
import org.apache.catalina.Manager;
import org.apache.catalina.SessionIdGenerator;

public abstract class AbstractSessionManager implements Manager {
    protected Context context;
    protected SessionIdGenerator sessionIdGenerator;
    protected Principal principal;
    protected int createReate;
    protected long sessionCounter;
    protected long expiredSessions;
    protected int maxActive;
    protected int activeSessions;
    protected int maxAliveTime;
    //protected String authType;
    @Override
    public Context getContext() {
        // TODO Auto-generated method stub
        return context;
    }
    @Override
    public void setContext(Context context) {
        this.context = context;
    }
    @Override
    public SessionIdGenerator getSessionIdGenerator() {
        // TODO Auto-generated method stub
        return sessionIdGenerator;
    }
    @Override
    public void setSessionIdGenerator(SessionIdGenerator sessionIdGenerator) {
        this.sessionIdGenerator = sessionIdGenerator;
    }
    public void setPrincipal(Principal principal){
        this.principal = principal;
    }
    public Principal getPrincipal() {
        return principal;
    }
    @Override
    public int getSessionCreateRate() {
        return createReate;
    }
    @Override
    public void setSessionCounter(long sessionCounter) {
        this.sessionCounter = sessionCounter;
    }
    @Override
    public void setExpiredSessions(long expiredSessions) {
        this.expiredSessions = expiredSessions;
    }
    @Override
    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }
    @Override
    public int getMaxActive() {
        // TODO Auto-generated method stub
        return maxActive;
    }
    @Override
    public int getActiveSessions() {
        // TODO Auto-generated method stub
        return this.activeSessions;
    }
    @Override
    public long getExpiredSessions() {
        return expiredSessions;
    }
    @Override
    public void setSessionMaxAliveTime(int sessionMaxAliveTime) {
        this.maxAliveTime = sessionMaxAliveTime;
    }
    @Override
    public int getSessionMaxAliveTime() {
        return maxAliveTime;
    }
    
}
