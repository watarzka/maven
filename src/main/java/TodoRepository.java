import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

public class TodoRepository {
    List<Todo> findAll(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Todo> result = session.createQuery("from Todo", Todo.class).list();
        transaction.commit();
        session.close();
        return result;
    }

    Todo toggleTodo(Integer id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Todo result = session.get(Todo.class, id);
        result.setDone(!result.isDone());
        transaction.commit();
        session.close();
        return result;
    }
    Todo addTodo(Todo newTodo){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(newTodo);

        transaction.commit();
        session.close();

        return newTodo;
    }
}
