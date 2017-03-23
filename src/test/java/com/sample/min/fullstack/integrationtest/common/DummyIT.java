package com.sample.min.fullstack.integrationtest.common;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.sample.min.fullstack.business.common.boundary.Dao;
import com.sample.min.fullstack.business.sample.boundary.SampleService;
import com.sample.min.fullstack.business.sample.entity.Sample;

public class DummyIT extends Arquillian {

	private static final Logger LOG = LoggerFactory.getLogger(DummyIT.class);

	@Inject
	private Dao dao;

	@Inject
	private SampleService service;

	@Deployment
	public static WebArchive createDeployment() {
		// get all maven dependecies
		final File[] files = Maven.resolver().loadPomFromFile("pom.xml").importCompileAndRuntimeDependencies().resolve()
				.withTransitivity().asFile();
		final List<File> deps = new ArrayList<>(files.length - 1);
		for (int i = 0; i < files.length; i++) {
			final File file = files[i];
			if (!file.getName().contains("slf4j-jdk14")) {
				deps.add(file);
			}
		}
		return ShrinkWrap.create(WebArchive.class).addAsLibraries(deps.toArray(new File[deps.size()]))
				.addPackages(true, "com.sample.min.fullstack.business", "com.sample.min.fullstack.web")
				.addAsResource(new File("src/main/resources/META-INF/persistence.xml"), "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));
	}

	@BeforeMethod
	public void setup() {
	}

	@Test
	public void testDummy() {
		final Faker faker = new Faker();
		for (int i = 0; i < 10; i++) {
			final Sample sample = new Sample();
			sample.setSomeAttribute(faker.name().fullName());
			dao.save(sample);
		}
		assertEquals(10, service.load().size());
		LOG.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
	}

}
