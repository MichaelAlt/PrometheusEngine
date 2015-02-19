# TABLE: prometheus_repositories_settings
This table contains specific settings for repositories.

| Field              | Type         | Null | Key         |
| -------------------| ------------ | ---- | ----------- |
| setting_key        | VARCHAR(36)  | NO   | PRIMARY KEY |
| setting_repository | VARCHAR(36)  | NO   |             |
| setting_name       | VARCHAR(255) | NO   |             |
| setting_value      | VARCHAR(255) | NO   |             |

## FIELD: setting_key
This field contains a unique 36 digits long unique key for the setting.

## FIELD: setting_repository
This field contains the 36 digitis long unique key of the repository.

## FIELD: setting_name
This field contains the unique name of the setting for one repository.

## FIELD: setting_value
This field contains the value of the setting.