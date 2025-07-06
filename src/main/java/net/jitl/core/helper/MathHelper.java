package net.jitl.core.helper;

import net.minecraft.core.Vec3i;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.Random;
import java.util.UUID;

public class MathHelper {
    public static final float SQRT_2 = sqrt(2.0F);
    private static final float[] SIN_TABLE = new float[65536];
    private static final Random RANDOM = new Random();
    private static final int[] MULTIPLY_DE_BRUIJN_BIT_POSITION;
    private static final double FRAC_BIAS;
    private static final double[] ASINE_TAB;
    private static final double[] COS_TAB;

    public static float sin(float p_sin_0_) {
        return SIN_TABLE[(int)(p_sin_0_ * 10430.378F) & '\uffff'];
    }

    public static float cos(float p_cos_0_) {
        return SIN_TABLE[(int)(p_cos_0_ * 10430.378F + 16384.0F) & '\uffff'];
    }

    public static float sqrt(float p_sqrt_0_) {
        return (float)Math.sqrt((double)p_sqrt_0_);
    }

    public static float sqrt(double p_sqrt_0_) {
        return (float)Math.sqrt(p_sqrt_0_);
    }

    public static int floor(float p_floor_0_) {
        int lvt_1_1_ = (int)p_floor_0_;
        return p_floor_0_ < (float)lvt_1_1_ ? lvt_1_1_ - 1 : lvt_1_1_;
    }

    public static int fastFloor(double p_fastFloor_0_) {
        return (int)(p_fastFloor_0_ + 1024.0) - 1024;
    }

    public static int floor(double p_floor_0_) {
        int lvt_2_1_ = (int)p_floor_0_;
        return p_floor_0_ < (double)lvt_2_1_ ? lvt_2_1_ - 1 : lvt_2_1_;
    }

    public static long lfloor(double p_lfloor_0_) {
        long lvt_2_1_ = (long)p_lfloor_0_;
        return p_lfloor_0_ < (double)lvt_2_1_ ? lvt_2_1_ - 1L : lvt_2_1_;
    }

    public static int absFloor(double p_absFloor_0_) {
        return (int)(p_absFloor_0_ >= 0.0 ? p_absFloor_0_ : -p_absFloor_0_ + 1.0);
    }

    public static float abs(float p_abs_0_) {
        return p_abs_0_ >= 0.0F ? p_abs_0_ : -p_abs_0_;
    }

    public static int abs(int p_abs_0_) {
        return p_abs_0_ >= 0 ? p_abs_0_ : -p_abs_0_;
    }

    public static int ceil(float p_ceil_0_) {
        int lvt_1_1_ = (int)p_ceil_0_;
        return p_ceil_0_ > (float)lvt_1_1_ ? lvt_1_1_ + 1 : lvt_1_1_;
    }

    public static int ceil(double p_ceil_0_) {
        int lvt_2_1_ = (int)p_ceil_0_;
        return p_ceil_0_ > (double)lvt_2_1_ ? lvt_2_1_ + 1 : lvt_2_1_;
    }

    public static int clamp(int p_clamp_0_, int p_clamp_1_, int p_clamp_2_) {
        if (p_clamp_0_ < p_clamp_1_) {
            return p_clamp_1_;
        } else {
            return p_clamp_0_ > p_clamp_2_ ? p_clamp_2_ : p_clamp_0_;
        }
    }

    public static float clamp(float p_clamp_0_, float p_clamp_1_, float p_clamp_2_) {
        if (p_clamp_0_ < p_clamp_1_) {
            return p_clamp_1_;
        } else {
            return p_clamp_0_ > p_clamp_2_ ? p_clamp_2_ : p_clamp_0_;
        }
    }

    public static double clamp(double p_clamp_0_, double p_clamp_2_, double p_clamp_4_) {
        if (p_clamp_0_ < p_clamp_2_) {
            return p_clamp_2_;
        } else {
            return p_clamp_0_ > p_clamp_4_ ? p_clamp_4_ : p_clamp_0_;
        }
    }

    public static double clampedLerp(double p_clampedLerp_0_, double p_clampedLerp_2_, double p_clampedLerp_4_) {
        if (p_clampedLerp_4_ < 0.0) {
            return p_clampedLerp_0_;
        } else {
            return p_clampedLerp_4_ > 1.0 ? p_clampedLerp_2_ : p_clampedLerp_0_ + (p_clampedLerp_2_ - p_clampedLerp_0_) * p_clampedLerp_4_;
        }
    }

