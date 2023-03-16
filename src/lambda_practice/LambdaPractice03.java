package lambda_practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaPractice03 {

    public static void main(String[] args) {

        Apartman daire1 = new Apartman("dogu", 4000, 3);
        Apartman daire2 = new Apartman("bati", 2500, 2);
        Apartman daire3 = new Apartman("guney", 3500, 1);
        Apartman daire4 = new Apartman("dogu", 3000, 5);

        List<Apartman> kiralikDaireler = new ArrayList<>(Arrays.asList(daire1, daire2, daire3, daire4));
        doguKirayaSirala(kiralikDaireler);
        System.out.println("kat2denCokKiraList(kiralikDaireler) = " + kat2denCokKiraList(kiralikDaireler));

    }

    //dogu cephesindeki kiralık daireleri kiralarına göre sıralayın.
    public static void doguKirayaSirala(List<Apartman> list) {
        list.stream().
                filter(t -> t.getCephe().equalsIgnoreCase("dogu")).//cephesi dogu olan daireleri filtreledim
                sorted(Comparator.comparing(Apartman::getKira)).forEach(System.out::println);

    }

    //kat sayısı 2den cok olan dairelerin kiralarını listeleyın.
    public static List<Integer> kat2denCokKiraList(List<Apartman> list) {
        return
        list.
                stream().
                filter(t -> t.getKatSayisi() > 2).
                map(t -> t.getKira()).
                collect(Collectors.toList());
    }


}
