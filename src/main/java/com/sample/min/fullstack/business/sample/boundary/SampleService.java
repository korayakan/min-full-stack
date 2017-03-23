package com.sample.min.fullstack.business.sample.boundary;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.sample.min.fullstack.business.common.boundary.Dao;
import com.sample.min.fullstack.business.sample.entity.Sample;

@Stateless
public class SampleService {

	@Inject
	private Dao dao;

	public List<Sample> load() {
		return dao.load(Sample.class);
	}

}
