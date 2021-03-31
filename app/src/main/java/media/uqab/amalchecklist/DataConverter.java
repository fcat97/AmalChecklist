package media.uqab.amalchecklist;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class DataConverter {
    @TypeConverter
    public String fromHistory(History history) {
        if (history == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<History>(){}.getType();
        return gson.toJson(history,type);
    }

    @TypeConverter
    public History toHistory(String json) {
        if (json == null) return null;

        Gson gson = new Gson();
        Type type = new TypeToken<History>(){}.getType();
        return gson.fromJson(json, type);
    }

    @TypeConverter
    public String fromType(AmalType amalType) {
        if (amalType == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<AmalType>(){}.getType();
        return gson.toJson(amalType,type);
    }

    @TypeConverter
    public AmalType toType(String json) {
        if (json == null) return null;

        Gson gson = new Gson();
        Type type = new TypeToken<AmalType>(){}.getType();
        return gson.fromJson(json, type);
    }
}
