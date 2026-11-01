componenttype InnerCalc(
    port in data x,
    port in data y,
    port out data o
);
     repeat (4) period=1 set o = ((x + 1) * 2) + (y / 2);
endcomponenttype

componenttype OuterCalc(
    port in data a,
    port in data b,
    port out data outt
);
    data bet = 0;
    InnerCalc u1(.x(a), .y(b), .o(bet));
    repeat (4) period=1 set outt = bet + 10;
endcomponenttype

configuration test4();
    data A = 2;
    data B = 4.0;
    data signed C = -2;
    data T1 = 0;

    repeat (4) period=1 T1 = (A * 2) + (C + 3);
    OuterCalc u1(.a(A), .b(B), .outt(C));
    repeat (4) period=1 display({A, B, C});
    repeat (4) period=1 display({T1});
endconfiguration
