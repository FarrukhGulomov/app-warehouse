package uz.pdp.online.appwarehouse.service.helper;



public abstract class  Operation {
    public String getCode(Integer maxId) {
        String code = "00";
        int num = 0;
        if (maxId==null) {
            num++;
            code +=num;
            return code;
        }
        num = maxId;
        num++;
        code +=num;
        return code;

    }
}
