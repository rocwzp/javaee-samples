package com.sivalabs.tweetertests.ejbs;

import java.io.File;

import javax.ejb.EJB;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.formatter.Formatters;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.sivalabs.tweeter.ejbs.SocialService;
import com.sivalabs.tweeter.ejbs.TweeterServiceBean;
import com.sivalabs.tweeter.entities.User;

/**
 * @author Siva
 *
 */
@RunWith(Arquillian.class)
public class TweeterServiceBeanTest
{

	private static final String WEBAPP_SRC = "src/main/webapp";
	
	@EJB
	private TweeterServiceBean tweeterServiceBean;
	
	@Inject
	private SocialService socialService;
	
	/*
	@Deployment
    public static JavaArchive createDeployment() {
		JavaArchive jar = ShrinkWrap.create(JavaArchive.class,"tweeter.jar")
				.addPackages(true, "com.sivalabs.tweeter")
				.addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
		        .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
		    System.out.println(jar.toString(true));
		    return jar;
    }
	*/
	
	
	@Deployment
    public static WebArchive createDeployment() {
		
		WebArchive war = ShrinkWrap.create(WebArchive.class, "tweeter1.war");
		war.merge(ShrinkWrap.create(GenericArchive.class).as(ExplodedImporter.class)
		    .importDirectory(WEBAPP_SRC).as(GenericArchive.class),
		    "/", Filters.includeAll());
		
		//WebArchive war =  ShrinkWrap.create(WebArchive.class, "tweeter1.war");
		
		war.addPackages(true, "com.sivalabs.tweeter")
        		
                .addAsResource("META-INF/persistence.xml")
                //.addAsWebInfResource("jbossas-ds.xml")
               // .addAsWebResource(new File(WEBAPP_SRC,"index.xhtml"))
                //.addAsWebInfResource(new StringAsset("<faces-config version=\"2.0\"/>"),"faces-config.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));
		System.out.println(war.toString(Formatters.VERBOSE));
		return war;
    }
	
	
	
	@Test
	public void testLogin()
	{
		System.out.println("socialService: "+socialService);
		socialService.tweet("helloooooooooooooooooooo");
		System.out.println("tweeterServiceBean: "+tweeterServiceBean);
		User user = tweeterServiceBean.login("admin", "admin");
		System.out.println(user);
	}
	
}
