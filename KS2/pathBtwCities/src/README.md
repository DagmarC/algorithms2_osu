# PathBetweenCities

## 📖 Overview

**PathBetweenCities** loads city structures from a JSON source, constructs graph, and calculates the shortest path between any two cities using the Breadth-First Search (BFS) algorithm.

## 🚀 Features

* **JSON loader:** Loads json file into the List
* **Graph Creation:** Creates graph structure from already parsed cities from json file
* **Graph Traversal:** Finds the shortest path between cities using BFS (Breadth-First Search).
Note that default city is used if "Y/y" is typed, oherwise the user is prompted to type hos own choice.
* **Error Handling:** Tries to handle all invalid cases like "file is missing, wrong format, invalid number of cities, null pointer exceptions, ..."
* **userChoice:** method (with other methods) responsible for UI and user prompting

---

## 🏗 Architecture & Design Principles

The project follows the **Single Responsibility Principle (SRP)**. Each class has a distinct role:

| Class | Responsibility                                                                                                |
| :--- |:--------------------------------------------------------------------------------------------------------------|
| **`CityDataLoader`** | Handles I/O operations and parses raw JSON into Java objects using the Jackson library.                       |
| **`Cities`** | Acts as a Read-Only Repository. It provides safe search methods (`findByName`, `findByUUID`) and data access. |
| **`CitiesGraph`** | Encapsulates the business logic. It manages the graph data structure (Nodes) and executes the BFS algorithm.  |
| **`City`** | A pure Data Transfer Object (DTO) implemented as a Java Record to ensure immutability.                        |
| **`Main`** | Handles bootstrapping, file verification, and the UI loop.                                                    |

---

## 🛠 Technology Stack

### 1. Jackson Library
`com.fasterxml.jackson` for JSON processing.
* It maps JSON fields to Java objects using annotations like `@JsonProperty`, - it eliminates manual parsing errors.
* It solves Java's Type Erasure problem using `TypeReference<List<City>>` to correctly deserialize lists of objects.

### 2. Java Records
The `City` object is implemented as a `record` (introduced in Java 14/16).
* City is a DTO (Data Transfer Object). 
* All fields are automatically private and final.
* Instead of calling city.getName() use city.name().
* It reduces boilerplate code (automatic generation of `equals`, `hashCode`, `toString`, and getters).

---

## 🧠 Algorithmic Approach

### Graph Construction
The app converts the list of loaded cities into the Tree structure graph.
* It uses a `HashMap<UUID, Node>` for O(1) constant-time access to any node.
* Recursion is triggered only from **Root Nodes**. I used  `this.rootNodes = new HashSet<>()` in case it is not Tree bud the forest (multiple cities are root cities).

### Shortest Path: Breadth-First Search (BFS)
To find the route:
1.  **Queue:** Appends all unvisited neighbours of the current node (parent (if exists) and children)
2.  **Visited Set:** Prevents cycles and infinite loops.
3.  **Parent Map:** Tracks the path. When the destination is reached, it "backtracks" via this map to reconstruct the route.

---
AI help:
The main area, where I use help of ai is the string formatting and usage of Jakson library and dependency injections + RAADME.md (corrections and special fonts). The choice of the algorithm and tree structure was mine.
---

## 💻 Getting Started

### Prerequisites
* Java Development Kit (JDK) 17 or higher.
* Maven or Gradle (to manage the Jackson dependency).

### Dependencies (Maven)
Add the Jackson Core/Databind dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.15.0</version>
</dependency>