package com.example.Cricket_Counter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/*
    * In the entire comments TeamA implies the team batting first and TeamB implies the team batting second.
 */

public class MainActivity extends AppCompatActivity {

    int runsTeamA = 0,
            wicketTeamA = 0,
            runsTeamB = 0,
            wicketTeamB = 0;

//It is used to hold the value of CSK radio button.
    boolean checkA = false,

//It is used to hold value of MI radio button.
            checkB = false,

//It is used to check if TeamA's batting is finished or not.(True => TeamA's batting is finished.)
            set = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//This method handles Radio Button usage.
    public void onRadioButtonClicked(View view) {
        RadioButton btnA = (RadioButton) findViewById(R.id.aradio);
        RadioButton btnB = (RadioButton) findViewById(R.id.bradio);

// To check if Radio button is clicked?
        boolean checked = ((RadioButton) view).isChecked();

//To check which button was clicked
        switch (view.getId()) {
            case R.id.aradio :
                if (checked) {
                    checkA = true;
//Because Toss is done only one time, so other button is disabled to prevent Double Toss.
                    btnB.setEnabled(false);
                }
                break;

            case R.id.bradio :
                if (checked) {
                    checkB = true;
                    btnA.setEnabled(false);
                }
                break;
        }
    }

//This method displays score of Team CSK.
    public void displayForTeamA(int runA, int wicketA) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(runA + "/" + wicketA);
    }

//This method displays score of Team MI.
    public void displayForTeamB(int runB, int wicketB) {
        TextView scoreView1 = (TextView) findViewById(R.id.team_b_score);
        scoreView1.setText(runB + "/" + wicketB);
    }

//This method displays the intermediate score as well as the final result of the match.
    public void displayResult() {
        TextView textView = (TextView) findViewById(R.id.result);

//This condition checks if CSK batted first and if so displays the total score and also displays the target score for the opponent.
        if (checkA && !set)
            textView.setText("CSK : " + runsTeamA + "/" + wicketTeamA + "\nMI needs " + (runsTeamA + 1) + " runs to win.");
//This condition checks if MI batted first and if so displays the total score and also displays the target score for the opponent.
        else if (checkB && !set)
            textView.setText("MI : " + runsTeamB + "/" + wicketTeamB + "\nCSK needs " + (runsTeamB + 1) + " runs to win.");
//This condition checks if CSK won?
        else if (runsTeamA > runsTeamB) {
            if (checkB)
                textView.setText("Result : CSK won by " + (runsTeamA - runsTeamB) + " runs.\nCongrats!!");
            else
                textView.setText("Result : CSK won with " + (10 - wicketTeamA) + " wickets remaining.\nCongrats!!");
//This condition checks if MI won?
        } else if (runsTeamB > runsTeamA) {
            if (checkA)
                textView.setText("Result : MI won by " + (runsTeamB - runsTeamA) + " runs.\nCongrats!!");
            else
                textView.setText("Result : MI won with " + (10 - wicketTeamB) + " wickets remaining.\nCongrats!!");
//This condition checks for the possibility of a draw.
        } else if (runsTeamA == runsTeamB)
            textView.setText("Result : The match resulted in a draw!!");

    }

