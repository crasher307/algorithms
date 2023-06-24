package lesson2.homework;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Log {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private final List<Log.Data> data = new ArrayList<>();
    private final String name;

    public Log(String name) {
        this.name = name;
    }

    public Log add(String message, String prefix, boolean showStamp) {
        this.data.add(new Log.Data(message, prefix, showStamp));
        return this;
    }
    public Log add(String message, String prefix) {
        return this.add(message, prefix, true);
    }
    public Log add(String message) {
        return this.add(message, " ", true);
    }

    public void show() {
        System.out.printf("%s:\n", this.name);
        this.data.forEach(System.out::println);
    }

    private static class Data {

        private final String message;
        private final String prefix;
        private final String stamp;

        public Data(String message, String prefix, boolean showStamp) {
            this.message = message;
            this.prefix = prefix;
            this.stamp = showStamp ? Log.dateFormat.format(new Date()) : String.format("%23s", "");
        }

        public String toString() {
            return String.format("%s%s%s", this.stamp, this.prefix, this.message);
        }
    }
}

