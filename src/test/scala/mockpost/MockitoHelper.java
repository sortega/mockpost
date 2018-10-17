package mockpost;

import org.mockito.Mockito;
import scala.collection.Map$;

public class MockitoHelper {
    public static void setupDefaultArguments(Service mock) {
        Mockito.when(mock.request$default$2()).thenReturn(Map$.MODULE$.empty());
    }
}
