package de.andrestefanov.dwa;

import com.google.gson.Gson;
import de.andrestefanov.dwa.model.Action;
import de.andrestefanov.dwa.model.ButtonAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

@RestController
public class Controller {

    @Autowired
    private Storage storage;

    @Autowired
    private Sender sender;

    @RequestMapping(value = "/images/{days}.jpg", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public void daysImage(HttpServletResponse response, @PathVariable long days) throws Exception {
        File file = new File("/opt/dwa/images/" + days + ".jpg");

        StreamUtils.copy(new FileInputStream(file), response.getOutputStream());
    }

    @RequestMapping(value = "/current", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public void currentImage(HttpServletResponse response) throws Exception {
        long days = TimeUnit.DAYS.convert(System.currentTimeMillis() - storage.getTimestamp(), TimeUnit.MILLISECONDS);

        daysImage(response, days);
    }

    @RequestMapping(value = "/value", method = RequestMethod.GET)
    public ResponseEntity<?> getCurrentValue() {
        return new ResponseEntity<>(storage.getTimestamp(), HttpStatus.OK);
    }

    @RequestMapping(value = "/value/{val}", method = RequestMethod.POST)
    public ResponseEntity<?> getCurrentValue(@PathVariable long val) {
        storage.setTimestamp(val);
        return new ResponseEntity<>(storage.getTimestamp(), HttpStatus.OK);
    }

    @RequestMapping(value = "/resend", method = RequestMethod.GET)
    public void resend() {
        sender.sendInspectionRequest();
    }

    @RequestMapping(value = "/remove/{ts}", method = RequestMethod.GET)
    public void remove(@PathVariable String ts) {
        sender.removeMessage(ts);
    }

    @RequestMapping(value = "/sendstatus", method = RequestMethod.GET)
    public void sendStatus() {
        long days = TimeUnit.DAYS.convert(System.currentTimeMillis() - storage.getTimestamp(), TimeUnit.MILLISECONDS);
        sender.sendCurrentStatus(days);
    }

    @RequestMapping(value = "/actions", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> handleButton(HttpServletRequest request) {
        ButtonAction payload = new Gson().fromJson(request.getParameter("payload"), ButtonAction.class);

        for (Action action : payload.actions) {
            if ("dwa".equals(action.name)) {
                if (action.value.equals("negative")) {
                    storage.resetTimestamp();
                }
            }
        }

        sender.removeMessage(payload.messageTs);

        long days = TimeUnit.DAYS.convert(System.currentTimeMillis() - storage.getTimestamp(), TimeUnit.MILLISECONDS);
        sender.sendCurrentStatus(days);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
