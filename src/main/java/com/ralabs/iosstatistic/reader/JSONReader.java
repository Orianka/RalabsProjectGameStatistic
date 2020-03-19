package com.ralabs.iosstatistic.reader;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ralabs.iosstatistic.DAO.GameDAO;
import com.ralabs.iosstatistic.domain.Game;
import com.ralabs.iosstatistic.domain.GameType;
import org.json.JSONException;

import javax.transaction.Transactional;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDate;

public class JSONReader {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static String readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            return jsonText;
        } finally {
            is.close();
        }
    }
    @Transactional
    public String[] getGames(String url, GameType typeOfGame) throws IOException, JSONException {
        String json = String.valueOf(readJsonFromUrl(url));
        String getOnlyGames = json.substring(json.lastIndexOf("results"));
        getOnlyGames = getOnlyGames.replace("results\":[", "");
        String [] result = getOnlyGames.split("artistName");
        return result;
    }

    @Transactional
    public Game makeGameObjectFromString(String result, GameType gameType) throws IOException, JSONException {
        String addArtistName = "{\"artistName"+result;
        System.out.println("addArtistName " + addArtistName);
        String makeJsonString =  removeLastChar(addArtistName);
        System.out.println(makeJsonString);
        String stripped = makeJsonString.replaceAll("(?s)\"kind\".*?}],", "");
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(stripped).getAsJsonObject();
        String dateString = String.valueOf(jsonObject.get("releaseDate"));
        dateString = dateString.replaceAll("\"", "");
        LocalDate date = LocalDate.parse(dateString);
        Game game = new Game(jsonObject.get("artistName").toString(),
                jsonObject.get("name").toString(), jsonObject.get("url").toString(), date, gameType);
        return game;
    }

    public static String removeLastChar(String str) {
        int i = 0;
        String newS = str.substring(0, str.length() - 1);
        while(i < 2) {
            newS = newS.substring(0, newS.length() - 1);
            i++;
        }
        return newS;
    }
}
