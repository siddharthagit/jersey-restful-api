#Jersey 3 Sample Application 
##This sample application supports following server
1. JDK Server
2. Grizzly Server


### Build the Application
`mvn install`
### Run Test

`mvn test`
### Run Application

`mvn clean compile exec:java`
## REST API overview

See the [curl][] scripts below with the REST API supported operations:

### Get a Menus

```bash
curl -X GET \
  'http://localhost:8080/rest/dishes' \
  -H 'Accept: text/plain'
```

### Get Menu by Id 1

```bash
curl -X GET \
  'http://localhost:8080/rest/dishes/1' \
  -H 'Accept: application/json'
```

### Add a Menu with id 101 and name "Veggie"

```bash
	curl --header "Content-Type: application/json"   
		 --request POST   --data '{"id":"101","name":"Veggie"}'   
		 http://localhost:8080/rest/menus -i'
```

### Update Menu with Id 1

```bash
	curl --header "Content-Type: application/json"   
	--request PUT   --data '{"id":"1","name":"Veggie u1"}'   
	http://localhost:8080/rest/menus/1 -i

```

### Patch Menu with a new name

```bash
	curl --header "Content-Type: application/json"   
	--request PATCH    
	http://localhost:8080/rest/menus/1?name=menuitem2 -i

```

### Delete Menu with Id 101

```bash
	curl --header "Content-Type: application/json"   --request DELETE    http://localhost:8080/rest/menus/1 -id

```




## Targeting the REST API with Postman

Alternatively to [curl][], you can use [Postman][] to target the REST API.


[Postman]: https://www.getpostman.com/
[curl]: https://curl.se/