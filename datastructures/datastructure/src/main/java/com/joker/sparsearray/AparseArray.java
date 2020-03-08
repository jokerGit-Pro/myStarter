package com.joker.sparsearray;

/**
 * 功能描述
 *
 * @param: 稀疏数组解决棋盘保存问题
 * @return:
 * @anthor:Tom-Joker
 * @date:
 */
public class AparseArray {

  public static void main(String[] args) {
    // 创建原始的二维数组(棋盘)11*11
    // 0表示没有棋子,1表示黑子,2表示篮子
    int chessArr1[][] = new int[11][11];
    chessArr1[1][2] = 1; // 黑子
    chessArr1[2][3] = 2; // 篮子
    chessArr1[4][5] = 2; // 篮子
    // 输出原始二维数组(棋盘)
    System.out.println("原始二维数组");
    for (int[] row : chessArr1) {
      for (int data : row) {
        System.out.printf("%d\t", data);
      }
      System.out.println();
    }

    // 将二位数组转化为稀疏数组的思想
    int sum = 0;
    // 1.先遍历二维数组,得到非0数据的个数
    for (int i = 0; i < 11; i++) {
      for (int j = 0; j < 11; j++) {
        if (chessArr1[i][j] != 0) {
          sum++;
        }
      }
    }
    System.out.println("sum=" + sum);
    // 2.创建对应的稀疏数组
    int sparseArr[][] = new int[sum + 1][3];
    // 给稀疏数组赋值
    sparseArr[0][0] = 11;
    sparseArr[0][1] = 11;
    sparseArr[0][2] = sum;

    int count = 0;
    for (int i = 0; i < 11; i++) {
      for (int j = 0; j < 11; j++) {
        if (chessArr1[i][j] != 0) {
          count++;
          sparseArr[count][0] = i;
          sparseArr[count][1] = j;
          sparseArr[count][2] = chessArr1[i][j];
        }
      }
    }

    // 输出稀疏数组
    System.out.println("得到的稀疏数组为:~~~~~~~~~~");
    for (int i = 0; i < sparseArr.length; i++) {
      System.out.printf("%d\t%d\t%d\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
    }

    // 将稀疏数组恢复成二维数组
    int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];

    // 恢复后的二维数组
      System.out.println("恢复后的二维数组:");

    for (int i = 1; i < sparseArr.length; i++){
      chessArr2[sparseArr[i][0]][sparseArr[i][1]]=sparseArr[i][2];
    }

    for (int[] row :chessArr2 ) {
        for (int data : row){
            System.out.printf("%d\t",data);
        }
        System.out.println();
    }
  }
}
