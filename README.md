Final project
---

During training on the Skill Factory course, namely the Java Core module, 
the final project was issued for advanced training and improve my skills.  
In the course of work, such libraries as java.util.* were studied and all his descendants. 
During the implementation of the program, the skills of understanding the principles of OOP, collections, 
input/output, exception handling, interface and generics were improved.

## What was in the project been using?

The project used a service method to add a client to the card 
and the main method of the client who authorized the account. 
After successful authorization, the user can log into his account and perform 3 operations through the ATM.

<ul>
    <li>
        1. Show your balance.
    </li>
    <li>
        2. Top up your balance.
    </li>
    <li>
        3. Transfer money to any customer who exists into the Map.
    </li>
    <li>
        4. Exit the program.
    </li>
</ul>

## What data structures were used in the project?

The most priority for the task was to use HashMap. 
HashMap is a good option because every requisite as a key, and every bank account is a value. 
Thus, the two concepts of key-value are interrelated.

Example of real life:
> In modern life, there are never the same requisites.

The advantage of this library is that you can easily get a specific user account 
in any of the methods of the BankService class, the fields of which have a specific hash code 
in the table of sets. And in order to set the desired value or find out whether such an account 
exists in the system, you do not need to iterate over all the values.
 
##Design patterns.

The code uses design patterns:

<ul>
    <li>
        <a href="https://refactoring.guru/ru/design-patterns/strategy" target="_blank">Strategy</a> - <i>interface Action and it implement</i>.
    </li>
    <li>
        <a href="https://refactoring.guru/ru/design-patterns/facade" target="_blank">Facade</a> (simple implementation) - <i>class BankService</i>.
    </li>
</ul>

Below is UML diagram<br><br>

![ScreenShot](/UML_shema.PNG)
