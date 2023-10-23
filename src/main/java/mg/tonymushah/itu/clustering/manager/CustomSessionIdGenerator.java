package mg.tonymushah.itu.clustering.manager;

import java.util.Optional;
import java.util.UUID;

import org.apache.catalina.SessionIdGenerator;

public class CustomSessionIdGenerator implements SessionIdGenerator {

    private int sessionIdLength;

    private String jvmRoute;

    @Override
    public String getJvmRoute() {
        return Optional.ofNullable(this.jvmRoute).orElse("");
    }

    @Override
    public void setJvmRoute(String jvmRoute) {
        Optional.ofNullable(jvmRoute).ifPresent(jvmRoute_ -> {
            this.jvmRoute = jvmRoute_;
        });
    }

    @Override
    public int getSessionIdLength() {
        if(sessionIdLength <= 0){
            return 1232346546;
        }
        return sessionIdLength;
    }

    @Override
    public void setSessionIdLength(int sessionIdLength) {
        this.sessionIdLength = sessionIdLength;
    }

    @Override
    public String generateSessionId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String generateSessionId(String route) {
        return this.generateSessionId();
    }
    
}
