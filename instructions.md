# Universal Counter Assignment

The purpose of the assignment is to see how well you can create a
program, using Test-Driven Development, that is not only correct, 
but is well-designed, readable, intentional, and modifiable.

The requirements that follow are intentionally minimal. They are no
different from some that you might encounter in a business situation
where the customer, often someone in product marketing, has an idea but
cannot necessarily communicate everything in the detail that developers
would like.

There is a #universal_counter channel on the Discord server for you to ask
questions about and get clarification on the requirements.

## Background

The Mega-Gadget company provides electronic components and gadgets
to hobbyists and companies. The marketing department has determined
the need for counting components that can be embedded in other devices
that count things. Some of these devices simply count one type of thing, like
the number of cars that go through a toll booth. Some, however might be used 
in systems that count multiple types of objects. For example if we wanted to
monitor the number of visits to a website and keep track of the number of 
visits by the country from where the request came, we would need to have a 
separate counter for each country.

You have been given the task of designing a counting component that will
provide counting services for Mega-Gadget's clients. The technical 
specifications are in the next section.

## Technical specifications

We have developed a Java interface file, **IUniversalCounter.java()** that has several methods 
defined. The javadoc comments in the interface provide details of 
each method. Read these carefully.

You will create an implementation of the `IUniversalCounter` interface. This file is
called **UniversalCounter.java**. This name is used to indicate that the class
may be extended by the customer's developers. 

Labels are simply names given to counters that count things.
The client must be able to specify the set of starting labels. Your implementation
must have at least the following constructors:

```
public UniversalCounter() {
  ...
}

public UniversalCounter(String... startingLabels) {
  ...
}
```

Counters may also be decremented in the same manner as incrementing
them. Notice that the arguments for incrementing and decrementing must
be positive integers.

The interface defines the contracts between the client and UniversalCounter.
If any contract is violated, you must throw a CounterException with the
ExceptionReason specified and an appropriate informative message of the
reason for the exception. The client has the responsibility for handling
these exceptions.

You should validate all input. That is, if any input argument to any method is invalid, you should throw
a `CounterException` with a `BAD_VALUE` reason and a message that explains the error.

## The assignment

You must implement the Universal Counter module based upon the starting code and
the technical specification.

You want to make your application
* CORRECT! (This should be obvious)
* Readable
* Intentional
* Modifiable

All of your code will be in the `counter` package and/or sub-packages.
The acceptance tests will only access your code through the constructors described in the 
technical specifications and the `IUniversalCounter` interface.

**NOTE:** You may not add any new modules or libraries to the IntelliJ project. 
The project has JUnit5 in its manifest. You may need to re-add it, however. 

As you develop your code, using TDD, use the TODO.md file to record the tests and the
order in which they were developed. Don't forget to refactor your code as you go.
If you do not know how to use Markdown, there is a good reference at
[Markdown Guide](https://www.markdownguide.org/).

Test files should only be in a separate **test** folder. 

### Deliverables

You must submit a zipped archive with your exported project. If you developed your
solution without using IntelliJ IDEA, then you must submit your **src**, **test**, 
and **TODO.md** file.

## Grading Criteria

You will be graded on the following criteria:

- Correctness. We will run your program using my master tests. Each test will
  have a specific number of points associated with it.
- Your tests and code coverage. We will run your tests. You should achieve 95%
  code coverage using branch coverage. Points will be taken off for each percentage
  point below 95%.
- Your use of TDD. Did you use TDD properly. This is evaluated by examining your 
  **TODO.md** file. The tests should be small, focused, and have been developed in
  a logical manner.
- Readability/understandability. Did you comment each public method? Did you use 
  good names and refactoring?
- Maintainability. Did you separate concerns and have good abstractions (there are
  not that many)?