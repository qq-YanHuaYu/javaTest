package com.xrc.test;

import java.util.ArrayList;
import java.util.List;

public class TSP {
    private static int[][] graph; // 存储图的邻接矩阵
    private static int numCities; // 城市数量
    private static boolean[] visited; // 记录城市是否被访问
    private static int[] path; // 存储当前路径
    private static int minCost; // 记录最小成本
    private static List<Integer> minPath; // 存储最小路径

    // TSP求解函数
    private static void tsp(int currentCity, int depth, int cost) {
        visited[currentCity] = true;
        path[depth] = currentCity;

        if (depth == numCities - 1) { // 所有城市都已经遍历
            if (graph[currentCity][0] != 0) { // 最后一个城市和起始城市有连接
                cost += graph[currentCity][0]; // 加上回到起始城市的成本
                if (cost < minCost) {
                    minCost = cost;
                    minPath = new ArrayList<>();
                    for (int i = 0; i < numCities; i++) {
                        minPath.add(path[i]);
                    }
                }
            }
        } else {
            for (int i = 0; i < numCities; i++) {
                if (!visited[i] && graph[currentCity][i] != 0) {
                    tsp(i, depth + 1, cost + graph[currentCity][i]);
                }
            }
        }

        visited[currentCity] = false; // 回溯
    }

    public static void main(String[] args) {
        // 初始化图的邻接矩阵
        numCities = 4;
        graph = new int[][]{
                {0, 10, 15, 20},
                {10, 0, 35, 25},
                {15, 35, 0, 30},
                {20, 25, 30, 0}
        };
        visited = new boolean[numCities];
        path = new int[numCities];
        minCost = Integer.MAX_VALUE;

        tsp(0, 0, 0);

        if (minPath != null) {
            System.out.println("有解，最小成本为：" + minCost);
            System.out.println("最小路径为：" + minPath);
        } else {
            System.out.println("无解");
        }

        // 重置变量
        for (int i = 0; i < numCities; i++) {
            visited[i] = false;
        }
        minCost = Integer.MAX_VALUE;
        minPath = null;

        // 设置无解的测试用例
        numCities = 3; // 修改城市数量
        graph = new int[][]{
                {0, 10, 15},
                {10, 0, 0},
                {15, 0, 0}
        };
        visited = new boolean[numCities];
        path = new int[numCities];

        tsp(0, 0, 0);

        if (minPath != null) {
            System.out.println("有解，最小成本为：" + minCost);
            System.out.println("最小路径为：" + minPath);
        } else {
            System.out.println("无解");
        }
    }
}

