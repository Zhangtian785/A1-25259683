# A1-username — PROG2004 Object Oriented Programming, Assessment 1

A simple appointment system for a health service, written in Java. The system
models health professionals and their subtypes, links patients to professionals
at booked time slots, and manages the day's appointments (add, filter, sort,
cancel). There is no user interface; the `main` method runs a scripted
simulation that prints results to the console.

## How to compile and run

From the project root:

```
javac -d out src/assignmentone/*.java
java -cp out assignmentone.AssignmentOne
```

## Source files (`src/assignmentone/`)

| File | Role |
|------|------|
| `AssignmentOne.java` | Contains the `main` method; Parts 3 and 6 demonstration. |
| `HealthProfessional.java` | Abstract base class for every professional (Part 1). |
| `GeneralPractitioner.java` | GP subtype (Part 2). |
| `Specialist.java` | Specialist subtype with a named specialty (Part 2). |
| `Nurse.java` | Nurse subtype (Part 2). |
| `Dietitian.java` | Dietitian subtype (Part 2). |
| `Patient.java` | Basic patient details: name and mobile number (Part 4). |
| `Appointment.java` | Links a patient, a professional and a time slot; comparable by time (Part 4). |
| `AppointmentManager.java` | Stores and manages the appointment collection (Part 5). |

## Class relationships

- `HealthProfessional` (abstract) is the superclass of
  `GeneralPractitioner`, `Specialist`, `Nurse`, `Dietitian`.
- `Appointment` holds one `Patient` and one `HealthProfessional` (so it works
  with any subtype).
- `AppointmentManager` holds a list of `Appointment` objects.

## The four pillars of OOP in this code

| Pillar | Where it appears |
|--------|------------------|
| Encapsulation | Private fields with getters; validation in constructors; defensive copies of the day list; internal appointment list not exposed directly. |
| Inheritance | The four professional subtypes extend `HealthProfessional` and reuse its fields and behaviour, calling `super(...)` to set up shared state. |
| Polymorphism | Professionals are stored in one `List<HealthProfessional>` and each responds to `toString()` / `roleDescription()` in its own way; an `Appointment` references the generic professional type. |
| Abstraction | `HealthProfessional` is abstract and cannot be instantiated directly; the abstract `roleDescription()` forces each subtype to define its role; the manager hides how appointments are stored behind simple operations. |

## Daily schedule

The clinic runs 08:00–16:00 in 30-minute slots. The schedule is defined once
as a constant in `AppointmentManager`, so changing it only affects that class.

