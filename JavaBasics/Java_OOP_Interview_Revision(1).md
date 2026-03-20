# ☕ Java OOP — Interview & Coding Revision Guide

> Based on *Head First Java* — Chapters 1, 2, 4 & 6  
> 🎯 Focus: Java Basics, OOP Concepts, Encapsulation, Variables, Equality, ArrayList, Boolean Expressions, Game Design Patterns, Interview Q&A

---

## 📌 Table of Contents

- [1. Java Basics — Syntax & Control Flow](#1-java-basics--syntax--control-flow)
- [2. OOP Core Concepts](#2-oop-core-concepts)
- [3. Classes vs Objects](#3-classes-vs-objects)
- [4. Inheritance](#4-inheritance)
- [5. Method Overriding](#5-method-overriding)
- [6. Encapsulation — private, getters & setters](#6-encapsulation--private-getters--setters)
- [7. Instance Variables vs Local Variables](#7-instance-variables-vs-local-variables)
- [8. Default Values of Instance Variables](#8-default-values-of-instance-variables)
- [9. Objects in Arrays](#9-objects-in-arrays)
- [10. == vs .equals() — Comparing Primitives & Objects](#10--vs-equals--comparing-primitives--objects)
- [11. Pass by Value](#11-pass-by-value)
- [12. Method Arguments & Return Types](#12-method-arguments--return-types)
- [13. Common Code Tracing Exercises](#13-common-code-tracing-exercises)
- [14. Interview Q&A Cheatsheet](#14-interview-qa-cheatsheet)
- [15. Extra Practice Questions with Solutions](#15-extra-practice-questions-with-solutions)
- [16. Common Mistakes to Avoid](#16-common-mistakes-to-avoid)
- [17. ArrayList — Dynamic Lists](#17-arraylist--dynamic-lists)
- [18. for-each Loop](#18-for-each-loop)
- [19. Boolean Expressions — &&, ||, !, Short-Circuit](#19-boolean-expressions----short-circuit)
- [20. Game Design — StartupBust Case Study](#20-game-design--startupbust-case-study)
- [21. Prep Code → Real Code Methodology](#21-prep-code--real-code-methodology)
- [22. Chapter 6 Interview Q&A](#22-chapter-6-interview-qa)

---

## 1. Java Basics — Syntax & Control Flow

### 🔷 Program Structure (Must Know)

```java
public class MyClass {                         // Class declaration
    public static void main(String[] args) {   // Entry point
        // your code here
    }
}
```

> ⚠️ **Interview Trap**: Without `public static void main(String[] args)`, the JVM won't know where to start execution.

---

### 🔷 `while` Loop

```java
int x = 3;
while (x > 0) {       // condition checked BEFORE each iteration
    System.out.println(x);
    x = x - 1;        // ← always modify the variable or you get INFINITE LOOP
}
```

> ⚠️ **Infinite Loop Warning**: If you forget to update the loop variable, the loop runs forever. Classic bug!

---

### 🔷 `if` / `if-else` Statement

```java
if (x > 2) {
    System.out.print("big");
} else if (x == 1) {
    System.out.print("one");
} else {
    System.out.print("other");
}
```

---

### 🔷 `System.out.print` vs `System.out.println`

| Method | Behavior |
|---|---|
| `System.out.print("a")` | Prints **without** newline |
| `System.out.println("a")` | Prints **with** newline at end |

---

### 🔷 Variable Declaration

```java
int x = 5;       // integer
String s = "hi"; // String (object, not primitive)
```

---

## 2. OOP Core Concepts

### 🧠 The Big 4 Pillars (Memorize These!)

| Pillar | One-liner |
|---|---|
| **Encapsulation** | Wrap data + methods into a class; hide internals |
| **Inheritance** | Subclass inherits fields & methods from superclass |
| **Polymorphism** | Same method name, different behavior per class |
| **Abstraction** | Expose only what's necessary, hide complexity |

---

### 🔷 Procedural vs OO Thinking

| Procedural (Laura's Way) | OO (Brad's Way) |
|---|---|
| Ask: *"What procedures do I need?"* | Ask: *"What objects/players are in this program?"* |
| Code organized around **functions** | Code organized around **objects** |
| Hard to extend — touch existing code | Easy to extend — add new class, don't touch old code |
| `rotate(shapeNum)` handles all cases | Each shape's `rotate()` knows its own behavior |

> 💡 **Interview Gold**: "OO design promotes the **Open/Closed Principle** — open for extension, closed for modification."

---

## 3. Classes vs Objects

### 🔷 Class = Blueprint, Object = Instance

```java
// CLASS — the blueprint
class Dog {
    String name;
    int age;

    void bark() {
        System.out.println(name + " says Woof!");
    }
}

// OBJECT — an instance of the class
Dog myDog = new Dog();
myDog.name = "Rex";
myDog.age = 3;
myDog.bark();   // Rex says Woof!
```

> 🎯 **Interview Q**: *"What's the difference between a class and an object?"*  
> **A**: A class is a template/blueprint. An object is a specific instance created from that template using `new`.

---

### 🔷 Attributes vs Methods

| Term | Also called | Example |
|---|---|---|
| **Attribute** | Field / Instance variable | `int xPoint;` |
| **Method** | Function / Procedure (OO term) | `void rotate() {}` |

---

## 4. Inheritance

### 🔷 Concept

```
Shape                  ← Superclass (more abstract)
 ├── Triangle          ← Subclass (inherits from Shape)
 ├── Square
 ├── Circle
 └── Amoeba
```

```java
class Shape {
    void rotate() {
        // default rotation logic
    }
    void playSound() {
        // default sound logic
    }
}

class Triangle extends Shape {
    // inherits rotate() and playSound() from Shape
    // no need to rewrite them!
}
```

> 🎯 **Interview Q**: *"What is inheritance?"*  
> **A**: Inheritance allows a subclass to acquire the fields and methods of a superclass using the `extends` keyword. It promotes **code reuse** and **hierarchical classification**.

---

### 🔷 Superclass vs Subclass

| Term | Meaning |
|---|---|
| **Superclass** | Parent class — more general/abstract |
| **Subclass** | Child class — more specific, inherits from parent |

```java
// "Square inherits from Shape"
class Square extends Shape { }

// "Amoeba inherits from Shape"
class Amoeba extends Shape { }
```

---

### 🔷 Why Inheritance Matters (The Real-World Scenario)

**Without Inheritance (Laura's problem):**
- `rotate()` exists in Triangle, Square, Circle, Amoeba separately
- 4 copies to maintain = 4x the bug surface area

**With Inheritance (Brad's solution):**
- `rotate()` lives only in `Shape`
- All subclasses automatically get it
- Change once → affects all ✅

---

## 5. Method Overriding

### 🔷 Concept

Override = subclass **redefines** an inherited method to change/extend its behavior.

```java
class Shape {
    void rotate() {
        // rotates around center of bounding rectangle
        System.out.println("Default rotation");
    }
}

class Amoeba extends Shape {
    int xPoint;   // extra attribute specific to Amoeba
    int yPoint;

    @Override
    void rotate() {
        // rotates around xPoint, yPoint — NOT the center!
        System.out.println("Amoeba-specific rotation");
    }

    @Override
    void playSound() {
        // plays .mp3 instead of .aif
        System.out.println("Amoeba sound: .mp3");
    }
}
```

> 🎯 **Interview Q**: *"What is method overriding?"*  
> **A**: When a subclass provides a specific implementation of a method already defined in its superclass. The method signature must be **identical**. At runtime, the JVM calls the subclass version — this is **runtime polymorphism**.

---

### 🔷 Override vs Overload — Don't Mix These Up!

| | Overriding | Overloading |
|---|---|---|
| **Where** | Subclass vs Superclass | Same class |
| **Signature** | Must be **same** | Must be **different** |
| **Polymorphism** | Runtime | Compile-time |
| **Annotation** | `@Override` | None needed |

```java
// Overloading — same class, different params
void rotate() { }
void rotate(int degrees) { }
void rotate(int x, int y) { }
```

---

### 🔷 How JVM Decides Which Method to Call

```java
Shape s = new Amoeba();
s.rotate();   // ← JVM calls Amoeba's rotate(), NOT Shape's!
```

> The JVM checks the **actual object type** at runtime, not the reference type. This is **dynamic dispatch**.

---

## 6. Encapsulation — private, getters & setters

### 🔷 What is Encapsulation?

Encapsulation = **hiding data** inside a class and controlling access through methods.  
It protects your data from being set to invalid/garbage values by outside code.

> 🎯 **Interview Q**: *"What is encapsulation?"*  
> **A**: Wrapping instance variables as `private` and exposing controlled access via `public` getter/setter methods. It gives you the power to validate or change internal logic later without breaking external code.

---

### 🔷 The GoodDog Example — Full Encapsulation

```java
class GoodDog {
    private int size;          // ← PRIVATE: no direct access from outside

    public int getSize() {     // ← GETTER: read the value
        return size;
    }

    public void setSize(int s) { // ← SETTER: write the value (can add validation)
        size = s;
    }

    void bark() {
        if (size > 60) {
            System.out.println("Wooof! Wooof!");   // big dog
        } else if (size > 14) {
            System.out.println("Ruff! Ruff!");      // medium dog
        } else {
            System.out.println("Yip! Yip!");        // small dog
        }
    }
}

class GoodDogTestDrive {
    public static void main(String[] args) {
        GoodDog one = new GoodDog();
        one.setSize(70);

        GoodDog two = new GoodDog();
        two.setSize(8);

        System.out.println("Dog one: " + one.getSize());  // 70
        System.out.println("Dog two: " + two.getSize());  // 8

        one.bark();   // Wooof! Wooof!
        two.bark();   // Yip! Yip!
    }
}
```

---

### 🔷 Access Modifiers Cheatsheet

| Modifier | Who can access |
|---|---|
| `private` | Only within the **same class** |
| `public` | **Anyone** from any class |
| `protected` | Same class + subclasses + same package |
| *(default/no modifier)* | Same package only |

---

### 🔷 Getter vs Setter — Purpose

| | Getter | Setter |
|---|---|---|
| **Also called** | Accessor | Mutator |
| **Job** | Returns the value | Sets/updates the value |
| **Has return type** | Yes (e.g., `int`, `String`) | No — always `void` |
| **Takes argument** | No | Yes — the new value |
| **Naming convention** | `getFieldName()` | `setFieldName(value)` |

```java
// Getter pattern
public int getSize() {
    return size;
}

// Setter pattern (can add validation!)
public void setSize(int s) {
    if (s > 0) {       // validation — never set negative size
        size = s;
    }
}
```

> 💡 **Interview Gold**: You can use a getter return value anywhere you'd use that type directly:
> ```java
> int x = 3 + one.getSize();   // valid! getSize() returns int
> ```

---

### 🔷 Why Encapsulation Matters

```
Without encapsulation:          With encapsulation:
dog.size = -5;   ← ALLOWED!    dog.setSize(-5);  ← setter can REJECT it
dog.size = 9999; ← ALLOWED!    dog.setSize(9999); ← setter can REJECT it
```

> Encapsulation = the class is responsible for its own data integrity.

---

## 7. Instance Variables vs Local Variables

### 🔷 The Core Difference

| | Instance Variable | Local Variable |
|---|---|---|
| **Declared** | Inside class, outside methods | Inside a method |
| **Scope** | Entire object lifetime | Only within that method |
| **Default value** | YES — auto-initialized | NO — must initialize manually |
| **Belongs to** | The object | The method call |

---

### 🔷 Instance Variable — lives in the object

```java
class Horse {
    private double height = 15.2;  // instance variable
    private String breed;          // instance variable (gets default null)
}
```

---

### 🔷 Local Variable — lives in the method

```java
class AddThing {
    int a;       // instance variable
    int b = 12;  // instance variable

    public int add() {
        int total = a + b;  // 'total' is a LOCAL variable
        return total;
    }
}
```

---

### 🔷 The Critical Rule — Local Variables MUST be initialized!

```java
class Foo {
    public void go() {
        int x;           // declared but NOT initialized
        int z = x + 3;   // ← COMPILER ERROR! x might not be initialized
    }
}
```

**Compiler error:**
```
Foo.java:4: variable x might not have been initialized
    int z = x + 3;
            ^
```

> ⚠️ **Interview Trap**: Instance variables get defaults. Local variables do NOT. This is a classic trick question.

---

### 🔷 Method Parameters — Special Case of Local Variables

```java
public int calcArea(int height, int width) {
    return height * width;
}
```

- Parameters ARE local variables (declared in the method signature)
- They are **always initialized** — the compiler ensures arguments are passed when calling
- You'll never get "might not be initialized" on a parameter

---

## 8. Default Values of Instance Variables

### 🔷 Memorize This Table!

| Type | Default Value |
|---|---|
| `int`, `short`, `long`, `byte` | `0` |
| `float`, `double` | `0.0` |
| `boolean` | `false` |
| `char` | `'\u0000'` (null char) |
| Any object reference (`String`, etc.) | `null` |

---

### 🔷 PoorDog Example — Uninitialized Instance Variables

```java
class PoorDog {
    private int size;      // no value assigned
    private String name;   // no value assigned

    public int getSize() { return size; }
    public String getName() { return name; }
}

public class PoorDogTestDrive {
    public static void main(String[] args) {
        PoorDog one = new PoorDog();
        System.out.println("Dog size is " + one.getSize());   // 0
        System.out.println("Dog name is " + one.getName());   // null
    }
}
```

**Output:**
```
Dog size is 0
Dog name is null
```

> 💡 `null` means: a reference variable that doesn't point to any object. It's a remote control with no device. Calling a method on `null` causes a `NullPointerException`!

---

## 9. Objects in Arrays

### 🔷 How to Create an Array of Objects

```java
// Step 1: Declare and create the array (holds references, not objects yet)
Dog[] pets = new Dog[7];

// Step 2: Create actual Dog objects and assign to array slots
pets[0] = new Dog();
pets[1] = new Dog();

// Step 3: Use the objects via array index
pets[0].setSize(30);
pets[1].setSize(8);

int x = pets[0].getSize();  // 30
```

---

### 🔷 Visualizing Object Arrays

```
pets[0] ──→ Dog object { size: 30 }
pets[1] ──→ Dog object { size:  8 }
pets[2] ──→ null  (no Dog created yet)
pets[3] ──→ null
...
```

> ⚠️ Until you do `pets[i] = new Dog()`, that slot holds `null`. Accessing it causes `NullPointerException`.

---

### 🔷 Array of Objects — Key Points

| Point | Detail |
|---|---|
| Array creation | Creates slots for references, NOT the objects themselves |
| Object creation | Each `new Dog()` creates the actual object |
| Accessing methods | Use `pets[i].methodName()` |
| Default slot value | `null` until assigned |

---

## 10. == vs .equals() — Comparing Primitives & Objects

### 🔷 The Golden Rule

| Use | When |
|---|---|
| `==` | Compare two **primitives** OR check if two references point to the **same object** |
| `.equals()` | Check if two **different objects** are meaningfully equivalent (same content) |

---

### 🔷 == with Primitives

```java
int a = 3;
byte b = 3;
if (a == b) { }   // TRUE — same bit pattern value
```

> `==` just compares the **bits**. For primitives, bits = the value.

---

### 🔷 == with Object References

```java
Foo a = new Foo();
Foo b = new Foo();
Foo c = a;         // c points to the SAME object as a

if (a == b) { }   // FALSE — different objects on heap
if (a == c) { }   // TRUE  — same object, same reference
if (b == c) { }   // FALSE — b is a different object
```

**Memory picture:**
```
a ──→ [Foo Object #1]
b ──→ [Foo Object #2]
c ──→ [Foo Object #1]   ← same as a!
```

---

### 🔷 .equals() with Strings

```java
String s1 = new String("Fred");
String s2 = new String("Fred");

if (s1 == s2) { }        // FALSE — two different String objects
if (s1.equals(s2)) { }   // TRUE  — same characters "Fred"
```

> 🎯 **Interview Q**: *"What's the difference between == and .equals()?"*  
> **A**: `==` compares memory addresses (references) or raw primitive values. `.equals()` compares the logical/content equality of two objects — what the object **means**, not where it lives in memory.

---

### 🔷 Common Trap — == on Strings

```java
String a = "hello";
String b = "hello";
if (a == b) { }  // might be TRUE due to String pooling — but DON'T rely on this!
                 // ALWAYS use .equals() for Strings
```

> ⚠️ **Never use == to compare Strings.** Always use `.equals()`.

---

## 11. Pass by Value

### 🔷 Java is ALWAYS Pass by Value

When you pass a variable to a method, Java passes a **copy** of the value. The original variable is never changed.

```java
class XCopy {
    public static void main(String[] args) {
        int orig = 42;
        XCopy x = new XCopy();
        int y = x.go(orig);
        System.out.println(orig + " " + y);  // 42 84
    }

    int go(int arg) {
        arg = arg * 2;   // only changes the COPY
        return arg;
    }
}
```

**Output:** `42 84`

> `orig` is still 42! The method got a copy (`arg = 42`), doubled it, returned 84. The original was never touched.

---

### 🔷 Pass by Value — Object References

When you pass an object reference, Java passes a **copy of the reference** (the remote control). Both the original and copy point to the same object — so the object CAN be mutated.

```java
void changeSize(Dog d) {
    d.setSize(100);   // affects the REAL object — both references point to it
}
```

> 🎯 **Interview Q**: *"Is Java pass by value or pass by reference?"*  
> **A**: Java is **always pass by value**. For primitives, the value itself is copied. For objects, the **reference** (memory address) is copied — not the object. So you can modify the object's state through the copied reference, but you can't make the original variable point to a new object.

---

## 12. Method Arguments & Return Types

### 🔷 Legal Method Calls — Rules

```java
int calcArea(int height, int width) {
    return height * width;
}
```

| Call | Legal? | Reason |
|---|---|---|
| `int a = calcArea(7, 12);` | ✅ Yes | Correct types and count |
| `calcArea(c, 15);` where `short c = 7` | ✅ Yes | `short` auto-promoted to `int` |
| `int d = calcArea(57);` | ❌ No | Missing one argument |
| `calcArea(2, 3);` | ✅ Yes | Legal to ignore return value |
| `int f = calcArea(t, 17);` where `long t = 42` | ❌ No | `long` can't narrow to `int` automatically |
| `int g = calcArea();` | ❌ No | No arguments passed |
| `byte h = calcArea(4, 20);` | ❌ No | `int` return can't auto-fit into `byte` |
| `int j = calcArea(2, 3, 5);` | ❌ No | Too many arguments |

---

### 🔷 Type Promotion Rules

```
byte → short → int → long → float → double
```

- **Widening (auto-allowed):** `short` → `int` ✅ (smaller fits into bigger)
- **Narrowing (needs cast):** `long` → `int` ❌ without explicit cast

```java
long t = 42;
int f = calcArea((int)t, 17);  // explicit cast makes it legal
```

---

### 🔷 Return Type Must Match

```java
void getTime() {
    return time;   // ❌ COMPILE ERROR! void means return nothing
}

String getTime() {
    return time;   // ✅ returns a String
}
```

> The `Clock` bug from the exercise: `getTime()` was declared `void` but tried to return `time`. Fix: change `void` to `String`.

---

## 13. Common Code Tracing Exercises

### 🔷 Trace This — What's the Output?

```java
int x = 3;
while (x > 0) {
    if (x > 2) {
        System.out.print("a");
    }
    x = x - 1;
    System.out.print("-");
    if (x == 2) {
        System.out.print("b c");
    }
    if (x == 1) {
        System.out.print("d");
        x = x - 1;
    }
}
```

**Step-by-step trace:**

| Iteration | x (start) | Print | x (end) |
|---|---|---|---|
| 1 | 3 | `a` → `x=2` → `-` → `b c` | 2 |
| 2 | 2 | (x>2? No) → `x=1` → `-` → `d` → `x=0` | 0 |
| Loop ends | 0 | x > 0 is false | — |

**Output:** `a-b c-d`

---

### 🔷 Trace This — DooBee

```java
int x = 1;
while (x < 3) {
    System.out.print("Doo");
    System.out.print("Bee");
    x = x + 1;
}
if (x == 3) {
    System.out.print("Do");
}
```

**Output:** `DooBeeDooBeeDo`

---

### 🔷 Classic Infinite Loop (Interview Warning)

```java
int x = 1;
while (x < 10) {
    // x is never updated here!
    System.out.println("forever");
}
```

> Fix: Add `x = x + 1;` inside the loop.

---

## 14. Interview Q&A Cheatsheet

### ❓ Java Fundamentals

**Q: What is the entry point of a Java program?**  
A: `public static void main(String[] args)` — the JVM looks for this exact signature to start execution.

**Q: Why is `main` declared `static`?**  
A: So the JVM can call it **without creating an object** of the class first.

**Q: Difference between `print` and `println`?**  
A: `print` outputs text on the same line; `println` appends a newline character at the end.

---

### ❓ Encapsulation, Variables & Equality

**Q: What is encapsulation and why do we use it?**  
A: Encapsulation hides instance variables using `private` and exposes controlled access via `public` getters/setters. It protects data integrity — setters can validate before updating, and internal implementation can change without breaking external code.

**Q: What is the difference between a getter and a setter?**  
A: A getter (accessor) returns an instance variable's value; it has a return type and no arguments. A setter (mutator) updates the value; it's `void` and takes an argument. Together they enforce encapsulation.

**Q: What is the difference between an instance variable and a local variable?**  
A: Instance variables are declared in the class body and belong to the object — they get default values automatically. Local variables are declared inside methods and must be explicitly initialized before use, or the code won't compile.

**Q: What are the default values for Java types?**  
A: `int/long/short/byte` → `0`, `float/double` → `0.0`, `boolean` → `false`, object references → `null`.

**Q: What is `null`?**  
A: `null` means a reference variable that doesn't point to any object on the heap. Calling a method on a `null` reference throws a `NullPointerException`.

**Q: What is the difference between == and .equals()?**  
A: `==` compares bits — for primitives it compares values, for references it checks if they point to the same object. `.equals()` compares logical content/meaning of two objects (e.g., two String objects with same characters).

**Q: Is Java pass by value or pass by reference?**  
A: Always **pass by value**. Primitives pass a copy of the value. Object references pass a copy of the reference — so the object's state can be changed inside the method, but the original reference can't be redirected.

**Q: Can you call a method with a `short` where an `int` is expected?**  
A: Yes — Java auto-promotes (widens) smaller types to larger ones. But the reverse (e.g., passing `long` for `int`) requires an explicit cast.

---

## 15. Extra Practice Questions with Solutions

### 🟡 Level 1 — Basics

---

**Q1. What is the output?**

```java
int x = 5;
while (x > 1) {
    x = x - 1;
    if (x < 3) {
        System.out.println("small x");
    }
}
```

<details>
<summary>▶ Show Solution</summary>

**Trace:**

| x (start) | x after decrement | x < 3? | Output |
|---|---|---|---|
| 5 | 4 | No | — |
| 4 | 3 | No | — |
| 3 | 2 | Yes | `small x` |
| 2 | 1 | Yes | `small x` |
| Loop ends (x=1, not > 1) | | | |

**Output:**
```
small x
small x
```
</details>

---

**Q2. Will this compile? If not, why?**

```java
class Foo {
    public void go() {
        int x;
        System.out.println(x);
    }
}
```

<details>
<summary>▶ Show Solution</summary>

❌ **Will NOT compile.**  
`x` is a local variable that was never initialized. Java requires local variables to be assigned a value before use.

**Fix:**
```java
int x = 0;
System.out.println(x);
```
</details>

---

**Q3. What is the output of PoorDog?**

```java
class PoorDog {
    private int size;
    private String name;
    public int getSize() { return size; }
    public String getName() { return name; }
}

PoorDog d = new PoorDog();
System.out.println(d.getSize());
System.out.println(d.getName());
```

<details>
<summary>▶ Show Solution</summary>

**Output:**
```
0
null
```

`size` is an `int` instance variable → default `0`.  
`name` is a `String` reference → default `null`.
</details>

---

### 🟠 Level 2 — Encapsulation & Methods

---

**Q4. What will this print?**

```java
class XCopy {
    public static void main(String[] args) {
        int orig = 42;
        XCopy x = new XCopy();
        int y = x.go(orig);
        System.out.println(orig + " " + y);
    }

    int go(int arg) {
        arg = arg * 2;
        return arg;
    }
}
```

<details>
<summary>▶ Show Solution</summary>

**Output:** `42 84`

`orig` is passed **by value** — a copy (`arg = 42`) is given to `go()`. The method doubles the copy to `84` and returns it. `orig` remains `42`.
</details>

---

**Q5. Fix the Clock class below so it compiles and works correctly:**

```java
class Clock {
    String time;

    void setTime(String t) {
        time = t;
    }

    void getTime() {      // ← BUG HERE
        return time;
    }
}
```

<details>
<summary>▶ Show Solution</summary>

**Bug:** `getTime()` is declared `void` but tries to return a `String`.

**Fix:**
```java
class Clock {
    String time;

    void setTime(String t) {
        time = t;
    }

    String getTime() {    // ← changed void to String
        return time;
    }
}
```

**Test:**
```java
Clock c = new Clock();
c.setTime("1245");
String tod = c.getTime();
System.out.println("time: " + tod);  // time: 1245
```
</details>

---

**Q6. Write a fully encapsulated `BankAccount` class with:**
- `private double balance`
- `deposit(double amount)` — only if amount > 0
- `withdraw(double amount)` — only if amount > 0 and balance is sufficient
- `getBalance()` getter

<details>
<summary>▶ Show Solution</summary>

```java
class BankAccount {
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance = balance + amount;
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance = balance - amount;
        }
    }
}

// Test:
BankAccount acc = new BankAccount();
acc.deposit(1000);
acc.withdraw(300);
System.out.println(acc.getBalance());  // 700.0
acc.withdraw(800);                     // rejected — insufficient funds
System.out.println(acc.getBalance());  // still 700.0
```
</details>

---

### 🔴 Level 3 — OOP, Arrays & Equality

---

**Q7. What is the output?**

```java
Foo a = new Foo();
Foo b = new Foo();
Foo c = a;

System.out.println(a == b);
System.out.println(a == c);
System.out.println(b == c);
```

<details>
<summary>▶ Show Solution</summary>

```
false
true
false
```

`a` and `c` point to the same object → `a == c` is `true`.  
`b` is a separate object → `a == b` is `false`, `b == c` is `false`.
</details>

---

**Q8. What's wrong here and how do you fix it?**

```java
String s1 = new String("hello");
String s2 = new String("hello");
if (s1 == s2) {
    System.out.println("same!");
}
```

<details>
<summary>▶ Show Solution</summary>

**Problem:** `==` on Strings compares references, not content. `s1` and `s2` are two different objects on the heap → `==` returns `false` → nothing prints.

**Fix:**
```java
if (s1.equals(s2)) {
    System.out.println("same!");   // prints "same!"
}
```
</details>

---

**Q9. Create a `Dog[]` array of size 3, set sizes 10, 40, 80 using setSize, then print each dog's bark.**

<details>
<summary>▶ Show Solution</summary>

```java
class GoodDog {
    private int size;
    public void setSize(int s) { size = s; }
    public int getSize() { return size; }

    public void bark() {
        if (size > 60) System.out.println("Wooof! Wooof!");
        else if (size > 14) System.out.println("Ruff! Ruff!");
        else System.out.println("Yip! Yip!");
    }
}

// Main:
GoodDog[] dogs = new GoodDog[3];
dogs[0] = new GoodDog(); dogs[0].setSize(10);
dogs[1] = new GoodDog(); dogs[1].setSize(40);
dogs[2] = new GoodDog(); dogs[2].setSize(80);

for (int i = 0; i < dogs.length; i++) {
    dogs[i].bark();
}
```

**Output:**
```
Yip! Yip!
Ruff! Ruff!
Wooof! Wooof!
```
</details>

---

**Q10. Design challenge — add a `Student` class with:**
- `private String name`, `private int marks`
- Getters, setters (marks must be 0–100)
- Method `getGrade()` returning `"A"` (≥90), `"B"` (≥75), `"C"` (≥60), `"F"` (below 60)

<details>
<summary>▶ Show Solution</summary>

```java
class Student {
    private String name;
    private int marks;

    public String getName() { return name; }
    public void setName(String n) { name = n; }

    public int getMarks() { return marks; }
    public void setMarks(int m) {
        if (m >= 0 && m <= 100) {
            marks = m;
        }
    }

    public String getGrade() {
        if (marks >= 90) return "A";
        else if (marks >= 75) return "B";
        else if (marks >= 60) return "C";
        else return "F";
    }
}

// Test:
Student s = new Student();
s.setName("Ravi");
s.setMarks(82);
System.out.println(s.getName() + " got grade: " + s.getGrade());
// Ravi got grade: B
```
</details>

---



**Q: What is a class?**  
A: A class is a blueprint/template that defines the state (attributes) and behavior (methods) that its objects will have.

**Q: What is an object?**  
A: An object is a specific instance of a class, created using `new`. It has its own copy of instance variables.

**Q: What is inheritance? Give a real example.**  
A: Inheritance lets a subclass reuse code from a superclass. Example: `Amoeba extends Shape` — Amoeba gets `rotate()` and `playSound()` for free, and can override them.

**Q: What is method overriding?**  
A: A subclass redefines an inherited method with the same name and signature to provide specific behavior. Decided at **runtime** by the JVM.

**Q: What is the difference between a superclass and a subclass?**  
A: Superclass is the parent (more abstract, e.g., `Shape`). Subclass is the child that extends it (more specific, e.g., `Triangle`, `Amoeba`).

**Q: What is polymorphism?**  
A: The ability of different objects to respond to the same method call in different ways. Example: calling `rotate()` on a Shape reference that holds an Amoeba object will run Amoeba's rotate, not Shape's.

**Q: Why is OO better for changing requirements?**  
A: You can add new subclasses without modifying existing, tested code. This reduces regression risk. (Open/Closed Principle)

---

### ❓ Common Coding Questions

**Q: Write a while loop that prints 1 to 5.**
```java
int i = 1;
while (i <= 5) {
    System.out.println(i);
    i++;
}
```

**Q: What happens if you forget to update the loop variable?**  
A: Infinite loop — the program never terminates.

**Q: Can a subclass add new methods not in the superclass?**  
A: Yes! A subclass can add its own unique methods (e.g., Amoeba adds `xPoint`, `yPoint` attributes and customizes `rotate()`).

---

## 16. Common Mistakes to Avoid

| ❌ Mistake | ✅ Fix |
|---|---|
| Forgetting class declaration | Every `.java` file needs `public class ClassName {}` |
| Missing `main` method | Need `public static void main(String[] args)` |
| Infinite loop | Always update loop variable inside the loop |
| Confusing `=` and `==` | `=` assigns, `==` compares |
| Overriding with wrong signature | Method name + params must match exactly for override |
| Calling `print` vs `println` wrong | `println` adds newline, `print` doesn't |
| Code outside a method | Executable code must be inside methods, not floating in a class |
| Using `==` to compare Strings | Use `.equals()` for String content comparison |
| Accessing uninitialized local variable | Initialize local variables before use or compiler errors |
| Assuming instance variables need initialization | They auto-get defaults (`0`, `false`, `null`) |
| Accessing null reference | Check for `null` before calling methods on objects |
| Passing `long` where `int` is needed | Explicit cast required: `(int) myLong` |
| Wrong return type (`void` returning a value) | Match return type in method signature to what you return |
| Forgetting `new` when creating array objects | `Dog[] arr = new Dog[5]` only creates slots — add `arr[i] = new Dog()` |
| Accessing array element before assigning an object | Array slots are `null` by default — assign objects first |

---

## 🚀 Quick Revision Summary

```
Java Program = Class + main() method + logic

OOP = Classes + Objects + Inheritance + Overriding + Polymorphism

Class       → Blueprint
Object      → Instance of class (new keyword)
extends     → Inheritance keyword
@Override   → Mark overridden methods
Superclass  → Parent (abstract/general)
Subclass    → Child (specific, inherits from parent)

Encapsulation:
  private   → hide instance variable
  getter    → read value (returns type, no args)
  setter    → write value (void, takes arg)

Variables:
  Instance variable → class level, gets default value
  Local variable    → method level, MUST initialize manually

Default Values:
  int/long  → 0
  double    → 0.0
  boolean   → false
  reference → null

Comparison:
  ==          → same bits (same primitive value OR same object reference)
  .equals()   → same logical content (use for Strings & objects)

Pass by Value:
  Primitives → copy of value passed
  Objects    → copy of reference passed (object state CAN change)

Type Widening (auto):  byte → short → int → long → float → double
Type Narrowing (cast): (int) myLong  ← explicit cast required

Procedural: "What do I need to DO?"
OO:         "What THINGS are in this system?"
```

---

> 💬 **Pro Tip for Interviews**: When asked OOP questions, always give a **real-world analogy** first, then explain the Java syntax. Interviewers love when you connect theory to practice.

---

*Revision guide based on Head First Java — Chapters 1, 2, 4 & 6*

---

## 17. ArrayList — Dynamic Lists

### 🔷 What is ArrayList?

A regular array has a **fixed size** — you declare it with a number and it never grows or shrinks.  
`ArrayList` is a **resizable, dynamic list** from the Java API that grows/shrinks automatically.

```java
import java.util.ArrayList;  // ← must import!

ArrayList<String> myList = new ArrayList<String>();
```

> 🎯 **Interview Q**: *"What's the difference between an array and an ArrayList?"*  
> **A**: An array has a fixed size set at creation. An `ArrayList` is dynamic — it can grow and shrink at runtime. ArrayList also provides built-in methods like `add()`, `remove()`, `size()`, `isEmpty()`, and `indexOf()`.

---

### 🔷 Core ArrayList Methods

| Method | What it does | Example |
|---|---|---|
| `add(element)` | Adds element to end of list | `myList.add("poniez")` |
| `remove(index)` | Removes element at index | `myList.remove(2)` |
| `remove(object)` | Removes first matching object | `myList.remove("poniez")` |
| `size()` | Returns number of elements | `myList.size()` → `3` |
| `isEmpty()` | Returns `true` if list has 0 elements | `myList.isEmpty()` → `false` |
| `indexOf(object)` | Returns index of element, or `-1` if not found | `myList.indexOf("hacqi")` → `1` |
| `get(index)` | Returns element at that index | `myList.get(0)` → `"poniez"` |

---

### 🔷 ArrayList vs Array — Side by Side

```java
// ---- ARRAY (fixed size) ----
String[] fixedArr = new String[3];
fixedArr[0] = "a";
fixedArr[1] = "b";
fixedArr[2] = "c";
// can't add a 4th without creating a new array!

// ---- ARRAYLIST (dynamic) ----
ArrayList<String> dynamicList = new ArrayList<String>();
dynamicList.add("a");
dynamicList.add("b");
dynamicList.add("c");
dynamicList.add("d");    // no problem — it just grows!
dynamicList.remove("b"); // removes "b", shifts remaining items
System.out.println(dynamicList.size()); // 3
```

---

### 🔷 Typed ArrayList — Generics `<Type>`

The `<String>` in `ArrayList<String>` is a **generic type parameter**.  
It tells the compiler: "This list holds only Strings."

```java
ArrayList<String> names = new ArrayList<String>();
names.add("poniez");
names.add("hacqi");
names.add("cabista");

// names.add(42);   // ← COMPILER ERROR — can't add int to String list
```

```java
// ArrayList of custom objects
ArrayList<Startup> startups = new ArrayList<Startup>();
startups.add(new Startup());
```

> ⚠️ **Interview Trap**: If you use a raw `ArrayList` (no type in `<>`), you lose type safety and may get `ClassCastException` at runtime.

---

### 🔷 ArrayList with indexOf() — Hit Detection Pattern

`indexOf()` returns the **position** of an element, or **-1** if not found.  
This is the exact pattern used in the `Startup.checkYourself()` method:

```java
ArrayList<String> locationCells = new ArrayList<String>();
locationCells.add("A2");
locationCells.add("A3");
locationCells.add("A4");

String userGuess = "A3";
int index = locationCells.indexOf(userGuess);  // returns 1

if (index >= 0) {
    locationCells.remove(index);  // hit! remove that cell
    if (locationCells.isEmpty()) {
        result = "kill";   // all cells hit!
    } else {
        result = "hit";
    }
}
```

---

### 🔷 ArrayList isEmpty() — Loop Control Pattern

```java
ArrayList<Startup> startups = new ArrayList<Startup>();
// ... add startups ...

while (!startups.isEmpty()) {         // keep playing while startups exist
    String userGuess = getUserInput();
    checkUserGuess(userGuess);        // may remove a startup from list
}
finishGame();
```

> 💡 `!startups.isEmpty()` is the same as `startups.isEmpty() == false`

---

## 18. for-each Loop

### 🔷 Concept

The `for-each` loop iterates over every element in an array or ArrayList **without** needing an index variable.

```java
// Regular for loop:
for (int i = 0; i < startups.size(); i++) {
    Startup s = startups.get(i);
    s.checkYourself(guess);
}

// for-each loop — cleaner!
for (Startup startup : startups) {
    startup.checkYourself(guess);
}
```

**Read as:** *"For each Startup in startups, do..."*

---

### 🔷 Syntax

```java
for (ElementType variableName : collectionOrArray) {
    // use variableName
}
```

```java
// With ArrayList:
for (Startup startup : startups) {
    // startup = current element each iteration
}

// With array:
String[] names = {"poniez", "hacqi", "cabista"};
for (String name : names) {
    System.out.println(name);
}
```

---

### 🔷 for-each Limitation — Can't Remove During Iteration

```java
// ❌ DANGEROUS — modifying list while iterating causes ConcurrentModificationException
for (Startup s : startups) {
    if (s.isDead()) {
        startups.remove(s);  // ← ERROR at runtime!
    }
}

// ✅ SAFE — break immediately after removing
for (Startup s : startups) {
    result = s.checkYourself(guess);
    if (result.equals("kill")) {
        startups.remove(s);
        break;   // ← exit loop right away, don't continue iterating
    }
}
```

> 🎯 **Interview Q**: *"Why can't you remove elements from an ArrayList while iterating with for-each?"*  
> **A**: Because modifying the list while the iterator is traversing it causes a `ConcurrentModificationException`. The safe approach is to either `break` immediately after removal, or use an `Iterator` with `iterator.remove()`.

---

## 19. Boolean Expressions — &&, ||, !, Short-Circuit

### 🔷 AND Operator — `&&`

Both sides must be `true` for the whole expression to be `true`.

```java
if (price >= 300 && price < 400) {
    camera = "X";
}
```

| Left | Right | Result |
|---|---|---|
| `true` | `true` | `true` |
| `true` | `false` | `false` |
| `false` | anything | `false` (short-circuit!) |

---

### 🔷 OR Operator — `||`

At least one side must be `true`.

```java
if (brand.equals("A") || brand.equals("B")) {
    // applies to brand A or brand B
}
```

| Left | Right | Result |
|---|---|---|
| `true` | anything | `true` (short-circuit!) |
| `false` | `true` | `true` |
| `false` | `false` | `false` |

---

### 🔷 NOT Operator — `!` and `!=`

```java
// != for primitives
if (model != 2000) {
    // everything except model 2000
}

// ! for boolean expressions / objects
if (!brand.equals("X")) {
    // every brand except X
}

// ! on method call
while (!startups.isEmpty()) {
    // while list is NOT empty
}
```

---

### 🔷 Short-Circuit Evaluation ⚡

The JVM is lazy — it stops evaluating as soon as the result is determined.

**`&&` short-circuit:**
```java
// If left side is false → JVM skips right side entirely
if (refVar != null && refVar.isValidType()) {
    // Safe! If refVar is null, the second check never runs
    // → avoids NullPointerException
}
```

**`||` short-circuit:**
```java
// If left side is true → JVM skips right side entirely
if (isAdmin() || hasPermission()) {
    // If isAdmin() returns true, hasPermission() is never called
}
```

> 🎯 **Interview Q**: *"What is short-circuit evaluation?"*  
> **A**: With `&&`, if the left operand is `false`, the right side is never evaluated. With `||`, if the left operand is `true`, the right side is never evaluated. This is both a performance optimization and a safety mechanism (null checks before method calls).

---

### 🔷 Non-Short-Circuit Operators — `&` and `|`

```java
// & and | always evaluate BOTH sides — even if result is already determined
if (isAdmin() & hasPermission()) { }   // hasPermission() always runs

// Rarely used for booleans — mainly for bit manipulation
int flags = 0b1010 & 0b1100;   // bitwise AND → 0b1000
```

| Operator | Short-circuits? | Common use |
|---|---|---|
| `&&` | ✅ Yes | Boolean logic |
| `\|\|` | ✅ Yes | Boolean logic |
| `&` | ❌ No | Bit manipulation |
| `\|` | ❌ No | Bit manipulation |

---

### 🔷 Complex Boolean Expressions

Use **parentheses** to make intent clear — don't rely on operator precedence:

```java
// Zoom camera logic:
if ((zoomType.equals("optical") && (zoomDegree >= 3 && zoomDegree <= 8)) ||
    (zoomType.equals("digital") && (zoomDegree >= 5 && zoomDegree <= 12))) {
    // valid zoom configuration
}
```

> 💡 Always use parentheses with complex boolean expressions. Clarity > cleverness.

---

## 20. Game Design — StartupBust Case Study

### 🔷 Overview

`StartupBust` is a number-guessing game from Chapter 6 that demonstrates:
- **Class design** with multiple collaborating classes
- **ArrayList** for dynamic collections
- **for-each** loops for iteration
- **Method decomposition** — splitting a big job into small focused methods

---

### 🔷 Class Responsibilities

| Class | Role |
|---|---|
| `StartupBust` | Main game controller — sets up, plays, finishes game |
| `Startup` | Represents one enemy startup — holds location, checks guesses |
| `GameHelper` | Utility — gets user input, places startups randomly on grid |

---

### 🔷 StartupBust — Key Instance Variables

```java
public class StartupBust {
    private GameHelper helper = new GameHelper();           // utility object
    private ArrayList<Startup> startups = new ArrayList<Startup>(); // active startups
    private int numOfGuesses = 0;                           // score tracker
}
```

---

### 🔷 Method Breakdown

```
setUpGame()       → create 3 Startup objects, name them, place them on grid
startPlaying()    → loop: get user guess → checkUserGuess() → until all dead
checkUserGuess()  → loop through all Startups, test each, handle hit/miss/kill
finishGame()      → print performance message based on numOfGuesses
main()            → entry point: create game object → setUpGame() → startPlaying()
```

---

### 🔷 The Complete Startup Class

```java
import java.util.ArrayList;

public class Startup {
    private ArrayList<String> locationCells;   // e.g. ["A2", "A3", "A4"]
    private String name;                        // e.g. "poniez"

    public void setLocationCells(ArrayList<String> loc) {
        locationCells = loc;
    }

    public void setName(String n) {
        name = n;
    }

    public String checkYourself(String userInput) {
        String result = "miss";
        int index = locationCells.indexOf(userInput); // -1 if not found

        if (index >= 0) {
            locationCells.remove(index);              // remove this hit cell

            if (locationCells.isEmpty()) {
                result = "kill";                       // all cells hit!
                System.out.println("Ouch! You sunk " + name + " :(");
            } else {
                result = "hit";
            }
        }
        return result;   // "miss", "hit", or "kill"
    }
}
```

---

### 🔷 The Complete StartupBust Class

```java
import java.util.ArrayList;

public class StartupBust {
    private GameHelper helper = new GameHelper();
    private ArrayList<Startup> startups = new ArrayList<Startup>();
    private int numOfGuesses = 0;

    private void setUpGame() {
        Startup one = new Startup();   one.setName("poniez");
        Startup two = new Startup();   two.setName("hacqi");
        Startup three = new Startup(); three.setName("cabista");

        startups.add(one);
        startups.add(two);
        startups.add(three);

        System.out.println("Your goal is to sink three Startups.");
        System.out.println("poniez, hacqi, cabista");
        System.out.println("Try to sink them all in the fewest guesses");

        for (Startup startup : startups) {
            ArrayList<String> newLocation = helper.placeStartup(3);
            startup.setLocationCells(newLocation);   // assign random grid location
        }
    }

    private void startPlaying() {
        while (!startups.isEmpty()) {                         // keep going until all sunk
            String userGuess = helper.getUserInput("Enter a guess");
            checkUserGuess(userGuess);
        }
        finishGame();
    }

    private void checkUserGuess(String userGuess) {
        numOfGuesses++;                     // increment score
        String result = "miss";             // assume miss until proven otherwise

        for (Startup startupToTest : startups) {
            result = startupToTest.checkYourself(userGuess);

            if (result.equals("hit")) {
                break;                      // found a hit — stop checking others
            }
            if (result.equals("kill")) {
                startups.remove(startupToTest);
                break;                      // dead startup removed — stop iterating
            }
        }
        System.out.println(result);         // print "miss", "hit", or "kill"
    }

    private void finishGame() {
        System.out.println("All Startups are dead! Your stock is now worthless.");
        if (numOfGuesses <= 18) {
            System.out.println("It only took you " + numOfGuesses + " guesses.");
            System.out.println("You got out before your options sank.");
        } else {
            System.out.println("Took you long enough. " + numOfGuesses + " guesses.");
            System.out.println("Fish are dancing with your options.");
        }
    }

    public static void main(String[] args) {
        StartupBust game = new StartupBust();
        game.setUpGame();
        game.startPlaying();
    }
}
```

---

### 🔷 Key Design Patterns in StartupBust

**Pattern 1 — Small, focused methods:**
```
One method = one responsibility
setUpGame() only sets up. startPlaying() only loops. checkUserGuess() only checks.
```

**Pattern 2 — Loop + break after list modification:**
```java
for (Startup s : startups) {
    result = s.checkYourself(guess);
    if (result.equals("kill")) {
        startups.remove(s);
        break;   // ← MUST break to avoid ConcurrentModificationException
    }
}
```

**Pattern 3 — Defensive default + override:**
```java
String result = "miss";   // assume worst case (miss) first
// only override if we find a hit or kill
```

---

## 21. Prep Code → Real Code Methodology

### 🔷 What is Prep Code?

Prep code is **pseudocode-like English** that bridges the gap between thinking and coding.  
Head First Java uses it as an intermediate step before writing real Java.

```
// Prep code example for checkUserGuess():
INCREMENT the number of guesses
SET result to "miss"
REPEAT with each Startup:
    EVALUATE the guess
    IF result is "kill", REMOVE Startup from list
DISPLAY the result
```

---

### 🔷 The Three Stages

```
1. PREP CODE   → English description of what each method does
2. TEST CODE   → Write tests before real code (TDD mindset)
3. REAL CODE   → Actual Java implementation
```

> 🎯 **Interview Q**: *"What is TDD (Test-Driven Development)?"*  
> **A**: Writing test code before writing the actual implementation. You define what success looks like first, then write code to make the tests pass. It forces you to think about behavior before internal details.

---

### 🔷 Why Method Decomposition Matters

The game has one big job: *"Play a game."*  
Instead of one 100-line method, it's split into:

| Method | Lines | Single responsibility |
|---|---|---|
| `setUpGame()` | ~20 | Create and place startups |
| `startPlaying()` | ~8 | Main game loop |
| `checkUserGuess()` | ~15 | Evaluate one guess |
| `finishGame()` | ~10 | Show final score |

> **Why?** Smaller methods are easier to test, debug, read, and modify independently.

---

## 22. Chapter 6 Interview Q&A

### ❓ Conceptual Questions

**Q: What is an ArrayList and why use it over a regular array?**  
A: `ArrayList` is a resizable list from `java.util`. Unlike a fixed-size array, it grows and shrinks dynamically. It provides built-in methods like `add()`, `remove()`, `size()`, `isEmpty()`, and `indexOf()`. Use it when you don't know the size upfront or when elements will be added/removed at runtime.

---

**Q: What does `indexOf()` return if the element is not found?**  
A: It returns `-1`. This is the standard sentinel value meaning "not found." Always check `if (index >= 0)` before using the result.

---

**Q: What is the difference between `&&` and `&` in Java?**  
A: `&&` is a short-circuit AND — if the left side is `false`, the right side is never evaluated. `&` is a non-short-circuit AND — both sides always evaluate. `&&` is preferred for boolean logic because it's safer (avoids side effects and NullPointerExceptions) and slightly more efficient.

---

**Q: How do you safely check for null before calling a method?**  
A: Use short-circuit `&&`:
```java
if (refVar != null && refVar.isValidType()) {
    // safe — if null, second expression never runs
}
```

---

**Q: Why is `break` important when removing from an ArrayList inside a for-each loop?**  
A: After calling `list.remove()`, the underlying list structure is modified. If you continue iterating, Java throws a `ConcurrentModificationException`. The `break` exits the loop immediately after removal, preventing this error.

---

**Q: What does `isEmpty()` return on an ArrayList?**  
A: `true` if the list has 0 elements, `false` otherwise. It's equivalent to `list.size() == 0` but more readable.

---

**Q: What is the for-each loop and when can't you use it?**  
A: The for-each loop iterates every element in a collection without an index:
```java
for (Type var : collection) { }
```
You can't use it when you need the index, or when you need to remove elements during iteration (use an `Iterator` or `break` pattern instead).

---

**Q: What is the difference between `||` and `|` in Java?**  
A: `||` short-circuits — if the left side is `true`, the right is skipped. `|` always evaluates both sides. `||` is used for boolean logic; `|` is primarily for bitwise operations on integers.

---

**Q: How does the `checkYourself()` method know if a Startup is sunk?**  
A: It uses `ArrayList.indexOf()` to find if the user's guess matches a stored location cell. If found, it removes that cell. If `locationCells.isEmpty()` is then `true`, all cells have been hit — the Startup is "killed."

---

**Q: Why do we split game logic into multiple small methods instead of one big method?**  
A: Small methods (single responsibility) are easier to test individually, easier to debug when something breaks, easier to read and understand, and easier to change without affecting other parts. This is the **Single Responsibility Principle** in action.

---

**Q: What does `numOfGuesses++` do?**  
A: It increments `numOfGuesses` by 1. It's shorthand for `numOfGuesses = numOfGuesses + 1`. The `++` is the **post-increment** operator.

---

### ❓ Code Tracing — Chapter 6

**Q: What does this print?**

```java
ArrayList<String> list = new ArrayList<String>();
list.add("A");
list.add("B");
list.add("C");
list.remove("B");
System.out.println(list.size());
System.out.println(list.get(1));
```

<details>
<summary>▶ Show Answer</summary>

```
2
C
```

After removing `"B"`, the list is `["A", "C"]`. Size is 2. Index 1 is `"C"`.
</details>

---

**Q: What does this print?**

```java
ArrayList<String> cells = new ArrayList<String>();
cells.add("B2");
cells.add("B3");
cells.add("B4");

String guess = "B3";
int index = cells.indexOf(guess);
if (index >= 0) {
    cells.remove(index);
}
System.out.println(cells.isEmpty());
System.out.println(cells.size());
```

<details>
<summary>▶ Show Answer</summary>

```
false
2
```

`indexOf("B3")` returns 1 (found). After removing index 1, list is `["B2", "B4"]`. Not empty. Size = 2.
</details>

---

**Q: Trace this boolean expression — what does each line print?**

```java
int x = 5;
System.out.println(x > 3 && x < 10);   // ?
System.out.println(x < 3 || x > 4);    // ?
System.out.println(!(x == 5));          // ?
System.out.println(x != 5);             // ?
```

<details>
<summary>▶ Show Answer</summary>

```
true
true
false
false
```

Line 1: `5 > 3` is true AND `5 < 10` is true → `true`  
Line 2: `5 < 3` is false, but `5 > 4` is true → `true`  
Line 3: `5 == 5` is true, NOT true = `false`  
Line 4: `5 != 5` is `false`
</details>

---

**Q: What is wrong with this code and how do you fix it?**

```java
ArrayList<String> items = new ArrayList<String>();
items.add("apple");
items.add("mango");
items.add("banana");

for (String item : items) {
    if (item.equals("mango")) {
        items.remove(item);  // ← problem!
    }
}
```

<details>
<summary>▶ Show Answer</summary>

**Problem:** Removing from the list while iterating with for-each causes `ConcurrentModificationException` at runtime.

**Fix 1 — break immediately:**
```java
for (String item : items) {
    if (item.equals("mango")) {
        items.remove(item);
        break;   // exit loop immediately
    }
}
```

**Fix 2 — use Iterator:**
```java
Iterator<String> it = items.iterator();
while (it.hasNext()) {
    if (it.next().equals("mango")) {
        it.remove();   // safe removal via iterator
    }
}
```
</details>

---

**Q: Design a `Weapon` class with:**
- `private String name`, `private int ammo`
- Getters and setters (ammo must be ≥ 0)
- Method `fire()` that decrements ammo by 1 if ammo > 0, else prints "Out of ammo!"
- Store 3 Weapon objects in an `ArrayList<Weapon>` and fire each once

<details>
<summary>▶ Show Answer</summary>

```java
import java.util.ArrayList;

class Weapon {
    private String name;
    private int ammo;

    public String getName() { return name; }
    public void setName(String n) { name = n; }

    public int getAmmo() { return ammo; }
    public void setAmmo(int a) {
        if (a >= 0) { ammo = a; }
    }

    public void fire() {
        if (ammo > 0) {
            ammo--;
            System.out.println(name + " fired! Ammo left: " + ammo);
        } else {
            System.out.println(name + " is out of ammo!");
        }
    }
}

// Main:
ArrayList<Weapon> arsenal = new ArrayList<Weapon>();

Weapon w1 = new Weapon(); w1.setName("Pistol");  w1.setAmmo(3);
Weapon w2 = new Weapon(); w2.setName("Rifle");   w2.setAmmo(1);
Weapon w3 = new Weapon(); w3.setName("Cannon");  w3.setAmmo(0);

arsenal.add(w1);
arsenal.add(w2);
arsenal.add(w3);

for (Weapon w : arsenal) {
    w.fire();
}
```

**Output:**
```
Pistol fired! Ammo left: 2
Rifle fired! Ammo left: 0
Cannon is out of ammo!
```
</details>

---

### 🔷 Chapter 6 — Quick Revision Summary

```
ArrayList:
  import java.util.ArrayList
  ArrayList<Type> list = new ArrayList<Type>()
  add(e)        → append to end
  remove(index) → remove by position
  remove(obj)   → remove by value
  size()        → count of elements
  isEmpty()     → true if 0 elements
  indexOf(obj)  → position, or -1 if not found
  get(index)    → element at position

for-each:
  for (Type var : collection) { }
  Cannot safely remove during for-each → use break or Iterator

Boolean:
  &&  → AND, short-circuits on false
  ||  → OR, short-circuits on true
  !   → NOT
  !=  → not equal
  &   → AND no short-circuit (bitwise)
  |   → OR no short-circuit (bitwise)

Short-circuit safety pattern:
  if (obj != null && obj.method()) { }

Game Design Pattern:
  One class = one responsibility
  Split big jobs into small methods
  Default result first, override only if condition met
  break after remove() in for-each loop
```
