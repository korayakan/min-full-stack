package com.sample.min.fullstack.business.common.boundary;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.sample.min.fullstack.business.common.entity.BaseEntity;

@Stateless
public class Dao {

	@PersistenceContext
	private EntityManager em;

	public BaseEntity<?> save(final BaseEntity<?> entity) {
		if (entity.getId() == null) {
			em.persist(entity);
		} else {
			em.merge(entity);
		}
		return entity;
	}

	public <T extends Object> List<T> load(final Class<T> type) {
		final CriteriaBuilder builder = em.getCriteriaBuilder();
		final CriteriaQuery<T> query = builder.createQuery(type);
		query.from(type);
		return em.createQuery(query).getResultList();
	}

}
