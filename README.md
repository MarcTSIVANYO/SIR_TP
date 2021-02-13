TAA Labs
===================

The goal of this lab is to add support of restful webservices for an application. 

In order to initialize your project, we provide a tiny sample project to show how to run an JAX-RS application, embedded in Undertow app server, fired up in a `main()` method. 

Undertow is a flexible performant web server written in java, providing both blocking and non-blocking API’s based on NIO.

Undertow has a composition based architecture that allows you to build a web server by combining small single purpose handlers. The gives you the flexibility to choose between a full Java EE servlet 3.1 container, or a low level non-blocking handler, to anything in between.

Undertow is designed to be fully embeddable, with easy to use fluent builder APIs. Undertow’s lifecycle is completely controlled by the embedding application.


### Getting started. 

Fork this project. 

Just build the project:

    mvn clean install
    
and launch the server:

    mvn exec:java -Dexec.mainClass="fr.istic.taa.jaxrs.RestServer" 
   
You can test the service using 
to get a person
> curl -H "Content-Type: application/json" -X GET http://localhost:8080/status/person

to insert a Person
> curl -H "Content-Type: application/json" -X POST -d '{"name":"test","firstName":"t"}' http://localhost:8080/status/person

You can import this maven project in your favorite IDE. 

# Task 1

Using this example, select an example of RestServices you could build.

You can have three choices. 

1. You can re-implement [swapi](https://swapi.co/)

2. You can provide a rest access within the [Borg calendar](https://github.com/mikeberger/borg_calendar). In this case, you need to install the [lombok tool](https://projectlombok.org/) for your IDE .

3. you can provide you own Rest API for your tweeter account [http://twitter4j.org/en/index.html](http://twitter4j.org/en/index.html). 

### 1. SWAPI

Create your domain class,

Create your CRUD Services

Create your REST Services.

### 2. Borg

Clone the project. 

Get the [lombok tool](https://projectlombok.org/) and run it for eclipse. 

Restart eclipse. 

Import the project as maven project. 

Enable Annotation processing for eclipse for your project. [Enable Annotation processing](https://www.eclipse.org/jdt/apt/introToAPT.php)

It must compile. 

Run Borg class in SwingUI project. 

Add dependencies to understow and other dependencies in your maven project. 

Design your rest services. 



### 3. Tweeter

For tweeter, you must go to [tweeter app](https://apps.twitter.com/) and create an application. When your application is created, you can see a new **tab key and access tokens**

you can find some sample [here](http://twitter4j.org/en/code-examples.html)

in particular 

```java
  public static void main(String args[]) throws Exception{
    // The factory instance is re-useable and thread safe.
    TwitterFactory factory = new TwitterFactory();
    AccessToken accessToken = loadAccessToken(Integer.parseInt(args[0]));
    Twitter twitter = factory.getInstance();
    twitter.setOAuthConsumer("[consumer key]", "[consumer secret]");
    twitter.setOAuthAccessToken(accessToken);
    Status status = twitter.updateStatus(args[1]);
    System.out.println("Successfully updated the status to [" + status.getText() + "].");
    System.exit(0);
  }
  private static AccessToken loadAccessToken(int useId){
    String token = // load from a persistent store
    String tokenSecret = // load from a persistent store
    return new AccessToken(token, tokenSecret);
  }

```


The goal is to design a clean CRUD API fo a business layer that allows to interact with your application using retful services.


Maxime provides a complete example with an integration with JPA. https://github.com/maxleiko/taa-jpa if require. 


# Task 2. 

Now, we would like to ensure that our API can be discovered. The OpenAPI Initiative (OAI) was created by a consortium of forward-looking industry experts who recognize the immense value of standardizing on how REST APIs are described. As an open governance structure under the Linux Foundation, the OAI is focused on creating, evolving and promoting a vendor neutral description format. 

APIs form the connecting glue between modern applications. Nearly every application uses APIs to connect with corporate data sources, third party data services or other applications. Creating an open description format for API services that is vendor neutral, portable and open is critical to accelerating the vision of a truly connected world.

To do this integration first, you must add a dependencies to openAPI libraries. 

```xml
		<dependency>
			<groupId>io.swagger.core.v3</groupId>
			<artifactId>swagger-jaxrs2</artifactId>
			<version>2.0.0</version>
		</dependency>
```

Next you have to add OpenAPI Resource to your application

Your application could be something like that. 

```java
@ApplicationPath("/")
public class RestApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> resources = new HashSet<>();


		// SWAGGER endpoints
		resources.add(OpenApiResource.class);

        //Your own resources. 
        resources.add(PersonResource.class);
....
		return resources;
	}
}
```

Next start your server, you must have your api description available at [http://localhost:8080/openapi.json](http://localhost:8080/openapi.json)

### Integrate Swagger UI. 

Next we have to integrate Swagger UI. We will first download it.
https://github.com/swagger-api/swagger-ui

Copy dist folder content in src/main/webapp/swagger in your project. 

Edit index.html file to automatically load your openapi.json file. 

At the end of the index.html, your must have something like that.

```js
   // Build a system
      const ui = SwaggerUIBundle({
        url: "http://localhost:8080/openapi.json",
        dom_id: '#swagger-ui',
        
        ...
```

Next add a new resources to create a simple http server when your try to access to http://localhost:8080/api/.

This new resources can be developped as follows

```java
package app.web.rest;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/api")
public class SwaggerResource {

    private static final Logger logger = Logger.getLogger(SwaggerResource.class.getName());

    @GET
    public byte[] Get1() {
        try {
            return Files.readAllBytes(FileSystems.getDefault().getPath("src/main/webapp/swagger/index.html"));
        } catch (IOException e) {
            return null;
        }
    }

    @GET
    @Path("{path:.*}")
    public byte[] Get(@PathParam("path") String path) {
        try {
            return Files.readAllBytes(FileSystems.getDefault().getPath("src/main/webapp/swagger/"+path));
        } catch (IOException e) {
            return null;
        }
    }

}
```

Add this new resources in your application

```java
@ApplicationPath("/")
public class RestApplication extends Application {


	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> resources = new HashSet<>();


		// SWAGGER endpoints
		resources.add(OpenApiResource.class);
		resources.add(PersonResource.class);
        //NEW LINE TO ADD
		resources.add(SwaggerResource.class);

		return resources;
	}
}
```

Restart your server and access to http://localhost:8080/api/, you should access to a swagger ui instance that provides documentation on your api. 

You can follow this guide to show how you can specialise the documentation through annotations.

https://github.com/swagger-api/swagger-samples/blob/2.0/java/java-resteasy-appclasses/src/main/java/io/swagger/sample/resource/PetResource.java
