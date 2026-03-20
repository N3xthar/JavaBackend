# Java OOP - Interview & Revision Notes
> Covers: Classes · Abstract Classes · Interfaces · Polymorphism · Inheritance · Backend-relevant OOP

---

## 1. The 4 Pillars of OOP (Quick Recap)

| Pillar | One-liner |
|---|---|
| **Encapsulation** | Hide internal state, expose only via methods |
| **Inheritance** | Child reuses parent's code via `extends` |
| **Polymorphism** | One interface, many implementations |
| **Abstraction** | Hide complexity, show only what's needed |

---

## 2. Class vs Abstract Class vs Interface — The Decision Tree

```
Do you need to CREATE objects directly?
├── YES → concrete class
└── NO
    ├── Is it a TEMPLATE with some shared code?
    │   └── abstract class
    └── Is it a ROLE/CONTRACT any class can play?
        └── interface
```

### When to use what — the rules

| Use | When |
|---|---|
| **class** | No IS-A relationship with anything else |
| **subclass (extends)** | Need a more specific version + override behavior |
| **abstract class** | Template for a group; some shared code; nobody should instantiate it directly |
| **interface** | Define a role that ANY class (from any part of the tree) can play |

---

## 3. Abstract Classes

### Theory
- Cannot be instantiated — `new Animal()` → compile error if `Animal` is abstract
- Can have BOTH abstract methods (no body) and concrete methods (with body)
- If a class has **even one** abstract method → class MUST be abstract
- Abstract method ends with semicolon, no curly braces
- First concrete subclass in the hierarchy **must** implement ALL abstract methods

### Syntax
```java
abstract class Shape {
    String color;  // field

    // concrete method — subclasses get this for free
    void setColor(String c) {
        this.color = c;
    }

    // abstract method — subclass MUST implement
    abstract double area();
}

class Circle extends Shape {
    double radius;

    Circle(double r) { this.radius = r; }

    @Override
    double area() {
        return Math.PI * radius * radius;
    }
}
```

### Interview Q: Can an abstract class have a constructor?
**Yes.** Even though you can't do `new AbstractClass()`, the constructor is called by the subclass via `super()`.

---

## 4. Interfaces

### Theory
- Defines a **contract / role** — not identity
- A class CAN implement multiple interfaces (solves multiple inheritance problem)
- All methods are `public abstract` by default (before Java 8)
- All fields are `public static final` by default
- Java 8+: can have `default` and `static` methods
- Java 9+: can have `private` methods

### Syntax
```java
interface Flyable {
    void fly();  // implicitly public abstract
}

interface Swimmable {
    void swim();
}

class Duck extends Bird implements Flyable, Swimmable {
    public void fly()  { System.out.println("Duck flying"); }
    public void swim() { System.out.println("Duck swimming"); }
}
```

### Java 8+ — default & static methods
```java
interface Logger {
    void log(String msg);  // abstract

    default void logInfo(String msg) {   // default — classes get this free
        log("[INFO] " + msg);
    }

    static Logger consoleLogger() {      // static factory — called on interface
        return msg -> System.out.println(msg);
    }
}
```

### Interview Q: Interface vs Abstract Class?

| | Abstract Class | Interface |
|---|---|---|
| Instantiate | No | No |
| Constructor | Yes | No |
| Fields | Any type | `public static final` only |
| Methods | Abstract + concrete | Abstract + default + static |
| Extends/Implements | `extends` one | `implements` many |
| IS-A vs CAN-DO | IS-A relationship | CAN-DO role |
| State | Can hold state | Cannot hold instance state |

---

## 5. Inheritance — `extends`

### Single Inheritance Only (Java rule)
Java allows **only one superclass**. Why? — The **Deadly Diamond of Death**.

```
        Animal
       /      \
    Cat        Dog
       \      /
        ???         <- which eat() to call?
```

Java sidesteps this by allowing only one parent class. Interfaces are fine for multiple because they historically had no implementation (pre-Java 8).

### The `super` keyword
```java
abstract class Report {
    void runReport() {
        System.out.println("Setting up report...");
    }
}

class BuzzwordReport extends Report {
    @Override
    void runReport() {
        super.runReport();              // call parent's version first
        addBuzzwords();                 // then add subclass-specific stuff
        System.out.println("Done");
    }

    void addBuzzwords() {
        System.out.println("Synergizing the pipeline...");
    }
}
```

# Method Resolution Order (MRO) in Java

## 🔹 Definition
Method Resolution Order (MRO) describes how Java determines **which method implementation to execute** when a method is called on an object.

---

## 🔹 Core Idea
Java follows **Runtime Polymorphism (Dynamic Method Dispatch)**:

> The method that gets executed depends on the **actual object (runtime type)**, not the reference type.

---

## 🔹 Complete Resolution Process

### Step 1: Compile-Time Check (Reference Type)
- The compiler checks whether the method exists in the **reference type (left side)**.
- If the method is not present → ❌ **Compilation Error**

---

