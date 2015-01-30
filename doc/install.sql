DROP DATABASE IF EXISTS prometheus;
CREATE DTABASE IF NOT EXISTS prometheus;

USE prometheus;

CREATE TABLE IF NOT EXISTS prometheus_configuration (
	entry_key VARCHAR(255),
	entry_value VARCHAR(255),
	PRIMARY KEY(entry_key)
)