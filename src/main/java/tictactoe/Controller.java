package tictactoe;
import org.springframework.web.bind.annotation.GetMapping;
@org.springframework.stereotype.Controller
public class Controller {
    @GetMapping("/TicTacToe")
    public String ticTacToe()
    {
        return "TicTacToe";
    }

}

