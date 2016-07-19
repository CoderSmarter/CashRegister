import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/16.
 * 打折类，存放各种打折信息
 */
enum PromotionType {DISCOUNT, BUYTWOGIVEONE, NOPROMOTION}

public class Discount {
    private static Discount single = null;
    private static ArrayList<String> discount_type = new ArrayList<String>();
    private static ArrayList<String> give_type = new ArrayList<String>();

    private Discount() {};

    public static Discount Initial() {
        if (single == null)
            single = new Discount();
        return single;
    }

    //如果新加的商品已属于买二赠一优惠，则不把此商品添加到打折优惠中
    public boolean AddDiscountItem(String barcode) {
        Iterator iterator = give_type.iterator();
        while (iterator.hasNext()) {
            String str = (String) iterator.next();
            if (str.equals(barcode)) {
                return false;
            }
        }
        discount_type.add(barcode);         //新加的商品不属于买二赠一优惠，可以添加到打折优惠中
        return true;
    }

    //如果新加的商品已属于打折优惠，则将其从打折优惠中删除
    public boolean AddGiveItem(String barcode) {
        give_type.add(barcode);
        Iterator iterator = discount_type.iterator();
        while (iterator.hasNext()) {
            String str = (String) iterator.next();
            if (str.equals(barcode)) {
                iterator.remove();
                return false;
            }
        }
        return true;
    }

    //判断商品属于哪种打折方式
    public PromotionType IsPromotion(String barcode) {
        Iterator iterator1 = give_type.iterator();
        while (iterator1.hasNext()) {
            String str = (String) iterator1.next();
            if (str.equals(barcode))
                return PromotionType.BUYTWOGIVEONE;
        }

        Iterator iterator2 = discount_type.iterator();
        while (iterator2.hasNext()) {
            String str = (String) iterator2.next();
            if (str.equals(barcode))
                return PromotionType.DISCOUNT;
        }

        return PromotionType.NOPROMOTION;
    }

    //计算商品的价格
    public void CalculatePrice(PurchaseItem item) {
        String barcode = item.getIteminfo().getBarcode();
        PromotionType type = IsPromotion(barcode);
        int itemnum = item.getItemnum();
        double price = item.getIteminfo().getPrice();
        switch (type) {
            case DISCOUNT:
            {
                item.setRealprice(itemnum * price);
                item.setDiscountprice(itemnum * price * 0.05);
                break;
            }
            case BUYTWOGIVEONE:
            {
                int itemnum_give = itemnum / 3;
                item.setRealprice(itemnum * price);
                item.setDiscountprice(itemnum_give * price);
                break;
            }
            case NOPROMOTION:
            {
                item.setRealprice(itemnum * price);
                item.setDiscountprice(0);
                break;
            }
        }
    }

    public void ClearDiscount() {
        if (!discount_type.isEmpty()) {
            Iterator iterator = discount_type.iterator();
            while (iterator.hasNext()) {
                iterator.next();
                iterator.remove();
            }
        }

        if (!give_type.isEmpty()) {
            Iterator iterator = give_type.iterator();
            while (iterator.hasNext()) {
                iterator.next();
                iterator.remove();
            }
        }
    }
}