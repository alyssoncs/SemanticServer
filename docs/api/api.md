# Semantic Server

The Semantic Server is a web service that maps the relationships between wireless devices with persons or locations.

## Resources

There are seven resources that the Semantic Server keeps, they are:

* Buildings
* Sections
* Rooms
* Users
* Roles
* Mhubs
* Things

Rooms belog to Sections that belongs to Buildings. Mhubs and Things belogs to either Users or Rooms. and Users might have Roles.

## REST API

The Semantic Server uses the base `/api` URL for every REST operation.
Thus, assuming that the server has been deployed at `http://localhost:8080`, all HTTP calls **must** contain `http://localhost:8080/api`. 

## Things

### **POST**  `/things`
#### Register a new thing
This operation inserts a new thing, expressed in the JSON format, into the Semantic Server.
**Note that all Thing fields are required**. 
If everything goes well, Semantic Server returns a response with HTTP status code 201 (CREATED), otherwise it returns a response with HTTP status code 400 (BAD REQUEST). 

 * Full URL: `http://localhost:8080/api` **`/things`**
 * HTTP Method: **POST**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | Thing JSON |

Example:

```json
{
    "thing": {
        "UUID":        "7d53edbe-2feb-4e63-a1d5-64334587a2df",
        "description": "LSDi bluetooth beacon",
        "holderID":    1248
   }
}
```

Where `holderID` is the ID of either a User or a Room (The IDs of Users and Rooms are garanteed to be mutually exclusive).

##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 201 (CREATED)      |
| Payload   | Thing created      |


```json
{
    "thing": {
        "id":          15,
        "UUID":        "7d53edbe-2feb-4e63-a1d5-64334587a2df",
        "description": "LSDi bluetooth beacon",
        "holderID":    1248
    }
}
```

### **PUT**  `/things/{id}`
#### Update an existing thing
This operation update an existing thing with id equals `{id}`. The new values should be expressed in the JSON format.
**Note that all Thing fields are required**. 
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST). 

 * Full URL: `http://localhost:8080/api` **`/things/{id}`**
 * HTTP Method: **PUT**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | Thing JSON |
|{id}  | The id of the thing|

Example:

```json
{
    "thing": {
        "UUID":        "7d53edbe-2feb-4e63-a1d5-64334587a2df",
        "description": "LSDi bluetooth beacon",
        "holderID":    2359
   }
}
```

Where `holderID` is the ID of either a User or a Room (The IDs of Users and Rooms are garanteed to be mutually exclusive).

##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 200 (OK)           |
| Payload   | Thing created      |


```json
{
    "thing": {
        "id":          15,
        "UUID":        "7d53edbe-2feb-4e63-a1d5-64334587a2df",
        "description": "LSDi bluetooth beacon",
        "holderID":    2359
    }
}
```

### **DELETE**  `/things/{id}`
#### Delete an existing thing
This operation deletes an existing thing with id equals `{id}` from the Semantic Server.
**Note that all Thing fields are required**. 
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST). 

 * Full URL: `http://localhost:8080/api` **`/things/{id}`**
 * HTTP Method: **DELETE**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | Empty           |
|{id}  | The id of the thing|


##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 200 (OK)           |
| Payload   | Thing deleted      |


```json
{
    "thing": {
        "id":          15,
        "UUID":        "7d53edbe-2feb-4e63-a1d5-64334587a2df",
        "description": "LSDi bluetooth beacon",
        "holderID":    2359
    }
}
```


### **GET**  `/things/{id}`
#### Retrieves an existing thing
This operation update an existing thing with id equals `{id}`, from the Semantic Server.
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST). 

 * Full URL: `http://localhost:8080/api` **`/things/{id}`**
 * HTTP Method: **GET**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | Thing JSON |
|{id}  | The id of the thing|


##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 200 (OK)           |
| Payload   | Thing retrieved    |


```json
{
    "thing": {
        "id":          15,
        "UUID":        "7d53edbe-2feb-4e63-a1d5-64334587a2df",
        "description": "LSDi bluetooth beacon",
        "holderID":    2359
    }
}
```


### **GET**  `/things`
#### Retrieves all the things
This operation retrieves all the existing things from the Semantic Server.
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST). 

 * Full URL: `http://localhost:8080/api` **`/things`**
 * HTTP Method: **GET**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
|  | |


##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 200 (OK)           |
| Payload   | Array of the things retrieved |


```json
{
    "things": [
        {
            "id":          15,
            "UUID":        "7d53edbe-2feb-4e63-a1d5-64334587a2df",
            "description": "LSDi bluetooth beacon",
            "holderID":    2359
        },
        {
            "id":          14,
            "UUID":        "8d53edbe-2feb-4e63-a1d5-64334587a2df",
            "description": "LSDi bluetooth beacon 2",
            "holderID":    2363
        },
        {
            "id":          13,
            "UUID":        "7d53edbe-2feb-4e63-a1d5-64334587a2df",
            "description": "Tercio's bluetooth beacon",
            "holderID":    2360
        }
    ]
}
```

