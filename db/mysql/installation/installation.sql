DROP DATABASE IF EXISTS prometheus;
CREATE DATABASE IF NOT EXISTS prometheus;

USE prometheus;

CREATE TABLE IF NOT EXISTS prometheus_configuration (
	configuration_key VARCHAR(36) NOT NULL,
	configuration_name VARCHAR(255) NOT NULL,
	configuration_value VARCHAR(255) NOT NULL,
	configuration_domain VARCHAR(255) NULL,
	PRIMARY KEY(configuration_key),
	UNIQUE KEY(configuration_name),
	INDEX(configuration_key),
	INDEX(configuration_name)
);

CREATE TABLE IF NOT EXISTS prometheus_identities (
	identity_key VARCHAR(36) NOT NULL,
	identity_unique VARCHAR(255) NOT NULL,
	identity_created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	identity_changed TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY(identity_key),
	UNIQUE KEY(identity_unique),
	INDEX(identity_key)
);

CREATE TABLE IF NOT EXISTS prometheus_identities_history (
	history_key VARCHAR(36) NOT NULL,
	history_identity VARCHAR(36) NOT NULL,
	history_attribute VARCHAR(255) NOT NULL,
	history_value VARCHAR(255) NULL,
	history_created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY(history_key),
	INDEX(history_identity,history_attribute)
);

CREATE TABLE IF NOT EXISTS prometheus_identities_attributes (
	attribute_key VARCHAR(36) NOT NULL,
	attribute_name VARCHAR(255) NOT NULL,
	attribute_type INT(3) NOT NULL,
	attribute_length INT(3) NOT NULL,
	attribute_nullable TINYINT(1) NOT NULL,
	attribute_default VARCHAR(255) NULL,
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
	repository_key VARCHAR(36) NOT NULL,
	repository_name VARCHAR(255) NOT NULL,
	repository_attribute VARCHAR(36) NOT NULL,
	PRIMARY KEY(repository_key),
	UNIQUE KEY(repository_name)
);

CREATE TABLE IF NOT EXISTS prometheus_repositories_settings (
	setting_key VARCHAR(36) NOT NULL,
    setting_repository VARCHAR(36) NOT NULL,
    setting_name VARCHAR(255) NOT NULL,
    setting_value VARCHAR(255) NOT NULL,
    PRIMARY KEY(setting_key),
    UNIQUE KEY(setting_repository,setting_name),
    INDEX(setting_repository,setting_name)
);