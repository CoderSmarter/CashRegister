/**
 * Created by Administrator on 2016/7/16.
 */
public class Discount {
    ProductInfo productInfo[]; //商品信息类的对象，暂时先用数组，之后再改为Map吧
    //单类商品总价
    double getSumMoney(){
        return this.num*this.price;
    }
    //单类商品的实际价格
    double realPrice(){             //根据barcode怎么写折扣?直接判断barcode类型可行？
        double sum = 0;
        //先判断是否折扣,还未写
        for(int i = 0;i<productInfo.length;i++){
            if(productInfo[i].barcodeDisType&&!productInfo[i].barcodeGiveType){
                sum = sum +getSumMoney()*0.9;
            }else if(!productInfo[i].barcodeDisType&&productInfo[i].barcodeGiveType){
                sum = sum +getSumMoney();//总价格没变，钱还是那么多，只是数量变了，要加一个数量返回值方法
                //数量变化
//                if(productInfo[i].num%2==0){
//                    num = num +num/2;
//                    productInfo[i].num = num;
//                }else if(productInfo[i].num%2!=0){num=num+(num-1)/2;productInfo[i].num = num;}
            }else if(productInfo[i].barcodeDisType&&productInfo[i].barcodeGiveType){
                sum = sum +getSumMoney();    //可加到上一组判断，仍然是钱没变数量变了
            }else if(!productInfo[i].barcodeDisType&&!productInfo[i].barcodeGiveType){
                sum = sum +getSumMoney();
            }
        }
        return sum;
    }
    //单类商品优惠
    double getDiscount(){
        return this.getSumMoney()-this.realPrice();
    }
    //得到所有商品实际价格总价
    double getProSumMoney(){
        double sum = 0;
        for(int i = 0;i<productInfo.length;i++){
            sum = sum+productInfo[i].realPrice();
        }
        return sum;
    }
    //得到所有商品总优惠
    double getProDiscount(){
        double sum = 0;
        for(int i = 0;i<productInfo.length;i++){
            sum = sum +productInfo[i].getDiscount();
        }
        return sum;
    }
}