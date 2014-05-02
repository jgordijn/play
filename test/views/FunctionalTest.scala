package views

import play.api.test._

class FunctionalTest extends PlaySpecification {
  "The temperature page " should {
    "show an image" in new WithBrowser {
      browser.goTo("/temptmpl/Bussum")
      browser.title must equalTo("Het weer in Bussum")
      browser.$("img").getAttribute("src") must contain("http://bit.ly/")
    }
  }
}
