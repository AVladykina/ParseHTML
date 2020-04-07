import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<String> imageUrl = new ArrayList<>();
    private static File htmlFile = new File("data/lentaMain.html");

    public static void main(String[] args) {

        Document doc = null;
        try {
            doc = Jsoup.parse(htmlFile, "UTF-8", "https://lenta.ru/");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements jpgs = doc.select("img[src$=.jpg]");
        jpgs.forEach(j -> {
            imageUrl.add(j.absUrl("src"));  //attr("src"));
        });
        downloadImg(imageUrl);

    }


    private static void downloadImg(List<String> imageUrl) {
        try {
            BufferedImage image;
            int i = 0;
            for (String img : imageUrl) {
                URL url = new URL(img);
                image = ImageIO.read(url);
                if (image != null){
                    ImageIO.write(image, "jpg",new File("/Users/nasenka/Desktop/FotoLentaRu/" + i + ".jpg"));
                    i++;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();;
        }
    }
}
