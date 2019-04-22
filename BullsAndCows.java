import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by moro2609 on 22.04.2019.
 */
public class BullsAndCows {

    final static int _size = 4;
    private static Map<String, UserData> _userDatas = new HashMap<>();
    private static Scanner _in = new Scanner(System.in);
    private static String _currentLogin;

    public static void main(String[] args)
    {
        login();

        while (true) {
            System.out.println("Choose your action. 1 - play the game. 2 - get average win steps. 3 - change player. 0 - exit");
            int action = _in.nextInt();
            _in.nextLine();

            if (action == 0)
                break;

            switch (action)
            {
                case 1:
                    playTheGame();
                    break;
                case 2:
                    System.out.println("Your average win steps are " + _userDatas.get(_currentLogin).getAverage());
                    break;
                case 3:
                    login();
                    break;
            }
        }

        System.out.println("Game over");
    }

    private static void login()
    {
        System.out.println("Input login");
        _currentLogin = _in.nextLine();

        if (_userDatas.get(_currentLogin) == null)
            _userDatas.put(_currentLogin, new UserData());
    }


    private static void playTheGame()
    {
        int[] number = getNumber();

        System.out.println("Number is genereted. Let's play");


        int steps = 0;
        while (true)
        {
            String userGuess = _in.nextLine();
            if (userGuess.length() != _size)
            {
                System.out.println("Number size must be 4.");
                continue;
            }

            steps++;
            int bulls = 0;
            int cows = 0;

            for (int i = 0; i < userGuess.length(); i++)
            {
                int digit = userGuess.charAt(i) - '0';

                if (number[i] == digit)
                    bulls++;
                else {
                    for (int j = 0; j < _size; ++j) {
                        if (number[j] == digit) {
                            cows++;
                            break;//т.к. числа не повторяются
                        }
                    }
                }
            }

            if (bulls == 4)
            {
                System.out.println("You won! You made " + steps + " steps");
                _userDatas.get(_currentLogin).addWinStep(steps);
                break;
            }

            System.out.println("Bulls: " + bulls + ". Cows: " + cows);

        }
    }

    private static int[] getNumber()
    {

        int[] number = new int[_size];
        Random rnd = new Random();

        for (int i = 0; i < _size; i++)
        {
            while (true) {
                int digit;

                if (i == 0) {
                    digit = 1 + rnd.nextInt(9);
                } else {
                    digit = rnd.nextInt(10);
                }

                boolean hasThisDigit = false;
                for (int j = 0; j < i; ++j)
                {
                    if (number[j] == digit)
                    {
                        hasThisDigit = true;
                        break;
                    }
                }

                if (!hasThisDigit)
                {
                    number[i] = digit;
                    break;
                }

            }

        }
        return number;
    }
}
