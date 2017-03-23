package com.sample.min.fullstack.business.sample.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.sample.min.fullstack.business.common.entity.BaseEntity;

@Entity
public class Sample extends BaseEntity<Long> {

	@Id
	@GeneratedValue
	private Long id;

	private String someAttribute;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getSomeAttribute() {
		return someAttribute;
	}

	public void setSomeAttribute(final String someAttribute) {
		this.someAttribute = someAttribute;
	}

}
