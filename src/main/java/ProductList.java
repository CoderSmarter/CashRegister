import java.util.*;

/**
 * Created by Administrator on 2016/7/12.
 * 该类用于保存商品的固有信息
 */
public class ProductList {
    private static ProductList single = null;
    private static Map<String, ProductInfo> productInfos = new HashMap<String, ProductInfo>();
        /*productInfos[0] = new ProductInfo("ITEM000000", "可口可乐", "瓶", "食品", "碳酸饮料", 3.0);
        productInfos[1] = new ProductInfo("ITEM000001", "篮球", "个", "运动物品", "球类", 98.0);
        productInfos[2] = new ProductInfo("ITEM000002", "羽毛球", "个", "运动物品", "球类", 1.0);
        productInfos[3] = new ProductInfo("ITEM000003", "苹果", "斤", "食品", "水果", 5.5);
        productInfos[4] = new ProductInfo("ITEM000004", "白菜", "斤", "食品", "蔬菜", 5.0);
        productInfos[5] = new ProductInfo("ITEM000005", "牙膏", "盒", "日用品", "洗漱用品", 3.0);*/

    private ProductList() {};

    public void AddProductInfo(ProductInfo info) {
        productInfos.put(info.getBarcode(), info);
    }

    public static ProductList Initial() {
        if (single == null)
            single = new ProductList();
        return single;
    }

    public ProductInfo getProductItemInfo(String barcode)      //根据barcode返回商品信息
    {
        return productInfos.get(barcode);
    }
}
