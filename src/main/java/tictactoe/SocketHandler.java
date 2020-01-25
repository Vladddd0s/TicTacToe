package tictactoe;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Random;


@Component
public class SocketHandler extends TextWebSocketHandler {

    private Game game=new Game();
    private WebSocketSession X;
    private WebSocketSession O;
    private WebSocketSession currentPlayer;

    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws  IOException {
        String payload = message.getPayload();
        Message mes = objectMapper.readValue(payload, Message.class);
        if(currentPlayer == session&&X!=null&&O!=null){
            char player = mes.type.toCharArray()[0];
            String s = mes.content;
            int row = Integer.parseInt(s.substring(0,1));
            int col =Integer.parseInt(s.substring(1,2));
            if(game.field[row][col] == ' '){
                if (player == 'X') { currentPlayer = O; }
                else if (player == 'O') { currentPlayer = X; }

                game.field[row][col] = player;

                O.sendMessage(new TextMessage(objectMapper.writeValueAsString(mes)));
                X.sendMessage(new TextMessage(objectMapper.writeValueAsString(mes)));

                if (player == 'X') {
                    SendM( O,"Notification", "Ход O.");
                    SendM( X,"Notification", "Ход O.");
                }
                else {
                    SendM( O,"Notification", "Ход X.");
                    SendM( X,"Notification", "Ход X.");
                }

                if (game.Winner(player)) {
                    String infX="";
                    String infO="";
                    if(game.winner.equals("X")){
                        infX="Вы выиграли.";
                        infO="Вы проиграли.";
                    }else if(game.winner.equals("O")){
                        infO="Вы выиграли.";
                        infX="Вы проиграли.";
                    }else if (game.winner.equals("Draw")){
                        infX="Ничья.";
                        infO="Ничья.";
                    }
                    SendM(O,"Notification",infO);
                    SendM(X,"Notification",infX);
                    O.close();
                    X.close();

                }

            }

        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        if (X == null && O == null)
        {
            game.fill();
            Random random = new Random();
            if (random.nextInt(2) == 1)
            {
                X = session;
                currentPlayer = session;
                SendM(X,"symb","X");
            }
            else
            {
                O = session;
                SendM(O,"symb","O");
            }

        }
        else if (O == null) {
                O = session;
                SendM(O,"symb","O");

            }
          else   if (X == null) {
                X = session;
                currentPlayer = session;
                SendM(X,"symb","X");

            }


    }


    void SendM(WebSocketSession session,String type,String content)  throws  IOException{
        session.sendMessage(new TextMessage(objectMapper.writeValueAsString(new Message(type, content))));
    }
}
