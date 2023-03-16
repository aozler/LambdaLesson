import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Lambda02 {
    public static void main(String[] args) {
        List<Integer> sayi = new ArrayList<>(Arrays.asList(4, 2, 6, 11, -5, 7, 3, 15));
        ciftKarePrint(sayi);
        System.out.println("\n **********");
        tekKupBirFazlaPrint(sayi);
        System.out.println("\n **********");
        maxElBul(sayi);
        System.out.println("\n **********");
        structuredMaxElBul(sayi);
        System.out.println("\n **********");
        elTopla(sayi);
        System.out.println("\n **********");
        ciftCarp(sayi);
        System.out.println("\n **********");
        minBul(sayi);
        System.out.println("\n **********");
        bestenBykTekKck(sayi);
        System.out.println("\n **********");
        ciftKareKbSortPrint(sayi);

    }
    //Task-1: Functional Programming ile listin cift elemanlarinin karelerini
    //        ayni satirda aralarina bosluk birakarak print ediniz.
    public static void ciftKarePrint(List<Integer> sayi){
        sayi.
                stream().
                filter(Lambda01::ciftBul). //akistadki cift sayilari filtreledim
                map(t -> t*t). //16, 4, 36 --> Stream icerisindeki elemanlari baska degerlere donusturur
                forEach(Lambda01::yazdir);

    }

    //Task-2: Functional Programming ile listin tek elemanlarinin kuplerinin bir fazlasini ayni satirda
    //        aralarina bosluk birakarak print ediniz.
    public static void tekKupBirFazlaPrint(List<Integer> sayi){
        sayi.
                stream(). // (4, 2, 6, 11, -5, 7, 3, 15)
                filter(t->t %2 !=0). // 11, -5, 7, 3, 15
                map(t->(t*t*t)+1). // 1332, -124, 344, 28, 3376
                forEach(Lambda01::yazdir);

    }

    //Task-3: Functional Programming ile listin cift elemanlarinin
    //        karekoklerini ayni satirda aralarina bosluk birakarak yazdiriniz.
    public static void ciftKarekokPrint(List<Integer> sayi){
        sayi.
                stream().
                filter(Lambda01::ciftBul).
                map(Math::sqrt). //double
                forEach(t-> System.out.print(t+" "));

    }

    //Task-4: List'in en buyuk elemanini yazdiriniz.
    public static void maxElBul(List<Integer> sayi){
        Optional<Integer> maxSayi = sayi.
                stream().
                reduce(Math::max); //eger benim resultim tek bir deger ise o zaman reduce terminal opr. kullanilir.
        System.out.println(maxSayi);//Optional[15] verir
    }
    //Structured yapi ile cozelim.
    public static void structuredMaxElBul(List<Integer> sayi){
       int max = Integer.MIN_VALUE;
        System.out.println("max = " + max); // max.soutv ile yazilir
        for (int i=0; i< sayi.size();i++){
            if (sayi.get(i)>max) max = sayi.get(i);
        }
        System.out.println("en buyuk sayi : " + max);// en buyuk sayi : 15

    }

    //Task-5: List'in cift elemanlarin karelerinin en buyugunu print ediniz.
    public static void ciftKareMaxPrint(List<Integer> sayi){
        System.out.println(sayi.
                stream().
                filter(Lambda01::ciftBul).
                map(a -> a * a).
                reduce(Integer::max)); // Math::max'a gore Integer::max daha hizli calisir

    }

    //Task-6: List'teki tum elemanlarin toplamini yazdiriniz. Lambda Expression...
    public static void elTopla(List<Integer> sayi){
        int toplam = sayi.stream().reduce(0,(a,b) -> a+b);
        System.out.println("toplam = " + toplam); //toplam = 43
        /*
           * a ilk degerini her zaman atanan degerden (ilk parametre) den alir, bu ornekte 0 oluyor.
           * b degerini her zaman stream() den akisdan alir.
           * a ilk degerinden sonraki her degeri action(islem) den alir.
        */

    }

    //Task-7: List'teki cift elemanlarin carpimini yazdiriniz.
    public static void ciftCarp(List<Integer> sayi){
        System.out.println(sayi.
                stream().
                filter(Lambda01::ciftBul).
                reduce(Math::multiplyExact)); //method refrance

    //2.Yol:
        System.out.println(sayi.
                stream().
                filter(Lambda01::ciftBul).
                reduce(1, (a, b) -> (a * b))); // lambda expression

    }

    //Task-8: List'teki elemanlardan en kucugunu print ediniz.
    public static void minBul(List<Integer> sayi){
        //1.Yol:
        System.out.println(sayi.
                stream().
                reduce(Integer::min)); // Math::min  -5

        //2.Yol:
        System.out.println(sayi.
                stream().
                reduce(Lambda02::byMiracMin));

    }
    public static int byMiracMin(int a, int b){
        return a<b ? a : b ;
    }

    //Task-9: List'teki 5'ten buyuk en kucuk tek sayiyi print ediniz.
    public static void bestenBykTekKck(List<Integer> sayi){
        System.out.println(sayi.
                stream().
                filter(a -> (a > 5) && (a % 2 == 1)).
                reduce(Lambda02::byMiracMin)); //Optional[7]

    }

    //Task-10: List'in cift elemanlarinin karelerini kucukten buyuge print ediniz.
    public static void ciftKareKbSortPrint(List<Integer> sayi){
        sayi.
                stream(). // akisi baslattik
                filter(Lambda01::ciftBul). // akis icindeki cift sayilari aldim
                map(t->t*t). // sayilarin karesi ile yeni bir akis olusturdum
                sorted(). // akis icindeki sayilari natural-order olarak siraladim
                forEach(Lambda01::yazdir); // akisdaki, sayilari ekrana yazdim           (4, 16, 36)
    }
}
