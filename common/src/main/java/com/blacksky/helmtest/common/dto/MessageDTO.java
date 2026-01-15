package com.blacksky.helmtest.common.dto;

public class MessageDTO {
    private String time;
    private String version;
    private String hostname;

    public MessageDTO() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    @Override
    public String toString() {
        return "MessageDTO{time=" + time + ", version=" + version + ", hostname=" + hostname + "}";
    }
}
