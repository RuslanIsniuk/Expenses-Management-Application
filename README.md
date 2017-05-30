# Expenses-Management-Application

Simple console application that provides ability for user to store and track expenditures.

## Launch

To launch the application, you need to: 
* open file /src/main/resources/hibernate.cfg.xml and change the following DB connection properties
```xml
      <property name="connection.url">jdbc:mysql://localhost:3306/intelliarts?useSSL=false</property>
      <property name="connection.username">root</property>
      <property name="connection.password">root</property>
```

* use IDE or just open the folder, where the project is saved, and enter in console 
```
      mvn package exec:java
```

## Command's usage syntax

Add new expenses:
```
     add 2017-04-24 10 USD Sweets
     add 2017-04-25 24 EUR Products
     add 2017-04-24 3 PLN Milk
     ...
```

Get list of all expenses sorted by date:
```
    list
```

Remove all expenses with specified date:
```
   clear 2017-04-24
   clear 2016-04-12
   ...
```

Get total amount of spent money in specific currency (using http://fixer.io):
```
   total PLN
   total USD
   ...
```

View command list:
```
   help
```
