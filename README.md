# ReadMe

## Test

http://localhost:8080/xeline-website/login<br>
http://localhost:8080/xeline-website/message<br>

## Settings

### Eclipse

Preferences -> Java -> Code Style -> Formatter -> Import -> [rule-files\formatter\java-formatter.xml]<br>
Preferences -> JavaScript -> Code Style -> Formatter -> Import -> [rule-files\formatter\javascript-formatter.xml]<br>

## Directory structure

```
.
├ build                   # webpack build configuration
├ .mvn                    # maven wrapper directory
├ node                    # maven will install node here
├ node_modules            # node modules
├ target                  # compiled java sources
├ src                     # sources
│  ├ main                 
│  │  ├ java                           # java sources
│  │  ├ vuejs                          # javascript sources
│  │  └ resources                      # resources
│  │     ├ static                      # static resources
│  │     │   ├ css                          # styles
│  │     │   ├ images                       # images
│  │     │   ├ dist                         # generated javascript goes here
│  │     │   └ index.html                   # development index.html
│  │     └ application.properties      # spring boot configuration properties
│  └ test                       # test sources
│      ├ java                   # java tests
│      └ vuejs                  # vue.js tests
├ .babelrc                 # babel configuration
├ .eslintrc                # eslint configuration
├ .gitignore               # gitignore
├ package.json             # node configuration
├ pom.xml                  # maven configuration 
├ mvnw                     # maven linux wrapper
├ mvnw.cmd                 # maven windows wrapper
├ npm                      # local npm linux wrapper
├ npm.cmd                  # local npm windows wrapper
└ README.md                # this file
```
