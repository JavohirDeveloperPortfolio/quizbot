package uz.pdp.repository;

import uz.pdp.config.CustomDataSource;
import uz.pdp.modul.BotState;
import uz.pdp.modul.Role;
import uz.pdp.modul.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    private final String insertUser =
            "INSERT INTO botusers (chat_id,state) VALUES (?,?)";
    private final String selectByChatId =
            "SELECT * FROM botusers WHERE chat_id = ?";

    private final String updateBotState =
            "UPDATE botusers SET state = ? WHERE chat_id = ?";

    private final String updateLanguage =
            "UPDATE botusers SET lang = ? WHERE chat_id = ?";

    public void updateBotState(long chatId, BotState botState){
        try {
            Connection conn = CustomDataSource.getInstance().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(updateBotState);

            preparedStatement.setString(1,botState.name());
            preparedStatement.setLong(2,chatId);

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateLanguage(long chatId, String lang){
        try {
            Connection conn = CustomDataSource.getInstance().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(updateLanguage);

            preparedStatement.setString(1,lang);
            preparedStatement.setLong(2,chatId);

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createUser(long chatId, BotState state) {
        try {
            Connection conn = CustomDataSource.getInstance().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(insertUser);

            preparedStatement.setLong(1,chatId);
            preparedStatement.setString(2,state.name());

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public User selectByChatId(long chatId) {
        User user = new User();
        try (
                Connection con = CustomDataSource.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(selectByChatId)
        ) {
            ps.setLong(1, chatId);
            ResultSet resultSet = ps.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            user.setFullName(resultSet.getString("lang"));
            user.setState(BotState.valueOf(resultSet.getString("state")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

}
