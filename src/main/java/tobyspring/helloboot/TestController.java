package tobyspring.helloboot;

public class TestController {
    private final String text;

    public TestController(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }
}
