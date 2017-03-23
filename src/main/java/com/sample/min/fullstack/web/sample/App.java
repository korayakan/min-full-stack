package com.sample.min.fullstack.web.sample;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.javafaker.Faker;
import com.sample.min.fullstack.business.common.boundary.Dao;
import com.sample.min.fullstack.business.sample.entity.Sample;

@Named
@ApplicationScoped
public class App implements Serializable {

	private static final long serialVersionUID = 8054918955471408120L;
	private static final Logger LOG = LoggerFactory.getLogger(App.class);

	@Inject
	private transient Dao dao;

	private String name;
	private String version;

	@PostConstruct
	public void init() {
		LOG.info("init app");
		initMetaData();
		initSampleData();
	}

	private void initMetaData() {
		InputStream in = null;
		try {
			in = getClass().getClassLoader().getResourceAsStream("buildInfo.properties");
			if (in != null) {
				final Properties props = new Properties();
				props.load(in);
				name = props.getProperty("build.name");
				version = props.getProperty("build.version");
			}
		} catch (final Exception e) {
			LOG.error(e.getMessage(), e);
		} finally {
			if (in != null) {
				IOUtils.closeQuietly(in);
			}
		}

	}

	private void initSampleData() {
		final Faker faker = new Faker();
		for (int i = 0; i < 10; i++) {
			final Sample sample = new Sample();
			sample.setSomeAttribute(faker.name().fullName());
			dao.save(sample);
		}

	}

	public String getName() {
		return name;
	}

	public String getVersion() {
		return version;
	}

}
