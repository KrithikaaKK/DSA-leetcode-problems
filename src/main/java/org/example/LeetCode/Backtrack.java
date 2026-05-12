package org.example.LeetCode;

public class Backtrack {

    public boolean exist(char[][] board, String word) {

        for(int i=0;i<board.length;i++) {
            for(int j=0;j<board[0].length;j++) {
                if(dfs(board,word,0,i,j)){
                    return true;
                }
            }
        }
        return false;

    }

    public boolean dfs(char[][] board,String word,int idx,int r,int c){
        if(idx==word.length()){
            return true;
        }

        if(r >= board.length || r<0 ) return false;
        if(c < 0 || c >= board[0].length) return false;
        if(board[r][c] != word.charAt(idx)) return false;
        if(board[r][c] == '#') return false;
        char temp = board[r][c];
        board[r][c] = '#';


        boolean found = dfs (board,word,idx+1,r+1,c) ||
                dfs (board, word, idx+1 , r-1, c) ||
                dfs(board , word , idx+1,r,c-1) ||
                dfs(board, word, idx+1 , r, c+1);
        board[r][c] = temp;   // important to undo the changes

        return found;


    }
}
