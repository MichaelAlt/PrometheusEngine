# TABLE: prometheus_configuration
This table contains all configuration properties for the application. These properties will be loaded
after the startup of the application and are available 

| Field                | Type         | Null | Key         |
| -------------------- | ------------ | ---- | ----------- |
| configuration_key    | VARCHAR(36)  | NO   | PRIMARY KEY |
| configuration_name   | VARCHAR(255) | NO   | UNIQUE KEY  |
| configuration_value  | VARCHAR(255) | NO   |             |
| configuration_domain | VARCHAR(255) | YES  |             |

## FIELD: configuration_key
This field contains an 36 digits long unique key for the configuration property.

## FIELD: configuration_name
This field contains the unique human readable name of the configuration property.

## FIELD: configuration_value
This field contains the value of the configuration property.

## FIELD: configuration_domain
This field contains a domain name for the configuration property. If the field has a value, the configuration property
can only be accessed by a module with the same domain.