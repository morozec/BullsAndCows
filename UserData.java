import java.util.ArrayList;

/**
 * Created by moro2609 on 22.04.2019.
 */
public class UserData {
    private ArrayList<Integer> _winSteps = new ArrayList<>();

    public void addWinStep(int winStep)
    {
        _winSteps.add(winStep);
    }

    public double getAverage()
    {
        if (_winSteps.size() == 0)
            return 0;

        double sum = 0;
        for (int i = 0; i < _winSteps.size(); i++)
        {
            sum += _winSteps.get(i);
        }
        return sum / _winSteps.size();
    }
}
