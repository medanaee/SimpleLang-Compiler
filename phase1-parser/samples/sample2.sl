configuration matrix_multiplication;

    parameter M = 2;
    parameter N = 3;
    parameter P = 2;

    data A[M][N];
    data B[N][P];
    data C[M][P];

    data i = 0;
    data j = 0;
    data k = 0;

    repeat (FOREVER) period=4 k = (k < N-1) ? (k + 1) : 0;

    repeat (FOREVER) period=4
        j = (k == N-1) ? ((j < P-1) ? (j + 1) : 0) : j;

    repeat (FOREVER) period=4
        i = ((k == N-1) && (j == P-1)) ? ((i < M-1) ? (i + 1) : 0) : i;

    repeat (12) period=4
        C[i][j] = (k == 0) ? (A[i][0] * B[0][j]) : (C[i][j] + A[i][k] * B[k][j]);

    repeat (12) period=4
        display({"i=", i, ", j=", j, ", k=", k, ", C[i][j]=", C[i][j]});

endconfiguration