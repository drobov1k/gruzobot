package by.gruzobot.telegram.gruzobot;

import by.gruzobot.telegram.gruzobot.beans.Logic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

@SpringBootApplication
public class GruzobotApplication {

	public static void main(String[] args) {
		ApiContextInitializer.init();
		TelegramBotsApi api = new TelegramBotsApi();

		try {
			api.registerBot(Logic.getInstance());
		} catch (TelegramApiException var3) {
			var3.printStackTrace();
		}

		SpringApplication.run(GruzobotApplication.class, args);
	}

}
