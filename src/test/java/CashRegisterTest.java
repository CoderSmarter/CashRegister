import java.util.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.*;

/**
 * Created by Administrator on 2016/7/3.
 */
public class CashRegisterTest {
    @BeforeClass
    public static void InitializeProductList()
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
    @Test
    public void test_ProductList() {
        ProductList list = ProductList.Initial();
        ProductInfo one_item_info = list.getProductItemInfo("ITEM000005");
        assertTrue(one_item_info.getName().equals("牙膏"));
    }

    //测试PurchaseList类
    @Test
    public void test_purchaselist() {
        PurchaseList list = new PurchaseList();
        Discount dis = Discount.Initial();
        dis.ClearDiscount();
        list.AddPurchaseListItem("ITEM000001");
        list.AddPurchaseListItem("ITEM000001");
        list.AddPurchaseListItem("ITEM000001");
        list.AddPurchaseListItem("ITEM000002-2");
        list.AddPurchaseListItem("ITEM000005-3");
        list.ParsePurchaseList();
        Map<String, PurchaseItem> returnlist = list.getPurchaseList();
        PurchaseItem item = returnlist.get("ITEM000001");
        assertTrue(item.getIteminfo().getName().equals("篮球"));
        assertEquals(item.getItemnum(), 3);
        assertEquals(item.getRealprice(), 294, 0.1);
        assertEquals(item.getDiscountprice(), 0, 0.1);
        assertEquals(returnlist.get("ITEM000005").getItemnum(), 3);
    }

    //测试Discount的添加折扣商品函数
    @Test
    public void AddDiscountItem_and_AddGiveItem() {
        Discount dis = Discount.Initial();
        dis.ClearDiscount();
        dis.AddDiscountItem("ITEM000001");
        dis.AddDiscountItem("ITEM000002");
        dis.AddGiveItem("ITEM000001");
        dis.AddGiveItem("ITEM000003");
        PromotionType type1 = dis.IsPromotion("ITEM000001");
        assertEquals(type1, PromotionType.BUYTWOGIVEONE);
        PromotionType type2 = dis.IsPromotion("ITEM000002");
        assertEquals(type2, PromotionType.DISCOUNT);
        PromotionType type3 = dis.IsPromotion("ITEM000003");
        assertEquals(type3, PromotionType.BUYTWOGIVEONE);
        PromotionType type4 = dis.IsPromotion("ITEM000005");
        assertEquals(type4, PromotionType.NOPROMOTION);
    }

    @Test
    public void should_calculate_price_CalculatePrice() {
        Discount dis = Discount.Initial();
        dis.ClearDiscount();
        dis.AddGiveItem("ITEM000001");
        dis.AddDiscountItem("ITEM000005");
        PurchaseList list = new PurchaseList();
        list.AddPurchaseListItem("ITEM000001");
        list.AddPurchaseListItem("ITEM000001");
        list.AddPurchaseListItem("ITEM000001");
        list.AddPurchaseListItem("ITEM000002-2");
        list.AddPurchaseListItem("ITEM000005-3");
        list.ParsePurchaseList();
        Map<String, PurchaseItem> returnlist = list.getPurchaseList();
        PurchaseItem item1 = returnlist.get("ITEM000001");
        assertEquals(item1.getDiscountprice(), 98, 0.1);
        assertEquals(item1.getRealprice(), 294, 0.1);
        PurchaseItem item2 = returnlist.get("ITEM000002");
        assertEquals(item2.getDiscountprice(), 0, 0.1);
        assertEquals(item2.getRealprice(), 2, 0.1);
        PurchaseItem item3 = returnlist.get("ITEM000005");
        assertEquals(item3.getRealprice(), 9, 0.1);
        assertEquals(item3.getDiscountprice(), 0.45, 0.1);
    }
}
