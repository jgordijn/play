package views

import play.api.test.{WithApplication, PlaySpecification}

class WeerImageSpec extends PlaySpecification {
  "Weer image template" should {
    "show rain cloud" in {
      val html = views.html.weer_image(Some(1.1d))
      contentAsString(html) must contain("http://bit.ly/1hCDcPV")
      contentAsString(html) must not contain("http://bit.ly/1kpGWDH")
    }

    "show sun" in {
      val html = views.html.weer_image(Some(1.0d))
      contentAsString(html) must not contain("http://bit.ly/1hCDcPV")
      contentAsString(html) must contain("http://bit.ly/1kpGWDH")
    }

    "show sund" in {
      val html = views.html.weer_image(Option.empty[Double])
      contentAsString(html) must not contain("http://bit.ly/1hCDcPV")
      contentAsString(html) must contain("http://bit.ly/1kpGWDH")
    }
  }

}
