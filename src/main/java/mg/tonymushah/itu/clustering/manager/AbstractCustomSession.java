package mg.tonymushah.itu.clustering.manager;

import java.security.Principal;

import org.apache.catalina.Manager;
import org.apache.catalina.Session;

public abstract class AbstractCustomSession implements Session {
    protected Principal principal;

    @Override
    public Principal getPrincipal() {
        return principal;
    }

    @Override
    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }

    protected Manager manager;

    @Override
    public Manager getManager() {
        return manager;
    }

    @Override
    public void setManager(Manager manager) {
        this.manager = manager;
    }

    void updateThis(){
        if(manager != null) this.manager.add(this);
    }

    void removeThis(){
        if(manager != null) this.manager.remove(this);
    }

    protected String authType;

    @Override
    public String getAuthType() {
        // TODO Auto-generated method stub
        return authType;
    }

    @Override
    public void setAuthType(String authType) {
        this.authType = authType;
        this.updateThis();
    }

    protected String id;

    @Override
    public String getId() {
        // TODO Auto-generated method stub
        return id;
    }
    @Override
    public void setId(String id, boolean notify) {
        // TODO Auto-generated method stub
        this.id = id;
        this.updateThis();
    }
    @Override
    public void setId(String id) {
        this.setId(id, false);
    }
    
    protected long creationTime;

    @Override
    public void setCreationTime(long time) {
        this.creationTime = time;
        this.updateThis();
    }

    @Override
    public long getCreationTime() {
        // TODO Auto-generated method stub
        return creationTime;
    }

    protected boolean isValid;

    @Override
    public void setValid(boolean isValid) {
        this.isValid = isValid;
        this.updateThis();
    }
    
    @Override
    public boolean isValid() {
        return this.isValid;
    }

    protected boolean isNew;

    @Override
    public void setNew(boolean isNew) {
        this.isNew = isNew;
        this.updateThis();
    }

    public boolean isNew() {
        return isNew;
    }

    @Override
    public String getIdInternal() {
        return this.getId();
    }

    protected int maxInactiveInterval;
    @Override
    public void setMaxInactiveInterval(int interval) {
        this.maxInactiveInterval = interval;
    }
    @Override
    public int getMaxInactiveInterval() {
        return this.maxInactiveInterval;
    }
}
