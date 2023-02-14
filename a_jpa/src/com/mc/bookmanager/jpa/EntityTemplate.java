package com.mc.bookmanager.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityTemplate {
	
	//EntityManagerFactory는 thread safe하기 때문에 static에 올려놓고 사용하거나 singleTon으로 사용해도 안전
	private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("a_jpa");
	
	//EntityManager는 thread safe하지 않기 때문에 반드시 지역에서 생성
	public static EntityManager getEntityManager() {
		return EMF.createEntityManager();
	}
	
	
	

}
