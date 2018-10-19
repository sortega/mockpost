package mockpost;

public class UseCompanion {
    public static void main() {
        Point origin = Point$.MODULE$.Origin();
        System.out.println(String.format(
                "Origin is at x=%d y=%d", origin.x(), origin.y()));
    }
}
