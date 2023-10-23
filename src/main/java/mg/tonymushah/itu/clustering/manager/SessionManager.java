package mg.tonymushah.itu.clustering.manager;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.catalina.Context;
import org.apache.catalina.Session;
import org.apache.catalina.SessionIdGenerator;
import org.hibernate.PersistentObjectException;

import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import mg.tonymushah.itu.clustering.entities.SessionEntity;

public class SessionManager extends AbstractSessionManager {
    private Optional<EntityManager> em = Optional.empty();
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

    public SessionManager(EntityManagerFactory entityManagerFactory) throws ClassNotFoundException, IOException {
        this.entityManagerFactory = entityManagerFactory;
        this.load();
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
                if (session instanceof CustomSession) {
                    CustomSession customSession = ((CustomSession) session);
                    var sessionEntity = customSession.getEntity();
                    if (!entityManager.contains(sessionEntity)) {
                        try {
                            entityManager.persist(sessionEntity);
                        } catch (PersistentObjectException e) {
                            // TODO: handle exception
                            e.printStackTrace(System.err);
                        }
                    }
                    customSession.refreshSession();
                }
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw new RuntimeException(e.getMessage(), e.getCause());
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
        return this.createSession(UUID.randomUUID().toString());
    }

    @Override
    public Session createSession(String sessionId) {
        Optional<String> id = Optional.ofNullable(sessionId).filter(inner -> !inner.isEmpty() && !inner.isBlank());
        var session = new CustomSession(new SessionEntity(id.orElse(null), null, new Date(), false));
        session.setManager(this);
        this.add(session);
        return session;
    }

    @Override
    public Session findSession(String id) throws IOException {
        System.out.println(String.format("Session id: '%s'", Optional.ofNullable(id).orElse("Null")));
        return em.map(manager -> {
            Optional<Session> inner = Optional.ofNullable(manager.find(SessionEntity.class, id)).map(data -> {
                try {
                    Session session = data.toSession();
                    session.setManager(this);
                    return session;
                } catch (JsonProcessingException e) {
                    // TODO Auto-generated catch block
                    throw new RuntimeException(e.getMessage(), e.getCause());
                }
            });
            return inner.orElseGet(() -> {
                System.out.println("Returned from session null");
                return null;
            });
        }).orElseGet(() -> {
            // System.out.println("Returned null");
            return null;
        });
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
            if (!transaction.isActive()) {
                transaction.begin();
                try {
                    if (session instanceof CustomSession) {
                        CustomSession customSession = ((CustomSession) session);
                        var sessionEntity = customSession.getEntity();
                        if (entityManager.contains(sessionEntity)) {
                            entityManager.remove(sessionEntity);
                        }
                    }
                    transaction.commit();
                } catch (Exception e) {
                    transaction.rollback();
                    throw new RuntimeException(e.fillInStackTrace());
                }
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
        /*
         * Optional.ofNullable(em).ifPresent(em -> {
         * em.ifPresent(manager -> {
         * EntityTransaction transaction = manager.getTransaction();
         * if (!transaction.isActive()) {
         * CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
         * CriteriaUpdate<SessionEntity> update =
         * criteriaBuilder.createCriteriaUpdate(SessionEntity.class);
         * Root<SessionEntity> root = update.from(SessionEntity.class);
         * update.set(root.get("isExpired"), true);
         * update.where(criteriaBuilder.greaterThanOrEqualTo(root.get("insertDate"),
         * criteriaBuilder.sum(root.get("insertDate"),
         * LocalTime.of(0, 30, 0).getLong(ChronoField.MICRO_OF_DAY))));
         * transaction.begin();
         * try {
         * manager.createQuery(update).executeUpdate();
         * transaction.commit();
         * } catch (Exception e) {
         * // TODO: handle exception
         * transaction.rollback();
         * }
         * }
         * 
         * });
         * });
         * 
         */

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
