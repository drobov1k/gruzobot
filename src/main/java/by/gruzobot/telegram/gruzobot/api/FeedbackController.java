package by.gruzobot.telegram.gruzobot.api;

import by.gruzobot.telegram.gruzobot.beans.Feedback;
import by.gruzobot.telegram.gruzobot.beans.Logic;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api")
public class FeedbackController {

    /*
    На этот эндпоинт приходит json формата { name: "Валера", phone: "375259379992" }
     */
    @PostMapping(value = "/feedback", headers = {"Content-type=application/json"})
    @ResponseStatus(HttpStatus.OK)
    public String resolve(@RequestBody Feedback feedback) {
        String messageToChat = String.format("Поступила заявка на звонок. %n" +
                "Имя: %s%nНомер: %s", feedback.getName(), feedback.getPhone());
        Logic currentThreadBot = Logic.getInstance();

        Logic.CHATS_IDS.forEach(id -> currentThreadBot.sendMsg(id, messageToChat));
        return "OK";
    }
}
