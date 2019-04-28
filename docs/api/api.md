# Semantic Server

The Semantic Server is a web service that maps the relationships between wireless devices with persons or locations.

## Resources

There are seven resources that the Semantic Server keeps, they are:
* [Things](#Things)
* [Mhubs](#Mhubs)
* [Users](#Users)
* [Roles](#Roles)
* [Rooms](#Rooms)
* Sections
* Buildings

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
| Payload   | Thing updated      |


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
#### Retrieve an existing thing
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
#### Retrieve all the things
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
If everything goes well, Semantic Server returns a response with HTTP status code 201 (CREATED), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

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
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

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
| Payload   | mhub updated      |


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
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

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
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

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
#### Retrieve all the mhubs
This operation retrieves all the existing mhubs from the Semantic Server.
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

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

## Users

### **POST**  `/users`
#### Register a new user
This operation inserts a new user, expressed in the JSON format, into the Semantic Server.
**Note that all user fields are required**.
If everything goes well, Semantic Server returns a response with HTTP status code 201 (CREATED), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

 * Full URL: `http://localhost:8080/api` **`/users`**
 * HTTP Method: **POST**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | user JSON |

Example:

```json
{
    "user": {
        "name":   "Alysson Cirilo",
        "email":  "alysson.cirilo@lsdi.ufma.br",
        "roleID": 1248
   }
}
```

Where `roleID` is the ID of a Role.

##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 201 (CREATED)      |
| Payload   | user created       |


```json
{
    "user": {
        "id":     15,
        "name":   "Alysson Cirilo",
        "email":  "alysson.cirilo@lsdi.ufma.br",
        "roleID": 1248
    }
}
```

### **PUT**  `/users/{id}`
#### Update an existing user
This operation update an existing user with id equals `{id}`. The new values should be expressed in the JSON format.
**Note that all user fields are required**.
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

 * Full URL: `http://localhost:8080/api` **`/users/{id}`**
 * HTTP Method: **PUT**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | user JSON |
|{id}  | The id of the user|

Example:

```json
{
    "user": {
        "name":   "Alysson Cirilo",
        "email":  "alysson.cirilo@lsdi.ufma.br",
        "roleID": 2359
   }
}
```

Where `roleID` is the ID of a Role.

##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 200 (OK)           |
| Payload   | user updated      |


```json
{
    "user": {
        "id":     15,
        "name":   "Alysson Cirilo",
        "email":  "alysson.cirilo@lsdi.ufma.br",
        "roleID": 2359
    }
}
```

### **DELETE**  `/users/{id}`
#### Delete an existing user
This operation deletes an existing user with id equals `{id}` from the Semantic Server.
**Note that all user fields are required**.
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

 * Full URL: `http://localhost:8080/api` **`/users/{id}`**
 * HTTP Method: **DELETE**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | Empty           |
|{id}  | The id of the user|


##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 200 (OK)           |
| Payload   | user deleted      |


```json
{
    "user": {
        "id":     15,
        "name":   "Alysson Cirilo",
        "email":  "alysson.cirilo@lsdi.ufma.br",
        "roleID": 2359
    }
}
```


### **GET**  `/users/{id}`
#### Retrieve an existing user
This operation update an existing user with id equals `{id}`, from the Semantic Server.
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

 * Full URL: `http://localhost:8080/api` **`/users/{id}`**
 * HTTP Method: **GET**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | user JSON |
|{id}  | The id of the user|


##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 200 (OK)           |
| Payload   | user retrieved    |


```json
{
    "user": {
        "id":     15,
        "name":   "Alysson Cirilo",
        "email":  "alysson.cirilo@lsdi.ufma.br",
        "roleID": 2359
    }
}
```


### **GET**  `/users`
#### Retrieve all the users
This operation retrieves all the existing users from the Semantic Server.
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

 * Full URL: `http://localhost:8080/api` **`/users`**
 * HTTP Method: **GET**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
|  | |


##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 200 (OK)           |
| Payload   | Array of the users retrieved |


```json
{
    "users": [
        {
            "id":     15,
            "name":   "Alysson Cirilo",
            "email":  "alysson.cirilo@lsdi.ufma.br",
            "roleID": 2359
        },
        {
            "id":     14,
            "name":   "Rodolfo Alves",
            "email":  "rodolfosalves@lsdi.ufma.br",
            "roleID": 2359
        }
    ]
}
```

## Roles

### **POST**  `/roles`
#### Register a new role
This operation inserts a new role, expressed in the JSON format, into the Semantic Server.
**Note that all role fields are required**.
If everything goes well, Semantic Server returns a response with HTTP status code 201 (CREATED), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

 * Full URL: `http://localhost:8080/api` **`/roles`**
 * HTTP Method: **POST**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | role JSON |

Example:

```json
{
    "role": {
        "name":   "PHD Student"
   }
}
```


##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 201 (CREATED)      |
| Payload   | role created       |


```json
{
    "role": {
        "id":   3,
        "name": "PHD Student"
   }
}
```

### **PUT**  `/roles/{id}`
#### Update an existing role
This operation update an existing role with id equals `{id}`. The new values should be expressed in the JSON format.
**Note that all role fields are required**.
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

 * Full URL: `http://localhost:8080/api` **`/roles/{id}`**
 * HTTP Method: **PUT**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | role JSON |
|{id}  | The id of the role|

Example:

```json
{
    "role": {
        "name": "PhD Student"
   }
}
```


##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 200 (OK)           |
| Payload   | role updated      |


```json
{
    "role": {
        "id":   3,
        "name": "PhD Student"
   }
}
```

### **DELETE**  `/roles/{id}`
#### Delete an existing role
This operation deletes an existing role with id equals `{id}` from the Semantic Server.
**Note that all role fields are required**.
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

 * Full URL: `http://localhost:8080/api` **`/roles/{id}`**
 * HTTP Method: **DELETE**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | Empty           |
|{id}  | The id of the role|


##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 200 (OK)           |
| Payload   | role deleted      |


```json
{
    "role": {
        "id":  3,
        "name": "PhD Student"
   }
}
```


### **GET**  `/roles/{id}`
#### Retrieve an existing role
This operation update an existing role with id equals `{id}`, from the Semantic Server.
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

 * Full URL: `http://localhost:8080/api` **`/roles/{id}`**
 * HTTP Method: **GET**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | role JSON |
|{id}  | The id of the role|


##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 200 (OK)           |
| Payload   | role retrieved    |


```json
{
    "role": {
        "id":  3,
        "name": "PhD Student"
   }
}
```


### **GET**  `/roles`
#### Retrieve all the roles
This operation retrieves all the existing roles from the Semantic Server.
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

 * Full URL: `http://localhost:8080/api` **`/roles`**
 * HTTP Method: **GET**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
|  | |


##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 200 (OK)           |
| Payload   | Array of the roles retrieved |


```json
{
    "roles": [
        {
            "id":  3,
            "name": "PhD Student"
        },
        {
            "id":  2,
            "name": "Professor"
        }
    ]
}
```

## Rooms

### **POST**  `/rooms`
#### Register a new room
This operation inserts a new room, expressed in the JSON format, into the Semantic Server.
**Note that all room fields are required**.
If everything goes well, Semantic Server returns a response with HTTP status code 201 (CREATED), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

 * Full URL: `http://localhost:8080/api` **`/rooms`**
 * HTTP Method: **POST**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | room JSON |

Example:

```json
{
    "room": {
        "name":      "Reunion room",
        "sectionID": 2
   }
}
```

Where `sectionID` is an id of an existing Section.

##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 201 (CREATED)      |
| Payload   | room created       |


```json
{
    "room": {
        "id":        20,
        "name":      "Reunion room",
        "sectionID": 2
   }
}
```


### **PUT**  `/rooms/{id}`
#### Update an existing room
This operation update an existing room with id equals `{id}`. The new values should be expressed in the JSON format.
**Note that all room fields are required**.
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

 * Full URL: `http://localhost:8080/api` **`/rooms/{id}`**
 * HTTP Method: **PUT**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | room JSON |
|{id}  | The id of the room|

Example:

```json
{
    "room": {
        "name":      "Reunion Room",
        "sectionID": 2
   }
}
```


##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 200 (OK)           |
| Payload   | room updated      |

```json
{
    "room": {
        "id":        20,
        "name":      "Reunion Room",
        "sectionID": 2
   }
}
```


### **DELETE**  `/rooms/{id}`
#### Delete an existing room
This operation deletes an existing room with id equals `{id}` from the Semantic Server.
**Note that all room fields are required**.
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

 * Full URL: `http://localhost:8080/api` **`/rooms/{id}`**
 * HTTP Method: **DELETE**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | Empty           |
|{id}  | The id of the room|


##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 200 (OK)           |
| Payload   | room deleted      |


```json
{
    "room": {
        "id":        20,
        "name":      "Reunion Room",
        "sectionID": 2
   }
}
```


### **GET**  `/rooms/{id}`
#### Retrieve an existing room
This operation update an existing room with id equals `{id}`, from the Semantic Server.
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

 * Full URL: `http://localhost:8080/api` **`/rooms/{id}`**
 * HTTP Method: **GET**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | room JSON |
|{id}  | The id of the room|


##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 200 (OK)           |
| Payload   | room retrieved    |


```json
{
    "room": {
        "id":        20,
        "name":      "Reunion Room",
        "sectionID": 2
   }
}
```


### **GET**  `/rooms`
#### Retrieve all the rooms
This operation retrieves all the existing rooms from the Semantic Server.
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

 * Full URL: `http://localhost:8080/api` **`/rooms`**
 * HTTP Method: **GET**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
|  | |


##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 200 (OK)           |
| Payload   | Array of the rooms retrieved |


```json
{
    "rooms": [
        {
            "id":        20,
            "name":      "Reunion Room",
            "sectionID": 2
        },
        {
            "id":        19,
            "name":      "Server Room",
            "sectionID": 2
        }
    ]
}
```

