package sat.recruitment.api.utils;

import org.springframework.stereotype.Component;

@Component
public class UserMoneyUtils {

    public Double getUserMoneyByType(String userType, Double userMoney) {
        Double money = userMoney;
        switch (userType) {
            case "Normal":
                if (Double.valueOf(userMoney) > 100) {
                    Double percentage = Double.valueOf("0.12");
                    // If new user is normal and has more than USD100
                    var gif = Double.valueOf(userMoney) * percentage;
                    money = userMoney + gif;
                }

                if (Double.valueOf(userMoney) < 100) {
                    if (Double.valueOf(userMoney) > 10) {
                        var percentage = Double.valueOf("0.8");
                        var gif = Double.valueOf(userMoney) * percentage;
                        money = userMoney + gif;
                    }
                }
                break;

            case "SuperUser":
                if (Double.valueOf(userMoney) > 100) {
                    Double percentage = Double.valueOf("0.20");
                    Double gif = Double.valueOf(userMoney) * percentage;
                    money = userMoney+ gif;
                }
                break;

            case  "Premium":
                if (Double.valueOf(userMoney) > 100) {
                    Double gif = Double.valueOf(userMoney) * 2;
                    money = userMoney + gif;
                }
                break;
        }

        return money;
    }
}
