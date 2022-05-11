package api.recipe

import org.scalatra.ScalatraServlet
import org.scalatra.swagger._


class ResourcesApp(implicit val swagger: Swagger) extends ScalatraServlet with NativeSwaggerBase

object RecipeSwagger{
  val Info = ApiInfo(
    "Recipe API",
    "Docs for the Recipe API",
    "https://github.com/JuliaBrigitte",
    ContactInfo("Julia Branke", "Julia.Branke@gmx.co.uk", "https://github.com/JuliaBrigitte"),
    LicenseInfo("MIT", "http://opensource.org/licenses/MIT")
  )
}
class RecipeSwagger extends Swagger(Swagger.SpecVersion, "1", RecipeSwagger.Info)
