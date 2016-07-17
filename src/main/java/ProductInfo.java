/**
 * Created by Administrator on 2016/7/17.
 */
public class ProductInfo {
    private String barcode;
    private String name;
    private double price;
    private double num;   //不能私有化了
    private String unit;
    boolean barcodeDisType;
    boolean barcodeGiveType;
    ProductInfo(String barcode, String name, double price, double num,
                String unit, boolean barcodeDisType, boolean barcodeGiveType){
        this.barcode = barcode;
        this.name = name;
        this.price = price;
        this.num = num;
        this.unit = unit;
        this.barcodeDisType = barcodeDisType;
        this.barcodeGiveType = barcodeGiveType;
    }
}
