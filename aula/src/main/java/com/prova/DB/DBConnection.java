package com.prova.DB;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.prova.pessoa.Pessoa;
import com.prova.sala.Sala;

public class DBConnection {
	private static SessionFactory sessionFactory;

	private static String senha = "banco";

	private static Session session;

	private static SessionFactory construirSessaoFactory() {
		try {
			return new Configuration().setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
					.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/grupo3")
					.setProperty("hibernate.connection.username", "postgres")
					.setProperty("hibernate.connection.password", senha).setProperty("hibernate.jdbc.time_zone", "UTC")
					.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
					.setProperty("hibernate.show_sql", "true").setProperty("hibernate.format_sql", "false")
					.setProperty("hibernate.hbm2ddl.auto", "update")
					.setProperty("hibernate.connection.autocommit", "true").addAnnotatedClass(Sala.class)
					.setProperty("hibernate.connection.autocommit", "true").addAnnotatedClass(Pessoa.class)
					.buildSessionFactory();
		} catch (Throwable e) {
			System.err.println("A criação da SessionFactory inicial falhou: " + e);
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null)
			sessionFactory = construirSessaoFactory();
		return sessionFactory;
	}

	public static Session getSession() {
		getSessionFactory();
		if (session == null)
			session = sessionFactory.openSession();
		return session;
	}
}