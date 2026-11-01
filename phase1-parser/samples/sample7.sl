componenttype FullAdder(
    port in  data A,
    port in  data B,
    port in  data Cin,
    port out data Sum,
    port out data Cout
);
    set Sum  = A ^ B ^ Cin;
    set Cout = (A & B) | (B & Cin) | (A & Cin);
endcomponenttype

componenttype AdderN #(parameter int N = 8)(
    port in  data [N-1:0] X,
    port in  data [N-1:0] Y,
    port in  data         Cin,
    port out data [N-1:0] Sum,
    port out data         Cout
);
    data [N:0] carry;
    set carry[0] = Cin;
    set Cout     = carry[N];

    index i;
    expand
      for (i = 0; i < N; i++) do : GEN_FA
        FullAdder fa (
          .A   (X[i]),
          .B   (Y[i]),
          .Cin (carry[i]),
          .Sum (Sum[i]),
          .Cout(carry[i+1])
        );
      enddo
    endexpand
endcomponenttype

configuration Top_ParametricAdder(
    port in  data [7:0] A,
    port in  data [7:0] B,
    port out data [8:0] SUM
);
    data [7:0] sum8;
    data       cout;

    AdderN #(.N(8)) u_add8 (
        .X   (A),
        .Y   (B),
        .Cin (1'b0),
        .Sum (sum8),
        .Cout(cout)
    );

    set SUM = {cout, sum8};
endconfiguration
