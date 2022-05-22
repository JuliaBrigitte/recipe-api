Scalatra Recipe Api
===================

Corresponds to the guide at [http://scalatra.org/guides/2.8/swagger.html](http://scalatra.org/guides/2.8/swagger.html)

A sample API, including automatically generated, runnable API documentation,
to demonstrate Scalatra's Swagger functionality.

## Api calls

http://localhost:8080/recipes/all
[{"shortCut":"butternut-feta","title":"Butternut Squash with Feta Cheese"},{"shortCut":"lasagna","title":"Lasagna"},{"shortCut":"salad","title":"salad"},{"shortCut":"raspberry-ice","title":"Vanilla ice-cream with hot raspberries"}]

http://localhost:8080/recipes/all?title=Salad
[{"shortCut":"salad","title":"Salad","mealTime":"lunch","calories":400}]

http://localhost:8080/recipes/showByShortcut/butternut-feta
{"shortCut":"butternut-feta","title":"Butternut Squash with Feta Cheese"}

http://localhost:8080/recipes/random
{"shortCut":"eggs-bacon","title":"Eggs and Bacon","mealTime":"Breakfast","calories":450}

http://localhost:8080/recipes/mealtime/Lunch
[{"shortCut":"butternut-feta","title":"Butternut Squash with Feta Cheese","mealTime":"Lunch","calories":600},{"shortCut":"lasagna","title":"Lasagna","mealTime":"Lunch","calories":800},{"shortCut":"salad","title":"Salad","mealTime":"Lunch","calories":400}]

http://localhost:8080/recipes/breakfast
[{"shortCut":"cereal","title":"Cereal","mealTime":"Breakfast","calories":300},{"shortCut":"eggs-bacon","title":"Eggs and Bacon","mealTime":"Breakfast","calories":450}]

http://localhost:8080/recipes/lunch

http://localhost:8080/recipes/dessert

http://localhost:8080/recipes/dinner


## Build & Run ##

bash

git clone https://github.com/scalatra/scalatra-website-examples.git

cd recipe-api/recipe/api

chmod +x ../../sbt/bin/sbt

../../sbt/bin/sbt ~jetty:start
../../sbt/bin/sbt ~jetty:test

You can see the recipe APIs specification at [http://localhost:8080/api-docs/swagger.json](http://localhost:8080/api-docs/swagger.json).
You can see Swagger-UI at [https://editor.swagger.io/](https://editor.swagger.io/) or [https://swagger.io/swagger-ui/](https://swagger.io/swagger-ui/).


## Original Task Description

Healthy Food API – create a daily meal planner which returns 3 meals for a total number of inputted calories, with exceptions for certain
ingredients and diets.

Create API endpoints with the appropriate HTTP verbs.

API base URL and endpoints are appropriately named.

Addition of unit tests.

Write a descriptive README to document the key features of your solution, your assumptions, approaches and future thoughts.

API endpoints are well documented. You could do this manually by documenting the end points on your README or alternatively look at
a popular API documenting tool called Swagger:
https://swagger.io/solutions/api-documentation/
https://index.scala-lang.org/swagger-api/swagger-play

Apply error and exception handling considerations in your API design.

Use Postman to explore the endpoints of the API and test the API endpoints to ensure you receive the required data returned from the
requests.

###Present solution:
The presentation should cover
Your planning and approach,
How the team worked together (if you worked alone, think about how you decided upon what to create, what decisions you had to make)
How you tracked your progress
Ideas such as showing your task board
Difficulties and successes
Key features
Demo of the application including explaining any technical choices you made and why
Future thoughts on how to develop the app further

Aim for around 10 mins but its entirely ok if its a bit shorter or longer 
