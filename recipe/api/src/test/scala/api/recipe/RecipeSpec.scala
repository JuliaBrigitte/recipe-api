package api.recipe

import org.scalatra.test.specs2._

class RecipeSpec extends ScalatraSpec { def is = s2"""
  GET / on Recipes random
    should return status 200 $randomTest
  GET / on Recipe with salad
    should return status 200 $recipesAllTest
"""

  implicit val swagger = new RecipeSwagger
  addServlet(new RecipeController, "/recipes/*")

  def randomTest = get("/recipes/random") {
    status must_== 200
  }

  def recipesAllTest = get("/recipes/all") {
    status must_== 200
  }
  
}
