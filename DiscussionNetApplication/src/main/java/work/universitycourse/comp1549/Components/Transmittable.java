package work.universitycourse.comp1549.Components;

import java.io.Serializable;
import java.sql.Timestamp;

// Used as an object that can be passed between socket connections
public abstract class Transmittable implements Serializable{
    
    private static final long serialVersionUID = 1L;
    public String sender, receiver;
    public Timestamp timestamp;

}
