package edu.au.cpsc.part2.sampledata;

import edu.au.cpsc.part2.data.Db;
import edu.au.cpsc.part2.model.ScheduledFlight;

import java.time.LocalTime;
import java.util.HashSet;

public class FlightGenerator {
    public static void main(String[] args) {
        LocalTime time = LocalTime.of(13, 30, 0);
        HashSet<String> days = new HashSet<>();
        days.add("Monday");
        days.add("Tuesday");
        ScheduledFlight f1 = new ScheduledFlight("PI22", "PITT",
                time, "LATT",time,days);
        ScheduledFlight f2 = new ScheduledFlight("3333", "PIT2",
                time, "LRTR",time,days);
        Db.getDatabase().addScheduledFlight(f1);
        Db.getDatabase().addScheduledFlight(f2);
        Db.saveDatabase();
    }
}
