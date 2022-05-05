# HERO SQUAD
#### A Java application that allows a user to recruit a well-balanced team of superheroes/any character type of their choosing

## Description

The Java application allows users to create heroes with various special powers and weakness and add them to a squad where they can fight for a cause. In addition to this, the user can specify a limit of how many heroes can be added to a squad. The user can also update the data of heroes and squads, delete them, add and delete heroes from their squads. Other features included are HTML5 form validation and sessions for data storage. The application also contains JUnit tests that check that the backend logic is working as expected in various scenarios as listed in the Behavior Driven Development section below. That is, in the development of the application TDD(Test Driven Development) has been used alongside BDD(Behavior Driven Development). 

#### By **[Lynn Nyangon](https://github.com/AnnaL001)**

## Setup/Installation Requirements

- Using a mobile device/laptop ensure you have access to stable internet connection
- To access the Java application's code from your GitHub repository, you can fork the repository main's branch via the 'Fork' button.
- To access the Java application's code locally, you can clone the main branch or download the ZIP folder via the 'Code' button
- Once locally, you can view/run the Java application's code via a text editor(VS Code or Sublime Text) or an IDE(IntelliJ).
- In the case of IntelliJ, to navigate you can reference their documentation https://www.jetbrains.com/help/idea/getting-started.html
- Otherwise to view the web application navigate to the link below <br>
  [Live Site](https://hero-squad01.herokuapp.com/)
## Behavior Driven Development(BDD)
gender=true represents male gender <br>
gender=false represents female gender

| **Behavior**                              | **Input Example**                           | **Output**                                                         |
|-------------------------------------------|:--------------------------------------------|:-------------------------------------------------------------------|
| Add a hero     | name=Polaris, age=18, specialPower=Magnetokinesis, weakness=Can't use powers without metal around, gender=false |  Hero is added and user redirected to hero list page    |
| Add a squad    | maxSize=5, name=The Avengers, cause = Stop Thanos | Squad is added and user redirected to squad list page |
| Read a hero's data  | id=1   | User redirected to hero's profile page   |
| Read a squad's data | id=1   | User redirected to squad's profile page |
| Update a hero's data | name=Polaris, age=20, specialPower=Magnetokinesis, weakness=Can't use powers without metal around, gender=false  | Hero data is updated and user redirected to hero profile |   
| Update a squad's data | maxSize=10, name=The Avengers, cause = Stop Thanos | Squad data is updated and user redirected to squad profile | 
| Delete a hero not in squad | id=1, list of heroes | Hero's data is deleted and user redirected to hero list page |
| Delete a hero in squad | id=1, list of heroes | Hero's data in hero list and in squad is deleted and user redirected to hero list page |
| Delete a squad | id=1, list of squads |  Squad's data is deleted and user redirected to squad list page |
| Delete a squad with heroes | id=1, list of squads |  Squad's data is deleted, heroes' squadId is updated to 0(NOT SET) and user redirected to squad list page |
| Add hero to squad when squad is not full | hero=Hero(name=Polaris, age=18, specialPower=Magnetokinesis, weakness=Can't use powers without metal around, gender=false), squadId=1, list of heroes, list of squads | Hero is added to squad and user redirected to squad profile
| Add hero to squad when squad is full | hero=Hero(name=Polaris, age=18, specialPower=Magnetokinesis, weakness=Can't use powers without metal around, gender=false), squadId=1, list of heroes, list of squads | Hero is not added and user receives feedback the squad is full
| Delete hero from squad | hero=Hero(name=Polaris, age=18, specialPower=Magnetokinesis, weakness=Can't use powers without metal around, gender=false), squadId=1, list of heroes, list of squads | Hero deleted from squad and their squadId set to 0(NOT SET)

## Dependencies

- JUnit 5 
- Spark Framework
- Handlebars 

## Technologies Used

- Java 
- Bootstrap
- HTML
- CSS

## Support and contact details

In case of any queries you can reach out via email; lynn.nyangon@gmail.com

### License

MIT License
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.<br>
Copyright (c) 2022 **Lynn Nyangon**
