package com.prometheus.engine;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Service;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.core.StandardServer;
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
	 */
	public static void main(String[] args) {

		PrometheusEngine engine = new PrometheusEngine();
		engine.startServer();
	}

	/**
	 * 
	 */
	public PrometheusEngine() {

	}

	/**
	 * 
	 */
	private void startServer() {

		System.setProperty("user.dir", "C:/Users/alt/Documents/GitHub/PrometheusEngine");

		try {

			DatabaseManager databaseManager = new DatabaseManager();
			ModuleManager moduleManager = new ModuleManager();

			Connector connector = getConnector();

			Tomcat tomcat = new Tomcat();
			tomcat.setBaseDir(System.getProperty("user.dir") + "/");
			tomcat.setConnector(connector);
			tomcat.enableNaming();

			StandardServer server = (StandardServer) tomcat.getServer();
			AprLifecycleListener listener = new AprLifecycleListener();
			server.addLifecycleListener(listener);

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
			System.exit(1);
		} catch (LifecycleException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * 
	 * @return
	 */
	private Connector getConnector() throws Exception {

		Connector connector = new Connector();

		connector.setPort(Integer.valueOf((String) ConfigurationContext.get("server.port")));
		connector.setAttribute("server", "Prometheus");

		connector.setSecure(true);
		connector.setScheme("https");
		if (ConfigurationContext.get("server.ssl.keystore").startsWith("/")) {
			connector.setAttribute("KeystoreFile", ConfigurationContext.get("server.ssl.keystore"));
		} else {
			connector.setAttribute("KeystoreFile", System.getProperty("user.dir") + "/" + ConfigurationContext.get("server.ssl.keystore"));
		}
		connector.setAttribute("KeystorePass", ConfigurationContext.get("server.ssl.keystorepass"));
		connector.setAttribute("KeyAlias", ConfigurationContext.get("server.ssl.keyalias"));
		connector.setAttribute("ClientAuth", "false");
		connector.setAttribute("SSLProtocol", "TLS");
		connector.setAttribute("SSLEnabled", true);

		return connector;
	}

}