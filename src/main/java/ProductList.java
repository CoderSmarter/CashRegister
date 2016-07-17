import java.util.*;

/**
 * Created by Administrator on 2016/7/12.
 * 该类用于保存商品的固有信息
 */
public class ProductList {
    private static ProductList single = null;
    private static Map<String, ProductInfo> productInfos = new HashMap<String, ProductInfo>();

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
