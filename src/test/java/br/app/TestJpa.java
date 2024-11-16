package br.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

import app.entities.UserEntity;
import app.jpa_.JPAUtil;
import app.repositories.UserRepository;

//@Ignore
public class TestJpa {
	/*
	
	//OK
	//@Ignore
	@Test
	public void connection() {
		  try {
	            var em = JPAUtil.getEntityManager();
	            System.out.println("Conexão com o banco de dados estabelecida: " + (em != null));
	            em.close();
	        } catch (Exception e) {
	            System.err.println("Erro ao obter EntityManager: " + e.getMessage());
	            e.printStackTrace();
	        }
	}
	
	@Ignore
	@Test
	public void saveUser() {
		UserEntity user = new UserEntity("teste","teste@teste");
		UserRepository userRepository = new UserRepository();
		
		try {
			userRepository.save(user);
		}catch(Exception e) {
			System.err.println("Erro ao obter salvar: " + e.getMessage());
            e.printStackTrace();
		}
	}
	
	@Ignore
	@Test
	public void deleteUserById() {
		UserRepository userRepository = new UserRepository();
		
		UserEntity user = new UserEntity("teste","teste@teste");
		
		userRepository.save(user);
		
		userRepository.deleteById(1);
	}
	
	@Ignore
	@Test
	public void findById() {
		UserRepository userRepository = new UserRepository();
		
		userRepository.findById(3);
	}
		
	@Ignore
	@Test
	public void updateUser() {
		UserRepository userRepository = new UserRepository();
		UserEntity user = userRepository.findById(16);
		user.setName("Marc");
		
		userRepository.update(user);
	}
	
	//@Ignore
	@Test
	public void findAll() {
		UserRepository userRepository = new UserRepository();
		
		List<UserEntity> users = userRepository.findAll();
		
		users.forEach(i->System.out.println(i.getEmail()));
	}
	
	@Ignore
	@Test
	public void testCustomQuery() {
		UserRepository userRepository = new UserRepository();
		//"SELECT u FROM UserEntity u WHERE u.name = :name"
		String queryString = String.format(
    		    "SELECT e FROM %s e WHERE e.name = :name",
    		    UserEntity.class.getSimpleName()
    		);
		
		Map<String,Object> params = new HashMap<>();
		params.put("name", "Marc");
		
		List<UserEntity> users = userRepository.executeQuery(queryString, params);
		
		
        for (UserEntity user : users) {
            System.out.println("Found user: " + user.getName() + ", Email: " + user.getEmail());
        }
	}
	
	*/
		
}
