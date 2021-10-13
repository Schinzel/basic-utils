# basic-utils

[![Build Status](https://travis-ci.org/Schinzel/basic-utils.svg?branch=master)](https://travis-ci.org/Schinzel/basic-utils)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=Schinzel_basic-utils&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=Schinzel_basic-utils)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=Schinzel_basic-utils&metric=security_rating)](https://sonarcloud.io/dashboard?id=Schinzel_basic-utils)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=Schinzel_basic-utils&metric=coverage)](https://sonarcloud.io/dashboard?id=Schinzel_basic-utils)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=Schinzel_basic-utils&metric=bugs)](https://sonarcloud.io/dashboard?id=Schinzel_basic-utils)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=Schinzel_basic-utils&metric=code_smells)](https://sonarcloud.io/dashboard?id=Schinzel_basic-utils)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=Schinzel_basic-utils&metric=sqale_index)](https://sonarcloud.io/dashboard?id=Schinzel_basic-utils)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=Schinzel_basic-utils&metric=ncloc)](https://sonarcloud.io/dashboard?id=Schinzel_basic-utils)

Some basic utilities mainly for Java. 
Most commonly less verbose versions of common code snippets.

Some examples
```java
//Get host, i.e. "www.example.com"
String input = "http://www.example.com/index.html?key1=val1&key2=val2";
SubString.create(input)
        .startDelimiter("http://")
        .endDelimiter("/index")
        .getStr()
        .writeToSystemOutWithPrefix("Host: ");

Thrower.throwIfTrue(myVar<100).message("This is an exception message");


//Sleep for 100 milliseconds
Sandman.snoozeMillis(100);

//Check for null and empty string, map, list and so on
if (Checker.isEmpty(str)) {
}

```


```xml
<repositories>
	<repository>
		<id>maven-repo.schinzel.io</id>
		<url>https://s3-eu-west-1.amazonaws.com/maven-repo.schinzel.io/release</url>
	</repository>
</repositories>    

<dependencies>
	<dependency>
		<groupId>io.schinzel</groupId>
		<artifactId>basic-utils</artifactId>
		<version>1.XX</version>
	</dependency>
</dependencies>    
```

