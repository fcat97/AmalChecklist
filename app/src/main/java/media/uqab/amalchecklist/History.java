package media.uqab.amalchecklist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class History implements Serializable {
    private HashMap<Long, Boolean> history = new HashMap<>();

    public void commit(Calendar calendar, Boolean state) {
        long key = getKey(calendar);
        history.remove(key);
        history.put(key, state);
    }

    public Boolean getState(Calendar calendar) {
        if (! history.containsKey(getKey(calendar))) return false;
        else return history.get(getKey(calendar));
    }

    private Long getKey(Calendar calendar) {
        Calendar c = Calendar.getInstance();
        c.clear();

        c.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));

        return c.getTimeInMillis();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder st = new StringBuilder();
        st.append("{"); // {
        List<Long> keyList = new ArrayList<>(history.keySet());
        for (Long key: keyList) {
            st.append("\""); // {"
            st.append(key); // {"20210324
            st.append("\""); // {"20210324"
            st.append(": "); // {"20210324":
            st.append(this.history.get(key));
            st.append(",");
        }
        st.deleteCharAt(st.length() - 1);
        st.append("}");
        return st.toString();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;
        History other = ((History) obj);
        return this.toString().equals(other.toString());
    }
}
