package com.pollsen.dweet;

import com.pollsen.DTO.PollDTO;
import com.pollsen.domain.Poll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class DweetService {

    @Autowired
    private TaskScheduler taskScheduler;

    public void send(Poll poll, boolean open) {
        Date delay;
        String status;

        if (open) {
            status = "open";
            delay = poll.getStartTime();
            send(poll, false);
        } else {
            status = "closed";
            delay = poll.getEndTime();
        }
        taskScheduler.schedule(() -> {
            try {
                sendPost(poll, status);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, delay);

    }

    private void sendPost(Poll poll, String status) throws IOException {
        URL url = new URL("https://dweet.io/dweet/for/paulpollsen");
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection) con;
        http.setRequestMethod("POST");
        http.setDoOutput(true);


        byte[] out = ("{\"question\":\"" + poll.getQuestion() + "\",\"status\":\"" + status + "\"}").getBytes(StandardCharsets.UTF_8);
        int length = out.length;

        http.setFixedLengthStreamingMode(length);
        http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        http.connect();
        try (OutputStream os = http.getOutputStream()) {
            os.write(out);
        }
    }

}
