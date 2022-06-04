# Iteration 2 Worksheet

## Paying off technical debt
###### Instance 1: Violated Single responsibility principle.
1. Paying it of by moving the logic layer functionality from presentation to business.
2. Commit link can be found [here](https://code.cs.umanitoba.ca/winter-2022-a01/group-4/team-flight-4/-/commit/02779ec4b3fcb67b4e1d91d00350a9b99cca11f4).
3. This would be classified under *deliberate prudent* because we wanted to ship iteration 1 before deadline and we would solve this in iteration 2.
###### Instance 2:
1. Moved the test files from androidTest to Test directory.
2. Commit link can be found [here](https://code.cs.umanitoba.ca/winter-2022-a01/group-4/team-flight-4/-/commit/4eae7e0e3f13232b739e5e5bbf92d55f626ef843).
3. This would be classified under *inadverent prudent* because now we know how it should be done from iteration 1 feedback.


## SOLID

In the project of group 3, The function addDetails violates Single Responsibility Principle, because it has logic layer functionality in presentation folder and no business directory has been implemented. Possible solution: Create a Business Directory and move all the logic functionality java files seperating Logic and UI. Here is the [issue](https://code.cs.umanitoba.ca/winter-2022-a01/group-3/my-awesome-project-a01-3/-/issues/55).

## Retrospective

Retrospective make us work on our codes more efficiently. We use **peer review** and **testing** to review our codes.

In peer review, we try to explain the function of the code to other group members and discuss if there is a better solution. Also, when we find some bugs but cannot relsove them, we try to ask other members to review and explain the codes to help us find and fix bugs.

In testing, one example is that when we run one of the test, we found a little typo of the string "Round trip", that "t" should be captical.

## Design patterns
##### Singleton 
Used Singleton pattern in [service](https://code.cs.umanitoba.ca/winter-2022-a01/group-4/team-flight-4/-/blob/development/flight/app/src/main/java/com/flight/application/Services.java) class.


##### Adapter

We use Adpater in these 2 activity for show search flight list data in [RecFlightsAdapter](https://code.cs.umanitoba.ca/winter-2022-a01/group-4/team-flight-4/-/blob/development/flight/app/src/main/java/com/flight/presentation/RecFlightsAdapter.java) and [MainActivity](https://code.cs.umanitoba.ca/winter-2022-a01/group-4/team-flight-4/-/blob/development/flight/app/src/main/java/com/flight/presentation/MainActivity.java).



##### Builder

We use builder here to build a date picker in [MainActivity](https://code.cs.umanitoba.ca/winter-2022-a01/group-4/team-flight-4/-/blob/development/flight/app/src/main/java/com/flight/presentation/MainActivity.java).



## Iteration 1 Feedback fixes

Refactored **Instance 2** for **Paying off technical debt** refrenced to section above.
