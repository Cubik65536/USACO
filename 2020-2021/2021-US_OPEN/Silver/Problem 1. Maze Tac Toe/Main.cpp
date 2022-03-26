#include<bits/stdc++.h>
using namespace std;

int n;
char board[25][25][3];
bool visited[25][25][19683];
int pow3[10];

set<int> results;

bool didWin(int b) {
    int tttBoard[3][3];
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            tttBoard[i][j] = b % 3;
            b /= 3;
        }
    }
    for (int row = 0; row < 3; row++) {
        if (tttBoard[row][0] == 1 && tttBoard[row][1] == 2 && tttBoard[row][2] == 2) {
            return true;
        }
        if (tttBoard[row][0] == 2 && tttBoard[row][1] == 2 && tttBoard[row][2] == 1) {
            return true;
        }
    }
    for (int col = 0; col < 3; col++) {
        if (tttBoard[0][col] == 1 && tttBoard[1][col] == 2 && tttBoard[2][col] == 2) {
            return true;
        }
        if (tttBoard[0][col] == 2 && tttBoard[1][col] == 2 && tttBoard[2][col] == 1) {
            return true;
        }
    }
    if (tttBoard[0][0] == 1 && tttBoard[1][1] == 2 && tttBoard[2][2] == 2) {
        return true;
    }
    if (tttBoard[0][0] == 2 && tttBoard[1][1] == 2 && tttBoard[2][2] == 1) {
        return true;
    }
    if (tttBoard[0][2] == 1 && tttBoard[1][1] == 2 && tttBoard[2][0] == 2) {
        return true;
    }
    if (tttBoard[0][2] == 2 && tttBoard[1][1] == 2 && tttBoard[2][0] == 1) {
        return true;
    }
    return false;
}

void dfs(int i, int j, int b) {
    if (visited[i][j][b]) {
        return;
    }
    visited[i][j][b] = true;
    if (board[i][j][0] == 'M' || board[i][j][0] == 'O') {
        int row = board[i][j][1] - '1';
        int col = board[i][j][2] - '1';
        int index = row * 3 + col;
        int currentChar = (b / pow3[index]) % 3;
        if (currentChar == 0) {
            int newChar = board[i][j][0] == 'M' ? 1 : 2;
            b = (b % pow3[index]) + newChar * pow3[index] + (b - b % pow3[index + 1]);
            if (!visited[i][j][b] && didWin(b)) {
                results.insert(b);
                return;
            }
            visited[i][j][b] = true;
        }
    }
    if (board[i - 1][j][0] != '#') {
        dfs(i - 1, j, b);
    }
    if (board[i + 1][j][0] != '#') {
        dfs(i + 1, j, b);
    }
    if (board[i][j - 1][0] != '#') {
        dfs(i, j - 1, b);
    }
    if (board[i][j + 1][0] != '#') {
        dfs(i, j + 1, b);
    }
}

int main() {
    int bessieI, bessieJ;
    int bstate = 0;
    pow3[0] = 1;
    for (int i = 1; i < 10; i++) {
        pow3[i] = pow3[i - 1] * 3;
    }

    cin >> n;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> board[i][j][0] >> board[i][j][1] >> board[i][j][2];
            if (board[i][j][0] == 'B') {
                bessieI = i;
                bessieJ = j;
            }
        }
    }

    dfs(bessieI, bessieJ, bstate);

    cout << results.size() << endl;

    return 0;
}
