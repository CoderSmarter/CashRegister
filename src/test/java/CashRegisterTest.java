import java.math.BigDecimal;
import java.util.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Administrator on 2016/7/3.
 */
public class CashRegisterTest {
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
}
