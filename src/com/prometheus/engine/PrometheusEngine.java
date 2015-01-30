package com.prometheus.engine;

import com.prometheus.core.configuration.ConfigurationContext;

public class PrometheusEngine {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		ConfigurationContext.get("test");
	}

}