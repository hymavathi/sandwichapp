package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static final String TAG = "SANDWICH-TAG";

    public static Sandwich parseSandwichJson(String json) {
        if (json == null) {
            return null;
        }
        try {
            JSONObject jsonObj = new JSONObject(json);

            JSONObject name = jsonObj.getJSONObject("name");
            String mainName = name.getString("mainName");
            JSONArray alsoKnownAs = name.getJSONArray("alsoKnownAs");
            String placeOfOrigin = jsonObj.getString("placeOfOrigin");
            String description = jsonObj.getString("description");
            String image = jsonObj.getString("image");
            JSONArray ingredients = jsonObj.getJSONArray("ingredients");
            // Constructing Sandwich
            Sandwich sandwich = new Sandwich(mainName, stringListFromJsonArray(alsoKnownAs), placeOfOrigin, description, image, stringListFromJsonArray(ingredients));
            return sandwich;
        } catch (JSONException ex) {

            Log.e(TAG, "parseSandwichJson: ", ex);
        }
        return null;
    }

    public static List<String> stringListFromJsonArray(JSONArray array) {

        if (array == null) {
            return null;
        }

        List<String> strList = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            strList.add(array.optString(i));
        }

        return strList;


    }
}
