# Semantic Server

The Semantic Server is a web service that maps the relationships between wireless devices with persons or locations.

## Resources

There are seven resources that the Semantic Server keeps, they are:
* [Things](#Things)
* [Mhubs](#Mhubs)
* [Users](#Users)
* [Roles](#Roles)
* [Physical Spaces](#Physical-Spaces)

Rooms belog to Sections that belongs to Buildings. Mhubs and Things belogs to either Users or Rooms. and Users might have Roles.

## REST API

The Semantic Server uses the base `/api` URL for every REST operation.
Thus, assuming that the server has been deployed at `http://localhost:8080`, all HTTP calls **must** contain `http://localhost:8080/api`. 

## Things

### **POST**  `/things`
#### Register a new thing
This operation inserts a new thing, expressed in the JSON format, into the Semantic Server.
**Note that not all Thing fields are required**.
`description`, `holder` and `uuid` can be set to `null`. if `uuid` is `null`, it will be set by the service itself.
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
	"uuid": "7ffae023-41f6-4a1f-a90c-abcffe417195",
	"name": "LSDi beacon",
	"description": "beacon de tercio",
	"holder": {
		"id": 7
	}
	
}
```

Where `id` property of `holder` object is the ID of either a User or a Room (The IDs of Users and Rooms are garanteed to be mutually exclusive).

##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 201 (CREATED)      |
| Payload   | Thing created      |


```json
{
  "uuid": "7ffae023-41f6-4a1f-a90c-abcffe417195",
  "holder": {
    "id": 7
  },
  "description": "beacon de tercio",
  "name": "LSDi beacon"
}
```

### **PUT**  `/things/{uuid}`
#### Update an existing thing
This operation update an existing thing with id equals `{uuid}`. The new values should be expressed in the JSON format.
**Note that not all Thing fields are required**.
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

 * Full URL: `http://localhost:8080/api` **`/things/{uuid}`**
 * HTTP Method: **PUT**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | Thing JSON |
|{uuid}  | The id of the thing|

Example:

```json
{
	"uuid": "7ffae023-41f6-4a1f-a90c-abcffe417195",
	"name": "LSDi beacon",
	"description": "beacon de tercio",
	"holder": {
		"id": 7
	}
	
}
```

Where `id` property of `holder` object is the ID of either a User or a Room (The IDs of Users and Rooms are garanteed to be mutually exclusive).

##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 200 (OK)           |
| Payload   | Thing updated      |


```json
{
  "uuid": "7ffae023-41f6-4a1f-a90c-abcffe417195",
  "holder": {
    "id": 7
  },
  "description": "beacon de tercio",
  "name": "LSDi beacon"
}
```

### **DELETE**  `/things/{uuid}`
#### Delete an existing thing
This operation deletes an existing thing with id equals `{uuid}` from the Semantic Server.
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

 * Full URL: `http://localhost:8080/api` **`/things/{uuid}`**
 * HTTP Method: **DELETE**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | Empty           |
|{uuid}  | The id of the thing|


##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 204 (No Content) or 404 (Not Found)           |
| Payload   | Empty      |




### **GET**  `/things/{uuid}`
#### Retrieve an existing thing
This operation retrieves an existing thing with id equals `{uuid}`, from the Semantic Server.
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

 * Full URL: `http://localhost:8080/api` **`/things/{uuid}`**
 * HTTP Method: **GET**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | Empty |
|{uuid}  | The id of the thing|


##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 200 (OK)           |
| Payload   | Thing retrieved    |


```json
{
  "uuid": "7ffae023-41f6-4a1f-a90c-abcffe417195",
  "holder": {
    "id": 7
  },
  "description": "beacon de tercio",
  "name": "LSDi beacon"
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
[
  {
    "uuid": "7ffae023-41f6-4a1f-a90c-abcffe417195",
    "holder": {
      "id": 7
    },
    "description": "beacon de tercio",
    "name": "LSDi beacon"
  },
  {
    "uuid": "b6421d2b-58ca-4c5a-97b1-ddc0a308f0f4",
    "holder": {
      "id": 7
    },
    "description": null,
    "name": "arduino uno"
  }
]
```

## Mhubs

### **POST**  `/mhubs`
#### Register a new mhub
This operation inserts a new mhub, expressed in the JSON format, into the Semantic Server.
**Note that not all mhub fields are required**.
`description`, `holder` and `uuid` can be set to `null`. if `uuid` is `null`, it will be set by the service itself.
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
	"name":"Moto Z1",
	"description": "melhor celular de todos os tempos",
	"holder": {
    "id": 6
  }
}
```

Where `id` property of `holder` object is the ID of either a User or a Room (The IDs of Users and Rooms are garanteed to be mutually exclusive).

##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 201 (CREATED)      |
| Payload   | mhub created      |


```json
{
  "uuid": "6d3d5a57-d041-405d-b398-6d98dd33d332",
  "holder": {
    "id": 6
  },
  "description": "melhor celular de todos os tempos",
  "name": "Moto Z1"
}
```

### **PUT**  `/mhubs/{uuid}`
#### Update an existing mhub
This operation update an existing mhub with id equals `{uuid}`. The new values should be expressed in the JSON format.
**Note that not all mhub fields are required**.
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

 * Full URL: `http://localhost:8080/api` **`/mhubs/{uuid}`**
 * HTTP Method: **PUT**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | mhub JSON |
|{uuid}  | The id of the mhub|

Example:

```json
{
  "uuid": "6d3d5a57-d041-405d-b398-6d98dd33d332",
  "holder": {
    "id": 6
  },
  "description": "melhor celular de todos os tempos",
  "name": "Moto Z1"
}
```

Where `id` property of `holder` object is the ID of either a User or a Room (The IDs of Users and Rooms are garanteed to be mutually exclusive).

##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 200 (OK)           |
| Payload   | mhub updated      |


```json
{
  "uuid": "6d3d5a57-d041-405d-b398-6d98dd33d332",
  "holder": {
    "id": 6
  },
  "description": "melhor celular de todos os tempos",
  "name": "Moto Z1"
}
```

### **DELETE**  `/mhubs/{uuid}`
#### Delete an existing mhub
This operation deletes an existing mhub with id equals `{uuid}` from the Semantic Server.
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

 * Full URL: `http://localhost:8080/api` **`/mhubs/{uuid}`**
 * HTTP Method: **DELETE**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | Empty           |
|{uuid}  | The id of the mhub|


##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 204 (No content) or 404(Not found)          |
| Payload   | Empty      |




### **GET**  `/mhubs/{uuid}`
#### Retrieves an existing mhub
This operation retrieves an existing mhub with id equals `{uuid}`, from the Semantic Server.
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

 * Full URL: `http://localhost:8080/api` **`/mhubs/{uuid}`**
 * HTTP Method: **GET**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | mhub JSON |
|{uuid}  | The id of the mhub|


##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 200 (OK)           |
| Payload   | mhub retrieved    |


```json
{
  "uuid": "67c6d15d-f73f-4b2d-9023-65184eb444e6",
  "holder": null,
  "description": null,
  "name": "Iphone 6"
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
[
  {
    "uuid": "67c6d15d-f73f-4b2d-9023-65184eb444e6",
    "holder": null,
    "description": null,
    "name": "Iphone 6"
  },
  {
    "uuid": "6d3d5a57-d041-405d-b398-6d98dd33d332",
    "holder": {
      "id": 6
    },
    "description": "melhor celular de todos os tempos",
    "name": "Moto Z1"
  }
]
```

## Persons

### **POST**  `/persons`
#### Register a new person
This operation inserts a new person, expressed in the JSON format, into the Semantic Server.
**Note that not all person fields are required**.
`fullName`, `roles` can be set to `null`.
`holder` object will be set automatically.
If everything goes well, Semantic Server returns a response with HTTP status code 201 (CREATED), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

 * Full URL: `http://localhost:8080/api` **`/persons`**
 * HTTP Method: **POST**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | person JSON |

Example:

```json
{
	"shortName": "Tercio Santana",
	"email": "tercio.sss@gmail.com",
	"roles": [
		{"name": "Aluno de Mestrado"}
	]
}
```

Where `roles` is an array of ID of Roles.

##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 201 (CREATED)      |
| Payload   | person created       |


```json
{
  "email": "tercio.sss@gmail.com",
  "holder": {
    "id": 7
  },
  "shortName": "Tercio Santana",
  "roles": [
    {
      "name": "Aluno de Mestrado"
    }
  ],
  "fullName": null
}
```

### **PUT**  `/persons/{email}`
#### Update an existing person
This operation update an existing person with email equals `{email}`. The new values should be expressed in the JSON format.
**Note that not all person fields are required**.
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

 * Full URL: `http://localhost:8080/api` **`/persons/{email}`**
 * HTTP Method: **PUT**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | person JSON |
|{email}  | The id of the person|

Example:

```json
{
	"shortName": "Tercio Santana",
	"email": "tercio.sss@gmail.com",
	"roles": [
		{"name": "Aluno de Mestrado"}
	]
}
```

Where `roles` is an array of ID of Roles.

##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 200 (OK)           |
| Payload   | person created       |


```json
{
  "email": "tercio.sss@gmail.com",
  "holder": {
    "id": 7
  },
  "shortName": "Tercio Santana",
  "roles": [
    {
      "name": "Aluno de Mestrado"
    }
  ],
  "fullName": null
}
```

### **DELETE**  `/persons/{email}`
#### Delete an existing person
This operation deletes an existing person with id equals `{email}` from the Semantic Server.
**Note that all person fields are required**.
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

 * Full URL: `http://localhost:8080/api` **`/persons/{email}`**
 * HTTP Method: **DELETE**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | Empty           |
|{email}  | The id of the person|


##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 204 (no content)           |
| Payload   | empty      |




### **GET**  `/persons/{email}`
#### Retrieve an existing person
This operation retrieves an existing person with id equals `{email}`, from the Semantic Server.
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

 * Full URL: `http://localhost:8080/api` **`/persons/{email}`**
 * HTTP Method: **GET**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | person JSON |
|{email}  | The id of the person|


##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 200 (OK)           |
| Payload   | person retrieved    |


```json
{
  "email": "alysson.cirilo@lsdi.ufma.br",
  "holder": {
    "id": 6
  },
  "shortName": "Alysson Cirilo",
  "roles": [
    {
      "name": "Aluno de Graduacao"
    },
    {
      "name": "Aluno de IC"
    }
  ],
  "fullName": null
}
```


### **GET**  `/persons`
#### Retrieve all the persons
This operation retrieves all the existing persons from the Semantic Server.
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

 * Full URL: `http://localhost:8080/api` **`/persons`**
 * HTTP Method: **GET**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
|  | |


##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 200 (OK)           |
| Payload   | Array of the persons retrieved |


```json
[
  {
    "email": "joao.neves@gmail.com",
    "holder": {
      "id": 1
    },
    "shortName": "joão das neves",
    "roles": [],
    "fullName": null
  },
  {
    "email": "alysson.cirilo@lsdi.ufma.br",
    "holder": {
      "id": 6
    },
    "shortName": "Alysson Cirilo",
    "roles": [
      {
        "name": "Aluno de Graduacao"
      },
      {
        "name": "Aluno de IC"
      }
    ],
    "fullName": null
  },
  {
    "email": "tercio.sss@gmail.com",
    "holder": {
      "id": 7
    },
    "shortName": "Tercio Santana",
    "roles": [
      {
        "name": "Aluno de Mestrado"
      }
    ],
    "fullName": null
  }
]
```

### **GET**  `/persons/{email}/things` **not implemented**
#### Retrieve the things that belong to an existing person
This operation retrieves the things that belong to an existing person with id equals `{email}`, from the Semantic Server.
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

 * Full URL: `http://localhost:8080/api` **`/persons/{email}/things`**
 * HTTP Method: **GET**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | person JSON |
|{email}  | The id of the person|


##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 200 (OK)           |
| Payload   | array of things retrieved    |


```json
{
    "things": [
        {
            "id":          13,
            "email":        "7d53edbe-2feb-4e63-a1d5-64334587a2df",
            "description": "Tercio's bluetooth beacon",
            "holderID":    2360
        }
    ]
}
```

### **GET**  `/persons/{email}/mhubs` **not implementd**
#### Retrieve the mhubs that belong to an existing person
This operation retrieves the mhubs that belong to an existing person with id equals `{email}`, from the Semantic Server.
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

 * Full URL: `http://localhost:8080/api` **`/persons/{email}/things`**
 * HTTP Method: **GET**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | person JSON |
|{email}  | The id of the person|


##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 200 (OK)           |
| Payload   | array of mhubs retrieved    |

```json
{
    "mhubs": [
        {
            "id":          13,
            "email":        "7d53edbe-2feb-4e63-a1d5-64334587a2de",
            "description": "Rodolfo's Iphone",
            "holderID":    2361
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
    "name":   "PHD Student"
}
```


##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 201 (CREATED)      |
| Payload   | role created       |


```json
{
    "name": "PHD Student"
}
```

### **PUT**  `/roles/{name}`
#### Update an existing role
This operation update an existing role with id equals `{name}`. The new values should be expressed in the JSON format.
**Note that all role fields are required**.
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

 * Full URL: `http://localhost:8080/api` **`/roles/{name}`**
 * HTTP Method: **PUT**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | role JSON |
|{name}  | The name of the role|

Example:

```json
{
    "name": "PhD Student"
}
```


##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 200 (OK)           |
| Payload   | role updated      |


```json
{
    "name": "PhD Student"
}
```

### **DELETE**  `/roles/{name}`
#### Delete an existing role
This operation deletes an existing role with id equals `{name}` from the Semantic Server.
**Note that all role fields are required**.
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

 * Full URL: `http://localhost:8080/api` **`/roles/{name}`**
 * HTTP Method: **DELETE**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | Empty           |
|{name}  | The name of the role|


##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 200 (OK)           |
| Payload   | empty      |




### **GET**  `/roles/{name}`
#### Retrieve an existing role
This operation retrieves an existing role with name equals `{name}`, from the Semantic Server.
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

 * Full URL: `http://localhost:8080/api` **`/roles/{name}`**
 * HTTP Method: **GET**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | role JSON |
|{name}  | The name of the role|


##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 200 (OK)           |
| Payload   | role retrieved    |


```json
{
  "name": "Aluno de IC"
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
[
  {
    "name": "Aluno de IC"
  },
  {
    "name": "Aluno de Graduacao"
  },
  {
    "name": "Aluno de Mestrado"
  }
]
```

## Physical-Spaces

### **POST**  `/physical_spaces`
#### Register a new physical space
This operation inserts a new physical space, expressed in the JSON format, into the Semantic Server.
**Note that not all physical space fields are required**.
`parent` and `description` can be set to `null`
If everything goes well, Semantic Server returns a response with HTTP status code 201 (CREATED), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

 * Full URL: `http://localhost:8080/api` **`/physical_spaces`**
 * HTTP Method: **POST**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | physical space JSON |

Example:

```json
{
	"name" : "LSDi",
	"description": "Laboratório de Sistemas Distribuídos Inteligentes",
	"parent": {
		"holder": {
			"id": 8
		}
	}
}
```

Where `holder` property of `parent` object is an id of an existing physical space, if it is not set to `null` it will mean that this newly created physical space belongs inside the parent physical space.

##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 201 (CREATED)      |
| Payload   | physical space created       |


```json
{
  "name": "LSDi",
  "description": "Laboratório de Sistemas Distribuídos Inteligentes",
  "holder": {
    "id": 9
  },
  "children": null
}
```
By default the parent is not returned in the output, but the children are.


### **PUT**  `/physical_spaces/{id}`
#### Update an existing physical space
This operation update an existing physical space with id equals `{id}`. The new values should be expressed in the JSON format.
**Note that not all physical space fields are required**.
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

 * Full URL: `http://localhost:8080/api` **`/physical_spaces/{id}`**
 * HTTP Method: **PUT**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | physical space JSON |
|{id}  | The id of the physical space|

Example:

```json
{
	"name" : "LSDi",
	"description": "Laboratório de Sistemas Distribuídos Inteligentes",
	"parent": {
		"holder": {
			"id": 8
		}
	}
}
```


##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 200 (OK)           |
| Payload   | physical space updated      |

```json
{
  "name": "LSDi",
  "description": "Laboratório de Sistemas Distribuídos Inteligentes",
  "holder": {
    "id": 9
  },
  "children": null
}
```


### **DELETE**  `/physical_spaces/{id}`
#### Delete an existing physical space
This operation deletes an existing physical space with id equals `{id}` from the Semantic Server.
**Note that all physical space fields are required**.
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

 * Full URL: `http://localhost:8080/api` **`/physical_spaces/{id}`**
 * HTTP Method: **DELETE**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | Empty           |
|{id}  | The id of the physical space|


##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 200 (OK)           |
| Payload   | empty      |




### **GET**  `/physical_spaces/{id}`
#### Retrieve an existing physical space
This operation retrieves an existing physical space with id equals `{id}`, from the Semantic Server.
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

 * Full URL: `http://localhost:8080/api` **`/physical_spaces/{id}`**
 * HTTP Method: **GET**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | physical space JSON |
|{id}  | The id of the physical space|


##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 200 (OK)           |
| Payload   | physical space retrieved    |


```json
{
  "name": "UFMA",
  "description": "Univerdidade Federal do Maranhão",
  "holder": {
    "id": 8
  },
  "children": [
    {
      "name": "LSDi",
      "description": "Laboratório de Sistemas Distribuídos Inteligentes",
      "holder": {
        "id": 14
      },
      "children": []
    }
  ]
}
```


### **GET**  `/physical_spaces`
#### Retrieve all the physical_spaces
This operation retrieves all the existing physical_spaces from the Semantic Server.
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

 * Full URL: `http://localhost:8080/api` **`/physical_spaces`**
 * HTTP Method: **GET**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
|  | |


##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 200 (OK)           |
| Payload   | Array of the physical_spaces retrieved |


```json
[
  {
    "name": "UFMA",
    "description": "Univerdidade Federal do Maranhão",
    "holder": {
      "id": 8
    },
    "children": [
      {
        "name": "LSDi",
        "description": "Laboratório de Sistemas Distribuídos Inteligentes",
        "holder": {
          "id": 14
        },
        "children": []
      }
    ]
  },
  {
    "name": "LSDi",
    "description": "Laboratório de Sistemas Distribuídos Inteligentes",
    "holder": {
      "id": 14
    },
    "children": []
  }
]
```

### **GET**  `/physical_spaces/{id}/things` **not implemented**
#### Retrieve the things belonging to an existing physical space
This operation retrieves the things that belong to an existing user with id equals `{id}`, from the Semantic Server.
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

 * Full URL: `http://localhost:8080/api` **`/physical_spaces/{id}/things`**
 * HTTP Method: **GET**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | user JSON |
|{id}  | The id of the physical space|


##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 200 (OK)           |
| Payload   | array of things retrieved    |


```json

```


### **GET**  `/physical_spaces/{id}/mhubs` **not implemented**
#### Retrieve the mhubs that belong to an existing physical space
This operation retrieves the mhubs that belong to an existing physical space with id equals `{id}`, from the Semantic Server.
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

 * Full URL: `http://localhost:8080/api` **`/physical_spaces/{id}/mhubs`**
 * HTTP Method: **GET**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | user JSON |
|{id}  | The id of the physical space|


##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 200 (OK)           |
| Payload   | array of mhubs retrieved    |

```json

```

### **GET**  `/physical_spaces/{id}/mhubs` **not implemented**
#### Retrieve the parent of an existing physical space
This operation retrieves the parent of an existing physical space with id equals `{id}`, from the Semantic Server.
If everything goes well, Semantic Server returns a response with HTTP status code 200 (OK), otherwise it returns a response with HTTP status code 400 (BAD REQUEST).

 * Full URL: `http://localhost:8080/api` **`/physical_spaces/{id}/mhubs`**
 * HTTP Method: **GET**

##### Input Parameters:

| Name | Payload         |
|------|-----------------|
| body | user JSON |
|{id}  | The id of the physical space|


##### Output Parameters:
| Name      | Value              |
|-----------|--------------------|
| HTTP Code | 200 (OK)           |
| Payload   | physical space retrieved    |

```json
{
  "name": "UFMA",
  "description": "Univerdidade Federal do Maranhão",
  "holder": {
    "id": 8
  },
  "children": [
    {
      "name": "LSDi",
      "description": "Laboratório de Sistemas Distribuídos Inteligentes",
      "holder": {
        "id": 14
      },
      "children": []
    }
  ]
}
```
