package array.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description 有效的数独
 *
 * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 *
 *
 * 上图是一个部分填充的有效的数独。
 *
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 *
 * 示例 1:
 *
 * 输入:
 * [
 *   ['5','3','.','.','7','.','.','.','.'],
 *   ['6','.','.','1','9','5','.','.','.'],
 *   ['.','9','8','.','.','.','.','6','.'],
 *   ['8','.','.','.','6','.','.','.','3'],
 *   ['4','.','.','8','.','3','.','.','1'],
 *   ['7','.','.','.','2','.','.','.','6'],
 *   ['.','6','.','.','.','.','2','8','.'],
 *   ['.','.','.','4','1','9','.','.','5'],
 *   ['.','.','.','.','8','.','.','7','9']
 * ]
 * 输出: true
 * 示例 2:
 *
 * 输入:
 * [
 *   ['8','3','.','.','7','.','.','.','.'],
 *   ['6','.','.','1','9','5','.','.','.'],
 *   ['.','9','8','.','.','.','.','6','.'],
 *   ['8','.','.','.','6','.','.','.','3'],
 *   ['4','.','.','8','.','3','.','.','1'],
 *   ['7','.','.','.','2','.','.','.','6'],
 *   ['.','6','.','.','.','.','2','8','.'],
 *   ['.','.','.','4','1','9','.','.','5'],
 *   ['.','.','.','.','8','.','.','7','9']
 * ]
 * 输出: false
 * 解释: 除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。
 *      但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。
 * 说明:
 *
 * 一个有效的数独（部分已被填充）不一定是可解的。
 * 只需要根据以上规则，验证已经填入的数字是否有效即可。
 * 给定数独序列只包含数字 1-9 和字符 '.' 。
 * 给定数独永远是 9x9 形式的。
 *
 **/
public class P36_ValidSudoku {

    public boolean isValidSudoku(char[][] board) {
        /**
         * 巨慢写法
         */
        /*// 区块
        Map<Character, String> block = new HashMap(13);
        int blockIndex;
        // 行
        Map<Character, String> row = new HashMap(13);
        // 列
        Map<Character, String> col = new HashMap(13);
        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                if (board[i][j] != '.') {
                    blockIndex = i / 3 * 3 + j / 3;
                    if ((block.containsKey(board[i][j]) && block.get(board[i][j]).indexOf(String.valueOf(blockIndex))!=-1) ||
                            (row.containsKey(board[i][j]) && row.get(board[i][j]).indexOf(String.valueOf(i))!=-1) ||
                            (col.containsKey(board[i][j]) && col.get(board[i][j]).indexOf(String.valueOf(j))!=-1)) {
                        return false;
                    } else {
                        block.put(board[i][j], (block.get(board[i][j]) == null ? "" : block.get(board[i][j])) + blockIndex);
                        row.put(board[i][j], (row.get(board[i][j]) == null ? "" : row.get(board[i][j])) + i);
                        col.put(board[i][j], (col.get(board[i][j]) == null ? "" : col.get(board[i][j])) + j);
                    }
                }
            }
        }
        return true;*/

        /**
         * 这个针滴快
         */
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(board[i][j] != '.') {
                    // 排除同一行出现重复的情况
                    for(int k=8; k>j; k--) {
                        if(board[i][j] == board[i][k]) return false;
                    }
                    // 排除同一列出现重复的情况
                    for(int k=8; k>i; k--) {
                        if(board[i][j] == board[k][j]) return false;
                    }
                    // 排除同一区块出现重复的情况
                    for(int k=i+1; k%3!=0; k++) {
                        for(int h=j/3*3; h<j/3*3+3; h++) {
                            if (board[i][j] == board[k][h]) return false;
                        }
                    }
                }
            }
        }
        return true;
    }

}