    public static double absMax(double p_absMax_0_, double p_absMax_2_) {
        if (p_absMax_0_ < 0.0) {
            p_absMax_0_ = -p_absMax_0_;
        }

        if (p_absMax_2_ < 0.0) {
            p_absMax_2_ = -p_absMax_2_;
        }

        return p_absMax_0_ > p_absMax_2_ ? p_absMax_0_ : p_absMax_2_;
    }

    public static int intFloorDiv(int p_intFloorDiv_0_, int p_intFloorDiv_1_) {
        return p_intFloorDiv_0_ < 0 ? -((-p_intFloorDiv_0_ - 1) / p_intFloorDiv_1_) - 1 : p_intFloorDiv_0_ / p_intFloorDiv_1_;
    }

    public static int getInt(Random p_getInt_0_, int p_getInt_1_, int p_getInt_2_) {
        return p_getInt_1_ >= p_getInt_2_ ? p_getInt_1_ : p_getInt_0_.nextInt(p_getInt_2_ - p_getInt_1_ + 1) + p_getInt_1_;
    }

    public static float nextFloat(Random p_nextFloat_0_, float p_nextFloat_1_, float p_nextFloat_2_) {
        return p_nextFloat_1_ >= p_nextFloat_2_ ? p_nextFloat_1_ : p_nextFloat_0_.nextFloat() * (p_nextFloat_2_ - p_nextFloat_1_) + p_nextFloat_1_;
    }

    public static double nextDouble(Random p_nextDouble_0_, double p_nextDouble_1_, double p_nextDouble_3_) {
        return p_nextDouble_1_ >= p_nextDouble_3_ ? p_nextDouble_1_ : p_nextDouble_0_.nextDouble() * (p_nextDouble_3_ - p_nextDouble_1_) + p_nextDouble_1_;
    }

    public static double average(long[] p_average_0_) {
        long lvt_1_1_ = 0L;
        long[] var3 = p_average_0_;
        int var4 = p_average_0_.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            long lvt_6_1_ = var3[var5];
            lvt_1_1_ += lvt_6_1_;
        }