/*
    *This method handles the various 'SINGLE' button click scenarios.
 */
    public void addOneForTeamA(View view) {
//This condition locks this CSK button if MI is batting and alerts the user about it.
        if (checkB) {
            Toast.makeText(this, "MI is Batting now!", Toast.LENGTH_SHORT).show();
            return;
        }
//This condition locks this button until Toss is completed and batting team is selected.
        else if (!checkA && !checkB && !set) {
            Toast.makeText(this, "Please Complete Toss First!", Toast.LENGTH_SHORT).show();
            return;
        }
//This condition locks this button after the match is finished and alert the user to Reset for new match.
        else if (!checkA && !checkB && set) {
            Toast.makeText(this, "Match Over.Reset for New Match!", Toast.LENGTH_SHORT).show();
            return;
        }
/*
    *This condition locks this button when the team is bowled out, so that the score cannot increase with no-one left to bat.
    *And also alerts the user about it.
*/
        else if (wicketTeamA == 10) {
            Toast.makeText(this, "ALL OUT!!Please click END BATTING", Toast.LENGTH_SHORT).show();
            return;
        }
/*
    *If TeamA has finished batting and teamB has won by crossing the target then this condition prevents further increase in score of TeamB, as it does not happen in a cricket match.
    * And it also alerts the user about it.
    * else it normally increases CSK score by 1 run and displays it immediately.
*/
        else {
            if (runsTeamA > runsTeamB && set) {
                Toast.makeText(this, "You Won!Click on End Batting for Result", Toast.LENGTH_SHORT).show();
                return;
            }
            else {
                runsTeamA += 1;
                displayForTeamA(runsTeamA, wicketTeamA);
            }
        }
    }

