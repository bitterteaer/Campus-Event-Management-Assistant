package test.qrcode;



public class starQRCodeUtil {

    public static String creatQRcode(String qrContent,String lujin){


        String qrurl=lujin+"/"+qrContent+".jpg";
        System.out.println(qrurl);
        try {
            System.out.println("hehe1");
            new QRCodeUtil().createQRCode(qrContent, 200, qrurl);
            System.out.println("hehe2");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return qrurl;
    }

    //测试生成不包含图片的二维码
    public static void main(String[] args){

//        try {
////            String hehe = creatQRcode("2346");
////            System.out.println(hehe);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }
}
