package by.gruzobot.telegram.gruzobot.beans;


import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.HashSet;
import java.util.Set;

public class Logic extends TelegramLongPollingBot {
    private static final String BOT_NAME = "gruzogomelbot";
    private static final String TOKEN = "1191962649:AAF85p3xp_yPVXhDTqS1bdxlEP7teb5hoKI";
    private static final String START_COMMAND = "/start";
    private static final String CHECK_COMMAND = "/check";
    private static Logic instance;

    public static final Set<Long> CHATS_IDS = new HashSet<>();

    private Logic(){}

    public void onUpdateReceived(Update update) {
        Message msg = update.getMessage();
        Long currentId = msg.getChatId();
        String txt = msg.getText(), message;

        if (CHATS_IDS.contains(currentId) && txt.equals(START_COMMAND)) {
            message = "Как только будет заказан звонок, сюда сразу же придёт сообщение.";
        } else {
            switch (txt) {
                case START_COMMAND:
                    message = "Привет, я бот грузоперевозок для ИП Соловьёв\n" +
                            "При запросе с сайта сюда будут приходить уведомления.\n" +
                            "Для проверки работоспособности бота введите /check";
                    break;
                case CHECK_COMMAND:
                    message = "Сервер работает исправно";
                    break;
                default:
                    message = "Я не понимаю других команд. Да оно мне и нах*й не надо";
            }
        }

        CHATS_IDS.add(currentId);
        this.sendMsg(msg, message);
    }

    public void sendMsg(Message msg, String text) {
        sendMsg(msg.getChatId(), text);
    }

    public void sendMsg(Long chatId, String text) {
        SendMessage s = new SendMessage();
        s.setChatId(chatId);
        s.setText(text);

        try {
            this.sendMessage(s);
        } catch (TelegramApiException var5) {
            var5.printStackTrace();
            //ТУТ БУДЕТ ОТПРАВКА СООБЩЕНИЯ НА ЕМЭЙЛ О ОТКАЗЕ БОТА
        }
    }

    public String getBotUsername() {
        return BOT_NAME;
    }

    public String getBotToken() {
        return TOKEN;
    }

    public static Logic getInstance() {
        if (instance == null) {
            instance = new Logic();
        }

        return instance;
    }
}

