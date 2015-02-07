DROP DATABASE IF EXISTS prometheus;
CREATE DATABASE IF NOT EXISTS prometheus;

USE prometheus;

CREATE TABLE IF NOT EXISTS prometheus_configuration (
	entry_key VARCHAR(36),
	entry_name VARCHAR(255),
	entry_value VARCHAR(255),
	PRIMARY KEY(entry_key),
	UNIQUE KEY(entry_name),
	INDEX(entry_name)
)