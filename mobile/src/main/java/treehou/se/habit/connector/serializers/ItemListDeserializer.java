package treehou.se.habit.connector.serializers;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import treehou.se.habit.core.db.ItemDB;

public class ItemListDeserializer implements JsonDeserializer<List<ItemDB>> {

    private static final String TAG = "ItemDeserializer";

    @Override
    public List<ItemDB> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context){

        List<ItemDB> itemList = new ArrayList<>();

        if (json.isJsonArray()) {
            JsonArray jsonArray = json.getAsJsonArray();
            for (JsonElement e : jsonArray) {
                ItemDB item = context.deserialize(e, ItemDB.class);
                itemList.add(item);
            }
        }
        else if (json.isJsonObject()) {
            JsonObject jsonObject = json.getAsJsonObject();
            if (jsonObject.has("item")) {
                return context.deserialize(jsonObject.get("item"), typeOfT);
            }
        }

        return itemList;
    }
}

