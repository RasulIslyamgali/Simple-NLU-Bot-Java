package com.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;

import java.util.Locale;


public class Bot {

    // Create your bot passing the token received from @BotFather
    private final TelegramBot bot = new TelegramBot(System.getenv("BOT_TOKEN"));

    public void serve() {


// Register for updates
        bot.setUpdatesListener(updates -> {
            // ... process updates
            // return id of last processed update or confirm them all
            updates.forEach(this::process);
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    private void process(Update update) {
        // Send messages
        Message message = update.message();
        CallbackQuery callbackQuery = update.callbackQuery();

        BaseRequest request = null;

        if (message != null) {
            System.out.println(message.text().toLowerCase());

            if (message.text().toLowerCase().equals("привет"))
                request = new SendMessage(message.chat().id(), "Вас приветствует телеграм бот, своего рода Simple NLU бот))");
            else if (message.text().toLowerCase().equals("как дела?"))
                request = new SendMessage(message.chat().id(), "У меня все хорошо, как у вас?");
            else if (message.text().toLowerCase().equals("хорошо") || message.text().toLowerCase().equals("нормально"))
                request = new SendMessage(message.chat().id(), "И у меня дела тоже неплохо идут)");
            else
                request = new SendMessage(message.chat().id(), "Пока что возможности мои скудны. Я вас не понял.\nИ вопрос не ко мне, а разработчику))");
            long chatId = message.chat().id();

        }

        if (request != null) {
            bot.execute(request);
        }
    }
}