        return (double)lvt_1_1_ / (double)p_average_0_.length;
    }

    public static boolean epsilonEquals(float p_epsilonEquals_0_, float p_epsilonEquals_1_) {
        return abs(p_epsilonEquals_1_ - p_epsilonEquals_0_) < 1.0E-5F;
    }

    public static int normalizeAngle(int p_normalizeAngle_0_, int p_normalizeAngle_1_) {
        return (p_normalizeAngle_0_ % p_normalizeAngle_1_ + p_normalizeAngle_1_) % p_normalizeAngle_1_;
    }

    public static float positiveModulo(float p_positiveModulo_0_, float p_positiveModulo_1_) {
        return (p_positiveModulo_0_ % p_positiveModulo_1_ + p_positiveModulo_1_) % p_positiveModulo_1_;
    }

    public static double positiveModulo(double p_positiveModulo_0_, double p_positiveModulo_2_) {
        return (p_positiveModulo_0_ % p_positiveModulo_2_ + p_positiveModulo_2_) % p_positiveModulo_2_;
    }

    public static float wrapDegrees(float p_wrapDegrees_0_) {
        p_wrapDegrees_0_ %= 360.0F;
        if (p_wrapDegrees_0_ >= 180.0F) {
            p_wrapDegrees_0_ -= 360.0F;
        }

        if (p_wrapDegrees_0_ < -180.0F) {
            p_wrapDegrees_0_ += 360.0F;
        }

        return p_wrapDegrees_0_;
    }

    public static double wrapDegrees(double p_wrapDegrees_0_) {
        p_wrapDegrees_0_ %= 360.0;
        if (p_wrapDegrees_0_ >= 180.0) {
            p_wrapDegrees_0_ -= 360.0;
        }

        if (p_wrapDegrees_0_ < -180.0) {
            p_wrapDegrees_0_ += 360.0;
        }

        return p_wrapDegrees_0_;
    }

    public static int wrapDegrees(int p_wrapDegrees_0_) {
        p_wrapDegrees_0_ %= 360;
        if (p_wrapDegrees_0_ >= 180) {
            p_wrapDegrees_0_ -= 360;
        }

        if (p_wrapDegrees_0_ < -180) {
            p_wrapDegrees_0_ += 360;
        }

        return p_wrapDegrees_0_;
    }

    public static int getInt(String p_getInt_0_, int p_getInt_1_) {
        try {
            return Integer.parseInt(p_getInt_0_);
        } catch (Throwable var3) {
            return p_getInt_1_;
        }
    }

    public static int getInt(String p_getInt_0_, int p_getInt_1_, int p_getInt_2_) {
        return Math.max(p_getInt_2_, getInt(p_getInt_0_, p_getInt_1_));
    }

    public static double getDouble(String p_getDouble_0_, double p_getDouble_1_) {
        try {
            return Double.parseDouble(p_getDouble_0_);
        } catch (Throwable var4) {
            return p_getDouble_1_;
        }
    }

    public static double getDouble(String p_getDouble_0_, double p_getDouble_1_, double p_getDouble_3_) {
        return Math.max(p_getDouble_3_, getDouble(p_getDouble_0_, p_getDouble_1_));
    }

    public static int smallestEncompassingPowerOfTwo(int p_smallestEncompassingPowerOfTwo_0_) {
        int lvt_1_1_ = p_smallestEncompassingPowerOfTwo_0_ - 1;
        lvt_1_1_ |= lvt_1_1_ >> 1;
        lvt_1_1_ |= lvt_1_1_ >> 2;
        lvt_1_1_ |= lvt_1_1_ >> 4;
        lvt_1_1_ |= lvt_1_1_ >> 8;
        lvt_1_1_ |= lvt_1_1_ >> 16;
        return lvt_1_1_ + 1;
    }

    private static boolean isPowerOfTwo(int p_isPowerOfTwo_0_) {
        return p_isPowerOfTwo_0_ != 0 && (p_isPowerOfTwo_0_ & p_isPowerOfTwo_0_ - 1) == 0;
    }

    public static int log2DeBruijn(int p_log2DeBruijn_0_) {
        p_log2DeBruijn_0_ = isPowerOfTwo(p_log2DeBruijn_0_) ? p_log2DeBruijn_0_ : smallestEncompassingPowerOfTwo(p_log2DeBruijn_0_);
        return MULTIPLY_DE_BRUIJN_BIT_POSITION[(int)((long)p_log2DeBruijn_0_ * 125613361L >> 27) & 31];
    }

    public static int log2(int p_log2_0_) {
        return log2DeBruijn(p_log2_0_) - (isPowerOfTwo(p_log2_0_) ? 0 : 1);
    }

    public static int roundUp(int p_roundUp_0_, int p_roundUp_1_) {
        if (p_roundUp_1_ == 0) {
            return 0;
        } else if (p_roundUp_0_ == 0) {
            return p_roundUp_1_;
        } else {
            if (p_roundUp_0_ < 0) {
                p_roundUp_1_ *= -1;
            }

            int lvt_2_1_ = p_roundUp_0_ % p_roundUp_1_;
            return lvt_2_1_ == 0 ? p_roundUp_0_ : p_roundUp_0_ + p_roundUp_1_ - lvt_2_1_;
        }
    }

    public static long getCoordinateRandom(int p_getCoordinateRandom_0_, int p_getCoordinateRandom_1_, int p_getCoordinateRandom_2_) {
        long lvt_3_1_ = (long)(p_getCoordinateRandom_0_ * 3129871) ^ (long)p_getCoordinateRandom_2_ * 116129781L ^ (long)p_getCoordinateRandom_1_;
        lvt_3_1_ = lvt_3_1_ * lvt_3_1_ * 42317861L + lvt_3_1_ * 11L;
        return lvt_3_1_;
    }


    public static int rgb(float p_rgb_0_, float p_rgb_1_, float p_rgb_2_) {
        return rgb(floor(p_rgb_0_ * 255.0F), floor(p_rgb_1_ * 255.0F), floor(p_rgb_2_ * 255.0F));
    }


    public static int rgb(int p_rgb_0_, int p_rgb_1_, int p_rgb_2_) {
        int lvt_3_1_ = (p_rgb_0_ << 8) + p_rgb_1_;
        lvt_3_1_ = (lvt_3_1_ << 8) + p_rgb_2_;
        return lvt_3_1_;
    }


    public static int multiplyColor(int p_multiplyColor_0_, int p_multiplyColor_1_) {
        int lvt_2_1_ = (p_multiplyColor_0_ & 16711680) >> 16;
        int lvt_3_1_ = (p_multiplyColor_1_ & 16711680) >> 16;
        int lvt_4_1_ = (p_multiplyColor_0_ & '\uff00') >> 8;
        int lvt_5_1_ = (p_multiplyColor_1_ & '\uff00') >> 8;
        int lvt_6_1_ = (p_multiplyColor_0_ & 255) >> 0;
        int lvt_7_1_ = (p_multiplyColor_1_ & 255) >> 0;
        int lvt_8_1_ = (int)((float)lvt_2_1_ * (float)lvt_3_1_ / 255.0F);
        int lvt_9_1_ = (int)((float)lvt_4_1_ * (float)lvt_5_1_ / 255.0F);
        int lvt_10_1_ = (int)((float)lvt_6_1_ * (float)lvt_7_1_ / 255.0F);
        return p_multiplyColor_0_ & -16777216 | lvt_8_1_ << 16 | lvt_9_1_ << 8 | lvt_10_1_;
    }


    public static double frac(double p_frac_0_) {
        return p_frac_0_ - Math.floor(p_frac_0_);
    }


    public static long getPositionRandom(Vec3i p_getPositionRandom_0_) {
        return getCoordinateRandom(p_getPositionRandom_0_.getX(), p_getPositionRandom_0_.getY(), p_getPositionRandom_0_.getZ());
    }

    public static UUID getRandomUUID(Random p_getRandomUUID_0_) {
        long lvt_1_1_ = p_getRandomUUID_0_.nextLong() & -61441L | 16384L;
        long lvt_3_1_ = p_getRandomUUID_0_.nextLong() & 4611686018427387903L | Long.MIN_VALUE;
        return new UUID(lvt_1_1_, lvt_3_1_);
    }

    public static UUID getRandomUUID() {
        return getRandomUUID(RANDOM);
    }

    public static double pct(double p_pct_0_, double p_pct_2_, double p_pct_4_) {
        return (p_pct_0_ - p_pct_2_) / (p_pct_4_ - p_pct_2_);
    }

    public static double atan2(double p_atan2_0_, double p_atan2_2_) {
        double lvt_4_1_ = p_atan2_2_ * p_atan2_2_ + p_atan2_0_ * p_atan2_0_;
        if (Double.isNaN(lvt_4_1_)) {
            return Double.NaN;
        } else {
            boolean lvt_6_1_ = p_atan2_0_ < 0.0;
            if (lvt_6_1_) {
                p_atan2_0_ = -p_atan2_0_;
            }

            boolean lvt_7_1_ = p_atan2_2_ < 0.0;
            if (lvt_7_1_) {
                p_atan2_2_ = -p_atan2_2_;
            }

            boolean lvt_8_1_ = p_atan2_0_ > p_atan2_2_;
            double lvt_9_2_;
            if (lvt_8_1_) {
                lvt_9_2_ = p_atan2_2_;
                p_atan2_2_ = p_atan2_0_;
                p_atan2_0_ = lvt_9_2_;
            }

            lvt_9_2_ = fastInvSqrt(lvt_4_1_);
            p_atan2_2_ *= lvt_9_2_;
            p_atan2_0_ *= lvt_9_2_;
            double lvt_11_1_ = FRAC_BIAS + p_atan2_0_;
            int lvt_13_1_ = (int)Double.doubleToRawLongBits(lvt_11_1_);
            double lvt_14_1_ = ASINE_TAB[lvt_13_1_];
            double lvt_16_1_ = COS_TAB[lvt_13_1_];
            double lvt_18_1_ = lvt_11_1_ - FRAC_BIAS;
            double lvt_20_1_ = p_atan2_0_ * lvt_16_1_ - p_atan2_2_ * lvt_18_1_;
            double lvt_22_1_ = (6.0 + lvt_20_1_ * lvt_20_1_) * lvt_20_1_ * 0.16666666666666666;
            double lvt_24_1_ = lvt_14_1_ + lvt_22_1_;
            if (lvt_8_1_) {
                lvt_24_1_ = 1.5707963267948966 - lvt_24_1_;
            }

            if (lvt_7_1_) {
                lvt_24_1_ = Math.PI - lvt_24_1_;
            }

            if (lvt_6_1_) {
                lvt_24_1_ = -lvt_24_1_;
            }

            return lvt_24_1_;
        }
    }

    public static double fastInvSqrt(double p_fastInvSqrt_0_) {
        double lvt_2_1_ = 0.5 * p_fastInvSqrt_0_;
        long lvt_4_1_ = Double.doubleToRawLongBits(p_fastInvSqrt_0_);
        lvt_4_1_ = 6910469410427058090L - (lvt_4_1_ >> 1);
        p_fastInvSqrt_0_ = Double.longBitsToDouble(lvt_4_1_);
        p_fastInvSqrt_0_ *= 1.5 - lvt_2_1_ * p_fastInvSqrt_0_ * p_fastInvSqrt_0_;
        return p_fastInvSqrt_0_;
    }


    public static int hsvToRGB(float p_hsvToRGB_0_, float p_hsvToRGB_1_, float p_hsvToRGB_2_) {
        int lvt_3_1_ = (int)(p_hsvToRGB_0_ * 6.0F) % 6;
        float lvt_4_1_ = p_hsvToRGB_0_ * 6.0F - (float)lvt_3_1_;
        float lvt_5_1_ = p_hsvToRGB_2_ * (1.0F - p_hsvToRGB_1_);
        float lvt_6_1_ = p_hsvToRGB_2_ * (1.0F - lvt_4_1_ * p_hsvToRGB_1_);
        float lvt_7_1_ = p_hsvToRGB_2_ * (1.0F - (1.0F - lvt_4_1_) * p_hsvToRGB_1_);
        float lvt_8_7_;
        float lvt_9_7_;
        float lvt_10_7_;
        switch (lvt_3_1_) {
            case 0:
                lvt_8_7_ = p_hsvToRGB_2_;
                lvt_9_7_ = lvt_7_1_;
                lvt_10_7_ = lvt_5_1_;
                break;
            case 1:
                lvt_8_7_ = lvt_6_1_;
                lvt_9_7_ = p_hsvToRGB_2_;
                lvt_10_7_ = lvt_5_1_;
                break;
            case 2:
                lvt_8_7_ = lvt_5_1_;
                lvt_9_7_ = p_hsvToRGB_2_;
                lvt_10_7_ = lvt_7_1_;
                break;
            case 3:
                lvt_8_7_ = lvt_5_1_;
                lvt_9_7_ = lvt_6_1_;
                lvt_10_7_ = p_hsvToRGB_2_;
                break;
            case 4:
                lvt_8_7_ = lvt_7_1_;
                lvt_9_7_ = lvt_5_1_;
                lvt_10_7_ = p_hsvToRGB_2_;
                break;
            case 5:
                lvt_8_7_ = p_hsvToRGB_2_;
                lvt_9_7_ = lvt_5_1_;
                lvt_10_7_ = lvt_6_1_;
                break;
            default:
                throw new RuntimeException("Something went wrong when converting from HSV to RGB. Input was " + p_hsvToRGB_0_ + ", " + p_hsvToRGB_1_ + ", " + p_hsvToRGB_2_);
        }

        int lvt_11_1_ = clamp((int)(lvt_8_7_ * 255.0F), 0, 255);
        int lvt_12_1_ = clamp((int)(lvt_9_7_ * 255.0F), 0, 255);
        int lvt_13_1_ = clamp((int)(lvt_10_7_ * 255.0F), 0, 255);
        return lvt_11_1_ << 16 | lvt_12_1_ << 8 | lvt_13_1_;
    }

    public static int hash(int p_hash_0_) {
        p_hash_0_ ^= p_hash_0_ >>> 16;
        p_hash_0_ *= -2048144789;
        p_hash_0_ ^= p_hash_0_ >>> 13;
        p_hash_0_ *= -1028477387;
        p_hash_0_ ^= p_hash_0_ >>> 16;
        return p_hash_0_;
    }

    static {
        int lvt_0_2_;
        for(lvt_0_2_ = 0; lvt_0_2_ < 65536; ++lvt_0_2_) {
            SIN_TABLE[lvt_0_2_] = (float)Math.sin((double)lvt_0_2_ * Math.PI * 2.0 / 65536.0);
        }

        MULTIPLY_DE_BRUIJN_BIT_POSITION = new int[]{0, 1, 28, 2, 29, 14, 24, 3, 30, 22, 20, 15, 25, 17, 4, 8, 31, 27, 13, 23, 21, 19, 16, 7, 26, 12, 18, 6, 11, 5, 10, 9};
        FRAC_BIAS = Double.longBitsToDouble(4805340802404319232L);
        ASINE_TAB = new double[257];
        COS_TAB = new double[257];

        for(lvt_0_2_ = 0; lvt_0_2_ < 257; ++lvt_0_2_) {
            double lvt_1_1_ = (double)lvt_0_2_ / 256.0;
            double lvt_3_1_ = Math.asin(lvt_1_1_);
            COS_TAB[lvt_0_2_] = Math.cos(lvt_3_1_);
            ASINE_TAB[lvt_0_2_] = lvt_3_1_;
        }

    }
}
