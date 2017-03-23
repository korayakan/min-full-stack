package com.sample.min.fullstack.web.sample;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sample.min.fullstack.business.sample.boundary.SampleService;
import com.sample.min.fullstack.business.sample.entity.Sample;

@Named
@SessionScoped
public class SampleBean implements Serializable {

	private static final long serialVersionUID = 2901264282062240823L;

	@Inject
	private transient SampleService service;

	private List<Sample> list;

	@PostConstruct
	public void init() {
		list = service.load();
	}

	public List<Sample> getList() {
		return list;
	}

	public void setList(final List<Sample> list) {
		this.list = list;
	}

}
