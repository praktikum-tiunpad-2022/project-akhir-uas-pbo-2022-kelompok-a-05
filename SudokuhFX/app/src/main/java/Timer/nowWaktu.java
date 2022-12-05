package Timer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class nowWaktu {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:MM:SS");
    LocalDateTime sekarang = LocalDateTime.now();
    
    public String currentTime(){
        return dtf.format(sekarang);
    }
}

