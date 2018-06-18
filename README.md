# basic-utils

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/fff657b5b823421997eeb2db64358f0e)](https://www.codacy.com/app/Kollektiva/basic-utils?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Schinzel/basic-utils&amp;utm_campaign=Badge_Grade)
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/fff657b5b823421997eeb2db64358f0e)](https://www.codacy.com/app/Kollektiva/basic-utils?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Schinzel/basic-utils&amp;utm_campaign=Badge_Coverage)
[![Build Status](https://travis-ci.org/Schinzel/basic-utils.svg?branch=master)](https://travis-ci.org/Schinzel/basic-utils)

Some basic utilities. 
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


//Sleep for 100 millis
Sandman.snooze(100);

//Check for null and empty string, map, list and so on
if (Checker.isEmpty(str)) {
}

```


```xml
<repositories>
	<repository>
		<id>maven-repo.schinzel.io</id>
		<url>http://maven-repo.schinzel.io/release</url>
	</repository>
</repositories>    

<dependencies>
	<dependency>
		<groupId>io.schinzel</groupId>
		<artifactId>basic-utils</artifactId>
		<version>1.23</version>
	</dependency>
</dependencies>    
```

