# basic-utils

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/fff657b5b823421997eeb2db64358f0e)](https://www.codacy.com/app/Kollektiva/basic-utils?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Schinzel/basic-utils&amp;utm_campaign=Badge_Grade)

[ ![Codeship Status for Schinzel/basic-utils](https://app.codeship.com/projects/742c59b0-b3aa-0134-6585-2a924262b5e8/status?branch=master)](https://app.codeship.com/projects/193508)

[![Coverage Status](https://coveralls.io/repos/github/Schinzel/basic-utils/badge.svg?branch=master)](https://coveralls.io/github/Schinzel/basic-utils?branch=master)

Some basic utilities I tend to use in projects. Most commonly less verbose versions of common code snippets.

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
		<version>1.3</version>
	</dependency>
</dependencies>    
```

## Sample code


### Checker
A less verbose way to check for null and empty variable for a set of data types.
```java
if (Checker.isEmpty(str)) {
}
```

### IdSet
A more succinct and easier-on-the-eyes version of storing values with identifiers.
Elements are returned in alphabetical orders. Supports wild card look ups.
 ```java
class MyValue implements IdSetValue {
	[...]
	public String getId() { return id; }
}

IdSet<MyValue> mySet = IdSet.create()
	.add(new MyValue("A"))
	.add(new MyValue("B"));

MyValue myVal = mySet.get("A");
```


### MapBuilder
A less verbose version to create a map with an initial set of values.
```java
Map<String, Integer> map = MapBuilder.create()
	.add("a", 1)
	.add("b", 2)
	.getMap();
```

### MiscUtil
A less verbose version of sleep.
```java
MiscUtil.snooze(100);
```

### RandomUtil
```java
//Get a random string with the length 12
String str = RandomUtil.getRandomString(12);
//Get a random number between 1-200 as a string padded to length 3
//E.g. "009", "175", "035"
str = RandomUtil.create(seed).getPaddedInt(1, 200, 3);
//An array that has the argument number of cells which will be filled with
//random numbers that will sum to the argument sum
int[] arr = RandomUtil.create().getIntArray(arrayLength, arraySum);
```
### State
Solves the problem of getting an overview of a state of a system or a sub-system. The state can be returned as a human readable string or a JSON object.

Interface to implement:
```java
public interface IStateNode {
	State getState();
}

class MyClass implements IStateNode{
	[...]
	public State getState(){
		return State.create()
			.add("Name", this.name)
			.add("countOfSomething", this.count)
			//Round to two decimals
			.add("someValue", this.val, 2)
			//Add a set of children that will be rendered as a sub tree
			.addChildren(stuff.iterator());
	}
}
```

 Sample output:
```java
Name:Music countOfSomething:123 someValue:17.14
-- Name:A countOfSomething:123 someValue:17.14
---- Name:George countOfSomething:55 someValue:7.40
---- Name:Ringo countOfSomething:44 someValue:1.88
---- Name:Paul countOfSomething:132 someValue:99.30
-- Name:B countOfSomething:12 312 someValue:67.84
---- Name:Lou countOfSomething:3 345 someValue:56 465.74
---- Name:Velvet countOfSomething:368 977 someValue:787.20
```

### SubStringer
Get the string in the string.
```java
String input = "http://www.example.com/index.html?key1=val1&key2=val2";
//Get everything after question mark, i.e. "key1=val1&key2=val2"
String queryString = SubStringer.create(input)
	.start("?")
	.toString();
//Get everything before question mark, i.e. "http://www.example.com/index.html"
String url = SubStringer.create(input)
	.end("?")
	.toString();
//Get host, i.e. "www.example.com"
String host = SubStringer.create(input)
	.start("http://")
	.end("/index")
	.toString();
//First get "www.example.com/index.html", then get everything after the slash, i.e. "index.html"
String page = SubStringer.create(input)
	.start("//").end("?")
	.getSubStringer()
	.start("/")
	.toString();				
```

### Thrower
Less verbose way to throw exceptions. Throws well formulated messages.
```java
Thrower.throwIfOutsideRange(value, valueName, min, max);
Thrower.throwIfEmpty(value, valueName);
Thrower.throwIfFalse(true, "A message");
```
