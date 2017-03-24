[![BCH compliance](https://bettercodehub.com/edge/badge/HanzehogeschoolSICT/Sorting)](https://bettercodehub.com/)
# Bettercodehub evaluatie
Dit project heeft een 9 van de 10. Het is geen 10 doordat er een afwezigheid is van Tests. Er zal ten alle tijden nog verbeteringen aangebracht kunnen worden en deze zullen hieronder worden beschreven. Hiernaast zal er in de toekomst ook gekeken moeten worden naar Unit Testing.


## Write Short Units of Code
Er zijn nog enkele methodes die over het limiet van 15 regels gaan en hier zou dan nog een refactor slag voor uitgevoerd kunnen worden.

## Write Simple Units of Code
Alle code is zo te lezen dat het eenvoudig te begrijpen is.

## Write Code Once
Er zijn nog een paar regels redunant in de code. Dit zou verbeterd kunnen worden door te abstraheren.

## Keep Unit Interfaces Small
Alle methoden hebben vier of minder parameters. Het zou nog verbeterd kunnen worden door ze nog meer in te korten. Dat is in deze situatie een overbodig.

## Seperate Concerns in Modules
De klasse AbstractSort is in dit systeem iets te veel verbonden met verschillende modulen. Dit weegt vrij weinig mee doordat het een abstracte klasse is voor de verschillende sorteer algoritmes.

## Couple Architecture Components Loosely
De code is vrij goed van elkaar los gekoppeld en hoeft hierdoor weinig van elkaar te weten om te kunnen functioneren.

## Keep Architecture Components Balanced
De gewichten van de componenten zijn goed doordat de code goed is opgesplitst naar aanleiding van functionaliteit.

## Keep your Codebase Small
De grootte van de code is erg klein 0.08 jaar. Hierdoor dus in de goede range van 0 tot en met 20 jaar.

## Automate Tests
De code wordt niet getest. Hierdoor is er geen zekerheid in functionaliteit wanneer er een wijziging plaatsvind.

## Write Clean Code
De code is goed te onderhouden en bevat geen tot weinig nutteloze commentaren, geen slechte namen voor methoden en variabelen.