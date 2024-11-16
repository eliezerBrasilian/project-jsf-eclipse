package app.jpa_;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//@ApplicationScoped
public class JPAUtil {

	private static EntityManagerFactory factory = null;

	static {
		init();
	}
	
	private static void init() {
		
		try {
			if (factory == null) 
					factory = Persistence.createEntityManagerFactory("meuprimeiroprojetojsf");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	

	//@Produces
	//@RequestScoped
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
	

	public Object getPrimaryKey(Object entity) {
		return factory.getPersistenceUnitUtil().getIdentifier(entity);
	}

}
