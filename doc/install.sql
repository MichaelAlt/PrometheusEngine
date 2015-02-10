DROP DATABASE IF EXISTS prometheus;
CREATE DATABASE IF NOT EXISTS prometheus;

USE prometheus;

CREATE TABLE IF NOT EXISTS prometheus_configuration (
	entry_key VARCHAR(36),
	entry_name VARCHAR(255),
	entry_value VARCHAR(255),
	PRIMARY KEY(entry_key),
	UNIQUE KEY(entry_name),
	INDEX(entry_key),
	INDEX(entry_name)
);

CREATE TABLE IF NOT EXISTS prometheus_identities (
	identity_key VARCHAR(36),
	identity_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
	identity_changed TIMESTAMP,
	PRIMARY KEY(identity_key),
	INDEX(identity_key)
);

CREATE TABLE IF NOT EXISTS prometheus_identities_attributes (
	attribute_key VARCHAR(36),
	attribute_name VARCHAR(255),
	attribute_type INT(3),
	attribute_length INT(3),
	attribute_index TINYINT(1),
	PRIMARY KEY(attribute_key),
	UNIQUE KEY(attribute_name),
	INDEX(attribute_key),
	INDEX(attribute_name)
);

CREATE TABLE IF NOT EXISTS prometheus_repositories (
	repository_key VARCHAR(36),
	repository_name VARCHAR(255),
	repository_attribute VARCHAR(255),
	PRIMARY KEY(repository_key),
	UNIQUE KEY(repository_name)
);