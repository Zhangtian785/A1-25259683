# Submission notes

## 1. Short video script — four pillars of OOP (max 5 minutes)

Record your own walkthrough. For each pillar: name it, explain it in your own
words, point to the code location, and explain why you used it there.

### Introduction (~30 seconds)
- This is the clinic appointment system. The `AssignmentOne` class runs the
  demonstration; professionals are in the `HealthProfessional` family,
  appointments are in `Appointment` and `AppointmentManager`.

### 1. Encapsulation (~1 minute)
- Concept: bundle data and the behaviour that works on it together, and hide
  the internal state so it can only be changed through controlled paths.
- Code: `HealthProfessional` keeps `id`, `name`, `daysAvailable` private,
  exposes them through getters, validates values in the constructor, and returns
  a defensive copy of the day list. `Patient` does the same for name and mobile.
- Why: external code can read the details but cannot put an object into an
  invalid state (for example a blank name or a non-numeric ID).

### 2. Inheritance (~1 minute)
- Concept: a subclass reuses the fields and methods of a superclass and adds its
  own specialised data and behaviour, instead of duplicating shared code.
- Code: `GeneralPractitioner`, `Specialist`, `Nurse`, `Dietitian` all extend
  `HealthProfessional`. They call `super(...)` to set up the shared information,
  then add their own field (prescribing authority, specialty, blood-sample
  authority, dietary focus).
- Why: the common professional information is written once in the base class;
  each subtype only describes what makes it different.

### 3. Polymorphism (~1 minute)
- Concept: objects of different subtypes can be treated through a common type,
  and each responds to the same message in its own way.
- Code: in `AssignmentOne`, a `List<HealthProfessional>` holds a mix of GPs,
  specialists, nurses and dietitians; the loop calls `toString()` and each object
  prints its own type-specific details. An `Appointment` also references the
  generic `HealthProfessional`, so it can book any subtype.
- Why: adding another professional type later does not require changing the
  list-processing code; the same loop works for the new subtype.

### 4. Abstraction (~1 minute)
- Concept: expose only the essential, high-level behaviour and hide the
  implementation detail; force subclasses to provide certain behaviour.
- Code: `HealthProfessional` is `abstract`, so a plain unspecialised
  professional cannot be created. The abstract method `roleDescription()` forces
  every subtype to supply its own role label. `AppointmentManager` hides the
  internal list behind simple operations: add, filter, sort, cancel.
- Why: the design cannot be misused by creating an incomplete generic
  professional, and callers of the manager do not need to know how the
  collection is stored.

### Closing (~20 seconds)
- `Appointment` implements `Comparable` so appointments sort earliest to
  latest; `AppointmentManager` rejects double-booking and out-of-schedule slots
  and returns a warning when an action cannot be performed.

## 2. GenAI declaration — choose the version that matches what you actually did

### If you DID use GenAI

> I acknowledge that I have used Generative AI tools to complete this
> assessment in accordance with the conditions outlined in the Assessment
> Brief. I used [insert name of GenAI tool(s)] to [briefly describe how the
> tool(s) were used, e.g. generate ideas, check grammar, organise structure].

### If you DID NOT use GenAI

> I acknowledge that I have not knowingly used GenAI to complete this
> assessment.

Note: the brief permits GenAI for researching ideas, assisting with learning
and debugging only. It does not permit GenAI to write the solution code, the
code comments, or the explanation video. Be prepared to explain your own code
if asked by the unit assessor.

## 3. Other submission items

- Project zip: rename `A1-username` to your MySCU username and zip it as
  `A1-username.zip`.
- GitHub: push all files to a repository; if private, invite the tutor and unit
  assessor as collaborators, and paste the link in the comments.
- WakaTime: attach the report/screenshot as `A1-username-WakaTime.png` (or
  `.jpg` / `.pdf`).
- Video: upload to SCU OneDrive, create a shareable link, and paste it in the
  comments.
