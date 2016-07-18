import java.util.Map;

/**
 * Created by Administrator on 2016/7/18.
 */
public class App {
    private static ProductList productlist = ProductList.Initial();    //商品固有信息
    private static Discount discount = Discount.Initial();             //打折的商品
    private static PurchaseList purchaseList = new PurchaseList();    //购物清单

    public static void main(String[] args) {
        InitialAll();
        DisplayPurchaseList();
    }

    public static void InitialAll() {
        //初始化商品固有信息
        productlist.AddProductInfo(new ProductInfo("ITEM000000", "可口可乐", "瓶", "食品", "碳酸饮料", 3.0));
        productlist.AddProductInfo(new ProductInfo("ITEM000001", "篮球", "个", "运动物品", "球类", 98.0));
        productlist.AddProductInfo(new ProductInfo("ITEM000002", "羽毛球", "个", "运动物品", "球类", 1.0));
        productlist.AddProductInfo(new ProductInfo("ITEM000003", "苹果", "斤", "食品", "水果", 5.5));
        productlist.AddProductInfo(new ProductInfo("ITEM000004", "白菜", "斤", "食品", "蔬菜", 5.0));
        productlist.AddProductInfo(new ProductInfo("ITEM000005", "牙膏", "盒", "日用品", "洗漱用品", 3.0));

        //初始化打折商品列表
        discount.AddDiscountItem("ITEM000000");
        discount.AddDiscountItem("ITEM000001");
        discount.AddDiscountItem("ITEM000002");
        discount.AddGiveItem("ITEM000003");
        discount.AddGiveItem("ITEM000004");
        discount.AddGiveItem("ITEM000005");

        //初始化购物清单
        purchaseList.AddPurchaseListItem("ITEM000001");
        purchaseList.AddPurchaseListItem("ITEM000001");
        purchaseList.AddPurchaseListItem("ITEM000001");
        purchaseList.AddPurchaseListItem("ITEM000002-2");
        purchaseList.AddPurchaseListItem("ITEM000003");
        purchaseList.AddPurchaseListItem("ITEM000004-2");
        purchaseList.AddPurchaseListItem("ITEM000004-4");
        purchaseList.AddPurchaseListItem("ITEM000005-3");
    }

    public static void DisplayPurchaseList() {
        purchaseList.ParsePurchaseList();
        Map<String, PurchaseItem> purchase_list = purchaseList.getPurchaseList();

        System.out.println("***<没钱赚商店>购物清单***");
        double totalprice = 0;
        double totaldiscountprice = 0;
        for (PurchaseItem iteminfo : purchase_list.values()) {               //输出各条商品的信息
            PromotionType type = discount.IsPromotion(iteminfo.getIteminfo().getBarcode());
            System.out.print("名称：" + iteminfo.getIteminfo().getName() +
                    "，数量：" + iteminfo.getItemnum() + iteminfo.getIteminfo().getUnit() +
                    "，单价：" + iteminfo.getIteminfo().getPrice() + "（元）" +
                    "，小计：" + (iteminfo.getRealprice() - iteminfo.getDiscountprice()) + "（元）");
            if (type == PromotionType.DISCOUNT){
                if (iteminfo.getDiscountprice() != 0)
                    System.out.print("，优惠：" + iteminfo.getDiscountprice() + "（元）");
            }
            System.out.println();
            totalprice += iteminfo.getRealprice() - iteminfo.getDiscountprice();
            totaldiscountprice += iteminfo.getDiscountprice();
        }

        System.out.println("------------------------");
        if (purchaseList.HasBuyTwoGiveOneType()) {                        //输出买二赠一商品
            System.out.println("买二赠一商品：");
            for (PurchaseItem item : purchase_list.values()) {
                PromotionType type = discount.IsPromotion(item.getIteminfo().getBarcode());
                if (type == PromotionType.BUYTWOGIVEONE) {
                    if ((item.getItemnum() / 3) != 0) {
                        System.out.println("名称：" + item.getIteminfo().getName() +
                                "，数量：" + (int) (item.getItemnum() / 3) + item.getIteminfo().getUnit());
                    }
                }
            }
            System.out.println("------------------------");
        }
        System.out.print("总计：" + totalprice + "（元）");
        if (totaldiscountprice != 0) {
            System.out.println("，优惠：" + totaldiscountprice + "（元）");
        }
    }
}
