package lab3;

/**
 * Created by max on 26.04.15.
 * Have fun!
 */
public class MyFloat {

    public static final int CHARACTERISTIC_SIZE = 8;
    public static final int MANTISSA_SIZE = 23;
    public static final int BIAS = (1 << (CHARACTERISTIC_SIZE - 1)) - 1;

    boolean isNegative;
    int characteristic;
    long mantissa;

    public MyFloat(MyFloat myFloat) {
        this.isNegative = myFloat.isNegative;
        this.characteristic = myFloat.characteristic;
        this.mantissa = myFloat.mantissa;
    }

    public MyFloat(double value) {
        if (value < 0) {
            isNegative = true;
            value = -value;
        }
        int k = 0;
        while (value < 1) {
            value *= 2;
            k--;
        }
        while (value >= 2) {
            value /= 2;
            k++;
        }
        characteristic = k + BIAS;
        value--;
        for (int i = 0; i < MANTISSA_SIZE; i++) {
            value *= 2;
            if (value >= 1) {
                mantissa |= (1 << (MANTISSA_SIZE - i - 1));
                value--;
            }
        }
    }

    @Override
    public String toString() {
        return (isNegative ? "1" : "0") + "|" +
                toBinaryString(characteristic, CHARACTERISTIC_SIZE) + "|" +
                toBinaryString(mantissa, MANTISSA_SIZE) +
                " : k = " + (characteristic - BIAS) +
                " : M = " + mantissaToDouble(mantissa) +
                " : VAL = " + (isNegative ? -toDouble() : toDouble());
    }

    String toBinaryString(long value, int bits) {
        final int SPACING = 4;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < bits; i++) {
            int bit = (int) (value & 1);
            value /= 2;
            result.append(bit);
            if ((i + 1) % SPACING == 0 && i != bits - 1) {
                result.append(' ');
            }
        }
        return result.reverse().toString();
    }

    double toDouble() {
        double result = mantissaToDouble(mantissa);
        if (characteristic > BIAS) {
            int k = characteristic - BIAS;
            for (int i = 0; i < k; i++) {
                result *= 2;
            }
        } else {
            int k = BIAS - characteristic;
            for (int i = 0; i < k; i++) {
                result /= 2;
            }
        }
        return result;
    }

    static MyFloat add(MyFloat value1, MyFloat value2) {
        MyFloat result;
        if (value1.characteristic > value2.characteristic) {
            result = new MyFloat(value1);
        } else {
            result = new MyFloat(value2);
            value2 = value1;
        }
        double mantissa1 = mantissaToDouble(result.mantissa);
        double mantissa2 = mantissaToDouble(value2.mantissa);
        int difference = result.characteristic - value2.characteristic;
        for (int i = 0; i < difference; i++) {
            mantissa2 /= 2;
        }
        if (result.isNegative != value2.isNegative) {
            mantissa1 -= mantissa2;
        } else {
            mantissa1 += mantissa2;
        }
        if (mantissa1 < 0) {
            result.isNegative = !result.isNegative;
            mantissa1 = -mantissa1;
        }
        int k = 0;
        while (mantissa1 < 1) {
            mantissa1 *= 2;
            k--;
        }
        while (mantissa1 >= 2) {
            mantissa1 /= 2;
            k++;
        }
        result.characteristic += k;
        result.mantissa = doubleToMantissa(mantissa1);
        return result;
    }

    public MyFloat add(MyFloat value) {
        return add(this, value);
    }

    public MyFloat sub(MyFloat value) {
        MyFloat negativeValue = new MyFloat(value);
        negativeValue.isNegative = !negativeValue.isNegative;
        return add(this, negativeValue);
    }

    public MyFloat mult(MyFloat value) {
        MyFloat result = new MyFloat(this);
        result.isNegative ^= value.isNegative;
        result.characteristic += value.characteristic - BIAS;
        double mantissa1 = mantissaToDouble(result.mantissa);
        double mantissa2 = mantissaToDouble(value.mantissa);
        mantissa1 *= mantissa2;
        int k = 0;
        while (mantissa1 < 1) {
            mantissa1 *= 2;
            k--;
        }
        while (mantissa1 >= 2) {
            mantissa1 /= 2;
            k++;
        }
        result.characteristic += k;
        result.mantissa = doubleToMantissa(mantissa1);
        return result;
    }

    public MyFloat div(MyFloat value) {
        MyFloat result = new MyFloat(this);
        result.isNegative ^= value.isNegative;
        result.characteristic -= value.characteristic - BIAS;
        double mantissa1 = mantissaToDouble(result.mantissa);
        double mantissa2 = mantissaToDouble(value.mantissa);
        mantissa1 /= mantissa2;
        int k = 0;
        while (mantissa1 < 1) {
            mantissa1 *= 2;
            k--;
        }
        while (mantissa1 >= 2) {
            mantissa1 /= 2;
            k++;
        }
        result.characteristic += k;
        result.mantissa = doubleToMantissa(mantissa1);
        return result;
    }

    static double mantissaToDouble(long value) {
        double result = 0;
        for (int i = 0; i < MANTISSA_SIZE; i++) {
            result = (result + (int) (value & 1)) / 2.0;
            value >>= 1;
        }
        return result + 1;
    }

    static long doubleToMantissa(double value) {
        long result = 0;
        value--;
        for (int i = 0; i < MANTISSA_SIZE; i++) {
            value *= 2;
            if (value >= 1) {
                result |= (1 << (MANTISSA_SIZE - i - 1));
                value--;
            }
        }
        return result;
    }
}