### Step 2: Runtime Check (Actual Object)
- JVM looks at the **actual object (right side)** created using `new`.

---

### Step 3: Method Lookup
- Check if the method exists in the **object's class**
  - If found →  execute it
  - If not found →  move to parent class

---

### Step 4: Walk Up the Hierarchy
- Continue checking in parent classes until:
  - Method is found → execute
  - Or top (`Object`) is reached

---

## 🔹 Example

```java
class A {
    void show() { System.out.println("A"); }
}

class B extends A {
    void show() { System.out.println("B"); }
}

class C extends B {
    void show() { System.out.println("C"); }
}

A obj = new C();
obj.show();

```java
Report r = new BuzzwordReport();   // reference type = Report
r.runReport();   // calls BuzzwordReport's runReport() <- polymorphism!
```

---

## 6. Polymorphism — Deep Dive

### Compile-time (Static) — Method Overloading
```java
class Calculator {
    int add(int a, int b)            { return a + b; }
    double add(double a, double b)   { return a + b; }   // different param types
    int add(int a, int b, int c)     { return a + b + c; } // different param count
}
```

### Runtime (Dynamic) — Method Overriding
```java
class Animal {
    void sound() { System.out.println("Some sound"); }
}

class Dog extends Animal {
    @Override
    void sound() { System.out.println("Woof"); }
}

class Cat extends Animal {
    @Override
    void sound() { System.out.println("Meow"); }
}

// Polymorphic array — reference type Animal, actual type varies
Animal[] animals = { new Dog(), new Cat(), new Dog() };
for (Animal a : animals) {
    a.sound();   // Woof, Meow, Woof — decided at RUNTIME
}
```

### Reference type vs Object type — critical interview concept
```java
Animal a = new Dog();   // reference type = Animal, object type = Dog

a.sound();       // OK — calls Dog's sound() (runtime polymorphism)
a.fetch();       // COMPILE ERROR — fetch() not in Animal reference type

// Cast to access subclass-specific methods
Dog d = (Dog) a;
d.fetch();       // OK now
```

**Rule:** You can only call methods that exist in the **reference type**. The actual implementation called depends on the **object type**.

---

## 7. Object class — Root of All Java

Explicitly = you write it yourself

Implicitly = Java (or system) does it for you

Every class implicitly extends `Object`. Key methods:

| Method | Purpose | Override? |
|---|---|---|
| `toString()` | String representation | Yes, always |
| `equals(Object o)` | Logical equality | Yes |
| `hashCode()` | Hash for HashMap/HashSet | Yes (with equals) |
| `getClass()` | Runtime class info | No |
| `clone()` | Shallow copy | Yes (with Cloneable) |

```java
class Person {
    String name;
    int age;

    @Override
    public String toString() {
        return "Person{name=" + name + ", age=" + age + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person p = (Person) o;
        return age == p.age && name.equals(p.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
```

### Interview Q: Why override hashCode when you override equals?
If `a.equals(b)` is true then `a.hashCode()` MUST equal `b.hashCode()`.
HashMap uses hashCode to find the bucket, then equals to find the key.
Breaking this contract causes objects to disappear from HashMaps.

---

## 8. Casting & instanceof

```java
Object obj = "Hello";

// Check before cast — safe pattern
if (obj instanceof String) {
    String s = (String) obj;
    System.out.println(s.length());
}

// Java 16+ — pattern matching instanceof
if (obj instanceof String s) {
    System.out.println(s.length());  // s auto-cast
}

// ClassCastException at runtime if incompatible
Dog d = (Dog) new Cat();   // BOOM at runtime
```

---

## 9. Generics — Why `ArrayList<Dog>` works

```java
// Without generics — risky
ArrayList list = new ArrayList();
list.add(new Dog());
Dog d = (Dog) list.get(0);   // manual cast, can fail at runtime

// With generics — safe
ArrayList<Dog> dogs = new ArrayList<>();
dogs.add(new Dog());
Dog d = dogs.get(0);   // compiler inserts cast — guaranteed safe
// dogs.add(new Cat());   // COMPILE ERROR — caught early
```

**Compiler inserts casts for you.** `ArrayList<Dog>` is a signal to the compiler, not a special class. Errors at compile time are far better than runtime surprises.

---

## 10. Backend / Spring Boot — OOP in Practice

### Interfaces for Service layer
```java
// Define the contract
public interface UserService {
    User findById(Long id);
    User save(User user);
    void delete(Long id);
}

// Implementation — can be swapped (mock for tests, real for prod)
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User save(User user) { return userRepository.save(user); }

    @Override
    public void delete(Long id) { userRepository.deleteById(id); }
}
```

### Abstract class for base entity
```java
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}

@Entity
public class User extends BaseEntity {
    private String name;
    private String email;
}

@Entity
public class Product extends BaseEntity {
    private String title;
    private Double price;
}
```

### Polymorphism — Strategy Pattern (very common interview topic)
```java
// Interface
public interface PaymentStrategy {
    void pay(double amount);
}

// Implementations
public class CreditCardPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Charging Rs." + amount + " to credit card");
    }
}

