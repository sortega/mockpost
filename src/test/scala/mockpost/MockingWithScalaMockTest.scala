package mockpost

import org.scalamock.scalatest.MockFactory
import org.scalatest.{FlatSpec, Matchers}

class MockingWithScalaMockTest extends FlatSpec with Matchers with MockFactory {

  "Mocking service" should "work when all arguments are explicitly passed" in {
    val service = mock[Service]
    (service.request _)
      .expects("id1", Map("k1" -> "v1", "k2" -> "v2"))
      .returning(List(42))
    val otherService = new OtherService(service)

    otherService.request("id1", maybeTags = Some("k1:v1, k2:v2")) shouldBe 42
  }

  it should "work when using default arguments" in {
    val service = mock[Service]
    (service.request _)
      .expects("id1", Map.empty[String, String])
      .returning(List(42))
    val otherService = new OtherService(service)

    otherService.request("id1", maybeTags = None) shouldBe 42
  }

}
