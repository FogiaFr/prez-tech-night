package com.mkl.prez.technight;

/**
 * Description of the class.
 *
 * @author MKL.
 */
public class RecursiveAntiSeche {
    public static int cpt(int i) {
        return cptInternal(i, 0).invoke();
    }

    private static TailCall<Integer> cptInternal(int i, int stack) {
        if (i == 0) {
            return TailCall.done(stack);
        }

        return () -> cptInternal(i - 1, stack + 1);
    }

    public static void main(String... args) {
        System.out.println(cpt(1));
        System.out.println(cpt(1000));
        System.out.println(cpt(1000000));
        System.out.println(cpt(100000000));
    }
}
