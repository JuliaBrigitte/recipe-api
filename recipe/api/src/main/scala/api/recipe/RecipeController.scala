package api.recipe

import org.scalatra._
import scala.util.Random

// Swagger-specific Scalatra imports
import org.scalatra.swagger._
import org.scalatra.swagger.ResponseMessage

// JSON-related libraries
import org.json4s.{DefaultFormats, Formats}

// JSON handling support from Scalatra
import org.scalatra.json._

class RecipeController(implicit val swagger: Swagger) extends ScalatraServlet with NativeJsonSupport with SwaggerSupport  {

  // Sets up automatic case class to JSON output serialization, required by
  // the JValueResult trait.
  protected implicit val jsonFormats: Formats = DefaultFormats

  // A description of our application. This will show up in the Swagger docs.
  protected val applicationDescription = "The recipe API. Browsing and searching for recipes"

  // Before every action runs, set the content type to be in JSON format.
  before() {
    contentType = formats("json")
  }

  //http://localhost:8080/recipes/all
  val getRecipes =
    (apiOperation[List[Recipe]]("getRecipes")
      summary "Show all recipes"
      tags("Recipes")
      description "Shows all the recipes in the recipe api."
      parameter queryParam[Option[String]]("title").description("A title to search for"))
      
  get("/all", operation(getRecipes)){
    params.get("title") match {
      case Some(title) => RecipeData.all filter (_.title.toLowerCase contains title.toLowerCase())
      case None => RecipeData.all
    }
  }
  
  //http://localhost:8080/recipes/random
  val getRandomRecipe =
    (apiOperation[List[Recipe]]("getRecipes")
    summary "Get random recipe"
    tags("Random recipe")
    description "Shows a random recipe."
    parameter queryParam[Option[String]]("title").description("A title to search for"))
  
  get("/random", operation(getRandomRecipe)){
    val random = new Random
    RecipeData.all(random.nextInt(RecipeData.all.length))
  }
  
  //http://localhost:8080/recipes/showByShortcut/salad
  val showByShortcut =
    (apiOperation[Recipe]("showByShortcut")
      summary "Find by a recipe by its shortcut"
      tags("Recipes")
      parameters (pathParam[String]("shortCut").description("ShortCut of recipe that needs to be fetched")))

  get("/showByShortcut/:shortCut", operation(showByShortcut)) {
    val shortCut=params("shortCut")
    RecipeData.all find (_.shortCut == params("shortCut")) match {
      case Some(b) => b
      case None => s"Search for shortcut $shortCut yields no results"
    }
  }
  
  //http://localhost:8080/recipes/showByTitle/Salad
  val showByTitle =
    (apiOperation[Recipe]("showByTitle")
    summary "Find by a recipe by its title"
    tags("Recipes")
    parameters (pathParam[String]("title").description("Title of recipe that needs to be fetched")))

  get("/showByTitle/:title", operation(showByTitle)) {
    val title=params("title")
    RecipeData.all find (_.title == params("title")) match {
      case Some(b) => b
      case None => s"Search for title $title yields no results"
    }
  }

  val breakfast =
    (apiOperation[Recipe]("breakfast")
    summary "Show Breakfast recipes"
    tags("Recipes"))

  get("/breakfast", operation(breakfast))
  {
    RecipeData.all.filter(recipe=>recipe.mealTime == "Breakfast") match
    {
      case Nil => s"No Recipes for Breakfast available"
      case list => list
    }
  }
  
  val lunch =
    (apiOperation[Recipe]("lunch")
    summary "Show Lunch recipes"
    tags("Recipes"))
  
  get("/lunch", operation(breakfast))
  {
    RecipeData.all.filter(recipe=>recipe.mealTime == "Lunch") match
    {
      case Nil => s"No Recipes for Lunch available"
      case list => list
    }
  }
  
  val dessert =
    (apiOperation[Recipe]("dessert")
    summary "Show Dessert recipes"
    tags("Recipes"))
  
  get("/dessert", operation(dessert))
  {
    RecipeData.all.filter(recipe=>recipe.mealTime == "Dessert") match
    {
      case Nil => s"No Recipes for Dessert available"
      case list => list
    }
  }
  
  val dinner =
    (apiOperation[Recipe]("dinner")
    summary "Show Dinner recipes"
    tags("Recipes"))
  
  get("/dinner", operation(dinner))
  {
    RecipeData.all.filter(recipe=>recipe.mealTime == "Dinner") match
    {
      case Nil => s"No Recipes for Dinner available"
      case list => list
    }
  }
  
  //http://localhost:8080/recipes/mealtime/Lunch
  val mealtime =
    (apiOperation[Recipe]("mealtime")
    summary "Show recipes for certain mealtimes"
    tags("Recipes"))
  
  get("/mealtime/:mealTime", operation(breakfast))
  {
    val mealTime=params("mealTime")
    RecipeData.all.filter(recipe=>recipe.mealTime == mealTime) match
    {
      case Nil => s"No Recipes for $mealTime available"
      case list => list
    }
  }
  
  //create a daily meal planner which returns 3 meals for a total number of inputted calories, with exceptions for certain
  //ingredients and diets.
  val mealPlan =
    (apiOperation[Recipe]("mealPlan")
    summary "Output a daily meal plan"
    tags("Recipes")
    parameters (
    pathParam[String]("calories").description("Calories allowed for daily meal plan"),
    pathParam[String]("diet").description("Diet choosen for daily meal plan"),
    pathParam[String]("ingredient").description("Ingredient that should be avoided for daily meal plan"))
    responseMessage ResponseMessage(404, "Recipe Not Found")
    )

