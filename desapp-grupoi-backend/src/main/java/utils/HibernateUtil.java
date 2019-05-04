package utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.EntityManagerFactory;

public class HibernateUtil
{
    private Session session = null;
    private Transaction tsn = null;

    private SessionFactory sessionFactory;

    public HibernateUtil(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }
//   private static SessionFactory sessionFactory = buildSessionFactory();
//
//   private static SessionFactory buildSessionFactory()
//   {
//      try
//      {
//         if (sessionFactory == null)
//         {
//            Configuration configuration = new Configuration().configure(HibernateUtil.class.getResource("/hibernate.cfg.xml"));
//            StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
//            serviceRegistryBuilder.applySettings(configuration.getProperties());
//            ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
//            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//         }
//         return sessionFactory;
//      } catch (Throwable ex)
//      {
//         System.err.println("Initial SessionFactory creation failed." + ex);
//         throw new ExceptionInInitializerError(ex);
//      }
//   }
//
    public SessionFactory getSessionFactory(){
      return sessionFactory;
    }

    public Session getSession() {
        return session;
    }

    public Transaction getTransaction() {
        return tsn;
    }

    public void openSessionBeginTransaction(){
       this.session = this.getSessionFactory().openSession();
       this.tsn = session.beginTransaction();
    }

    public void transactionCommit(){
        this.tsn.commit();
    }

    public void transactionRollback(){
        this.tsn.rollback();
    }

    public void sessionClose()    {
      this.session.close();
      this.session = null;
      this.tsn = null;
    }
}
