Scalatra Recipe Api
===================

Corresponds to the guide at [http://scalatra.org/guides/2.8/swagger.html](http://scalatra.org/guides/2.8/swagger.html)

A sample API, including automatically generated, runnable API documentation,
to demonstrate Scalatra's Swagger functionality.

## Api calls

http://localhost:8080/recipes
[{"shortCut":"butternut-feta","title":"Butternut Squash with Feta Cheese"},{"shortCut":"lasagna","title":"Lasagna"},{"shortCut":"salad","title":"salad"},{"shortCut":"raspberry-ice","title":"Vanilla ice-cream with hot raspberries"}]

http://localhost:8080/recipes/butternut-feta
{"shortCut":"butternut-feta","title":"Butternut Squash with Feta Cheese"}

## Build & Run ##

```sh
$ git clone https://github.com/scalatra/scalatra-website-examples.git
$ cd recipe-api/recipe/api
$ chmod +x ../../../sbt/bin/sbt
$ ../../../sbt/bin/sbt ~jetty:start
```

You can see the recipe APIs specification at [http://localhost:8080/api-docs/swagger.json](http://localhost:8080/api-docs/swagger.json).
You can see Swagger-UI at [https://editor.swagger.io/](https://editor.swagger.io/) or [https://swagger.io/swagger-ui/](https://swagger.io/swagger-ui/).
