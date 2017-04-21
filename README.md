# basic-utils

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/fff657b5b823421997eeb2db64358f0e)](https://www.codacy.com/app/Kollektiva/basic-utils?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Schinzel/basic-utils&amp;utm_campaign=Badge_Grade)

[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/fff657b5b823421997eeb2db64358f0e)](https://www.codacy.com/app/Kollektiva/basic-utils?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Schinzel/basic-utils&amp;utm_campaign=Badge_Coverage)

[![Build Status](https://travis-ci.org/Schinzel/basic-utils.svg?branch=master)](https://travis-ci.org/Schinzel/basic-utils)

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
		<version>1.16</version>
	</dependency>
</dependencies>    
```

## Sample code

### Cache
A simple cache with hits statistics for unit tests.


### Checker
A less verbose way to check for null and empty variables for a set of data types.
```java
if (Checker.isEmpty(str)) {
}
```


### KeyValues
A collection that stores values that know their own string-keys and support finding values by string-keys.
 
Many things stored in maps know their key used to store them, or it would help debugging if they did. 
If so, data is stored in two places, which is a notorious source for bugs. The code is less verbose and clear. 
It is not always clear what the map-key is making it harder to understand. 

```java
Instead of
map.put(fund1.getId(), fund1)
map.put(fund2.getId(), fund2)

We have
values.add(fund1)
values.add(fund2)
```


Offers fail-fast, *addAndGet*, wildcard lookups and other handy features. 
```java
class MyValue implements IValueKey {
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

**Fail fast**
Do a *has* check before instead of a null check after. 
It has the prevents the annoying null-pointer exception. 
Should the *has* check be forgotten, the error message is helpful:

*No value with argument key in collection. Class:'KeyValues' Method:'get' Props:{key:'apa' collectionName:'My nice values'}*
```java
MyValue category = mySet.has("C")
    ? mySet.get("C")
    : mySet.addAndGet(new MyValue("C"));
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

### Ratio
The purpose of Ratio is to do exact basic arithmetic operations on non integers. 

For example Java has no data type that can store 1/3 exactly. 
Another simple example is to add 0.1 ten times. The sum should be 1 but that is not what we get:
```java
double d = 0d;
for (int i = 0; i < 10; i++) {
    d += 0.1d;
}
System.out.println(d);

Output:
0.9999999999999999
```
If you work with statistics or another field with somewhat extensive calculations
with non integer numbers the errors can accumulate and cause incorrect results and/or behavior.

Sample usage:
```java
Ratio ratio = Ratio.create(1, 3)
        .times(1, 3)
        .plus(1, 3)
        .dividedBy(2)
        .minus(1, 6);
System.out.println("Str: " + ratio.toString());
System.out.println("Big dec: " + ratio.getBigDec());
System.out.println("Double: " + ratio.getDouble());

Str: 1/18
Big dec: 0.05555555555555555555555555555555556
Double: 0.05555555555555555
```

### Sandman
A less verbose version of sleep.
```java
Sandman.snooze(100);
```


### State
Solves the problem of getting an overview of a state of a system or a sub-system. 
The state can be returned as a human readable string or a JSON object.

Interface to implement:
```java
public interface IStateNode {
	State getState();
}

class MyClass implements IStateNode{
	[...]
    public State getState() {
        return State.getBuilder()
                .addProp().key("Name").val(mName).buildProp()
                .addProp().key("SomeCount").val(mCount).buildProp()
                .addProp().key("SomeVal").val(mVal).decimals(2).buildProp()
                //Add a set of children that will be rendered as a sub tree
                .addChildren("SubThingies", mChildren)
                .build();
    }
}
```

 Sample output:
```java
Name:Root SomeCount:77 SomeVal:27.01
SubThingies
┗━ Name:yt87 SomeCount:47 SomeVal:99.28
┗━ Name:y10o SomeCount:70 SomeVal:20.81
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
    .startDelimiter("?")
    .toString();
//Get everything before question mark, i.e. "http://www.example.com/index.html"
String url = SubStringer.create(input)
    .endDelimiter("?")
    .toString();
//Get host, i.e. "www.example.com"
String host = SubStringer.create(input)
    .startDelimiter("http://")
    .endDelimiter("/index")
    .toString();
//First get "www.example.com/index.html", then get everything after the slash, i.e. "index.html"
String page = SubStringer.create(input)
    .startDelimiter("//")
    .endDelimiter("?")
    .getSubStringer()
    .startDelimiter("/")
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
Name:root Tot:145ms Avg:145.47ms Hits:1
sublaps
┗━ Name:A Root:5% Parent:5% Tot:7ms Avg:7.66ms Hits:1
┗━ Name:B Root:87% Parent:87% Tot:126ms Avg:126.45ms Hits:1
   ┗━ sublaps
      ┗━ Name:B1 Root:8% Parent:10% Tot:12ms Avg:1.30ms Hits:10
      ┗━ Name:B2 Root:78% Parent:90% Tot:113ms Avg:22.67ms Hits:5
┗━ Name:C Root:8% Parent:8% Tot:11ms Avg:11.26ms Hits:1
```



### Thrower
Less verbose way to throw exceptions. Throws well formulated messages.
```java
Thrower.throwIfOutsideRange(value, valueName, min, max);
Thrower.throwIfEmpty(value, valueName);
Thrower.throwIfFalse(true, "A message");
```

# Change Log

## Release Candidate: 1.17
- Checker now has isNotEmpty methods. 
- MapBuilder removed. Use Guava ImmutableMap.Builder instead.
- State
    - Property builders added
    - Properties optionally has units
- Str
    - Create constructor accepts string argument
- Substringer
    - Added method contains
    - Moved to its own package
    - Now supports finding a specific occurrence of a delimiter
    - Clearer method names
- Timekeeper output has units
## 1.16
- StateBuilder handles null values without throwing errors.
- State has a public getStr
## 1.15
- MiscUtil renamed to Sandman
- Sandman.snooze renamed to snoozeMillis
- Ratio added

