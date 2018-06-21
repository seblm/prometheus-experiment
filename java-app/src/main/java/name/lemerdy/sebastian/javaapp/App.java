package name.lemerdy.sebastian.javaapp;

import io.prometheus.client.Counter;
import io.prometheus.client.exporter.HTTPServer;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class App {

    private static final Counter requests = Counter.build()
            .name("java_app_requests_total")
            .help("Total requests.")
            .register();

    public static void main(String[] args) {
        try {
            new HTTPServer(1234);
            System.out.println("Prometheus server started");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                System.out.println("Incrementing");
                requests.inc();
            }
        }, 1000, 2000);

        System.out.println("I'm done with main thread");
    }

}
