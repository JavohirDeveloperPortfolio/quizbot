package uz.pdp;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.modul.BotState;
import uz.pdp.modul.Role;
import uz.pdp.modul.User;
import uz.pdp.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class MyBot extends TelegramLongPollingBot {
    private final UserRepository userRepository = new UserRepository();

    public MyBot(String botToken) {
        super(botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasCallbackQuery()){

        } else if (update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            String text = update.getMessage().getText();

            if (text.equals("/start")){
                if (userRepository.selectByChatId(chatId) == null){
                    userRepository.createUser(chatId, BotState.START);
                }

                ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
                List<KeyboardRow> rowList = new ArrayList<>();
                KeyboardRow row1 = new KeyboardRow();
                KeyboardRow row2 = new KeyboardRow();
                KeyboardButton uz = new KeyboardButton();
                uz.setText("\uD83C\uDDFA\uD83C\uDDFFUzbek tili");
                KeyboardButton ru = new KeyboardButton();
                ru.setText("\uD83C\uDDF7\uD83C\uDDFARus tili");


                row1.add(uz);
                row2.add(ru);


                rowList.add(row1);
                rowList.add(row2);
                markup.setKeyboard(rowList);
                markup.setResizeKeyboard(true);
                SendMessage message = new SendMessage();
                message.setText("Tilni tanlang");
                message.setChatId(chatId);
                message.setReplyMarkup(markup);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
            User user = userRepository.selectByChatId(chatId);
            if (user.getRole() == Role.ADMIN){

            } else if (user.getRole() == Role.STUDENT) {

            } else if (user.getRole() == Role.TEACHER) {

            } else {

            }
        }
    }

    @Override
    public String getBotUsername() {
        return "pdp_senior_group_bot";
    }
}
