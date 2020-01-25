package tictactoe;

public class Message {
    String type;
    String content;

    public Message(String type, String content)
    {
        this.type = type;
        this.content = content;
    }

    @Override
    public String toString()
    {
        return "{type='" + type + '\'' + ", content=" + content + "}";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
