package mg.tonymushah.itu.clustering.manager;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.catalina.Context;
import org.apache.catalina.Session;
import org.apache.catalina.SessionIdGenerator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import mg.tonymushah.itu.clustering.entities.SessionEntity;

public class SessionManager extends AbstractSessionManager {
    private Optional<EntityManager> em;
    protected Context context;
    protected SessionIdGenerator sessionIdGenerator;
    protected EntityManagerFactory entityManagerFactory;

    @Override
    public int getActiveSessions() {
        Session[] sessions = findSessions();
        if (sessions != null) {
            return sessions.length;
        } else {
            return 0;
        }
    }

    public SessionManager(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public Optional<EntityManager> getEm() {
        return em;
    }

    public void setEm(Optional<EntityManager> em) {
        this.em = em;
    }

    @Override
    public void unload() throws IOException {
        this.getEm().ifPresent(inner -> {
            inner.close();
            this.setEm(Optional.empty());
        });
    }

    @Override
    public void load() throws ClassNotFoundException, IOException {
        this.getEm().ifPresent((inner) -> {
            inner.close();
        });
        this.setEm(Optional.of(entityManagerFactory.createEntityManager()));
    }

    @Override
    public long getSessionCounter() {
        return 0;
    }

    @Override
    public int getRejectedSessions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRejectedSessions'");
    }

    @Override
    public int getSessionAverageAliveTime() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSessionAverageAliveTime'");
    }

    @Override
    public int getSessionExpireRate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSessionExpireRate'");
    }

    @Override
    public void add(Session session) {
        em.ifPresentOrElse(entityManager -> {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            try {
                var sessionEntity = new SessionEntity(session);
                if (!entityManager.contains(sessionEntity)) {
                    entityManager.persist(sessionEntity);
                }
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw new RuntimeException(e.fillInStackTrace());
            }
        }, null);
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
        return this.createSession(this.getSessionIdGenerator().generateSessionId());
    }

    @Override
    public Session createSession(String sessionId) {
        var session = new CustomSession();
        session.setManager(this);
        session.setId(sessionId);
        return session;
    }

    @Override
    public Session findSession(String id) throws IOException {
        return em.map(manager -> {
            SessionEntity inner = manager.find(SessionEntity.class, id);
            try {
                Session toReturn = inner.toSession();
                toReturn.setManager(this);
                return toReturn;
            } catch (JsonMappingException e) {
                throw new RuntimeException(e.fillInStackTrace());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e.fillInStackTrace());
            }
        }).orElse(null);
    }

    @Override
    public Session[] findSessions() {
        return em.map(manager -> {
            CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
            CriteriaQuery<SessionEntity> query = criteriaBuilder.createQuery(SessionEntity.class);
            Root<SessionEntity> root = query.from(SessionEntity.class);
            query.select(root).where(root.get("isExpired").in(false));
            TypedQuery<SessionEntity> res = manager.createQuery(query);
            Set<Session> sessions = res.getResultStream().map(value -> {
                try {
                    return value.toSession();
                } catch (JsonProcessingException e) {
                    return null;
                }
            }).filter(v -> Optional.ofNullable(v).isPresent()).collect(Collectors.toSet());
            Session[] sessions2 = new Session[sessions.size()];
            sessions2 = sessions.toArray(sessions2);
            return sessions2;
        }).orElse(null);
    }

    @Override
    public void remove(Session session) {
        this.remove(session, false);
    }

    @Override
    public void remove(Session session, boolean update) {
        em.ifPresentOrElse(entityManager -> {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            try {
                var sessionEntity = new SessionEntity(session);
                if (entityManager.contains(sessionEntity)) {
                    entityManager.remove(sessionEntity);
                }
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw new RuntimeException(e.fillInStackTrace());
            }
        }, null);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removePropertyChangeListener'");
    }

    @Override
    public void backgroundProcess() {
        // TODO add
        em.ifPresent(manager -> {
            CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
            CriteriaUpdate<SessionEntity> update = criteriaBuilder.createCriteriaUpdate(SessionEntity.class);
            Root<SessionEntity> root = update.from(SessionEntity.class);
            update.set(root.get("isExpired"), true);
            update.where(criteriaBuilder.greaterThanOrEqualTo(root.get("insertDate"),
                    criteriaBuilder.sum(root.get("insertDate"), LocalTime.of(0, 30, 0).getLong(null))));
            EntityTransaction transaction = manager.getTransaction();
            transaction.begin();
            try {
                manager.createQuery(update).executeUpdate();
                transaction.commit();
            } catch (Exception e) {
                // TODO: handle exception
                transaction.rollback();
            }
        });
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
