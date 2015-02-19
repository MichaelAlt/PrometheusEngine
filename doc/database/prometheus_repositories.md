# TABLE: prometheus_repositories
This table contains all the values of the identities. Initially it has only three fields. By configuring identity attributes, this table
will be expanded with additional fields.

| Field                | Type         | Null | Key         |
| -------------------- | ------------ | ---- | ----------- |
| repository_key       | VARCHAR(36)  | NO   | PRIMARY KEY |
| repository_name      | VARCHAR(255) | NO   | UNIQUE KEY  |
| repository_attribute | VARCHAR(36)  | NO   |             |

## FIELD: repository_key
This field contains a unique 36 digits long unique key of the repository.

## FIELD: repository_name
This field contains the unique name of the repository.

## FIELD: repository_attribute
This field contains the 36 digits long unique key of the attribute that is assigned to this repository.