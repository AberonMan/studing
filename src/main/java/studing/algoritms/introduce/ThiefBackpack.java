package studing.algoritms.sort.introduce;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class ThiefBackpack {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int goodsCount = scanner.nextInt();
        int backpackVolume = scanner.nextInt();
        List<Goods> goods = new ArrayList<>();
        while (goodsCount > 0) {
            goods.add(new Goods(scanner.nextInt(), scanner.nextInt()));
            goodsCount--;
        }
        Collections.sort(goods);

        double backpackCost = calculateMaxBackpackCost(goods, backpackVolume);
        System.out.println(String.format("%.3f",backpackCost));


    }

    private static double calculateMaxBackpackCost(List<Goods> goods, int backpackVolume) {

        double cost = 0;

        for (Goods good : goods) {
            if (backpackVolume >= good.volume) {
                backpackVolume -= good.volume;
                cost += good.cost;
                if (backpackVolume == 0) return cost;
            } else {
                return cost += good.specificCost * backpackVolume;

            }
        }
        return cost;
    }

    private static class Goods implements Comparable<Goods> {

        int cost;
        int volume;
        double specificCost;

        Goods(int cost, int volume) {
            this.cost = cost;
            this.volume = volume;
            this.specificCost = ((double) cost) / volume;
        }

        @Override
        public int compareTo(Goods that) {
            return Double.valueOf(that.specificCost).compareTo(this.specificCost);
        }
    }

}
