package model;

/**
 *
 */
public class TimeCalculations {
        TimeList<Time> timesToAdd;

        //total is a global variable since both methods utilize the information in total
        Time total = new Time();

     public Time toAdd(TimeList<Time> timesToAdd) {

         //loop through each time and add the seconds, minutes, and hours to each
         while(timesToAdd.head != null) {
             total.setSeconds(total.getSeconds() + timesToAdd.head.data.getSeconds());
             total.setMinutes(total.getMinutes() + timesToAdd.head.data.getMinutes());
             total.setHours(total.getHours() + timesToAdd.head.data.getHours());
         }

         //convert the seconds and minutes
         total = convertTime(total.getMinutes(), total.getSeconds(), true);
         total = convertTime(total.getHours(), total.getMinutes(), false);


         return total;
     }

    /**
     * This method can be used to convert seconds and minutes since there are 60 seconds in a minute and 60 minutes in an hour
     * if converting seconds, the large will be minutes and the small will be seconds
     * if converting minutes, the large will be hours and the small will be minutes
     *
     * since there are only two possible scenarios, a boolean would make the most sense to clarify whether seconds or minutes will be reassigned
      * @param large
     * @return
     */
    protected Time convertTime(int large, int small, boolean convertSeconds) {
            int carry;
            if(small < 60) {
                return total;
            } else {
                //this will determine what needs to be carried over to large (ie. 120 seconds --> 120 / 60 = 2 for 2 minutes)
                carry = small / 60;

                //the remainder is what will be the small value;
                small = small % 60;

                //add the carry to the large
                large += carry;

                //set minutes and seconds if true
                if(convertSeconds) {
                    total.setSeconds(small);
                    total.setMinutes(large);
                }
                //set hours and minutes if false
                else {
                    total.setMinutes(small);
                    total.setHours(large);
                }

            }
    }
}
