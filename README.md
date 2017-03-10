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
		<version>1.14</version>
	</dependency>
</dependencies>    
```

## Sample code

### Cache
A simple cache with hits statistics for unit tests.


### Checker
A less verbose way to check for null and empty variable for a set of data types.
```java
if (Checker.isEmpty(str)) {
}
```


### KeyValues
A collection that stores values that know their own string-keys and support finding values by string-keys. 

Offers fail-fast, addAndGet, wildcard lookups and other handy features. 
 ```java
class MyValue implements IKeyValue {
    final String key;
    MyValue(String key){ 
        this.key = key;
    }
	[...]
	public String getKey() { return this.key; }
}

NameValues<MyValue> mySet = NameValues.<MyValue>create()
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

### Str
A string utility class that contains the most common string operations in 
one class with less verbose syntax and less boilerplate code.

 Instead of:
```java
 StringBuilder sb = new StringBuilder();
 sb.append("A")
    .append(String.format( "%.2f", myDouble ))
    .append("B");
 System.out.println(sb.toString());
```
 
 We have:
```java
 Str.create().a("A").a(myDouble, 2).a("B").pln();
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



## Timekeeper
Timekeeper is used to find bottlenecks by offering a lightweight way to measure time consumed by lines.

Finding bottlenecks often involves drilling further and further down in the code until one finds the line or block of code that is the culprit. To facilitate this the timekeeper structures the code blocks measured as a tree and for each measurement reports:
* % of time of whole (root)
* % of time of immediate parent
* Total time in milliseconds
* Average time per iteration in milliseconds
* Number of times this block of code was hit

```java
//Get the timekeeper. There is also a create method if one does not want
//to use a singleton.
Timekeeper timekeeper = Timekeeper.getSingleton();
timekeeper.startLap("A");
//Some code runs here that will be measured as lap A
timekeeper.stopLap();
timekeeper.startLap("B");
for (int i = 0; i < 10; i++) {
	//Start lab B1. As lap B is running, B1 will be a sub-lap to B.
	timekeeper.startLap("B1");
	MiscUtil.snooze(1);
	timekeeper.stopLap();
}
for (int i = 0; i < 5; i++) {
	//Start lab B2. As lap B is running, B2 will be a sub-lap to B.
	timekeeper.startLap("B2");
	MiscUtil.snooze(20);
	timekeeper.stopLap();
}
timekeeper.stopLap();
timekeeper.startLap("C");
//Some code runs here that will be measured as lap C
MiscUtil.snooze(10);
timekeeper.stopLap();
//Stop the whole stopwatch
timekeeper.stop();
```

Sample output of above. Results also available as JSON.
```
 Name:root Tot:141ms Avg:141.35ms Hits:1
-- Name:A Root:0% Parent:0% Tot:0ms Avg:0.01ms Hits:1
-- Name:B Root:91% Parent:91% Tot:128ms Avg:128.50ms Hits:1
---- Name:B1 Root:10% Parent:11% Tot:14ms Avg:1.46ms Hits:10
---- Name:B2 Root:80% Parent:88% Tot:113ms Avg:22.70ms Hits:5
-- Name:C Root:9% Parent:9% Tot:12ms Avg:12.69ms Hits:1
```



### Thrower
Less verbose way to throw exceptions. Throws well formulated messages.
```java
Thrower.throwIfOutsideRange(value, valueName, min, max);
Thrower.throwIfEmpty(value, valueName);
Thrower.throwIfFalse(true, "A message");
```
