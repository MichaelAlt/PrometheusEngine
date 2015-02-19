# TABLE: prometheus_identities_attributes
This table contains all the attributes that have been created for the identities. All fields in this table correspond will fields in the
prometheus_identities table. 

| Field              | Type         | Null | Key         |
| ------------------ | ------------ | ---- | ----------- |
| attribute_key      | VARCHAR(36)  | NO   | PRIMARY KEY |
| attribute_name     | VARCHAR(255) | NO   | UNIQUE KEY  |
| attribute_type     | INT(3)       | NO   |             |
| attribute_length   | INT(3)       | NO   |             |
| attribute_nullable | TINYINT(1)   | NO   |             |
| attribute_default  | VARCHAR(255) | YES  |             |

## FIELD: attribute_key
This field contains a unique 36 digits long unique key for the attribute.

## FIELD: attribute_name
This field contains the unique name of the attribute.

## FIELD: attribute_type
This field contains the data type of the attribute.
- 1 = VARCHAR
- 2 = INT
- 3 = DOUBLE

## FIELD: attribute_length
This field contains the length of the attribute.

## FIELD: attribute_nullable
This field specifies if the attribute is nullable or not.

## FIELD: attribute_default
This field specifies an optional default value for the attribute.