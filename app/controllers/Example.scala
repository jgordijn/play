package controllers

import play.api.mvc._
import play.api.libs.ws.WS
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object Example extends Controller {
  //curl -v http://localhost:9000/simpel
  //< Content-Type: text/plain; charset=utf-8
  def simpel = Action {
    Ok("Dit is een simpele actie")
  }

  //curl -v -XPUT --header "Content-Type: text/xml" --data "<reservation><name>Jeroen</name></reservation>" http://localhost:9000/reservation/12
  //< Content-Type: application/xml; charset=utf-8
  def reservation(id: Long) = Action(parse.xml) { request =>
    Ok(<thanks><id>{id}</id><name>{(request.body \ "name").text}</name></thanks>)
  }

  def temperature(city: String) = Action.async {
    val url = s"http://api.openweathermap.org/data/2.5/weather?q=${city},NL&mode=json&units=metric"
    for {
      response <- WS.url(url).get()
      temp = (response.json \ "main" \ "temp").as[Double]
      rain = (response.json \ "rain" \ "3h").asOpt[Double]
    } yield Ok(<html><body>
          <h1>Het is nu {temp}&deg; C in {city}</h1>
          Er komt {rain.map(mm => s"${mm}mm").getOrElse("geen")} regen
          </body></html>).as(HTML)
  }





}
