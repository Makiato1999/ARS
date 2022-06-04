# Coding Standards
In order to write simple, clean and functional code for the project lifecycle, the following coding standards will be followed by all team members. 
Note: To be followed from iteration 2 onwards.

## Naming
**Class Names:** First letter of the class will be capitalized, the followiing new words will be capitalized as well.
```java
Class ClassNameYouChoose { }
```

**Variable Names in Java:** Will be using reasonable variable names which starts in lower case and capitalize only first letter of each new word (following camelCase).
```java
yourVariableName 
```

**Ids in XML layout:** All xml related ids will have the variable name of the android studio palette separated with underscore, followed by id. For example the departure date button id will be written as folllows.
```xml
departure_date_bttn_id
```

**Varibale Names in XML layout:** All xml related names will have the variable name of the android studio palette separated with underscore. For example the departure date button  will be written as folllows.
```xml
departure_date_bttn
```



## Writting readable code
**Comments:** Add comments to blocks of code if not obivious.
```java
//customizing screen layout to set dark theme and hide action bar
setTheme(R.style.Base_Theme_AppCompat_Light_DarkActionBar); 
getSupportActionBar().hide(); 
```

**Indentation:** Will use consitent indentation to clarify control structures and levels of indentation should clearly indecate the depth of nesting.
```java
Class ClassName{
    public static void methodName(){
        if(...){
          //some code
        }
    }
}
```

**Braces:** All braces will be besides the line. In functions, conditional statements. They should also be vertically aligned as their constructors.
```java
public void bracesNext(){
    if(...){
      //some code
    }
}
```
