# ☕ Java Complete Concepts & Interview Guide

> Extracted from *Head First Java* — Chapters 3, 5 & 6 | Full Interview Prep

---

## 📚 Table of Contents

1. [Java Primitive Data Types](#1-java-primitive-data-types)
2. [Type Casting](#2-type-casting)
3. [Operators & Expressions](#3-operators--expressions)
4. [Control Flow — Loops](#4-control-flow--loops)
5. [Arrays](#5-arrays)
6. [Methods](#6-methods)
7. [Classes & Objects](#7-classes--objects)
8. [The Java API & java.lang](#8-the-java-api--javalang)
9. [Math Class](#9-math-class)
10. [Wrapper Classes](#10-wrapper-classes)
11. [Interview Q&A — All Topics](#11-interview-qa--all-topics)

---

## 1. Java Primitive Data Types

### Concept
Java has **8 primitive types** with fixed sizes. They are not objects.

| Type    | Size    | Range / Notes                            |
|---------|---------|------------------------------------------|
| `byte`  | 8 bits  | -128 to 127                              |
| `short` | 16 bits | -32,768 to 32,767                        |
| `int`   | 32 bits | ~-2.1 billion to 2.1 billion             |
| `long`  | 64 bits | Very large integers; use `L` suffix      |
| `float` | 32 bits | Decimal; use `f` suffix e.g. `3.14f`     |
| `double`| 64 bits | More precise decimal                     |
| `char`  | 16 bits | Single Unicode character e.g. `'A'`      |
| `boolean`| 1 bit | `true` or `false` only                   |

```java
int x = 42;
long bigNum = 40002L;
float pi = 3.14f;
double precise = 3.14159265;
boolean flag = true;
char letter = 'A';
```

### Key Rule
You **cannot directly assign a larger type to a smaller type** without casting:
```java
long y = 42;
int x = y;        // ❌ Won't compile
int x = (int) y;  // ✅ Explicit cast required
```

---

## 2. Type Casting

### Concept
**Casting** forces the compiler to convert one numeric type to another. It can **lose data** if the value exceeds the target type's range.

### Widening (Safe — Automatic)
Smaller → Larger: No cast needed.
```java
int x = 5;
long y = x;   // ✅ automatic widening
double d = x; // ✅ automatic widening
```

### Narrowing (Unsafe — Explicit Cast Required)
Larger → Smaller: Must use cast operator `(type)`.
```java
long y = 40002;
short x = (short) y;   // x = -25534 ⚠️ Data loss!
```

### Why Weird Numbers?
When a value exceeds the target range, the **binary bits are truncated from the left**, producing unexpected results due to two's complement representation.

### Float to Int
The decimal part is **truncated (not rounded)**:
```java
float f = 3.14f;
int x = (int) f;   // x = 3 (decimal is cut off)

float g = 3.99f;
int y = (int) g;   // y = 3 (still truncated, not 4)
```

### Boolean — Cannot Be Cast
```java
// ❌ NEVER do this — won't compile
int x = (int) true;
boolean b = (boolean) 1;
```

### Casting in Practice — Math.random()
```java
int randomNum = (int)(Math.random() * 10); // 0 to 9
```
`Math.random()` returns a `double` (0.0 to just under 1.0), so you **must cast** to int.

---

## 3. Operators & Expressions

### Arithmetic Operators
| Operator | Meaning     | Example        |
|----------|-------------|----------------|
| `+`      | Addition    | `5 + 3 = 8`    |
| `-`      | Subtraction | `5 - 3 = 2`    |
| `*`      | Multiply    | `5 * 3 = 15`   |
| `/`      | Divide      | `5 / 2 = 2` (int division) |
| `%`      | Modulus     | `5 % 2 = 1`    |

### Integer Division
```java
int a = 5 / 2;     // a = 2 (not 2.5!)
double b = 5.0 / 2; // b = 2.5
```

### Increment & Decrement
```java
int x = 5;
x++;   // x = 6 (post-increment: use THEN increment)
++x;   // x = 7 (pre-increment: increment THEN use)
x--;   // x = 6 (post-decrement)
--x;   // x = 5 (pre-decrement)
```

#### Pre vs Post — Critical Difference
```java
int a = 5;
System.out.println(a++); // prints 5, then a becomes 6
System.out.println(++a); // a becomes 7, then prints 7
```

### Compound Assignment
```java
x += 3;   // same as x = x + 3
x -= 2;
x *= 4;
x /= 2;
x %= 3;
```

### Comparison Operators
```java
==   // equal to
!=   // not equal to
>    // greater than
<    // less than
>=   // greater than or equal
<=   // less than or equal
```

### Logical Operators
```java
&&   // AND — both must be true
||   // OR  — at least one true
!    // NOT — reverses boolean
```

---

## 4. Control Flow — Loops

### for Loop
```java
for (initialization; condition; update) {
    // body
}

for (int i = 0; i < 8; i++) {
    System.out.println(i);
}
```
**Three parts:**
- **Init**: runs once before loop starts
- **Condition**: checked before each iteration
- **Update**: runs after each iteration

### while Loop
```java
int i = 0;
while (i < 5) {
    System.out.println(i);
    i++;
}
```
Checks condition **before** entering body.

### do-while Loop
```java
int i = 0;
do {
    System.out.println(i);
    i++;
} while (i < 5);
```
Executes body **at least once**, then checks condition.

### Nested Loops
```java
for (int i = 0; i < 4; i++) {
    for (int j = 4; j > 2; j--) {
        System.out.println(i + " " + j);
    }
}
// Output: 0 4, 0 3, 1 4, 1 3, 2 4, 2 3, 3 4, 3 3
```

### break Statement
Exits the **innermost** loop immediately:
```java
for (int i = 0; i < 10; i++) {
    if (i == 5) break;   // stops at 5
    System.out.println(i);
}
```

### continue Statement
Skips the **current iteration** and goes to the next:
```java
for (int i = 0; i < 5; i++) {
    if (i == 2) continue;  // skips 2
    System.out.println(i); // prints 0,1,3,4
}
```

### Loop Tracing Example (from book)
```java
void go() {
    int value = 7;
    for (int i = 1; i < 8; i++) {
        value++;                          // increment every iteration
        if (i > 4) {
            System.out.print(++value + " "); // pre-increment then print
        }
        if (value > 14) {
            System.out.println(" i = " + i);
            break;
        }
    }
}
// Output: 13 15  i = 6
```

**Trace:**
| i | value (after value++) | i > 4? | ++value printed | value > 14? |
|---|----------------------|--------|-----------------|-------------|
| 1 | 8                    | No     | —               | No          |
| 2 | 9                    | No     | —               | No          |
| 3 | 10                   | No     | —               | No          |
| 4 | 11                   | No     | —               | No          |
| 5 | 12                   | Yes    | 13              | No          |
| 6 | 13                   | Yes    | 15              | Yes → break |

---

## 5. Arrays

### Declaration & Initialization
```java
// Declare and allocate
int[] numbers = new int[5];

// Declare, allocate, and initialize
String[] islands = {"Fiji", "Cozumel", "Bermuda", "Azores"};
int[] scores = {95, 87, 72, 88, 91};
```

### Accessing Elements
```java
System.out.println(islands[0]);  // Fiji
System.out.println(islands[3]);  // Azores
islands[1] = "Hawaii";           // Modify element
```

### Array Length
```java
int[] arr = {1, 2, 3, 4, 5};
System.out.println(arr.length);  // 5 (property, not method)
```

### Iterating Arrays
```java
// Traditional for loop
for (int i = 0; i < islands.length; i++) {
    System.out.println("island = " + islands[i]);
}

// Enhanced for-each loop
for (String island : islands) {
    System.out.println("island = " + island);
}
```

### Output from book example:
```
island = Fiji
island = Cozumel
island = Bermuda
island = Azores
```

### Default Values
| Type             | Default |
|------------------|---------|
| `int`, `long`, etc. | `0`  |
| `float`, `double`   | `0.0`|
| `boolean`           | `false`|
| `char`              | `'\u0000'`|
| Object reference    | `null` |

### ArrayIndexOutOfBoundsException
```java
int[] arr = {1, 2, 3};
System.out.println(arr[5]); // ❌ Runtime exception!
// Valid indices: 0, 1, 2 (length-1)
```

---

## 6. Methods

### Method Structure
```java
returnType methodName(paramType paramName) {
    // body
    return value;
}

int add(int a, int b) {
    return a + b;
}
```

### void Methods
```java
void printHello() {
    System.out.println("Hello!");
    // No return statement needed (or use bare return;)
}
```

### Method with Parameters
```java
void go() {
    int value = 7;
    // method body
}
```

### Calling Methods
```java
Output output = new Output(); // create object
output.go();                  // call method on object
```

### static Methods
Called on the **class**, not an object:
```java
// Math class methods are static
double r = Math.random();
int abs = Math.abs(-5);
double sq = Math.sqrt(16);
```

### main Method
Entry point of every Java program:
```java
public static void main(String[] args) {
    // program starts here
}
```

---

## 7. Classes & Objects

### Class Definition
```java
class Output {
    // instance variables (state)
    int value = 7;

    // methods (behavior)
    void go() {
        System.out.println(value);
    }
}
```

### Creating Objects
```java
Output output = new Output();
output.go();
```

### Instance vs Local Variables
- **Instance variable**: defined in the class, outside methods; belongs to the object
- **Local variable**: defined inside a method; only exists during method execution

```java
class Dog {
    String name;     // instance variable

    void bark() {
        int count = 3;          // local variable
        System.out.println(name + " barks " + count + " times");
    }
}
```

---

## 8. The Java API & java.lang

### What Is the Java API?
The Java API is a **massive library of pre-built classes** you can use in your programs without writing them from scratch.

### java.lang — Automatically Imported
You never need to import `java.lang.*` — it's available by default.

Key classes:
- `String`
- `Math`
- `Integer`, `Double`, `Boolean` (Wrapper classes)
- `System`
- `Object`

### Using Other API Packages
```java
import java.util.ArrayList;
import java.util.Random;
```

---

## 9. Math Class

All methods are **static** — call them on the class directly.

```java
Math.random()          // double between 0.0 and < 1.0
Math.abs(-5)           // 5 (absolute value)
Math.sqrt(16.0)        // 4.0 (square root)
Math.pow(2, 10)        // 1024.0 (2 to the power 10)
Math.max(3, 7)         // 7
Math.min(3, 7)         // 3
Math.round(3.7)        // 4
Math.floor(3.9)        // 3.0
Math.ceil(3.1)         // 4.0
Math.PI                // 3.141592653589793 (constant)
```

### Random Integer in a Range
```java
// Random int from 0 to 9
int num = (int)(Math.random() * 10);

// Random int from 1 to 10
int num = (int)(Math.random() * 10) + 1;

// Random int from min to max (inclusive)
int num = (int)(Math.random() * (max - min + 1)) + min;
```

---

## 10. Wrapper Classes

### Concept
Each primitive has a corresponding **Wrapper class** that is an actual object.

| Primitive | Wrapper   |
|-----------|-----------|
| `int`     | `Integer` |
| `double`  | `Double`  |
| `boolean` | `Boolean` |
| `char`    | `Character`|
| `long`    | `Long`    |
| `float`   | `Float`   |
| `byte`    | `Byte`    |
| `short`   | `Short`   |

### Autoboxing & Unboxing (Java 5+)
Java automatically converts between primitive and wrapper:
```java
Integer obj = 42;        // autoboxing: int → Integer
int val = obj;           // unboxing: Integer → int
```

### Useful Wrapper Methods
```java
Integer.parseInt("42")       // String → int
Double.parseDouble("3.14")   // String → double
Integer.MAX_VALUE            // 2147483647
Integer.MIN_VALUE            // -2147483648
Integer.toBinaryString(255)  // "11111111"
```

### Why Wrapper Classes?
- Collections (like `ArrayList`) only hold **objects**, not primitives
- Parsing strings to numbers
- Utility methods (convert, compare, etc.)
- Used where `Object` type is required

---

## 11. Interview Q&A — All Topics

---

### 🔷 Section A: Primitives & Data Types

**Q1. What are the 8 primitive data types in Java?**
> `byte`, `short`, `int`, `long`, `float`, `double`, `char`, `boolean`

**Q2. What is the default value of an int? A boolean? An object reference?**
> `int` → `0`, `boolean` → `false`, object reference → `null`

**Q3. What is the difference between `int` and `Integer`?**
> `int` is a primitive (value stored directly, faster, no methods). `Integer` is a wrapper class (an object, can be null, has utility methods, required for collections).

**Q4. Can you assign a `long` to an `int` directly?**
> No. `long` is 64-bit and `int` is 32-bit. You must explicitly cast: `int x = (int) longValue;`. Data may be lost.

**Q5. What is the size of a `char` in Java?**
> 16 bits (2 bytes) — Java uses Unicode (UTF-16).

**Q6. Why can't you cast a `boolean` to an int in Java?**
> Java's type system does not define any relationship between `boolean` and numeric types. They are semantically incompatible — unlike C/C++ where `true = 1`.

---

### 🔷 Section B: Type Casting

**Q7. What is the difference between widening and narrowing conversion?**
> **Widening**: smaller type → larger type (e.g., `int` to `long`). Safe, automatic.
> **Narrowing**: larger type → smaller type (e.g., `long` to `int`). Requires explicit cast, can lose data.

**Q8. What happens when you cast a `float` to an `int`?**
> The decimal part is **truncated** (not rounded). `(int) 3.99f` gives `3`.

**Q9. What output does this produce?**
```java
long y = 40002;
short x = (short) y;
System.out.println(x);
```
> `-25534` — because 40002 exceeds the 16-bit short range (max 32767), the bits are truncated, resulting in a negative number via two's complement.

**Q10. Why do we need to cast `Math.random()` when generating a random int?**
> `Math.random()` returns a `double`. Assigning it to an `int` is a narrowing conversion, so explicit cast `(int)` is required.

**Q11. What is two's complement?**
> A binary representation method for signed integers where the leftmost bit is the sign bit (0=positive, 1=negative). When casting oversized values, the leftmost bits are cut off, flipping the sign bit and producing unexpected negative numbers.

---

### 🔷 Section C: Operators

**Q12. What is the difference between `++i` and `i++`?**
> `++i` (pre-increment): increments first, then returns the value.
> `i++` (post-increment): returns the value first, then increments.
```java
int i = 5;
System.out.println(i++); // prints 5, i becomes 6
System.out.println(++i); // i becomes 7, prints 7
```

**Q13. What does `%` (modulus) do?**
> Returns the remainder of division. `10 % 3 = 1`, `10 % 2 = 0` (even check).

**Q14. What is integer division? Give an example.**
> When both operands are integers, Java truncates the decimal: `7 / 2 = 3` (not 3.5). To get a decimal result, at least one operand must be a float/double: `7.0 / 2 = 3.5`.

**Q15. What is the result of `5 + 3 + "Hello"`?**
> `"8Hello"` — left to right evaluation: `5 + 3 = 8` (int addition), then `8 + "Hello" = "8Hello"` (string concatenation).

**Q16. What is the result of `"Hello" + 5 + 3`?**
> `"Hello53"` — because `"Hello" + 5` is `"Hello5"` (string concat), then `"Hello5" + 3` is `"Hello53"`.

---

### 🔷 Section D: Control Flow & Loops

**Q17. What are the three parts of a `for` loop?**
> 1. **Initialization** (runs once), 2. **Condition** (checked before each iteration), 3. **Update** (runs after each iteration body).

**Q18. What is the difference between `while` and `do-while`?**
> `while` checks condition **before** the body — may never execute.
> `do-while` checks condition **after** the body — always executes at least once.

**Q19. What does `break` do inside a loop?**
> Immediately exits the **innermost** loop (or switch statement). Execution continues after the loop.

**Q20. What does `continue` do?**
> Skips the rest of the **current iteration** and jumps to the loop's update step (for `for` loops) or condition check (for `while`/`do-while`).

**Q21. Trace this code — what is the output?**
```java
for (int i = 0; i < 4; i++) {
    for (int j = 4; j > 2; j--) {
        System.out.println(i + " " + j);
    }
    if (i == 1) { i++; }
}
```
> ```
> 0 4
> 0 3
> 1 4
> 1 3
> 3 4
> 3 3
> ```
> When `i == 1`, `i++` makes `i = 2`. Then the loop's own `i++` makes `i = 3`. So `i = 2` is skipped.

**Q22. Can you have an infinite loop? How?**
```java
while (true) { }       // infinite while loop
for (;;) { }           // infinite for loop
```
> Yes. Use `break` or return to exit.

**Q23. What is a nested loop and what is its time complexity?**
> A loop inside another loop. For two nested loops each running `n` times, complexity is O(n²).

---

### 🔷 Section E: Arrays

**Q24. What is the difference between `array.length` and `String.length()`?**
> `array.length` is a **property** (no parentheses).
> `String.length()` is a **method** (with parentheses).

**Q25. What exception occurs when you access an invalid array index?**
> `ArrayIndexOutOfBoundsException` — a runtime exception.

**Q26. Are arrays objects in Java?**
> Yes. Arrays are objects in Java and inherit from `Object`. They live on the heap.

**Q27. What is the default value of elements in a new `int[]` array?**
> `0`. Java initializes all array elements to default values automatically.

**Q28. What is the difference between `int[] arr = new int[5]` and `int[] arr = {1,2,3,4,5}`?**
> First creates array with default values (all zeros). Second creates and initializes with given values.

**Q29. Can an array hold different data types?**
> No (for typed arrays). An `int[]` only holds `int`. An `Object[]` can hold mixed types but loses type safety.

---

### 🔷 Section F: Methods

**Q30. What is the difference between a parameter and an argument?**
> **Parameter**: the variable in the method declaration: `void foo(int x)` — `x` is the parameter.
> **Argument**: the actual value passed when calling: `foo(5)` — `5` is the argument.

**Q31. What is a `void` method?**
> A method that does not return any value. It performs an action but returns nothing.

**Q32. Can a method call itself? What is this called?**
> Yes — this is called **recursion**. It must have a base case to avoid infinite recursion (StackOverflowError).

**Q33. What is method overloading?**
> Defining multiple methods with the **same name** but **different parameter lists** (different types or number of params).
```java
int add(int a, int b) { return a + b; }
double add(double a, double b) { return a + b; }
```

---

### 🔷 Section G: Classes & Objects

**Q34. What is the difference between a class and an object?**
> A **class** is a blueprint/template. An **object** is an instance of that class created at runtime with `new`.

**Q35. What is the difference between an instance variable and a local variable?**
> **Instance variable**: declared inside class, outside methods; belongs to object; has default value; lives as long as the object.
> **Local variable**: declared inside a method; no default value (must initialize); lives only during method execution.

**Q36. What does `new` do?**
> Allocates memory on the heap, creates an object, calls the constructor, and returns a reference to that object.

**Q37. What is `this` keyword?**
> Refers to the **current object** instance. Used to distinguish instance variables from local variables with the same name.
```java
class Dog {
    String name;
    Dog(String name) { this.name = name; } // this.name = instance var
}
```

---

### 🔷 Section H: Java API & Libraries

**Q38. What is the Java API?**
> A large collection of pre-built classes and interfaces bundled with the JDK. Allows developers to use ready-made code for common tasks (data structures, I/O, math, networking, etc.).

**Q39. What is `java.lang` and why don't you need to import it?**
> `java.lang` is the core Java package containing fundamental classes (`String`, `Math`, `Object`, `System`, wrapper classes). It is **automatically imported** by the compiler into every Java file.

**Q40. How do you use a class from the Java API that is NOT in `java.lang`?**
> You must import it explicitly:
```java
import java.util.ArrayList;
import java.util.Scanner;
```

**Q41. What is the difference between the JDK, JRE, and JVM?**
> - **JVM** (Java Virtual Machine): Executes bytecode
> - **JRE** (Java Runtime Environment): JVM + core libraries; runs Java programs
> - **JDK** (Java Development Kit): JRE + compiler + tools; develops Java programs

---

### 🔷 Section I: Math Class

**Q42. What does `Math.random()` return?**
> A `double` value ≥ 0.0 and < 1.0 (never exactly 1.0).

**Q43. How do you generate a random integer between 1 and 100 (inclusive)?**
```java
int num = (int)(Math.random() * 100) + 1;
```

**Q44. What is the difference between `Math.floor()`, `Math.ceil()`, and `Math.round()`?**
> - `Math.floor(3.7)` → `3.0` (rounds down)
> - `Math.ceil(3.2)` → `4.0` (rounds up)
> - `Math.round(3.5)` → `4` (rounds to nearest; .5 rounds up)

**Q45. Are all Math methods static? Why?**
> Yes. The `Math` class is a utility class — you never need to create a `Math` object. Static methods can be called directly on the class: `Math.sqrt(25)`.

---

### 🔷 Section J: Wrapper Classes

**Q46. What is autoboxing?**
> Java's automatic conversion of a **primitive to its wrapper object**:
```java
Integer obj = 42;  // compiler converts: Integer obj = Integer.valueOf(42);
```

**Q47. What is unboxing?**
> Automatic conversion of a **wrapper object to a primitive**:
```java
Integer obj = 42;
int val = obj;  // compiler converts: int val = obj.intValue();
```

**Q48. Can wrapper class objects be `null`? Can primitives?**
> Wrapper objects **can** be null (they are objects). Primitives **cannot** be null.

**Q49. What is the Integer cache in Java?**
> Java caches `Integer` objects for values -128 to 127. Autoboxed integers in this range are the **same object** in memory:
```java
Integer a = 127;
Integer b = 127;
System.out.println(a == b);   // true (same cached object)

Integer c = 128;
Integer d = 128;
System.out.println(c == d);   // false (different objects)
System.out.println(c.equals(d)); // true (same value)
```

**Q50. How do you convert a String to an int?**
```java
int num = Integer.parseInt("42");
```

---

### 🔷 Section K: Tricky / Advanced

**Q51. What is the output?**
```java
int x = 5;
System.out.println(x++ + ++x);
```
> `x++` returns 5 (then x=6), `++x` increments to 7 then returns 7. `5 + 7 = 12`.

**Q52. What is the output?**
```java
int x = 10;
x += x-- - --x;
System.out.println(x);
```
> This is complex compound assignment. Always prefer clear code over tricky operator chains in real code.

**Q53. What happens if you divide an int by zero?**
```java
int x = 5 / 0;  // throws ArithmeticException: / by zero
```

**Q54. What happens if you divide a double by zero?**
```java
double x = 5.0 / 0.0;  // x = Infinity (no exception!)
double y = 0.0 / 0.0;  // y = NaN (Not a Number)
```

**Q55. What is `NaN` and how do you check for it?**
> `NaN` (Not a Number) results from undefined floating-point operations. Use `Double.isNaN(value)` — never `value == Double.NaN` (which always returns false).

**Q56. Why does `0.1 + 0.2 != 0.3` in Java?**
> Floating-point numbers use binary representation, which cannot precisely represent all decimals. Use `BigDecimal` for exact decimal arithmetic (e.g., financial calculations).

**Q57. What is the difference between `==` and `.equals()` for objects?**
> `==` compares **references** (memory addresses).
> `.equals()` compares **content/value**.
```java
String a = new String("hello");
String b = new String("hello");
System.out.println(a == b);       // false (different objects)
System.out.println(a.equals(b));  // true  (same content)
```

**Q58. What is a `StackOverflowError`?**
> Occurs when recursion is too deep (no base case or base case never reached), exhausting the call stack.

**Q59. What is the difference between a compile-time error and a runtime error?**
> **Compile-time error**: Caught by the compiler before running (e.g., type mismatch, missing semicolon).
> **Runtime error**: Occurs during execution (e.g., `ArrayIndexOutOfBoundsException`, `NullPointerException`).

**Q60. What is pseudocode and why is it useful?**
> Pseudocode is informal, plain-language description of algorithm logic — not actual Java syntax. It helps plan program structure before writing real code, making it easier to catch logical errors early.

---

## 🧩 Quick Reference Cheat Sheet

```
CASTING:     (targetType) value
LOOP:        for(init; condition; update) { }
RANDOM INT:  (int)(Math.random() * n) + offset
PARSE INT:   Integer.parseInt("42")
ARRAY LEN:   arr.length    (NOT arr.length())
STRING LEN:  str.length()  (WITH parentheses)
PRE-INC:     ++x  → increments first, returns new value
POST-INC:    x++  → returns old value, then increments
```

---

*Based on Head First Java, Chapters 3, 5, & 6 — Primitives, Casting, Loops, Arrays, Methods, and the Java API*


# ☕ Java Inheritance & Polymorphism — Complete Concepts + Interview Guide

> Extracted from *Head First Java* — Chapter 7 | OOP Deep Dive

---

## 📚 Table of Contents

1. [What is Inheritance?](#1-what-is-inheritance)
2. [Superclass & Subclass](#2-superclass--subclass)
3. [The `extends` Keyword](#3-the-extends-keyword)
4. [What Gets Inherited?](#4-what-gets-inherited)
5. [Method Overriding](#5-method-overriding)
6. [Designing an Inheritance Tree](#6-designing-an-inheritance-tree)
7. [Abstract Classes (Intro)](#7-abstract-classes-intro)
8. [Polymorphism (Intro)](#8-polymorphism-intro)
9. [IS-A vs HAS-A Relationship](#9-is-a-vs-has-a-relationship)
10. [Real Code Examples](#10-real-code-examples)
11. [Interview Q&A — All Topics](#11-interview-qa--all-topics)
12. [Coding Questions](#12-coding-questions-with-solutions)

---

## 1. What is Inheritance?

### Concept
Inheritance allows one class (**subclass**) to **automatically get** all the instance variables and methods of another class (**superclass**), without rewriting that code.

> **Key Idea:** Put common code in ONE place (superclass). All subclasses automatically get it.

### Why It Matters
Without inheritance:
```
Dog     → has rotate(), playSound()
Cat     → has rotate(), playSound()   ← duplicate code!
Lion    → has rotate(), playSound()   ← duplicate code!
Tiger   → has rotate(), playSound()   ← duplicate code!
```

With inheritance:
```
Animal  → has rotate(), playSound()   ← ONE copy
Dog, Cat, Lion, Tiger → inherit from Animal automatically
```

**Benefit:** Maintain code in ONE place. Fix a bug once, it's fixed for all subclasses.

---

## 2. Superclass & Subclass

### Definitions
| Term           | Meaning                                             |
|----------------|-----------------------------------------------------|
| **Superclass** | The parent class. More abstract. Has shared code.   |
| **Subclass**   | The child class. More specific. Inherits from super.|

### The Shape Example (from book)
```
Shape              ← superclass (abstract, common behavior)
├── Circle         ← subclass
├── Square         ← subclass
├── Triangle       ← subclass
└── Amoeba         ← subclass (overrides some behavior)
```

```java
class Shape {
    void rotate() { /* common rotate logic */ }
    void playSound() { /* common sound logic */ }
}

class Circle extends Shape { }   // inherits rotate() and playSound()
class Square extends Shape { }   // inherits rotate() and playSound()
class Triangle extends Shape { } // inherits rotate() and playSound()

class Amoeba extends Shape {
    void rotate() { /* amoeba-specific rotate */ }      // overrides!
    void playSound() { /* amoeba-specific sound */ }    // overrides!
}
```

### The Doctor Example (from book)
```
Doctor                        ← superclass
├── Surgeon   (overrides treatPatient, adds makeIncision)
└── FamilyDoctor (adds makesHouseCalls, giveAdvice)
```

```java
public class Doctor {
    boolean worksAtHospital;
    void treatPatient() { /* perform checkup */ }
}

public class FamilyDoctor extends Doctor {
    boolean makesHouseCalls;
    void giveAdvice() { /* give advice */ }
    // treatPatient() inherited from Doctor
}

public class Surgeon extends Doctor {
    void treatPatient() { /* perform surgery — OVERRIDES */ }
    void makeIncision() { /* make incision */ }
}
```

**Answers from book exercise:**

| Question                          | Answer |
|-----------------------------------|--------|
| Surgeon instance variables?       | 1 (inherits `worksAtHospital` from Doctor) |
| FamilyDoctor instance variables?  | 2 (`makesHouseCalls` + inherited `worksAtHospital`) |
| Doctor methods?                   | 1 (`treatPatient`) |
| Surgeon methods?                  | 2 (`treatPatient` overridden + `makeIncision`) |
| FamilyDoctor methods?             | 2 (`giveAdvice` + inherited `treatPatient`) |
| Can FamilyDoctor do treatPatient?| ✅ Yes (inherited from Doctor) |
| Can FamilyDoctor do makeIncision? | ❌ No (only in Surgeon) |

---

## 3. The `extends` Keyword

### Syntax
```java
class SubClass extends SuperClass {
    // additional variables and methods
    // optionally override inherited methods
}
```

### Rules
- A class can only `extends` **ONE** class (single inheritance in Java)
- You can have **multi-level** inheritance: `A → B → C`
- Every class in Java **implicitly extends `Object`** if no superclass is specified

```java
class Animal { }                    // implicitly extends Object
class Dog extends Animal { }        // Dog → Animal → Object
class Labrador extends Dog { }      // Labrador → Dog → Animal → Object
```

---

## 4. What Gets Inherited?

### Inherited Members
A subclass inherits:
- ✅ All **instance variables** from superclass
- ✅ All **public and protected methods** from superclass
- ✅ All inherited stuff from the superclass's own superclass (chain)

### NOT Inherited
- ❌ **Constructors** (not inherited, but you can call them with `super()`)
- ❌ **Private** members (hidden from subclass)

### SuperHero Example (from book)
```java
class SuperHero {
    String suit;
    String tights;
    String specialPower;
    void useSpecialPower() { }
    void putOnSuit() { }
}

class PantherMan extends SuperHero {
    // Automatically has: suit, tights, specialPower
    // Automatically has: useSpecialPower(), putOnSuit()

    void useSpecialPower() { /* overrides — panther-specific */ }
    void putOnSuit() { /* overrides — panther-specific */ }
}

class FriedEggMan extends SuperHero {
    // Inherits everything, overrides NOTHING
    // Uses all SuperHero methods as-is
}
```

> **Key Point:** Instance variables are **never overridden** — subclass just sets different values.

```java
PantherMan pm = new PantherMan();
pm.tights = "purple";    // sets inherited variable to own value

FriedEggMan fe = new FriedEggMan();
fe.tights = "white";     // same inherited variable, different value
```

---

## 5. Method Overriding

### Concept
A subclass **redefines** a method it inherited from the superclass to change or extend its behavior.

### Rules for Overriding
1. Method name must be **exactly the same**
2. Parameters must be **exactly the same** (same type, same order)
3. Return type must be the **same** (or a subtype — covariant return)
4. Access modifier must be **same or less restrictive** (can't reduce visibility)
5. Use `@Override` annotation — best practice

```java
class Animal {
    void makeNoise() {
        System.out.println("Some generic animal sound");
    }
}

class Dog extends Animal {
    @Override
    void makeNoise() {
        System.out.println("Woof! Woof!");   // overrides with dog-specific
    }
}

class Cat extends Animal {
    @Override
    void makeNoise() {
        System.out.println("Meow!");         // overrides with cat-specific
    }
}
```

### Calling the Superclass Method with `super`
```java
class Amoeba extends Shape {
    @Override
    void rotate() {
        super.rotate();          // calls Shape's rotate() first
        System.out.println("Extra amoeba rotation logic");
    }
}
```

### Overriding vs Overloading — KEY DIFFERENCE

| Feature         | Overriding                     | Overloading                        |
|-----------------|--------------------------------|------------------------------------|
| Where           | Subclass overrides superclass  | Same class, different params       |
| Method name     | Same                           | Same                               |
| Parameters      | **Must be same**               | **Must be different**              |
| Return type     | Same (or subtype)              | Can be different                   |
| Runtime/Compile | Resolved at **runtime**        | Resolved at **compile time**       |
| Also called     | Runtime polymorphism           | Compile-time polymorphism          |

```java
// OVERRIDING (subclass)
class Animal { void eat() { } }
class Dog extends Animal {
    @Override void eat() { }    // same signature, different class
}

// OVERLOADING (same class)
class Calculator {
    int add(int a, int b) { return a + b; }
    double add(double a, double b) { return a + b; }  // different params
}
```

---

## 6. Designing an Inheritance Tree

### Step-by-Step Process (from book)

**Step 1:** Look for objects that have common attributes and behaviors.

**Step 2:** Design a class that represents the common state and behavior (superclass).

**Step 3:** Decide which methods need to be overridden by subclasses.

**Step 4:** Look for further grouping opportunities (intermediate classes).

**Step 5:** Finalize the hierarchy.

### Animal Simulation Design (from book)

```
Animal                              ← superclass
│  picture, food, hunger,
│  boundaries, location
│  makeNoise(), eat(), sleep(), roam()
│
├── Canine                          ← intermediate superclass
│   │  roam()  (group-specific)
│   ├── Dog
│   │     makeNoise(), eat()       (overridden)
│   └── Wolf
│         makeNoise(), eat()       (overridden)
│
├── Feline                          ← intermediate superclass
│   │  roam()  (group-specific)
│   ├── Cat
│   │     makeNoise(), eat()       (overridden)
│   ├── Tiger
│   │     makeNoise(), eat()       (overridden)
│   └── Lion
│         makeNoise(), eat()       (overridden)
│
└── Hippo
      makeNoise(), eat()           (overridden)
```

```java
class Animal {
    String picture;
    String food;
    int hunger;
    int[] boundaries;
    int[] location;

    void makeNoise() { }   // to be overridden
    void eat() { }         // to be overridden
    void sleep() { System.out.println("Zzz..."); }    // generic, shared
    void roam() { System.out.println("Wandering..."); } // generic, shared
}

class Canine extends Animal {
    @Override
    void roam() { System.out.println("Roaming with the pack"); }
}

class Dog extends Canine {
    @Override
    void makeNoise() { System.out.println("Woof!"); }
    @Override
    void eat() { System.out.println("Dog eating..."); }
}

class Wolf extends Canine {
    @Override
    void makeNoise() { System.out.println("Howl!"); }
    @Override
    void eat() { System.out.println("Wolf eating prey..."); }
}
```

### Design Principle
> Move shared behavior **UP** the hierarchy. Only put behavior in a subclass if it's specific to that type.

---

## 7. Abstract Classes (Intro)

### Concept
An abstract class is a partially implemented class that forces subclasses to implement specific methods.

```java

### Rules 
|                              | Simple Meaning                             |
| ---------------------------------------- | ------------------------------------------ |
| Abstract class cannot be instantiated    | You **cannot create its object**           |
| Abstract method has no body              | Only **method declaration**, no code       |
| Subclass must implement abstract methods | Child class **must write the method code** |
| Abstract class can have concrete methods | It **can also have normal methods**        |
| Reference of abstract class allowed      | You can write `Animal a = new Dog()`       |
| Constructor allowed                      | Abstract class **can have a constructor**  |

abstract class Animal {
    abstract void makeNoise();  // no body — MUST be overridden
    void sleep() { System.out.println("Zzz"); }  // concrete method
}

// Animal a = new Animal();  ❌ Cannot instantiate abstract class!
Animal d = new Dog();        // ✅ Reference type Animal, object type Dog
```

### Abstract Method
- Has no body
- Declared with `abstract` keyword
- Subclass **must** override it (unless subclass is also abstract)

```java
abstract class Shape {
    abstract void draw();     // no body — subclass MUST implement
    void erase() { System.out.println("Erasing..."); } // concrete
}

class Circle extends Shape {
    @Override
    void draw() { System.out.println("Drawing circle"); } // must implement
}
```

---

## 8. Polymorphism (Intro)

### Concept
**Poly** = many, **morph** = form. One reference type can refer to objects of many different subclass types.

```java
Animal[] animals = new Animal[4];
animals[0] = new Dog();
animals[1] = new Cat();
animals[2] = new Wolf();
animals[3] = new Hippo();

for (Animal a : animals) {
    a.makeNoise();   // calls each animal's OWN version at RUNTIME
}
// Output: Woof! / Meow! / Howl! / Grunt!
```

> **Key:** The JVM figures out **which method to call at runtime** based on the actual object type — not the reference type.

### Why Polymorphism is Powerful
```java
// Without polymorphism — you'd need this:
void process(Dog d) { d.makeNoise(); }
void process(Cat c) { c.makeNoise(); }
void process(Wolf w) { w.makeNoise(); }

// With polymorphism — one method handles ALL animals:
void process(Animal a) { a.makeNoise(); }   // works for Dog, Cat, Wolf, etc.
```

---

## 9. IS-A vs HAS-A Relationship

### IS-A (Inheritance)
```
Dog IS-A Animal       ✅  → use extends
Surgeon IS-A Doctor   ✅  → use extends
```

### HAS-A (Composition)
```
Dog HAS-A Collar      ✅  → use instance variable
Car HAS-A Engine      ✅  → use instance variable
```

### IS-A Test
Before using inheritance, ask: **"Does this IS-A relationship make sense in ALL cases?"**

```java
// ✅ Good: Triangle IS-A Shape (always true)
class Triangle extends Shape { }

// ❌ Bad: Tub IS-A Bathroom (not always true — tub can be anywhere)
class Tub extends Bathroom { }  // wrong! use HAS-A instead

class Bathroom {
    Tub myTub;    // ✅ Bathroom HAS-A Tub
}
```

### IS-A is Directional
```
Dog IS-A Animal       ✅
Animal IS-A Dog       ❌ (not every animal is a dog)
```

---

## 10. Real Code Examples

### Full Inheritance Example
```java
class Vehicle {
    String brand;
    int speed;

    void move() {
        System.out.println(brand + " moving at " + speed + " km/h");
    }

    void stop() {
        System.out.println(brand + " stopped");
    }
}

class Car extends Vehicle {
    int doors;

    @Override
    void move() {
        System.out.println(brand + " car driving at " + speed + " km/h");
    }

    void honk() {
        System.out.println("Beep beep!");
    }
}

class Motorcycle extends Vehicle {
    boolean hasSidecar;

    @Override
    void move() {
        System.out.println(brand + " motorcycle zooming at " + speed + " km/h");
    }
}

// Usage
Vehicle v = new Car();    // polymorphism!
v.move();                 // calls Car's move() — runtime decision
```

### Multi-level Inheritance
```java
class Animal {
    void breathe() { System.out.println("Breathing..."); }
}

class Mammal extends Animal {
    void feedMilk() { System.out.println("Feeding milk..."); }
}

class Dog extends Mammal {
    void bark() { System.out.println("Woof!"); }
}

// Dog has: breathe() + feedMilk() + bark()
Dog d = new Dog();
d.breathe();    // from Animal
d.feedMilk();  // from Mammal
d.bark();      // own method
```

---

## 11. Interview Q&A — All Topics

---

### 🔷 Section A: Inheritance Basics

**Q1. What is inheritance in Java?**
> Inheritance is an OOP mechanism where a **subclass** automatically acquires the instance variables and methods of a **superclass**. It promotes code reuse and establishes an IS-A relationship between classes.

**Q2. What keyword is used for inheritance in Java?**
> `extends`. A class can extend only **one** superclass (single inheritance).
```java
class Dog extends Animal { }
```

**Q3. Why do we use inheritance?**
> - **Code reuse** — write once, use many times
> - **Maintainability** — fix code in one place
> - **Extensibility** — add new classes without changing existing code
> - **Polymorphism** — treat related objects uniformly

**Q4. What is a superclass? What is a subclass?**
> **Superclass (parent)**: The more general class that contains shared code.
> **Subclass (child)**: The more specific class that inherits from the superclass. It can add new behavior and override inherited behavior.

**Q5. Does Java support multiple inheritance with classes?**
> **No.** Java does NOT support multiple inheritance with classes (a class cannot extend more than one class). This avoids the "Diamond Problem." However, a class **can implement multiple interfaces**.

**Q6. What is the Diamond Problem?**
> When a class inherits from two classes that both have a method with the same signature, it's ambiguous which version to use. Java avoids this by restricting class inheritance to one parent.

**Q7. What does a subclass inherit from its superclass?**
> - All **public** and **protected** instance variables and methods
> - Package-private members (if in the same package)
> Does NOT inherit: **private** members, **constructors**

**Q8. What is multi-level inheritance?**
> A chain of inheritance: `C extends B`, `B extends A`.
> C inherits from both B and A. This is allowed in Java.
```java
class A { }
class B extends A { }
class C extends B { }  // C has everything from B and A
```

**Q9. What class does every Java class inherit from?**
> `java.lang.Object` — it's the root of the entire Java class hierarchy. Every class implicitly extends `Object` if no explicit superclass is given.

---

### 🔷 Section B: Method Overriding

**Q10. What is method overriding?**
> When a **subclass provides its own implementation** of a method that it already inherited from its superclass. The method must have the **same name, same parameters, and same return type**.

**Q11. What is the `@Override` annotation? Is it mandatory?**
> It tells the compiler "I intend to override a superclass method." Not mandatory, but **strongly recommended** — the compiler will catch mistakes if you accidentally create a new method instead of overriding.

**Q12. What are the rules for method overriding?**
> 1. Same method name
> 2. Same parameter list
> 3. Same return type (or covariant — a subtype)
> 4. Cannot reduce visibility (public → private is NOT allowed)
> 5. Cannot throw new or broader checked exceptions
> 6. `static` and `private` methods cannot be overridden

**Q13. Can you override a private method?**
> **No.** Private methods are not visible to subclasses — they are not inherited, so they cannot be overridden. If you define a method with the same name in a subclass, it's a new method, not an override.

**Q14. Can you override a static method?**
> **No.** Static methods belong to the class, not instances. What looks like overriding a static method is actually **method hiding**. The version called depends on the reference type, not the object type (no runtime polymorphism).

**Q15. What is the difference between method overriding and method hiding?**
> **Overriding** (instance methods): The subclass's method is called based on the **actual object type** at runtime.
> **Hiding** (static methods): The method called is based on the **reference type** at compile time.

**Q16. How do you call the superclass's overridden method from the subclass?**
> Using `super.methodName()`:
```java
class Dog extends Animal {
    @Override
    void makeNoise() {
        super.makeNoise();    // calls Animal's version
        System.out.println("Woof!");
    }
}
```

**Q17. What is covariant return type?**
> An overriding method can return a **subtype** of the original return type. This is allowed since Java 5.
```java
class Animal {
    Animal create() { return new Animal(); }
}
class Dog extends Animal {
    @Override
    Dog create() { return new Dog(); }  // Dog IS-A Animal — allowed!
}
```

---

### 🔷 Section C: Overriding vs Overloading

**Q18. What is the difference between method overriding and method overloading?**

| Feature         | Overriding                     | Overloading                        |
|-----------------|--------------------------------|------------------------------------|
| Location        | Subclass vs superclass         | Same class                         |
| Parameters      | Must be identical              | Must be different                  |
| Resolved at     | **Runtime** (dynamic dispatch) | **Compile time** (static dispatch) |
| `@Override`     | Applicable                     | Not applicable                     |
| Relationship    | IS-A (inheritance)             | Same class                         |

**Q19. Can we overload the `main` method?**
> Yes! You can define multiple `main` methods with different parameters. But the JVM only calls `public static void main(String[] args)` as the entry point.

---

### 🔷 Section D: Abstract Classes

**Q20. What is an abstract class?**
> A class declared with the `abstract` keyword that **cannot be instantiated** directly. It can have abstract methods (no body) and concrete methods (with body).

**Q21. What is an abstract method?**
> A method with no body, declared with `abstract`. Any concrete (non-abstract) subclass **must** override all abstract methods.
```java
abstract class Shape {
    abstract double area();   // no body — subclass MUST implement
}
```

**Q22. Can an abstract class have a constructor?**
> Yes! Even though you can't instantiate an abstract class directly, its constructor can be called by subclasses using `super()`.

**Q23. Can an abstract class have non-abstract (concrete) methods?**
> Yes. Abstract classes can have both abstract methods AND fully implemented (concrete) methods.

**Q24. What is the difference between abstract class and interface?**

| Feature               | Abstract Class              | Interface                    |
|-----------------------|-----------------------------|------------------------------|
| Instantiation         | Cannot instantiate          | Cannot instantiate           |
| Methods               | Abstract + concrete         | Abstract (+ default/static since Java 8) |
| Variables             | Instance variables allowed  | Only `public static final` constants |
| Inheritance           | `extends` (one only)        | `implements` (multiple!)     |
| Constructor           | Has constructor             | No constructor               |
| Use when              | Related classes, share code | Unrelated classes, define capability |

---

### 🔷 Section E: Polymorphism

**Q25. What is polymorphism in Java?**
> The ability of a **single reference type** to refer to objects of **many different types**, and to call the correct method on each at runtime.
```java
Animal a = new Dog();
a.makeNoise();  // calls Dog's makeNoise() — not Animal's
```

**Q26. What are the two types of polymorphism in Java?**
> 1. **Runtime polymorphism** (dynamic dispatch): Method overriding — the JVM decides which method to call based on the actual object type at runtime.
> 2. **Compile-time polymorphism** (static dispatch): Method overloading — compiler decides based on parameters at compile time.

**Q27. What is dynamic method dispatch?**
> The process by which the JVM determines which overridden method to call **at runtime** based on the actual type of the object, not the declared reference type.
```java
Animal a = new Dog();  // reference type = Animal, object type = Dog
a.makeNoise();         // JVM calls Dog's makeNoise() — decided at runtime
```

**Q28. Can you assign a subclass object to a superclass reference?**
> Yes — this is **upcasting** and it's automatic (no cast operator needed):
```java
Animal a = new Dog();  // ✅ Dog IS-A Animal — automatic upcast
```

**Q29. What is downcasting?**
> Assigning a superclass reference back to a subclass type. Requires explicit cast and can throw `ClassCastException` if wrong:
```java
Animal a = new Dog();
Dog d = (Dog) a;      // ✅ works — object actually IS a Dog

Animal a2 = new Cat();
Dog d2 = (Dog) a2;    // ❌ ClassCastException at runtime!
```

**Q30. How do you safely downcast? What is `instanceof`?**
> Use `instanceof` to check before casting:
```java
if (a instanceof Dog) {
    Dog d = (Dog) a;  // safe cast
}
```

---

### 🔷 Section F: IS-A and HAS-A

**Q31. What is the IS-A relationship?**
> Represents inheritance: "A Dog IS-A Animal." Used to decide if `extends` is appropriate. Must be true in **all** directions and situations.

**Q32. What is the HAS-A relationship?**
> Represents composition: "A Car HAS-A Engine." Implemented using an **instance variable** referencing another object.

**Q33. How do you decide between inheritance (IS-A) and composition (HAS-A)?**
> Ask: "Is X truly a type of Y in all cases?"
> - If YES → use inheritance (IS-A)
> - If NO → use composition (HAS-A)

**Q34. Can you give an example where people mistakenly use inheritance instead of composition?**
> Bad design: `class Stack extends ArrayList` — a Stack IS NOT an ArrayList (you shouldn't be able to call `get(index)` on a Stack).
> Good design: `class Stack { private ArrayList list; }` — Stack HAS-A ArrayList.

---

### 🔷 Section G: `super` Keyword

**Q35. What is the `super` keyword used for?**
> Three uses:
> 1. Call superclass constructor: `super()` or `super(args)`
> 2. Call superclass method: `super.methodName()`
> 3. Access superclass variable: `super.variableName`

**Q36. Where must `super()` appear in a constructor?**
> As the **first statement** in the subclass constructor.
```java
class Dog extends Animal {
    Dog(String name) {
        super(name);  // MUST be first line
        // rest of constructor
    }
}
```

**Q37. What happens if you don't explicitly call `super()` in a subclass constructor?**
> Java automatically inserts a call to `super()` (the no-arg constructor of the superclass). If the superclass has no no-arg constructor, you'll get a **compile error**.

---

### 🔷 Section H: `final` Keyword

**Q38. What does `final` mean for a class?**
> The class **cannot be subclassed** (no class can extend it):
```java
final class String { }   // you can't extend String
```

**Q39. What does `final` mean for a method?**
> The method **cannot be overridden** by subclasses.

**Q40. What does `final` mean for a variable?**
> The variable's value **cannot be changed** after assignment (constant).

---

### 🔷 Section I: Tricky Concepts

**Q41. What is the output?**
```java
class Animal {
    void makeNoise() { System.out.println("Animal sound"); }
}
class Dog extends Animal {
    @Override
    void makeNoise() { System.out.println("Woof"); }
}
public class Test {
    public static void main(String[] args) {
        Animal a = new Dog();
        a.makeNoise();
    }
}
```
> **Output: `Woof`**
> Because the actual object is `Dog`, even though the reference is `Animal`. Runtime polymorphism calls `Dog`'s method.

**Q42. What is the output?**
```java
class Parent {
    static void display() { System.out.println("Parent static"); }
    void show() { System.out.println("Parent instance"); }
}
class Child extends Parent {
    static void display() { System.out.println("Child static"); }
    @Override
    void show() { System.out.println("Child instance"); }
}
public class Test {
    public static void main(String[] args) {
        Parent p = new Child();
        p.display();  // static — method hiding
        p.show();     // instance — method overriding
    }
}
```
> `Parent static` (static methods use reference type — method hiding, not overriding)
> `Child instance` (instance methods use actual object type — runtime polymorphism)

**Q43. What is the output?**
```java
class A {
    A() { System.out.println("A constructor"); }
}
class B extends A {
    B() { System.out.println("B constructor"); }
}
class C extends B {
    C() { System.out.println("C constructor"); }
}
new C();
```
> ```
> A constructor
> B constructor
> C constructor
> ```
> Constructors are called **top-down** (superclass first). Each calls `super()` implicitly.

**Q44. Can a subclass reduce the visibility of an inherited method?**
> **No.** You cannot reduce visibility when overriding. You can only maintain or increase it.
```java
class Animal { public void eat() { } }
class Dog extends Animal {
    private void eat() { }  // ❌ Compile error! Can't reduce from public to private
}
```

**Q45. What happens if a subclass constructor doesn't call `super()` and the superclass has no no-arg constructor?**
```java
class Animal {
    Animal(String name) { }  // only parameterized constructor
}
class Dog extends Animal {
    Dog() { }  // ❌ compile error — no super(name) call, no default Animal()
}
```
> **Compile error.** Java inserts implicit `super()` but finds no matching no-arg constructor in Animal.

---

## 12. Coding Questions with Solutions

---

**Coding Q1: Create a 3-level inheritance chain for vehicles.**
```java
class Vehicle {
    String brand;
    int speed;
    void move() { System.out.println(brand + " moving"); }
}

class Car extends Vehicle {
    int doors;
    @Override
    void move() { System.out.println(brand + " car driving"); }
}

class ElectricCar extends Car {
    int batteryLevel;
    @Override
    void move() { System.out.println(brand + " electric car silently driving"); }
    void charge() { System.out.println("Charging battery..."); }
}

// ElectricCar has: brand, speed (from Vehicle) + doors (from Car) + batteryLevel (own)
```

---

**Coding Q2: Demonstrate polymorphism with an array.**
```java
class Shape {
    double area() { return 0; }
}
class Circle extends Shape {
    double radius;
    Circle(double r) { this.radius = r; }
    @Override
    double area() { return Math.PI * radius * radius; }
}
class Rectangle extends Shape {
    double width, height;
    Rectangle(double w, double h) { this.width = w; this.height = h; }
    @Override
    double area() { return width * height; }
}

// Polymorphic usage
Shape[] shapes = { new Circle(5), new Rectangle(4, 6), new Circle(3) };
for (Shape s : shapes) {
    System.out.println("Area: " + s.area());  // calls correct version!
}
```

---

**Coding Q3: Use `super` to call superclass constructor and method.**
```java
class Animal {
    String name;
    Animal(String name) {
        this.name = name;
        System.out.println("Animal created: " + name);
    }
    void eat() { System.out.println(name + " is eating"); }
}

class Dog extends Animal {
    String breed;
    Dog(String name, String breed) {
        super(name);              // calls Animal(String name)
        this.breed = breed;
        System.out.println("Dog breed: " + breed);
    }
    @Override
    void eat() {
        super.eat();              // calls Animal's eat()
        System.out.println("(chewing bone enthusiastically)");
    }
}

Dog d = new Dog("Rex", "Labrador");
d.eat();
// Output:
// Animal created: Rex
// Dog breed: Labrador
// Rex is eating
// (chewing bone enthusiastically)
```

---

**Coding Q4: Safe downcasting with `instanceof`.**
```java
class Animal { void speak() { System.out.println("..."); } }
class Dog extends Animal { void fetch() { System.out.println("Fetching!"); } }
class Cat extends Animal { void purr() { System.out.println("Purring!"); } }

Animal[] animals = { new Dog(), new Cat(), new Dog() };

for (Animal a : animals) {
    if (a instanceof Dog) {
        Dog d = (Dog) a;     // safe cast
        d.fetch();
    } else if (a instanceof Cat) {
        Cat c = (Cat) a;     // safe cast
        c.purr();
    }
}
// Output: Fetching! / Purring! / Fetching!
```

---

**Coding Q5: Reproduce the Doctor inheritance from the book.**
```java
public class Doctor {
    boolean worksAtHospital;
    void treatPatient() {
        System.out.println("Doctor: performing checkup");
    }
}

public class FamilyDoctor extends Doctor {
    boolean makesHouseCalls;
    void giveAdvice() {
        System.out.println("FamilyDoctor: take two aspirin and rest");
    }
}

public class Surgeon extends Doctor {
    @Override
    void treatPatient() {
        System.out.println("Surgeon: performing surgery");
    }
    void makeIncision() {
        System.out.println("Surgeon: making incision");
    }
}

// Test
Doctor d = new Surgeon();
d.treatPatient();    // "Surgeon: performing surgery" (polymorphism!)
```

---

## 🧩 Quick Reference Cheat Sheet

```
INHERITANCE:      class Child extends Parent { }
OVERRIDE:         @Override + same name + same params
SUPER METHOD:     super.methodName()
SUPER CONSTRUCTOR:super() — must be FIRST line
IS-A TEST:        "Dog IS-A Animal" → valid inheritance
HAS-A TEST:       "Car HAS-A Engine" → use instance variable
POLYMORPHISM:     Animal a = new Dog();  → a.makeNoise() calls Dog's version
INSTANCEOF:       if (a instanceof Dog) { Dog d = (Dog) a; }
FINAL CLASS:      cannot be extended
FINAL METHOD:     cannot be overridden
ABSTRACT CLASS:   cannot instantiate, may have abstract methods
ABSTRACT METHOD:  no body, MUST be overridden by concrete subclass
```

---

*Based on Head First Java, Chapter 7 — Inheritance and Polymorphism*

# ☕ Java Interfaces & Abstract Classes — Quick Revision + Interview Prep

> Head First Java — Chapters 7 & 8 | Short Notes Edition

---

## 📌 1. Abstract Classes — Quick Notes

### What & Why
- A class you **cannot instantiate** (`new Animal()` ❌)
- Used when a superclass is **too generic** to make objects from
- Forces subclasses to provide specific implementations
- Still used as **reference type** for polymorphism ✅

### Syntax
```java
abstract class Animal {
    abstract void makeNoise();       // no body — MUST override
    void sleep() { System.out.println("Zzz"); }  // concrete — optional override
}
// Animal a = new Animal(); ❌ compile error
Animal a = new Dog();               // ✅ reference only
```

### Key Rules
| Rule | Detail |
|------|--------|
| `abstract` keyword | before class name |
| Cannot instantiate | `new AbstractClass()` → compile error |
| Abstract method | no body, subclass MUST override |
| Can have concrete methods | yes — mix of both allowed |
| Can have constructor | yes — called via `super()` |
| Can have instance variables | yes |
| Subclass must implement ALL abstract methods | unless subclass is also abstract |

---

## 📌 2. Interface — Quick Notes

### What & Why
- A **100% abstract class** (conceptually)
- Defines a **contract** — "what to do", not "how to do it"
- Solves the **multiple inheritance** problem
- Enables polymorphism across **unrelated** class trees

### Syntax
```java
interface Flyable {
    void fly();              // implicitly public abstract
    void land();
}

class Bird extends Animal implements Flyable {
    public void fly() { System.out.println("Bird flying"); }
    public void land() { System.out.println("Bird landing"); }
}
```

### Key Rules
| Rule | Detail |
|------|--------|
| `interface` keyword | replaces `class` |
| `implements` keyword | class uses interface |
| Methods | implicitly `public abstract` |
| Variables | implicitly `public static final` (constants) |
| Multiple interfaces | YES — `implements A, B, C` |
| `extends` + `implements` | YES — `class Dog extends Animal implements Flyable` |
| Cannot instantiate | `new Flyable()` ❌ |
| No constructor | interfaces have no constructor |
| Java 8+ | can have `default` and `static` methods with body |

---

## 📌 3. Abstract Class vs Interface — THE BIG TABLE

| Feature | Abstract Class | Interface |
|---------|---------------|-----------|
| Keyword | `abstract class` | `interface` |
| Used with | `extends` | `implements` |
| Multiple? | ONE only | MULTIPLE ✅ |
| Methods | abstract + concrete | abstract (+ default/static since Java 8) |
| Variables | instance variables allowed | `public static final` only |
| Constructor | ✅ has constructor | ❌ no constructor |
| Access modifiers | any | methods are `public` by default |
| Use when | related classes, shared code | unrelated classes, define capability |

---

## 📌 4. Polymorphism with Interfaces

```java
interface Playable {
    void play();
}
class Guitar implements Playable {
    public void play() { System.out.println("Strum!"); }
}
class Piano implements Playable {
    public void play() { System.out.println("Tinkle!"); }
}

// Polymorphic — works for ALL Playable types
Playable[] instruments = { new Guitar(), new Piano() };
for (Playable p : instruments) {
    p.play();   // correct version called at runtime
}
```

> Guitar and Piano are **unrelated** classes yet share the `Playable` behavior — this is the power of interfaces.

---

## 📌 5. The Animal Hierarchy (from book)

```
Animal (abstract)
│  makeNoise() ← abstract
│  eat()       ← abstract
│  sleep()     ← concrete
│  roam()      ← concrete
│
├── Canine (abstract) — overrides roam()
│   ├── Dog   — overrides makeNoise(), eat()
│   └── Wolf  — overrides makeNoise(), eat()
│
├── Feline (abstract) — overrides roam()
│   ├── Cat   — overrides makeNoise(), eat()
│   ├── Tiger — overrides makeNoise(), eat()
│   └── Lion  — overrides makeNoise(), eat()
│
└── Hippo — overrides makeNoise(), eat()
```

---

## 📌 6. BE the Compiler — Key Rules (from exercise)

### Valid Override ✅
- Same method name
- Same parameter type(s)
- Compatible return type (same or subtype)
- Same or wider access modifier

### NOT a valid override ❌
```java
// Changing ONLY return type from boolean to int = NOT valid
class Monster { boolean frighten(int d) { return true; } }
class Vampire extends Monster {
    int frighten(int x) { return 1; }  // ❌ int not compatible with boolean
}
```

### NOT an overload either ❌
> Changing ONLY the return type is **neither** a valid override nor a valid overload.
> Overloading requires **different parameters**.

```java
// This IS a valid overload (different param type: byte vs int)
boolean frighten(byte b) { return true; }   // overload, NOT override
// But at runtime, frighten(int) won't call this — type mismatch
```

---

## 📌 7. Concrete vs Abstract — Decision Guide

| Class Type | Can Instantiate? | Has abstract methods? |
|------------|-----------------|----------------------|
| Concrete | ✅ YES | No |
| Abstract | ❌ NO | Optional (can have 0+) |
| Interface | ❌ NO | All methods abstract (before Java 8) |

**Rule of thumb:**
- Is the class specific enough to make an object? → **Concrete**
- Too generic / "what does a new Animal look like?" → **Abstract**
- Defines a capability across unrelated classes? → **Interface**

---

## 📌 8. Pool Puzzle Key Takeaways (from exercise)

```java
// Inheritance in action — Boat hierarchy
class Boat {
    private int length;
    public void setLength(int len) { length = len; }
    public int getLength() { return length; }
    public void move() { System.out.print("drift "); }
}
class Rowboat extends Boat {
    public void rowTheBoat() { System.out.print("stroke natasha"); }
    // move() inherited from Boat → prints "drift"
}
class Sailboat extends Boat {
    public void move() { System.out.print("hoist sail "); }  // overrides
}
// Output: drift  drift  hoist sail
```

**Key lessons:**
- `private` variables are NOT directly accessible in subclasses — use getter/setter
- Subclass inherits `move()` unless it overrides it
- `void` is a valid return type (not just `boolean`/`int`)

---

## 🎯 Interview Q&A — Fast Revision

**Q1. Can you instantiate an abstract class?**
> No. Compile error. Use it only as a reference type.

**Q2. Can an abstract class have no abstract methods?**
> Yes! A class can be declared `abstract` with zero abstract methods — just to prevent instantiation.

**Q3. What happens if a subclass doesn't implement all abstract methods?**
> The subclass must also be declared `abstract`, or it won't compile.

**Q4. Can an interface extend another interface?**
> Yes! `interface B extends A` — and it can extend multiple interfaces: `interface C extends A, B`.

**Q5. Can a class implement multiple interfaces?**
> Yes — `class Dog extends Animal implements Flyable, Swimmable` ✅

**Q6. What is default method in interface (Java 8)?**
> A method with a body inside an interface using `default` keyword. Subclasses get it for free but can override.
```java
interface Flyable {
    default void land() { System.out.println("Generic landing"); }
}
```

**Q7. Changing ONLY the return type — is it overriding or overloading?**
> **Neither** if return type is incompatible. If return type is a subtype → **override** (covariant). Changing ONLY return type is **never** an overload.

**Q8. What is a marker interface?**
> An interface with **no methods** at all. Used to tag/mark a class (e.g., `Serializable`, `Cloneable`).

**Q9. Why use interface over abstract class?**
> When you want **multiple inheritance of type**, or when classes are **unrelated** but share a capability (e.g., both `Bird` and `Airplane` can `Fly`, but they're not related by inheritance).

**Q10. IS-A test with interfaces — does it work?**
> Yes! If `Dog implements Flyable`, then `Dog IS-A Flyable` is true. You can use `Flyable` as reference type.
```java
Flyable f = new Dog();  // ✅ if Dog implements Flyable
```

---

## 🧩 Cheat Sheet

```
abstract class  → cannot instantiate, has abstract + concrete methods
abstract method → no body, subclass MUST override
interface       → 100% abstract (pre Java 8), implements, multiple allowed
extends         → ONE superclass only
implements      → ONE or MORE interfaces

OVERRIDE rules:
  ✅ same name + same params + compatible return + same/wider access
  ❌ only return type changed + types incompatible = compile error
  ❌ private/static methods cannot be overridden

POLYMORPHISM:
  Animal a = new Dog();     → reference = Animal, object = Dog
  a.makeNoise();            → calls Dog's version at RUNTIME

INTERFACE polymorphism:
  Flyable f = new Bird();   → works if Bird implements Flyable
```

---

*Head First Java — Chapters 7 & 8 | Abstract Classes, Interfaces & Polymorphism*


# ☕ Java — Interfaces & Polymorphism Cheat Sheet
> Head First Java Ch.8 | Interview Ready

---

## 🧱 When to Use What?

| Use | When |
|-----|------|
| **Plain class** | Doesn't pass IS-A test for anything |
| **Subclass** (`extends`) | Need more specific version, override/add behavior |
| **Abstract class** | Template for subclasses, has SOME shared code, can't instantiate |
| **Interface** | Define a ROLE any class can play, regardless of inheritance tree |

---

## ⚖️ Abstract Class vs Interface

| | Abstract Class | Interface |
|--|----------------|-----------|
| Methods | Concrete + abstract both allowed | All abstract (except `default`/`static`) |
| Inheritance | Only **one** (`extends`) | **Multiple** (`implements`) |
| Fields | Any type | Only `public static final` (constants) |
| Constructor | ✅ Yes | ❌ No |

> 💡 Even **ONE** abstract method → entire class must be marked `abstract`  
> 💡 Abstract method has **no body**: `public void go();`

---

## 🔗 extends vs implements

```java
// Only ONE superclass
class Dog extends Animal { }

// MULTIPLE interfaces allowed ✅
class Dog extends Animal implements Pet, Trainable { }
```

- **Must implement ALL** interface methods in the concrete class
- Abstract class can `implements` without overriding — defers to subclass
- **Deadly Diamond of Death 💀** = why multiple class inheritance is banned

---

## 🎭 Polymorphism — The Core Rules

```
Reference type  →  decides WHAT methods you CAN CALL
Object type     →  decides WHICH version actually RUNS
```

```java
Animal a = new Dog();   // ref = Animal, obj = Dog
a.eat();                // runs Dog's eat() ← polymorphism
a.fetch();              // ❌ compile error — Animal has no fetch()
```

```java
// Object reference = most restrictive
Object o = new Dog();
o.eat();   // ❌ Object doesn't have eat()

// Cast to access subclass methods
Dog d = (Dog) o;   // ✅ runtime cast
```

> ⚠️ Bad cast → `ClassCastException` at **runtime**  
> ✅ Prefer compile-time errors over runtime failures

---

## 🧬 super Keyword

```java
abstract class Report {
    void runReport() { /* generic setup */ }
}

class BuzzwordsReport extends Report {
    void runReport() {
        super.runReport();   // ← runs parent's version first
        buzzwordCompliance(); // ← then adds subclass-specific stuff
    }
}
```

> Use `super.method()` when you want to **ADD TO** superclass behavior, not fully replace it

---

## 📦 ArrayList & Generics (Quick)

```java
ArrayList<Dog> list = new ArrayList<>();
list.add(new Dog());     // ✅ only Dogs allowed
Dog d = list.get(0);     // ✅ no cast needed — compiler handles it
```

- `<Dog>` signals compiler: only Dogs in, only Dogs out
- Compiler inserts cast automatically
- Blocks wrong types at **compile time** not runtime ✅

---

## ⚡ Interview Quick-Fire

| Question | Answer |
|----------|--------|
| Can abstract class have a constructor? | **YES** — subclass calls via `super()` |
| Can interface have fields? | Only `public static final` (constants) |
| Every class extends? | `java.lang.Object` — root of all Java |
| Why no multiple class inheritance? | Deadly Diamond of Death — ambiguous method resolution |
| Abstract class vs interface in one line? | Abstract = *shared template*, Interface = *pure contract/role* |
| Can we instantiate abstract class? | **NO** — that's the whole point |

---

## 🧠 One-Line Mental Model

```
extends   →  who you ARE       (max 1)
implements →  roles you PLAY   (unlimited)
```

---

*Head First Java · Chapter 8 condensed*