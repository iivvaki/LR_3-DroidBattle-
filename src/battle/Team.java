package battle;

import com.droid.Droid;

import java.util.List;
import java.util.Scanner;

public class Team {

    private Droid droid;

    public void teams(List<Droid> team1, List<Droid> team2, List<Droid> droids){
        Scanner scan = new Scanner(System.in);
        System.out.println("Would you like auto fight team or create new team?\n"+"1. Auto team\n"+"2. Create new team");
        int num3 = scan.nextInt();
        if(num3 == 1){
            chooseDroidsForTeam(team1, team2, droids);
        }
        else {
            createNewTeam(team1, team2, droids);
        }
    }

    public void chooseDroidsForTeam(List<Droid> Team, List<Droid> Team1, List<Droid> droids){
//        printListDroid();

        int i = 0;
        System.out.println("\nChoose droids for team 1:");
        while(i < 3){
            Droid newDroid = droid.selectDroid(droids);
            Team.add(newDroid);
            droids.remove(newDroid);
            i++;
        }
        System.out.println("\nChoose droids for team 2:");
        int j = 0;
        while(j < 3){
            Droid newDroid = droid.selectDroid(droids);
            Team1.add(newDroid);
            droids.remove(newDroid);
            j++;
        }

    }


    public void createNewTeam(List<Droid> Team, List<Droid> Team1, List<Droid> droids){
        System.out.println("Create 3 droids for first team");
        int i = 0;
        while(i < 3){
            Droid newDroid = droid.selectDroid(droids);
            Team.add(newDroid);
            i++;
        }
        int j = 0;
        while(j < 3){
            Droid newDroid = droid.selectDroid(droids);
            Team1.add(newDroid);
            j++;
        }
    }


}
