# TABLE: prometheus_identities
This table contains all the values of the identities. Initially it has only three fields. By configuring identity attributes, this table
will be expanded with additional fields.

| Field            | Type        | Null | Key         |
| ---------------- | ----------- | ---- | ----------- |
| identity_key     | VARCHAR(36) | NO   | PRIMARY KEY |
| identity_created | TIMESTAMP   | NO   |             |
| identity_changed | TIMESTAMP   | NO   |             |

## FIELD: identity_key
This field contains a unique 36 digits long unique key for the identity.

## FIELD: identity_created
This field contains the timestamp the identity was created. It is automatically filled.

## FIELD: identity_changed
This field contains the timestamp the identitiy was last changed.