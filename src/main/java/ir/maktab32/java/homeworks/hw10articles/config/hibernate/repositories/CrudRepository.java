package ir.maktab32.java.homeworks.hw10articles.config.hibernate.repositories;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class CrudRepository<Entity, ID extends Serializable> {

    protected abstract Class<Entity> getEntityClass();

    protected abstract Session getSession();

    public Entity save(Entity entity){
        getSession().beginTransaction();
        getSession().save(entity);
        getSession().getTransaction().commit();
        return entity;
    }

    public Entity update(Entity entity){
        getSession().beginTransaction();
        getSession().update(entity);
        getSession().getTransaction().commit();
        return entity;
    }

    public void remove(Entity entity){
        getSession().beginTransaction();
        getSession().remove(entity);
        getSession().getTransaction().commit();
    }

    public void removeById(ID id){
        Entity entity = findById(id);
        if (entity != null){
            getSession().beginTransaction();
            getSession().remove(entity);
            getSession().getTransaction().commit();
        }
    }

    public List<Entity> findAll(){
        getSession().beginTransaction();
        Query<Entity> query = getSession().createQuery("from " + getEntityClass().getName(), getEntityClass());
        List<Entity> entities = query.list();
        getSession().getTransaction().commit();
        return entities;
    }

    public Entity findById(ID id){
        getSession().beginTransaction();
        Entity entity = getSession().load(getEntityClass(), id);
        getSession().getTransaction().commit();
        return entity;
    }

    public boolean isExisting(ID id){
        try {
            findById(id).equals(null);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<Entity> findAll(Predicate<Entity> predicate){
        List<Entity> allEntities = findAll();
        List<Entity> result = new ArrayList<>();;
        if (allEntities != null && !allEntities.isEmpty()) {
            getSession().beginTransaction();
            try {
                allEntities.stream().filter(predicate).forEach(result::add);
            }catch (Exception e){
                System.out.println("\t\u26a0 Invalid Predicate!");
                System.out.println("\t\u26a0 " + e.getMessage());
            }
            getSession().getTransaction().commit();
        }
        return result;
    }

    public <T> List<T> findAll(Function<Entity,T> function){
        List<Entity> allEntities = findAll();
        List<T> result = new ArrayList();
        if (allEntities != null && !allEntities.isEmpty()){
            getSession().beginTransaction();
            try {
                allEntities.stream().map(function).forEach(result::add);
            }catch (Exception e){
                System.out.println("\t\u26a0 Invalid Function!");
                System.out.println("\t\u26a0 " + e.getMessage());
            }

            getSession().getTransaction().commit();
        }
        return result;
    }
}
