/**
 * Created by Administrator on 2016/7/13.
 */
public class PurchaseItem {
    private ProductInfo iteminfo;
    private int itemnum;
    private double realprice;
    private double discountprice;

    public ProductInfo getIteminfo() {
        return iteminfo;
    }

    public int getItemnum() {
        return itemnum;
    }

    public double getRealprice() {
        return realprice;
    }

    public double getDiscountprice() {
        return discountprice;
    }

    public void setIteminfo(ProductInfo iteminfo) {
        this.iteminfo = iteminfo;
    }

    public void setItemnum(int itemnum) {
        this.itemnum = itemnum;
    }

    public void setRealprice(double realprice) {
        this.realprice = realprice;
    }

    public void setDiscountprice(double discountprice) {
        this.discountprice = discountprice;
    }
}
