package media.uqab.amalchecklist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "amal_table")
public class Amal implements Serializable {

    @PrimaryKey @NonNull
    public String amalID;

    public String label;
    public AmalType type;

    public History history = new History();

    public Amal(String label, AmalType type) {
        this.label = label;
        this.type = type;
        this.amalID = IdGenerator.getNewID();
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;
        Amal other = ((Amal) obj);

        return this.amalID.equals(other.amalID)
                && this.label.equals(other.label)
                && this.type.equals(other.type)
                && this.history.equals(other.history);
    }
}
