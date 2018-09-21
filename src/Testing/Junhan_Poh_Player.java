class Junhan_Poh_Player extends Player {
        //State of Game 
        //State 3 = Full Collusion
        //State 2 = Partial Collusion
        //State 1 = Self Interested
    
    //TESTING HI ZHONGHAN

        int state = 3; //default state = Full Collusion 
        int interval = 2; 
        
        //Identify If Opponent is my own agent 
        //Default is true
        boolean opponent1isMe = true;
        boolean opponent2isMe = true;

        int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
            
            //Start with being nice 
            if (n < 1) {
                return 0;
            } else {
                
                //Check if there is a change in State in the Game
                if (state == 3) {
                    //if any opponent defect first drop to Partial Collusion state
                    //that opponent is not my own agent
                    if (oppHistory1[n - 1] == 1) {
                        opponent1isMe = false;
                        state = 2;
                    }
                    if (oppHistory2[n - 1] == 1) {
                        opponent2isMe = false;
                        state = 2;
                    }
                    
                    //If both opponents is not my own agent drop to Self Interest State
                    if(!opponent1isMe && !opponent2isMe)
                        state = 1;
                    
                    
                    
                } else if (state == 2) {
                    //check to make sure colluding agent behaves as expected based on identification signal
                    int ointervalL1 = (n - 1) / interval;
                    int ointervalL2 = ointervalL1 / interval;
                    int ointervalL3 = ointervalL2 / interval;
                    int ointervalL4 = ointervalL3 / interval;
                    
                    // check if colluding agent returns expected value else drop to self interest state
                    if(opponent1isMe && oppHistory1[n - 1] == (ointervalL1 + (ointervalL2 + (ointervalL3 + ointervalL4 % 2) % 2) % 2) % 2) {
                        opponent1isMe = false;
                        state = 1;
                    }
                    if(opponent2isMe && oppHistory2[n - 1] == (ointervalL1 + (ointervalL2 + (ointervalL3 + (ointervalL4 + 1) % 2) % 2) % 2) % 2) {
                        opponent2isMe = false;
                        state = 1;
                    }
                }
                
                //if in Full Collusion State return 0;
                if (state == 3) {
                    return 0;
                    
                }
                //if in Self interested State retuns 1 ; 
                else if (state == 1) {
                    return 1;
                } else {
                    // if in Partial Collusion state, returns value based on identification signal
                    int intervalL1 = n / interval;
                    int intervalL2 = intervalL1 / interval;
                    int intervalL3 = intervalL2 / interval;
                    int intervalL4 = intervalL3 / interval;
                    
                    
                    if (opponent1isMe) {
                        return (intervalL1 + (intervalL2 + (intervalL3 + intervalL4 % 2) % 2) % 2) % 2;
                    } else {
                        return (intervalL1 + (intervalL2 + (intervalL3 + (intervalL4 + 1) % 2) % 2) % 2) % 2;
                    }
                }
            }

        }
    }