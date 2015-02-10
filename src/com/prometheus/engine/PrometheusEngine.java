package com.prometheus.engine;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Service;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.ContextConfig;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.apache.tomcat.util.descriptor.web.FilterMap;

import com.prometheus.core.configuration.ConfigurationContext;
import com.prometheus.core.database.DatabaseManager;
import com.prometheus.core.exception.ConfigurationException;
import com.prometheus.core.modules.ModuleConfiguration;
import com.prometheus.core.modules.ModuleManager;
import com.prometheus.core.modules.annotations.ModuleType.ModuleTypes;
import com.prometheus.core.security.SecurityFilter;

/**
 * 
 * @author alt
 *
 */
public class PrometheusEngine {

	/**
	 * 
	 * @param args
	 * @throws ServletException
	 * @throws LifecycleException
	 * @throws ConfigurationException
	 */
	public static void main(String[] args) {
		PrometheusEngine engine = new PrometheusEngine();
	}

	/**
	 * 
	 * @throws ServletException
	 * @throws LifecycleException
	 * @throws ConfigurationException
	 */
	public PrometheusEngine() {

		System.setProperty("user.dir", "C:/Users/alt/Documents/GitHub/PrometheusEngine");
		
		try {

			DatabaseManager databaseManager = new DatabaseManager();
			ModuleManager moduleManager = new ModuleManager();

			Connector connector = getConnector();

			Tomcat tomcat = new Tomcat();
			tomcat.setBaseDir(System.getProperty("user.dir") + "/");
			tomcat.setConnector(connector);
			tomcat.enableNaming();
			tomcat.setSilent(true);

			Service service = tomcat.getService();
			service.addConnector(connector);

			Context defaultContext = tomcat.addWebapp("/", System.getProperty("user.dir") + "/web/");
			defaultContext.addLifecycleListener(new ContextConfig());

			databaseManager.getConnections(defaultContext);
			
			FilterDef security = new FilterDef();
			security.setFilterName("security");
			security.setFilterClass(SecurityFilter.class.getName());

			FilterMap filterMap = new FilterMap();
			filterMap.setFilterName("security");
			filterMap.addURLPattern("/*");

			defaultContext.addFilterDef(security);
			defaultContext.addFilterMap(filterMap);
			defaultContext.addWelcomeFile("index");

			for (ModuleConfiguration module : moduleManager.getModules()) {

				if (module.getModuleType() == ModuleTypes.API) {
					Tomcat.addServlet(defaultContext, module.getModuleName(), module.getModuleClass());
					defaultContext.addServletMapping("/api/" + module.getModuleName(), module.getModuleName());
				} else if (module.getModuleType() == ModuleTypes.WEB) {
					Tomcat.addServlet(defaultContext, module.getModuleName(), module.getModuleClass());
					defaultContext.addServletMapping("/" + module.getModuleName(), module.getModuleName());
				}
			}

			tomcat.start();
			tomcat.getServer().await();

		} catch (ServletException e) {
			e.printStackTrace();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		} catch (LifecycleException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return
	 */
	private static Connector getConnector() {

		Connector connector = new Connector();

		connector.setPort(Integer.valueOf((String) ConfigurationContext.get("server.port")));
		connector.setAttribute("server", "Prometheus");

		connector.setScheme("http");
		//
		// connector.setSecure(true);
		// connector.setScheme("https");
		// if (((String)
		// configuration.get("ssl.keystore.path")).startsWith("/")) {
		// connector.setAttribute("keystoreFile",
		// configuration.get("ssl.keystore.path"));
		// } else {
		// connector.setAttribute("keystoreFile", System.getProperty("user.dir")
		// + configuration.get("ssl.keystore.path"));
		// }
		// connector.setAttribute("keystorePass",
		// configuration.get("ssl.keystore.pass"));
		// connector.setAttribute("keyAlias",
		// configuration.get("ssl.keystore.alias"));
		// connector.setAttribute("clientAuth", "false");
		// connector.setAttribute("sslProtocol", "TLS");
		// connector.setAttribute("SSLEnabled", true);

		return connector;
	}

}