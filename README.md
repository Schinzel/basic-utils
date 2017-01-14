# basic-utils

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/fff657b5b823421997eeb2db64358f0e)](https://www.codacy.com/app/Kollektiva/basic-utils?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Schinzel/basic-utils&amp;utm_campaign=Badge_Grade)

[ ![Codeship Status for Schinzel/basic-utils](https://app.codeship.com/projects/742c59b0-b3aa-0134-6585-2a924262b5e8/status?branch=master)](https://app.codeship.com/projects/193508)

[![Coverage Status](https://coveralls.io/repos/github/Schinzel/basic-utils/badge.svg?branch=master)](https://coveralls.io/github/Schinzel/basic-utils?branch=master)

Some basic utilities I tend to use in projects. Most commonly less verbose version of common code snippets.

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
		<version>1.0</version>
	</dependency>
</dependencies>    
```

## Sample code
### SubStringer
Get the string in the string.
```java
String input = "http://www.example.com/index.html?key1=val1&key2=val2";
//Get everything after question mark
String queryString = SubStringer.create(input)
	.start("?")
	.toString();
//Get everything before question mark
String url = SubStringer.create(input)
	.end("?")
	.toString();
//Get host
String host = SubStringer.create(input)
	.start("http://")
	.end("/index")
	.toString();
String page = SubStringer.create(input)
	.start("//").end("?")
	.getSubStringer()
	.start("/")
	.toString();				
```

### Checker
A less verbose way to check for null and empty variable for a set of data types.
```java
if (Checker.isEmpty(str)) {
}
```

### MiscUtil
A less verbose version of sleep.
```java
MiscUtil.snooze(100);
```

### RandomUtil
```java
String str = RandomUtil.getRandomString(stringLength);
str = RandomUtil.create(seed).getPaddedInt(min, max, padding);
int[] arr = RandomUtil.create().getIntArray(arrayLength, arraySum);
```

### MapBuilder
A less verbose version to create a map with an initial set of values.
```java
Map<String, Integer> map = MapBuilder.create()
	.add("a", 1)
	.add("b", 2)
	.getMap();
```

### Thrower
Less verbose way to throw exceptions. Throws well formulated messages.
```java
Thrower.throwIfOutsideRange(value, valueName, min, max);
Thrower.throwIfEmpty(value, valueName);
Thrower.throwIfFalse(true, "An message");
```
