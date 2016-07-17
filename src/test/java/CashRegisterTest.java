import java.math.BigDecimal;
import java.util.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Administrator on 2016/7/3.
 */
public class CashRegisterTest {
    public void InitializeProductList()
    {
        ProductList productlist = ProductList.Initial();
        productlist.AddProductInfo(new ProductInfo("ITEM000000", "可口可乐", "瓶", "食品", "碳酸饮料", 3.0));
        productlist.AddProductInfo(new ProductInfo("ITEM000001", "篮球", "个", "运动物品", "球类", 98.0));
        productlist.AddProductInfo(new ProductInfo("ITEM000002", "羽毛球", "个", "运动物品", "球类", 1.0));
        productlist.AddProductInfo(new ProductInfo("ITEM000003", "苹果", "斤", "食品", "水果", 5.5));
        productlist.AddProductInfo(new ProductInfo("ITEM000004", "白菜", "斤", "食品", "蔬菜", 5.0));
        productlist.AddProductInfo(new ProductInfo("ITEM000005", "牙膏", "盒", "日用品", "洗漱用品", 3.0));
    }

    //测试ProductList类
    @org.junit.Test
    public void should_return_ProductList() {
        ProductList list = ProductList.Initial();
        list.AddProductInfo(new ProductInfo("ITEM000000", "可口可乐", "瓶", "食品", "碳酸饮料", 3.0));
        list.AddProductInfo(new ProductInfo("ITEM000001", "篮球", "个", "运动物品", "球类", 98.0));
        list.AddProductInfo(new ProductInfo("ITEM000002", "羽毛球", "个", "运动物品", "球类", 1.0));
        list.AddProductInfo(new ProductInfo("ITEM000003", "苹果", "斤", "食品", "水果", 5.5));
        list.AddProductInfo(new ProductInfo("ITEM000004", "白菜", "斤", "食品", "蔬菜", 5.0));
        list.AddProductInfo(new ProductInfo("ITEM000005", "牙膏", "盒", "日用品", "洗漱用品", 3.0));
        ProductInfo one_item_info = list.getProductItemInfo("ITEM000005");
        assertTrue(one_item_info.getName().equals("牙膏"));
    }

    //测试PurchaseList类
    @org.junit.Test
    public void should_return_purchaselist() {
        InitializeProductList();
        PurchaseList list = new PurchaseList();
        list.AddPurchaseListItem("ITEM000001");
        list.AddPurchaseListItem("ITEM000001");
        list.AddPurchaseListItem("ITEM000002-2");
        list.AddPurchaseListItem("ITEM000005-3");
        list.ParsePurchaseList();
        Map<String, PurchaseItem> returnlist = list.getPurchaseList();
        PurchaseItem item = returnlist.get("ITEM000001");
        //assertTrue(item.getIteminfo().getName().equals("篮球"));
        //assertEquals(item.getItemnum(), 2);
        assertEquals(returnlist.get("ITEM000005").getItemnum(), 3);
    }

    //测试Discount
    @org.junit.Test
    public void should_add_discount_AddDiscountItem() {
        Discount dis = Discount.Initial();
        dis.AddDiscountItem("ITEM000001");
        dis.AddDiscountItem("ITEM000002");
        dis.AddGiveItem("ITEM000001");
        PromotionType type = dis.IsPromotion("ITEM000001");
        assertEquals(type, PromotionType.BUYTWOGIVEONE);
        /*PromotionType notype = dis.IsPromotion("ITEM000005");
        assertEquals(notype, PromotionType.NOPROMOTION);*/
    }

    @org.junit.Test
    public void should_add_give_AddGiveItem() {
        Discount dis = Discount.Initial();
        dis.AddGiveItem("ITEM000001");
        dis.AddGiveItem("ITEM000002");
        dis.AddDiscountItem("ITEM000001");
        PromotionType type = dis.IsPromotion("ITEM000001");
        assertEquals(type, PromotionType.BUYTWOGIVEONE);
    }

    @org.junit.Test
    public void should_calculate_price_CalculatePrice() {
        InitializeProductList();
        Discount dis = Discount.Initial();
        dis.AddGiveItem("ITEM000001");
        PurchaseList list = new PurchaseList();
        list.AddPurchaseListItem("ITEM000001");
        list.AddPurchaseListItem("ITEM000001");
        list.AddPurchaseListItem("ITEM000001");
        list.AddPurchaseListItem("ITEM000002-2");
        list.AddPurchaseListItem("ITEM000005-3");
        list.ParsePurchaseList();
        Map<String, PurchaseItem> returnlist = list.getPurchaseList();
        PurchaseItem item = returnlist.get("ITEM000001");
        assertEquals(item.getDiscountprice(), 98, 0.1);
    }
}
