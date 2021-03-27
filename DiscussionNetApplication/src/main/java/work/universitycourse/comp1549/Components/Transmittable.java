package work.universitycourse.comp1549.Components;

import java.io.Serializable;
import java.sql.Timestamp;

public abstract class Transmittable implements Serializable{
    

    private static final long serialVersionUID = 1L;
    public String sender, receiver;
    public Timestamp timestamp;

    // public abstract String toString();

    // public abstract <T> fromString(String formattedString);

}
