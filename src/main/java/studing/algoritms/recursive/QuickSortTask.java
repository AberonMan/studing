package studing.algoritms.recursive;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * @author mash0916
 */
public class QuickSortTask {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int segmentsCount = scanner.nextInt();
        int pointsCount = scanner.nextInt();

        int i = 0;
        Segment[] segments = new Segment[segmentsCount];
        int[] points = new int[pointsCount];
        int minBegin;
        int maxEnd;
        minBegin = scanner.nextInt();
        maxEnd = scanner.nextInt();
        segments[i++] = new Segment( minBegin, maxEnd);
        while (i < segmentsCount) {
            int begin = scanner.nextInt();
            if (begin < minBegin) minBegin = begin;
            int end = scanner.nextInt();
            if (end > maxEnd) maxEnd = end;
            segments[i++] = new Segment(begin, end);
        }

        int k = 0;
        while (k < pointsCount) {
            points[k++] = scanner.nextInt();
        }
        sort(segments, 0, segments.length - 1);
        Segment unionSegment = new Segment(minBegin, maxEnd);

        List<Integer> result = new ArrayList<>();
        for (int point : points) {
            int containCount = 0;
            for (Segment segment : segments) {
                if (!unionSegment.contain(point)) break;
                if (segment.begin > point) break;
                if (segment.contain(point)) containCount++;
            }
            result.add(containCount);
        }

        StringBuilder builder = new StringBuilder();
        for (Integer integer : result) {
            builder.append(integer).append(" ");
        }
        System.out.print(builder.toString().trim());
    }


    private static void sort(Segment[] segments, int begin, int end) {
        while (begin < end){
            int partisionIndex = findPartishion(segments, begin, end);
            sort(segments, begin, partisionIndex - 1);
            begin = ++partisionIndex;
        }
    }

    private static int findPartishion(Segment[] segments, int begin, int end) {

        Random random = new Random();

        int baseIndex = random.nextInt(end - begin) + begin;
        swap(begin,baseIndex,segments);
        baseIndex = begin;
        Segment baseElement = segments[baseIndex];

        for (int i = baseIndex + 1; i <= end; i++) {
            if (segments[i].compareTo(baseElement) <= 0){
                baseIndex++;
                swap(baseIndex, i, segments);
            }
        }

        swap(baseIndex, begin, segments);
        return baseIndex;
    }

    private static void swap(int to, int from, Segment[] segments) {
        Segment tmp = segments[from];
        segments[from] = segments[to];
        segments[to] = tmp;
    }

    static class Segment implements Comparable<Segment> {
        int begin;
        int end;

        public Segment(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }

        boolean contain(int coordinate) {
            return coordinate >= begin && coordinate <= end;
        }

        @Override
        public int compareTo(Segment that) {
            return this.begin - that.begin;
        }

        @Override
        public String toString() {
            return "Segment{" +
                    "begin=" + begin +
                    ", end=" + end +
                    '}';
        }
    }

}
