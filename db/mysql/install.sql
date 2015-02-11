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
	attribute_nullable TINYINT(1),
	attribute_default VARCHAR(255),
	PRIMARY KEY(attribute_key),
	UNIQUE KEY(attribute_name),
	INDEX(attribute_key),
	INDEX(attribute_name)
);

CREATE TABLE IF NOT EXISTS prometheus_identities_attributes_indexes (
	index_key VARCHAR(36),
	index_name VARCHAR(255),
	index_attributes VARCHAR(255),
	PRIMARY KEY(index_key)
);

CREATE TABLE IF NOT EXISTS prometheus_repositories (
	repository_key VARCHAR(36),
	repository_name VARCHAR(255),
	repository_attribute VARCHAR(36),
	PRIMARY KEY(repository_key),
	UNIQUE KEY(repository_name)
);

CREATE TABLE IF NOT EXISTS prometheus_repositories_settings (
	setting_key VARCHAR(36),
    setting_repository VARCHAR(36),
    setting_name VARCHAR(255),
    setting_value VARCHAR(266),
    PRIMARY KEY(setting_key),
    UNIQUE KEY(setting_repository,setting_name),
    INDEX(setting_repository,setting_name)
);