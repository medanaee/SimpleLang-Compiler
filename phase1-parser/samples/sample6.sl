componenttype Incrementer(
    port in  data [7:0] inp,
    port out data [7:0] outp
);
    set outp = inp + 8'd1;
endcomponenttype

componenttype Multiplier(
    port in  data [7:0] a,
    port in  data [7:0] b,
    port out data [15:0] product
);
    set product = a * b;
endcomponenttype

configuration Top_Basic(
    port in  data [7:0] X,
    port in  data [7:0] Y,
    port out data [15:0] Result
);
    data [7:0] inc_out;

    Incrementer u_inc (
        .inp (X),
        .outp(inc_out)
    );

    Multiplier u_mul (
        .a      (inc_out),
        .b      (Y),
        .product(Result)
    );

    repeat (1) period=10 display({"Input X:", X});
    repeat (1) period=10 display({"Input Y:", Y});
    repeat (1) period=10 display({"Incremented X (inc_out):", inc_out});
    repeat (1) period=10 display({"Final Product Result:", Result});
endconfiguration
