package com.xrc.test;

import java.util.Arrays;
import java.util.Scanner;

class GraphIsomorphism {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        System.out.println("请分别输入两个图的顶点数");
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // 同构图测试案例
        int[][] graph1 = new int[n][n];
        int[][] graph2 = new int[m][m];

        System.out.println("请输入第一个邻接图");
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
              graph1[i][j] = scanner.nextInt();
            }
        }

        System.out.println("请输入第二个邻接图");
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                graph2[i][j] = scanner.nextInt();
            }
        }


        if (isXrc(graph1, graph2)) {
            System.out.println("这两个图同构");
        } else {
            System.out.println("这两个图不同构");
        }

    }

    public static boolean isXrc(int[][] graph1, int[][] graph2) {
        if (graph1 == null || graph2 == null || graph1.length != graph2.length) {
            return false;
        }

        int n = graph1.length;
        int[] vertexMapping = new int[n];
        Arrays.fill(vertexMapping, -1); // 初始化映射

        return matchVertices(graph1, graph2, vertexMapping, 0);
    }

    private static boolean matchVertices(int[][] graph1, int[][] graph2, int[] vertexMapping, int vertexIndex) {
        // 如果所有顶点都已经匹配完成，则两个图同构
        if (vertexIndex == vertexMapping.length) {
            return true;
        }

        for (int i = 0; i < vertexMapping.length; i++) {
            // 如果顶点i未被匹配且与当前顶点的度序列相同，则可以将其作为当前顶点的匹配
            if (vertexMapping[i] == -1 && degreeSequenceEqual(graph1[vertexIndex], graph2[i])) {
                vertexMapping[i] = vertexIndex; // 记录顶点匹配
                if (matchVertices(graph1, graph2, vertexMapping, vertexIndex + 1)) {
                    return true; // 成功匹配，返回true
                }
                vertexMapping[i] = -1; // 回溯
            }
        }

        return false; // 所有匹配都失败，返回false
    }

    private static boolean degreeSequenceEqual(int[] degreeSequence1, int[] degreeSequence2) {
        if (degreeSequence1 == null || degreeSequence2 == null || degreeSequence1.length != degreeSequence2.length) {
            return false;
        }

        Arrays.sort(degreeSequence1);
        Arrays.sort(degreeSequence2);

        return Arrays.equals(degreeSequence1, degreeSequence2);
    }
}
