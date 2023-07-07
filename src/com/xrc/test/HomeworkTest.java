package com.xrc.test;

class GraphColoring {
    // 图结构
    private int[][] graph;
    // 颜色数量
    private int numColors;
    // 结点数量
    private int numNodes;
    // 结点颜色数组
    private int[] nodeColors;

    public GraphColoring(int[][] g, int nc) {
        graph = g;
        numColors = nc;
        numNodes = graph.length;
        nodeColors = new int[numNodes];
    }

    // 检查是否能给节点v涂上颜色color
    private boolean isColorValid(int v, int color) {
        for (int i = 0; i < numNodes; i++) {
            if (graph[v][i] == 1 && color == nodeColors[i]) {
                return false;
            }
        }
        return true;
    }

    // 使用回溯法来为节点涂色
    private boolean colorNode(int v) {
        if (v == numNodes) {
            return true;
        }

        for (int c = 1; c <= numColors; c++) {
            if (isColorValid(v, c)) {
                nodeColors[v] = c;
                if (colorNode(v + 1)) {
                    return true;
                }
                nodeColors[v] = 0;
            }
        }

        return false;
    }

    // 打印节点颜色
    public void printNodeColors() {
        System.out.println("Node colors:");
        for (int i = 0; i < numNodes; i++) {
            System.out.print(nodeColors[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // 有解测试用例
        int[][] graph1 = {{0, 1, 1, 1},
                {1, 0, 1, 0},
                {1, 1, 0, 1},
                {1, 0, 1, 0}};
        GraphColoring gc1 = new GraphColoring(graph1, 3);
        if (gc1.colorNode(0)) {
            gc1.printNodeColors();
        } else {
            System.out.println("No solution found.");
        }

        // 无解测试用例
        int[][] graph2 = {{0, 1, 1, 1, 1},
                {1, 0, 1, 1, 0},
                {1, 1, 0, 1, 1},
                {1, 1, 1, 0, 0},
                {1, 0, 1, 0, 0}
        };

        GraphColoring gc2 = new GraphColoring(graph2, 3);
        if (gc2.colorNode(0)) {
            gc2.printNodeColors();
        } else {
            System.out.println("No solution found.");
        }
    }
}
