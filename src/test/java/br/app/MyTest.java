package br.app;

import javax.inject.Inject;

import org.junit.Test;

import app.jpa_.JPAUtil;

public class MyTest {
	@Test
	public void testHibernateUtil() {
		 System.out.println(JPAUtil.getEntityManager());
	
	}
}
