import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Teest {

    public static void main(String[] args) {

        int page = 0;

        while (true) {
            double time = System.currentTimeMillis();
            try {
                URL url = new URL("https://api.hypixel.net/v2/skyblock/auctions?page=" + page);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type", "application/json");

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();

                Gson gson = new Gson();
                Page page1 = gson.fromJson(response.toString(), Page.class);


                for (Auction ac : page1.getAuctions()) {
                    boolean con = false;
                    if (!ac.isBin()) continue;
                    if (ac.getItemName().contains("Crimson")) {
                        String has = getValue("Magic Find", ac.getItemLore());
                        if (has.isEmpty()) continue;
                        String veteran = getValue("Veteran", ac.getItemLore());
                        if (veteran.isEmpty()) continue;
                        ac.setAt1(has);
                        ac.setAt2(veteran);
                        con = true;
                        if (ac.getStartingBid() > 100000000) continue;
                    }

                    if (!con) continue;


                    MessageBuilder webhook = new MessageBuilder("https://discord.com/api/webhooks/1166100349984178266/JUY8nT0KaBgHJ0yxB9-Yj7M6AmKfN3waPtSzDTaP9iAPBeB65mhUSBjjuWAR_Y5wqQym");

                    webhook.setUsername("MyBotName");
                    webhook.setContent("Ah items");
                    webhook.setTts(false);

                    webhook.addEmbed((new MessageBuilder.EmbedObject()).setTitle(ac.getItemName())
                            .addField("Auctioner", "`" + getUsername(ac.getAuctioneer()) + "`", false)
                            .addField("Starting bid", "`" + ac.getStartingBid() + "`", false)
                            .addField("Attribute 1", "`" + ac.getAt1() + "`", false)
                            .addField("Attribute 2", "`" + ac.getAt2() + "`", false)

                            .setColor(Color.PINK));


                    webhook.execute();
                }


                // Cierra la conexión
                connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
            page++;
            double fi = System.currentTimeMillis();

            double diferenciaEnSegundos = (fi - time) / 1000;
            System.out.println(diferenciaEnSegundos + " - "  + page);

        }


    }

    public static String getValue(String keyword, String text) {
        Pattern pattern = Pattern.compile("§[a-f0-9]" + keyword + " ([IVXLCDMivxlcdm0-9/]+)");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }

    public static String getUsername(String uuid) throws IOException {

        URL url = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        if (!response.toString().isEmpty()) {
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(response.toString()).getAsJsonObject();
            if (jsonObject.has("name")) {
                return jsonObject.get("name").getAsString();
            }
        }
        return uuid;
    }
}