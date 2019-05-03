package utils;

import org.hibernate.SessionFactory;

import javax.persistence.EntityManagerFactory;

public class HibernateUtil
{
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
//
//   public void shutdown()
//   {
//      getSessionFactory().close();
//   }
}
