# TABLE: prometheus_identities_history
This table contains the history values of the identities and it's attributes.

| Field             | Type         | Null | Key         |
| ----------------- | ------------ | ---- | ----------- |
| history_key       | VARCHAR(36)  | NO   | PRIMARY KEY |
| history_identity  | VARCHAR(36)  | NO   |             |
| history_attribute | VARCHAR(255  | NO   |             |
| history_value     | VARCHAR(255) | NO   |             |
| history_created   | TIMESTAMP    | NO   |             |


## FIELD: history_key
This field contains a unique 36 digits long key for the history entry.

## FIELD: history_identity
This field contains the 36 digits long unique key of the identity.

## FIELD: history_attribute
This field contains the unique name of the changed attribute.

## FIELD: history_value
This field contains the last value of the changed attribute.

##FIELD: history_created
This field contains the timestamp the attribute was changed.