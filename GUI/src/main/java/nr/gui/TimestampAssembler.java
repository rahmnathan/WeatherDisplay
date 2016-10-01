package nr.gui;

import java.time.LocalDateTime;

class TimestampAssembler {

    private final String[] dateTime = LocalDateTime.now().toString().split("T");

    String getDate(){
        String[] dateArray = dateTime[0].split("-");
        return dateArray[1] + "-" + dateArray[2] + "-" + dateArray[0];
    }

    String getTime(){
        String[] timeStampArray = dateTime[1].split(":");
        Integer hour = Integer.valueOf(timeStampArray[0]);
        if(hour > 12) {
            hour -= 12;
        }
        if(hour == 0){
            hour += 12;
        }
        String hourString = String.valueOf(hour);
        return hourString + ":" + timeStampArray[1];
    }
}