public class UpiPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Sending Rs." + amount + " via UPI");
    }
}

// Context — doesn't care which strategy, just calls pay()
public class OrderService {
    private PaymentStrategy paymentStrategy;

    public OrderService(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }

    public void checkout(double amount) {
        paymentStrategy.pay(amount);  // runtime polymorphism
    }
}
```

### Encapsulation — DTO pattern
```java
// Entity — internal representation
@Entity
public class User {
    private Long id;
    private String passwordHash;  // never expose this
    private String email;
}

// DTO — what you expose via API (encapsulation at API boundary)
public class UserResponseDTO {
    private Long id;
    private String email;
    // no passwordHash!
}
```

---

## 11. SOLID Principles (asked in every interview)

| Principle | Meaning | OOP Link |
|---|---|---|
| **S** ingle Responsibility | One class, one reason to change | class design |
| **O** pen/Closed | Open for extension, closed for modification | inheritance / interfaces |
| **L** iskov Substitution | Subclass must be usable wherever parent is used | polymorphism |
| **I** nterface Segregation | Many small interfaces > one fat interface | interfaces |
| **D** ependency Inversion | Write code that depends on interfaces / abstract classes ❌ Not on specific concrete classes    | interfaces | 
           

```java
// Dependency Inversion — depend on interface, not impl
// BAD
class OrderService {
    MySQLDatabase db = new MySQLDatabase();  // tightly coupled
}

// GOOD
class OrderService {
    Database db;  // interface
    OrderService(Database db) { this.db = db; }  // injected
}
```

---

## 12. Common Interview Questions & Answers

**Q: Can an interface extend another interface?**
Yes. `interface B extends A { }`. A class implementing B must implement methods from both.

**Q: Can we have a constructor in an interface?**
No. Interfaces cannot be instantiated and have no instance state.

**Q: What is a functional interface?**
An interface with exactly one abstract method. Used with lambdas.
```java
@FunctionalInterface
interface Validator<T> {
    boolean validate(T t);
}
Validator<String> notEmpty = s -> !s.isEmpty();
```

**Q: Difference between overloading and overriding?**

| | Overloading | Overriding |
|---|---|---|
| Where | Same class | Subclass |
| Signature | Different | Same |
| Return type | Can differ | Must be same (or covariant) |
| Resolved | Compile time | Runtime |
| @Override | No | Yes |

**Q: Can we override a static method?**
No. Static methods belong to the class, not the object. You can *hide* them (method hiding), not override. Polymorphism does not apply to static methods.

**Q: abstract class with all abstract methods vs interface?**
Prefer interface. Use abstract class only when you need constructors, instance fields, or shared non-abstract methods.

**Q: What happens if two interfaces have the same default method?**
```java
interface A { default void hello() { System.out.println("A"); } }
interface B { default void hello() { System.out.println("B"); } }

class C implements A, B {
    public void hello() {       // MUST override to resolve conflict
        A.super.hello();        // choose explicitly
    }
}
```

**Q: Can abstract class implement interface?**
Yes, and it does NOT have to implement all interface methods — the first concrete subclass must.

**Q: What is covariant return type?**
An overriding method can return a subtype of the original return type.
```java
class Animal { Animal create() { return new Animal(); } }
class Dog extends Animal { 
    @Override
    Dog create() { return new Dog(); }  // Dog is subtype of Animal — valid
}
```

---

## 13. Quick Syntax Reference

```java
// Class
public class Dog extends Animal implements Pet, Trainable { }

// Abstract class
public abstract class Vehicle {
    abstract void start();
    void stop() { System.out.println("Stopping"); }
}

// Interface
public interface Serializable { }
public interface Comparable<T> { int compareTo(T other); }

// Calling super method
@Override
void runReport() {
    super.runReport();  // run parent's version, then extend
    doExtraStuff();
}

// Safe cast with pattern matching (Java 16+)
if (obj instanceof Dog d) {
    d.fetch();
}

// Functional interface + lambda
Runnable r = () -> System.out.println("Running");
```

---

## 14. Cheat Sheet — One-liners for Interview

- **`extends`** = IS-A, single parent only, inherit code
- **`implements`** = CAN-DO role, multiple allowed, must fulfill contract
- **abstract class** = template with partial implementation, no instantiation
- **interface** = pure contract (pre-Java 8), roles across unrelated classes
- **polymorphism** = reference type decides what you *can call*, object type decides *what runs*
- **`super`** = invoke parent's version of an overridden method
- **Deadly Diamond of Death** = why Java has single class inheritance
- **Generics** = compiler adds casts for you, errors at compile time > runtime
- **Always override `hashCode` with `equals`** — HashMap contract
- **SOLID** = five OOP design principles every interviewer expects you to know
- **Strategy pattern** = swap implementations via interface reference — classic polymorphism use case
- **DTO pattern** = encapsulate what you expose at the API boundary