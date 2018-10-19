package mockpost

// javap's output
/*
public interface Service {
  public abstract List<java.lang.Object> request(String id, Map<String, String> tags);
  public static Map request$default$2$(Service service);
  public Map<String, String> request$default$2();
}
 */

trait Service {
  def request(id: String, tags: Map[String, String] = Map.empty): List[Int]
}

final class OtherService(service: Service) {
  def request(id: String, maybeTags: Option[String]): Int =
    (maybeTags match {
      case Some(tags) => service.request(id, parseTags(tags))
      case None       => service.request(id)
    }).sum

  private def parseTags(tags: String): Map[String, String] =
    (for {
      pair <- tags.split(",").filter(_.nonEmpty)
      Array(key, value) = pair.split(":")
    } yield key.trim -> value.trim).toMap
}
