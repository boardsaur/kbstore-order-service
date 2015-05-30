import akka.event.NoLogging
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.ContentTypes._
import akka.http.scaladsl.model.{HttpResponse, HttpRequest}
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.stream.scaladsl.Flow
import org.scalatest._

class ServiceSpec extends FlatSpec with Matchers with ScalatestRouteTest with Service {
  override def testConfigSource = "akka.loglevel = WARNING"
  override def config = testConfig
  override val logger = NoLogging

  "Service" should "respond to /order request" in {
    Get(s"/order") ~> routes ~> check {
      status shouldBe OK
      contentType shouldBe `application/json`
      responseAs[Vector[Order]] shouldBe Vector(Order("138", "mpigg", "modelm"),
        Order("139", "mpigg", "omnikey"))
    }
  }
}