/*
    *This method handles the various 'DOUBLE' button click scenarios just like mentioned in the above 'SINGLE' button method.
 */
    public void addTwoForTeamA(View view) {
        if (checkB) {
            Toast.makeText(this, "MI is Batting now!", Toast.LENGTH_SHORT).show();
            return;
        } else if (!checkA && !checkB && !set) {
            Toast.makeText(this, "Please Complete Toss First!", Toast.LENGTH_SHORT).show();
            return;
        } else if (!checkA && !checkB && set) {
            Toast.makeText(this, "Match Over.Reset for New Match!", Toast.LENGTH_SHORT).show();
            return;
        } else if (wicketTeamA == 10) {
            Toast.makeText(this, "ALL OUT!!Please click END BATTING", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (runsTeamA > runsTeamB && set) {
                Toast.makeText(this, "You Won!Click on End Batting for Result", Toast.LENGTH_SHORT).show();
                return;
            } else {
                runsTeamA += 2;
                displayForTeamA(runsTeamA, wicketTeamA);
            }
        }
    }

/*
    *This method handles the various '3 RUNS' button click scenarios just like mentioned in the above 'SINGLE' button method.
 */
    public void addThreeForTeamA(View view) {
        if (checkB) {
            Toast.makeText(this, "MI is Batting now!", Toast.LENGTH_SHORT).show();
            return;
        } else if (!checkA && !checkB && !set) {
            Toast.makeText(this, "Please Complete Toss First!", Toast.LENGTH_SHORT).show();
            return;
        } else if (!checkA && !checkB && set) {
            Toast.makeText(this, "Match Over.Reset for New Match!", Toast.LENGTH_SHORT).show();
            return;
        } else if (wicketTeamA == 10) {
            Toast.makeText(this, "ALL OUT!!Please click END BATTING", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (runsTeamA > runsTeamB && set) {
                Toast.makeText(this, "You Won!Click on End Batting for Result", Toast.LENGTH_SHORT).show();
                return;
            } else {
                runsTeamA += 3;
                displayForTeamA(runsTeamA, wicketTeamA);
            }
        }
    }

/*
 *This method handles the various 'FOUR' button click scenarios just like mentioned in the above 'SINGLE' button method.
 */
    public void addFourForTeamA(View view) {
        if (checkB) {
            Toast.makeText(this, "MI is Batting now!", Toast.LENGTH_SHORT).show();
            return;
        } else if (!checkA && !checkB && !set) {
            Toast.makeText(this, "Please Complete Toss First!", Toast.LENGTH_SHORT).show();
            return;
        } else if (!checkA && !checkB && set) {
            Toast.makeText(this, "Match Over.Reset for New Match!", Toast.LENGTH_SHORT).show();
            return;
        } else if (wicketTeamA == 10) {
            Toast.makeText(this, "ALL OUT!!Please click END BATTING", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (runsTeamA > runsTeamB && set) {
                Toast.makeText(this, "You Won!Click on End Batting for Result", Toast.LENGTH_SHORT).show();
                return;
            } else {
                runsTeamA += 4;
                displayForTeamA(runsTeamA, wicketTeamA);
            }
        }
    }

/*
 *This method handles the various 'SIX' button click scenarios just like mentioned in the above 'SINGLE' button method.
 */
    public void addSixForTeamA(View view) {
        if (checkB) {
            Toast.makeText(this, "MI is Batting now!", Toast.LENGTH_SHORT).show();
            return;
        } else if (!checkA && !checkB && !set) {
            Toast.makeText(this, "Please Complete Toss First!", Toast.LENGTH_SHORT).show();
            return;
        } else if (!checkA && !checkB && set) {
            Toast.makeText(this, "Match Over.Reset for New Match!", Toast.LENGTH_SHORT).show();
            return;
        } else if (wicketTeamA == 10) {
            Toast.makeText(this, "ALL OUT!!Please click END BATTING", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (runsTeamA > runsTeamB && set) {
                Toast.makeText(this, "You Won!Click on End Batting for Result", Toast.LENGTH_SHORT).show();
                return;
            } else {
                runsTeamA += 6;
                displayForTeamA(runsTeamA, wicketTeamA);
            }
        }
    }

/*
 *This method handles the various 'WICKET' button click scenarios just like mentioned in the above 'SINGLE' button method.
 */
    public void addWicketForTeamA(View view) {
        if (checkB) {
            Toast.makeText(this, "MI is Batting now!", Toast.LENGTH_SHORT).show();
            return;
        } else if (!checkA && !checkB && !set) {
            Toast.makeText(this, "Please Complete Toss First!", Toast.LENGTH_SHORT).show();
            return;
        } else if (!checkA && !checkB && set) {
            Toast.makeText(this, "Match Over.Reset for New Match!", Toast.LENGTH_SHORT).show();
            return;
        } else if (wicketTeamA == 10) {
            Toast.makeText(this, "ALL OUT!!Please click END BATTING", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (runsTeamA > runsTeamB && set) {
                Toast.makeText(this, "You Won!Click on End Batting for Result", Toast.LENGTH_SHORT).show();
                return;
            } else {
                wicketTeamA += 1;
                displayForTeamA(runsTeamA, wicketTeamA);
            }
        }
    }

/*
 *This method handles the various MI's 'SINGLE' button click scenarios just like mentioned in the above 'SINGLE' button method.
 */
    public void addOneForTeamB(View view) {
        if (checkA) {
            Toast.makeText(this, "CSK is Batting now!", Toast.LENGTH_SHORT).show();
            return;
        } else if (!checkA && !checkB && !set) {
            Toast.makeText(this, "Please Complete Toss First!", Toast.LENGTH_SHORT).show();
            return;
        } else if (!checkA && !checkB && set) {
            Toast.makeText(this, "Match Over.Reset for New Match!", Toast.LENGTH_SHORT).show();
            return;
        } else if (wicketTeamB == 10) {
            Toast.makeText(this, "ALL OUT!!Please click END BATTING", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (runsTeamB > runsTeamA && set) {
                Toast.makeText(this, "You Won!Click on End Batting for Result", Toast.LENGTH_SHORT).show();
                return;
            } else {
                runsTeamB += 1;
                displayForTeamB(runsTeamB, wicketTeamB);
            }
        }
    }

/*
 *This method handles the various MI's 'DOUBLE' button click scenarios just like mentioned in the above 'SINGLE' button method.
 */
    public void addTwoForTeamB(View view) {
        if (checkA) {
            Toast.makeText(this, "CSK is Batting now!", Toast.LENGTH_SHORT).show();
            return;
        } else if (!checkA && !checkB && !set) {
            Toast.makeText(this, "Please Complete Toss First!", Toast.LENGTH_SHORT).show();
            return;
        } else if (!checkA && !checkB && set) {
            Toast.makeText(this, "Match Over.Reset for New Match!", Toast.LENGTH_SHORT).show();
            return;
        } else if (wicketTeamB == 10) {
            Toast.makeText(this, "ALL OUT!!Please click END BATTING", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (runsTeamB > runsTeamA && set) {
                Toast.makeText(this, "You Won!Click on End Batting for Result", Toast.LENGTH_SHORT).show();
                return;
            } else {
                runsTeamB += 2;
                displayForTeamB(runsTeamB, wicketTeamB);
            }
        }
    }

/*
 *This method handles the various MI's '3 RUNS' button click scenarios just like mentioned in the above 'SINGLE' button method.
 */
    public void addThreeForTeamB(View view) {
        if (checkA) {
            Toast.makeText(this, "CSK is Batting now!", Toast.LENGTH_SHORT).show();
            return;
        } else if (!checkA && !checkB && !set) {
            Toast.makeText(this, "Please Complete Toss First!", Toast.LENGTH_SHORT).show();
            return;
        } else if (!checkA && !checkB && set) {
            Toast.makeText(this, "Match Over.Reset for New Match!", Toast.LENGTH_SHORT).show();
            return;
        } else if (wicketTeamB == 10) {
            Toast.makeText(this, "ALL OUT!!Please click END BATTING", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (runsTeamB > runsTeamA && set) {
                Toast.makeText(this, "You Won!Click on End Batting for Result", Toast.LENGTH_SHORT).show();
                return;
            } else {
                runsTeamB += 3;
                displayForTeamB(runsTeamB, wicketTeamB);
            }
        }
    }

/*
 *This method handles the various MI's 'FOUR' button click scenarios just like mentioned in the above 'SINGLE' button method.
 */
    public void addFourForTeamB(View view) {
        if (checkA) {
            Toast.makeText(this, "CSK is Batting now!", Toast.LENGTH_SHORT).show();
            return;
        } else if (!checkA && !checkB && !set) {
            Toast.makeText(this, "Please Complete Toss First!", Toast.LENGTH_SHORT).show();
            return;
        } else if (!checkA && !checkB && set) {
            Toast.makeText(this, "Match Over.Reset for New Match!", Toast.LENGTH_SHORT).show();
            return;
        } else if (wicketTeamB == 10) {
            Toast.makeText(this, "ALL OUT!!Please click END BATTING", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (runsTeamB > runsTeamA && set) {
                Toast.makeText(this, "You Won!Click on End Batting for Result", Toast.LENGTH_SHORT).show();
                return;
            } else {
                runsTeamB += 4;
                displayForTeamB(runsTeamB, wicketTeamB);
            }
        }
    }

/*
 *This method handles the various MI's 'SIX' button click scenarios just like mentioned in the above 'SINGLE' button method.
 */
    public void addSixForTeamB(View view) {
        if (checkA) {
            Toast.makeText(this, "CSK is Batting now!", Toast.LENGTH_SHORT).show();
            return;
        } else if (!checkA && !checkB && !set) {
            Toast.makeText(this, "Please Complete Toss First!", Toast.LENGTH_SHORT).show();
            return;
        } else if (!checkA && !checkB && set) {
            Toast.makeText(this, "Match Over.Reset for New Match!", Toast.LENGTH_SHORT).show();
            return;
        } else if (wicketTeamB == 10) {
            Toast.makeText(this, "ALL OUT!!Please click END BATTING", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (runsTeamB > runsTeamA && set) {
                Toast.makeText(this, "You Won!Click on End Batting for Result", Toast.LENGTH_SHORT).show();
                return;
            } else {
                runsTeamB += 6;
                displayForTeamB(runsTeamB, wicketTeamB);
            }
        }
    }

/*
 *This method handles the various MI's 'WICKET' button click scenarios just like mentioned in the above 'SINGLE' button method.
 */
    public void addWicketForTeamB(View view) {
        if (checkA) {
            Toast.makeText(this, "CSK is Batting now!", Toast.LENGTH_SHORT).show();
            return;
        } else if (!checkA && !checkB && !set) {
            Toast.makeText(this, "Please Complete Toss First!", Toast.LENGTH_SHORT).show();
            return;
        } else if (!checkA && !checkB && set) {
            Toast.makeText(this, "Match Over.Reset for New Match!", Toast.LENGTH_SHORT).show();
            return;
        } else if (wicketTeamB == 10) {
            Toast.makeText(this, "ALL OUT!!Please click END BATTING", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (runsTeamB > runsTeamA && set) {
                Toast.makeText(this, "You Won!Click on End Batting for Result", Toast.LENGTH_SHORT).show();
                return;
            } else {
                wicketTeamB += 1;
                displayForTeamB(runsTeamB, wicketTeamB);
            }
        }
    }

/*
 *This method handles the various CSK's 'END BATTING' button click scenarios just like mentioned in the above 'SINGLE' button method.
 */
    public void endTeamA(View view) {
        if (checkB) {
            Toast.makeText(this, "MI is Batting now!", Toast.LENGTH_SHORT).show();
            return;
        } else if (!checkA && !checkB && !set) {
            Toast.makeText(this, "Please Complete Toss First!", Toast.LENGTH_SHORT).show();
            return;
        } else if (!checkA && !checkB && set) {
            Toast.makeText(this, "Match Over.Reset for New Match!", Toast.LENGTH_SHORT).show();
            return;
        }
/*
    *This condition displays intermediate score and result of CSK.
    * Also makes selection of CSK as false to lock all CSK buttons.
    * Also it checks if MI has finished batting or not by checking value of 'set' variable, and if not it selects MI for batting.
    * Also it changes value of 'set' to true, to imply that TeamA has finished batting.
 */
        else {
            displayResult();
            checkA = false;
            if (!set)
                checkB = true;
            set = true;
        }
    }

/*
 *This method handles the various MI's 'END BATTING' button click scenarios just like mentioned in the above 'SINGLE' button method.
 */
    public void endTeamB(View view) {
        if (checkA) {
            Toast.makeText(this, "CSK is Batting now!", Toast.LENGTH_SHORT).show();
            return;
        } else if (!checkA && !checkB && !set) {
            Toast.makeText(this, "Please Complete Toss First!", Toast.LENGTH_SHORT).show();
            return;
        } else if (!checkA && !checkB && set) {
            Toast.makeText(this, "Match Over.Reset for New Match!", Toast.LENGTH_SHORT).show();
            return;
        }
/*
 *This condition displays intermediate score and result of MI.
 * Also makes selection of MI as false to lock all MI buttons.
 * Also it checks if CSK has finished batting or not by checking value of 'set' variable, and if not it selects CSK for batting.
 * Also it changes value of 'set' to true, to imply that TeamA has finished batting.
 */
        else {
            displayResult();
            if (!set)
                checkA = true;
            checkB = false;
            set = true;
        }
    }

/*
    *This method resets the app to the initial state.
 */
    public void reset(View view) {
        set = false;
        runsTeamA = 0;
        runsTeamB = 0;
        wicketTeamA = 0;
        wicketTeamB = 0;
        checkA = false;
        checkB = false;

        RadioButton btn1 = (RadioButton) findViewById(R.id.aradio);
        RadioButton btn2 = (RadioButton) findViewById(R.id.bradio);
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn1.setChecked(false);
        btn2.setChecked(false);
        TextView txt = (TextView) findViewById(R.id.result);
        TextView txt1 = (TextView) findViewById(R.id.team_b_score);
        TextView txt2 = (TextView) findViewById(R.id.team_a_score);
        txt.setText("");
        txt1.setText("0");
        txt2.setText("0");
    }
}

