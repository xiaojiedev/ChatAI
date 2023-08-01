package dev.xiaojie.enums;

public enum URL {
    getModel("https://api.openai.com/v1/models", "GET"),
    chatCompletion("https://api.openai.com/v1/chat/completions", "POST"),
    edit("https://api.openai.com/v1/edits", "POST"),
    images("https://api.openai.com/v1/images/generations", "POST")
    ;

    public final String url;
    public final String method;

    URL(String url, String method) {
        this.url = url;
        this.method = method;
    }
}
