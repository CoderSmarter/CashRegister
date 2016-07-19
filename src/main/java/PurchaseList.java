import java.util.*;

/**
 * Created by Administrator on 2016/7/16.
 * 该类包含二种信息：一种是原始的编号形式的数据购物清单，一种是用于显示物品信息的购物清单
 * 在调用AddPurchaseListItem添加完购物清单数据后，需要调用ParsePurchaseList来生成第二种购
 * 物清单，接下来就可以调用getPurchaseList获取第二种购物清单用于显示。
 */
public class PurchaseList {
    private List<String> purchase_list_primary = new ArrayList<String>();
    private Map<String, PurchaseItem> purchase_list = new HashMap<String, PurchaseItem>();
    private ProductList list = ProductList.Initial();

    public void AddPurchaseListItem(String item) {
        purchase_list_primary.add(item);
    }

    public int getSize() {
        return purchase_list_primary.size();
    }

    public String getPurchaseListItem(int index) {
        return purchase_list_primary.get(index);
    }

    private void ParseOneItem(int index) {         //分析原始购物清单中的一个条目，并更新显示商品信息的购物清单
        String item = purchase_list_primary.get(index);
        int pos = item.indexOf('-');                //分析购物清单中的一个条目，从中获取barcode和数量
        String barcode = null;
        int productitemnum = 0;
        if (pos == -1)
        {
            barcode = item;
            productitemnum = 1;
        }
        else
        {
            barcode = item.substring(0, pos);
            String rest = item.substring(pos + 1);
            productitemnum = Integer.parseInt(rest);
        }

        PurchaseItem olditem = purchase_list.get(barcode);
        if (olditem == null) {           //如果编号对应的商品信息不存在，重新创建该商品的信息
            PurchaseItem newpurchaseitem = new PurchaseItem();
            newpurchaseitem.setIteminfo(list.getProductItemInfo(barcode));
            newpurchaseitem.setItemnum(productitemnum);
            purchase_list.put(barcode, newpurchaseitem);
        } else {                         //编号对应的商品信息存在，更新商品信息
            olditem.setItemnum(olditem.getItemnum() + productitemnum);
        }


    }

    public void ParsePurchaseList() {           //分析原始购物清单的所有条目
        purchase_list.clear();          //更新商品购物清单前先清空

        for (int i = 0; i < purchase_list_primary.size(); i++) {
            ParseOneItem(i);
        }

        Discount discount = Discount.Initial();
        for (PurchaseItem item : purchase_list.values()) {
            discount.CalculatePrice(item);
        }
    }

    public Map<String, PurchaseItem> getPurchaseList() {
        return purchase_list;
    }

    //判断商品列表是否有属于买二赠一的商品
    public boolean HasBuyTwoGiveOneType() {
        Discount discount = Discount.Initial();
        for (PurchaseItem item : purchase_list.values()) {
            PromotionType type = discount.IsPromotion(item.getIteminfo().getBarcode());
            if (type == PromotionType.BUYTWOGIVEONE) return true;
        }
        return false;
    }
}
