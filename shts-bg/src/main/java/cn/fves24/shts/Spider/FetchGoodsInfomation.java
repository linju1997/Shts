package cn.fves24.shts.Spider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.net.URL;

/**
 * 抓取闲鱼商品信息，并转化为SQL
 */
public class FetchGoodsInfomation {

    public static void main(String[] args) {
        String[] ids = new String[]{"593948514966",
                "593554652638",
                "593948222326",
                "593947418999",
                "594120015353",
                "593948090040",
                "593490567186",
                "593948142666",
                "593947874084",
                "594120963430",
                "593555708714",
                "593777873975",
                "594118655724",
                "594120399900",
                "594119295394",
                "593948002667",
                "593555000245",
                "593948918416",
                "593553908535",
                "594119603798",
                "593948774148",
                "594119383606",
                "593778045416",
                "593778225779",
                "593555284750",
                "593554704049",
                "594119199587",
                "593948034490",
                "593778145858",
                "593778757033",
                "593777969368",
                "593948462068",
                "593777681253",
                "593948058240",
                "593948754131",
                "593948230335",
                "594118807706",
                "593778129914",
                "593948290179",
                "594120519416",
        };
        for (String id : ids) {
            try {
                URL url = new URL("https://2.taobao.com/item.htm?id={id}&fmdirect=true".replace("{id}", id));
                Document parse = Jsoup.parse(url, 3000);
                Element jProperty = parse.getElementById("J_Property");
                String title = jProperty.child(0).text();
                String price = jProperty.child(1).child(0).child(1).text().substring(1);
                Element jSlider = parse.getElementById("J_Slider");
                String img = "http:" + jSlider.child(0).child(0).child(0).child(0).attr("lazyload-img");
                Element descEle = parse.getElementById("desc-intro");
                String descUrl = "http:" + descEle.child(1).attr("data-url");
                Document descText = Jsoup.parse(new URL(descUrl), 3000);
                String desc = descText.text().replace("\\", "").replace("';", "").substring(10);
                String sql = "INSERT INTO `shts`.`goods`(`user_id`, `title`, `desc`, `type`, `price`, `quantity`, `img`, `status`, `create_time`, `sale_time`)" +
                        "VALUES" +
                        "(10033,'{title}', '{desc}', '其他', {price}, 1, '{img}', 0, '2019-05-06 00:00:00', NULL);"
                                .replace("{title}", title)
                                .replace("{title}", title)
                                .replace("{desc}", desc)
                                .replace("{price}", price)
                                .replace("{img}", img);
                System.out.println(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
