import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class Weather {
    private static Document getPage(String url) throws IOException {
        Document page = Jsoup.parse(new URL(url), 5000);
        return page;
    }

    public static void main(String[] args) throws IOException {
        String url1 = "https://ua.sinoptik.ua/%D0%BF%D0%BE%D0%B3%D0%BE%D0%B4%D0%B0-%D0%BA%D0%BE%D0%B7%D0%BE%D0%B2%D0%B0";
        Document page1 = getPage(url1);
        Elements min1 = page1.select("div[class=min]").select("span");
        Elements max1 = page1.select("div[class=max]").select("span");
        List<String> min1St = Arrays.asList(min1.text().split(" "));
        List<String > max1St = Arrays.asList(max1.text().split(" "));
        Elements weath1Ico = page1.select("div[id=bd2]").select("div");
        String weath1 = weath1Ico.attr("title");

        String url2 = "https://www.gismeteo.ua/ua/weather-kozova-12246/";
        Document page2 = getPage(url2);
        Elements t = page2.select("span[class=unit unit_temperature_c]");
        List<String > tSt = Arrays.asList(t.text().split(" "));
        Elements weath2Ico = page2.select("a[href=/ua/weather-kozova-12246/tomorrow/]");
        String weath2 = weath2Ico.attr("data-text");

        System.out.println("Weather for tomorrow");
        System.out.println("Sinoptik: "+min1St.get(1)+"  "+max1St.get(1)+"  "+weath1);
        System.out.println("Gismeteo: "+tSt.get(4)+"  "+tSt.get(5)+"  "+weath2);
    }
}
