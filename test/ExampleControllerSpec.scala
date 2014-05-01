import controllers.Example
import org.specs2.mutable.Specification
import play.api.http.MimeTypes
import play.api.templates.Html
import play.api.test._
import play.api.test.Helpers._
import scala.xml.XML
import org.specs2.matcher.XmlMatchers


class ExampleControllerSpec extends PlaySpecification with XmlMatchers{
  "In ExampleController the reservation" should {
    "return xml body" in {
      val req = FakeRequest().withBody(<reservation><name>Jeroen</name></reservation>)
      val reservationId = 123
      val result = Example.reservation(reservationId)(req)
      status(result) must equalTo(OK)
      contentType(result) must beSome(MimeTypes.XML)
      val xml = XML.loadString(contentAsString(result))
      xml must \("id") \> "123"
      xml must \("name") \> "Jeroen"
    }

    "respond to the index Action" in new WithApplication {
      val Some(result) = route(FakeRequest(PUT, "/reservation/123").withBody(<reservation><name>Jeroen</name></reservation>))
      contentType(result) must beSome(MimeTypes.XML)
      val xml = XML.loadString(contentAsString(result))
      xml must \("id") \> "123"
      xml must \("name") \> "Jeroen"
    }

  }


}
