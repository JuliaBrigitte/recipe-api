package api.recipe
import org.scalatra._

class PageController extends ScalatraServlet with FlashMapSupport
{
  
  get("/")
  {
    <html>
      <head><title>Recipes</title></head>
      <h1>Welcome to the recipe api</h1>
      <ul>
      <li><a href="http://localhost:8080/recipes/all">recipes/all</a></li>
      <li><a href="http://localhost:8080/recipes/all?title=Salad">recipes/all?title=Salad</a></li>
      <li><a href="http://localhost:8080/recipes/showByShortcut/butternut-feta">recipes/showByShortcut/butternut-feta</a></li>
      <li><a href="http://localhost:8080/recipes/random">recipes/random</a></li>
      <li><a href="http://localhost:8080/recipes/mealtime/Lunch">mealtime/Lunch get("/mealtime/:mealTime", operation(breakfast)) </a></li>
      <li><a href="http://localhost:8080/recipes/breakfast">recipes/breakfast</a></li>
      <li><a href="http://localhost:8080/recipes/lunch">recipes/lunch</a></li>
      <li><a href="http://localhost:8080/recipes/dessert">recipes/dessert</a></li>
      <li><a href="http://localhost:8080/recipes/dinner">recipes/dinner get("/dinner", operation(dinner))</a></li>
      <li><a href="http://localhost:8080/recipes/mealPlan?calories=400">recipes/mealPlan?calories=400</a></li>
      <li><a href="http://localhost:8080/recipes/mealPlan?calories=2000">recipes/mealPlan?calories=2000</a></li>
      <li><a href="http://localhost:8080/recipes/mealPlan?calories=4000">recipes/mealPlan?calories=4000</a></li>
      <li><a href='http://localhost:8080/recipes/mealPlan?calories=1000&amp;diet=Vegetarian'>recipes/mealPlan?calories=1000&amp;diet=Vegetarian</a></li>
      <li><a href='http://localhost:8080/recipes/mealPlan?calories=2500&amp;diet=Vegetarian&amp;ingredient=eggs'>recipes/mealPlan?calories=2500&amp;diet=Vegetarian&amp;ingredient=eggs</a></li>
  
      </ul>
    </html>
  }
}