## Mhubs

### **POST**  `/mhubs`
#### Register a new mhub
This operation inserts a new mhub, expressed in the JSON format, into the Semantic Server.
**Note that all mhub fields are required**. 
If everymhub goes well, Semantic Server returns a response with HTTP status code 201 (CREATED), otherwise it returns a response with HTTP status code 400 (BAD REQUEST). 

 * Full URL: `http://localhost:8080/api` **`/mhubs`**
 * HTTP Method: **POST**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | mhub JSON |

Example:

```json
{
    "mhub": {
        "UUID":        "7d53edbe-2feb-4e63-a1d5-64334587a2df",
        "description": "LSDi moto G",
        "holderID":    1248
   }
}
```

Where `holderID` is the ID of either a User or a Room (The IDs of Users and Rooms are garanteed to be mutually exclusive).

##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 201 (CREATED)      |
| Payload   | mhub created      |


```json
{
    "mhub": {
        "id":          15,
        "UUID":        "7d53edbe-2feb-4e63-a1d5-64334587a2de",
        "description": "LSDi moto G",
        "holderID":    1248
    }
}
```

### **PUT**  `/mhubs/{id}`
#### Update an existing mhub
This operation update an existing mhub with id equals `{id}`. The new values should be expressed in the JSON format.
**Note that all mhub fields are required**. 
If everymhub goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST). 

 * Full URL: `http://localhost:8080/api` **`/mhubs/{id}`**
 * HTTP Method: **PUT**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | mhub JSON |
|{id}  | The id of the mhub|

Example:

```json
{
    "mhub": {
        "UUID":        "7d53edbe-2feb-4e63-a1d5-64334587a2de",
        "description": "LSDi moto G",
        "holderID":    2359
   }
}
```

Where `holderID` is the ID of either a User or a Room (The IDs of Users and Rooms are garanteed to be mutually exclusive).

##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 200 (OK)           |
| Payload   | mhub created      |


```json
{
    "mhub": {
        "id":          15,
        "UUID":        "7d53edbe-2feb-4e63-a1d5-64334587a2de",
        "description": "LSDi moto G",
        "holderID":    2359
    }
}
```

### **DELETE**  `/mhubs/{id}`
#### Delete an existing mhub
This operation deletes an existing mhub with id equals `{id}` from the Semantic Server.
**Note that all mhub fields are required**. 
If everymhub goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST). 

 * Full URL: `http://localhost:8080/api` **`/mhubs/{id}`**
 * HTTP Method: **DELETE**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | Empty           |
|{id}  | The id of the mhub|


##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 200 (OK)           |
| Payload   | mhub deleted      |


```json
{
    "mhub": {
        "id":          15,
        "UUID":        "7d53edbe-2feb-4e63-a1d5-64334587a2de",
        "description": "LSDi moto G",
        "holderID":    2359
    }
}
```


### **GET**  `/mhubs/{id}`
#### Retrieves an existing mhub
This operation update an existing mhub with id equals `{id}`, from the Semantic Server.
If everymhub goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST). 

 * Full URL: `http://localhost:8080/api` **`/mhubs/{id}`**
 * HTTP Method: **GET**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | mhub JSON |
|{id}  | The id of the mhub|


##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 200 (OK)           |
| Payload   | mhub retrieved    |


```json
{
    "mhub": {
        "id":          15,
        "UUID":        "7d53edbe-2feb-4e63-a1d5-64334587a2de",
        "description": "LSDi moto G",
        "holderID":    2359
    }
}
```


### **GET**  `/mhubs`
#### Retrieves all the mhubs
This operation retrieves all the existing mhubs from the Semantic Server.
If everymhub goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST). 

 * Full URL: `http://localhost:8080/api` **`/mhubs`**
 * HTTP Method: **GET**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
|  | |


##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 200 (OK)           |
| Payload   | Array of the mhubs retrieved |


```json
{
    "mhubs": [
        {
            "id":          15,
            "UUID":        "7d53edbe-2feb-4e63-a1d5-64334587a2de",
            "description": "LSDi moto G",
            "holderID":    2359
        },
        {
            "id":          14,
            "UUID":        "8d53edbe-2feb-4e63-a1d5-64334587a2de",
            "description": "LSDi moto G2",
            "holderID":    2363
        },
        {
            "id":          13,
            "UUID":        "7d53edbe-2feb-4e63-a1d5-64334587a2de",
            "description": "Rodolfo's Iphone",
            "holderID":    2361
        }
    ]
}
```