  get("/mealPlan", operation(mealPlan)) {
    val calories=params("calories")

    val random = new Random
    var sequenceToCheck:Seq[Recipe]=Seq()
    var result:Seq[Recipe]=Seq()
    var diet=params.getOrElse("diet","none")
    var ingredient=params.getOrElse("ingredient","")
    if ("Vegetarian" == diet)
      {
        if (ingredient != "")
        {
          sequenceToCheck=RecipeData.all.filter(recipe=>((recipe.diet == "Vegetarian")&&(!(recipe.ingredients contains ingredient))))
        }
        else
        {
          sequenceToCheck=RecipeData.all.filter(recipe=>recipe.diet == "Vegetarian")
        }
      }
    else
      {
        if (ingredient != "")
          {
            sequenceToCheck=RecipeData.all.filter(recipe=>(!(recipe.ingredients contains ingredient)))

          }
        else
          {
            sequenceToCheck=RecipeData.all
          }
      }
  
    var mealPlan="print"
    if (451 > calories.toInt)
    {
      mealPlan="fast"
    }
    else if (3000 < calories.toInt)
    {
      mealPlan="tooMuch"
    }
    else
    {
      var totalCalories:Int=0
      if (sequenceToCheck.filter(recipe=>recipe.mealTime == "Breakfast").length > 0)
      {
        var randomRecipe=sequenceToCheck.filter(recipe=>recipe.mealTime == "Breakfast")(random.nextInt(sequenceToCheck.filter(recipe=>recipe.mealTime == "Breakfast").length))
        totalCalories=totalCalories+randomRecipe.calories.toInt
        result=result:+randomRecipe
      }
  
      if (sequenceToCheck.filter(recipe=>recipe.mealTime == "Lunch").length > 0)
      {
        var randomRecipe2=sequenceToCheck.filter(recipe=>recipe.mealTime == "Lunch")(random.nextInt(sequenceToCheck.filter(recipe=>recipe.mealTime == "Lunch").length))
        totalCalories=totalCalories+randomRecipe2.calories.toInt
        if (totalCalories < calories.toInt)
        {
          result=result:+randomRecipe2
        }
      }
  
      if (sequenceToCheck.filter(recipe=>recipe.mealTime == "Dinner").length > 0)
      {
        var randomRecipe3=sequenceToCheck.filter(recipe=>recipe.mealTime == "Dinner")(random.nextInt(sequenceToCheck.filter(recipe=>recipe.mealTime == "Dinner").length))
        totalCalories=totalCalories+randomRecipe3.calories.toInt
        if (totalCalories < calories.toInt)
        {
          result=result:+randomRecipe3
        }
      }
  
      if (sequenceToCheck.filter(recipe=>recipe.mealTime == "Dessert").length > 0)
      {
        var randomRecipe4=sequenceToCheck.filter(recipe=>recipe.mealTime == "Dessert")(random.nextInt(sequenceToCheck.filter(recipe=>recipe.mealTime == "Dessert").length))
        totalCalories=totalCalories+randomRecipe4.calories.toInt
        while (totalCalories < calories.toInt)
        {
          result=result:+randomRecipe4
          randomRecipe4=sequenceToCheck.filter(recipe=>recipe.mealTime == "Dessert")(random.nextInt(sequenceToCheck.filter(recipe=>recipe.mealTime == "Dessert").length))
          totalCalories=totalCalories+randomRecipe4.calories.toInt
        }
      }
    }
    

    (mealPlan ) match
    {
      case "fast" => s"You might as well fast today"
      case "tooMuch" => s"You should not plan to eat so much"
      case print => result
    }
  }
  
}

// A Recipe object to use as a data model
case class Recipe(shortCut: String, title: String, mealTime: String, calories: Int, diet: String, ingredients: Array[String])

// An amazing datastore!
object RecipeData {

  /**
   * Some fake flowers data so we can simulate retrievals.
   */
  var all = Seq(
    Recipe("cereal", "Cereal", "Breakfast", 300, "Vegetarian", Array("milk", "nuts")),
    Recipe("eggs-bacon", "Eggs and Bacon", "Breakfast", 450, "Omnivore", Array("eggs", "meat")),
    Recipe("butternut-feta", "Butternut Squash with Feta Cheese", "Lunch", 600, "Vegetarian", Array("cheese")),
    Recipe("lasagna", "Lasagna", "Lunch", 800, "Omnivore", Array("milk", "meat")),
    Recipe("salad", "Salad", "Lunch", 400, "Vegetarian", Array()),
    Recipe("raspberry-ice", "Vanilla ice-cream with hot raspberries", "Dessert", 450, "Vegetarian", Array("milk")),
    Recipe("creme-brulee", "Creme Brulee French desert", "Dessert", 450, "Vegetarian", Array("milk", "eggs")),
    Recipe("orange-cream", "Orange Cream", "Dessert", 550, "Vegetarian", Array("milk")),
    Recipe("bread-cheese", "Bread and cheese", "Dinner", 500, "Vegetarian", Array("cheese")),
    Recipe("corn-hob", "Corn on the hob", "Dinner", 400, "Vegetarian", Array()),
    Recipe("artichokes", "Artichokes", "Dinner", 400, "Vegetarian", Array())
  )
}
