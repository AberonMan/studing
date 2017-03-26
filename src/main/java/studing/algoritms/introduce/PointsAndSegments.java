package studing.algoritms.introduce;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PointsAndSegments {

    public static void main(String[] args) {
        List<Segment> segments = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int segmentCount = scanner.nextInt();
        while (segmentCount > 0){
            segments.add(new Segment(scanner.nextInt(), scanner.nextInt()));
            segmentCount--;
        }
        Collections.sort(segments);
        List<Integer> points = calculatePoints(segments);
        System.out.println(points.size());
        for (Integer point : points) {
            System.out.print(point + " ");
        }

    }

    private static List<Integer> calculatePoints(List<Segment> segments) {

        List<Integer> points = new ArrayList<>();
        for (Segment segment : segments) {
            if(points.size() == 0){
                points.add(segment.end);
            }
            else{
                if(!segment.intercept(points)){
                    points.add(segment.end);
                }
            }
        }

        return points;
    }

    private static class Segment implements Comparable<Segment> {

        int begin;
        int end;
        int length;

        Segment(int begin, int end) {
            this.begin = begin;
            this.end = end;
            this.length = end - begin;
        }

        @Override
        public int compareTo(Segment that) {
            return this.end - that.end;
        }

        @Override
        public String toString() {
            return "Segment{" +
                    "begin=" + begin +
                    ", end=" + end +
                    '}';
        }

        boolean intercept(List<Integer> points){
            for (Integer point : points) {
               if( this.begin <= point && this.end >= point)
                   return  true;
            }
            return false;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Segment segment = (Segment) o;

            return begin == segment.begin && end == segment.end;
        }

        @Override
        public int hashCode() {
            int result = begin;
            result = 31 * result + end;
            return result;
        }
    }

}
