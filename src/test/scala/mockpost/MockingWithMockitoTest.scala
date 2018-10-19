package mockpost

import org.mockito.Mockito.when
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.mockito.MockitoSugar

class MockingWithMockitoTest extends FlatSpec with Matchers with MockitoSugar {

  "Mocking service" should "work when all arguments are explicitly passed" in {
    val service = mock[Service]
    when(service.request("id1", Map("k1" -> "v1", "k2" -> "v2")))
      .thenReturn(List(42))
    val otherService = new OtherService(service)

    otherService.request("id1", maybeTags = Some("k1:v1, k2:v2")) shouldBe 42
  }

  it should "work when using default arguments" in {
    val service = mock[Service]
    when(service.request("id1", Map.empty)).thenReturn(List(42))
    val otherService = new OtherService(service)

    otherService.request("id1", maybeTags = None) shouldBe 42
  }

  it should "work when using default arguments (mitigation)" in {
    val service = mock[Service]
    // We are going to receive null instead of the default argument.
    // Sorry 'bout this. Trust me!
    when(service.request("id1", null)).thenReturn(List(42))
    val otherService = new OtherService(service)

    otherService.request("id1", maybeTags = None) shouldBe 42
  }

  it should "work when using default arguments (mitigation 2)" in {
    val service = mock[Service]
    MockitoHelper.setupDefaultArguments(service)
    when(service.request("id1", Map.empty)).thenReturn(List(42))
    val otherService = new OtherService(service)

    otherService.request("id1", maybeTags = None) shouldBe 42
  }
}
