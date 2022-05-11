package api.recipe

import org.scalatra._

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

  val getRecipes =
    (apiOperation[List[Recipe]]("getRecipes")
      summary "Show all recipes"
      tags("Flowers")
      description "Shows all the recipes in the recipe api. You can search it too."
      parameter queryParam[Option[String]]("title").description("A title to search for"))

  get("/", operation(getRecipes)){
    params.get("title") match {
      case Some(title) => RecipeData.all filter (_.title.toLowerCase contains title.toLowerCase())
      case None => RecipeData.all
    }
  }


  val findByShortCut =
    (apiOperation[Recipe]("findByShortCut")
      summary "Find by a recipe by its ShortCut"
      tags("Recipes")
      parameters (
      pathParam[String]("shortCut").description("ShortCut of recipe that needs to be fetched"))
      responseMessage ResponseMessage(404, "Recipe Not Found"))

  get("/:shortCut", operation(findByShortCut)) {
    RecipeData.all find (_.shortCut == params("shortCut")) match {
      case Some(b) => b
      case None => halt(404)
    }
  }
}


// A Flower object to use as a data model
case class Recipe(shortCut: String, title: String)

// An amazing datastore!
object RecipeData {

  /**
   * Some fake flowers data so we can simulate retrievals.
   */
  var all = List(
    Recipe("butternut-feta", "Butternut Squash with Feta Cheese"),
    Recipe("lasagna", "Lasagna"),
    Recipe("salad", "salad"),
    Recipe("raspberry-ice", "Vanilla ice-cream with hot raspberries"))
}
